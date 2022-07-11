
package com.mycompany.testwithmaven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class was created to achieve further decoupling and cleaner Controller implementation
 * it holds secondary methods that are neither used to interface with the database 
 * nor directly handling GUI components.
 * 
 */
@SuppressWarnings("empty-statement")
public class ModelAssistant 
{
    Model model;
    public ModelAssistant() 
    {
        model = new Model();
    } // end constructor
    
    public ArrayList<String> customersList()
    // implements collection oveservableArrayList()
    {
	ArrayList<String> customersViewList = new ArrayList<>();
	String customerToString;

	for(Customer c: model.getAllCustomers())          
	{
	    customerToString =  c.toString();
	    customersViewList.add(customerToString);
	}
	return customersViewList;
    } // end method customersList()
    
    public ArrayList<Integer> customerIdList()
    // implements collection oveservableArrayList()
    {
	ArrayList<Integer> customerChoices = new ArrayList<>();
	int customerIdIntegrChoice;

	for(Customer c: model.getAllCustomers())    
	{
	    customerIdIntegrChoice =  c.getCustomerID();
	    customerChoices.add(customerIdIntegrChoice);
	}
	return customerChoices;
    } // end method customerIdList
    
    public ArrayList<String> vehiclesList()
    // implements collection oveservableArrayList()
    {
	ArrayList<String> vehiclesViewList = new ArrayList<>();
	String vehicleToString;

	for(Vehicle v: model.getAllVehicles())    
	{
	    vehicleToString =  v.toString();
	    vehiclesViewList.add(vehicleToString);
	}
	return vehiclesViewList;
    } // end method vehiclesList
    
    public ArrayList<Integer> vehicleIDList()
    // implements collection oveservableArrayList()
    {
	ArrayList<Integer> vehicleIDChoices = new ArrayList<>();
	int vehicleID;

	for(Vehicle v: model.getAllVehicles())            
	{
	    vehicleID =  v.getVEHICLEID();
            vehicleIDChoices.add(vehicleID);
	}
	    return vehicleIDChoices;
    } // end method vehicleIDList
    
    public ArrayList<Service> sortedByPrice()
    {
       ArrayList<Service> sortedArrayList = new ArrayList<>();
       
       for(Service s : model.getAllServices())
       {
           if(s.getServicePrice() == 0)
           {
               ;
           }
           else 
           {
               sortedArrayList.add(s);
           }
       }
       Collections.sort(sortedArrayList, Comparator.comparing(Service::getServicePrice)); // .reversed()
       return sortedArrayList;       
    } // end method sortedByPrice()
    
    public ArrayList<String> servicesList()
    // implements collection oveservableArrayList()
    {
	ArrayList<String> servicesViewList = new ArrayList<>();
	String serviceToString;

	for(Service s: sortedByPrice())                    
	{
	    serviceToString =  s.toString();
	    servicesViewList.add(serviceToString);
	}
	return servicesViewList;
    } // end method servicesList() 
    
    
    public String minMaxAverage()
    {
        double allServicesPrice = 0;
        double average = 0;
        String averages = "";
        try 
        {
            for(Service s : sortedByPrice())
            {
                allServicesPrice += s.getServicePrice(); 
            }
            average = (allServicesPrice * 1.0 / model.getAllServices().size());
 
            Service max = Collections.max(sortedByPrice());
            Service min = Collections.min(sortedByPrice());

            String a = String.valueOf("Total Services Price: " + String.format("$%.3f", allServicesPrice));
            String b = String.valueOf("Average Service Price: " + String.format("$%.3f", average));
            String c = ("Minimum Service Price: $" + String.valueOf(min.getServicePrice()));
            String d = ("Maximum Service Price: $" + String.valueOf(max.getServicePrice()));

            averages = a + "\n" + b + "\n" + c + "\n" + d;
            
        } 
        catch (Exception e) 
        {
            ;
        }
        return averages;
    } // end method minMaxAverage
    
    public ArrayList<String> SearchByRegoNumber()
    {
        String foundString = null;
        ArrayList<String> foundServices = new ArrayList<>();
        
        for(Service s : model.getAllServices())
        {
            for(Vehicle v : model.getAllVehicles())
            {
                if(s.getVehicleID() == v.getVEHICLEID())
                {
                    foundString = "Service ID: " + s.getServiceID() + " | " + " Service Date: " +
                    s.getServiceDate() +  " | " + "Vehicle ID: " + v.getVEHICLEID() + " | " 
                    + "Rego: " + v.getRegoNumber() + " | Make: " + v.getMake();  
                }  
            }
            foundServices.add(foundString);
        }        
        return foundServices;
    } // end SearchByRegoNumber()
} // end class ModelAssistant
