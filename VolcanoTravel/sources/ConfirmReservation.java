import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfirmReservation extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JButton jButton1;
    
	 public ConfirmReservation(Connection con,String option,Caller c,CompanyManager cm ,int id  ) {
        createFrame(con,option,c,cm,id );
    }

	    private void createFrame(Connection con,String option,Caller c,CompanyManager cm ,int id  ) {

	        jButton1 = new javax.swing.JButton();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jTextField1 = new javax.swing.JTextField();

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	        jButton1.setText("Confirm");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton1ActionPerformed(evt,con,option,c,cm,id );
	            }
	        });

	        jLabel1.setText("Your reservation");

	        jLabel2.setText("Reservation ID");
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

	    private void  jButton1ActionPerformed(java.awt.event.ActionEvent evt,Connection con,String option,Caller c,CompanyManager cm ,int id  ) {                                         
        	try{
               	Statement stm2= con.createStatement();
               	FileWriter fstream = new FileWriter("logVolcano.txt",true);
          	  BufferedWriter out = new BufferedWriter(fstream);
          	  
               	Date d = new Date();
               	if (option.equals("room")){
               		out.append("Time = "+d.toString()+"Employee = " + id +"Confirmed Room Reservation "+jTextField1.getText()+"\n");
               		stm2.execute("UPDATE Book_Room SET Confirmed=1 WHERE Book_Room.BookRoomID="+jTextField1.getText());}
               	if (option.equals("car")){
               		out.append("Time = "+d.toString()+"Employee = " + id +"Confirmed Car Reservation "+jTextField1.getText()+"\n");
               		stm2.execute("UPDATE Rent_Car SET Confirmed=1 WHERE Rent_Car.RentCarID="+jTextField1.getText());}
               	if (option.equals("flight")){
               		out.append("Time = "+d.toString()+"Employee = " + id +"Confirmed Room Reservation "+jTextField1.getText()+"\n");
               		stm2.execute("UPDATE Book_Flight SET Confirmed=1 WHERE Book_Flight.BookFlightID="+jTextField1.getText());}
                ResultWindow w1 = new ResultWindow("Your reservation has been confirmed",con,c,null,cm,id );
                w1.setVisible(true);
                setVisible(false);
                out.close();
                }catch (SQLException  | IOException e) {
                    e.printStackTrace();
                }
	    }               
}
