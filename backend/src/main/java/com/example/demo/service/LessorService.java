package com.example.demo.service;

import com.example.demo.dto.request.CreateLessorRequest;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.UpdateLessorRequest;
import com.example.demo.dto.response.LessorResponse;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.entity.Lessor;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.LessorMapper;
import com.example.demo.repository.LessorRepository;
import com.example.demo.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LessorService {
    PasswordEncoder passwordEncoder;
    LessorRepository lessorRepository;
    LessorMapper lessorMapper;
    private static final String SECRET_KEY = "01234567890123456789012345678901";

    public LessorResponse createLessor(CreateLessorRequest request) {
        if (lessorRepository.existsLessorByLessorName(request.getLessorName())) {
            throw new AppException(ErrorCode.USERNAME_EXISTED);
        } else if (lessorRepository.existsLessorByLessorEmail(request.getLessorEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        Lessor lessor = lessorMapper.toLessor(request);
        lessor.setLessorPassword(passwordEncoder.encode(lessor.getLessorPassword()));
        return lessorMapper.toLessorResponse(lessorRepository.save(lessor));
    }

    public LessorResponse updateLessor(Integer lessorId ,UpdateLessorRequest request) {
        Lessor lessor = lessorRepository.findById(lessorId)
                .orElseThrow(() -> new AppException(ErrorCode.LESSOR_NOT_EXISTED));
        lessorMapper.updateLessor(lessor, request);
        return lessorMapper.toLessorResponse(lessorRepository.save(lessor));
    }

    public void deleteLessor(Integer lessorId) {
        lessorRepository.deleteById(lessorId);
    }

    public LessorResponse getMyInf() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            Lessor lessor = lessorRepository.findByLessorEmail(email)
                    .orElseThrow(() -> new AppException(ErrorCode.LESSOR_NOT_EXISTED));
            return lessorMapper.toLessorResponse(lessor);
        }
        throw new AppException(ErrorCode.UNAUTHORIZED);
    }


    public LoginResponse login(LoginRequest request) {
        Lessor lessor = lessorRepository.findByLessorEmail(request.getLessorEmail())
                .orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_FOUND));

        if (!passwordEncoder.matches(request.getLessorPassword(), lessor.getLessorPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        String token = JwtUtil.generateToken(lessor.getLessorEmail(), lessor.getLessorId());

        return new LoginResponse(token, lessorMapper.toLessorResponse(lessor));
    }



    public Integer extractUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("lessorId", Integer.class);
    }


}
