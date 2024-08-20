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
    private String first_name;
    private String last_name;
    private String email;
    private String mobile;
    private boolean deleted;
}
