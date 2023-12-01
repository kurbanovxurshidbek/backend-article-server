package com.article.article.model.enums;

import lombok.Getter;

@Getter
public enum RoleType {
    ROLE_USER("User"),
    ROLE_ADMIN("Admin");

    private final String description;

    RoleType(String description) {
        this.description = description;
    }
}
