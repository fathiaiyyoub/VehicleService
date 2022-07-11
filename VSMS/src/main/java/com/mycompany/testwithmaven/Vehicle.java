
package com.mycompany.testwithmaven;

/**
 *
 * @author fali8
 */
public class Vehicle 
{
    private int VEHICLEID; // database primary key 
    private String regoNumber;
    private String make;
    private String model;
    private int yearManufactured;
    private int mileage;
    private int customerID;

    public Vehicle() 
    {
        
    }

    public Vehicle(int VEHICLEID, String regoNumber, String make, String model, int yearManufactured, int mileage, int customerID) {
        this.VEHICLEID = VEHICLEID;
        this.regoNumber = regoNumber;
        this.make = make;
        this.model = model;
        this.yearManufactured = yearManufactured;
        this.mileage = mileage;
        this.customerID = customerID;
    }

    public int getVEHICLEID() {
        return VEHICLEID;
    }

    public void setVEHICLEID(int VEHICLEID) {
        this.VEHICLEID = VEHICLEID;
    }

    public String getRegoNumber() {
        return regoNumber;
    }

    public void setRegoNumber(String regoNumber) {
        this.regoNumber = regoNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearManufactured() {
        return yearManufactured;
    }

    public void setYearManufactured(int yearManufactured) {
        this.yearManufactured = yearManufactured;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return  "Vehicle ID: " + VEHICLEID + " | Rego#: " + regoNumber + " | Make: " + make + " | Model: " + model + " | Year Manuf. : " + yearManufactured + " | Mileage: " + mileage + " | CustomerID: " + customerID;
    }

}