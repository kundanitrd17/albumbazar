package com.albumbazaar.albumbazar.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.albumbazaar.albumbazar.dao.OrderAndCustomerCareRepository;
import com.albumbazaar.albumbazar.dao.OrderRepository;
import com.albumbazaar.albumbazar.dao.PaperRepository;
import com.albumbazaar.albumbazar.dao.SheetDetailRepository;
import com.albumbazaar.albumbazar.dto.AddressDTO;
import com.albumbazaar.albumbazar.dto.OrderDetailDTO;
import com.albumbazaar.albumbazar.dto.SheetDetailDTO;
import com.albumbazaar.albumbazar.form.order.OrderDetailForm;
import com.albumbazaar.albumbazar.form.order.OrderDetailFormDTO;
import com.albumbazaar.albumbazar.model.AddressEntity;
import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.model.OrderAndCustomerCareEntity;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.model.Paper;
import com.albumbazaar.albumbazar.model.SheetDetail;
import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.CustomerService;
import com.albumbazaar.albumbazar.services.OrderService;
import com.albumbazaar.albumbazar.services.ProductService;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("orderService")
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final SheetDetailRepository sheetDetailRepository;
    private final OrderAndCustomerCareRepository orderAndCustomerCareRepository;
    private final ProductService productService;
    private final ApplicationContext context;

    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository, final SheetDetailRepository sheetDetailRepository,
            final OrderAndCustomerCareRepository orderAndCustomerCareRepository,
            @Qualifier("productService") final ProductService productService, final ApplicationContext context) {

        this.context = context;
        this.orderAndCustomerCareRepository = orderAndCustomerCareRepository;
        this.orderRepository = orderRepository;
        this.sheetDetailRepository = sheetDetailRepository;
        this.productService = productService;

    }

    @Override
    @Transactional
    public OrderDetail createNewOrder(final OrderDetailFormDTO orderDetailFormDTO, final Long customerId) {

        final OrderDetail orderDetail = new OrderDetail();
        final Association association = context.getBean(AssociationService.class)
                .getAssociation(orderDetailFormDTO.getSelectedAssociationId());

        orderDetail.setAssociationName(orderDetailFormDTO.getAssociationName());
        orderDetail.setAssociation(association);

        orderDetail.setCover(productService.getCoverEntity(orderDetailFormDTO.getCoverId()));

        final CustomerService customerService = context.getBean(CustomerService.class);
        orderDetail.setCustomer(customerService.getCustomer(customerId));

        orderDetail.setDescription(orderDetailFormDTO.getDescription());
        orderDetail.setOrientation(orderDetailFormDTO.getOrientation());
        orderDetail.setProductName(orderDetailFormDTO.getProductCategory());
        orderDetail.setProductSize(orderDetailFormDTO.getProductSize());

        final JSONArray paperAndNumberOfPaperDetailList = new JSONArray();
        int length = orderDetailFormDTO.getPaperId().length;
        for (int index = 0; index < length; index++) {
            try {
                final JSONObject paperIdAndNumberOfSheet = new JSONObject();

                final Long paperId = orderDetailFormDTO.getPaperId()[index];

                final Paper paper = productService.getPaperEntity(paperId);

                paperIdAndNumberOfSheet.put("paper_id", paper.getId());
                paperIdAndNumberOfSheet.put("sheets", orderDetailFormDTO.getNumberOfSheet()[index]);

                paperAndNumberOfPaperDetailList.put(paperIdAndNumberOfSheet);

            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }

        orderDetail.setPaperDetailsWithNumberOfSheetsList(paperAndNumberOfPaperDetailList.toString());

        return orderRepository.save(orderDetail);

    }

    @Deprecated
    @Override
    @Transactional
    public OrderDetail addOrder(final OrderDetailForm orderDetails) {
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

        return orderRepository.save(order);

    }

    @Override
    @Transactional(readOnly = true)
    public OrderDetail getOrder(final Long id) throws NoSuchElementException {
        return orderRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetail> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderDetail> getOrderByPaymentStatus(final Boolean paymentStatus) {
        if (paymentStatus == null) {
            return new ArrayList<OrderDetail>();
        }
        return orderRepository.findByPaymentStatus(paymentStatus);
    }

    @Override
    public List<OrderDetail> getAllOrderWithStatus(final String status) {

        try {
            if (status.equalsIgnoreCase("process")) {
                Collection<String> orderStatus = Arrays.asList("pending", "completed").stream()
                        .collect(Collectors.toList());
                return orderRepository.findByOrderStatusNotIn(orderStatus);
            }
            return orderRepository.findByOrderStatus(status);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("unable to load order details");
        }

    }

    @Override
    public List<OrderDetail> getOrdersWithStatus(final OrderDetailStatus status) {
        try {
            return orderRepository.findByOrderStatus(status.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Unable to get Orders");
        }

    }

    @Override
    @Transactional
    public OrderDetail updateOrderInfo(final Long id, final OrderDetailDTO orderDetailDTO) {

        final OrderDetail orderDetailEntity = orderRepository.findById(id).orElseThrow();

        if (orderDetailDTO.getAssociationName() != null && !orderDetailDTO.getAssociationName().isBlank()) {
            orderDetailEntity.setAssociationName(orderDetailDTO.getAssociationName().trim());
        }

        if (orderDetailDTO.getDescription() != null && !orderDetailDTO.getDescription().isBlank()) {
            orderDetailEntity.setDescription(orderDetailDTO.getDescription().trim());
        }

        if (orderDetailDTO.getOrientation() != null && !orderDetailDTO.getOrientation().isBlank()) {
            orderDetailEntity.setOrientation(orderDetailDTO.getOrientation());
        }

        if (orderDetailDTO.getProductName() != null && !orderDetailDTO.getProductName().isBlank()) {
            orderDetailEntity.setProductName(orderDetailDTO.getProductName());
        }

        return orderDetailEntity;
    }

    /**
     * Change order status to orderStatus
     */
    @Override
    @Transactional
    public void changeOrderStatus(Long orderId, Long customerCareId, OrderDetailStatus orderStatus) {

        // Change the status of the order detail

        final OrderAndCustomerCareEntity orderAndCustomerCareEntity = orderAndCustomerCareRepository
                .findByCustomerAndOrder(orderId, customerCareId).orElseThrow();
        orderAndCustomerCareEntity.getOrder().setOrderStatus(orderStatus.toString());

        // If the order is completed
        if (orderStatus.equals(OrderDetailStatus.COMPLETED) || orderStatus.equals(OrderDetailStatus.PENDING)) {
            orderAndCustomerCareRepository.delete(orderAndCustomerCareEntity);

        }

    }

    @Override
    public List<OrderDetail> getOrdersAssociatedWithEmployeeAndStatus(final Long employeeId,
            final OrderDetailStatus orderDetailStatus) {

        return orderRepository.findAllByEmployeeId(employeeId, orderDetailStatus.toString());

    }

    @Override
    public List<OrderDetailStatus> availableOrderStatus() {
        return Stream.of(OrderDetailStatus.values()).collect(Collectors.toList());
    }

    @Override
    public OrderDetail getOrderWithRazorpayOrderId(String razorpayOrderId) {

        return orderRepository.findByRazorpayOrderId(razorpayOrderId).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetail> getOrdersOfCustomer(final Long customerId) {

        return orderRepository.findAllByCustomerId(customerId).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetail> getOrderWithAssociationIdAndAssociationStatus(final Long associationId, boolean status) {

        return orderRepository.findAllByAssociationIdAndHasAssociationAccepted(associationId, status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetail> getCompletedOrdersWithAssociationId(final Long associationId) {

        return orderRepository.findAllByAssociationIdAndOrderStatus(associationId,
                OrderDetailStatus.COMPLETED.toString());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetail> getUnderProcessOrdersWithAssociationId(final Long associationId) {

        return orderRepository.findAllUnderProcessByAssociationId(associationId);
    }

    @Override
    @Transactional
    public void updateHasAssociationAccepted(Long orderId, boolean status) {

        final OrderDetail order = this.getOrder(orderId);
        order.setHasAssociationAccepted(status);

    }

    @Override
    public List<OrderDetail> getOrdersWithAssociationAndStatus(Association association,
            OrderDetailStatus readyToDeliver) {

        return orderRepository.findAllByAssociationAndOrderStatus(association, readyToDeliver.toString());

    }

    @Override
    public List<SheetDetailDTO> getSheetDetails(Long orderId) {
        // Get Order Detail
        final OrderDetail order = this.getOrder(orderId);

        // List of sheet detail DTO
        final List<SheetDetailDTO> sheetDetailDTOs = new ArrayList<>();
        // Fetching array from order Entity
        final JSONArray sheetDetailArray = new JSONArray(order.getPaperDetailsWithNumberOfSheetsList());

        for (int index = 0; index < sheetDetailArray.length(); ++index) {
            final JSONObject eachSheetInfo = new JSONObject(sheetDetailArray.get(index).toString());

            long paperId = eachSheetInfo.getLong("paper_id");
            int sheetCount = eachSheetInfo.getInt("sheets");

            final Paper paper = productService.getPaperEntity(paperId);

            final SheetDetailDTO sheetDetail = new SheetDetailDTO();
            sheetDetail.setPaperId(paper.getId());
            sheetDetail.setPaperName(paper.getPaperQuality());
            sheetDetail.setPaperSize(paper.getPaperSize());
            sheetDetail.setSheets(sheetCount);

            sheetDetailDTOs.add(sheetDetail);

        }

        // Creating a JSONObject with sheet info
        final HashMap<String, Object> sheetDetails = new HashMap<>();
        sheetDetails.put("data", sheetDetailDTOs);
        return sheetDetailDTOs;

    }

    @Override
    public AddressEntity getDeliveryAddress(Long orderId) {
        final OrderDetail order = this.getOrder(orderId);
        return order.getDeliveryAddress();
    }

}
