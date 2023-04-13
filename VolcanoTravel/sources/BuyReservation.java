
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuyReservation extends javax.swing.JFrame {


	    private javax.swing.JButton jButton1;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JTextField jTextField1;
	    private javax.swing.JTextField jTextField2;
	
    public BuyReservation(Connection con,Caller c,CompanyManager cm,int id   
) {
        createFrame(con,c,cm,id  );
    }
                        
    private void createFrame(Connection con,Caller c,CompanyManager cm,int id    
) {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Pay for your reservation");

        jLabel2.setText("Reservation Type");

        jLabel3.setText("ReservationID");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt,con,c,cm,id  );
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
                            .addComponent(jLabel3))
                        .addGap(106, 106, 106)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(jTextField2)))
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
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }               


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt,Connection con,Caller c,CompanyManager cm,int id   ) {                                         
    	try{
    		FileWriter fstream = new FileWriter("logVolcano.txt",true);
      	  BufferedWriter out = new BufferedWriter(fstream);
      	  Date d = new Date();
    		if (jTextField1.getText().equals("room")){
    			out.append("Time = "+d.toString() + " Employee " + id + "took money for room reservation "+jTextField2.getText());
    			Statement stmt = con.createStatement();
        		stmt.execute("UPDATE Book_Room SET Paid=1 WHERE Book_Room.BookRoomID="+jTextField2.getText());
        		stmt.execute("update Book_Room set totalPrice=datediff(day,checkIn,checkOut)*HotelRooms.DiscountPrice from book_Room inner join HotelRooms on HotelRooms.RoomID=book_room.RoomID");
        		ResultSet rs = stmt.executeQuery("Select totalPrice from Book_Room where BookRoomID="+jTextField2.getText());
        		String res = "Total price to be paid for this reservation = ";
        		while (rs.next()) res = res + rs.getInt(1);
        		ResultWindow w1 = new ResultWindow(res + "\nYour reservation has been paid!",con,c,null,cm,id  );
    			w1.setVisible(true);
    			setVisible(false);
    		}
    		else if (jTextField1.getText().equals("car")){
    			out.append("Time = "+d.toString() + " Employee " + id + "took money for car reservation "+jTextField2.getText());
    			Statement stmt = con.createStatement();
        		stmt.execute("UPDATE Rent_Car SET Paid=1 WHERE Rent_Car.RentCarID="+jTextField2.getText());
        		stmt.execute("update Rent_Car  set totalPrice=datediff(day,checkIn,checkOut)*Car.DiscountPrice from Rent_Car inner join Car on Car.CarID=Rent_Car.RentCarID");
        		ResultSet rs = stmt.executeQuery("Select totalPrice from Rent_Car where RentCarID="+jTextField2.getText());
        		String res = "Total price to be paid for this reservation = ";
        		while (rs.next()) res = res + rs.getInt(1);
        		ResultWindow w1 = new ResultWindow(res + "\nYour reservation has been paid!",con,c,null,cm,id  );
    			w1.setVisible(true);
    			setVisible(false);
    			
    		}
    		else if (jTextField1.getText().equals("flight")){
    			out.append("Time = "+d.toString() + " Employee " + id + "took money for flight reservation "+jTextField2.getText());
    			Statement stmt = con.createStatement();
        		stmt.execute("UPDATE Book_Flight SET Paid=1 WHERE Book_Flight.BookFlightID="+jTextField2.getText());	
        		ResultSet rs = stmt.executeQuery("SELECT Flights.DiscountPrice FROM Book_Flight INNER JOIN Flights ON Book_Flight.FlightID=Flights.FlightID WHERE Book_Flight.BookFlightID="+jTextField2.getText());
        		String res = "Total price to be paid for this reservation = ";
        		while (rs.next()) res = res + rs.getInt(1);
        		ResultWindow w1 = new ResultWindow(res + "\nYour reservation has been paid!",con,c,null,cm,id  );
    			w1.setVisible(true);
    			setVisible(false);
    		}
    	}catch(SQLException | IOException e){
    		e.printStackTrace();
    	}
    
    }                                        
              
}
