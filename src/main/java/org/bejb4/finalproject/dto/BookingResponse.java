package org.bejb4.finalproject.dto;

import lombok.Data;
import org.bejb4.finalproject.model.Jadwal;
import org.bejb4.finalproject.model.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;


public class BookingResponse {
    private UUID bookingId;

    private Jadwal jadwal;

    private Long userId;
    private String email;
    private String username;
    private String phoneNum;
    private String fullName;

    private Boolean isPaid = false;

    private Integer jmlPenumpang;

    private Integer totalHarga;

    public BookingResponse(UUID bookingId, String email,Jadwal jadwal,
                           Long userId, String username, String phoneNum,
                           String fullName, Boolean isPaid,
                           Integer jmlPenumpang, Integer totalHarga) {
        this.bookingId = bookingId;
        this.jadwal = jadwal;
        this.userId = userId;
        this.username = username;
        this.phoneNum = phoneNum;
        this.fullName = fullName;
        this.isPaid = isPaid;
        this.jmlPenumpang = jmlPenumpang;
        this.totalHarga = totalHarga;
        this.email = email;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public Jadwal getJadwal() {
        return jadwal;
    }

    public void setJadwal(Jadwal jadwal) {
        this.jadwal = jadwal;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Integer getJmlPenumpang() {
        return jmlPenumpang;
    }

    public void setJmlPenumpang(Integer jmlPenumpang) {
        this.jmlPenumpang = jmlPenumpang;
    }

    public Integer getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(Integer totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
