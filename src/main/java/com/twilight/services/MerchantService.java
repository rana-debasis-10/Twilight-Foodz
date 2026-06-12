package com.twilight.services;

import com.twilight.annotations.MobileNumber;
import com.twilight.dataTransferObjects.OutletDetailed;
import com.twilight.objects.Merchant;
import com.twilight.objects.OutletInvitation;
import com.twilight.dataTransferObjects.Point;
import com.twilight.objects.Restaurant;
import org.apache.coyote.BadRequestException;
import software.amazon.awssdk.annotations.NotNull;

import java.util.List;

public interface MerchantService {
    void createOutlet(@MobileNumber @NotNull String mobNo, Point point);
    void createMerchant(@NotNull Merchant merchant,@NotNull Restaurant restaurant);
    OutletInvitation inviteManager(@MobileNumber @NotNull String merchantMobNo, @MobileNumber @NotNull String inviteeMobNo, Integer outletId);
    OutletInvitation inviteOtherManager(@MobileNumber @NotNull String merchantMobNo, String inviteeMobNo, @NotNull Integer outletId, @NotNull Integer invitationId) throws BadRequestException;

    List<OutletDetailed> viewAllOutlets(@MobileNumber String merchantMobNo);
}
