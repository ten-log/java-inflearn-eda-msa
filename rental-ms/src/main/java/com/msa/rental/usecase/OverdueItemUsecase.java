package com.msa.rental.usecase;

import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;

public interface OverdueItemUsecase {
    public RentalCardOutputDTO rentItem(UserItemInputDTO rental) throws Exception;
}
