package com.sam.siamon.BudgetTrackerMicroservice.repositories;

import com.sam.siamon.BudgetTrackerMicroservice.models.User.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends CassandraRepository<User, UUID> {
    @AllowFiltering
    Optional<User> findByEmail(String email);
}
