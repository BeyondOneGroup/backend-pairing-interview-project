package com.beyondone.quickbuy.offers;

import com.beyondone.quickbuy.model.offers.OfferIdsDto;
import com.beyondone.quickbuy.model.offers.OffersManagementOfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("offers-management")
public class OfferManagementController {

    @Autowired
    OfferStore offerStore;

    @GetMapping
    public ResponseEntity<List<OffersManagementOfferDto>> getOffers() {
        final List<OffersManagementOfferDto> allOffers = offerStore.getAllManagementOffers();
        return ResponseEntity.ok(allOffers);
    }

    @PostMapping
    public ResponseEntity<List<OffersManagementOfferDto>> upsert(
            @RequestBody List<OffersManagementOfferDto> offersManagementOfferDtos) {
        offersManagementOfferDtos.forEach(offerStore::addOffers);
        return ResponseEntity.ok(offersManagementOfferDtos);
    }

    @DeleteMapping
    public ResponseEntity<OfferIdsDto> delete(@RequestBody OfferIdsDto offerIdsDto) {
        offerIdsDto.forEach(offerStore::delete);
        return ResponseEntity.ok(offerIdsDto);
    }
}
