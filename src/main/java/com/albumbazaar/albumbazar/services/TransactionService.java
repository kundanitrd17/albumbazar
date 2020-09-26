package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.model.Expense;
import com.albumbazaar.albumbazar.model.Income;

public interface TransactionService {

    List<Income> getAllIncome();

    List<Expense> getAllExpense();

}
