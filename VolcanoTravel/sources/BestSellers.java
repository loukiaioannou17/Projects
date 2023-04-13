import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;

public class BestSellers extends javax.swing.JFrame {

    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
	
    public BestSellers(Connection con,CompanyManager cm,int id ) {
        initComponents(con,cm,id  );
    }
                         
    private void initComponents(Connection con,CompanyManager cm,int id ) {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Best Seller");

        jLabel2.setText("Month");

        jLabel3.setText("Top x");

        jButton1.setText("ΟΚ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt,con,cm,id  );
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
                                          

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt,Connection con,CompanyManager cm,int id ) {                                         
    	try {
			CallableStatement c = con.prepareCall("{CALL BestSellers(?,?)}");
    		c.setInt(1, Integer.parseInt(jTextField2.getText()));
    		c.setString(2, jTextField1.getText());
    		boolean flag = c.execute();
    		String result = "";
    		int count=0;
    		while (flag){
    			count++;
    			ResultSet rs = c.getResultSet();
    			if (count == 1){
    				
    				result = result + "TotalCarSearches\tCarID\tType\tPrice\tDiscount\n";
    				while (rs.next()){
    					result = result +rs.getInt(1)+ "\t";
    					result = result +rs.getInt(2)+ "\t";
    					result = result +rs.getString(3)+ "\t";
    					result = result +rs.getInt(4)+ "\t";
    					result = result +rs.getInt(5)+ "\n";}
    				}
    			if (count == 2){
    				
        				result = result + "TotalCarΤypesSold\tCarID\tType\tPrice\tDiscount\n";
        				while (rs.next()){
        					result = result +rs.getInt(1)+ "\t";
        					result = result +rs.getInt(2)+ "\t";
        					result = result +rs.getString(3)+ "\t";
        					result = result +rs.getInt(4)+ "\t";
        					result = result +rs.getInt(5)+ "\n";}
    			}
        		if (count == 3){
        			
        			result = result + "TotalFlightSearches\tFlightID\tNoSeats\tArrival\tDeparture\tPrice\tDiscount\n";
        			while (rs.next()){
    					result = result +rs.getInt(1)+ "\t";
    					result = result +rs.getInt(2)+ "\t";
    					result = result +rs.getInt(3)+ "\t";
    					result = result +rs.getString(4)+ "\t";
    					result = result +rs.getString(5)+ "\t";
    					result = result +rs.getInt(6)+ "\t";
    					result = result +rs.getInt(7)+ "\n";}
			}
        			if (count == 4){
        				
            			result = result + "TotalFlightSold\tFlightID\tNoSeats\tArrival\tDeparture\tPrice\tDiscount\n";
            			while (rs.next()){
        					result = result +rs.getInt(1)+ "\t";
        					result = result +rs.getInt(2)+ "\t";
        					result = result +rs.getInt(3)+ "\t";
        					result = result +rs.getString(4)+ "\t";
        					result = result +rs.getString(5)+ "\t";
        					result = result +rs.getInt(6)+ "\t";
        					result = result +rs.getInt(7)+ "\n";}
    			}
            			if (count == 5){
            				
            				result = result + "TotalRoomSearched\tRoomID\tType\tPrice\tDiscount\tScore\n";
            				while (rs.next()){
            					result = result +rs.getInt(1)+ "\t";
            					result = result +rs.getInt(2)+ "\t";
            					result = result +rs.getString(3)+ "\t";
            					result = result +rs.getInt(4)+ "\t";
            					result = result +rs.getInt(5)+ "\t";
            					result = result +rs.getInt(6)+ "\n";}
            					
            				}
            				if (count == 6){
            					
                				result = result + "TotalRoomSold\tRoomID\tType\tPrice\tDiscount\tScore\n";
                				while (rs.next()){
                					result = result +rs.getInt(1)+ "\t";
                					result = result +rs.getInt(2)+ "\t";
                					result = result +rs.getString(3)+ "\t";
                					result = result +rs.getInt(4)+ "\t";
                					result = result +rs.getInt(5)+ "\t";
                					result = result +rs.getInt(6)+ "\n";}
                				}
        			
        		
    			result = result + "\n";
    			flag = c.getMoreResults();
    			}
    	
    		ResultWindow r = new ResultWindow(result,con,null,null,cm,id  );
    		r.setVisible(true);
    		setVisible(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }       

                 
}
