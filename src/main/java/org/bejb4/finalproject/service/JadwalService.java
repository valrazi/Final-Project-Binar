package org.bejb4.finalproject.service;

import org.bejb4.finalproject.model.Jadwal;
import org.bejb4.finalproject.repository.JadwalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<Jadwal> getJadwalByTglKeberangkatan(LocalDate tgl, String kotaBrgkt, String kotaKedatangan, Integer idKelas){
        return jadwalRepository.findJadwalByTglKeberangkatan(tgl, kotaBrgkt, kotaKedatangan, idKelas);
    }

    public Object getHarga(LocalDate tgl, String kotaBrgkt, String kotaKedatangan, Integer idKelas){
        Pageable onePrice = PageRequest.of(0,1);
        List<Integer> dataHarga = jadwalRepository.findHarga(tgl, kotaBrgkt, kotaKedatangan, idKelas, onePrice);
        if(dataHarga.size() > 0){
            Integer hargaTermurah = dataHarga.get(0);
            return  hargaTermurah;
        }
        return null;
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
        newJadwal.setDurasiJam(hoursDurasi);
        newJadwal.setDurasiMenit(minuteDurasi);


        newJadwal.setKotaKeberangkatan(jadwal.getKotaKeberangkatan());
        newJadwal.setKotaKedatangan(jadwal.getKotaKedatangan());

        newJadwal.setMaskapai(jadwal.getMaskapai());

        newJadwal.setKelas(jadwal.getKelas());
        return jadwalRepository.save(newJadwal);
    }
}
