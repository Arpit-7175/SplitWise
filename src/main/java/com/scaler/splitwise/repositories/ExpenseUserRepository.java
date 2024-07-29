package com.scaler.splitwise.repositories;

import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.ExpenseUser;
import com.scaler.splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExpenseUserRepository extends JpaRepository<ExpenseUser, Long> {
    List<ExpenseUser> findAllByUser(User user);
}
