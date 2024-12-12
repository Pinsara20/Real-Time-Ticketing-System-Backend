package com.example.Backend_TicketingSystem.Service;

import org.springframework.stereotype.Component;

@Component
public class TicketPool {

    private int ticketCollection = 0;
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int availableTickets;
    private int vendorCount;

    private VendorService vendorService;
    private CustomerService customerService;

    public TicketPool() {
    }

    public void setVendorService(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void configure(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public synchronized void addTickets(int availableTickets, int ticketReleaseRate) {
        while (ticketCollection >= maxTicketCapacity) {
            try {
                System.out.println("Vendor waiting, capacity is full!");
                wait(); // Wait until there is space to add tickets
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor interrupted while waiting.");
            }
        }

        // Check if there is enough space to add tickets
        int spaceLeft = maxTicketCapacity - ticketCollection;
        if (spaceLeft > 0) {
            int ticketsToAdd = Math.min(ticketReleaseRate, spaceLeft); // Limit tickets to add based on capacity

            // Update ticketCollection
            ticketCollection += ticketsToAdd;
            System.out.println("Added " + ticketsToAdd + " tickets. Total tickets: " + ticketCollection);

            // Notify any waiting threads that tickets have been added
            notifyAll();
        }
    }

    public synchronized void removeTickets(int customerId, int ticketsToRetrieve) {
        while (ticketCollection < ticketsToRetrieve) {
            try {
                System.out.println("Customer " + customerId + " waiting, not enough tickets available!");
                wait(); // Wait until there are enough tickets to retrieve
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer " + customerId + " interrupted while waiting.");
            }
        }

        // Update ticketCollection
        ticketCollection -= ticketsToRetrieve;
        System.out.println("Customer " + customerId + " retrieved " + ticketsToRetrieve + " tickets. Remaining tickets: " + ticketCollection);

        // Notify any waiting threads that tickets have been removed
        notifyAll();
    }

    public void startSimulation() {
        vendorService.vendorStart(vendorService.getVendorCount());
        customerService.CustomerStart();
    }

    public void stopSimulation() {
        vendorService.vendorStop();
        customerService.CustomerStop();
    }
}
