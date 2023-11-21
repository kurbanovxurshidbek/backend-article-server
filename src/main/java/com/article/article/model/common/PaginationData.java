package com.article.article.model.common;

import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationData {

    private int numberOfElements;

    private long totalElements;

    private int totalPages;

    private int currentPageNumber;

    private int pageSize;

    public static PaginationData build(Page<?> page) {
        return PaginationData.builder()
                .numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .pageSize(page.getSize())
                .currentPageNumber(page.getNumber())
                .build();
    }
}
