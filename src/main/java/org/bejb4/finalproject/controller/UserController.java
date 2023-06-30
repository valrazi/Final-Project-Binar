package org.bejb4.finalproject.controller;

import org.bejb4.finalproject.dto.BookingResponse;
import org.bejb4.finalproject.dto.UserDetailsResponse;
import org.bejb4.finalproject.model.Booking;
import org.bejb4.finalproject.model.Jadwal;
import org.bejb4.finalproject.model.user.User;
import org.bejb4.finalproject.repository.BookingRepository;
import org.bejb4.finalproject.repository.JadwalRepository;
import org.bejb4.finalproject.repository.UserRepository;
import org.bejb4.finalproject.security.jwt.AuthTokenFilter;
import org.bejb4.finalproject.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Object> userDetails(HttpServletRequest request) {
        try{
            String token = authTokenFilter.parseJwt(request);
            String username  = jwtUtils.getUserNameFromToken(token);

            User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
            return ResponseEntity.ok(new UserDetailsResponse(user.getId(), user.getUsername(), user.getEmail(),
                    user.getPhoneNumber(), user.getFullName()));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/booking")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Object> userAccess(HttpServletRequest request, @RequestBody Booking booking) {
        try{
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
            Booking bookingSaved = bookingRepository.save(newBooking);
            BookingResponse bookingResponse = new BookingResponse(bookingSaved.getBookingId(),
                    bookingSaved.getUser().getEmail(), booking.getJadwal(),
                    booking.getUser().getId(), booking.getUser().getUsername(),
                    booking.getUser().getPhoneNumber(), booking.getUser().getFullName(),
                    booking.getIsPaid(), booking.getJmlPenumpang(),
                    booking.getTotalHarga());
            return ResponseEntity.ok(bookingResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/order")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Object> userOrderHistory(HttpServletRequest request){
        try {
            String token = authTokenFilter.parseJwt(request);
            String username  = jwtUtils.getUserNameFromToken(token);
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
            List<BookingResponse> bookingResponseList = new ArrayList<BookingResponse>();
            bookingRepository.findByUser_Id(user.getId()).stream().forEach(booking -> {
                BookingResponse bookingResponse = new BookingResponse(
                        booking.getBookingId(), booking.getUser().getEmail(),
                        booking.getJadwal(), booking.getUser().getId(),
                        booking.getUser().getUsername(), booking.getUser().getPhoneNumber(),
                        booking.getUser().getFullName(), booking.getIsPaid(),
                        booking.getJmlPenumpang(), booking.getTotalHarga());
                bookingResponseList.add(bookingResponse);
            });

            return ResponseEntity.ok(bookingResponseList);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/booking/{bookingId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getBookingDetails(@PathVariable UUID bookingId){
        try{
            Booking bookingFound = bookingRepository.findById(bookingId).orElseThrow(() -> new IllegalArgumentException("booking not found"));
            return ResponseEntity.ok(new BookingResponse(bookingFound.getBookingId(), bookingFound.getUser().getEmail(),
                    bookingFound.getJadwal(), bookingFound.getUser().getId(), bookingFound.getUser().getUsername(),
                    bookingFound.getUser().getPhoneNumber(), bookingFound.getUser().getFullName(), bookingFound.getIsPaid(),
                    bookingFound.getJmlPenumpang(), bookingFound.getTotalHarga()));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/booking/{bookingId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Object> updateIsPaid(@PathVariable UUID bookingId){
       try{
           Booking bookingFound = bookingRepository.findById(bookingId).orElseThrow(() -> new IllegalArgumentException("booking not found"));
           bookingFound.setIsPaid(true);
           bookingRepository.save(bookingFound);
           return ResponseEntity.ok(new BookingResponse(bookingFound.getBookingId(), bookingFound.getUser().getEmail(),
                   bookingFound.getJadwal(), bookingFound.getUser().getId(), bookingFound.getUser().getUsername(),
                   bookingFound.getUser().getPhoneNumber(), bookingFound.getUser().getFullName(), bookingFound.getIsPaid(),
                   bookingFound.getJmlPenumpang(), bookingFound.getTotalHarga()));
       }catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

}