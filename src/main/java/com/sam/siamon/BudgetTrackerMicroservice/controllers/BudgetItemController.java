package com.sam.siamon.BudgetTrackerMicroservice.controllers;

import com.sam.siamon.BudgetTrackerMicroservice.models.BudgetItem.BudgetItem;
import com.sam.siamon.BudgetTrackerMicroservice.models.BudgetItem.BudgetItemDTO;
import com.sam.siamon.BudgetTrackerMicroservice.services.BudgetItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/budgetItems")
public class BudgetItemController {
    private final BudgetItemService budgetItemService;
    @Autowired
    public BudgetItemController(BudgetItemService budgetItemService) { this.budgetItemService = budgetItemService; }
    @GetMapping("")
    public List<BudgetItem> getAll(@PathVariable("id") UUID id) {
        return budgetItemService.findAll();
    }
    @GetMapping("/{id}")
    public Optional<BudgetItem> getBudgetItemById(@PathVariable("id") UUID id) {
        return budgetItemService.findById(id);
    }
    @GetMapping("/user/{id}")
    public List<BudgetItem> getBudgetItemByUser(@PathVariable("id") UUID id) {
        return budgetItemService.findByUser(id);
    }
    @PostMapping("")
    public ResponseEntity<BudgetItem> createBudgetItem(@RequestBody BudgetItemDTO budgetItem) {
        return budgetItemService.createBudgetItem(budgetItem);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BudgetItem> updateBudgetItem(@PathVariable("id") UUID id, @RequestBody BudgetItemDTO budgetItemDTO) {
        return budgetItemService.updateBudgetItem(id, budgetItemDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBudgetItem(@PathVariable("id") UUID id) {
        return budgetItemService.deleteBudgetItemById(id);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteBudgetItemByUser(@PathVariable("id") UUID id) {
        return budgetItemService.deleteBudgetItemByUser(id);
    }
    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAll() {
        return budgetItemService.deleteAllBudgetItems();
    }
}
