package com.albumbazaar.albumbazar.controller;

import java.util.List;

import com.albumbazaar.albumbazar.model.Expense;
import com.albumbazaar.albumbazar.model.Income;
import com.albumbazaar.albumbazar.services.RazorPayPaymentService;
import com.albumbazaar.albumbazar.services.TransactionService;
import com.albumbazaar.albumbazar.utilities.PaymentDTORazorpay;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.razorpay.Payment;
import com.razorpay.RazorpayException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import okhttp3.Response;

@Controller
@RequestMapping("/transaction")
public final class TransactionController {

    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private TransactionService transactionService;
    private RazorPayPaymentService razorPayPaymentService;

    @Autowired
    public TransactionController(@Qualifier("transactionService") TransactionService transactionService,
            @Qualifier("razorPayPaymentService") final RazorPayPaymentService razorPayPaymentService) {
        this.transactionService = transactionService;
        this.razorPayPaymentService = razorPayPaymentService;
    }

    @GetMapping("/paid")
    public ResponseEntity<Object> getAll() throws RazorpayException {

        // AllTransactions transacts = new AllTransactions();

        // transacts.setIncomes(transactionService.getAllIncome());
        // transacts.setExpenses(transactionService.getAllExpense());

        // return ResponseEntity.ok(transacts);

        List<PaymentDTORazorpay> payments = razorPayPaymentService.getAllPayments();
        payments.forEach(System.out::println);
        return ResponseEntity.ok().body("payments");
    }

    @GetMapping("/expense")
    public ResponseEntity<Object> getByExpense(@RequestParam(value = "date", defaultValue = "") String date) {

        final AllTransactions transactions = new AllTransactions();
        try {
            if (date.isBlank()) {
                transactions.setExpenses(transactionService.getAllExpense());
            } else {
                transactions.setExpenses(transactionService.getExpenseAfterDate(date));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/income")
    public ResponseEntity<Object> getIncomeByDate(@RequestParam(value = "date", defaultValue = "") String date) {

        // final AllTransactions transactions = new AllTransactions();
        // try {
        // if (date.isBlank()) {
        // transactions.setIncomes(transactionService.getAllIncome());
        // } else {
        // transactions.setIncomes(transactionService.getIncomeAfterDate(date));
        // }

        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // }

        try {
            final List<PaymentDTORazorpay> payments = transactionService.getIncomeAfterDate(null);
            return ResponseEntity.ok().body(payments);

        } catch (Exception e) {
            // TODO: handle exception
        }

        return ResponseEntity.notFound().build();
    }

}

@JsonInclude(JsonInclude.Include.NON_NULL)
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