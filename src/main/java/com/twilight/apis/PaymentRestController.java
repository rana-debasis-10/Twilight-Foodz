package com.twilight.apis;

import com.twilight.services.ItemService;
import com.twilight.services.PaymentService;
import com.twilight.dataTransferObjects.response.payment.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentRestController {

    private final PaymentService paymentService;
    @Autowired
    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;

    }

    @PostMapping("/verify")
    public ResponseEntity<?> handlePayment(@RequestBody PaymentResponse payment) {

        return new ResponseEntity<>(paymentService.handlePayment(payment));
    }

    @PostMapping("/webhook")
    public void handleWebhook(
            @RequestBody String payload,
            @RequestHeader("X-Razorpay-Signature") String signature) {
        paymentService.handleWebhook(payload,signature);
    }

}
