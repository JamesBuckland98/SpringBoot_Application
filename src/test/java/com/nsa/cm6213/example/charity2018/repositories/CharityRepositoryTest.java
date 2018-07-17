package com.nsa.cm6213.example.charity2018.repositories;

import com.nsa.cm6213.example.charity2018.domain.Charity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class CharityRepositoryTest {


    @Autowired
    private CharityRepository charityRepository;

    @Test
    public void findCharityByRegistrationNumber() {
        Charity aCharity = this.charityRepository.findByRegistrationNumber("12345678").get();

        assertThat(aCharity.getName()).isEqualTo("NSPCC");
        assertThat(aCharity.getLogoPath()).isEqualTo("nspcc");
    }

    @Test
    public void findCharityByName() {
        Charity aCharity = this.charityRepository.findByName("NSPCC").get(0);

        assertThat(aCharity.getName()).isEqualTo("NSPCC");
        assertThat(aCharity.getLogoPath()).isEqualTo("nspcc");
    }


    /**
     * This test should fail, but MySQL queries are not case-sensitive
     */
    @Test
    public void findCharityByLowerCaseName() {
        Charity aCharity = this.charityRepository.findByName("nspcc").get(0);

        assertThat(aCharity.getName()).isEqualTo("NSPCC");
        assertThat(aCharity.getLogoPath()).isEqualTo("nspcc");
    }


}
