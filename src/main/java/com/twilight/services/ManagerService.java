package com.twilight.services;

import com.twilight.objects.OutletInvitation;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    String findLinkedOutlet(String mobNo) throws ChangeSetPersister.NotFoundException;
    String acceptInvitation(String mobNo,Integer invitationId) throws ChangeSetPersister.NotFoundException;
    public OutletInvitation getInvitation(String mobNo) throws ChangeSetPersister.NotFoundException;

}
