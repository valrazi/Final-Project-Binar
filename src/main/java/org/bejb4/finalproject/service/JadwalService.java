package org.bejb4.finalproject.service;

import org.bejb4.finalproject.model.Jadwal;
import org.bejb4.finalproject.model.Kelas;
import org.bejb4.finalproject.repository.JadwalRepository;
import org.bejb4.finalproject.repository.KelasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class JadwalService {
    @Autowired
    private JadwalRepository jadwalRepository;

    @Autowired
    private KelasRepository kelasRepository;
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

    public List<Jadwal> addJadwal(Jadwal jadwal){

        List<Jadwal> jadwalList = new ArrayList<Jadwal>();
        for (int i = 1; i <= 4; i++) {
            Jadwal newJadwal = new Jadwal();
            newJadwal.setTglKeberangkatan(jadwal.getTglKeberangkatan());
            newJadwal.setTglKedatangan(jadwal.getTglKedatangan());
            newJadwal.setJamKeberangkatan(jadwal.getJamKeberangkatan());
            newJadwal.setJamKedatangan(jadwal.getJamKedatangan());
            LocalTime jamKeberangkatan = jadwal.getJamKeberangkatan();
            LocalTime jamKedatangan = jadwal.getJamKedatangan();
            int hoursDurasi = jamKedatangan.minusHours(jamKeberangkatan.getHour()).getHour();
            int minuteDurasi = jamKedatangan.minusMinutes(jamKeberangkatan.getMinute()).getMinute();
            newJadwal.setDurasiJam(hoursDurasi);
            newJadwal.setDurasiMenit(minuteDurasi);


            newJadwal.setKotaKeberangkatan(jadwal.getKotaKeberangkatan());
            newJadwal.setKotaKedatangan(jadwal.getKotaKedatangan());

            newJadwal.setMaskapai(jadwal.getMaskapai());
            Kelas kelas = kelasRepository.findById(i).orElseThrow(() -> new IllegalArgumentException("kelas not foubd"));
            if(i == 1){
                newJadwal.setHargaTiket(jadwal.getHargaTiket());
            }else if(i == 2){
                newJadwal.setHargaTiket(jadwal.getHargaTiket() * 2);
            }else if(i == 3){
                newJadwal.setHargaTiket(jadwal.getHargaTiket() * 4);
            }else if(i == 4){
                newJadwal.setHargaTiket(jadwal.getHargaTiket() * 8);
            }
            newJadwal.setKelas(kelas);
            jadwalList.add(newJadwal);
        }
        return jadwalRepository.saveAll(jadwalList);
    }
}
