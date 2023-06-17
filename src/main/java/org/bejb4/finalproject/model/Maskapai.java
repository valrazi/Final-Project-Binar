package org.bejb4.finalproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "maskapai")
public class Maskapai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMaskapai;

    private String namaMaskapai;

    private String kodeMaskapai;

    private String logoMaskapai;
}
