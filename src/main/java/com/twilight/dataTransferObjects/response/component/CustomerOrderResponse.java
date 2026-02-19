package com.twilight.dataTransferObjects.response.component;

import com.twilight.objects.database.Order;
import com.twilight.dataTransferObjects.request.AddressDetails;
import com.twilight.types.Currency;
import com.twilight.types.DeliveryStatus;
import com.twilight.types.PaymentStatus;

public record CustomerOrderResponse (String id, Double amount, Currency currency, PaymentStatus paymentStatus, DeliveryStatus deliveryStatus, String receipt, String mobNo, AddressDetails address){
    public CustomerOrderResponse (Order order){
        this(order.getId(),order.getTotal(),order.getCurrency(),order.getPaymentStatus(),order.getDeliveryStatus(),order.getReceipt(), order.getMobNo(), new AddressDetails(order.getLocation()));
    }
}