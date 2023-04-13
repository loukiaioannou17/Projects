import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.sql.*;
import java.util.*;

public class ReserveCarRoom extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JButton jButton1;
    
	 public ReserveCarRoom(Connection con,int id,Caller c,CompanyManager cm ) {
        createFrame(con,id,c,cm);
    }

	    private void createFrame(Connection con,int id,Caller c,CompanyManager cm) {

	        jButton1 = new javax.swing.JButton();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jTextField1 = new javax.swing.JTextField();

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	        jButton1.setText("Confirm");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton1ActionPerformed(evt,con,id,c,cm);
	            }
	        });

	        jLabel1.setText("Reserve Room,Car,Flight");

	        jLabel2.setText("Reserve type");
	        jLabel2.setToolTipText("Enter your option:");

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

	    private void  jButton1ActionPerformed(java.awt.event.ActionEvent evt,Connection con,int id,Caller c,CompanyManager cm) {                                         	
	    	if (jTextField1.getText().equals("room")){
	    		CallerReserve w2 = new CallerReserve(con,"room",id,c,cm);
	    		w2.setVisible(true);
	    	}
	    	else if (jTextField1.getText().equals("car")){
	    		CallerReserve w2 = new CallerReserve(con,"car",id,c,cm);
	    		w2.setVisible(true);
	    	}
	    	else if (jTextField1.getText().equals("flight")){
	    		CallerReserve w2 = new CallerReserve(con,"flight",id,c,cm);
	    		w2.setVisible(true);
	    		}
	    	setVisible(false);
	    }       
	    
	    
                  
 


                 
}
