package org.bejb4.finalproject.repository;

import org.bejb4.finalproject.model.Jadwal;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface JadwalRepository extends JpaRepository<Jadwal, Long> {
    @Query("SELECT j from Jadwal j where j.tglKeberangkatan = ?1 AND j.kotaKeberangkatan.cityCode= ?2 AND j.kotaKedatangan.cityCode = ?3 AND j.kelas.idKelas = ?4")
    List<Jadwal> findJadwalByTglKeberangkatan(LocalDate tgl, String kotaBrgkt, String kotaKedatangan, Integer idKelas);


    @Query(value = "SELECT j.hargaTiket from Jadwal j where j.tglKeberangkatan = ?1 AND j.kotaKeberangkatan.cityCode= ?2 AND j.kotaKedatangan.cityCode = ?3 AND j.kelas.idKelas = ?4 ORDER BY j.hargaTiket ASC")
    List<Integer> findHarga(LocalDate tgl, String kotaBrgkt, String kotaKedatangan, Integer idKelas, Pageable pageable);




}
