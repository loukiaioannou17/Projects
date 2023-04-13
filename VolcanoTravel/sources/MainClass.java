import java.io.*;
import java.sql.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.awt.event.ActionListener;
import javax.*;
import java.*;

public class MainClass {
	
	private static boolean dbDriverLoaded = false;
	private static Connection conn = null;
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	private Connection getDBConnection() {
		String dbConnString = "jdbc:sqlserver://mssql.cs.ucy.ac.cy;user=lioann02;password=B7aGaA5n;";

		if (!dbDriverLoaded)
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				dbDriverLoaded = true;
			} catch (ClassNotFoundException e) {
				System.out.println("Cannot load DB driver!");
				return null;
			}

		try {
			if (conn == null)
				conn = DriverManager.getConnection(dbConnString);
			else if (conn.isClosed())
				conn = DriverManager.getConnection(dbConnString);
		} catch (SQLException e) {
			System.out.print("Cannot connect to the DB!\nGot error: ");
			System.out.print(e.getErrorCode());
			System.out.print("\nSQL State: ");
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public static void main(String[] args){
		
		MainClass anObj = new MainClass();
        conn = anObj.getDBConnection();

        if (conn == null) 
            return;
        
        System.out.println("Connection with the SQL server is accuired ! \n\n");
     
		LoginWindow w1 = new LoginWindow(conn);
		w1.setVisible(true);

   
    	}

}
