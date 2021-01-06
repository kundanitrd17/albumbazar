package com.albumbazaar.albumbazar.services.impl;

import java.util.List;

import com.albumbazaar.albumbazar.dao.ExpenseRepository;
import com.albumbazaar.albumbazar.dao.IncomeRepository;
import com.albumbazaar.albumbazar.model.Expense;
import com.albumbazaar.albumbazar.model.Income;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.services.RazorPayPaymentService;
import com.albumbazaar.albumbazar.services.TransactionService;
import com.albumbazaar.albumbazar.utilities.PaymentDTORazorpay;
import com.razorpay.RazorpayException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("transactionService")
public class TransactionServiceImpl implements TransactionService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    private final RazorPayPaymentService razorPayPaymentService;

    public TransactionServiceImpl(final IncomeRepository incomeRepository, final ExpenseRepository expenseRepository,
            @Qualifier("razorPayPaymentService") final RazorPayPaymentService razorPayPaymentService) {
        this.expenseRepository = expenseRepository;
        this.incomeRepository = incomeRepository;
        this.razorPayPaymentService = razorPayPaymentService;
    }

    @Override
    public List<Income> getAllIncome() {
        return incomeRepository.findAll();

    }

    @Override
    public List<Expense> getAllExpense() {
        return expenseRepository.findAll();

    }

    @Override
    public List<Expense> getExpenseAfterDate(String date) {

        return expenseRepository.findAllWhereDateTimeGreaterThan(date);
    }

    @Override
    public List<PaymentDTORazorpay> getIncomeAfterDate(String date) throws RazorpayException {
        return razorPayPaymentService.getAllPaidPayments();
    }

    @Override
    @Transactional
    public void addNewIncome(final OrderDetail orderDetail) {
        final Income income = new Income();
        income.setAmount(orderDetail.getTotalAmount());
        income.setOrder(orderDetail);

        incomeRepository.save(income);

    }

}
