package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentServiceImplTest {
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Order order;
    private Map<String, String> validBankTransferData;
    private Map<String, String> invalidBankTransferData;
    private Map<String, String> validVoucherData;
    private Map<String, String> invalidVoucherData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("product-1");
        product.setProductName("Test Product");
        product.setProductQuantity(1);
        products.add(product);

        order = new Order("order-1", products, System.currentTimeMillis(), "Customer");

        validBankTransferData = new HashMap<>();
        validBankTransferData.put("bankName", "BCA");
        validBankTransferData.put("referenceCode", "REF123456");

        invalidBankTransferData = new HashMap<>();
        invalidBankTransferData.put("bankName", "BCA");
        invalidBankTransferData.put("referenceCode", "");

        validVoucherData = new HashMap<>();
        validVoucherData.put("voucherCode", "ESHOP12345678ABC");

        invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "INVALID");

        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Mock orderService.updateStatus to return the updated order
        when(orderService.updateStatus(anyString(), anyString())).thenAnswer(invocation -> {
            String orderId = invocation.getArgument(0);
            String status = invocation.getArgument(1);

            Order updatedOrder = new Order(
                    order.getId(),
                    order.getProducts(),
                    order.getOrderTime(),
                    order.getAuthor(),
                    status
            );
            return updatedOrder;
        });

        // Mock findById to return a payment
        when(paymentRepository.findById(anyString())).thenAnswer(invocation -> {
            String id = invocation.getArgument(0);
            if ("payment-1".equals(id)) {
                return Payment.builder()
                        .id(id)
                        .method(PaymentMethod.BANK_TRANSFER.getValue())
                        .status(PaymentStatus.WAITING.getValue())
                        .paymentData(validBankTransferData)
                        .order(order)
                        .build();
            }
            return null;
        });

        // Mock findAll to return a list of payments
        when(paymentRepository.findAll()).thenReturn(List.of(
                Payment.builder()
                        .id("payment-1")
                        .method(PaymentMethod.BANK_TRANSFER.getValue())
                        .status(PaymentStatus.WAITING.getValue())
                        .paymentData(validBankTransferData)
                        .order(order)
                        .build(),
                Payment.builder()
                        .id("payment-2")
                        .method(PaymentMethod.VOUCHER.getValue())
                        .status(PaymentStatus.SUCCESS.getValue())
                        .paymentData(validVoucherData)
                        .order(order)
                        .build()
        ));
    }

    @Test
    void testValidateBankTransferData() {
        // Valid: Both fields are non-empty
        assertTrue(paymentService.isValidBankTransferData(validBankTransferData));

        // Invalid for missing reference code
        assertFalse(paymentService.isValidBankTransferData(invalidBankTransferData));

        // Invalid for missing bank name
        Map<String, String> missingBankName = new HashMap<>();
        missingBankName.put("bankName", "");
        missingBankName.put("referenceCode", "REF123456");
        assertFalse(paymentService.isValidBankTransferData(missingBankName));

        // Invalid for null data
        assertFalse(paymentService.isValidBankTransferData(null));

        // Invalid for missing fields
        Map<String, String> missingFields = new HashMap<>();
        assertFalse(paymentService.isValidBankTransferData(missingFields));
    }

    @Test
    void testAddPaymentWithValidVoucher() {
        Payment payment = paymentService.addPayment(order, PaymentMethod.VOUCHER.getValue(), validVoucherData);

        assertNotNull(payment);
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(validVoucherData, payment.getPaymentData());
        assertEquals(order, payment.getOrder());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(orderService, times(1)).updateStatus(order.getId(), OrderStatus.SUCCESS.getValue());    
    }

    @Test
    void testAddPaymentWithInvalidVoucher() {
        Payment payment = paymentService.addPayment(order, PaymentMethod.VOUCHER.getValue(), invalidVoucherData);

        assertNotNull(payment);
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(invalidVoucherData, payment.getPaymentData());
        assertEquals(order, payment.getOrder());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(orderService, times(1)).updateStatus(order.getId(), OrderStatus.FAILED.getValue());
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = Payment.builder()
                .id("payment-test")
                .method(PaymentMethod.BANK_TRANSFER.getValue())
                .status(PaymentStatus.WAITING.getValue())
                .paymentData(validBankTransferData)
                .order(order)
                .build();

        Payment updatedPayment = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());

        assertEquals(PaymentStatus.SUCCESS.getValue(), updatedPayment.getStatus());
        
        verify(paymentRepository, times(1)).save(payment);
        verify(orderService, times(1)).updateStatus(order.getId(), OrderStatus.SUCCESS.getValue());
    }

    @Test
    void testValidateVoucherCode() {
        assertTrue(paymentService.isValidVoucherCode("ESHOP12345678BWG"));
        assertTrue(paymentService.isValidVoucherCode("ESHOP1234BZF5678"));

        // not 16 chars -> invalid
        assertFalse(paymentService.isValidVoucherCode("ESHOP123456"));

        // for doesn't start with "ESHOP" -> invalid
        assertFalse(paymentService.isValidVoucherCode("SHOP1234567890ABCD"));

        // for doesn't contain 8 numeric chars -> invalid
        assertFalse(paymentService.isValidVoucherCode("ESHOPABCDEFGHIJKL"));
        assertFalse(paymentService.isValidVoucherCode("ESHOP1234ABCDEFG"));
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = Payment.builder()
                .id("payment-test")
                .method(PaymentMethod.BANK_TRANSFER.getValue())
                .status(PaymentStatus.WAITING.getValue())
                .paymentData(validBankTransferData)
                .order(order)
                .build();

        Payment updatedPayment = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(), updatedPayment.getStatus());

        verify(paymentRepository, times(1)).save(payment);
        verify(orderService, times(1)).updateStatus(order.getId(), OrderStatus.FAILED.getValue());
    }

    @Test
    void testGetPaymentById() {
        Payment payment = paymentService.getPayment("payment-1");

        assertNotNull(payment);
        assertEquals("payment-1", payment.getId());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), payment.getMethod());

        verify(paymentRepository, times(1)).findById("payment-1");
    }

    @Test
    void testGetAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();

        assertNotNull(payments);
        assertEquals(2, payments.size());

        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    void testAddPaymentWithBankTransfer() {
        Payment payment = paymentService.addPayment(order, PaymentMethod.BANK_TRANSFER.getValue(), validBankTransferData);

        assertNotNull(payment);
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(validBankTransferData, payment.getPaymentData());
        assertEquals(order, payment.getOrder());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(orderService, times(1)).updateStatus(order.getId(), OrderStatus.SUCCESS.getValue());    }

    @Test
    void testAddPaymentWithInvalidBankTransfer() {
        Payment payment = paymentService.addPayment(order, PaymentMethod.BANK_TRANSFER.getValue(), invalidBankTransferData);
        assertNotNull(payment);
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(invalidBankTransferData, payment.getPaymentData());
        assertEquals(order, payment.getOrder());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(orderService, times(1)).updateStatus(order.getId(), OrderStatus.FAILED.getValue());
    }

}