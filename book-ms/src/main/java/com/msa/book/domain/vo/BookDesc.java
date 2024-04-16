package com.msa.book.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDesc {
    private String description;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    private Source source;
    public static BookDesc createBookDesc(String author,
                                          String isbn,
                                          String description,
                                          LocalDate publicationDate,
                                          Source source) {
        return new BookDesc(description, author, isbn, publicationDate,
                source);
    }
    public static BookDesc sample() {
        return createBookDesc("마틴파울러",
                "123456789",
                "엔터프라이즈 아키텍처 패턴을 설명",
                LocalDate.now(),
                Source.SUPPLY);
    }
}