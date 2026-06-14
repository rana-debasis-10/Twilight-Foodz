package com.twilight.serviceImpls;

import com.twilight.dataTransferObjects.FoodR;
import com.twilight.dataTransferObjects.OutletR;
import com.twilight.exceptions.BadRequestException;
import com.twilight.exceptions.NotFoundException;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.objects.Food;
import com.twilight.objects.Manager;
import com.twilight.objects.Outlet;
import com.twilight.objects.OutletInvitation;
import com.twilight.repositories.FoodRepository;
import com.twilight.repositories.ManagerRepository;
import com.twilight.repositories.OutletInvitationRepository;
import com.twilight.repositories.OutletRepository;
import com.twilight.services.ManagerService;
import com.twilight.types.OutletStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    OutletInvitationRepository outletInvitationRepository;


    @Autowired
    OutletRepository outletRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    ManagerRepository managerRepository;


    @Override
    public Integer findLinkedOutlet(String mobNo) throws NotFoundException {
        Manager manager = managerRepository
                .findById(mobNo).
                orElseThrow(()-> new NotFoundException(
                        "User is unable to find the outlet with Mobile Number :"
                                + mobNo
                        ,"No linked outlet"));
        return manager.getOutletId();
    }

    @Override
    public Integer acceptInvitation(String mobNo, Integer invitationId) throws NotFoundException{
        OutletInvitation invitation = outletInvitationRepository
                .findById(invitationId)
                .orElseThrow(()-> new NotFoundException(
                        "User is unable to find invitation with invitation id : "
                                + invitationId + "and Mobile Number : "
                                + mobNo
                        ,"Invitation does not exist "));
        if(!invitation.getInviteeMobileNo().equals(mobNo))
            throw new UnAuthorizedException("User trying to get invitation access","Invitation does not belong to you");
        Integer outletId = invitation.getOutletId();
        Manager manager = new Manager(mobNo,outletId);
        managerRepository.save(manager);

        return outletId;
    }

    @Override
    public List<OutletInvitation> getInvitation(String mobNo) throws NotFoundException {
        return outletInvitationRepository.findByInviteeMobileNo(mobNo);
    }

    @Override
    public void updateFoodPrice(Integer outletId, Integer foodId, Double price) throws NotFoundException {
        Food food = foodRepository.
                    findFoodByIdAndOutletId(foodId,outletId).
                    orElseThrow(
                            ()-> new NotFoundException(
                                    "Merchant is not found"
                                    ,"No Linked Try creating one"));

        food.setPriceOverride(price);
        foodRepository.save(food);
    }

    @Override
    public void makeFoodAvailable(Integer outletId, Integer foodId) throws NotFoundException {
        updateFoodAvailability(outletId,foodId,true);
    }

    private void updateFoodAvailability(Integer outletId, Integer foodId, boolean available) throws NotFoundException {
        Food food = foodRepository.
                findFoodByIdAndOutletId(foodId,outletId).
                orElseThrow(()->
                        new NotFoundException("Expected food not found","Food does not exist or does not belong your outlet"));
        food.setAvailable(available);
    }

    @Override
    public void makeFoodUnavailable(Integer outletId, Integer foodId) throws NotFoundException {
        updateFoodAvailability(outletId,foodId,false);
    }

    @Override
    public void openOutlet(Integer outletId) throws NotFoundException {
        operateOutlet(outletId, OutletStatus.open);
    }

    @Override
    public void closeOutlet(Integer outletId) throws NotFoundException {
        operateOutlet(outletId,OutletStatus.closed);
    }

    @Override
    public OutletR viewOutlet(Integer outletId) throws BadRequestException {
        return outletRepository.findByOutletId(outletId).orElseThrow(()-> new NotFoundException("Expected outlet not found","Could not find your outlet"));

    }

    @Override
    public List<FoodR> getAllFoods(Integer outletId) throws NotFoundException {
        return foodRepository.findMenuByOutletId(outletId);
    }



    private void operateOutlet(Integer outletId,OutletStatus status)throws NotFoundException {
        Outlet outlet = outletRepository
                .findById(outletId)
                .orElseThrow(()-> new NotFoundException("Expected outlet not found","Could not find your outlet"));
        outlet.setOutletStatus(status);
        outletRepository.save(outlet);
    }
}

