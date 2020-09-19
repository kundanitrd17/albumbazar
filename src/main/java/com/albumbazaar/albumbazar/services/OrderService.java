package com.albumbazaar.albumbazar.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.albumbazaar.albumbazar.dao.OrderRepository;
import com.albumbazaar.albumbazar.dao.SheetDetailRepository;
import com.albumbazaar.albumbazar.form.order.OrderDetailForm;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.SheetDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("orderService")
public class OrderService {

    private OrderRepository orderRepository;
    private SheetDetailRepository sheetDetailRepository;

    @Autowired
    public OrderService(final OrderRepository orderRepository, final SheetDetailRepository sheetDetailRepository) {

        this.orderRepository = orderRepository;
        this.sheetDetailRepository = sheetDetailRepository;
    }

    public boolean addOrder(final OrderDetailForm orderDetails) {
        // parse the form to get in proper input

        final OrderDetail order = new OrderDetail(orderDetails); // parse orderdetail to get a proper order

        final List<SheetDetail> sheets = new ArrayList<>(50);

        final String[] paperQuality = orderDetails.getSheetType();
        final String[] paperPrice = orderDetails.getSheetPrice();
        for (int index = 0; index < orderDetails.getSheetType().length; index++) {

            SheetDetail sheet = new SheetDetail();
            sheet.setPaperIndex(index + 1);

            sheetDetailRepository.save(sheet);

            sheets.add(sheet);
        }

        order.setSheets(sheets);

        orderRepository.save(order);

        // System.out.println(order);

        return true;
    }

    public OrderDetail getOrder(final Long id) throws NoSuchElementException {
        return orderRepository.findById(id).get();
    }

    public List<OrderDetail> getAllOrder() {
        return orderRepository.findAll();
    }

}
