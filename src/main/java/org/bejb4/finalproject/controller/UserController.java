package org.bejb4.finalproject.controller;

import org.bejb4.finalproject.model.Booking;
import org.bejb4.finalproject.model.Jadwal;
import org.bejb4.finalproject.model.user.User;
import org.bejb4.finalproject.repository.BookingRepository;
import org.bejb4.finalproject.repository.JadwalRepository;
import org.bejb4.finalproject.repository.UserRepository;
import org.bejb4.finalproject.security.jwt.AuthTokenFilter;
import org.bejb4.finalproject.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthTokenFilter authTokenFilter;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    JadwalRepository jadwalRepository;

    @PostMapping("/booking")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Booking userAccess(HttpServletRequest request, @RequestBody Booking booking) {
        Booking newBooking = new Booking();
        String token = authTokenFilter.parseJwt(request);
        String username  = jwtUtils.getUserNameFromToken(token);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        Jadwal jadwal = jadwalRepository.findById(booking.getJadwal().getIdJadwal())
                .orElseThrow(() -> new UsernameNotFoundException("jadwal not found"));

        newBooking.setJadwal(jadwal);
        newBooking.setIsPaid(false);
        newBooking.setJmlPenumpang(booking.getJmlPenumpang());


        newBooking.setTotalHarga(jadwal.getHargaTiket() * booking.getJmlPenumpang());
        newBooking.setUser(user);
        return bookingRepository.save(newBooking);
    }

}