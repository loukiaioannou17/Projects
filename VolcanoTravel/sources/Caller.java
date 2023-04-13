import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Caller extends javax.swing.JFrame {
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
	
    public Caller(Connection con,int id ,long start) { createFrame(con,id,start ); }
                         
    private void createFrame(Connection con,int id,long start) {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jComboBox2 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("You are signed in as a Caller !");

        jLabel2.setText("Select action to take");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Customer Form", "Search Island Description", "Search Hotel Description", "Search Customer", "Search Flight", "Search Flights With Intermediate Stops", "Search car or room", "Weighted Room Search", "Reserve", "Buy", "Customer Report", "Top Room Report", "Empty Room Report", "Full Room Report", "Price Room Report", "Exit (Back to Login Page)" }));
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
                        .addGap(103, 103, 103)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        pack();
    }                       

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt,Connection con,int id,long start) {
    	if (jComboBox2.getSelectedItem().equals("Customer Form")){
    		CustomerForm c = new CustomerForm(con,this,null,id );
    		c.setVisible(true);
    	}
    	else if (jComboBox2.getSelectedItem().equals("Search Flights With Intermediate Stops")){
    		SearchFlightsWithStops s = new SearchFlightsWithStops(con,this,null,id);
    		s.setVisible(true);
    	}
    	else if (jComboBox2.getSelectedItem().equals("Search Island Description")){
    		SearchIsland s = new SearchIsland(con,this,null,id  );
    		s.setVisible(true);
    	}
    	else if (jComboBox2.getSelectedItem().equals("Search Hotel Description")){
    		SearchHotel h = new SearchHotel(con,this,null,id  );
    		h.setVisible(true);
    	}
    	else if (jComboBox2.getSelectedItem().equals("Search Customer")){
    		SearchCustomer c = new SearchCustomer(con,this,null,id  );
    		c.setVisible(true);
    	}
    	else if (jComboBox2.getSelectedItem().equals("Search Flight")){
    		SearchFlight f =new SearchFlight(con,this,null,id  );
    		f.setVisible(true);
    	}
    	else if (jComboBox2.getSelectedItem().equals("Search car or room")){
    		SearchCarRoom w1 = new SearchCarRoom(con,this,null,id  );
    		w1.setVisible(true);
    		setVisible(false);
    		 
    	}
    	else if (jComboBox2.getSelectedItem().equals("Weighted Room Search")){
    		WeightedRoomSearch w1 = new WeightedRoomSearch(con,this,null,id  );
    		w1.setVisible(true);
    		setVisible(false);
    	}
    	else if (jComboBox2.getSelectedItem().equals("Reserve")){
    		ReserveCarRoom w1 = new ReserveCarRoom(con,id,this,null  );
    		w1.setVisible(true);
    		setVisible(false);
    		
    	}
    	else if (jComboBox2.getSelectedItem().equals("Buy")){
    		BuyReservation b1 = new BuyReservation(con,this,null,id );
    		b1.setVisible(true);
    		setVisible(false);
    	}
    	else if (jComboBox2.getSelectedItem().equals("Customer Report")){
    		CustomerReport w = new CustomerReport(con,this,null,id  );
    		w.setVisible(true);
    		setVisible(false);
    	}
    	else if (jComboBox2.getSelectedItem().equals("Top Room Report")){
    		String label = "RoomID \t Duration\n"; 
    		try{
    			Statement stmt = con.createStatement();
    			ResultSet rs = stmt.executeQuery("select RoomID  from  (Select top 10 RoomID, COUNT(RoomID) AS Days from Book_Room GROUP by RoomID order by COUNT(RoomID) desc)a union all select RoomID from  (select top 5 RoomID, COUNT(RoomID) AS Days from Book_Room GROUP by RoomID order by COUNT(RoomID) asc)b");
    			label = "RoomID\n";
    			while (rs.next()){
    				label = label + rs.getInt(1) + " ";
    		
    				label = label + "\n";
    			}
    			
    		}catch(SQLException e){
    			e.printStackTrace();
    		}
    		ResultWindow w = new ResultWindow(label,con,this,null,null,id );
    		w.setVisible(true);
    		setVisible(false);
    	}
    	else if (jComboBox2.getSelectedItem().equals("Empty Room Report")){
    		EmptyRoomReport r1 = new EmptyRoomReport(con,this,null,id  );
    		r1.setVisible(true);
    		setVisible(false);
    	}
    	else if (jComboBox2.getSelectedItem().equals("Full Room Report")){
    		FullRoomReport r2 = new FullRoomReport(con,this,null,id  );
    		r2.setVisible(true);
    		setVisible(false);
    	}
    	else if (jComboBox2.getSelectedItem().equals("Price Room Report")){
    		PriceRoomReport r3 = new PriceRoomReport(con,this,null,id  );
    		r3.setVisible(true);
    		setVisible(false);
    	}
    	else if(jComboBox2.getSelectedItem().equals("Exit (Back to Login Page)")){
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
