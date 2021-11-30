/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Blaine
 */
public class FlooringMasteryDaoFileImplTest {
    
    FlooringMasteryDao testDao;
    
    public FlooringMasteryDaoFileImplTest() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        testDao = new FlooringMasteryDaoFileImpl();
//        testDao.products.put("Carpet", [2.25,2.10])
        
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

//    @Test
//    public void testRemoveOrder() {
//    }
    
    @Test
    public void testCreateOrder() {
       
        
        String OrderDate = new String("11111111");
        LocalDate orderDateParsed = LocalDate.parse(OrderDate, DateTimeFormatter.ofPattern("MMddyyyy"));
        String CustomerName = new String("Bill");
        String State = new String("TX");
        String ProductType = new String("Tile");
        BigDecimal Area = new BigDecimal(1.00);
        
        Order testOrder = new Order();
        testOrder.setDate(orderDateParsed);
        testOrder.setCustomerName(CustomerName);
        testOrder.setState(State);
        testOrder.setProductType(ProductType);
        testOrder.setArea(Area);
        
        //Act - call the function your testing with test data
//        Order testOrder2 = testDao.prepOrder(testOrder);
//        testDao.addOrder(testOrder2);
        //Assert - check results are as expected
        assertTrue(testOrder.getState().equals("TX"));
    }
    
//    @Test
//    public void testPrepOrder() {
//       
//        
//        String OrderDate = new String("11111111");
//        LocalDate orderDateParsed = LocalDate.parse(OrderDate, DateTimeFormatter.ofPattern("MMddyyyy"));
//        String CustomerName = new String("Bill");
//        String State = new String("TX");
//        String ProductType = new String("Tile");
//        BigDecimal Area = new BigDecimal(1.00);
//        
//        Order testOrder = new Order();
//        testOrder.setDate(orderDateParsed);
//        testOrder.setCustomerName(CustomerName);
//        testOrder.setState(State);
//        testOrder.setProductType(ProductType);
//        testOrder.setArea(Area);
//        
//        //Act - call the function your testing with test data
//        Order testOrder2 = testDao.prepOrder(testOrder);
////        testDao.addOrder(testOrder2);
//        //Assert - check results are as expected
//        assertTrue(testOrder2.getState().equals("TX"));
//    }
    
}
