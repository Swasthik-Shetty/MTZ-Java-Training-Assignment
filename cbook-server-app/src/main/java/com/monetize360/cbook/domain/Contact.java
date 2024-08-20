package com.monetize360.cbook.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Contact {
    private UUID id;
    private String first_name;
    private String last_name;
    private String email;
    private String mobile;
    private boolean deleted;
}
