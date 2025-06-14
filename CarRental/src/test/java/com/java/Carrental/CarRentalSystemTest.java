package com.java.Carrental;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.java.Carrental.Exception.CarNotFoundException;
import com.java.Carrental.Exception.CustomerNotFoundException;
import com.java.Carrental.Exception.LeaseNotFoundException;
import com.java.Carrental.bal.CustomerImpbal;
import com.java.Carrental.bal.Leaseimpbal;
import com.java.Carrental.bal.carImpBal;
import com.java.Carrental.dao.CarImp;
import com.java.Carrental.dao.Cardao;
import com.java.Carrental.dao.CustomerDao;
import com.java.Carrental.dao.CustomerImp;
import com.java.Carrental.dao.LeaseImp;
import com.java.Carrental.dao.Leasedao;
import com.java.Carrental.model.Car;
import com.java.Carrental.model.Lease;


public class CarRentalSystemTest {

    private carImpBal carDao;
    private CustomerImpbal customerDao;
    private Leaseimpbal leaseDao;
    private LeaseImp leaseImp;

    @BeforeEach
    public void setup() {
        carDao = new carImpBal();
        customerDao = new CustomerImpbal();
        leaseDao = new Leaseimpbal();
    }
    @Test
    public void testCarCreatedSuccessfully() throws Exception {
//    	private int id;
//    	private String model;
//    	private String brand;
//    	private boolean availability;
//    	private double perDay;
//    	private double perMonth;
//    	private String engine;
        Car car = new Car();
        car.setAvailability(true);
        car.setBrand("fortuner");
        car.setEngine("124");
        car.setModel("Toyota");
        car.setPerDay(100);
        car.setPerMonth(200);
        carDao.addCarbal(car);
        assertTrue(car.getId() > 0);
        Car retrievedCar = carDao.findCarByIdbal(car.getId());
        assertEquals("Toyota", retrievedCar.getModel());
    }
    @Test
    public void testLeaseCreatedSuccessfully() throws Exception {
        Lease lease = new Lease();
        lease.setCarId(1);
        lease.setCustomerId(1);
        lease.setEndDate(LocalDate.parse("2025-08-05"));
        lease.setStartDate(LocalDate.parse("2025-06-05"));
        lease.setReturned(false);
        lease.setType("daily");
        leaseDao.createLeasebal(lease);
        assertTrue(lease.getLeaseId() > 0);
    }
    @Test
    public void testLeaseRetrievedSuccessfully() throws Exception {
        Lease lease = leaseDao.getLeasebyIdbal(1);
        assertNotNull(lease);
        assertEquals(1, lease.getLeaseId());
    }

    @Test
    public void testInvalidCustomerThrowsException() {
        assertThrows(CustomerNotFoundException.class, () -> {
            customerDao.getCustomerByIdbal(-1);
        });
    }

    @Test
    public void testInvalidCarThrowsException() {
        assertThrows(CarNotFoundException.class, () -> {
            carDao.findCarByIdbal(-1);
        });
    }

    @Test
    public void testInvalidLeaseThrowsException() {
        assertThrows(LeaseNotFoundException.class, () -> {
            leaseDao.getLeasebyIdbal(-1);
        });
    }
}
