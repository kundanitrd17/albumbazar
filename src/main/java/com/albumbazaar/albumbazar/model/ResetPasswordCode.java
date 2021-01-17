package com.albumbazaar.albumbazar.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "created_at", "otp", "user_identifier" }))
public class ResetPasswordCode {

    @Id
    @NotNull
    @NotBlank
    @Column(name = "user_identifier", updatable = false, nullable = false)
    private String userIdentifierKey;

    @NotNull
    @NotBlank
    @Column(name = "otp", updatable = false, nullable = false)
    private String OTP;

    @Column(nullable = false, name = "created_at")
    @CreatedDate
    private Instant createdAt;

    public ResetPasswordCode() {
        this.createdAt = Instant.now();
    }

}
