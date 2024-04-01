package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.RentalCardOuputPort;
import com.msa.rental.application.usecase.ReturnItemUsercase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
@Transactional
public class ReturnItemInputPort implements ReturnItemUsercase {
    private final RentalCardOuputPort rentalCardOuputPort;

    @Override
    public RentalCardOutputDTO returnItem(UserItemInputDTO returnDto) throws Exception {
        // OutputPort를 사용해서 rental를 검색한 후
        // 없으면 에러 ,있으면 도서 반납
        RentalCard rental =
                rentalCardOuputPort.loadRentalCard(returnDto.getUserId())
                        .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));

        Item returnItem = new Item(returnDto.getItemId(), returnDto.getItemTitle());
        rental = rental.returnItem(returnItem, LocalDate.now());
        return RentalCardOutputDTO.mapToDTO(rental);
    }
}
