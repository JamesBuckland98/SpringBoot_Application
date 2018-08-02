package com.nsa.cm6213.example.charity2018.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "charity")
public class Charity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "acronym")
    private String acronym;

    @Column(name = "purpose")
    private String description;

    @Column(name = "logo_file_name")
    private String logoPath;

    @Column(name = "registration_id")
    private String registrationNumber;

    @Column(name = "is_active")
    private Boolean isActive;
}
