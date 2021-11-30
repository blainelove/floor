package com.mycompany.controller;

import com.mycompany.dto.Order;
import com.mycompany.service.FlooringMasteryServiceLayer;
import com.mycompany.ui.FlooringMasteryView;
import com.mycompany.ui.UserIOConsoleImpl;
import com.mycompany.ui.UserIO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Blaine
 */
public class FlooringMasteryController {
    
    private FlooringMasteryView view;
    private FlooringMasteryServiceLayer service;
    private UserIO io;
    
    
    public FlooringMasteryController (FlooringMasteryServiceLayer service, FlooringMasteryView view, UserIO io){
        this.service = service;
        this.view = view;
        this.io = io;
    }
    
    public void run() {
        
        boolean keepGoing = true;
        int menuSelection = 0;
        
        //this will load current database into program on start
        service.initalizeDatabase();
        
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    datedisplayOrderList();
                    break;
                case 2:
                    createOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    saveAndQuit();
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        }
        io.print("GOOD BYE");
    }
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
      private void createOrder() {
        Order newOrder = view.getNewOrder();
        Order preporder = service.prepOrder(newOrder);
        Boolean decision = view.getOrderSummary(preporder);
        Boolean proceed = Boolean.valueOf("True");
        if(decision == proceed){
            service.addOrder(newOrder);  
      }
    }
    
    private void datedisplayOrderList() {
        List<Order> orderList = service.getAllOrders();
        view.displayOrderList(orderList);
        
    }
    
    private void editOrder() {
        String[] orderToEdit = view.getOrderChoice();
        Order editOrder = service.prepRemoveOrder(orderToEdit); //todo rename
        Order tempOrder = view.getEditOrderData(editOrder);
        Order finalTempOrder = service.prepOrder(tempOrder);
        Boolean decision = view.getEditOrderSummary(finalTempOrder, editOrder);
        Boolean proceed = Boolean.valueOf("True");
        if(decision == proceed){
            service.editOrder(finalTempOrder, editOrder);  
        }
    }
    
    private void removeOrder() {
        String[] orderToRemove = view.getOrderChoice();
        Order removeableorder = service.prepRemoveOrder(orderToRemove);
        Boolean decision = view.getRemoveOrderSummary(removeableorder);
        Boolean proceed = Boolean.valueOf("True");
        if(decision == proceed){
            service.removeOrder(removeableorder);  
      }
        
    }
    
    private void saveAndQuit() {
        service.saveAndQuit();
    }
}

