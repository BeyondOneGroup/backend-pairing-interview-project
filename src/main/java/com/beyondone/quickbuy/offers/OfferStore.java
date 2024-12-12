package com.beyondone.quickbuy.offers;

import com.beyondone.quickbuy.model.offers.OfferDto;
import com.beyondone.quickbuy.model.offers.OffersManagementOfferDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OfferStore {

    private final Map<String, Offer> offers;

    public OfferStore(Map<String, Offer> offers) {
        this.offers = offers;
    }

    public List<OfferDto> getAllOffers() {
        return this.offers.values()
                .stream()
                .map(Offer::toOfferDto)
                .collect(Collectors.toList());
    }

    public List<OffersManagementOfferDto> getAllManagementOffers() {
        return this.offers.values()
                .stream()
                .map(Offer::toOfferManagementOfferDto)
                .collect(Collectors.toList());
    }

    public void addOffers(OffersManagementOfferDto offersManagementOfferDto) {
        final Offer offer = Offer.from(offersManagementOfferDto);
        offers.put(offer.getId(), offer);
    }

    public void delete(String offerId) {
        offers.remove(offerId);
    }
}
