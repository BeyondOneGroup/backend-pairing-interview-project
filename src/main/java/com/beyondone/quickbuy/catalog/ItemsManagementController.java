package com.beyondone.quickbuy.catalog;

import com.beyondone.quickbuy.model.items.management.ItemIdsDto;
import com.beyondone.quickbuy.model.items.management.ItemsManagementLineDto;
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
@RequestMapping("items-management")
public class ItemsManagementController {

    @Autowired
    private ItemsService itemsService;

    @GetMapping
    public ResponseEntity<List<ItemsManagementLineDto>> getInventory() {
        final List<ItemsManagementLineDto> items = itemsService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<List<ItemsManagementLineDto>> upsert(@RequestBody List<ItemsManagementLineDto> itemsManagementLineDtos) {
        itemsManagementLineDtos.forEach(line -> itemsService.addItem(line.getItem(), line.getQuantity()));
        return ResponseEntity.ok(itemsManagementLineDtos);
    }

    @DeleteMapping
    public ResponseEntity<ItemIdsDto> delete(@RequestBody ItemIdsDto itemIdsDto) {
        itemsService.delete(itemIdsDto);
        return ResponseEntity.ok(itemIdsDto);
    }
}
