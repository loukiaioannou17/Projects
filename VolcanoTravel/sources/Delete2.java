

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Delete2 extends javax.swing.JFrame {

    /**
     * Creates new form Delete
     */
    public Delete2(Connection con,DatabaseManager dm,int id) {
        initComponents(con,dm,id);
    }
                         
    private void initComponents(Connection con,DatabaseManager dm,int id) {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Are you sure [Y/N]");

     
        jToggleButton1.setText("OK");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt,con,dm,id);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jToggleButton1))
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton1)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }                                          

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt,Connection con,DatabaseManager dm,int id) {                                               
        if (jTextField1.getText().equals("Y")){
        	try{
        		Statement stmt = con.createStatement();
        		stmt.execute("ALTER TABLE Airports NOCHECK CONSTRAINT ALL ALTER TABLE Book_Flight NOCHECK CONSTRAINT ALL ALTER TABLE Book_Room NOCHECK CONSTRAINT ALL ALTER TABLE Car NOCHECK CONSTRAINT ALL ALTER TABLE Car_Rentals NOCHECK CONSTRAINT ALL ALTER TABLE Customer NOCHECK CONSTRAINT ALL ALTER TABLE Employee_proj NOCHECK CONSTRAINT ALL ALTER TABLE Flights  NOCHECK CONSTRAINT ALL ALTER TABLE HotelRooms NOCHECK CONSTRAINT ALL ALTER TABLE Hotels NOCHECK CONSTRAINT ALL ALTER TABLE ISLANDS NOCHECK CONSTRAINT ALL ALTER TABLE Rent_Car NOCHECK CONSTRAINT ALL ALTER TABLE Travel_Airlines NOCHECK CONSTRAINT ALL delete from Airports delete from Book_Flight delete from Book_Room delete from Car delete from Car_Rentals delete from Customer delete from Employee_proj delete from Flights delete from HotelRooms delete from Hotels delete from ISLANDS delete from Rent_Car delete from Travel_Airlines");
        	}catch(SQLException e){
        		e.printStackTrace();
        	}
        	ResultWindow w2 = new ResultWindow("You lost a lot of work...",con,null,dm,null,id);
        	w2.setVisible(true);
        	
        }
        else {
        	ResultWindow w1 = new ResultWindow("You made the right choice!",con,null,dm,null,id);
        	w1.setVisible(true);
        }
        setVisible(false);
    }                                              


    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration                   
}


