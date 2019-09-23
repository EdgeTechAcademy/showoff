package com.edgetech.showoff.service;

import com.edgetech.showoff.model.Inventory;
import com.edgetech.showoff.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Iterable<Inventory> listAllInventorys() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Integer id) {
        Optional<Inventory> oInventory = inventoryRepository.findById(id);
        return oInventory.orElse(null);
    }

    public Inventory saveInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
        return inventory;
    }

    public void deleteInventory(Integer id) {
        Optional<Inventory> oInventory = inventoryRepository.findById(id);
        oInventory.ifPresent(inventory -> inventoryRepository.delete(inventory));
    }

    public Iterable<Inventory> findByModel(String carModel) {
        return inventoryRepository.findByModel(carModel);
    }
    public Iterable<Inventory> findByMake (String carMake) {
        return inventoryRepository.findByMake(carMake);
    }

}
