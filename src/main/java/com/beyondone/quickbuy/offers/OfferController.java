package com.beyondone.quickbuy.offers;

import com.beyondone.quickbuy.model.offers.OfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("offers")
public class OfferController {

    @Autowired
    private OfferStore offerStore;

    @GetMapping
    public ResponseEntity<List<OfferDto>> getOffers() {
        final List<OfferDto> offers = offerStore.getAllOffers();
        return ResponseEntity.ok(offers);
    }
}
