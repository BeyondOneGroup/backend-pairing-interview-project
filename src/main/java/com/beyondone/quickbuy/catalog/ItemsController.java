package com.beyondone.quickbuy.catalog;

import com.beyondone.quickbuy.model.items.LineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @GetMapping
    public ResponseEntity<List<LineDto>> getItems() {
        final List<LineDto> availableItems = itemsService.getAllAvailableItems();
        return ResponseEntity.ok(availableItems);
    }
}
