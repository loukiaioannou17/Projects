
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.sql.*;
import java.util.*;

public class PriceRoomReport extends javax.swing.JFrame {


	   private javax.swing.JButton jButton1;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;

	    private javax.swing.JTextField jTextField1;

	
    public PriceRoomReport(Connection con,Caller c,CompanyManager cm,int id) {
        createFrame(con,c,cm,id);
    }
                        
    private void createFrame(Connection con,Caller c,CompanyManager cm,int id) {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Show Full Rooms");

        jLabel2.setText("Give No of month");



        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt,con,c,cm,id);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            )
                        .addGap(106, 106, 106)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                           ))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel1)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    
                    )
                .addGap(34, 34, 34)
                .addComponent(jButton1)
                )
        );

        pack();
    }               


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt,Connection con,Caller c,CompanyManager cm,int id) {                                         
    	try{
    		Statement stmt = con.createStatement();
    		ResultSet s = stmt.executeQuery("DECLARE @counter int set @counter = (select AVG(Price) from HotelRooms,Book_Room where Confirmed=1) DECLARE @average int set @average = (Select AVG(DiscountPrice) from HotelRooms) SELECT MONTH('2019-"+jTextField1.getText()+"-01') as Month,Book_Room.RoomID,Type,Price,DiscountPrice,Score FROM (Book_Room INNER JOIN HotelRooms ON Book_Room.RoomID=HotelRooms.RoomID)  where (@counter>@average);");
    		String label = "Month\tBookRoomID\tType\tDiscountPrice\tScore\n";
    		while (s.next()){
    			label = label + s.getInt(1) + "\t";
    			label = label + s.getInt(2) + "\t";
    			label = label + s.getString(3) + "\t";
    			label = label + s.getInt(5) + "\t";
    			label = label + s.getInt(6) + "\t";
    			label = label + "\n";
    		}
    		
    		ResultWindow w = new ResultWindow(label,con,c,null,cm,id);
    		w.setVisible(true);
    		setVisible(false);
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	
    }                                        
              
}
