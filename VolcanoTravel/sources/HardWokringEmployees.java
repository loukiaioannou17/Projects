import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;

public class HardWokringEmployees extends javax.swing.JFrame {

	  private javax.swing.JButton jButton1;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;

	    private javax.swing.JTextField jTextField1;

	
    public HardWokringEmployees(Connection con,CompanyManager cm,int id) {
        initComponents(con,cm,id);
    }
                       
    private void initComponents(Connection con,CompanyManager cm,int id) {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Hardworking Employees of the Month");

        jLabel2.setText("Month");

     

        jButton1.setText("ΟΚ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt,con,cm,id);
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
                            .addComponent(jLabel2))
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        ))
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
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }                     

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt,Connection con,CompanyManager cm,int id) {                                         
    	try{
    		CallableStatement s = con.prepareCall("{CALL HardworkingEmployees(?)}");
    		s.setString(1, jTextField1.getText());
    		boolean flag = s.execute();
    		String result = "";
    		while (flag){
    			ResultSet rs = s.getResultSet();
    			result = result + "Number\tEmployeeID\tName\tSurname\tEmail\tPosition\n";
    			while (rs.next()){
    				result = result +rs.getInt(1)+ "\t";
    				result = result +rs.getInt(2)+ "\t";
    				result = result +rs.getString(3) +"\t";
    				result = result +rs.getString(4) +"\t";
    				result = result +rs.getString(5) +"\t";
    				result = result +rs.getString(6) +"\n";
    			}
    			result = result + "\n";
    			flag = s.getMoreResults();
    			System.out.println("flag = "+flag + "\n");
    		}
    		ResultWindow r = new ResultWindow(result,con,null,null,cm,id);
    		r.setVisible(true);
    		setVisible(false);
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    }                                                         
}
