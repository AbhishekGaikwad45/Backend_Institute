package com.institute.service;

import com.institute.model.User;
import com.institute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private UserRepository userRepository;



    // -------------------------------
    //  NORMAL USER SIGNUP with OTP
    // -------------------------------


    public String registerUser(User user) {

        // Check if email already exists
        User existing = repo.findByEmail(user.getEmail());
        if (existing != null) {
            return "Email already exists!";
        }

        // Encrypt password 🔥 (VERY IMPORTANT)
//        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Activate user immediately
        user.setActive(true);

        // Remove OTP fields
        user.setOtp(null);
        user.setOtpExpiry(null);

        repo.save(user);

        return "User Registered Successfully";
    }




    // -------------------------------------------
    //  ADMIN OTP VERIFY
    // -------------------------------------------
    public String verifyAdminOtp(Long userId, String otp) {

        User user = repo.findById(userId).orElse(null);

        if (user == null) return "User not found!";
        if (user.isActive()) return "User already active!";
        if (user.getOtp() == null || user.getOtpExpiry().isBefore(Instant.now()))
            return "OTP expired!";
        if (!user.getOtp().equals(otp)) return "Invalid OTP!";

        // OTP Correct → Activate Account
        user.setActive(true);
        user.setOtp(null);
        user.setOtpExpiry(null);
        repo.save(user);

        return "User activated successfully!";
    }


    // -------------------------------------------
    //  NORMAL LOGIN (NO OTP REQUIRED)
    // -------------------------------------------
    public String loginUser(String email, String password) {

        User user = repo.findByEmail(email);

        if (user == null) return "User Not Found!";
        if (!user.getPassword().equals(password)) return "Incorrect Password!";

        return "Login Successful";
    }





    // -------------------------------------------
    //  CHECK EMAIL EXISTS
    // -------------------------------------------
    public boolean checkEmailExists(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}
