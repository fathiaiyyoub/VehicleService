
package com.mycompany.testwithmaven;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.util.ArrayList;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author fali8
 */
public class GUIController implements Initializable {

    @FXML
    private Label vsmslabel;
    @FXML
    private ListView customerListView;
    @FXML
    private ListView vehicleListView;
    @FXML
    private ListView serviceListView;
    @FXML
    private Button addCustomerBtn;
    @FXML
    private Button updateCustomerBtn;
    @FXML
    private Button searchCustomerBtn;
    @FXML
    private Label customersLabel;
    @FXML
    private Label vehicleLabel;
    @FXML
    private Label servicingLabel;
    @FXML
    private Button addVehicleBtn;
    @FXML
    private Button diaplayAllVehiclesBtn;
    @FXML
    private Button addServiceBtn;
    @FXML
    private Button cancelServiceBtn;
    @FXML
    private Button viewStatisticsBtn;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField searchCustomerTextField;
    @FXML
    private TextField regoTextField;
    @FXML
    private TextField brandTextField;
    @FXML
    private TextField modelTextField;
    @FXML
    private TextField yearManuTextField;
    @FXML
    private TextField mileageTextArea;
    private ChoiceBox regoChoiceService;
    @FXML
    private ChoiceBox customerChoiceVehicle;
    @FXML
    private Button listAllServicesBtn;
    @FXML
    private DatePicker dateServicePicker;
    @FXML
    private TextField serviceTypeTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private Button searchServiceBtn;
    @FXML
    private TextField searchServiceTextField;
    @FXML
    private Button exitButton;
    @FXML
    private Button displayAllCustomersButton;
    @FXML
    private TextField phoneTextField;
    @FXML
    private ChoiceBox serviceVehicleIDChoice;   
    @FXML
    private Button resetServiceAreaData;
    
    Model model;
    ModelAssistant ma;

    public GUIController() 
    {
        // model object to access all methods of the Model class (Model.java)
        model = new Model();
        ma = new ModelAssistant();
    }

    @FXML
    private void addCustomerBtnHandler(ActionEvent event) 
    {
        // customerID logic 
        int customerID = model.getAllCustomers().size() + 1;
        
        String firstName; 
        if(firstNameTextField.getText().trim().length() == 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Data Entry Error:");
            alert.setContentText("Customer First Name cannot be BLANK!");
            alert.showAndWait();
            return;
        }
        else
        {
            firstName = firstNameTextField.getText();
        }
        
        String lastName; 
        if(lastNameTextField.getText().trim().length() == 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Data Entry Error:");
            alert.setContentText("Customer Last Name cannot be BLANK!");
            alert.showAndWait();
            return;
        }
        else
        {
            lastName = lastNameTextField.getText();
        }
        
        String address; 
        if(addressTextField.getText().trim().length() == 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Data Entry Error:");
            alert.setContentText("Customer Address cannot be BLANK!");
            alert.showAndWait();
            return;
        }
        else
        {
            address = addressTextField.getText();
        }
        
        String phoneNumber; 
        if(phoneTextField.getText().trim().length() == 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Data Entry Error:");
            alert.setContentText("Customer Phone Number cannot be BLANK!");
            alert.showAndWait();
            return;
        }
        else
        {
            phoneNumber = phoneTextField.getText();
        }
        
        for(Customer c : model.getAllCustomers())
        {
            if(c.getFirstName().equalsIgnoreCase(firstNameTextField.getText()) 
                    && c.getLastName().equalsIgnoreCase(lastNameTextField.getText()))
            {
                Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Error");
		alert.setHeaderText("Data Entry Error:");
		alert.setContentText("Customer already exists in our database!");

		alert.showAndWait();
                firstNameTextField.setEditable(true);
                lastNameTextField.setEditable(true);
                resetCustomerData();
                return;
            }
        }
        
        model.insertCustomer(customerID, firstName, lastName, address, phoneNumber);
        ObservableList<Number> customersOptionList = FXCollections.observableArrayList(ma.customerIdList());
        customerChoiceVehicle.setItems(customersOptionList);
        resetCustomerData();
    } // end method addCustomerBtnHandler

    void resetCustomerData()
    {
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        addressTextField.setText("");
        phoneTextField.setText("");  
    }
    
    @FXML
    private void displayAllCustomersButtonHnadler(ActionEvent event) 
    {
        ObservableList<String> customersViewList = FXCollections.observableArrayList(ma.customersList());
        customerListView.setItems(customersViewList);
    }
    
    @FXML
    private void populateUserData(MouseEvent event) throws Exception
    {
        try 
        {
            int i = customerListView.getSelectionModel().getSelectedIndex(); 
        
            ArrayList<Customer> clist = model.getAllCustomers();
            Customer c = clist.get(i);
            firstNameTextField.setText(c.getFirstName());
            lastNameTextField.setText(c.getLastName());
            firstNameTextField.setEditable(false);
            lastNameTextField.setEditable(false);
            addressTextField.setText(c.getAddress()) ; 
            phoneTextField.setText(c.getPhoneNumber());
        } 
        catch (IndexOutOfBoundsException e) 
        {
            
        }
    }
    
    @FXML
    private void updateCustomerBtnHandler(ActionEvent event) 
    {   int customerID = 0;
        for(Customer c : model.getAllCustomers())
        {
            if(c.getFirstName().equalsIgnoreCase(firstNameTextField.getText()) && 
                    c.getLastName().equalsIgnoreCase(lastNameTextField.getText()))
            {
                customerID = c.getCustomerID();
                //model.updateCustomer(addressTextField.getText(), phoneTextField.getText());
            }
            model.updateCustomer(customerID, addressTextField.getText(), phoneTextField.getText());
        }
        resetCustomerData();
        firstNameTextField.setEditable(true);
        lastNameTextField.setEditable(true);
    }

    @FXML
    @SuppressWarnings("empty-statement")
    private void searchCustomerBtnHandler(ActionEvent event) 
    {
        customerListView.setItems(null);
        String searchKey;
        
        if(searchCustomerTextField.getText().trim().length() == 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Search Key:");
            alert.setContentText("pleae Enter valid value!");
            alert.showAndWait();
            
            return;
        }
        else
        {
            searchKey = searchCustomerTextField.getText().toLowerCase();
        }
        for(Customer c : model.getAllCustomers())
        {
            if(c.toString().toLowerCase().contains(searchKey))
            {
                ArrayList<String> foundArrayList = new ArrayList<>();
                foundArrayList.add(c.toString());
                ObservableList<String> foundcustomersViewList = FXCollections.observableArrayList(foundArrayList);
                customerListView.setItems(foundcustomersViewList);
                return;
            }
            else
            {
                ;
            }   
        }
    } // end method searchCustomerBtnHandler
    
    
    @FXML
    private void addVehicleBtnHandler(ActionEvent event) 
    {
        int vehicleID = model.getAllVehicles().size() + 1;
        int cutomerID;
      
        if(customerChoiceVehicle.getValue() == null)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Data Entry Error:");
            alert.setContentText("Please select customer ID from Dropdown Munu!");
            alert.showAndWait();
            return;
        }
        else
        {
            cutomerID = (int) customerChoiceVehicle.getValue();
        }
        
        String regoNumber; 
        if(regoTextField.getText().trim().length() == 0 || regoTextField.getText().trim().length() > 6)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Data Entry Error:");
            alert.setContentText("Please enter vehicle Reg.#!");
            alert.showAndWait();
            return;
        }
        else
        {
            regoNumber = regoTextField.getText();
        }
        
        String make; 
        if(brandTextField.getText().trim().length() == 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Data Entry Error:");
            alert.setContentText("Please enter vehicle Make (Brand)!");
            alert.showAndWait();
            return;
        }
        else
        {
            make = brandTextField.getText();
        }
        
        String carModel;
        if(modelTextField.getText().trim().length() == 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Data Entry Error:");
            alert.setContentText("Please enter vehicle Model!");
            alert.showAndWait();
            return;
        }
        else
        {
            carModel = modelTextField.getText();
        }
        
        int yearManufactured;
        
        if(yearManuTextField.getText().trim().length() == 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Data Entry Error:");
            alert.setContentText("Please enter Manuf.Year!");
            alert.showAndWait();
            return;
        }
        else
        {
            try
            {
                yearManufactured = Integer.parseInt(yearManuTextField.getText());
            }
            catch(NumberFormatException nfe)
            {   
                yearManuTextField.setText("");
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Data Entry Error:");
                alert.setContentText("Please enter Number ONLY!");
                alert.showAndWait();
                return;
            }
        }
        
        int mileage;
        if(mileageTextArea.getText().trim().length() == 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Data Entry Error:");
            alert.setContentText("Please enter Mileage");
            alert.showAndWait();
            return;
        }
        else
        {
            try 
            {
                mileage = Integer.parseInt(mileageTextArea.getText());
            } 
            catch (NumberFormatException exception) 
            {
                mileageTextArea.setText("");
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Data Entry Error:");
                alert.setContentText("Please enter Number ONLY!");
                alert.showAndWait();
                return;
            }
        }
        
        // prevents duplicate entry
        for(Vehicle v : model.getAllVehicles())
        {
            if(v.getRegoNumber().equalsIgnoreCase(regoTextField.getText()))
            {
                Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Error");
		alert.setHeaderText("Data Entry Error:");
		alert.setContentText("Vehicle already exists in our database!");
                resetVehicleData();
		alert.showAndWait();
                return;
            }
        }
        
        model.insertVehicle(vehicleID, regoNumber, make, carModel, yearManufactured, mileage, cutomerID);
        ObservableList<Integer> IDOptionList = FXCollections.observableArrayList(ma.vehicleIDList());
        serviceVehicleIDChoice.setItems(IDOptionList);
        resetVehicleData();
    } // end method addVehicleBtnHandler
    
    void resetVehicleData()
    {
        customerChoiceVehicle.setValue(null);
        regoTextField.setText("");
        brandTextField.setText("");
        modelTextField.setText("");
        yearManuTextField.setText("");
        mileageTextArea .setText(""); 
    }
    
    @FXML
    private void diaplayAllVehiclesBtnHandler(ActionEvent event) 
    {
        ObservableList<String> vehicleViewList = FXCollections.observableArrayList(ma.vehiclesList());
        vehicleListView.setItems(vehicleViewList);
    }

    @FXML
    private void addServiceBtnHandler(ActionEvent event) 
    {
        int serviceID = model.getAllServices().size() + 1;
      
        int vehicleID;
        if(serviceVehicleIDChoice.getValue() == null)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Data Entry Error:");
            alert.setContentText("Please select vehicle ID");
            alert.showAndWait();
            return;
        }
        else
        {
            vehicleID = (int) serviceVehicleIDChoice.getValue();
        }
        
        String serviceDescription;
        if(serviceTypeTextField.getText().trim().length() == 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Data Entry Error:");
            alert.setContentText("Please Enter Service Description\nUpto 500 Characters");
            alert.showAndWait();
            return;
        }
        else
        {
            serviceDescription = serviceTypeTextField.getText();
        }
        
        Date serviceDate;
        if(dateServicePicker.getValue() == null)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Data Entry Error:");
            alert.setContentText("Please use calandar Enter Service Date");
            alert.showAndWait();
            return;
        }
        else
        {
            serviceDate = Date.valueOf(dateServicePicker.getValue());
        }
        
        double servicePrice;
        if(priceTextField.getText().trim().length() == 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Data Entry Error:");
            alert.setContentText("Please specifiy service price");
            alert.showAndWait();
            return;
        }
        else
        {
            try 
            {
                servicePrice = Double.parseDouble(priceTextField.getText());
            } 
            catch (NumberFormatException nfe) 
            {
                mileageTextArea.setText("");
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Data Entry Error:");
                alert.setContentText("Please enter Number ONLY!");
                alert.showAndWait();
                return;
            }
        }
        String serviceStatus = "Completed"; 
        model.insertService(serviceID, serviceDescription, serviceDate, servicePrice, serviceStatus, vehicleID);
        resetServiceData();
    } // end method addServiceBtnHandler

    
    void resetServiceData()
    {
        serviceVehicleIDChoice.setValue(null);
        serviceTypeTextField.setText("");
        dateServicePicker.setValue(null);
        priceTextField.setText("");
    }
    
    @FXML
    private void listAllServicesBtnHandler(ActionEvent event) 
    {
        ObservableList<String> servicesViewList = FXCollections.observableArrayList(ma.servicesList());
        serviceListView.setItems(servicesViewList);
    }
    
    @FXML
    @SuppressWarnings("UnnecessaryReturnStatement")
    private void serviceCancelled(MouseEvent event) throws Exception
    {
        try 
        {
            int i = serviceListView.getSelectionModel().getSelectedIndex(); 
        
            ArrayList<Service> slist= ma.sortedByPrice();

            Service s = slist.get(i);
            serviceTypeTextField.setText(s.getServiceDescription()); 
            priceTextField.setText(String.valueOf(s.getServicePrice()));
            serviceVehicleIDChoice.setValue(s.getVehicleID());
            serviceTypeTextField.setEditable(false);
            priceTextField.setEditable(false);
            serviceVehicleIDChoice.setDisable(true);
        } 
        catch (IndexOutOfBoundsException e) 
        {
            return;
        }
    }
    
    @FXML
    private void cancelServiceBtnHandler(ActionEvent event) 
    {   
        int serviceID = 0;
        
        if(serviceVehicleIDChoice.getValue() == null && serviceTypeTextField.getText().trim().length() == 0
                && priceTextField.getText().trim().length() == 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Selection Error:");
            alert.setContentText("Please chose a service to cancel!");
            alert.showAndWait();
            
            return;
        }
        
        
        for(Service s: ma.sortedByPrice())
        {
            if(s.toString().contains(serviceTypeTextField.getText())
                   &&  s.toString().contains(priceTextField.getText()))
            {
                serviceID = s.getServiceID();
            }
            model.cancelService(serviceID, "Cancelled", 0);
        }
        serviceTypeTextField.setEditable(true);
        priceTextField.setEditable(true);
        serviceVehicleIDChoice.setDisable(false);
        resetServiceData();
    }

    @FXML
    private void viewStatisticsBtnHandler(ActionEvent event) 
    {
        String listString = String.join(", ", model.carServicedByMake());
        //ma.minMaxAverage();
        //System.out.println(model.carServicedByMake());

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Statistics");
        dialog.setHeaderText("Statistics");
        TextArea textField = new TextArea();
        dialog.setHeight(400);
        dialog.setResizable(true);
        dialog.getDialogPane().setContent(textField);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        textField.setEditable(false);
        textField.setText(listString + "\n" + "\n" + ma.minMaxAverage());
        textField.setWrapText(true);
        dialog.showAndWait();
    }

    @FXML
    private void searchServiceBtnHandler(ActionEvent event) 
    {
        //serviceListView.setItems(null);
        String searchKey;
        ArrayList<String> foundArrayList = new ArrayList<>();
        
        if(searchServiceTextField.getText().trim().length() == 0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Search Key:");
            alert.setContentText("pleae Enter valid value!");
            alert.showAndWait();
            
            return;
        }
        else
        {
            searchKey = searchServiceTextField.getText().toLowerCase();
        }
        for(String s : ma.SearchByRegoNumber())
        {
            if(s.toLowerCase().contains(searchKey))
            {
                foundArrayList.add(s);            
            }
            else
            {
                serviceListView.setItems(null);
            }
        }
        ObservableList<String> foundServicesViewList = FXCollections.observableArrayList(foundArrayList);
        serviceListView.setItems(foundServicesViewList);
    }
    
    @FXML
    private void exitButtonHandler(ActionEvent event) 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit the application?");
        alert.showAndWait().ifPresent(response -> {
        if (response == ButtonType.OK)
        {
            Platform.exit();
        }
        });
    } // end method exitButtonHandler
    
    @FXML
    private void resetServiceAreaDataHandler(ActionEvent event) 
    {
        resetServiceData();
        serviceListView.setItems(null);
        serviceVehicleIDChoice.setDisable(false);
        serviceTypeTextField.setEditable(true);
        priceTextField.setEditable(true);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // customerChoiceVehicle - addVehicle method
        ObservableList<Number> customersOptionList = FXCollections.observableArrayList(ma.customerIdList());
        customerChoiceVehicle.setItems(customersOptionList);
        
        // IDChoiceService
        ObservableList<Integer> IDOptionList = FXCollections.observableArrayList(ma.vehicleIDList());
        serviceVehicleIDChoice.setItems(IDOptionList);
    }

}
