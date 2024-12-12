package com.beyondone.quickbuy.offers;

import com.beyondone.quickbuy.model.offers.OfferDto;
import com.beyondone.quickbuy.model.offers.OffersManagementOfferDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class OfferStoreTest {

    private OfferStore offerStore;
    private Map<String, Offer> offers;

    @Before
    public void setUp() {
        offers = new HashMap<>();
        final Offer offerOne = OfferFixture.getOfferOne();
        offers.put(offerOne.getId(), offerOne);
        offerStore = new OfferStore(offers);
    }

    @Test
    public void getAllOffersTest() {

        final List<OfferDto> allOffers = offerStore.getAllOffers();

        assertNotNull(allOffers);
        allOffers.forEach(offerDto -> {
            final Offer offer = offers.get(offerDto.getId());
            assertEquals(offer.getId(), offerDto.getId());
            assertEquals(offer.getName(), offerDto.getName());
            assertEquals(offer.getDescription(), offerDto.getDescription());
            assertEquals(offer.getItemId(), offerDto.getItemId());
            assertEquals(offer.getQuantityThreshold(), offerDto.getQuantityThreshold());
            assertEquals(offer.getPriceReduction().getValue(), offerDto.getPriceReduction());
        });
    }

    @Test
    public void getAllManagementOffersTest() {

        final List<OffersManagementOfferDto> allOffers = offerStore.getAllManagementOffers();

        assertNotNull(allOffers);
        allOffers.forEach(offerDto -> {
            final Offer offer = offers.get(offerDto.getId());
            assertEquals(offer.getId(), offerDto.getId());
            assertEquals(offer.getName(), offerDto.getName());
            assertEquals(offer.getDescription(), offerDto.getDescription());
            assertEquals(offer.getItemId(), offerDto.getItemId());
            assertEquals(offer.getQuantityThreshold(), offerDto.getQuantityThreshold());
            assertEquals(offer.getPriceReduction().getValue(), offerDto.getPriceReduction());
        });
    }

    @Test
    public void addOffersTest() {

        final Offer offerOne = OfferFixture.getOfferTwo();
        offerStore.addOffers(offerOne.toOfferManagementOfferDto());

        assertEquals(2, offers.size());

        final Offer offer = offers.get(offerOne.getId());
        assertEquals(offerOne.getId(), offer.getId());
        assertEquals(offerOne.getName(), offer.getName());
        assertEquals(offerOne.getDescription(), offer.getDescription());
        assertEquals(offerOne.getItemId(), offer.getItemId());
        assertEquals(offerOne.getQuantityThreshold(), offer.getQuantityThreshold());
        assertEquals(offerOne.getPriceReduction().getValue(), offer.getPriceReduction().getValue());
    }

    @Test
    public void deleteOffersTest() {
        final Offer offerOne = OfferFixture.getOfferOne();
        offerStore.delete(offerOne.getId());

        assertFalse(offers.containsKey(offerOne.getId()));
    }
}