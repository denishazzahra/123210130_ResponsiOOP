/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerresponsi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelresponsi.AlMaulModel;
import viewresponsi.AdminPageView;
import viewresponsi.EditRentView;

/**
 *
 * @author LENOVO
 */
public class EditRentController {
    EditRentView EPV;
    AlMaulModel AM;
    String[] data;

    public EditRentController(EditRentView EPV, AlMaulModel AM, String[] data) {
        this.EPV = EPV;
        this.AM = AM;
        this.data = data;
        setForm();
        EPV.btnedit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                AM.editRent(EPV.getName(),EPV.getId(),EPV.getContact(),EPV.getDuration(),data[6],data[1]);
                AdminPageView APV=new AdminPageView();
                AdminPageController APC=new AdminPageController(APV,AM);
                EPV.dispose();
            }
        });
    }
    void setForm(){
        EPV.tfname.setText(data[0]);
        EPV.tfid.setText(data[1]);
        EPV.tfcontact.setText(data[2]);
        EPV.tfduration.setText(data[3]);
    }
}
