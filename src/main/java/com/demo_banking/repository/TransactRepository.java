package com.demo_banking.repository;
import com.demo_banking.models.Transact;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactRepository extends CrudRepository<Transact, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO transaction_history(account_id, transaction_type, amount, source, status, created_at)" +
            "VALUES(:account_id, :transact_type, :amount, :source, :status, :created_at)", nativeQuery = true)
    void logTransaction(@Param("account_id")Long account_id,
                        @Param("transact_type")String transact_type,
                        @Param("amount")double amount,
                        @Param("source")String source,
                        @Param("status")String status,
                        @Param("created_at") LocalDateTime created_at);
    @Query ("SELECT COUNT(*) FROM Transact t WHERE t.account_id = :account_id")
    long countByAccountId(Long account_id);

    @Query("SELECT t FROM Transact t WHERE t.account_id = :account_id")
    List<Transact> findByAccountId(Long account_id, PageRequest createdAt);
}
