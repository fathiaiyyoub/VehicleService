
package com.mycompany.testwithmaven;

/**
 *
 * @author fali8
 */
import java.sql.Date;

public class Service implements Comparable<Service>
{
    private int serviceID; // database primary key
    private String serviceDescription;
    private Date serviceDate;
    private double servicePrice;
    private String serviceStatus; // bookoed, completed, or cancelled
    private int vehicleID; 

    // default constructor
    public Service() 
    {
       
    }

    // param constructor
    public Service(int serviceID, String serviceDescription, Date serviceDate, double servicePrice, String serviceStatus, int vehicleID) {
        this.serviceID = serviceID;
        this.serviceDescription = serviceDescription;
        this.serviceDate = serviceDate;
        this.servicePrice = servicePrice;
        this.serviceStatus = serviceStatus;
        this.vehicleID = vehicleID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }
    
    @Override
    public int compareTo(Service o) 
    {
        // used with Collection.min,max 
        if (this.getServicePrice() > o.getServicePrice()) 
        {
            return 1;
        } 
        else if (this.getServicePrice() < o.getServicePrice()) 
        {
            return -1;
        }
        return 0;
    }
    
    @Override
    public String toString() 
    {
        return "Service ID: " + serviceID + " | Description: " + serviceDescription + " | Date: " + serviceDate + " | Price: " + servicePrice + " | Status: " + serviceStatus + 
                " | VehicleID: " + vehicleID;
    }   
}
