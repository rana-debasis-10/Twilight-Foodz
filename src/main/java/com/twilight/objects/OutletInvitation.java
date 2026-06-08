package com.twilight.objects;

import com.twilight.annotations.MobileNumber;
import com.twilight.types.InvitationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import javax.annotation.processing.Generated;

@Entity
@Getter
@Setter
public class OutletInvitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @MobileNumber
    String inviteeMobileNo;

    String outletId;

    @Enumerated(value = EnumType.STRING)
    InvitationStatus status;

}
