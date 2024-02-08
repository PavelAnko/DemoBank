package com.demo_banking.repository;

import com.demo_banking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT a FROM Account a WHERE a.account_usd_number = :cardNumber")
    Account findByCardNumber(@Param("cardNumber") String cardNumber);

    @Query("SELECT COUNT(a) > 0 FROM Account a WHERE a.account_usd_number = :accountUsdNumber")
    boolean existsByAccountUsdNumber(@Param("accountUsdNumber") String accountUsdNumber);

    @Query("SELECT COUNT(a) > 0 FROM Account a WHERE a.account_uah_number = :accountUahNumber")
    boolean existsByAccountUahNumber(@Param("accountUahNumber") String accountUahNumber);

    @Query("SELECT a FROM Account a WHERE a.user_email = :userEmail")
    Optional<Account> findByUserEmail(@Param("userEmail") String userEmail);
    @Query(value = "SELECT * FROM accounts WHERE user_id = :user_id", nativeQuery = true)
    List<Account> getAccountById(@Param("user_id") int user_id);

//    @Query(value = "SELECT * FROM accounts WHERE user_id = :user_id", nativeQuery = true)
//    BigDecimal getTotalBalance(@Param("user_id") int user_id);

    @Query("SELECT SUM(a.balance_usd) FROM Account a WHERE a.user_id = ?1")
    BigDecimal getUsdBalance(int user_id);

    @Query("SELECT SUM(a.balance_uah) FROM Account a WHERE a.user_id = ?1")
    BigDecimal getUahBalance(int user_id);


}
