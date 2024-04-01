package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.RentalCardOuputPort;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import com.msa.rental.application.usecase.RentItemUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RentItemInputPort implements RentItemUsecase {
    private final RentalCardOuputPort rentalCardOuputPort;
    @Override
    public RentalCardOutputDTO rentItem(UserItemInputDTO rental) throws
            Exception {
        // 사용자의 RentalCard 검색 또는 새로 생성
        RentalCard rentalCard =
                rentalCardOuputPort.loadRentalCard(rental.getUserId())
                        .orElseGet(() -> RentalCard.createRentalCard(new
                                IDName(rental.getUserId(), rental.getUserNm())));
        // 대여할 아이템 생성 및 대여 처리
        Item newItem = new Item(rental.getItemId(), rental.getItemTitle());
        rentalCard.rentItem(newItem);
//       RentalCard save = rentalCardOutputPort.save(rentalCard);
        return RentalCardOutputDTO.mapToDTO(rentalCard);
    }
}