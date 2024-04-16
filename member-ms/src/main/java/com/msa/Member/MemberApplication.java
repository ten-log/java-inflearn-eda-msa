package com.msa.rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.stream.Collectors;

@SpringBootApplication
public class MemberApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MemberApplication.class, args);
    }


}
