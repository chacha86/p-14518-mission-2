package com.sbb_prac.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USERT");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
