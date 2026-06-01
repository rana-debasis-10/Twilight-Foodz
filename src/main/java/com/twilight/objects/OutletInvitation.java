package com.twilight.objects;

import com.twilight.annotations.MobileNumber;
import com.twilight.types.InvitationStatus;
import jakarta.persistence.*;


import javax.annotation.processing.Generated;

@Entity
public class OutletInvitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @MobileNumber
    String inviteeMobileNo;

    @MobileNumber
    String inviterMobileNo;

    @Enumerated(value = EnumType.STRING)
    InvitationStatus status;

}
