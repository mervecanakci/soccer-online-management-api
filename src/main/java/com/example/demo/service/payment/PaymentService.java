package com.example.demo.service.payment;


import com.example.demo.common.dto.CreateTransferPaymentRequest;

import java.util.List;

public interface PaymentService {
    List<PaymentResponse> getAll();

    PaymentResponse getById(int id);

    PaymentResponse add(PaymentRequest request);

    PaymentResponse update(int id, PaymentRequest request);

    void delete(int id);

    void processTransferPayment(CreateTransferPaymentRequest request);

}
