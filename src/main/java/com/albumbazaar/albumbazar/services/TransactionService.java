package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.dto.OrderBillDTO;
import com.albumbazaar.albumbazar.model.Expense;
import com.albumbazaar.albumbazar.model.Income;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.utilities.PaymentDTORazorpay;
import com.razorpay.RazorpayException;

public interface TransactionService {

    // @return all incomes statements
    List<Income> getAllIncome();

    // @return all expense statements
    List<Expense> getAllExpense();

    // @return all expense statement for a month
    List<Expense> getExpenseAfterDate(String month);

    // @return all income statement for a month
    List<PaymentDTORazorpay> getIncomeAfterDate(String month) throws RazorpayException;

    void addNewIncome(OrderDetail orderDetail);



}
