package appointment;

import appointment.Tree;
import appointment.PatientForm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;

public class Appointment extends PatientForm {
	private static final long serialVersionUID = 1L;
	static int newID = 1;

	public static void addNode() {

		LinkedList<PatientObject> list = new LinkedList<PatientObject>();
		Tree a = new Tree();

		String newName;
		String newSurname;
		try {
			// asks for fields to create the patient entry
			newName = txtFirstName.getText();
			newSurname = txtLastName.getText();
			String newDoctor;
			String newHour;
			String newGender = null;
			newDoctor = cmbDoctor.getSelectedItem().toString();
			newHour = cmbHour.getSelectedItem().toString();
			Date date = dateChooserAppointmentDate.getDate();
			String newDate = DateFormat.getDateInstance().format(date);

			if (rbMale.isSelected())
				newGender = "male";
			if (rbFemale.isSelected())
				newGender = "female";
			PatientObject b = new PatientObject(newID, newName, newSurname, newGender, newDoctor, newDate, newHour);
			a.insertValue(newID);
			list.insertAtFront(b);

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
			String SQL = "INSERT INTO [dbo].[PatientTable] ([Name],[Surname],[Doctor],[Gender],[Date],[Hour]) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement prepstmt = con.prepareStatement(SQL);
			prepstmt.setString(1, newName);
			prepstmt.setString(2, newSurname);
			prepstmt.setString(3, newDoctor);
			prepstmt.setString(4, newGender);
			prepstmt.setString(5, newDate);
			prepstmt.setString(6, newHour);
			prepstmt.executeUpdate();
			con.close();
			newID++;

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		list.printAllRecords();
		System.out.println("Tree Node Number: " + a.toString());
	} // end addNode

	public static void orderById() {
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
				System.out.println(rs.getString("PatientId") + " / " + rs.getString("Name") + " / "
						+ rs.getString("Surname") + " / " + rs.getString("Doctor") + " / " + rs.getString("Gender")
						+ " / " + rs.getString("Date") + " / " + rs.getString("Hour"));
				PatientForm.listAppointments.add(rs.getString("PatientId") + " / " + rs.getString("Name") + " / "
						+ rs.getString("Surname") + " / " + rs.getString("Doctor") + " / " + rs.getString("Gender")
						+ " / " + rs.getString("Date") + " / " + rs.getString("Hour"));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end orderById
}
