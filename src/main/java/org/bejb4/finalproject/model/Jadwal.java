package org.bejb4.finalproject.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "jadwal")
public class Jadwal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idJadwal;

    private LocalDate tglKeberangkatan;

    private LocalDate tglKedatangan;

    private LocalTime jamKeberangkatan;

    private LocalTime jamKedatangan;

    private String durasiKeberangkatan;

    private int hargaTiket;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_city_keberangkatan", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private City kotaKeberangkatan;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_city_kedatangan", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private City kotaKedatangan;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_maskapai", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Maskapai maskapai;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_kelas", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Kelas kelas;



}
