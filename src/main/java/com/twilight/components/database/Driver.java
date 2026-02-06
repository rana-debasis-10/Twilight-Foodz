package com.twilight.components.database;

import com.twilight.dataTransferObjects.request.KycRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Drivers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @Column(name = "customerId")
    private String customerId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;

    private String pan;
    private String aadhaar;
    private String bankAccount;
    private String ifsc;

    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
    private Vehicle vehicle;

    public Driver(KycRequest kycRequest) {
        this.pan = kycRequest.pan();
        this.aadhaar = kycRequest.aadhaar();
        this.bankAccount = kycRequest.bankAccount();
        this.ifsc = kycRequest.ifsc();
    }

}
