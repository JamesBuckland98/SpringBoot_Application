package com.nsa.cm6213.example.charity2018.repositories;

import com.nsa.cm6213.example.charity2018.domain.Charity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class CharityRepositoryTest {


    @Autowired
    private CharityRepository charityRepository;

    @Test
    public void findCharityByRegistrationNumber() {
        Charity aCharity = this.charityRepository.findByRegistrationNumber("12345678").get();

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
        List<Charity> cancerCharities = this.charityRepository.findBySearchTerm("cancer");

        assertEquals(3, cancerCharities.size());
        assertThat(cancerCharities.get(0).getAcronym()).isEqualTo("CRUK"); //What's wrong with this test???

    }



}
