package com.msa.rental.application.inputport;


import com.msa.rental.application.outputport.RentalCardOuputPort;
import com.msa.rental.application.usecase.ClearOverdueItemUsecase;
import com.msa.rental.application.usecase.OverdueItemUsercase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.framework.web.dto.ClearOverdueInfoDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.RentalResultOutputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClearOverDueItemInputPort implements ClearOverdueItemUsecase {

    private final RentalCardOuputPort rentalCardOuputPort;

    @Override
    public RentalResultOutputDTO clearOverdue(ClearOverdueInfoDTO
                                                     clearOverdueInfoDTO) throws Exception {
        RentalCard rentalCard = rentalCardOuputPort.loadRentalCard(clearOverdueInfoDTO.UserId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));

        rentalCard.makeAvailableRental(clearOverdueInfoDTO.getPoint());
        return RentalResultOutputDTO.mapToDTO(rentalCard);
    }
}


