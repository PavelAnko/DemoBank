package com.demo_banking.repository;

import com.demo_banking.models.Replenishment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReplenishmentRepository  extends CrudRepository<Replenishment, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO transaction_history(account_id, replenishment_type, amount, source, status, created_at)" +
            "VALUES(:account_id, :replenishment_type, :amount, :source, :status, :created_at)", nativeQuery = true)
    void logReplenishment(@Param("account_id")int account_id,
                        @Param("replenishment_type")String transact_type,
                        @Param("amount")double amount,
                        @Param("source")String source,
                        @Param("status")String status,
                        @Param("created_at") LocalDateTime created_at);

    @Query("SELECT t FROM Replenishment t WHERE t.account_id = :account_id")
    List<Replenishment> findByAccountId(int account_id);

    @Query("SELECT r FROM Replenishment r WHERE r.account_id= ?1")
    Page<Replenishment> findByAccountIdPaged(int accountId, Pageable pageable);

}
