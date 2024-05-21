package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.Services.ShipperService;
import com.example.backend_uppgift.models.Shipper;
import com.example.backend_uppgift.repositories.ShipperRepo;
import org.springframework.stereotype.Service;

@Service
public class ShipperServiceImpl implements ShipperService {
    private final ShipperRepo shipperRepo;

    public ShipperServiceImpl(ShipperRepo shipperRepo) {
        this.shipperRepo = shipperRepo;
    }

    @Override
    public void saveShipper(Shipper shipper){
        shipperRepo.save(shipper);
    }
}
