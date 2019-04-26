/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointment;

import java.util.Scanner;

import javax.swing.DefaultListModel;

import appointment.Tree;
import appointment.PatientForm;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Appointment extends PatientForm {
	static int newID = 1;

	public static void main(String[] args) {

		int input = 0;
		LinkedList<?> list = new LinkedList<PatientObject>();

		do {
			boolean validInput = false;
			// user is prompted until they give a valid prompt
			while (!validInput) {
				// prompt the user to select an option
				try {
					System.out.println(
							"Operations on List\n  1. Make Empty\n  2. Find ID\n  3. Insert At Front\n    4. Delete ID\n  5. Print All Records\n  6.Print All Record Order by ID");
					System.out.print("    Your choice: ");
					// validates that the input is within bounds of operations
					if (input < 1 || input > 7) {
						System.out.println("ERROR: Invalid operation. Please select an integer from 1-7.\n");
					} else {
						validInput = true;
					}
				} catch (Exception e) {
					System.out.println("ERROR: You did not select an integer. Please select an integer.\n");
				}
			}
			// input is now validated
			Scanner sc = new Scanner(System.in);

			// operations on list
			switch (input) {
			// empties list
			case 1:
				list.makeEmpty();
				System.out.println("    List emptied.\n");
				break;
			// asks user for ID input, and then searches for ID in list ********* burada
			// database den önce id ye göre bütün elemanları alıcaz ona göre
			// sıralayacagız case 6 da var
			case 2:
				System.out.print("    ID No: ");
				int ID;
				try {
					ID = sc.nextInt();
					if (list.findID(ID) == null) {
						System.out.println("    ID not found.\n");
					} else {
						list.findID(ID).printID();
						System.out.println(); // adds a new line for cleaner formatting
					}
				} catch (Exception e) {
					System.out.println("    This is not a valid ID number.\n");
				}
				sc.close();
				break;
			// prints the entire list of records. Added lines for cleaner formatting
			case 3:
				System.out.println();
				System.out.println("-------------------------------");
				list.printAllRecords();
				System.out.println("-------------------------------");
				System.out.println("Printed all records.\n");
				break;
			} // end switch-case
		} // end do-while
		while (input != 7); // continues until the user selects 7, which is done
	} // end main

	public static void addNode() {
		LinkedList<PatientObject> list = new LinkedList<PatientObject>();
		Tree a = new Tree();

		String newName;
		String newSurname;
		try {
			// asks for fields to create the patient entry
			newName = txtFirstName.getText();
			newSurname = txtLastName.getText();
			PatientObject m = new PatientObject(newID, newName, newSurname);
			a.insertValue(newID);
			list.insertAtFront(m);

			// Create a variable for the connection string.
			String connectionUrl = "jdbc:sqlserver://localhost:1433;"
					+ "databaseName=PatientDB;integratedSecurity=true;";

			// Declare the JDSC objects.
			Connection con = null;

			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);

			// Create and execute an SQL statement that returns some data.
			String SQL = "INSERT INTO [dbo].[PatientTable] ([Name],[Surname]) VALUES(?, ?)";
			PreparedStatement prepstmt = con.prepareStatement(SQL);
			prepstmt.setString(1, newName);
			prepstmt.setString(2, newSurname);
			prepstmt.executeUpdate();
			con.close();
			newID++;

			// Iterate through the data in the result set and display it.
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		list.printAllRecords();
		System.out.println(a.toString());
	} // end addNode

	public static void deleteNode() {
		LinkedList<?> list = new LinkedList<PatientObject>();
		Tree a = new Tree();

		System.out.print("    Enter ID to be deleted: ");
		int deleteID = 0;
		try {
			if (list.findID(deleteID) == null) {
				System.out.println("    Item does not exist in the list.\n");
			} else {
				list.delete(deleteID).printID();
				System.out.println("    Item has been deleted.\n");
			}
		} catch (Exception e) {
			System.out.println("    Please enter a valid numerical ID for the object.\n");
		}
		try {
			Class<?> c = Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			Driver drv = (Driver) c.newInstance();

			Connection conn = drv.connect("jdbc:sqlserver://127.0.0.1; databaseName=pubs; user=sa; password=123", null);

			String query = "DELETE FROM [dbo].[PatientTable] WHERE PatientId = " + newID;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, deleteID);
			preparedStmt.execute();

			conn.close();

		} catch (Exception e) {
		}
	} // end deleteNode

	public static void orderById() {
		LinkedList<?> list = new LinkedList<PatientObject>();
		Tree a = new Tree();
		DefaultListModel listModel = new DefaultListModel();
		
		try {
//			Class<?> c = Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//			Driver drv = (Driver) c.newInstance();

//			Connection conn = drv.connect("jdbc:sqlserver://127.0.0.1; databaseName=pubs; user=sa; password=123", null);
			String connectionUrl = "jdbc:sqlserver://localhost:1433;"
					+ "databaseName=PatientDB;integratedSecurity=true;";

			// Declare the JDSC objects.
			Connection con = null;

			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);

			Statement stmt = con.createStatement();

			ResultSet rs;

			rs = stmt.executeQuery("SELECT * FROM [dbo].[PatientTable] ORDER BY PatientId");

			while (rs.next()) {
				System.out.println(rs.getString("PatientId") + " / " + rs.getString("Name") + " / " + rs.getString("Surname"));
//				listModel.addElement(rs.getString("PatientId") + " / " + rs.getString("Name") + " / " + rs.getString("Surname");
				PatientForm.listAppointments.add(rs.getString("PatientId") + " / " + rs.getString("Name") + " / " + rs.getString("Surname"));
//				PatientForm.this.listApp.
			}
//			PatientForm.this.listApp.setModel(listModel);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(a.toString());
	} // end orderById
	
}
