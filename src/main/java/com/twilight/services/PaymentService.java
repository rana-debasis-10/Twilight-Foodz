package com.twilight.services;


import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.twilight.dataTransferObjects.response.payment.PaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PaymentService {
    @Value("${razorpay.key.secret}")
    private String keySecret;
    @Value("${razorpay.webhook.secret}")
    private String webhookSecret;
    @Value("${razorpay.key.id}")
    private String keyId;


    private final ObjectMapper objectMapper;

    @Autowired
    public PaymentService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /// code to verify payment response
    public HttpStatusCode handlePayment(PaymentResponse payment) {
        JSONObject options = new JSONObject();
        options.put("razorpay_order_id", payment.razorpayOrderId());
        options.put("razorpay_payment_id", payment.razorpayPaymentId());
        options.put("razorpay_signature", payment.razorpaySignature());

        try{
            if(Utils.verifyPaymentSignature(options, keySecret)){
                return HttpStatus.OK;
            }
            else
                return HttpStatus.UNAUTHORIZED;
        }
        catch (RazorpayException e){
            return HttpStatus.UNAUTHORIZED;
        }
    }

    public void handleWebhook(String payload, String signature){
        try {
            Utils.verifyWebhookSignature(payload, signature, webhookSecret);
        } catch (RazorpayException e) {
            log.info("Invalid Webhook received");
            return;
        }
        processWebhook(payload);
    }

    private void processWebhook(String payload) {

        try {
            JsonNode root = objectMapper.readTree(payload);
            String event = root.path("event").asString();

            switch (event) {

                case "payment.captured" -> handlePaymentCaptured(root);

                case "payment.failed" -> handlePaymentFailed(root);

                case "order.paid" -> handleOrderPaid(root);

                default -> {
                    log.info("Unhandled Razorpay event: {}", event);
                }
            }

        } catch (Exception e) {
            log.error("Failed to process webhook", e);
            throw new RuntimeException("Webhook processing failed");
        }
    }

    public Map<String, Object> createPayment(Double total, String currency, String receipt) throws RazorpayException {
        Map<String, Object> response = new HashMap<>();
        System.out.println("Response created");
        RazorpayClient client = new RazorpayClient(keyId, keySecret);
        System.out.println("RazorpayClient created");
        int amountInPaise = (int) Math.round(total * 100);

        JSONObject req = new JSONObject();
        req.put("amount", amountInPaise);
        req.put("currency", currency);
        req.put("receipt", receipt);

        Order order = client.orders.create(req);
        System.out.println("Order created");
        String orderId = order.get("id");

        response.put("orderId", orderId);
        response.put("key", keyId);
        response.put("amount",total);
        return response;
    }

    private void handleOrderPaid(JsonNode root) {
    }

    private void handlePaymentFailed(JsonNode root) {
    }

    private void handlePaymentCaptured(JsonNode root) {
        
    }
}
