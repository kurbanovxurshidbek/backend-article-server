package com.article.article.model.common;

import jakarta.validation.Valid;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

import static com.project.articles.model.common.ErrorCodes.ERROR;
import static com.project.articles.model.common.ErrorCodes.SUCCESS;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Header<T> {

    @NotNull
    private LocalDateTime transactionTime;

    private String resultCode;

    private String resultMsg;

    @Valid
    private T data;

    private PaginationData pagination;

    public static <T> Header<T> ok() {
        return Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("0000")
                .resultMsg("OK")
                .build();
    }

    public static <T> Header<T> ok(T data) {
        return Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(SUCCESS)
                .resultMsg("OK")
                .data(data)
                .build();
    }

    public static <T> Header<T> ok(T data, PaginationData pagination) {
        return Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(SUCCESS)
                .resultMsg("OK")
                .data(data)
                .pagination(pagination)
                .build();
    }

    public static <T> Header<T> error() {
        return Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(ERROR)
                .resultMsg("ERROR")
                .build();
    }

    public static <T> Header<T> error(String msg) {
        return Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(ERROR)
                .resultMsg(msg)
                .build();
    }

    public static <T> Header<T> error(String customCode,
                                      String msg) {
        return Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(customCode)
                .resultMsg(msg)
                .build();
    }
}
