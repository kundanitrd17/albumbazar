package com.albumbazaar.albumbazar.services;

import com.albumbazaar.albumbazar.dao.ExpenseRepository;
import com.albumbazaar.albumbazar.dao.IncomeRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("transactionService")
public class TransactionService {

    final IncomeRepository incomeRepository;
    final ExpenseRepository expenseRepository;
    public TransactionService(final IncomeRepository incomeRepository
            , final ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
        this.incomeRepository = incomeRepository;
    }
    
}
