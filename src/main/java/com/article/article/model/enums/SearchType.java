package com.article.article.model.enums;

import lombok.Getter;

@Getter
public enum SearchType {
    TITLE("Title"),
    CONTENT("Content"),
    ID("User ID"),
    NICKNAME("Nickname"),
    HASHTAG("Hashtag");

    private final String description;

    SearchType(String description) {
        this.description = description;
    }
}
