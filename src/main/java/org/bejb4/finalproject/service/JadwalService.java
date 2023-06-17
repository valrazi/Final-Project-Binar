package org.bejb4.finalproject.service;

import org.bejb4.finalproject.model.Jadwal;
import org.bejb4.finalproject.repository.JadwalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class JadwalService {
    @Autowired
    private JadwalRepository jadwalRepository;
    public List<Jadwal> getAllJadwal(){
        return jadwalRepository.findAll();
    }

    public List<Jadwal> getJadwalByTglKeberangkatan(LocalDate tgl, String kotaBrgkt, String kotaKedatangan){
        return jadwalRepository.findJadwalByTglKeberangkatan(tgl, kotaBrgkt, kotaKedatangan);
    }

    public Jadwal addJadwal(Jadwal jadwal){
        Jadwal newJadwal = new Jadwal();
        newJadwal.setTglKeberangkatan(jadwal.getTglKeberangkatan());
        newJadwal.setTglKedatangan(jadwal.getTglKedatangan());
        newJadwal.setJamKeberangkatan(jadwal.getJamKeberangkatan());
        newJadwal.setJamKedatangan(jadwal.getJamKedatangan());
        newJadwal.setHargaTiket(jadwal.getHargaTiket());
        LocalTime jamKeberangkatan = jadwal.getJamKeberangkatan();
        LocalTime jamKedatangan = jadwal.getJamKedatangan();
        int hoursDurasi = jamKedatangan.minusHours(jamKeberangkatan.getHour()).getHour();
        int minuteDurasi = jamKedatangan.minusMinutes(jamKeberangkatan.getMinute()).getMinute();
        newJadwal.setDurasiKeberangkatan(hoursDurasi + "h " + minuteDurasi + "m");


        newJadwal.setKotaKeberangkatan(jadwal.getKotaKeberangkatan());
        newJadwal.setKotaKedatangan(jadwal.getKotaKedatangan());

        newJadwal.setMaskapai(jadwal.getMaskapai());

        newJadwal.setKelas(jadwal.getKelas());
        return jadwalRepository.save(newJadwal);
    }
}
