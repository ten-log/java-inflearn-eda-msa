package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.RentStatus;
import com.msa.rental.domain.model.vo.RentalCardNo;
import com.msa.rental.domain.model.vo.ReturnItem;
import com.msa.rental.domain.model.vo.LateFee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCard {
    private RentalCardNo rentalCardNo;
    private IDName member;
    private RentStatus rentStatus;
    private LateFee lateFee;
    private List<RentalItem> rentalItemList = new ArrayList<RentalItem>();
    private List<ReturnItem> returnItemList = new ArrayList<ReturnItem>();

    public static RentalCard sample(){
        RentalCard rentalCard = new RentalCard();
        rentalCard.setRentalCardNo(RentalCardNo.crateRentalCardNo());
        rentalCard.setMember(IDName.sampe());
        rentalCard.setRentStatus(RentStatus.RENT_AVAILABLE);
        rentalCard.setLateFee((LateFee.sample()));
        return rentalCard;
    }

    private void addRentalItem(RentalItem rentalItem){
        this.rentalItemList.add(rentalItem);
    }
    private void removeRentalItem(RentalItem rentalItem){
        this.rentalItemList.remove(rentalItem);
    }
    private void addReturnItem(ReturnItem returnItem){
        this.returnItemList.add(returnItem);
    }

}