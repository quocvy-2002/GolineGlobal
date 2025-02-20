package com.example.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateLessorRequest {
    String lessorName;
    String lessorEmail;
    String lessorAddress;
    String lessorPassword;
    String lessorStatus;
}
