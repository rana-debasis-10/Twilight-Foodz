package com.twilight.endPoints;

import com.twilight.dataTransferObjects.FoodR;
import com.twilight.dataTransferObjects.OutletR;
import com.twilight.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchEndpoint {
    @Autowired
    SearchService searchService ;
    @GetMapping("/outlet")
    public List<OutletR> findNearestOutlet(@RequestParam double lat, @RequestParam double lon ){
        return searchService.findNearestOutlets(lat,lon);
    }
    @GetMapping("/food")
    public List<FoodR> getFoods(@RequestParam(name = "o")Integer outletId){
        return searchService.getFoods(outletId);
    }

}
