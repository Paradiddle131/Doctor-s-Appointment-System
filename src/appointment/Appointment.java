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

	public static void main(String[] args)
	{

		int input = 0;
		LinkedList<?> list = new LinkedList<PatientObject>();

		
	} 

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

			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		list.printAllRecords();
		System.out.println(a.toString());
	} // end addNode

	
	public static void orderById() {
		LinkedList<?> list = new LinkedList<PatientObject>();
		Tree a = new Tree();
		DefaultListModel listModel = new DefaultListModel();
		
		try {

			String connectionUrl = "jdbc:sqlserver://localhost:1433;"
					+ "databaseName=PatientDB;integratedSecurity=true;";

		
			Connection con = null;

			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);

			Statement stmt = con.createStatement();

			ResultSet rs;

			rs = stmt.executeQuery("SELECT * FROM [dbo].[PatientTable] ORDER BY PatientId");

			while (rs.next()) {
				System.out.println(rs.getString("PatientId") + " / " + rs.getString("Name") + " / " + rs.getString("Surname"));

				PatientForm.listAppointments.add(rs.getString("PatientId") + " / " + rs.getString("Name") + " / " + rs.getString("Surname"));

			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(a.toString());
	} // end orderById
	
}
