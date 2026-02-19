package com.twilight.objects.database;
import com.twilight.dataTransferObjects.response.component.FoodResponse;
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

    public Item(FoodResponse food, int quantity) {
        this.id = food.id();
        this.name = food.name();
        this.image = food.image();
        this.price = food.price();
        this.quantity = quantity;
        this.subtotal = calculateSubtotal();
    }

    private Double calculateSubtotal() {
        return this.price * this.quantity;
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
        this.subtotal = calculateSubtotal();
    }
}

