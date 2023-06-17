package org.bejb4.finalproject.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "kelas")
public class Kelas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idKelas;

    @Enumerated(EnumType.STRING)
    private NamaKelas namaKelas;
}
