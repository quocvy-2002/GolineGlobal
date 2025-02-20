package com.example.demo.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid messega key error", HttpStatus.BAD_REQUEST),
    PRODUCT_EXISTED(1002, "Product existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must have at least 8 characters, 1 capital letter, 1 number and 1 special character.", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_FOUND(1005, "User not existed", HttpStatus.NOT_FOUND),
    EMAIL_EXISTED(1006, "Email existed", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    NUMBERPHONE_EXISTED(1007, "NumberPhone existed", HttpStatus.BAD_REQUEST),
    UNAUTHETICATED(1008, "unautheicated", HttpStatus.UNAUTHORIZED),
    LESSOR_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    STORENAME_EXISTED(1006, "Email existed", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_EXISTED(1005, "Product not existed", HttpStatus.NOT_FOUND),
    USER_EXISTED(1005, "User  existed", HttpStatus.NOT_FOUND),
    USERNAME_EXISTED(1003, "username already exists", HttpStatus.BAD_REQUEST),
    LESSOR_NOT_EXISTED(1002, "User not existed", HttpStatus.BAD_REQUEST),
    FORBIDDEN(1001, "Forbidden", HttpStatus.FORBIDDEN),
    INVALID_TOKEN(1002, "Invalid token", HttpStatus.UNAUTHORIZED);

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;


}
