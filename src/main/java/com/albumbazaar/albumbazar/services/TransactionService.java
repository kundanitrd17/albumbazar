package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.model.Expense;
import com.albumbazaar.albumbazar.model.Income;

public interface TransactionService {

    // @return all incomes statements
    List<Income> getAllIncome();

    // @return all expense statements
    List<Expense> getAllExpense();

    // @return all expense statement for a month
    List<Expense> getExpenseAfterDate(String month);

    // @return all income statement for a month
    List<Income> getIncomeAfterDate(String month);

}
