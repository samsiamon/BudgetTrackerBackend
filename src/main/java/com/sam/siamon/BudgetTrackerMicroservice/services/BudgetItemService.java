package com.sam.siamon.BudgetTrackerMicroservice.services;

import com.sam.siamon.BudgetTrackerMicroservice.models.BudgetItem.BudgetItem;
import com.sam.siamon.BudgetTrackerMicroservice.models.BudgetItem.BudgetItemDTO;
import com.sam.siamon.BudgetTrackerMicroservice.repositories.BudgetItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BudgetItemService {
    private final BudgetItemRepository budgetItemRepository;
    public BudgetItemService(BudgetItemRepository budgetItemRepository) {
        this.budgetItemRepository = budgetItemRepository;
    }
    public List<BudgetItem> findAll() {
        return budgetItemRepository.findAll();
    }
    public Optional<BudgetItem> findById(UUID id) {
        return budgetItemRepository.findById(id);
    }
    public List<BudgetItem> findByUser(UUID user) { return budgetItemRepository.findByUser(user); }
    public ResponseEntity<BudgetItem> createBudgetItem(BudgetItemDTO budgetItem) {
        try {
            BudgetItem _budgetItem = budgetItemRepository.save(new BudgetItem(budgetItem));
            return new ResponseEntity<>(_budgetItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<BudgetItem> updateBudgetItem(UUID id, BudgetItemDTO budgetItem){
        Optional<BudgetItem> budgetItemData = budgetItemRepository.findById(id);
        if (budgetItemData.isPresent()) {
            BudgetItem _budgetItem = budgetItemData.get();
            _budgetItem.setName(budgetItem.getName());
            _budgetItem.setAmount(budgetItem.getAmount());
            _budgetItem.setItemType(budgetItem.getItemType());
            _budgetItem.setFrequency(budgetItem.getFrequency());
            return new ResponseEntity<>(budgetItemRepository.save(_budgetItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<HttpStatus> deleteBudgetItemById(UUID id) {
        try {
            Optional<BudgetItem> _budgetItem = budgetItemRepository.findById(id);
            budgetItemRepository.deleteById(_budgetItem.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<HttpStatus> deleteBudgetItemByUser(UUID id) {
        try {
            List<BudgetItem> _budgetItems = budgetItemRepository.findByUser(id);
            budgetItemRepository.deleteAll(_budgetItems);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<HttpStatus> deleteAllBudgetItems() {
        budgetItemRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}