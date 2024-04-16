package com.msa.book.domain;


import com.msa.book.domain.vo.BookDesc;
import com.msa.book.domain.vo.BookStatus;
import com.msa.book.domain.vo.Classfication;
import com.msa.book.domain.vo.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private long no;
    private String title;
    private BookDesc desc;
    private Classfication classfication;
    private BookStatus bookStatus;
    private Location location;
}
