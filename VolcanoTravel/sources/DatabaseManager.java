import java.awt.event.ActionEvent;
import java.io.*;
import java.sql.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;

public class DatabaseManager extends javax.swing.JFrame {
  

	private static final long serialVersionUID = 1L;
	private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;

    public DatabaseManager(Connection con,int id,long start) {
        CreateFrame(con,id,start);
    }
                         
    private void CreateFrame(Connection con,int id,long start) {

        jLabel1 = new javax.swing.JLabel();
       
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("You are signed in as Database Manager");

        jLabel2.setText("Select action to take");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Import Database", "Export Database", "Delete Database", "Exit(Back to Login Page)" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt,con,id,start);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel2))
          
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addContainerGap(17, Short.MAX_VALUE))
        		);
        pack();
    }                   

	public static void save(PrintWriter writer, ResultSet rs, String dname) throws Exception{
		rs.beforeFirst();
        ResultSetMetaData meta = rs.getMetaData();
        int numberOfColumns = meta.getColumnCount();
        writer.println("NEW TABLE");
        writer.println(dname);
        String dataHeaders = "\"" + meta.getColumnName(1) + "\"";
        for(int i = 2; i < numberOfColumns + 1; i++) {
        	dataHeaders += ",\"" + meta.getColumnName(i).replaceAll("\"","\\\"") + "\"";
        }
        writer.println(dataHeaders);
        while(rs.next()) {
        	String row;
            if(rs.getString(1) != null) row = "\"" + rs.getString(1).replaceAll("\"","\\\"") + "\"";
            else row = "null";
            for(int i = 2; i < numberOfColumns + 1; i++) {
            	if(rs.getString(i) != null) row += ",\"" + rs.getString(i).replaceAll("\"","\\\"") + "\"";
            	else row += ",null";
            }
        writer.println(row);
        }
	}
    
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt,Connection con,int id,long start) {
    	if(jComboBox2.getSelectedItem().equals("Import Database")){
    		Input in = new Input(7, "Import Wizard");
    		if(in.canceled == 0){
    			if(in.fn.equals("") || in.fn.endsWith(".dat") == false) JOptionPane.showMessageDialog(new JFrame(), "Please give a valid file name!", "Warning!", JOptionPane.WARNING_MESSAGE);
    			else{
    					Statement stmt = null;
    					try {
							stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						} catch (SQLException e1) {System.out.println(e1.getMessage());}
    				
    					Scanner scan = null;
						try {
							scan = new Scanner(new File(in.fn));
						} catch (FileNotFoundException e1) {System.out.println(e1.getMessage());}
    					String table = "";
    					String c[] = null;
    					while(scan.hasNextLine()){
    						String l = scan.nextLine();
    						if(l.equals("") == false){
    							if(l.equals("NEW TABLE")){
        							table = scan.nextLine();
        							c = scan.nextLine().split(",");
        							for(int i = 0; i < c.length; i++) c[i] = c[i].substring(1, c[i].length() - 1);
        						}else{
        							String q = "INSERT INTO [" + table + "](";
        							String a[] = l.split(",");
        							for(int i = 0; i < c.length; i++) if(a[i].equals("null") == false){
        								a[i] = a[i].substring(1, a[i].length() - 1);
        								String x = "";
        								for(int j = 0; j < a[i].length(); j++){
        									x += a[i].charAt(j);
        									if(a[i].charAt(j) == '\'') x += "'";
        								}
        								a[i] = x;
        								a[i] = "'" + a[i] + "'";
        							}
        	                        for(int i = 0; i < c.length; i++){
        	                        	q += "[" + c[i];
        	                        	if(i != c.length - 1) q += "], ";
        	                        	else q += "]) VALUES(";
        	                        }
        	                        for(int i = 0; i < c.length; i++){
        	                        	q += a[i];
        	                        	if(i != c.length - 1) q += ", ";
        	                        	else q += ")";
        	                        }
        	                        try {
										stmt.executeQuery(q);
									} catch (SQLException e1) {System.out.println(e1.getMessage());}
        						}
    						}
    					}
    			}
    		}
		}
    	else if(jComboBox2.getSelectedItem().equals("Export Database")){
			setVisible(false);
			Statement stmt;
			try {	
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				PrintWriter writer = new PrintWriter(new File("backup.dat"));
				
				String dname = "Airports";
			    ResultSet rs = stmt.executeQuery("exec  SelectNC2 '[" + dname + "]'");
			    save(writer, rs, dname);   
			    dname = "Book_Flight";
			    rs = stmt.executeQuery("exec  SelectNC2 '[" + dname + "]'");
			    save(writer, rs, dname);
			    dname = "Book_Room";
			    rs = stmt.executeQuery("exec  SelectNC2 '[" + dname + "]'");
			    save(writer, rs, dname);
			    dname = "Car";
			    rs = stmt.executeQuery("exec  SelectNC2 '[" + dname + "]'");
			    save(writer, rs, dname);
			    dname = "Car_Rentals";
			    rs = stmt.executeQuery("exec  SelectNC2 '[" + dname + "]'");
			    save(writer, rs, dname);
			    dname = "Customer";
			    rs = stmt.executeQuery("exec  SelectNC2 '[" + dname + "]'");
			    save(writer, rs, dname);
			    dname = "Employee_proj";
			    rs = stmt.executeQuery("exec  SelectNC2 '[" + dname + "]'");
			    save(writer, rs, dname);
			    dname = "Flights";
			    rs = stmt.executeQuery("exec  SelectNC2 '[" + dname + "]'");
			    save(writer, rs, dname);
			    dname = "HotelRooms";
			    rs = stmt.executeQuery("exec  SelectNC2 '[" + dname + "]'");
			    save(writer, rs, dname);
			    dname = "Hotels";
			    rs = stmt.executeQuery("exec  SelectNC2 '[" + dname + "]'");
			    save(writer, rs, dname);
			    dname = "ISLANDS";
			    rs = stmt.executeQuery("exec  SelectNC2 '[" + dname + "]'");
			    save(writer, rs, dname);
			    dname = "Rent_Car";
			    rs = stmt.executeQuery("exec  SelectNC2 '[" + dname + "]'");
			    save(writer, rs, dname);
			    dname = "Travel_Airlines";
			    rs = stmt.executeQuery("exec  SelectNC2 '[" + dname + "]'");
			    save(writer, rs, dname);
			  
			    
			    writer.close();
			} catch (Exception e) {
				e.printStackTrace();}
			ResultWindow w1 = new ResultWindow("Your data have been exported to file",con,null,this,null,id);
			w1.setVisible(true);
			
		}
    	else if(jComboBox2.getSelectedItem().equals("Delete Database")){
			Delete d = new Delete(con,this,id);
			d.setVisible(true);
		}
    	else if(jComboBox2.getSelectedItem().equals("Exit(Back to Login Page)")){
    		long end = System.currentTimeMillis();
    		double time = (end-start)/2.77777778*Math.pow(10, -7);
    		try{
    		Statement s = con.createStatement();
    		s.execute("declare @hours int SELECT @hours=hourss FROM Employee_proj WHERE Employee_proj.SSN="+id+" set @hours = @hours + "+time+" UPDATE Employee_proj SET hourss=@hours WHERE Employee_proj.SSN="+id);
    		}catch(SQLException e){
    			e.printStackTrace();
    		}
    		System.out.println("Logged in for "+time);
    		setVisible(false);
			LoginWindow w1 = new LoginWindow(con);
			w1.setVisible(true);
			
		}
    }  
    

                
}
