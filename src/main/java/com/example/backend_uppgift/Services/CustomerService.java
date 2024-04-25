package com.example.backend_uppgift.Services;

import com.example.backend_uppgift.DTO.CompressedCustomerDTO;
import com.example.backend_uppgift.DTO.CompressedRoomDTO;
import com.example.backend_uppgift.DTO.DetailedCustomerDTO;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.models.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerService {
    public CompressedCustomerDTO customerToCompCustomerDTO(Customer customer);
    public DetailedCustomerDTO customerToDetailedCustomerDTO(Customer customer);
    @Modifying
    @Transactional
    @Query(value = "UPDATE customer set name=:newVal WHERE name=: oldVal",nativeQuery = true)
    public void changeCustomerName(String newVal,String oldVal);


    void changeCustomerEmail(String newVal, String oldVal);

    public List<DetailedCustomerDTO> getAllCustomers();

    CompressedRoomDTO roomToCompRoomDTO(Room room);
}
