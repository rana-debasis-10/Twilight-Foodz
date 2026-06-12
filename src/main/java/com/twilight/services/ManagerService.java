package com.twilight.services;

import com.twilight.annotations.MobileNumber;
import com.twilight.dataTransferObjects.FoodR;
import com.twilight.dataTransferObjects.OutletR;
import com.twilight.exceptions.NotFoundException;
import com.twilight.exceptions.SqlException;
import com.twilight.objects.OutletInvitation;
import org.apache.coyote.BadRequestException;
import software.amazon.awssdk.annotations.NotNull;

import java.util.List;

public interface ManagerService {
    Integer findLinkedOutlet(@MobileNumber String mobNo) throws NotFoundException;

    Integer acceptInvitation(@MobileNumber @NotNull String mobNo, @NotNull Integer invitationId) throws NotFoundException , SqlException;

    List<OutletInvitation> getInvitation( @MobileNumber @NotNull String mobNo) throws NotFoundException;

    void updateFoodPrice(@NotNull Integer outletId, @NotNull Integer foodId, Double price) throws NotFoundException;

    void makeFoodAvailable(@NotNull Integer outletId , @NotNull Integer foodId) throws NotFoundException;

    void makeFoodUnavailable(@NotNull Integer outletId, @NotNull Integer foodId) throws NotFoundException;

    void openOutlet(@NotNull Integer outletId)throws NotFoundException;

    void closeOutlet(@NotNull Integer outletId)throws NotFoundException;

    OutletR viewOutlet(@NotNull Integer outletId) throws BadRequestException;

    List<FoodR> getAllFoods(@NotNull Integer outletId) throws NotFoundException;
}
