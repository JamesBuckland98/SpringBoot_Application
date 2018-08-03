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

    /**
     * Returns a list of charities that "like" match the term on acronym, reference number or name.
     * The binding can only be done once, and it appears that the last binding wins.
     * <p>
     * Alternatives would be
     * to custom implement the repository
     * to use a separate DonationSearchRepository and use JDBC
     * to change the method signature so that the term is based over more than once
     * to do separate searches and combine in Java     *
     *
     * @param term
     * @return
     */

    @Query(value = "select c from Charity c where c.acronym like %:term% or c.registrationNumber like %:term% or c.name LIKE %:term% ")
    public List<Charity> findBySearchTerm(@Param("term") String term);


}
