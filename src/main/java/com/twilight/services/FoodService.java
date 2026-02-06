package com.twilight.services;

import com.twilight.dataTransferObjects.request.FoodRequest;
import com.twilight.dataTransferObjects.response.component.FoodResponse;
import com.twilight.repositories.FoodRepo;
import com.twilight.repositories.OutletFoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class FoodService {
    private final OutletFoodRepo outletFoodRepo;
    @Autowired
    public FoodService(OutletFoodRepo outletFoodRepo, FoodRepo foodRepo) {
        this.outletFoodRepo = outletFoodRepo;
    }

    @Transactional
    public List<FoodResponse> search(String keyword, int page){
        return outletFoodRepo.search(keyword, (Pageable) PageRequest.of(page,20));
    }

    @Transactional
    public List<FoodResponse> searchByOutlet(String outletId,String keyword, int page){
        return outletFoodRepo.searchByOutletId(outletId,keyword, (Pageable) PageRequest.of(page,20));
    }
}
