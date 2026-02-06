package com.twilight.services;
import com.twilight.dataTransferObjects.request.AddressDetails;
import com.twilight.repositories.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.jspecify.annotations.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepo addressRepo;

    @Autowired
    public AddressService(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    @Transactional
    public List<AddressDetails> getAll(int page) throws Exception{
        return addressRepo.findAll(PageRequest.of(page, 20))
                .getContent()
                .stream()
                .map(AddressDetails::new)
                .toList();
    }
    @Transactional
    public List<AddressDetails> getByPostalCode(@NonNull String postalCode) throws NullPointerException{
        return addressRepo.findAllByPostalCode(postalCode)
                .stream()
                .map(AddressDetails::new)
                .toList();
    }


}
