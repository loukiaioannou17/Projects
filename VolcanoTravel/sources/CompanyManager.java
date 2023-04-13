import java.awt.event.ActionEvent;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.awt.event.ActionListener;

public class CompanyManager extends javax.swing.JFrame {
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;

	public CompanyManager(Connection con, int id ,long start ) {
		createFrame(con, id,start );
	}

	private void createFrame(Connection con, int id ,long start ) {

		jLabel2 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();

		jLabel3 = new javax.swing.JLabel();
		jComboBox2 = new javax.swing.JComboBox();
		jLabel2.setText("Select action to take");

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("You are signed in as Company Manager");

		jLabel3.setText("Select Action to Take");

		jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Customer Form",
				"Search Island Description", "Search Hotel Description", "Search Customer", "Search Flight",
				"Search Flights With Intermediate Stops", "Search car or room", "Weighted Room Search", "Reserve", "Buy",
				"Customer Report", "Top Room Report", "Empty Room Report", "Full Room Report", "Price Room Report",
				"Employee Form", "Search Employee", "Calculate Payroll", "Cash Reservations", "Confirmed Turnover",
				"Hardworking Employees", "Best Flight Partners", "Flight Roster", "Best Sellers",
				"Exit (Back to Login Page)" }));
		jComboBox2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox2ActionPerformed(evt, con, id,start );
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(39, 39, 39).addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1)

								.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createSequentialGroup().addGap(91, 91, 91).addComponent(jLabel3)))
				.addContainerGap(78, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(23, 23, 23).addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
						.addComponent(jLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)

						.addContainerGap(10, Short.MAX_VALUE)));

		pack();
	}

	private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt, Connection con, int id,long start) {
		if (jComboBox2.getSelectedItem().equals("Search Flights With Intermediate Stops")){
		SearchFlightsWithStops s = new SearchFlightsWithStops(con,null,this,id);
		s.setVisible(true);
	}
	else if (jComboBox2.getSelectedItem().equals("Customer Form")) {
			CustomerForm c = new CustomerForm(con, null,this, id );
			c.setVisible(true);
		} else if (jComboBox2.getSelectedItem().equals("Search Island Description")) {
			SearchIsland s = new SearchIsland(con, null,this, id );
			s.setVisible(true);
		} else if (jComboBox2.getSelectedItem().equals("Search Hotel Description")) {
			SearchHotel h = new SearchHotel(con, null,this, id );
			h.setVisible(true);
		} else if (jComboBox2.getSelectedItem().equals("Search Customer")) {
			SearchCustomer c = new SearchCustomer(con, null,this, id );
			c.setVisible(true);
		} else if (jComboBox2.getSelectedItem().equals("Search Flight")) {
			SearchFlight f = new SearchFlight(con, null,this, id );
			f.setVisible(true);
		} else if (jComboBox2.getSelectedItem().equals("Search car or room")) {
			SearchCarRoom w1 = new SearchCarRoom(con, null,this, id );
			w1.setVisible(true);
			setVisible(false);

		} else if (jComboBox2.getSelectedItem().equals("Weighted Room Search")) {
			WeightedRoomSearch w1 = new WeightedRoomSearch(con, null,this, id );
			w1.setVisible(true);
			setVisible(false);
		} else if (jComboBox2.getSelectedItem().equals("Reserve")) {
			ReserveCarRoom w1 = new ReserveCarRoom(con, id, null,this );
			w1.setVisible(true);
			setVisible(false);

		} else if (jComboBox2.getSelectedItem().equals("Buy")) {
			BuyReservation b1 = new BuyReservation(con, null,this, id );
			b1.setVisible(true);
			setVisible(false);
		} else if (jComboBox2.getSelectedItem().equals("Customer Report")) {
			CustomerReport w = new CustomerReport(con, null,this, id );
			w.setVisible(true);
			setVisible(false);
		} else if (jComboBox2.getSelectedItem().equals("Top Room Report")) {
			String label = "RoomID \t Duration\n";
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(
						"select RoomID  from  (Select top 10 RoomID, COUNT(RoomID) AS Days from Book_Room GROUP by RoomID order by COUNT(RoomID) desc)a union all select RoomID from  (select top 5 RoomID, COUNT(RoomID) AS Days from Book_Room GROUP by RoomID order by COUNT(RoomID) asc)b");
				label = "RoomID\n";
				while (rs.next()) {
					label = label + rs.getInt(1) + " ";

					label = label + "\n";
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			ResultWindow w = new ResultWindow(label, con, null,null,this, id );
			w.setVisible(true);
			setVisible(false);
		} else if (jComboBox2.getSelectedItem().equals("Empty Room Report")) {
			EmptyRoomReport r1 = new EmptyRoomReport(con, null,this, id );
			r1.setVisible(true);
			setVisible(false);
		} else if (jComboBox2.getSelectedItem().equals("Full Room Report")) {
			FullRoomReport r2 = new FullRoomReport(con, null,this, id );
			r2.setVisible(true);
			setVisible(false);
		} else if (jComboBox2.getSelectedItem().equals("Price Room Report")) {
			PriceRoomReport r3 = new PriceRoomReport(con, null,this, id );
			r3.setVisible(true);
			setVisible(false);
		}

		else if (jComboBox2.getSelectedItem().equals("Employee Form")) {
			EmployeeForm c = new EmployeeForm(con, null, this, id );
			c.setVisible(true);
		} else if (jComboBox2.getSelectedItem().equals("Search Employee")) {
			SearchEmployee c = new SearchEmployee(con, null, this, id );
			c.setVisible(true);
		} else if (jComboBox2.getSelectedItem().equals("Best Flight Partners")) {
			try {
				CallableStatement s = con.prepareCall("{CALL BestFlightPartnersFixed()}");
				ResultSet rs = s.executeQuery();
				String result = "MaximumSeats\tAirline\n``";
				while (rs.next()) {
					result = result + rs.getInt(1) + "\t";
					result = result + rs.getString(2) + "\n";
				}
				ResultWindow w = new ResultWindow(result, con, null, null, this, id );
				w.setVisible(true);
				setVisible(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (jComboBox2.getSelectedItem().equals("Hardworking Employees")) {
			HardWokringEmployees w = new HardWokringEmployees(con, this, id );
			w.setVisible(true);
			setVisible(false);

		} else if (jComboBox2.getSelectedItem().equals("Confirmed Turnover")) {
			ConfirmedTurnover t = new ConfirmedTurnover(con, this, id );
			t.setVisible(true);
			setVisible(false);
		} else if (jComboBox2.getSelectedItem().equals("Flight Roster")) {
			FlightRoster f = new FlightRoster(con, this, id );
			f.setVisible(true);
			setVisible(false);
		} else if (jComboBox2.getSelectedItem().equals("Calculate Payroll")) {
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(
						"update Employee_proj set salaryy=hourss*SalarPerHour SELECT position,max(salaryy) as MAX ,min(salaryy) as MIN ,avg(salaryy) as AVERAGE from Employee_proj  GROUP BY Position");
				String label = "Position\tMax\tMin\tAverage\n";
				while (rs.next()) {
					label = label + rs.getString(1) + "\t";
					label = label + rs.getFloat(2) + "\t";
					label = label + rs.getFloat(3) + "\t";
					label = label + rs.getFloat(4) + "\n";
				}
				ResultWindow w = new ResultWindow("The salaries of the employers have been calculated\n" + label, con,
						null, null, this, id );
				w.setVisible(true);
				setVisible(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (jComboBox2.getSelectedItem().equals("Cash Reservations")) {
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(
						"declare @date smalldatetime select @date=CONVERT(char(10),GETDATE(),126) Select VisaNumber, flights.Price,customer.Name,customer.Surname From Book_Flight inner join Flights on Flights.FlightID = Book_Flight.FlightID inner join Customer on book_Flight.CustomerID = customer.CustomerID where Book_Flight.Paid='true'and Book_Flight.DatePaid=@date");
				String res = "Flight Reservations\nVisa\tPrice\tName\tSurname\n";
				while (rs.next()) {
					res = res + rs.getInt(1) + "\t";
					res = res + rs.getInt(2) + "\t";
					res = res + rs.getString(3) + "\t";
					res = res + rs.getString(4) + "\n";
				}
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery(
						"declare @date smalldatetime select @date=CONVERT(char(10),GETDATE(),126) select Book_Room.totalPrice ,customer.Name,customer.Surname from book_Room inner join HotelRooms on HotelRooms.RoomID =Book_Room.RoomID inner join customer on book_room.CustomerID=customer.CustomerID where book_Room.paid='true' and book_room.DatePaid=@date");
				res = res + "\nRoom Reservations\nVisa\tPrice\tName\tSurname\n";
				while (rs2.next()) {

					res = res + rs.getInt(1) + "\t";
					res = res + rs.getString(2) + "\t";
					res = res + rs.getString(3) + "\n";
				}
				Statement stmt3 = con.createStatement();
				ResultSet rs3 = stmt3.executeQuery(
						"declare @date smalldatetime select @date=CONVERT(char(10),GETDATE(),126) select Rent_Car.totalPrice,Rent_Car.VisaNumber,customer.Name,customer.Surname from Car_Rentals inner join Rent_Car on Rent_Car.RentCarID=Car_Rentals.CarRentaID  inner join customer on Rent_car.CustomerID=customer.CustomerID where Rent_car.Paid='true' and Rent_Car.DatePaid=@date");
				res = res + "\nCar Reservations\nVisa\tPrice\tName\tSurname\n";
				while (rs3.next()) {
					res = res + rs.getInt(1) + "\t";
					res = res + rs.getInt(2) + "\t";
					res = res + rs.getString(3) + "\t";
					res = res + rs.getString(4) + "\n";
				}
				ResultWindow w = new ResultWindow(res, con, null, null, this, id );
				w.setVisible(true);
				setVisible(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (jComboBox2.getSelectedItem().equals("Best Sellers")) {
			BestSellers bs = new BestSellers(con, this, id );
			bs.setVisible(true);
			setVisible(false);
		} else if (jComboBox2.getSelectedItem().equals("Exit (Back to Login Page)")) {
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
