package com.twilight.objects;

import com.twilight.annotations.MobileNumber;
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
    @MobileNumber
    @Column(name = "mob_no", length = 15)
    private String mobNo;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mob_no")
    private User user;

    private String aadhaarNumber;
    private String bankAccount;
    private String ifsc;
    private String bankName;
    private String drivingLicense;

}
