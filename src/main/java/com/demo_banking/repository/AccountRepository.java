package com.demo_banking.repository;
import com.demo_banking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.account_usd_number = :usdCardNumber")
    Account findByUsdCardNumber(String usdCardNumber);

    @Query("SELECT a FROM Account a WHERE a.account_uah_number = :uahCardNumber")
    Account findByUahCardNumber(String uahCardNumber);

    @Query("SELECT a FROM Account a WHERE a.user_email = :userEmail")
    Optional<Account> findByUserEmail(@Param("userEmail") String userEmail);

    @Query("SELECT SUM(a.balance_usd) FROM Account a WHERE a.user_id = ?1")
    BigDecimal getUsdBalance(Long user_id);

    @Query("SELECT SUM(a.balance_uah) FROM Account a WHERE a.user_id = ?1")
    BigDecimal getUahBalance(Long user_id);

}
