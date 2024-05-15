package com.demo_banking.repository;
import com.demo_banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT email FROM users WHERE email = :email", nativeQuery = true)
    String getUserEmail(@Param("email")String email);

    @Query(value = "SELECT password FROM users WHERE email = :email", nativeQuery = true)
    String getUserPassword(@Param("email")String email);

    @Query(value = "SELECT email FROM users", nativeQuery = true)
    List<String> getAllUserEmails();

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    User getUserDetails(@Param("email")String email);

    @Query("SELECT u.last_name FROM User u WHERE u.user_id = ?1")
    String findLastNameById(Long last_name);

    @Query("SELECT u.first_name FROM User u WHERE u.user_id = ?1")
    String findFirstNameById(Long last_name);
}
