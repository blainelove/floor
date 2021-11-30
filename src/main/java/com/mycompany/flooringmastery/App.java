/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flooringmastery;

import com.mycompany.controller.FlooringMasteryController;
import com.mycompany.dao.FlooringMasteryDao;
import com.mycompany.dao.FlooringMasteryDaoFileImpl;
import com.mycompany.service.FlooringMasteryServiceLayerImpl;
import com.mycompany.ui.FlooringMasteryView;
import com.mycompany.ui.UserIO;
import com.mycompany.ui.UserIOConsoleImpl;

/**
 *
 * @author Blaine
 */
public class App {
    public static void main(String[] args) {
        UserIO fmIo = new UserIOConsoleImpl();
        FlooringMasteryView fmView = new FlooringMasteryView(fmIo);
        FlooringMasteryDao fmDao = new FlooringMasteryDaoFileImpl();
        FlooringMasteryServiceLayerImpl fmService = new FlooringMasteryServiceLayerImpl(fmDao);
        FlooringMasteryController controller = new FlooringMasteryController(fmService, fmView, fmIo);
        controller.run();
        
        
        //  UserIO vmIo = new UserIOConsoleImpl();
//          VendingMachineView vmView = new VendingMachineView(vmIo);
//          VendingMachineDao vmDao = new VendingMachineDaoFileImpl();
//          VendingMachineServiceLayerImpl vmService = new VendingMachineServiceLayerImpl(vmDao);
//          VendingMachineController controller = new VendingMachineController(vmService, vmView, vmIo);
//          controller.run();
    }
}
