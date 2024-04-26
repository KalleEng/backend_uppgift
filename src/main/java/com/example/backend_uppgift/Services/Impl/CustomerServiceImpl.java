package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.DTO.CompressedBookingDTO;
import com.example.backend_uppgift.DTO.CompressedCustomerDTO;
import com.example.backend_uppgift.DTO.DetailedCustomerDTO;
import com.example.backend_uppgift.Services.CustomerService;
import com.example.backend_uppgift.models.Booking;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.repositories.BookingRepo;
import com.example.backend_uppgift.Services.BookingService;
import com.example.backend_uppgift.repositories.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final BookingRepo bookingRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo, BookingRepo bookingRepo) {
        this.customerRepo = customerRepo;
        this.bookingRepo = bookingRepo;
    }

    @Override
    public void registerCustomer(String name, String email) {

    }

    @Override
    public void updateCustomer(Long customerId, String name, String email) {

    }

    @Override
    public boolean hasBookings(Long customerId) {
        return false;
    }

    @Override
    public void deleteCustomer(Long customerId) {

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
                        .map(booking -> bookingToCompBookingDTO(booking)).toList())
                .build();
    }

    @Override
    public List<DetailedCustomerDTO> getAllCustomers() {
        return customerRepo.findAll().stream().map(c -> customerToDetailedCustomerDTO(c)).toList();
    }


    private CompressedBookingDTO bookingToCompBookingDTO(Booking booking) {
        return CompressedBookingDTO.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .build();
    }
}
