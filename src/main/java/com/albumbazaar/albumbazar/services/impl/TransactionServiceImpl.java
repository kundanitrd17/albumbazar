package com.albumbazaar.albumbazar.services.impl;

import java.util.List;

import com.albumbazaar.albumbazar.dao.ExpenseRepository;
import com.albumbazaar.albumbazar.dao.IncomeRepository;
import com.albumbazaar.albumbazar.model.Expense;
import com.albumbazaar.albumbazar.model.Income;
import com.albumbazaar.albumbazar.services.TransactionService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("transactionService")
public class TransactionServiceImpl implements TransactionService {

    final IncomeRepository incomeRepository;
    final ExpenseRepository expenseRepository;

    public TransactionServiceImpl(final IncomeRepository incomeRepository, final ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
        this.incomeRepository = incomeRepository;
    }

    @Override
    public List<Income> getAllIncome() {
        return incomeRepository.findAll();

    }

    @Override
    public List<Expense> getAllExpense() {
        return expenseRepository.findAll();

    }

}
