package com.beyondone.quickbuy.offers;

import com.beyondone.quickbuy.model.offers.OfferIdsDto;
import com.beyondone.quickbuy.model.offers.OffersManagementOfferDto;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OfferManagementControllerTest {

    @InjectMocks
    OfferManagementController offerManagementController;

    @Mock
    OfferStore offerStore;

    @Test
    public void getOffers() {

        final OffersManagementOfferDto offersManagementOfferDto = OfferFixture.getOfferOne().toOfferManagementOfferDto();
        final List<OffersManagementOfferDto> offersManagementOfferDtos = Collections.singletonList(offersManagementOfferDto);
        when(offerStore.getAllManagementOffers()).thenReturn(offersManagementOfferDtos);

        final ResponseEntity<List<OffersManagementOfferDto>> response = offerManagementController.getOffers();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        areEqual(offersManagementOfferDtos, response.getBody());
        verify(offerStore, times(1)).getAllManagementOffers();
    }

    @Test
    public void upsertTest() {

        final List<OffersManagementOfferDto> offersManagementOfferDtos =
                Collections.singletonList(OfferFixture.getOfferOne().toOfferManagementOfferDto());
        final ResponseEntity<List<OffersManagementOfferDto>> response = offerManagementController.upsert(offersManagementOfferDtos);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        areEqual(offersManagementOfferDtos, response.getBody());
        verify(offerStore, times(1)).addOffers(any(OffersManagementOfferDto.class));
    }

    @Test
    public void deleteTest() {
        final Offer offerOne = OfferFixture.getOfferOne();
        final OfferIdsDto offerIdsDto = new OfferIdsDto();
        offerIdsDto.add(offerOne.getId());

        offerManagementController.delete(offerIdsDto);

        verify(offerStore, times(1)).delete(offerOne.getId());
    }

    private void areEqual(List<OffersManagementOfferDto> offersManagementOfferDtos, List<OffersManagementOfferDto> responseBody) {
        for (int i = 0; i < responseBody.size(); i++) {
            final OffersManagementOfferDto expected = offersManagementOfferDtos.get(i);
            final OffersManagementOfferDto actual = responseBody.get(i);
            assertEquals(expected.getId(), actual.getId());
            assertEquals(expected.getName(), actual.getName());
            assertEquals(expected.getDescription(), actual.getDescription());
            assertEquals(expected.getQuantityThreshold(), actual.getQuantityThreshold());
            assertEquals(expected.getItemId(), actual.getItemId());
            assertEquals(expected.getPriceReduction(), actual.getPriceReduction());
        }
    }
}