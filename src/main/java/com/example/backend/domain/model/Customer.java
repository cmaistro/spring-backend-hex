package com.example.backend.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Customer {

    private UUID id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
