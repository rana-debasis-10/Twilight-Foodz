package com.twilight.components.database;

import com.twilight.dataTransferObjects.request.KycRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Merchants")
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {
    @Id
    private String id;

    @MapsId
    @JoinColumn
    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToOne(mappedBy = "merchant", cascade = CascadeType.ALL)
    private Restaurant restaurant;

    @NotNull
    private String pan;
    @NotNull
    private String aadhaar;
    @NotNull
    private String bankAccount;
    @NotNull
    private String ifsc;

    public Merchant(Customer customer, KycRequest kycRequest) {
        this.customer = customer;
        this.pan = kycRequest.pan();
        this.aadhaar = kycRequest.aadhaar();
        this.bankAccount = kycRequest.bankAccount();
        this.ifsc = kycRequest.ifsc();

    }

    public Merchant(KycRequest kyc) {
        this.pan = kyc.pan();
        this.aadhaar = kyc.aadhaar();
        this.bankAccount = kyc.bankAccount();
        this.ifsc = kyc.ifsc();
    }
}

