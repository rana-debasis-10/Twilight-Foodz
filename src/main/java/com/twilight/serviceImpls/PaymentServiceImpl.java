package com.twilight.serviceImpls;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.twilight.dataTransferObjects.payment.Payment;
import com.twilight.exceptions.UnauthorizedException;
import com.twilight.services.PaymentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Value("${razorpay.key.secret}")
    private String keySecret;
    @Value("${razorpay.key.id}")
    private String keyId;
    @Value("${razorpay.webhook.secret}")
    private String webhookSecret;


    @Autowired
    private ObjectMapper objectMapper;

    public void handlePayment(Payment payment) {
        JSONObject options = new JSONObject();
        options.put("order_id", payment.orderId());
        options.put("payment_id", payment.paymentId());
        options.put("signature", payment.signature());

        try{
            Utils.verifyPaymentSignature(options, keySecret);
        }
        catch (RazorpayException e){
           throw new UnauthorizedException(e.getMessage());
        }
    }

    public void handleWebhook(String payload, String signature){
        try {
            Utils.verifyWebhookSignature(payload, signature, webhookSecret);
        } catch (RazorpayException e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }

    public Map<String, Object> createPayment(Double total, String currency, String receipt) throws RazorpayException {
        Map<String, Object> response = new HashMap<>();
        RazorpayClient client = new RazorpayClient(keyId, keySecret);
        int amountInPaise = (int) Math.round(total * 100);

        JSONObject req = new JSONObject();
        req.put("amount", amountInPaise);
        req.put("currency", currency);
        req.put("receipt", receipt);

        Order order = client.orders.create(req);
        String id = order.get("id");

        response.put("id", id);
        response.put("key", keyId);
        response.put("amount",total);
        return response;
    }

}
