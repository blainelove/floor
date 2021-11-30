/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.dto.Order;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Blaine
 */
public interface FlooringMasteryServiceLayer {
    public Order addOrder(Order order);
    public Order prepOrder(Order order);
    public List<Order> getAllOrders();
    
    public Order prepRemoveOrder(String[] orderToRemove);
    public void removeOrder(Order orderToRemove);
    public void editOrder(Order finalTempOrder, Order editOrder);
    public void initalizeDatabase();
    public void saveAndQuit();
}
