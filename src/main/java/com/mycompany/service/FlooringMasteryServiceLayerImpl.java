/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.dao.FlooringMasteryDao;
import com.mycompany.dto.Order;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Blaine
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer{
    
    FlooringMasteryDao dao;
    
    public FlooringMasteryServiceLayerImpl(FlooringMasteryDao fmDao) {
        this.dao = fmDao;
    }
    

    
    @Override
    public void initalizeDatabase() {
        dao.initializeDatabase();
    }
    
    @Override
    public void saveAndQuit() {
        dao.saveAndQuit();
    }
    
    @Override
    public Order addOrder(Order order) {
        return dao.addOrder(order);
    }
    
    @Override
    public Order prepOrder(Order order){
        return dao.prepOrder(order);
    }
    
    @Override
    public List<Order> getAllOrders() {
        return dao.getAllOrders();
    }
    
    @Override
    public Order prepRemoveOrder(String[] orderToRemove) {
        return dao.prepRemoveOrder(orderToRemove);
    }
    
    @Override
    public void removeOrder(Order orderToRemove) {
        dao.removeOrder(orderToRemove);
    }
    
    @Override
    public void editOrder(Order finalTempOrder, Order editOrder){
        dao.editOrder(finalTempOrder, editOrder);
    }
    
}
