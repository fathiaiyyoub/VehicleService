
package com.mycompany.testwithmaven;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author fali8
 */
@SuppressWarnings("CallToPrintStackTrace")
public class Model implements IQuery
{
    String databaseURL = "jdbc:mysql://localhost:3306/carservicedb";
    String userName = "root";
    String password = "Fathi@1982"; 
    Connection connection = null;
    
    // select statements
    PreparedStatement selectAllCustomersQuery = null; // DONE 
    PreparedStatement selectAllBookingsQuery = null; // DONE 
    PreparedStatement selectAllVehicles = null; // DONE
    
    // insert statements
    PreparedStatement inertCustomerQuery = null;
    PreparedStatement inertVehicleQuery = null;
    PreparedStatement inertSeviceBookingQuery = null;
    
    // car serviced by make 
    PreparedStatement carsServicedByMakeQuery = null;
    
    // update statements
    PreparedStatement updateCustomerQuery = null;
    
    // cancel statements
    PreparedStatement cancelServiceQuery = null;
    
    
    // constructor
    public Model() 
    {
       try
        {
            connection =
            DriverManager.getConnection( databaseURL, userName, password );

            //------------------------  Retrieve -------------------------------//
            
            // create query that selects all CUSTOMERS
            selectAllCustomersQuery =
            connection.prepareStatement( "SELECT * FROM CUSTOMERS" );

            // create query that selects ALL SERVICE BOOKINGS
            selectAllBookingsQuery = connection.prepareStatement(
            "SELECT * FROM SERVICES" );
            
            // create query that selects ALL VEHICLES
            selectAllVehicles = connection.prepareStatement ( 
            "SELECT * FROM VEHICLES");
            
            //------------------------  Insert -------------------------------//
            
            // create insert query that adds a new CUSTOMER into the database
            inertCustomerQuery = connection.prepareStatement(
            "INSERT INTO CUSTOMERS " +
            "( CUSTOMERID, FIRSTNAME, LASTNAME, ADDRESS, PHONE ) " +
            "VALUES ( ?, ?, ?, ?, ? )" );
            
            
            // create insert query that adds a new VEHICLE into the database
            inertVehicleQuery = connection.prepareStatement(
            "INSERT INTO VEHICLES " +
            "( VEHICLEID, REGONUMBER, MAKE, MODEL, YEARMANUFATURED, MILEAGE, CUSTOMERID ) " +
            "VALUES ( ?, ?, ?, ?, ?, ?, ? )" );
            
            // create insert query that adds a new SERVICE/BOOKING into the database
            inertSeviceBookingQuery = connection.prepareStatement(
            "INSERT INTO SERVICES " +
            "( SERVICEID, SERVICEDESCRIPTION, SERVICEDATE, SERVICEPRICE, SERVICESTATUS, vehicleID ) " +
            "VALUES ( ?, ?, ?, ?, ?, ? )" );
            
            // 
            carsServicedByMakeQuery = connection.prepareStatement(" with CTE (Make, Counter) as \n" +
            "(\n" +
            "select make as CarMake, count(Make) as Counter\n" +
            "from VEHICLES\n" +
            "INNER JOIN SERVICES ON VEHICLES.VEHICLEID = SERVICES.vehicleID\n" +
            "group by VEHICLES.make\n" +
            "\n" +
            ")\n" + "select * from cte" + "\n" + "order by Counter DESC;");
            
            // update customer query 
            updateCustomerQuery = connection.prepareStatement(
            "UPDATE customers SET ADDRESS=? , PHONE=? WHERE CUSTOMERID=?");
            
            // create cancel query that 
            cancelServiceQuery = connection.prepareStatement(
            "UPDATE SERVICES SET  SERVICESTATUS=? , SERVICEPRICE=? WHERE SERVICEID=?");
   
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
            System.exit( 1 );
        } // end catch
    }

    @Override
    public ArrayList< Customer > getAllCustomers() 
    {
        ArrayList< Customer > allCustomers = new ArrayList<>();
        ResultSet resultSet = null;
        
        try
        {
            resultSet = selectAllCustomersQuery.executeQuery();
            
            //allPatients = new ArrayList< Patient >();

            while ( resultSet.next() )
            {
                allCustomers.add( new Customer(resultSet.getInt( "customerID" ),
                resultSet.getString( "firstName" ),
                resultSet.getString( "lastName" ),
                resultSet.getString( "Address" ),
                resultSet.getString("PHONE")));
            } // end while
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
        } // end catch
        finally
        {
            try
        {
            resultSet.close();
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
            closeConnection();
        } // end catch
        } // end finally
   
        return allCustomers;
    }

    @Override
    public ArrayList< Vehicle > getAllVehicles() 
    {
        ArrayList< Vehicle > allVehicles = new ArrayList<>();
        ResultSet resultSet = null;
        
        try
        {            
            resultSet = selectAllVehicles.executeQuery();
            
            //allPatients = new ArrayList< Patient >();

            while ( resultSet.next() )
            {
                allVehicles.add( new Vehicle(resultSet.getInt( "VEHICLEID" ),
                resultSet.getString( "regoNumber" ),
                resultSet.getString( "make" ),
                resultSet.getString( "model" ),
                resultSet.getInt("YEARMANUFATURED"),
                resultSet.getInt("mileage"),
                resultSet.getInt("customerID")));
            } // end while
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
        } // end catch
        finally
        {
            try
        {
            resultSet.close();
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
            closeConnection();
        } // end catch
        } // end finally

        return allVehicles;
    }

    
    @Override
    public ArrayList< Service > getAllServices() 
    {
        ArrayList< Service > allServices = new ArrayList<>();
        ResultSet resultSet = null;
        
        try
        {
            resultSet = selectAllBookingsQuery.executeQuery();
            
            //allPatients = new ArrayList< Patient >();

            while ( resultSet.next() )
            {
                allServices.add( new Service(resultSet.getInt( "SERVICEID" ),
                resultSet.getString( "SERVICEDESCRIPTION" ),
                resultSet.getDate("SERVICEDATE"),
                resultSet.getDouble("SERVICEPRICE" ),
                resultSet.getString("SERVICESTATUS"),
                resultSet.getInt("vehicleID")
                ));
                
            } // end while
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
        } // end catch
        finally
        {
            try
        {
            resultSet.close();
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
            closeConnection();
        } // end catch
        } // end finally

        return allServices;
    }

    
    @Override
    public int insertCustomer(int customerID, String firstName, String lastName, String address, String phoneNumber) 
    {
        int result = 0;
        
        try
        {
            inertCustomerQuery.setInt      ( 1, customerID );
            inertCustomerQuery.setString   ( 2, firstName );
            inertCustomerQuery.setString   ( 3, lastName);
            inertCustomerQuery.setString   ( 4, address );
            inertCustomerQuery.setString   ( 5, phoneNumber);
            
            
        // insert the new entry; returns # of rows updated
            result = inertCustomerQuery.executeUpdate();
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
            closeConnection();
        } // end catch

        return result;
    }

    @Override
    public int insertVehicle(int VEHICLEID, String regoNumber, String make, String model, int yearManufactured, int mileage, int customerID) 
    {
        // inertVehicleQuery
        int result = 0;
        
        try
        {
            inertVehicleQuery.setInt      ( 1, VEHICLEID );
            inertVehicleQuery.setString   ( 2, regoNumber );
            inertVehicleQuery.setString   ( 3, make);
            inertVehicleQuery.setString   ( 4, model );
            inertVehicleQuery.setInt      ( 5, yearManufactured);
            inertVehicleQuery.setInt      ( 6, mileage);
            inertVehicleQuery.setInt      ( 7, customerID);

        // insert the new entry; returns # of rows updated
            result = inertVehicleQuery.executeUpdate();
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
            closeConnection();
        } // end catch

        return result;
    }

    @Override
    public int insertService(int serviceID, String serviceDescription, Date serviceDate, double servicePrice, String serviceStatus, int vehicleID) 
    {
         // inertSeviceBookingQuery
        int result = 0;
        
        try
        {
            inertSeviceBookingQuery.setInt      ( 1, serviceID );
            inertSeviceBookingQuery.setString   ( 2, serviceDescription );
            inertSeviceBookingQuery.setString   ( 3, serviceDate.toString());
            inertSeviceBookingQuery.setDouble   ( 4, servicePrice );
            inertSeviceBookingQuery.setString   ( 5, serviceStatus);
            inertSeviceBookingQuery.setInt      ( 6, vehicleID);   
            
        // insert the new entry; returns # of rows updated
            result = inertSeviceBookingQuery.executeUpdate();
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
            closeConnection();
        } // end catch

        return result;
    }

    @SuppressWarnings({"CallToPrintStackTrace", "empty-statement", "UnusedAssignment"})
    public ArrayList<String> carServicedByMake()
    {
        ResultSet resultSet = null;
        String resultString = "";
        ArrayList<String> carStats = new ArrayList<>();
        try
        {
            resultSet = carsServicedByMakeQuery.executeQuery();
            
            while ( resultSet.next() )
            {
                resultSet.getString("Make");
                resultSet.getInt("Counter");
                resultString = resultSet.getString("Make") + " | " +  resultSet.getInt("Counter" ) + " Serviced";
                if(carStats.size() < 3)
                {
                    carStats.add(resultString);
                }
                else
                {
                    ; 
                }
            } // end while
            
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
        } // end catch
        finally
        {
            try
        {
            resultSet.close();
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
            closeConnection();
        } // end catch
        } // end finally
        return carStats;
    } // end method carServicedByMake()       
        
    
    @Override
    public int updateCustomer(int customerID, String address, String phoneNumber) {
        int rowsUpdated = 0;

        try {

            updateCustomerQuery.setString(1, address);
            updateCustomerQuery.setString(2, phoneNumber);
            updateCustomerQuery.setInt(3, customerID);

            rowsUpdated = updateCustomerQuery.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            closeConnection();
        }

        return rowsUpdated;
    } // end method updateCustomer()
     
    @Override
    public int cancelService(int serviceID, String serviceStatus, double servicePrice) {
        int rowsUpdated = 0;

        try {

            cancelServiceQuery.setString(1, serviceStatus);
            cancelServiceQuery.setDouble(2, servicePrice);
            cancelServiceQuery.setInt(3, serviceID);

            rowsUpdated = cancelServiceQuery.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            closeConnection();
        }

        return rowsUpdated;
    } // end method cancelService
    
    
    @Override
    public void closeConnection() 
    {
        try
        {
            connection.close();
        } // end try
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
        } // end catch
    }   

} // end class Model
