package com.twilight.objects;

import com.twilight.dataTransferObjects.DriverR;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    private String id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    private String aadhaarNumber;
    private String bankAccount;
    private String ifsc;
    private String bankName;
    private String drivingLicense;

}
