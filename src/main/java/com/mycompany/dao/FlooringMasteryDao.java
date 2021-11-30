/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.dto.Order;
import java.util.List;

/**
 *
 * @author Blaine
 */
public interface FlooringMasteryDao {
    Order addOrder(Order order);

    Order prepOrder(Order order);
    List<Order> getAllOrders();

    public Order prepRemoveOrder(String[] orderToRemove);  
    public void removeOrder(Order orderToRemove);
    public void editOrder(Order finalTempOrder, Order editOrder);
    public void initializeDatabase();
    public void saveAndQuit();
    
}
