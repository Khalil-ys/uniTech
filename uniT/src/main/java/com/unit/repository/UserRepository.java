package com.unit.repository;


import com.unit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPin(String pin);

    Optional<User> findById(Long id);

    Boolean existsByPin(String pin);
}
