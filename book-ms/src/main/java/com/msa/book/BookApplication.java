package com.msa.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.stream.Collectors;

@SpringBootApplication
public class BookApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BookApplication.class, args);
    }


}
