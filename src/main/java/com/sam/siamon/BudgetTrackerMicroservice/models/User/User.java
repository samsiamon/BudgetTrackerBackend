package com.sam.siamon.BudgetTrackerMicroservice.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends UserDTO {
    @PrimaryKey
    UUID id = UUID.randomUUID();
    public User(UserDTO userDTO) {
        super(
                userDTO.getEmail(),
                userDTO.getPassword()
        );
    }
}
