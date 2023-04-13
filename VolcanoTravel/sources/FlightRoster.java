import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;

public class FlightRoster extends javax.swing.JFrame {

	  private javax.swing.JButton jButton1;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JTextField jTextField1;
	    private javax.swing.JTextField jTextField2;
	
    public FlightRoster(Connection con,CompanyManager cm,int id) {
        initComponents(con,cm,id);
    }
                       
    private void initComponents(Connection con,CompanyManager cm,int id) {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Flight Roster");

        jLabel2.setText("Airline");

        jLabel3.setText("Date ");

        jButton1.setText("ΟΚ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt,con,cm,id);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                            .addComponent(jTextField2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jButton1))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }                     

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt,Connection con,CompanyManager cm,int id) {                                         
    	try{
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT NumberofSeats , Arrival , Departure , ArrivalTime , DepartureTime , Price,flights.FlightID,BOOK_FLIGHT.BookFlightID,Customer.Name from flights inner join  book_flight on Flights.FlightID=Book_Flight.BookFlightID inner join Customer on Book_Flight.CustomerID=Customer.CustomerID where Airline='"+jTextField1.getText()+"' and Book_Flight.dateStart='"+jTextField2.getText()+"'");
    		String result = "NoSeats\tArrival\tDeparture\tArrivalTime\tDepartureTime\tPrice\tFlightID\tBookFlightID\tName\n";
    		while (rs.next()){
    			result = result + rs.getInt(1) + "\t";
    			result = result + rs.getString(2) + "\t";
    			result = result + rs.getString(3) + "\t";
    			result = result + rs.getTime(4) + "\t";
    			result = result + rs.getTime(5) + "\t";
    			result = result + rs.getInt(6) + "\t";
    			result = result + rs.getInt(7) + "\t";
    			result = result + rs.getInt(8) + "\t";
    			result = result + rs.getString(9) +"\n";
    			
    		}
    		ResultWindow r = new ResultWindow(result,con,null,null,cm,id);
    		r.setVisible(true);
    		setVisible(false);
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    }                                                         
}
