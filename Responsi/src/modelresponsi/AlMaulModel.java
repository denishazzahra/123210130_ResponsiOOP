/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelresponsi;

import javax.swing.JOptionPane;
import java.sql.*;
/**
 *
 * @author LENOVO
 */
public class AlMaulModel extends Connector implements PriceInterface {
    public String checkLogin(String user, String pass){
        try{
            String query="SELECT role from accounts where username='"+user+"' and password='"+pass+"'";
            statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            while(rs.next()){
                if(rs.getString("role").equals("admin")){
                    return "admin";
                }
                else if(rs.getString("role").equals("renter")){
                    return "renter";
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Checking failed!");
        }
        return "none";
    }
    public String[][] readRoom(){
        String[][] data=new String[numOfRoom()][4];
        int total=0;
        try{
            String query="SELECT * from rooms";
            statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            while(rs.next()){
                data[total][0]=rs.getString("name");
                data[total][1]=rs.getString("size");
                data[total][2]=rs.getString("price");
                data[total][3]=rs.getString("status");
                total++;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Reading failed!");
        }
        return data;
    }
    public int numOfRoom(){
        int total=0;
        try{
            String query="SELECT * from rooms";
            statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            while(rs.next()){
                total++;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Reading failed!");
        }
        return total;
    }
    public void insertRent(String name,String id, String contact, int duration, String room){
        try{
            String query="INSERT INTO renter VALUES('"+name+"','"+id+"','"+contact+"','"+duration+"','"+getPrice(room,duration)+"', 'notPaid','"+room+"')";
            System.out.println(query);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Booking success!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Insert failed!");
        }
    }
    @Override
    public int getPrice(String room,int duration){
        int price=0;
        try{
            String query="SELECT price from rooms where name='"+room+"'";
            statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            while(rs.next()){
                price=Integer.parseInt(rs.getString("price"))*duration;
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Getting price failed!");
        }
        return price;
    }
    public String[][] readRenter(){
        String[][] data=new String[numOfRenter()][7];
        int total=0;
        try{
            String query="SELECT * from renter";
            statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            while(rs.next()){
                data[total][0]=rs.getString("name");
                data[total][1]=rs.getString("id");
                data[total][2]=rs.getString("contact");
                data[total][3]=rs.getString("duration");
                data[total][4]=rs.getString("bill");
                data[total][5]=rs.getString("status");
                data[total][6]=rs.getString("room");
                total++;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Reading failed!");
        }
        return data;
    }
    public int numOfRenter(){
        int total=0;
        try{
            String query="SELECT * from renter";
            statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            while(rs.next()){
                total++;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Reading failed!");
        }
        return total;
    }
    public void deleteRent(String id,String room){
        try{
            String query="DELETE from renter where id='"+id+"'";
            statement=connection.createStatement();
            statement.executeUpdate(query);
            String query1="UPDATE rooms set status='empty' where name='"+room+"'";
            statement=connection.createStatement();
            statement.executeUpdate(query1);
            JOptionPane.showMessageDialog(null, "Delete data success!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Delete failed!");
        }
    }
    public void updateStatus(String name,String id, String room){
        try{
            if(checkAvailable(room)){
                String query="UPDATE renter set status='Paid' where id='"+id+"'";
                statement=connection.createStatement();
                statement.executeUpdate(query);
                String query1="UPDATE rooms set status='"+name+"' where name='"+room+"'";
                statement=connection.createStatement();
                statement.executeUpdate(query1);
                JOptionPane.showMessageDialog(null, "Update status success!");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Update status failed!");
        }
    }
    public boolean checkAvailable(String room){
        try{
            String query="SELECT status from rooms where name='"+room+"'";
            statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            while(rs.next()){
                if(rs.getString("status").equals("empty")){
                    return true;
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Checking failed!");
        }
        JOptionPane.showMessageDialog(null,"Room is occupied! Please wait until the room is available.");
        return false;
    }
    public void editRent(String name, String id, String contact, int duration,String room, String oldID){
        try{
            String query="UPDATE renter set name='"+name+"', id='"+id+"', contact='"+contact+"', duration='"+duration+"', bill='"+getPrice(room,duration)+"' where id='"+oldID+"'";
            statement=connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Edit rent success!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Edit rent failed!");
        }
    }
}
