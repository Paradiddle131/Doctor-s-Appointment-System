package appointment;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.LineNumberInputStream;
import java.util.LinkedList;
import java.awt.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PatientForm extends JFrame {
	public static JTextField txtFirstName;
	public static JTextField txtLastName;
	public static List listAppointments;
	public static JComboBox cmbDoctor;
    public static JComboBox cmbHour; 
	private final ButtonGroup buttonGroup = new ButtonGroup();
    public static JDateChooser dateChooserAppointmentDate; 
    public static JRadioButton rbMale, rbFemale;

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientForm frame = new PatientForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the frame.
	public PatientForm() {
		setTitle("Patient Appointment Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 402, 553);
		getContentPane().setLayout(null);

		JLabel lblFirstName = new JLabel("Last Name: ");
		lblFirstName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblFirstName.setBounds(39, 84, 95, 33);
		getContentPane().add(lblFirstName);

		JLabel lblFirstName_1 = new JLabel("First Name: ");
		lblFirstName_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblFirstName_1.setBounds(39, 48, 95, 33);
		getContentPane().add(lblFirstName_1);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(174, 56, 157, 20);
		getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(174, 92, 157, 20);
		getContentPane().add(txtLastName);

		JLabel lblDoctor = new JLabel("Doctor: ");
		lblDoctor.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblDoctor.setBounds(39, 178, 95, 33);
		getContentPane().add(lblDoctor);

		cmbDoctor = new JComboBox<PatientObject>();
		cmbDoctor.setModel(
				new DefaultComboBoxModel(new String[] { "Dr. Ivan Rebick", "Dr. Joshua Rusko", "Dr. Waylon Urps" }));
		cmbDoctor.setBounds(174, 186, 157, 20);
		getContentPane().add(cmbDoctor);

		dateChooserAppointmentDate = new JDateChooser();
		dateChooserAppointmentDate.setBounds(174, 222, 157, 20);
		getContentPane().add(dateChooserAppointmentDate);

		JLabel lblAppointmentDate = new JLabel("Appointment Date: ");
		lblAppointmentDate.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblAppointmentDate.setBounds(39, 222, 133, 20);
		getContentPane().add(lblAppointmentDate);

		rbFemale = new JRadioButton("Female");
		buttonGroup.add(rbFemale);
		rbFemale.setBounds(174, 135, 77, 23);
		getContentPane().add(rbFemale);

		rbMale = new JRadioButton("Male");
		buttonGroup.add(rbMale);
		rbMale.setBounds(272, 135, 59, 23);
		getContentPane().add(rbMale);

		JLabel lblGender = new JLabel("Gender: ");
		lblGender.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblGender.setBounds(39, 128, 95, 33);
		getContentPane().add(lblGender);

		JButton btnGetAnAppointment = new JButton("Get An Appointment");
		btnGetAnAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Appointment.addNode();
				Appointment.orderById();
			}
		});

		btnGetAnAppointment.setBounds(102, 301, 182, 33);
		getContentPane().add(btnGetAnAppointment);
		listAppointments = new List();
		listAppointments.setBounds(10, 348, 366, 156);
		getContentPane().add(listAppointments);

		JLabel lblAvailableHours = new JLabel("Available Hours:");
		lblAvailableHours.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblAvailableHours.setBounds(39, 260, 133, 20);
		getContentPane().add(lblAvailableHours);

		cmbHour = new JComboBox();
		cmbHour.setModel(new DefaultComboBoxModel(new String[] { "08.00am", "08.30am", "09.00am", "09.30am", "10.00am",
				"10.30am", "11.00am", "11.30am", "12.00pm", "12.30pm", "01.00pm", "01.30pm", "02.00pm", "02.30pm",
				"03.00pm", "03.30pm", "04.00pm", "04.30pm", "05.00pm", "05.30pm" }));
		cmbHour.setBounds(174, 262, 157, 18);
		getContentPane().add(cmbHour);
	}

	public List getListAppointments() {
		return getListAppointments();
	}
}
