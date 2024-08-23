package com.monetize360.cbook.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ContactDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private boolean deleted;
}
