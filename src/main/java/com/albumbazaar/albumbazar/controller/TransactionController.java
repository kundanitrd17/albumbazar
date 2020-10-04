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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/expense")
    public ResponseEntity<Object> getByExpense(@RequestParam(value = "date", defaultValue = "") String date) {

        List<Expense> expenses = null;
        try {
            if (date.isBlank()) {
                expenses = transactionService.getAllExpense();
            } else {
                expenses = transactionService.getExpenseAfterDate(date);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/income")
    public ResponseEntity<Object> getIncomeByDate(@RequestParam(value = "date", defaultValue = "") String date) {

        List<Income> incomes = null;
        try {
            if (date.isBlank()) {
                incomes = transactionService.getAllIncome();
            } else {
                incomes = transactionService.getIncomeAfterDate(date);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(incomes);
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