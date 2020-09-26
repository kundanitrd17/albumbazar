package com.albumbazaar.albumbazar.controller;

import java.util.List;

import com.albumbazaar.albumbazar.model.Expense;
import com.albumbazaar.albumbazar.model.Income;
import com.albumbazaar.albumbazar.services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(@Qualifier("transactionService") TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getAll() {

        AllTransactions transacts = new AllTransactions();

        transacts.setIncomes(transactionService.getAllIncome());
        transacts.setExpenses(transactionService.getAllExpense());

        return ResponseEntity.ok(transacts);
    }
}

class AllTransactions {
    private List<Income> incomes;
    private List<Expense> expenses;

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "AllTransactions [expenses=" + expenses + ", incomes=" + incomes + "]";
    }

}