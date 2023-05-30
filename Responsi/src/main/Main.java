/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controllerresponsi.LoginPageController;
import modelresponsi.AlMaulModel;
import viewresponsi.LoginPageView;

/**
 *
 * @author LENOVO
 */
public class Main {
    public static void main(String[] args) {
        LoginPageView LP=new LoginPageView();
        AlMaulModel AM=new AlMaulModel();
        LoginPageController LPC=new LoginPageController(LP,AM);
        LP.setVisible(true);
    }
}
