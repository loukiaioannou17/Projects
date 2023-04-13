import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;

public class ShowEmployee extends javax.swing.JFrame {
    
	private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    
    public ShowEmployee(Connection con,Caller c,CompanyManager cm,int id) {
        initComponents(con,c,cm,id);
    }
                          
    private void initComponents(Connection con,Caller c,CompanyManager cm,int id) {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Search Employee");

        jLabel2.setText("Give EmployeeID");

  

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
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2)
                        .addGap(46, 46, 46)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jButton1)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt,Connection con,Caller c,CompanyManager cm,int id) {                                         

    	String label="";
    	try{
    		Statement s = con.createStatement();
    		ResultSet rs = s.executeQuery("SELECT * FROM Employee_proj WHERE Employee_proj.SSN='"+jTextField1.getText()+"'");
    		label = label +"SSN\tName\tSurname\tAddress\tEmail\tPhoneNumber\tUsername\tSupervisor\tPosition\n";
    		while(rs.next()){
    			label = label +rs.getInt(1) +"\t";
    			label = label +rs.getString(2) +"\t";
    			label = label +rs.getString(3) +"\t";
    			label = label +rs.getString(4) +"\t";
    			label = label +rs.getString(6) +"\t";
    			label = label +rs.getString(7) +"\t";
    			label = label +rs.getInt(8) +"\t";
    			label = label +rs.getString(9) +"\t";
    			label = label +rs.getInt(10) +"\t";
    			
    		}
    		ResultWindow w1 = new ResultWindow(label,con,c,null,cm,id);
    		w1.setVisible(true);
    		setVisible(false);
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    
    }                                                        
}
