package appointment;

public class PatientObject implements Patient {

	private int patientID;
	private String name, surname, gender,doctor,hour,date;
	public PatientObject(int id, String n, String s, String g, String d,String da, String h) 
	    {
	        patientID = id;
	        name = n;
	        surname = s;
	        gender = g;
	        doctor = d;
	        date = da;
	        hour = h;
	    }
	
	public PatientObject() {

	}

	public PatientObject(int id, String n, String s) {
		patientID = id;
		name = n;
		surname = s;
	}

	@Override
	public int getID() {
		return patientID;
	}

	@Override
	public void printID() {
		System.out.println("Patient Number: " + patientID + "\n    " + name + "\n    " + surname + "\n    " + gender + "\n    " + doctor + "\n    " + date + "\n    " + hour);

	}

}
