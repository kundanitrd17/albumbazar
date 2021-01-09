package com.albumbazaar.albumbazar.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import com.albumbazaar.albumbazar.Mapper.AddressMapper;
import com.albumbazaar.albumbazar.Mapper.CoverMapper;
import com.albumbazaar.albumbazar.dao.AddressRepository;
import com.albumbazaar.albumbazar.dao.OrderAndCustomerCareRepository;
import com.albumbazaar.albumbazar.dao.OrderRepository;
import com.albumbazaar.albumbazar.dao.SheetDetailRepository;
import com.albumbazaar.albumbazar.dto.AddressDTO;
import com.albumbazaar.albumbazar.dto.OrderBillDTO;
import com.albumbazaar.albumbazar.dto.OrderDetailDTO;
import com.albumbazaar.albumbazar.dto.ProductDetailDTO;
import com.albumbazaar.albumbazar.dto.SheetDetailDTO;
import com.albumbazaar.albumbazar.form.order.OrderDetailForm;
import com.albumbazaar.albumbazar.form.order.OrderDetailFormDTO;
import com.albumbazaar.albumbazar.model.AddressEntity;
import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.model.Cover;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.model.OrderAndCustomerCareEntity;
import com.albumbazaar.albumbazar.model.OrderBillEmbeddable;
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
    private final AddressRepository addressRepository;
    private final ApplicationContext context;

    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository, final SheetDetailRepository sheetDetailRepository,
            final OrderAndCustomerCareRepository orderAndCustomerCareRepository,
            @Qualifier("productService") final ProductService productService, final AddressRepository addressRepository,
            final ApplicationContext context) {

        this.context = context;
        this.orderAndCustomerCareRepository = orderAndCustomerCareRepository;
        this.orderRepository = orderRepository;
        this.sheetDetailRepository = sheetDetailRepository;
        this.productService = productService;
        this.addressRepository = addressRepository;
    }

    @Override
    public OrderDetail createOrderByBranchOrAdmin(OrderDetailFormDTO orderDetailFormDTO) {

        final OrderDetail orderDetail = this.createNewOrder(orderDetailFormDTO, orderDetailFormDTO.getCustomerId());

        return orderDetail;

    }

    @Override
    @Transactional
    public OrderDetail createNewOrder(final OrderDetailFormDTO orderDetailFormDTO, final Long customerId) {

        final OrderDetail orderDetail = new OrderDetail();
        final Association association = context.getBean(AssociationService.class)
                .getAssociation(orderDetailFormDTO.getSelectedAssociationId());

        orderDetail.setAssociationName(orderDetailFormDTO.getAssociationName());
        orderDetail.setAssociation(association);

        // Calculating total amount of the order
        float totalAmount = 0f;

        final Cover cover = productService.getCoverEntity(orderDetailFormDTO.getCoverId());
        totalAmount += cover.getCoverPrice();
        orderDetail.setCover(cover);

        orderDetail.setDescription(orderDetailFormDTO.getDescription());
        orderDetail.setOrientation(orderDetailFormDTO.getOrientation());
        orderDetail.setProductName(orderDetailFormDTO.getProductCategory());
        orderDetail.setProductSize(orderDetailFormDTO.getProductSize());

        final JSONArray paperAndNumberOfPaperDetailList = new JSONArray();
        int length = orderDetailFormDTO.getPaperId().length;
        for (int index = 0; index < length; index++) {

            final JSONObject paperIdAndNumberOfSheet = new JSONObject();

            System.out.println("paper: " + orderDetailFormDTO.getPaperId()[index] + "\nsheets: "
                    + orderDetailFormDTO.getNumberOfSheet()[index]);

            final Long paperId = orderDetailFormDTO.getPaperId()[index];

            final Paper paper = productService.getPaperEntity(paperId);

            paperIdAndNumberOfSheet.put("paper_id", paper.getId());
            paperIdAndNumberOfSheet.put("sheets", orderDetailFormDTO.getNumberOfSheet()[index]);

            paperAndNumberOfPaperDetailList.put(paperIdAndNumberOfSheet);

            totalAmount += paper.getPaperPrice() * Math.abs(orderDetailFormDTO.getNumberOfSheet()[index]);

        }

        orderDetail.setPaperDetailsWithNumberOfSheetsList(paperAndNumberOfPaperDetailList.toString());

        final CustomerService customerService = context.getBean(CustomerService.class);
        final Customer customer = customerService.getCustomer(customerId);

        // Creating the object for OrderBillEmbeddable to return
        final OrderBillEmbeddable orderBill = new OrderBillEmbeddable();
        orderBill.setTotalAmount(totalAmount);
        orderBill.setDiscount(customer.getDiscount());
        orderBill.setWallet(customer.getWallet());
        orderBill.setAmountToPay(orderBill.getTotalAmount() - orderBill.getDiscount() - orderBill.getWallet());

        orderDetail.setOrderBill(orderBill);

        orderDetail.setCustomer(customer);

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
    @Transactional(readOnly = true)
    public List<OrderDetail> getOrdersWithAssociationAndStatus(Association association,
            OrderDetailStatus readyToDeliver) {

        return orderRepository.findAllByAssociationAndOrderStatus(association, readyToDeliver.toString());

    }

    @Override
    public List<SheetDetailDTO> getSheetDetails(Long orderId) {
        // Get Order Detail
        final OrderDetail orderDetail = this.getOrder(orderId);

        return this.getActualSheetDetailInfo(orderDetail);

    }

    @Transactional(readOnly = true)
    private List<SheetDetailDTO> getActualSheetDetailInfo(final OrderDetail orderDetail) {

        // List of sheet detail DTO
        final List<SheetDetailDTO> sheetDetailDTOs = new ArrayList<>();

        // Fetching array from order Entity
        final JSONArray sheetDetailArray = new JSONArray(orderDetail.getPaperDetailsWithNumberOfSheetsList());

        for (int index = 0; index < sheetDetailArray.length(); ++index) {
            final JSONObject eachSheetInfo = new JSONObject(sheetDetailArray.get(index).toString());

            long paperId = eachSheetInfo.getLong("paper_id");
            int sheetCount = eachSheetInfo.getInt("sheets");

            final Paper paper = productService.getPaperEntity(paperId);

            final SheetDetailDTO sheetDetail = new SheetDetailDTO();
            sheetDetail.setPaperId(paper.getId());
            sheetDetail.setPaperName(paper.getPaperQuality());
            sheetDetail.setPaperSize(paper.getPaperSize());
            sheetDetail.setPaperPrice(paper.getPaperPrice());
            sheetDetail.setSheets(sheetCount);

            sheetDetailDTOs.add(sheetDetail);

        }

        return sheetDetailDTOs;
    }

    @Override
    @Transactional(readOnly = true)
    public AddressEntity getDeliveryAddress(Long orderId) {
        final OrderDetail order = this.getOrder(orderId);
        return order.getDeliveryAddress();
    }

    @Override
    @Transactional
    public void forwardToAssociation(Long orderId) {

        final OrderDetail order = this.getOrder(orderId);

        if (order.getDeliveryAddress() == null) {
            throw new RuntimeException("Delivery Address is Invalid to continue!");
        }

        order.setIsForwardedToAssociation(true);

    }

    @Override
    @Transactional(readOnly = true)
    public ProductDetailDTO getProductInfo(Long orderId) {
        final OrderDetail orderDetail = this.getOrder(orderId);

        final ProductDetailDTO productDetailDTO = new ProductDetailDTO();

        // Set sheet details infos
        final List<SheetDetailDTO> sheetDetailDTOs = this.getActualSheetDetailInfo(orderDetail);
        productDetailDTO.setSheet_detail_list(sheetDetailDTOs);

        // Fetch cover info
        final Cover cover = orderDetail.getCover();
        productDetailDTO.setCover(context.getBean(CoverMapper.class).coverTCoverDTO(cover));

        // Set other info's
        productDetailDTO.setAssociation_name(orderDetail.getAssociation().getName());
        productDetailDTO.setProduct_name(orderDetail.getProductName());
        productDetailDTO.setProduct_size(orderDetail.getProductSize());

        return productDetailDTO;
    }

    @Override
    @Transactional
    public void changeDeliveryAddress(final AddressDTO addressDTO, final Long EmployeeId) {
        final AddressMapper addressMapper = context.getBean(AddressMapper.class);

        final Long orderId = addressDTO.getOrderId();
        final OrderDetail order = this.getOrder(orderId);

        final AddressEntity address = addressMapper.addressDTOToAddressEntity(addressDTO);

        final AddressEntity savedAddressEntity = addressRepository.save(address);

        order.setDeliveryAddress(savedAddressEntity);

    }

    @Override
    @Transactional
    public void changeOrderDescription(final Long orderId, final String description) {
        if (description == null || description.isBlank()) {
            throw new RuntimeException();
        }

        final OrderDetail order = this.getOrder(orderId);

        order.setDescription(description);
    }

}
