package com.example.Backend_TicketingSystem.Controller;

import com.example.Backend_TicketingSystem.Data.ConfigurationData;
import com.example.Backend_TicketingSystem.Data.CustomerCountData;
import com.example.Backend_TicketingSystem.Data.VendorCountData;
import com.example.Backend_TicketingSystem.Service.CustomerService;
import com.example.Backend_TicketingSystem.Service.TicketPool;
import com.example.Backend_TicketingSystem.Service.VendorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticketing")
public class TicketController {

    private final TicketPool ticketPool;
    private final VendorService vendorService;
    private final CustomerService customerService;

    public TicketController(TicketPool ticketPool, VendorService vendorService, CustomerService customerService) {
        this.ticketPool = ticketPool;
        this.vendorService = vendorService;
        this.customerService = customerService;
        this.ticketPool.setVendorService(vendorService);
        this.ticketPool.setCustomerService(customerService);
        this.vendorService.setTicketPool(ticketPool);
    }

    @PostMapping("/config")
    public String configure(@RequestBody ConfigurationData configurationData) {
        ticketPool.configure(configurationData.getTotalTickets(), configurationData.getTicketReleaseRate(), configurationData.getCustomerRetrievalRate(), configurationData.getMaxTicketCapacity());
        vendorService.setReleaseRate(configurationData.getTicketReleaseRate());
        customerService.setRetrieveTickets(configurationData.getCustomerRetrievalRate());
        return "Configuration successful";
    }

    @PostMapping("/start")
    public String start() {
        ticketPool.startSimulation();
        return "Starting ticketing service";
    }

    @PostMapping("/stop")
    public String stop() {
        ticketPool.stopSimulation();
        return "Stopping ticketing service";
    }

    @PostMapping("/customer-count")
    public String setCustomerCount(@RequestBody CustomerCountData customerCountData) {
        customerService.setCustomerCount(customerCountData.getCustomerCount());
        return "Customer count set to " + customerCountData.getCustomerCount();
    }

    @PostMapping("/vendor-count")
    public String setVendorCount(@RequestBody VendorCountData vendorCountData) {
        vendorService.setVendorCount(vendorCountData.getVendorCount());
        return "Vendor count set to " + vendorCountData.getVendorCount();
    }
}