// VendorService.java
package com.example.Backend_TicketingSystem.Service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class VendorService {
    private int vendorCount;
    private int releaseRate;
    private List<Thread> vendorThreads = new ArrayList<>();
    private TicketPool ticketPool;

    public VendorService() {
    }

    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public void vendorStart(int vendorCount) {
        if (ticketPool == null) {
            throw new IllegalStateException("TicketPool must be set before starting vendors");
        }
        for (int i = 1; i <= vendorCount; i++) {
            int vendorId = i;
            Thread thread = new Thread(new Vendor(ticketPool, vendorId, releaseRate));
            vendorThreads.add(thread);
            thread.start();
        }
    }

    public void vendorStop() {
        for (Thread thread : vendorThreads) {
            thread.interrupt();
        }
    }

    public int getVendorCount() {
        return vendorCount;
    }

    public void setVendorCount(int vendorCount) {
        this.vendorCount = vendorCount;
    }

    public void setReleaseRate(int releaseRate) {
        this.releaseRate = releaseRate;
    }
}