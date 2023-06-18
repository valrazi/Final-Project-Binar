package org.bejb4.finalproject.controller;

import org.bejb4.finalproject.model.Jadwal;
import org.bejb4.finalproject.service.JadwalService;
import org.bejb4.finalproject.utils.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/jadwal")
public class JadwalController {
    Logger logger = LoggerFactory.getLogger(JadwalController.class);
    @Autowired
    JadwalService jadwalService;
    @GetMapping
    public ResponseEntity<Object> getAllJadwal(@RequestParam(name = "date", required = false) String tgl,
                                               @RequestParam(name = "from", required = false) String from,
                                               @RequestParam(name = "to", required = false) String to,
                                               @RequestParam(name = "class", required = false) Integer idKelas){
         if(tgl != null && from != null && to != null && idKelas != null){
             LocalDate tglParsed = LocalDate.parse(tgl);
             return ResponseHandler.generateResponse("success", HttpStatus.OK, jadwalService.getJadwalByTglKeberangkatan(tglParsed, from, to, idKelas));
         }
         return ResponseHandler.generateResponse("success", HttpStatus.OK, jadwalService.getAllJadwal());
    }

    @GetMapping("/harga")
    public ResponseEntity<Object> getHargaJadwal(@RequestParam(name = "date") String tgl,
                                                 @RequestParam(name = "from") String from,
                                                 @RequestParam(name = "to") String to,
                                                 @RequestParam(name = "class") Integer idKelas){
        try{
            LocalDate tglParsed = LocalDate.parse(tgl);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, jadwalService.getHarga(tglParsed, from, to, idKelas));
        }catch (Exception e){
            return ResponseHandler.generateResponse("error", HttpStatus.BAD_REQUEST, null);
        }
    }



    @PostMapping
    public ResponseEntity<Object> addJadwal(@RequestBody Jadwal jadwal){
        try{
            return ResponseHandler.generateResponse("success", HttpStatus.CREATED, jadwalService.addJadwal(jadwal));
        }catch (Exception e){
            return ResponseHandler.generateResponse("error", HttpStatus.BAD_REQUEST, null);
        }
    }
}
