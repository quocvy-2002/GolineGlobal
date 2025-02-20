package com.example.demo.controller;

import com.example.demo.dto.request.CreateLessorRequest;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.UpdateLessorRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.LessorResponse;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.service.LessorService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("http://localhost:3000")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@RestController
@RequestMapping("/lessor")
public class LessorController {
    LessorService lessorService;
    @PostMapping
    ApiResponse<LessorResponse> createLessor(@RequestBody CreateLessorRequest request) {
        return ApiResponse.<LessorResponse>builder()
                .result(lessorService.createLessor(request))
                .build();
    }

    @PutMapping("/{lessorId}")
    ApiResponse<LessorResponse> updateLessor(@PathVariable Integer lessorId,@Valid @RequestBody UpdateLessorRequest request) {
        return ApiResponse.<LessorResponse>builder()
                .result(lessorService.updateLessor(lessorId,request))
                .build();
    }

    @GetMapping("/my-inf")
    public ApiResponse<LessorResponse> getMyInf() {
        return ApiResponse.<LessorResponse>builder()
                .result(lessorService.getMyInf())
                .build();
    }

    @DeleteMapping("/{lessorId}")
    ApiResponse<LessorResponse> deleteLessor(@PathVariable Integer lessorId) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(202);
        lessorService.deleteLessor(lessorId);
        apiResponse.setMessage("lessor had been delete");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = lessorService.login(request);

        return ApiResponse.<LoginResponse>builder()
                .code(1000)
                .message("Login successful")
                .result(loginResponse)
                .build();
    }

}

