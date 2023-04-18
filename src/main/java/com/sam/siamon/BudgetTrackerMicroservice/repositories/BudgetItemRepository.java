package com.sam.siamon.BudgetTrackerMicroservice.repositories;

import com.sam.siamon.BudgetTrackerMicroservice.models.BudgetItem.BudgetItem;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BudgetItemRepository extends CassandraRepository<BudgetItem, UUID> {
    @AllowFiltering
    List<BudgetItem> findByUser(UUID user);
}
