package com.example.backend.infrastructure.database.entity;

import com.example.backend.infrastructure.database.repository.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity extends BaseEntity {

    private String name;

    private String email;

    private String phone;

    private LocalDate birthDate;

    private String role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public CustomerEntity(String id, String name, String email, String phone, LocalDate birthDate, String role, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
