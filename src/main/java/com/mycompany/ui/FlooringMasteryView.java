/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ui;

import com.mycompany.dto.Order;
import com.mycompany.dao.FlooringMasteryDaoFileImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 *
 * @author Blaine
 */
public class FlooringMasteryView {
    
    
    UserIO io;

    public FlooringMasteryView(UserIO fmIo) {
        this.io = fmIo;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove a Order");
        io.print("5. Save/Quit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }
    
    public Order getNewOrder() {
        String OrderDate = io.readString("Please enter order date MMddyyyy");
        LocalDate orderDateParsed = LocalDate.parse(OrderDate, DateTimeFormatter.ofPattern("MMddyyyy"));
        String CustomerName = io.readString("Please enter Customer Name");
        String State = io.readString("Please enter State");
        String ProductType = io.readString("Please enter Product Type");
        BigDecimal Area = io.readBigDecimal("Please enter Area");
//        Integer OrderNumber = (1);
        Order currentOrder = new Order();
        currentOrder.setDate(orderDateParsed);
        currentOrder.setCustomerName(CustomerName);
        currentOrder.setState(State);
        currentOrder.setProductType(ProductType);
        currentOrder.setArea(Area);
        return currentOrder;
    }
    
    public Boolean getOrderSummary(Order prepOrder) {
        Boolean decision;
        io.print("Please confirm order summary below:");
        String orderInfo = String.format("OrderDate: %s, Customer Name: %s, State: %s, TaxRate: %s, Product type: %s, Area: %s, CostPerSquareFoot: %s, LaborCostPerSquareFoot: %s, MaterialCost: %s, LaborCost: %s, Tax: %s,  Total: $%s",
                      prepOrder.getDate(),
                      prepOrder.getCustomerName(),
                      prepOrder.getState(),
                      prepOrder.getTaxRate(),
                      prepOrder.getProductType(),
                      prepOrder.getArea(),
                      prepOrder.getCostPerSquareFoot(),
                      prepOrder.getLaborCostPerSquareFoot(),
                      prepOrder.getMaterialCost(),
                      prepOrder.getLaborCost(),
                      prepOrder.getTax(),
                      prepOrder.getTotal());
                    io.print(orderInfo);
                    String yayNay = io.readString("Do you want to place the order? (Y/N)");
                    if(yayNay.equals("Y")){
                        decision = Boolean.valueOf("True");
                    }else{
                        decision = Boolean.valueOf("False");
                    }
                    return decision;
    }
    
    public Boolean getRemoveOrderSummary(Order orderToRemove) {
        Boolean decision;
        io.print("Please confirm order to remove below:");
        String orderInfo = String.format("OrderDate: %s, Customer Name: %s, State: %s, TaxRate: %s, Product type: %s, Area: %s, CostPerSquareFoot: %s, LaborCostPerSquareFoot: %s, MaterialCost: %s, LaborCost: %s, Tax: %s,  Total: $%s",
                      orderToRemove.getDate(),
                      orderToRemove.getCustomerName(),
                      orderToRemove.getState(),
                      orderToRemove.getTaxRate(),
                      orderToRemove.getProductType(),
                      orderToRemove.getArea(),
                      orderToRemove.getCostPerSquareFoot(),
                      orderToRemove.getLaborCostPerSquareFoot(),
                      orderToRemove.getMaterialCost(),
                      orderToRemove.getLaborCost(),
                      orderToRemove.getTax(),
                      orderToRemove.getTotal());
                    io.print(orderInfo);
                    String yayNay = io.readString("Do you want to remove the order? (Y/N)");
                    if(yayNay.equals("Y")){
                        decision = Boolean.valueOf("True");
                    }else{
                        decision = Boolean.valueOf("False");
                    }
                    return decision;
    }
    
    public Order getEditOrderData(Order orderToEdit) {
        String newCustomerName = io.readString(String.format("Enter new Customer Name (current customer name is %s)", orderToEdit.getCustomerName()));
        String newState = io.readString(String.format("Enter new State (current State is %s)", orderToEdit.getState()));
        String newProductType = io.readString(String.format("Enter new Product Type (current Product Type is %s)", orderToEdit.getProductType()));
        BigDecimal newArea = io.readBigDecimal(String.format("Enter new Area (current area is %s)", orderToEdit.getArea()));
        
        
        Order tempOrder = new Order();
        tempOrder.setCustomerName(newCustomerName);
        tempOrder.setState(newState);
        tempOrder.setProductType(newProductType);
        tempOrder.setArea(newArea);
        return tempOrder;
        
        // todo check if enter is hit what returns null or empty ""
    }

    public Boolean getEditOrderSummary(Order finalTempOrder, Order editOrder) {
        Boolean decision;
        String customerName;
        if(finalTempOrder.getCustomerName().isEmpty()){
            customerName = editOrder.getCustomerName();
        } else {
            customerName = finalTempOrder.getCustomerName();
        }
        io.print("Please confirm order to edit below:");
        String orderInfo = String.format("Customer Name: %s, State: %s, TaxRate: %s, Product type: %s, Area: %s, CostPerSquareFoot: %s, LaborCostPerSquareFoot: %s, MaterialCost: %s, LaborCost: %s, Tax: %s,  Total: $%s",
                      customerName,
                      finalTempOrder.getState(),
                      finalTempOrder.getTaxRate(),
                      finalTempOrder.getProductType(),
                      finalTempOrder.getArea(),
                      finalTempOrder.getCostPerSquareFoot(),
                      finalTempOrder.getLaborCostPerSquareFoot(),
                      finalTempOrder.getMaterialCost(),
                      finalTempOrder.getLaborCost(),
                      finalTempOrder.getTax(),
                      finalTempOrder.getTotal());
                    io.print(orderInfo);
                    String yayNay = io.readString("Do you want to edit the order? (Y/N)");
                    if(yayNay.equals("Y")){
                        decision = Boolean.valueOf("True");
                    }else{
                        decision = Boolean.valueOf("False");
                    }
                    return decision;
    }
                    
    public String[] getOrderChoice() {
        String orderDate = io.readString("Please enter order date MMddyyyy");
        String orderNumber = io.readString("Please enter Order Number");
        String[] orderDetails = {orderDate, orderNumber};
        return orderDetails;
    }
    
    public String displayOrderList(List<Order> orderList) {
        String userDate = io.readString("Please enter order date MMddyyyy");
        LocalDate userDateParsed = LocalDate.parse(userDate, DateTimeFormatter.ofPattern("MMddyyyy"));
        List<Order> sameDateOrders = orderList.stream()
                                    .filter((s) -> (s.getDate().equals(userDateParsed)))
                                    .collect(Collectors.toList());
        sameDateOrders.stream()
//          orderList.stream()
                .forEach((s) -> {
                    String orderInfo = String.format("OrderNumber: %s, OrderDate: %s, Customer Name: %s, State: %s, TaxRate: %s, Product type: %s, Area: %s, CostPerSquareFoot: %s, LaborCostPerSquareFoot: %s, MaterialCost: %s, LaborCost: %s, Tax: %s,  Total: $%s",
                      s.getOrderNumber(),
                      s.getDate(),
                      s.getCustomerName(),
                      s.getState(),
                      s.getTaxRate(),
                      s.getProductType(),
                      s.getArea(),
                      s.getCostPerSquareFoot(),
                      s.getLaborCostPerSquareFoot(),
                      s.getMaterialCost(),
                      s.getLaborCost(),
                      s.getTax(),
                      s.getTotal());
                    io.print(orderInfo);
                });
        return null;
    }
}