package com.sam.siamon.BudgetTrackerMicroservice.models.BudgetItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetItemDTO {
    UUID user;
    String name;
    String itemType;
    Double amount;
    String frequency;
}
