package com.sam.siamon.BudgetTrackerMicroservice.models.BudgetItem;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetItem extends BudgetItemDTO {
    @PrimaryKey
    @Setter(AccessLevel.NONE) UUID id = UUID.randomUUID();
    public BudgetItem(BudgetItemDTO dto) {
        super(
                dto.getUser(),
                dto.getName(),
                dto.getItemType(),
                dto.getAmount(),
                dto.getFrequency()
        );
    }
}
