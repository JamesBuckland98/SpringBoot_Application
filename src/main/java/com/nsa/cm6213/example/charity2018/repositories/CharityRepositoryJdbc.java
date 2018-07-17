package com.nsa.cm6213.example.charity2018.repositories;

import com.nsa.cm6213.example.charity2018.domain.Charity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CharityRepositoryJdbc implements CharityRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Charity> charityMapper;

    @Value("${sql.charity.by.name}")
    private String charityByNameSQL;

    @Value("${sql.charity.by.regid}")
    private String charityByRegIdSQL;

    @Value("${sql.charities.all}")
    private String charitiesAllSQL;



    @Autowired
    public CharityRepositoryJdbc(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        System.out.println(charityByRegIdSQL);
        System.out.println(charityByNameSQL);
        System.out.println(charitiesAllSQL);



        charityMapper = (rs, i) -> new Charity(
                rs.getString("name"),
                rs.getString("purpose"),
                rs.getString("logo_file_name"),
                rs.getString("registration_id"),
                true
        );
    }

    @Override
    public Optional<Charity> findOne(Long id) {


        //TODO

        return Optional.empty();
    }

    @Override
    public List<Charity> findByName(String aName) {
        return jdbcTemplate.query(
                charityByNameSQL,
                new Object[]{aName},
                charityMapper);

    }

    @Override
    public List<Charity> findAll() {
        return jdbcTemplate.query(
                charitiesAllSQL,
                new Object[]{},
                charityMapper);
    }

    @Override
    public Optional<Charity> findByRegistrationNumber(String regNo) {


        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        charityByRegIdSQL,
                        new Object[]{regNo},
                        charityMapper
                ));
    }


}
