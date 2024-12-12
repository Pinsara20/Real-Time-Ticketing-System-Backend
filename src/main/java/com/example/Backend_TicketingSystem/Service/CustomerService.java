package com.example.Backend_TicketingSystem.Service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerService {
    private int customerCount;
    private int retrieveTickets;
    private List<Thread> customerThreads = new ArrayList<>();
    private TicketPool ticketPool;

    public CustomerService(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public void CustomerStart() {
        for (int i = 1; i <= customerCount; i++) {
            Thread thread = new Thread(new Customer(ticketPool, i + 1, retrieveTickets));
            customerThreads.add(thread); // Correctly add the thread to the list
            thread.start();
        }
    }

    public void CustomerStop() {
        for (Thread thread : customerThreads) {
            thread.interrupt();
        }
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public void setRetrieveTickets(int retrieveTickets) {
        this.retrieveTickets = retrieveTickets;
    }
}
