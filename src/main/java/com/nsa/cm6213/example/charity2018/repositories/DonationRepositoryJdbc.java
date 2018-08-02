package com.nsa.cm6213.example.charity2018.repositories;

import com.nsa.cm6213.example.charity2018.controllers.DonationController;
import com.nsa.cm6213.example.charity2018.domain.Address;
import com.nsa.cm6213.example.charity2018.domain.Charity;
import com.nsa.cm6213.example.charity2018.domain.Donation;
import com.nsa.cm6213.example.charity2018.domain.Donor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.ZoneId;

@Repository
public class DonationRepositoryJdbc implements DonationRepository {

    static final Logger LOG = LoggerFactory.getLogger(DonationRepositoryJdbc.class);


    private JdbcTemplate jdbcTemplate;
    @Value("${sql.address.insert}")
    private String addressInsertSQL;

    @Value("${sql.donor.insert}")
    private String donorInsertSQL;

    @Value("${sql.donation.insert}")
    private String donationInsertSQL;

    @Value("${sql.donation.last.id}")
    private String lastDonationIdSQL;

    @Autowired
    public DonationRepositoryJdbc(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;
    }

    @Override
    public void save(Donation aDonation) {
        Donor aDonor = aDonation.getDonor();
        Charity aCharity = aDonation.getCharity();
        Address address = aDonor.getAddress();

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        //save address - consider refactoring.

        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(addressInsertSQL, Statement.RETURN_GENERATED_KEYS);

                        ps.setString(1, address.getStreet());
                        ps.setString(2, address.getDistrict());
                        ps.setString(3, address.getCity());
                        ps.setString(4, address.getPostCode());
                        ps.setString(5, address.getCountryCode());
                        return ps;
                    }
                },
                holder);

        address.setId(holder.getKey().longValue());

        //repeat pattern for donor

        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(donorInsertSQL, Statement.RETURN_GENERATED_KEYS);

                        ps.setString(1, aDonor.getFirstName());
                        ps.setString(2, aDonor.getLastName());
                        ps.setLong(3, address.getId());
                        return ps;
                    }
                },
                holder);

        aDonor.setId(holder.getKey().longValue());

        //repeat for donation

        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(donationInsertSQL, Statement.RETURN_GENERATED_KEYS);


                        ps.setLong(1, aDonation.getAmountInPence());
                        ps.setDate(2, java.sql.Date.valueOf(aDonation.getDonationDate().toLocalDate()));
                        ps.setBoolean(3, Boolean.TRUE);
                        ps.setBoolean(4, Boolean.TRUE);
                        ps.setBoolean(5, aDonation.getWishesToGiftAid());
                        ps.setLong(6, aDonor.getId());
                        ps.setLong(7, aCharity.getId());
                        return ps;
                    }
                },
                holder);

        aDonation.setId(holder.getKey().longValue());

        LOG.debug(aDonation.toString());

    }

    @Override
    public Long findLastDonationId() {
        return jdbcTemplate.queryForObject(
                lastDonationIdSQL,
                new Object[]{},
                Long.class);
    }
}
