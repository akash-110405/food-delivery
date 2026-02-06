package com.microservices.food_delivery.service;

import com.microservices.food_delivery.dto.AuthResponse;
import com.microservices.food_delivery.entity.User;
import com.microservices.food_delivery.repository.UserRepository;
import com.microservices.food_delivery.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final EmailService emailService;
    private final SmsService smsService;
    private final JwtUtil jwtUtil;

    public AuthResponse loginWithEmailPassword(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getPassword() == null)
            throw new RuntimeException("Password login not enabled");

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid password");

        user.setTokenVersion(user.getTokenVersion() + 1);
        userRepository.save(user);

        String token =  jwtUtil.generateToken(
                user.getEmail(),
                user.getTokenVersion()
        );

        return new AuthResponse("Authorized successfulluy",
                HttpStatus.SC_OK,
                user.getRole().name(),
                token);
    }

    public String requestEmailOtp(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String otp = otpService.generateOtp();

        user.setOtp(otp);
        user.setOtpexpiry(otpService.expiryTime());
        userRepository.save(user);

        emailService.sendOtp(email, otp);

        return "OTP sent to email";
    }

    public AuthResponse verifyEmailOtp(String email, String otp) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        validateOtp(user, otp);
        clearOtp(user);

        user.setTokenVersion(user.getTokenVersion() + 1);
        userRepository.save(user);

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getTokenVersion()
        );
        return new AuthResponse("Authorized successfully",
                HttpStatus.SC_ACCEPTED,
                user.getRole().name(),
                token);
    }

    public String requestPhoneOtp(String phone) {

        User user = userRepository.findByPhoneNumber(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String otp = otpService.generateOtp();

        user.setOtp(otp);
        user.setOtpexpiry(otpService.expiryTime());
        userRepository.save(user);

        smsService.sendOtp(phone, otp);

        return "OTP sent to phone";
    }

    public AuthResponse verifyPhoneOtp(String phone, String otp) {

        User user = userRepository.findByPhoneNumber(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("EMAIL = " + user.getEmail());
        System.out.println("PHONE = " + user.getPhoneNumber());
        System.out.println("ROLE = " + user.getRole());
        System.out.println("TOKEN_VERSION = " + user.getTokenVersion());

        validateOtp(user, otp);
        clearOtp(user);

        user.setTokenVersion(user.getTokenVersion() + 1);
        userRepository.save(user);

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getTokenVersion()
        );
        return new AuthResponse("Authorized successfully",
                HttpStatus.SC_ACCEPTED,
                user.getRole().name(),
                token);
    }

    private void validateOtp(User user, String otp) {

        if (user.getOtp() == null)
            throw new RuntimeException("OTP expired");

        if (!user.getOtp().equals(otp))
            throw new RuntimeException("Invalid OTP");

        if (user.getOtpexpiry().isBefore(LocalDateTime.now()))
            throw new RuntimeException("OTP expired");
    }

    private void clearOtp(User user) {
        user.setOtp(null);
        user.setOtpexpiry(null);
        userRepository.save(user);
    }
}