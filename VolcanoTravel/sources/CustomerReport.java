import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.sql.*;
import java.util.*;
public class CustomerReport extends javax.swing.JFrame {


	   private javax.swing.JButton jButton1;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JTextField jTextField6;
	    private javax.swing.JTextField jTextField7;
	    private javax.swing.JTextField jTextField8;
	
    public CustomerReport(Connection con,Caller c,CompanyManager cm,int id ) {
        CreateFrame(con,c,cm,id  );
    }
                        
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void CreateFrame(Connection con,Caller c,CompanyManager cm,int id ) {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Customer Report");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt,con,c,cm,id  );
            }
        });

        jLabel1.setText("Start Date");

        jLabel2.setText("End Date");

        jLabel3.setText("Customer ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 31, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>             


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt,Connection con,Caller c,CompanyManager cm,int id ) {                                         
    	try{
    		Statement stmt = con.createStatement();
    		ResultSet s = stmt.executeQuery("SELECT Customer.CustomerID,Customer.Address,Customer.Email,Customer.Name,Customer.Surname,Rent_Car.RentCarID,Rent_Car.CheckIn,Rent_Car.CheckOut,Book_Flight.FlightID,Book_Room.RoomID,Book_Room.CheckIn,Book_Room.CheckOut FROM ((Customer INNER JOIN Rent_Car ON Customer.CustomerID = Rent_Car.CustomerID) INNER JOIN Book_Flight ON Customer.CustomerID = Book_Flight.CustomerID) INNER JOIN Book_Room ON Customer.CustomerID = Book_Room.CustomerID WHERE (Book_Room.CheckIn BETWEEN '"+jTextField7.getText()+"' AND '"+jTextField6.getText()+"') AND (Rent_car.CheckIn BETWEEN '"+jTextField7.getText()+"' AND '"+jTextField6.getText()+"') AND Customer.CustomerID = "+jTextField8.getText()+";");
    		String label = "CustomerID\tAddress\tEmail\tName\tSurname\tCarID\tCheckIn\tCheckOut\tFlightID\tRoomID\tCheckIn\tCheckOut\n";
    		while (s.next()){
    			label = label + s.getInt(1) + "\t";
    			label = label + s.getString(2) + "\t";
    			label = label + s.getString(3) + "\t";
    			label = label + s.getString(4) + "\t";
    			label = label + s.getString(5) + "\t";
    			label = label + s.getInt(6) + "\t";
    			label = label + s.getDate(7) + "\t";
    			label = label + s.getDate(8) + "\t";
    			label = label + s.getInt(9) + "\t";
    			label = label + s.getInt(10) + "\t";
    			label = label + s.getDate(11) + "\t";
    			label = label + s.getDate(12) + "\t";
    			label = label + "\n";
    		}
    		
    		ResultWindow w = new ResultWindow(label,con,c,null,cm,id  );
    		w.setVisible(true);
    		setVisible(false);
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	
    }                                        
              
}
