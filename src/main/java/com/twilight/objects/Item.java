package com.twilight.objects;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item {

    @Id
    private String id;
    private String name;
    private Double price;
    private String image;
    private Integer quantity;
    private Double subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerOrderId")
    private Order order;

    private Double calculateSubtotal() {
        return this.price * this.quantity;
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
        this.subtotal = calculateSubtotal();
    }
}

