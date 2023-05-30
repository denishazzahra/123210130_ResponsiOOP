/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerresponsi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelresponsi.AlMaulModel;
import viewresponsi.AdminPageView;
import viewresponsi.EditRentView;
import viewresponsi.LoginPageView;
import viewresponsi.RenterDataView;

/**
 *
 * @author LENOVO
 */
public class AdminPageController {
    AdminPageView APV;
    AlMaulModel AM;
    String[][] data;
    int row;
    public AdminPageController(AdminPageView APV, AlMaulModel AM) {
        this.APV = APV;
        this.AM = AM;
        showData();
        APV.blogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                LoginPageView LPV=new LoginPageView();
                LoginPageController LPC=new LoginPageController(LPV,AM);
                APV.window.dispose();
            }
        });
        APV.tabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                row=APV.tabel.getSelectedRow();
                if(data[row][5].equals("notPaid"))
                {
                    int input=JOptionPane.showConfirmDialog(null,"Update booking status of renter '"+data[row][0]+"' to 'Paid'?","Option",JOptionPane.YES_NO_OPTION);
                    if(input==0){
                        AM.updateStatus(data[row][0], data[row][1], data[row][6]);
                        showData();
                    }
                }
                else{
                    int input=JOptionPane.showConfirmDialog(null,"Delete booking of room '"+data[row][6]+"'?","Option",JOptionPane.YES_NO_OPTION);
                    if(input==0){
                        AM.deleteRent(data[row][1],data[row][6]);
                        showData();
                    }else{
                        int input1=JOptionPane.showConfirmDialog(null,"Edit booking of room '"+data[row][6]+"'?","Option",JOptionPane.YES_NO_OPTION);
                        if(input1==0){
                            EditRentView ERV=new EditRentView();
                            EditRentController EPC=new EditRentController(ERV,AM,data[row]);
                            ERV.setVisible(true);
                            APV.window.dispose();
                        }
                    }
                }
            }
        });
    }
    void showData(){
        data=AM.readRenter();
        String[] columnName={"Name","ID","Contact","Duration","Bill","Status","Room"};
        DefaultTableModel tableModel=new DefaultTableModel(data,columnName){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        APV.tabel.setModel(tableModel);
    }
}
