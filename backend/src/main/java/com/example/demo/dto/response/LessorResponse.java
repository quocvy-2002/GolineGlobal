package com.example.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessorResponse {
    String lessorName;
    String lessorEmail;
    String lessorAddress;
    String lessorPassword;
    String lessorStatus;
}
