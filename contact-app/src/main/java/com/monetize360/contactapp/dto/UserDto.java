package com.monetize360.contactapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private UUID id;

    private String userName;

    private String password;

    private String email;

    private String mobile;

    private boolean deleted;
}
