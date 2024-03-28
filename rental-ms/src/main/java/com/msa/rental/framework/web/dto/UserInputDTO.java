package com.msa.rental.framework.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInputDTO {
    public String UserId;
    public String UserNm;
}