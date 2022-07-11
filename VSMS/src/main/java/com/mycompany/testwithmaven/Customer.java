

package com.mycompany.testwithmaven;

/**
 *
 * @author fali8
 */
public class Customer 
{
    int customerID; // database primary key
    String firstName;
    String lastName;
    String address;
    String phoneNumber;

    public Customer() 
    {  
    }

    public Customer(int customerID, String firstName, String lastName, String address, String phoneNumber) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return  "customerID: " + customerID + " | First Name: " + firstName + " | Last Name: " + lastName + " | Address: " + address + " | Phone Number: " + phoneNumber;
    }    
}
