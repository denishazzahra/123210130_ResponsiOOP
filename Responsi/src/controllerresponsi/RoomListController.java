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
import viewresponsi.LoginPageView;
import viewresponsi.RenterDataView;
import viewresponsi.RoomListView;

/**
 *
 * @author LENOVO
 */
public class RoomListController {
    RoomListView RLV;
    AlMaulModel AM;
    String[][] data;
    int row;
    public RoomListController(RoomListView RLV, AlMaulModel AM) {
        this.RLV = RLV;
        this.AM = AM;
        showData();
        RLV.tabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                row=RLV.tabel.getSelectedRow();
                if(data[row][3].equals("empty"))
                {
                    RenterDataView RDV=new RenterDataView();
                    RenterDataController RDC=new RenterDataController(RDV,AM,data[row][0]);
                    RLV.window.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Room is occupied!");
                }
            }
        });
        RLV.bcancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                LoginPageView LPV=new LoginPageView();
                LoginPageController LPC=new LoginPageController(LPV,AM);
                RLV.window.dispose();
            }
        });
    }
    void showData(){
        data=AM.readRoom();
        String[] columnName={"Name","Size","Price","Status"};
        DefaultTableModel tableModel=new DefaultTableModel(data,columnName){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        RLV.tabel.setModel(tableModel);
    }
}
