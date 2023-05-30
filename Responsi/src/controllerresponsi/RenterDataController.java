/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerresponsi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelresponsi.AlMaulModel;
import viewresponsi.LoginPageView;
import viewresponsi.RenterDataView;

/**
 *
 * @author LENOVO
 */
class RenterDataController {
    RenterDataView RDV;
    AlMaulModel AM;
    String room;
    public RenterDataController(RenterDataView RDV, AlMaulModel AM, String room) {
        this.RDV = RDV;
        this.AM = AM;
        this.room=room;
        RDV.btnAddPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                AM.insertRent(RDV.getName(), RDV.getId(), RDV.getContact(), RDV.getRentTime(), room);
            }
        });
        RDV.btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                LoginPageView LPV=new LoginPageView();
                LoginPageController LPC=new LoginPageController(LPV,AM);
                RDV.window.dispose();
            }
        });
    }
    
}
