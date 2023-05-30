/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerresponsi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelresponsi.AlMaulModel;
import viewresponsi.AdminPageView;
import viewresponsi.LoginPageView;
import viewresponsi.RenterDataView;
import viewresponsi.RoomListView;

/**
 *
 * @author LENOVO
 */
public class LoginPageController {
    LoginPageView LP;
    AlMaulModel AM;

    public LoginPageController(LoginPageView LP, AlMaulModel AM) {
        this.LP = LP;
        this.AM = AM;
        LP.blogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(AM.checkLogin(LP.getUsername(), LP.getPassword()).equals("admin")){
                    AdminPageView APV=new AdminPageView();
                    AdminPageController APC=new AdminPageController(APV,AM);
                    LP.dispose();
                }
                else if(AM.checkLogin(LP.getUsername(), LP.getPassword()).equals("renter")){
                    RoomListView RDV=new RoomListView();
                    RoomListController RDC=new RoomListController(RDV,AM);
                    LP.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Wrong username/password!");
                }
            }
        });
    }
    
}
