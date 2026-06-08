package com.twilight.apis;

import com.twilight.dataTransferObjects.OutletR;
import com.twilight.objects.Outlet;
import com.twilight.services.SearchService;
import lombok.Getter;
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
    @GetMapping
    public List<OutletR> findNearestOutlet(@RequestParam double lat, @RequestParam double lon ){
        return searchService.findNearestOutlets(lat,lon);
    }
}
