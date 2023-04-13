import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;

public class EmployeeForm extends javax.swing.JFrame {

	 private javax.swing.JComboBox jComboBox1;
	    private javax.swing.JLabel jLabel1;
	
    public EmployeeForm(Connection con,Caller c,CompanyManager cm,int id) {
        createFrame(con,c,cm,id);
    }
                      
    private void createFrame(Connection con,Caller c,CompanyManager cm,int id) {

        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Add new Employee", "Correct Employee", "Show Employee Details" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt,con,c,cm,id);
            }
        });

        jLabel1.setText("Manage Customers");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
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
    }// </editor-fold>                        

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt,Connection con,Caller c,CompanyManager cm,int id) {                                           
        if(jComboBox1.getSelectedItem().equals("Add new Employee")){
        	NewEmployee b = new NewEmployee(con,c,cm,id);
        	b.setVisible(true);
        	setVisible(false);
        }
        else if(jComboBox1.getSelectedItem().equals("Correct Employee")){
        	CorrectEmployee w = new CorrectEmployee(con,c,cm,id);
        	w.setVisible(true);
        	setVisible(false);
        }
        else if(jComboBox1.getSelectedItem().equals("Show Employee Details")){
        	ShowEmployee w = new ShowEmployee(con,c,cm,id);
        	w.setVisible(true);
        	setVisible(false);
        }
        	
    }                                          


               
}
