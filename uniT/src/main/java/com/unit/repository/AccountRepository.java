package com.unit.repository;

import com.unit.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    @Query("select a from Account  a where a.isActive= :isActive and a.user.id=:userId")
    List<Account> findAllByUserId(@Param("userId") Long userId, boolean isActive);


    @Query("select a from Account  a where a.accountNumber= :accountNumber")
    Optional<Account> findByAccountNumber(String accountNumber);

    boolean existsAccountByAccountNumber(String accountNumber);
}
