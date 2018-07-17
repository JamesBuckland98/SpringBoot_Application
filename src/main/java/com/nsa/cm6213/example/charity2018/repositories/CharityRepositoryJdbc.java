package com.nsa.cm6213.example.charity2018.repositories;

import com.nsa.cm6213.example.charity2018.domain.Charity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Repository
public class CharityRepositoryJdbc implements CharityRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Charity> charityMapper;
    private Map<String, String> queries;

    @Autowired
    public CharityRepositoryJdbc(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        queries = new TreeMap<String, String>();

        queries.put("charity_by_name", "select id, name, purpose, logo_file_name, registration_id  from charity where name=?");
        queries.put("charity_by_regid", "select id, name, purpose, logo_file_name, registration_id  from charity where registration_id=?");
        queries.put("charities all", "select id, name, purpose, logo_file_name, registration_id  from charity");

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


        Charity aCharity = jdbcTemplate.queryForObject(
                queries.get("charity_by_id"),
                new Object[]{id},
                charityMapper);

        return Optional.ofNullable(aCharity);
    }

    @Override
    public List<Charity> findByName(String aName) {
        return jdbcTemplate.query(
                queries.get("charity_by_name"),
                new Object[]{aName},
                charityMapper);

    }

    @Override
    public List<Charity> findAll() {
        return jdbcTemplate.query(queries.get("charities all"),
                new Object[]{},
                charityMapper);
    }

    @Override
    public Optional<Charity> findByRegistrationNumber(String regNo) {

        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        queries.get("charity_by_regid"),
                        new Object[]{regNo},
                        charityMapper
                ));
    }


}
