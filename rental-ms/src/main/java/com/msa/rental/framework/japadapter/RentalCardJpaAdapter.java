package com.msa.rental.framework.japadapter;

import com.msa.rental.application.outputport.RentalCardOuputPort;
import com.msa.rental.domain.model.RentalCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RentalCardJpaAdapter implements RentalCardOuputPort {
    private final RentalCardRepository rentalCardRepository;
    @Override
    public Optional<RentalCard> loadRentalCard(String userId) {
//        return rentalCardRepository.findByMemberId(userId).get();
        return null;
    }
    @Override
    public RentalCard save(RentalCard rentalCard) {
        return null;
//        return rentalCardRepository.save(rentalCard);
    }
}