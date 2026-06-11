package com.twilight.services;

import com.twilight.dataTransferObjects.OutletR;
import com.twilight.objects.OutletInvitation;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    String findLinkedOutlet(String mobNo) throws ChangeSetPersister.NotFoundException;

    String acceptInvitation(String mobNo,Integer invitationId) throws ChangeSetPersister.NotFoundException;

    public OutletInvitation getInvitation(String mobNo) throws ChangeSetPersister.NotFoundException;

    void updateFoodPrice(String outletId, String foodId, Double price)
            throws ChangeSetPersister.NotFoundException;

    void makeFoodAvailable(String outletId, String foodId) throws ChangeSetPersister.NotFoundException;

    void makeFoodUnavailable(String outletId, String foodId) throws ChangeSetPersister.NotFoundException;

    void openOutlet(String outletId)throws ChangeSetPersister.NotFoundException;

    void closeOutlet(String outletId)throws ChangeSetPersister.NotFoundException;

    OutletR viewOutlet(String outlet) throws BadRequestException;
}
