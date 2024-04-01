package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.RentalCardOuputPort;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDTO;
import com.msa.rental.application.usecase.CreateRentalCardUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateRentalCardInputPort implements CreateRentalCardUsecase {
    private final RentalCardOuputPort rentalCardOuputPort;
    @Override
    public RentalCardOutputDTO createRentalCard(UserInputDTO owner) {
        RentalCard rentalCard = RentalCard.createRentalCard(new
                IDName(owner.getUserId(), owner.getUserNm()));
        RentalCard save = rentalCardOuputPort.save(rentalCard);
        return RentalCardOutputDTO.mapToDTO(save);
    }
}
