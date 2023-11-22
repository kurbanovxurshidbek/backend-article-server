package com.article.article.model.enums;

import lombok.Getter;

public enum SearchType {
    TITLE("Title"),
    CONTENT("Content"),
    ID("User ID"),
    NICKNAME("Nickname");

    @Getter
    private final String description;

    SearchType(String description) {
        this.description = description;
    }
}
