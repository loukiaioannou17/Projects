
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;

public class SearchEmployee extends javax.swing.JFrame {

	   private javax.swing.JButton jButton1;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JTextField jTextField1;
	    private javax.swing.JTextField jTextField2;
	    private javax.swing.JToggleButton jToggleButton1;
	    private javax.swing.JToggleButton jToggleButton2;
    public SearchEmployee(Connection con,Caller c,CompanyManager cm,int id) {
        initComponents(con,c,cm,id);
    }

                          
    private void initComponents(Connection con,Caller c,CompanyManager cm,int id) {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Search Employee");

        jLabel2.setText("ID");

        jLabel3.setText("Name");

        jButton1.setText("ID");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt,con,c,cm,id);
            }
        });

        jToggleButton1.setText("Name");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt,con,c,cm,id);
            }
        });

        jToggleButton2.setText("Both");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt,con,c,cm,id);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jToggleButton1)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jToggleButton2)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jToggleButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jToggleButton2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        
//id
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt,Connection con,Caller c,CompanyManager cm ,int id) {                                         
        // TODO add your handling code here:
    	try{
    	Statement stmt = con.createStatement();
    	ResultSet s = stmt.executeQuery("SELECT E.Username,E.Email,E.Phone_Number,E.Name,E.Surname FROM lioann02.Employee_proj E WHERE (E.SSN="+jTextField1.getText()+")");
    	String label = " ";
		while (s.next()){
		
			
			label = label+ s.getString(4) + " ";
			label = label+ s.getString(5) + " ";
			label = label+ s.getString(1) + " ";
			label = label+ s.getString(2) + " ";
			label = label+ s.getInt(3) + " ";
			
		}
		ResultWindow w2 = new ResultWindow(label,con,c,null,cm,id);
		w2.setVisible(true);
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	setVisible(false);
    }                                        
//name
    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt,Connection con,Caller c,CompanyManager cm ,int id) {                                               
        
// TODO add your handling code here:
try{
Statement stmt = con.createStatement();
ResultSet s = stmt.executeQuery("SELECT E.Address,E.Email,E.Phone_Number,E.Surname FROM lioann02.Employee_proj E WHERE (E.Name='"+jTextField2.getText()+"')");
String label = " ";
while (s.next()){


label = label+ s.getString(4) + " ";
label = label+ s.getString(1) + " ";
label = label+ s.getString(2) + " ";
label = label+ s.getInt(3) + " ";

}
ResultWindow w2 = new ResultWindow(label,con,c,null,cm,id);
w2.setVisible(true);
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
setVisible(false);

    }                                              
//both
    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt,Connection con,Caller c,CompanyManager cm ,int id) {                                               
        
// TODO add your handling code here:
try{
Statement stmt = con.createStatement();
ResultSet s = stmt.executeQuery("SELECT E.Address,E.Email,E.Phone_Number,E.Surname FROM Employee_proj E WHERE (E.SSN="+jTextField1.getText()+" AND E.Name='"+jTextField2.getText()+"')");
String label = " ";
while (s.next()){


label = label+ s.getString(4) + " ";
label = label+ s.getString(1) + " ";
label = label+ s.getString(2) + " ";
label = label+ s.getInt(3) + " ";

}
ResultWindow w2 = new ResultWindow(label,con,c,null,cm,id);
w2.setVisible(true);
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
setVisible(false);

    }                                              

                 
}
