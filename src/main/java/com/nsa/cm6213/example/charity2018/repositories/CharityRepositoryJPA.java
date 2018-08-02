package com.nsa.cm6213.example.charity2018.repositories;

import com.nsa.cm6213.example.charity2018.domain.Charity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import java.util.List;

@Repository
public interface CharityRepositoryJPA extends JpaRepository<Charity, Long>, CharityRepository {

//    sql.charity.by.search=select id, acronym, name, purpose, logo_file_name, registration_id  from charity where acronym=? or name like ? or registration_id = ?

    @Query(value = "select c from Charity c where c.acronym like %:term% or c.registrationNumber like %:term% or c.name LIKE %:term% ")
    public List<Charity> findBySearchTerm(@Param("term") String term);


}
