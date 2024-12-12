package com.beyondone.quickbuy.offers;

import com.beyondone.quickbuy.model.offers.OfferDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OfferControllerTest {

    @InjectMocks
    OfferController offerController;

    @Mock
    OfferStore offerStore;

    @Test
    public void getOffers() {

        final OfferDto offerDto = OfferFixture.getOfferOne().toOfferDto();
        final List<OfferDto> offerDtos = Collections.singletonList(offerDto);
        when(offerStore.getAllOffers()).thenReturn(offerDtos);

        final ResponseEntity<List<OfferDto>> response = offerController.getOffers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        final List<OfferDto> responseOfferDtos = response.getBody();

        for (int i = 0; i < responseOfferDtos.size(); i++) {
            assertEquals(offerDtos.get(i).getId(), responseOfferDtos.get(i).getId());
            assertEquals(offerDtos.get(i).getName(), responseOfferDtos.get(i).getName());
            assertEquals(offerDtos.get(i).getDescription(), responseOfferDtos.get(i).getDescription());
            assertEquals(offerDtos.get(i).getItemId(), responseOfferDtos.get(i).getItemId());
            assertEquals(offerDtos.get(i).getQuantityThreshold(), responseOfferDtos.get(i).getQuantityThreshold());
            assertEquals(offerDtos.get(i).getPriceReduction(), responseOfferDtos.get(i).getPriceReduction());
        }

        verify(offerStore, times(1)).getAllOffers();
    }
}