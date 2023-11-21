package com.article.article.model.response;

import com.article.article.model.dto.UserAccountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public record UserIdResponse(
        String userId
) {

    public static UserIdResponse of(String userId) {
        return new UserIdResponse(userId);
    }

    public static UserIdResponse from(UserAccountDto dto) {
        return new UserIdResponse(
                dto.userId()
        );
    }

}
