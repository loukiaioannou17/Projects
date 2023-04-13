import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;

public class CallerReserve extends javax.swing.JFrame {
	  
	private javax.swing.JComboBox jComboBox1;
	    private javax.swing.JLabel jLabel1;
    
	   public CallerReserve(Connection con,String option,int id,Caller c,CompanyManager cm ) {
        initComponents(con,option,id,c,cm );
    }
                         
    private void initComponents(Connection con,String option,int id,Caller c,CompanyManager cm ) {

        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Create New Reservation", "Correct Reservation", "Show Reservation", "Confirm Reservation", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jComboBox1ActionPerformed(evt,con,option,id,c,cm );
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });


        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Create New Reservation", "Correct Reservation", "Show Reservation", "Confirm Reservation" }));

        jLabel1.setText("Manage Reservations");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }                      

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt,Connection con,String option,int id,Caller c,CompanyManager cm ) throws SQLException {                                           
        if (jComboBox1.getSelectedItem().equals("Create New Reservation")){
        	Reservation r = new Reservation(con,option,id,c,cm);
        	r.setVisible(true);
        	setVisible(false);
   }
        else if (jComboBox1.getSelectedItem().equals("Show Reservation")){
        	ShowReservation w1 = new ShowReservation(con,option,c,cm,id);
        	w1.setVisible(true);
        	setVisible(false);
        
        }
        else if (jComboBox1.getSelectedItem().equals("Confirm Reservation")){
        	ConfirmReservation w1 = new ConfirmReservation(con,option,c,cm,id );
        	w1.setVisible(true);
        	setVisible(false);
        }
        else if (jComboBox1.getSelectedItem().equals("Correct Reservation")){
        	CorrectReservation w1 = new CorrectReservation(con,option,id,c,cm );
        	w1.setVisible(true);
        	setVisible(false);
        }
    	setVisible(false);
    }         
}
