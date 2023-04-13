import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CorrectReservation extends javax.swing.JFrame {

	private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;

    public CorrectReservation(Connection con,String option,int id,Caller c,CompanyManager cm ) {
        initComponents(con,option,id,c,cm  );
    }
 
    private void initComponents(Connection con,String option,int id,Caller c,CompanyManager cm ) {

        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();

        jButton1.setText("jButton1");

        jLabel8.setText("CustomerID");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Correct Reservation");

        jLabel2.setText("ItemID");

        jLabel3.setText("Visa");

        jLabel4.setText("CheckIn");

        jLabel5.setText("CustomerID");

        jLabel6.setText("CheckOut");

        jButton2.setText("Confirm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt,con,option,id,c,cm  );
            }
        });

        jLabel9.setText("Date(flight)");

        jLabel10.setText("ReservationID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap())
        );

        pack();
    }

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt,Connection con,String option,int id,Caller c,CompanyManager cm ) {   
    	try {
    	  	FileWriter fstream = new FileWriter("logVolcano.txt",true);
        	  BufferedWriter out = new BufferedWriter(fstream);
        	  
			Date d = new Date();
    	if (option.equals("room")) {
    		out.append("Time = "+d.toString()+" Employee "+id+" corrected a room reservation\n");
    		
    		CallableStatement stm = con.prepareCall("{CALL ChangeRoomReservation(?,?,?,?,?,?)}");
    		stm.setInt( 1,Integer.parseInt(jTextField7.getText()));
    		stm.setInt( 4,Integer.parseInt(jTextField4.getText()));
    		String fromDate1 = jTextField3.getText();
    		if (jTextField3.getText().equals("")) stm.setDate(2, null);
    		else{
    		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dtt1 = df1.parse(fromDate1);
			java.sql.Date ds1 = new java.sql.Date(dtt1.getTime());
			stm.setDate(2,ds1 );
    		}
			String fromDate2 = jTextField2.getText();
			if (jTextField2.getText().equals("")) stm.setDate(3, null);
    		else{
    		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dtt2 = df2.parse(fromDate2);
			java.sql.Date ds2 = new java.sql.Date(dtt2.getTime());
    		
    		stm.setDate(3,ds2 );}
    		stm.setInt(5,Integer.parseInt(jTextField5.getText()));
    		stm.setInt(6,id);
    		stm.execute();
    	
    	}
    	else if(option.equals("car")) {
    		out.append("Time = "+d.toString()+" Employee "+id+" corrected a car reservation\n");
    		
    		CallableStatement stm = con.prepareCall("{CALL ChangeCarReservation(?,?,?,?,?,?)}");
    		
    		stm.setInt( 1,Integer.parseInt(jTextField7.getText()));
    		stm.setInt( 4,Integer.parseInt(jTextField4.getText()));
    		
    		String fromDate1 = jTextField3.getText();
    		if (jTextField3.getText().equals("")) stm.setDate(2, null);
    		else{
    		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dtt1 = df1.parse(fromDate1);
			java.sql.Date ds1 = new java.sql.Date(dtt1.getTime());
			stm.setDate(2,ds1 );
			}
			
			String fromDate2 = jTextField2.getText();
			if (jTextField2.getText().equals("")) stm.setDate(3, null);
    		else{
    		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dtt2 = df2.parse(fromDate2);
			java.sql.Date ds2 = new java.sql.Date(dtt2.getTime());
    		
 
    		stm.setDate(3,ds2 );}
    
    		
    		stm.setInt(5,Integer.parseInt(jTextField5.getText()));
    		stm.setInt(6,Integer.parseInt(jTextField1.getText()));

    		stm.execute();
    
    	
    	
    	}
    	else if (option.equals("flight")){
 
    		out.append("Time = "+d.toString()+" Employee "+id+" corrected a flight reservation\n");
    			CallableStatement stm = con.prepareCall("{CALL ChangeFlightReservation(?,?,?,?,?)}");
    			stm.setInt(1, Integer.parseInt(jTextField7.getText()));
    			stm.setInt( 2,Integer.parseInt(jTextField5.getText()));
    			stm.setInt( 3,Integer.parseInt(jTextField4.getText()));
    			stm.setInt(4,id);
    			stm.setInt(5,Integer.parseInt(jTextField1.getText()));
    			
    			String fromDate = jTextField6.getText();
    			if (jTextField6.getText().equals("")) stm.setDate(6, null);
        		else{
    			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    			java.util.Date dtt = df.parse(fromDate);
    			java.sql.Date ds = new java.sql.Date(dtt.getTime());
    			stm.setDate(6, ds);}
    			
    			stm.execute();
    	
        	
    	}
   	 ResultWindow w = new ResultWindow("Your reservation has been corrected",con,c,null,cm,id  );
	    w.setVisible(true);
	    setVisible(false);
	    out.close();
	    
    		}catch (SQLException e) {
                e.printStackTrace();
    		}catch (Exception d){
    			d.printStackTrace();
    		}
    	
    	}
   
    }        
                  
