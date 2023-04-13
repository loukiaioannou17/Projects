import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.sql.*;
import java.util.*;

public class ResultWindow extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JToggleButton jToggleButton1;
    
	public ResultWindow(String text,Connection con,Caller c,DatabaseManager m,CompanyManager cm,int id ) {
        CreateFrame(text,con,c,m,cm,id );
    }

	private void CreateFrame(String text,Connection con,Caller c,DatabaseManager m,CompanyManager cm,int id ) {


        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jToggleButton1 = new javax.swing.JToggleButton();

   

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextPane1.setText(text);
        jScrollPane1.setViewportView(jTextPane1);

        jToggleButton1.setSelected(true);
        jToggleButton1.setText("Back");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt,con,c,m,cm,id );
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(jToggleButton1)
                .addContainerGap(169, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jToggleButton1))
        );

        pack();
    }
	
    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt,Connection con,Caller c,DatabaseManager m,CompanyManager cm,int id ) {                                               
    	setVisible(false);
    	if (c!=null){
    		c.setVisible(true);
    		
    		setVisible(false);
    	}
    	if (m!=null){
    		m.setVisible(true);
    	
    		setVisible(false);
    	}
    	if (cm!=null){
    	cm.setVisible(true);
    		setVisible(false);
    	}
    	setVisible(false);
    
    } 
                 
}
