package com.twilight.objects;

import com.twilight.dataTransferObjects.DriverR;
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
    private User user;

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

    public Merchant(User user, DriverR driverR) {
        this.user = user;
        this.pan = driverR.pan();
        this.aadhaar = driverR.aadhaar();
        this.bankAccount = driverR.bankAccount();
        this.ifsc = driverR.ifsc();

    }

    public Merchant(DriverR kyc) {
        this.pan = kyc.pan();
        this.aadhaar = kyc.aadhaar();
        this.bankAccount = kyc.bankAccount();
        this.ifsc = kyc.ifsc();
    }
}

