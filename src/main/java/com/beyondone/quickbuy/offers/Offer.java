package com.beyondone.quickbuy.offers;

import com.beyondone.quickbuy.model.offers.OfferDto;
import com.beyondone.quickbuy.model.offers.OffersManagementOfferDto;
import com.beyondone.quickbuy.utils.Money;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Offer {
    private String id;
    private String name;
    private String description;
    private String itemId;
    private Money priceReduction;
    private Integer quantityThreshold;

    public static Offer from(OffersManagementOfferDto offersManagementOfferDto) {
        return Offer.builder()
                .id(offersManagementOfferDto.getId())
                .description(offersManagementOfferDto.getDescription())
                .name(offersManagementOfferDto.getName())
                .itemId(offersManagementOfferDto.getItemId())
                .priceReduction(new Money(offersManagementOfferDto.getPriceReduction()))
                .quantityThreshold(offersManagementOfferDto.getQuantityThreshold())
                .build();
    }

    public OfferDto toOfferDto() {
        final OfferDto offerDto = new OfferDto();
        offerDto.setId(this.getId());
        offerDto.setName(this.getName());
        offerDto.setItemId(this.getItemId());
        offerDto.setDescription(this.getDescription());
        offerDto.setQuantityThreshold(this.getQuantityThreshold());
        offerDto.setPriceReduction(this.getPriceReduction().getValue());
        return offerDto;
    }

    public OffersManagementOfferDto toOfferManagementOfferDto() {
        final OffersManagementOfferDto offersManagementOfferDto = new OffersManagementOfferDto();
        offersManagementOfferDto.setId(this.getId());
        offersManagementOfferDto.setName(this.getName());
        offersManagementOfferDto.setItemId(this.getItemId());
        offersManagementOfferDto.setDescription(this.getDescription());
        offersManagementOfferDto.setQuantityThreshold(this.getQuantityThreshold());
        offersManagementOfferDto.setPriceReduction(this.getPriceReduction().getValue());
        return offersManagementOfferDto;
    }
}
