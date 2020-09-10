package com.albumbazaar.albumbazar.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.dao.InventoryRepository;
import com.albumbazaar.albumbazar.model.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("inventoryService")
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    
    @Autowired
    public InventoryService(final InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }   


    public Inventory addItem(/*  Pass the form containing data  */) {
        
        final Inventory inventory = new Inventory(); // create proper model object
        
        // Validate details

        // save
        inventoryRepository.save(inventory);

        return inventory;

    }


    public Optional<List<Inventory>> getAllItem() {
        final List<Inventory> allItems = inventoryRepository.findAll().stream().collect(Collectors.toList());

        return Optional.of(allItems);
    }

    public Inventory getItem(final Long id) throws NoSuchElementException {
        final Inventory inventory = inventoryRepository.findById(id).get();

        return inventory;
    }

    public void deleteItem(final Long id) throws IllegalArgumentException {
       inventoryRepository.deleteById(id);
    }

    public Inventory updateItem(final Long id) throws NoSuchElementException {
        final Inventory inventory = inventoryRepository.findById(id).get();

        return inventory;
    }

}
