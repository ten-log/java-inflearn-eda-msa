package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
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

    //대여카드 생성
    public static RentalCard createRentalCard(IDName creator){
        RentalCard rentalCard = new RentalCard();
        rentalCard.setRentalCardNo(RentalCardNo.crateRentalCardNo());
        rentalCard.setRentStatus(RentStatus.RENT_AVAILABLE);
        rentalCard.setLateFee(LateFee.createLateFee());
        return rentalCard;
    }

    //대여처리
    public RentalCard rentItem(Item item){
        checkRentalAvailable();
        this.addRentalItem(RentalItem.createRentalItem(item));
        return this;
    }

    private void checkRentalAvailable(){
        if(this.rentStatus == RentStatus.RENT_UNAVILABLE) throw new IllegalArgumentException("대여불가입니다");
        if(this.rentalItemList.size() >5) throw new IllegalArgumentException("이미 5원빌림 최대 대여수입니다");
    }

    public RentalCard returnItem(Item item, LocalDate returnDate){
        RentalItem rentalItem = this.rentalItemList.stream().filter(i-> i.getItem().equals(item)).findFirst().get();
        calculateLateFee(rentalItem, returnDate);
        this.addReturnItem(ReturnItem.createReturnItem(rentalItem));
        this.removeRentalItem(rentalItem);
        return this;
    }

    private void calculateLateFee(RentalItem rentalItem, LocalDate returnDate) {

        if(returnDate.compareTo(rentalItem.getOverdueDate()) > 0){
            long point;
            point = Period.between(rentalItem.getOverdueDate(), returnDate).getDays() * 10;
            LateFee addPoint = this.lateFee.addPoint(point);
            this.lateFee.setPoint(addPoint.getPoint());
        }
    }

    public RentalCard overdueItem(Item item){
        RentalItem rentalItem = this.rentalItemList.stream().filter(i-> i.getItem().equals(item)).findFirst().get();
        rentalItem.setOverdued(true);
        this.rentStatus = RentStatus.RENT_UNAVILABLE;

        //연체 억지로 만들기 - 불필요한 코드
        rentalItem.setOverdueDate(LocalDate.now().minusDays(1));
        return this;
    }

    public long makeAvailableRental(long point) throws Exception {
        if(this.rentalItemList.size() != 0) throw new IllegalArgumentException("모든도서가 반납되어야 정지를 해체할수있습니다 ");
        if(this.getLateFee().getPoint() != point) throw new IllegalArgumentException("해당 포인트로 연체를 해체할수 없습니다");

        this.setLateFee(lateFee.removePoint(point));
        if(this.getLateFee().getPoint() == 0 ){
            this.rentStatus = RentStatus.RENT_AVAILABLE;
        }
        return this.getLateFee().getPoint();

    }


}