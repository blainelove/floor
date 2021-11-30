/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static javax.management.Query.value;
import static javax.management.Query.value;

/**
 *
 * @author Blaine
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao{
    private Map<Integer, Order> orders = new HashMap<>();
    public Map<String, BigDecimal[]> products = new HashMap<>();
    public Map<String, String[]> states = new HashMap<>();
    public final String flooring_delimiter = ",";
    public final String orders_dir = "Orders";
    public final String taxes = "Data/Taxes.txt";
    public final String products_inv = "Data/Products.txt";
    public final String export = "Backup/DataExport.txt";

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<Order>(orders.values());
    }

    @Override
    public Order prepOrder( Order preporder) {
        String product = preporder.getProductType();
        BigDecimal cost = products.get(product)[0];
        preporder.setCostPerSquareFoot(cost);
        BigDecimal labor = products.get(product)[1];
        preporder.setLaborCostPerSquareFoot(labor);
        preporder.calcMaterialCost(preporder.getArea(), cost);
        preporder.calcLaborCost(preporder.getArea(), labor);
        String state = preporder.getState();
        String taxRateObj = states.get(state)[1];
        BigDecimal taxrate = new BigDecimal(taxRateObj);
        preporder.setTaxRate(taxrate);
        preporder.calcTax(preporder.getMaterialCost(), taxrate, preporder.getLaborCost());
        preporder.calcTotal(preporder.getTax(), preporder.getMaterialCost(), preporder.getLaborCost());
        return preporder;
    }
    @Override
    public Order addOrder( Order order) {
        String product = order.getProductType();
        BigDecimal cost = products.get(product)[0];
        order.setCostPerSquareFoot(cost);
        BigDecimal labor = products.get(product)[1];
        order.setLaborCostPerSquareFoot(labor);
        order.calcMaterialCost(order.getArea(), cost);
        order.calcLaborCost(order.getArea(), labor);
        String state = order.getState();
        String taxRateObj = states.get(state)[1];
        BigDecimal taxrate = new BigDecimal(taxRateObj);
        order.setTaxRate(taxrate);
        order.calcTax(order.getMaterialCost(), taxrate, order.getLaborCost());
        order.calcTotal(order.getTax(), order.getMaterialCost(), order.getLaborCost());
        order.setOrderNumber(orders.size() + 1);
        Order newOrder = orders.put(order.getOrderNumber(), order);
        
        return newOrder;
    }
    
    @Override
    public void initializeDatabase() {
        Scanner tc = null;
        Scanner pc = null;
        LocalDate dt = null;
        String currentLine;
        Order currentOrder;
        
        try {
            File dir = new File(orders_dir);
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    Scanner sc = null;
                    sc = new Scanner(new BufferedReader(new FileReader(child)));
                    String fileName = child.getName().split("_")[1];
                    String dtAsString = fileName.split("\\.")[0];
                    LocalDate dtAsLocalDate = LocalDate.parse(dtAsString, DateTimeFormatter.ofPattern("MMddyyyy"));
                    sc.nextLine(); // Ignore header in txt file
                    while(sc.hasNextLine()){
                        currentLine = sc.nextLine();
                        currentOrder = unmarshallOrder(currentLine, dtAsLocalDate);
                        orders.put(currentOrder.getOrderNumber(), currentOrder);
                        }
                }
            }
            tc = new Scanner(new BufferedReader(new FileReader(taxes)));
            pc = new Scanner(new BufferedReader(new FileReader(products_inv)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlooringMasteryDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pc.nextLine(); // Ignore header in txt file
        while(pc.hasNextLine()){
            currentLine = pc.nextLine();
            unmarshallProduct(currentLine);
            }   
        
        tc.nextLine(); // Ignore header in txt file
        while(tc.hasNextLine()){
            currentLine = tc.nextLine();
            unmarshallState(currentLine);
            
            }   
        }
    
    private Order unmarshallOrder(String orderAsText, LocalDate dt) {
        String[] orderFields = orderAsText.split(flooring_delimiter);
        Order newOrder = new Order();
        newOrder.setOrderNumber(orders.size() + 1);
        newOrder.setCustomerName(orderFields[1]);
        newOrder.setState(orderFields[2]);
        newOrder.setTaxRate(BigDecimal.valueOf(Double.parseDouble(orderFields[3])));
        newOrder.setProductType(orderFields[4]);
        newOrder.setArea(BigDecimal.valueOf(Double.parseDouble(orderFields[5])));
        newOrder.setCostPerSquareFoot(BigDecimal.valueOf(Double.parseDouble(orderFields[6])));
        newOrder.setLaborCostPerSquareFoot(BigDecimal.valueOf(Double.parseDouble(orderFields[7])));
        newOrder.setMaterialCost(BigDecimal.valueOf(Double.parseDouble(orderFields[8])));
        newOrder.setLaborCost(BigDecimal.valueOf(Double.parseDouble(orderFields[9])));
        newOrder.setTax(BigDecimal.valueOf(Double.parseDouble(orderFields[10])));
        newOrder.setTotal(BigDecimal.valueOf(Double.parseDouble(orderFields[11])));
        newOrder.setDate(dt);
        return newOrder;
        }
    private void unmarshallProduct(String productAsText) {
        String[] orderFields = productAsText.split(flooring_delimiter);
        BigDecimal costPerSquareFoot = BigDecimal.valueOf(Double.parseDouble(orderFields[1]));
        BigDecimal laborCostPerSquareFoot = BigDecimal.valueOf(Double.parseDouble(orderFields[2]));
        BigDecimal[] costs = {costPerSquareFoot, laborCostPerSquareFoot};
        products.put(orderFields[0], costs);
        }
    
    private void unmarshallState(String stateAsText) {
        String[] orderFields = stateAsText.split(flooring_delimiter);
        String state = orderFields[1];
        String taxRate = (orderFields[2]);
        String[] stateObj = {state,taxRate};
        states.put(orderFields[0], stateObj);
        }
        
    @Override
    public void saveAndQuit() {
        PrintWriter out;
        
        try {
            //Write backup file with all orders across all dates
            out = new PrintWriter(new FileWriter(export));
            String textOrder;
            String header;
            List<Order> listOfOrders = new ArrayList<Order>(orders.values());
            header = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total,Date";
            out.println(header);
            out.flush();
            for (Order currentOrder : listOfOrders){
                textOrder = marshallOrder(currentOrder); 
                out.println(textOrder);
                out.flush();
                }
            out.close();
            
            //Write orders to dated Orders directory
            List<LocalDate> listOfDates= new ArrayList<LocalDate>();
            for (Order o : listOfOrders){
                if (!listOfDates.contains(o.getDate())) {
                    listOfDates.add(o.getDate());
                    }
            }
            for (LocalDate dt : listOfDates){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
                String dtAsString = dt.format(formatter);
                String fileName = orders_dir + String.format("/Orders_%s.txt", dtAsString);
                out = new PrintWriter(new FileWriter(fileName));
                String header2;
                header2 = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total";
                out.println(header2);
                out.flush();
                List<Order> sameDateOrders = listOfOrders.stream()
                                    .filter((s) -> (s.getDate().equals(dt)))
                                    .collect(Collectors.toList());
                for (Order currentOrder : sameDateOrders){
                    textOrder = marshallOrder(currentOrder); 
                    out.println(textOrder);
                    out.flush();
                    }
                out.close();
            }

        } catch (IOException ex){
            Logger.getLogger(FlooringMasteryDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
    
    private String marshallOrder(Order aOrder){
        String writeOrder = aOrder.getOrderNumber() + flooring_delimiter;
        writeOrder += aOrder.getCustomerName() + flooring_delimiter;
        writeOrder += aOrder.getState() + flooring_delimiter;
        writeOrder += aOrder.getTaxRate() + flooring_delimiter;
        writeOrder += aOrder.getProductType() + flooring_delimiter;
        writeOrder += aOrder.getArea() + flooring_delimiter;
        writeOrder += aOrder.getCostPerSquareFoot() + flooring_delimiter;
        writeOrder += aOrder.getLaborCostPerSquareFoot() + flooring_delimiter;
        writeOrder += aOrder.getMaterialCost() + flooring_delimiter;
        writeOrder += aOrder.getLaborCost() + flooring_delimiter;
        writeOrder += aOrder.getTax() + flooring_delimiter;
        writeOrder += aOrder.getTotal() + flooring_delimiter;
        writeOrder += aOrder.getDate();
        
        return writeOrder;
        }
    
    @Override
    public Order prepRemoveOrder(String[] orderToRemove) {
        List<Order> listOfOrders = new ArrayList<Order>(orders.values());
        LocalDate orderDateParsed = LocalDate.parse(orderToRemove[0], DateTimeFormatter.ofPattern("MMddyyyy"));
        Integer orderNum = Integer.valueOf(orderToRemove[1]);
        
        List<Order> removableOrder = listOfOrders.stream()
                                    .filter((s) -> (s.getDate().equals(orderDateParsed)))
                                    .filter((s) -> (s.getOrderNumber()==orderNum))
                                    .collect(Collectors.toList());
        return removableOrder.get(0);
    }

    @Override
    public void removeOrder(Order orderToRemove) {
        orders.remove(orderToRemove.getOrderNumber());
    }
    
    @Override
    public void editOrder(Order finalTempOrder, Order editOrder) {
        
        if(!finalTempOrder.getCustomerName().isEmpty() && finalTempOrder.getCustomerName() != editOrder.getCustomerName()) {
            editOrder.setCustomerName(finalTempOrder.getCustomerName());
        }
        if(!finalTempOrder.getState().isEmpty() && finalTempOrder.getState() != editOrder.getState()) {
            editOrder.setState(finalTempOrder.getState());
        }
        if(!finalTempOrder.getProductType().isEmpty() && finalTempOrder.getProductType() != editOrder.getProductType()) {
            editOrder.setProductType(finalTempOrder.getProductType());
        }
        
        editOrder.setTaxRate(finalTempOrder.getTaxRate());
        editOrder.setArea(finalTempOrder.getArea());
        editOrder.setLaborCostPerSquareFoot(finalTempOrder.getLaborCostPerSquareFoot());
        editOrder.setCostPerSquareFoot(finalTempOrder.getCostPerSquareFoot());
        editOrder.setMaterialCost(finalTempOrder.getMaterialCost());
        editOrder.setLaborCost(finalTempOrder.getLaborCost());
        editOrder.setTax(finalTempOrder.getTax());
        editOrder.setTotal(finalTempOrder.getTotal());
    }
    
}
