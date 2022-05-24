package com.example.AutoPartsStore.repo;

import com.example.AutoPartsStore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByActivationCode(String code);

    @Query("SELECT COUNT(u) FROM User u")
    Long countUsers();
}
