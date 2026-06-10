package com.twilight.serviceImpls;

import com.twilight.objects.Food;
import com.twilight.objects.Outlet;
import com.twilight.objects.OutletInvitation;
import com.twilight.repositories.FoodRepository;
import com.twilight.repositories.OutletInvitationRepository;
import com.twilight.repositories.OutletRepository;
import com.twilight.services.ManagerService;
import com.twilight.types.OutletStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;


public class ManagerServiceImpl implements ManagerService {
    @Autowired
    OutletInvitationRepository outletInvitationRepository;

    @Autowired
    OutletRepository outletRepository;

    @Autowired
    FoodRepository foodRepository;


    @Override
    public String findLinkedOutlet(String mobNo)
            throws ChangeSetPersister.NotFoundException {
        return outletRepository.
                findByManager(mobNo)
                .orElseThrow(ChangeSetPersister
                        .NotFoundException::new);
    }

    @Override
    public String acceptInvitation(String mobNo, Integer invitationId)
            throws ChangeSetPersister.NotFoundException {
        OutletInvitation invitation = outletInvitationRepository
                .findByIdAndInviteeMobileNo(invitationId, mobNo)
                .orElseThrow((ChangeSetPersister.NotFoundException::new));
        outletInvitationRepository.delete(invitation);
        String outletId = invitation.getOutletId();
        Outlet outlet = outletRepository.findById(outletId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        outlet.setManager(mobNo);
        outletRepository.save(outlet);

        return outletId;
    }

    @Override
    public OutletInvitation getInvitation(String mobNo)
            throws ChangeSetPersister.NotFoundException {
        return outletInvitationRepository.findByInviteeMobileNo(mobNo).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public void updateFoodPrice(String outletId ,String foodId, Double price)
            throws ChangeSetPersister.NotFoundException
    {
        Food food = foodRepository.
                findFoodByIdAndOutletId(foodId,outletId).
                orElseThrow(ChangeSetPersister
                        .NotFoundException::new);

        food.setPriceOverride(price);
        foodRepository.save(food);
    }
    public void makeFoodAvailable(String outletId ,String foodId) throws ChangeSetPersister.NotFoundException {
       updateFoodAvailability(outletId,foodId,true);
    }
    public void makeFoodUnavailable(String outletId ,String foodId) throws ChangeSetPersister.NotFoundException {
        updateFoodAvailability(outletId,foodId,false);
    }
    public void openOutlet(String outletId)throws ChangeSetPersister.NotFoundException {
        operateOutlet(outletId,OutletStatus.open);
    }
    public void closeOutlet(String outletId)throws ChangeSetPersister.NotFoundException {
        operateOutlet(outletId,OutletStatus.closed);
    }
    private void operateOutlet(String outletId,OutletStatus status)throws ChangeSetPersister.NotFoundException {
        Outlet outlet = outletRepository.findById(outletId).orElseThrow(ChangeSetPersister
                .NotFoundException::new);
        outlet.setOutletStatus(status);
    }

    private void updateFoodAvailability(String outletId ,String foodId,boolean available) throws ChangeSetPersister.NotFoundException {
        Food food = foodRepository.
                findFoodByIdAndOutletId(foodId,outletId).
                orElseThrow(ChangeSetPersister
                        .NotFoundException::new);
        food.setAvailable(available);
    }
}
