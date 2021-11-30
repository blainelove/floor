/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Blaine
 */
public class Order {
    private Integer OrderNumber;
    private String CustomerName;
    private String State;
    private BigDecimal TaxRate;
    private String ProductType;
    private BigDecimal Area;
    private BigDecimal CostPerSquareFoot;
    private BigDecimal LaborCostPerSquareFoot;
    private BigDecimal MaterialCost;
    private BigDecimal LaborCost;
    private BigDecimal Tax;
    private BigDecimal Total;
    private LocalDate Date;
   
//    public Order(Integer OrderNumber) {
//        this.OrderNumber = OrderNumber;
//    }

    public Integer getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(Integer OrderNumber) {
        this.OrderNumber = OrderNumber;
    }
    
    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }
    
    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }


    public BigDecimal getTaxRate() {
        return TaxRate;
    }
    
    public void setTaxRate(BigDecimal TaxRate) {
        this.TaxRate = TaxRate;
    }   
    
    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
    }
    
    public BigDecimal getArea() {
        return Area;
    }
    
    public void setArea(BigDecimal Area) {
        this.Area = Area;
    }
    
    public BigDecimal getLaborCostPerSquareFoot() {
        return LaborCostPerSquareFoot;
    }
    
    public void setLaborCostPerSquareFoot(BigDecimal LaborCostPerSquareFoot) {
        this.LaborCostPerSquareFoot = LaborCostPerSquareFoot;
    }
    
    public BigDecimal getCostPerSquareFoot() {
        return CostPerSquareFoot;
    }
    
    public void setCostPerSquareFoot(BigDecimal CostPerSquareFoot) {
        this.CostPerSquareFoot = CostPerSquareFoot;
    }
    
    public BigDecimal getMaterialCost() {
        return MaterialCost;
    }
    
    public void calcMaterialCost(BigDecimal Area, BigDecimal CostPerSquareFoot) {
        BigDecimal cost = Area.multiply(CostPerSquareFoot);
        setMaterialCost(cost);
    }
    
    public void setMaterialCost(BigDecimal MaterialCost) {
        this.MaterialCost = MaterialCost;
    }
    
    public BigDecimal getLaborCost() {
        return LaborCost ;
    }
    
    public void calcLaborCost (BigDecimal Area, BigDecimal LaborCostPerSquareFoot) {
        BigDecimal cost = Area.multiply(LaborCostPerSquareFoot);
        setLaborCost(cost);
    }
    public void setLaborCost (BigDecimal LaborCost) {
        this.LaborCost = LaborCost;
    }
    
    public BigDecimal getTax() {
        return Tax;
    }
    public void calcTax(BigDecimal MaterialCost, BigDecimal TaxRate, BigDecimal LaborCost) {
        BigDecimal hundred = new BigDecimal(100);
        BigDecimal taxR = TaxRate.divide(hundred);
        BigDecimal Cost = MaterialCost.add(LaborCost);
        BigDecimal costTax = Cost.multiply(taxR);
        setTax(costTax);
    }   
    public void setTax(BigDecimal Tax) {
        this.Tax = Tax;
    }   
    
    public BigDecimal getTotal() {
        return Total;
    }
    
    public void calcTotal(BigDecimal Tax, BigDecimal MaterialCost, BigDecimal LaborCost) {
        BigDecimal Cost = MaterialCost.add(LaborCost);
        BigDecimal costTotal = Tax.add(Cost);
        setTotal(costTotal);
    }   
    public void setTotal(BigDecimal Total) {
        this.Total = Total;
    }   
    
    public void setDate(LocalDate dt) {
        this.Date = dt;
    }   
    
    public LocalDate getDate() {
        return Date;
    }
}
