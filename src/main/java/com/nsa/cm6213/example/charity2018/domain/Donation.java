package com.nsa.cm6213.example.charity2018.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "donation")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount_in_pence")
    private Long amountInPence;

    @Column(name = "donation_date")
    private LocalDateTime donationDate;


    @Column(name = "is_own_money")
    private Boolean isOwnMoney;

    @Column(name = "has_no_benefit_to_donor")
    private Boolean hasNoBenefitToDonor;

    @Column(name = "wishes_to_gift_aid")
    private Boolean wishesToGiftAid;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "charity_id")
    private Charity charity;

    public void setWishesToGiftAid(Boolean yesNo) {
        this.wishesToGiftAid = yesNo;
        this.hasNoBenefitToDonor = yesNo;
        this.isOwnMoney = yesNo;
    }

}
