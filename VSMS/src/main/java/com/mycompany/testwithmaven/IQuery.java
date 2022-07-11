
package com.mycompany.testwithmaven;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author fali8
 */
public interface IQuery 
{
    // retrive data
    ArrayList < Customer >  getAllCustomers();
    ArrayList < Vehicle  >  getAllVehicles();
    ArrayList < Service  >  getAllServices();
    
    // insert data
    // follows the constructor
    int insertCustomer( int customerID, String firstName, String lastName, String address, String phoneNumber );
    int insertVehicle( int VEHICLEID, String regoNumber, String make, String model, int yearManufactured, int mileage, int customerID );
    int insertService( int serviceID, String serviceDescription, Date serviceDate, double servicePrice, String serviceStatus, int vehicleID );
    
     // update data 
    public int cancelService(int serviceID, String serviceStatus, double servicePrice);
    public int updateCustomer(int customerID, String address, String phoneNumber);
    // closes db connection
    void closeConnection();
    
} // end interface iQuery
