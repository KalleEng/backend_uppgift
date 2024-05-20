package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.DTO.CompressedBookingDTO;
import com.example.backend_uppgift.DTO.CompressedCustomerDTO;
import com.example.backend_uppgift.DTO.CompressedRoomDTO;
import com.example.backend_uppgift.DTO.DetailedCustomerDTO;
import com.example.backend_uppgift.Services.CustomerService;
import com.example.backend_uppgift.models.Booking;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.models.Room;
import com.example.backend_uppgift.repositories.BookingRepo;
import com.example.backend_uppgift.repositories.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }
    @Override
    public CompressedCustomerDTO customerToCompCustomerDTO(Customer customer) {
        return CompressedCustomerDTO.builder().id(customer.getId()).name(customer.getName()).build();
    }

    @Override
    public DetailedCustomerDTO customerToDetailedCustomerDTO(Customer customer) {
        return DetailedCustomerDTO.builder().id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .compBookingDTO(customer.getBookingList().stream()
                        .map(booking -> bookingToCompBookingDTO(booking))
                        .toList())
                .build();
    }


    @Override
    public List<DetailedCustomerDTO> getAllCustomers() {
        return customerRepo.findAll().stream().map(c -> customerToDetailedCustomerDTO(c)).toList();
    }


    public CompressedBookingDTO bookingToCompBookingDTO(Booking booking) {
        return CompressedBookingDTO.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .compRoom(roomToCompRoomDTO(booking.getRoom()))
                .build();
    }

    @Override
    public CompressedRoomDTO roomToCompRoomDTO(Room room){
        return CompressedRoomDTO.builder().id(room.getId()).build();
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        customerRepo.deleteById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }


}
