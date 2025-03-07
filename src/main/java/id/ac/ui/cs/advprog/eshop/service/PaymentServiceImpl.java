package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;
import java.util.Map;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        
    }

    @Override
    public Payment getPayment(String paymentId) {

    }

    @Override
    public List<Payment> getAllPayments() {

    }
    
    @Override
    public Payment setStatus(Payment payment, String status) {

    }

    private String determineInitialStatus(String method, Map<String, String> paymentData) {

    }

    boolean isValidVoucherCode(String voucherCode) {

    }

    boolean isValidBankTransferData(Map<String, String> paymentData) {

    }
}