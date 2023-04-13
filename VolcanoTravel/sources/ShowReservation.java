import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class ShowReservation extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JButton jButton1;
    
	 public ShowReservation(Connection con,String option,Caller c,CompanyManager cm,int id) {
        createFrame(con,option,c,cm,id);
    }

	    private void createFrame(Connection con,String option,Caller c,CompanyManager cm,int id) {

	        jButton1 = new javax.swing.JButton();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jTextField1 = new javax.swing.JTextField();

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	        jButton1.setText("Confirm");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton1ActionPerformed(evt,con,option,c,cm,id);
	            }
	        });

	        jLabel1.setText("Your reservation number");

	        jLabel2.setText("Insert your reservationID");
	        jLabel2.setToolTipText("");

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(80, 80, 80)
	                        .addComponent(jButton1))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(21, 21, 21)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addGap(30, 30, 30)
	                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))))
	                .addContainerGap(34, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addGap(24, 24, 24)
	                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addComponent(jButton1)
	                .addContainerGap())
	        );

	        pack();
	    }                       

	    private void  jButton1ActionPerformed(java.awt.event.ActionEvent evt,Connection con,String option,Caller c,CompanyManager cm,int id) {                                         

        	String text=jTextField1.getText();
        	String result=" ";
        	try{
            Statement stmt = con.createStatement();
            if (option.equals("room")){
            ResultSet rs = stmt.executeQuery("SELECT * FROM Book_Room WHERE Book_Room.BookRoomID="+text);
            result = "ID\tCheckIn\tCheckOut\tVisa\tConfirmed\tIsland\tHotel\tCustomer\tEmployee\tRoomID\tPaid\tDatePaid\tTotal\n";
            while (rs.next()) {
            	result = result + rs.getInt(1) + "\t";
            	result = result + rs.getDate(2) + "\t";
            	result = result + rs.getDate(9) + "\t";
            	result = result + rs.getInt(3) + "\t";
            	result = result + rs.getInt(4) + "\t";
            	result = result + rs.getInt(5) + "\t";
            	result = result + rs.getInt(6) + "\t";
            	result = result + rs.getInt(7) + "\t";
            	result = result + rs.getInt(8) + "\t";
            	result = result + rs.getInt(10) + "\t";
            	result = result + rs.getInt(11) + "\t";
            	result = result + rs.getDate(12) + "\t";
            	result = result + rs.getInt(13) + "\t";
            	}
            
            }
            if (option.equals("car")){

                ResultSet rs = stmt.executeQuery("SELECT * FROM Rent_Car WHERE Rent_Car.RentCarID="+text);
                result = "ID\tCheckIn\tCheckOut\tVisa\tConfirmed\tIsland\tCustomer\tEmployee\tRoomID\tPaid\tDatePaid\tTotal\n";
                while (rs.next()) {
                	result = result + rs.getInt(1) + "\t";
                	result = result + rs.getDate(2) + "\t";
                	result = result + rs.getDate(9) + "\t";
                	result = result + rs.getInt(3) + "\t";
                	result = result + rs.getInt(4) + "\t";
                	result = result + rs.getInt(5) + "\t";
                	result = result + rs.getInt(6) + "\t";
                	result = result + rs.getInt(7) + "\t";
                	result = result + rs.getInt(8) + "\t";
                	result = result + rs.getInt(10) + "\t";
                	result = result + rs.getDate(11) + "\t";
                	result = result + rs.getInt(12) + "\t";
             
                	}
                
                
               }
            if (option.equals("flight")){
                ResultSet rs = stmt.executeQuery("SELECT * FROM Book_Flight WHERE Book_Flight.BookFlightID="+text);
                result = "ID\tVisa\tConfirmed\tFlight\tCustomer\tEmployee\tPaid\tDate\tDatePaid\n";
                while (rs.next()) {
                	result = result + rs.getInt(1) + "\t";
                	result = result + rs.getInt(2) + "\t";
                	result = result + rs.getInt(3) + "\t";
                	result = result + rs.getInt(4) + "\t";
                	result = result + rs.getInt(5) + "\t";
                	result = result + rs.getInt(6) + "\t";
                	result = result + rs.getInt(7) + "\t";
                	result = result + rs.getDate(8) + "\t";
                	result = result + rs.getDate(9) + "\t";
                	
                }
             }
            ResultWindow r = new ResultWindow(result,con,c,null,cm,id);
           	r.setVisible(true);
            setVisible(false);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        
        
	    	   
	  
	    }       
	    

                  
 


                 
}
