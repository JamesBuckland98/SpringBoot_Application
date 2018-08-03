package com.nsa.cm6213.example.charity2018.repositories;

import com.nsa.cm6213.example.charity2018.domain.Charity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase
public class CharityRepositoryTest {


    @Autowired
    private CharityRepository charityRepository;

    @Test
    public void findCharityByRegistrationNumber() {
        Charity aCharity = this.charityRepository.findBySearchTerm("12345678").get(0);

        assertThat(aCharity.getAcronym()).isEqualTo("NSPCC");
        assertThat(aCharity.getLogoPath()).isEqualTo("nspcc");
    }

    @Test
    public void findCharityByAcronymInSearch() {
        Charity aCharity = this.charityRepository.findBySearchTerm("NSPCC").get(0);

        assertThat(aCharity.getAcronym()).isEqualTo("NSPCC");
        assertThat(aCharity.getLogoPath()).isEqualTo("nspcc");
    }


    /**
     * This test should fail, but MySQL queries are not case-sensitive
     */
    @Test
    public void findCharityByAcronymInSearchLowerCase() {
        Charity aCharity = this.charityRepository.findBySearchTerm("nspcc").get(0);

        assertThat(aCharity.getAcronym()).isEqualTo("NSPCC");
        assertThat(aCharity.getLogoPath()).isEqualTo("nspcc");
    }

    @Test
    public void findCharityBySearchForCancer() {
        List<Charity> cancerCharities = this.charityRepository.findBySearchTerm("Cancer");

        assertEquals(3, cancerCharities.size());
        //assertThat(cancerCharities.get(0).getAcronym()).isEqualTo("CRUK"); //What's wrong with this test???

    }


}
