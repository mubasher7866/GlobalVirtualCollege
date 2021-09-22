/*
 *
 * Author: 										Mubasher Zeb Khan & Michele Sousa
 *
 * ID:											21694 & 21959
 *
 * Code Running Status							Perfect
 *
 *
 */

package vgc_Mubasher_Zeb_Khan_21694;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;

public class StudentAttendance extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> cbCourseName;
	private JTable table;
	private JLabel lblStudentAttendance;
	private JLabel lbGotoUpdateAttendance;
	private JButton btnSaveAttendance;
	private JButton btnUpdate;
	private JLabel lbAttendance;
	private JDateChooser dtpAttendance;
	private static StudentAttendance frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new StudentAttendance();
					frame.setVisible(true);
					
					checkForLoginAuthentication();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentAttendance() {
		// Setting icon for window
		ImageIcon logo = new ImageIcon(Objects
				.requireNonNull(getClass().getClassLoader().getResource("virtual_global_campus_logo_small.png")));
		setIconImage(logo.getImage());
		setTitle("Student Attendance");
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		dtpAttendance = new JDateChooser();
		dtpAttendance.setBounds(178, 74, 128, 20);
		contentPane.add(dtpAttendance);
		dtpAttendance.setDateFormatString("yyyy-MM-dd");

		table = new JTable() {

			private static final long serialVersionUID = 1L;

			// Setting the data types of columns after loading data in it.
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				default:
					return Boolean.class;
				}
			}
		};
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 105, 638, 224);
		getContentPane().add(scrollPane);

		cbCourseName = new JComboBox<String>();
		cbCourseName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		cbCourseName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cbCourseName.getItemCount() > 0 && cbCourseName.getSelectedItem() != null && isVisible() == true) {
					loadStudentAttendanceRecords("Combobox", cbCourseName.getSelectedItem().toString());
				}
			}
		});
		cbCourseName.setSelectedIndex(-1);
		cbCourseName.setBounds(10, 72, 158, 22);
		contentPane.add(cbCourseName);

		JLabel lblNewLabel = new JLabel("Course Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 54, 85, 14);
		contentPane.add(lblNewLabel);

		btnSaveAttendance = new JButton("Save Attendance");
		btnSaveAttendance.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSaveAttendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkControls()) {
					if (btnSaveAttendance.getText().equals("Save Attendance")) {
						TableModel model = table.getModel();
						String query = "";
						for (int i = 0; i < table.getRowCount(); i++) {
							try {
								// Start saving records in attendance table
								if (model.getValueAt(i, 3).toString().equals("true"))
									query = "insert into attendance(studentID,courseName,attendanceStatus,createdBy,isActive)values('"
											+ model.getValueAt(i, 2).toString() + "','"
											+ cbCourseName.getSelectedItem().toString() + "','1','System','1')";
								else
									query = "insert into attendance(studentID,courseName,attendanceStatus,createdBy,isActive)values('"
											+ model.getValueAt(i, 2).toString() + "','"
											+ cbCourseName.getSelectedItem().toString() + "','0','System','1')";

								AppConfig.pst = AppConfig.con.prepareStatement(query);
								AppConfig.pst.executeUpdate();
								// End saving records in attendance table
							} catch (SQLException exe) {
								exe.printStackTrace();
								break;
							}

						}
						JOptionPane.showMessageDialog(null, "Attendance is added successfully!");
						clearAllControls();
					} else if (btnSaveAttendance.getText().equals("Load Old Attendance")) {
						loadStudentAttendanceRecords("Update",
								DateFormat.getDateInstance(DateFormat.SHORT).format(dtpAttendance.getDate()));
					} else if (btnSaveAttendance.getText().equals("Cancel")) {
						clearAllControls();
					}
				}
			}

		});
		btnSaveAttendance.setBounds(463, 72, 173, 23);
		contentPane.add(btnSaveAttendance);

		lblStudentAttendance = new JLabel("Student Attendance");
		lblStudentAttendance.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblStudentAttendance.setBounds(276, 11, 145, 20);
		contentPane.add(lblStudentAttendance);

		lbGotoUpdateAttendance = new JLabel("Goto Update Attendance");
		lbGotoUpdateAttendance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				lbGotoUpdateAttendance.setVisible(false);
				btnUpdate.setVisible(true);
				lbAttendance.setVisible(true);
				dtpAttendance.setVisible(true);
				btnSaveAttendance.setText("Load Old Attendance");
			}
		});
		lbGotoUpdateAttendance.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbGotoUpdateAttendance.setBounds(463, 38, 173, 22);
		contentPane.add(lbGotoUpdateAttendance);

		lbAttendance = new JLabel("Attendance Date");
		lbAttendance.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbAttendance.setBounds(178, 54, 128, 14);
		contentPane.add(lbAttendance);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					//if user is faculty then goto his respective screen
					if(AppConfig.getRoleID().equals("4"))
					{
						FacultyForm.main(null);
						setVisible(false);
					}
				}
				catch(Exception exe)
				{
					exe.printStackTrace();
				}
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnBack.setBounds(285, 338, 89, 23);
		contentPane.add(btnBack);
		
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkControls()) {
					
					TableModel model = table.getModel();
					String query = "";
					for (int i = 0; i < table.getRowCount(); i++) {
						try {
							// Start saving records in attendance table
							if (model.getValueAt(i, 3).toString().equals("true"))
								query = "update attendance set attendanceStatus='1',updatedBy='System' where ID='"+model.getValueAt(i, 0).toString()+"'";
							else
								query = "update attendance set attendanceStatus='0',updatedBy='System' where ID='"+model.getValueAt(i, 0).toString()+"'";

							AppConfig.pst = AppConfig.con.prepareStatement(query);
							AppConfig.pst.executeUpdate();
							// End saving records in attendance table
						} catch (SQLException exe) {
							exe.printStackTrace();
							break;
						}

					}
					JOptionPane.showMessageDialog(null, "Attendance is updated successfully!");
					clearAllControls();

				}
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnUpdate.setBounds(351, 72, 102, 23);
		contentPane.add(btnUpdate);

		// Start My function calls at the load time
		AppConfig.Connect();
		addCoursesInComboBox();
		clearAllControls();
		// End My function calls at the load time
	}

	public void addCoursesInComboBox() {
		try {

			String query = "select * from room where isActive=1";
			AppConfig.pst = AppConfig.con.prepareStatement(query);
			AppConfig.rs = AppConfig.pst.executeQuery();

			while (AppConfig.rs.next()) {

				cbCourseName.addItem(AppConfig.rs.getString("name"));
			}

			cbCourseName.setSelectedIndex(-1);
		} catch (SQLException exe) {
			exe.printStackTrace();
		}
	}

	private void clearAllControls() {

		// Deleting all the rows from table
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		btnUpdate.setVisible(false);
		lbGotoUpdateAttendance.setVisible(true);
		btnSaveAttendance.setText("Save Attendance");
		lbAttendance.setVisible(false);
		dtpAttendance.setVisible(false);

		cbCourseName.setSelectedIndex(-1);
	}

	public void loadStudentAttendanceRecords(String loadType, String searchKey) {
		try {
			String query = null;
			if (loadType.toLowerCase().equals("search") == true)
				query = "SELECT users.name as 'Student Name',fee.studentID as 'Student ID' FROM fee INNER JOIN courses ON fee.enrollCourseID = courses.ID INNER JOIN student ON student.studentID = courses.studentID INNER JOIN users ON users.ID = student.ID where users.name like '"
						+ searchKey + "%' and courses.isActive=1 order by users.name asc;";
			else if (loadType.toLowerCase().equals("list") == true)
				query = "SELECT users.name as 'Student Name',fee.studentID as 'Student ID' FROM fee INNER JOIN courses ON fee.enrollCourseID = courses.ID INNER JOIN student ON student.studentID = courses.studentID INNER JOIN users ON users.ID = student.ID where courses.isActive=1 order by users.name asc;";
			else if (loadType.toLowerCase().equals("combobox") == true)
				query = "SELECT users.name as 'Student Name',fee.studentID as 'Student ID' FROM fee INNER JOIN courses ON fee.enrollCourseID = courses.ID INNER JOIN student ON student.studentID = courses.studentID INNER JOIN users ON users.ID = student.ID where courses.isActive=1 && courses.name='"
						+ searchKey + "' order by users.name asc;";
			else if (loadType.toLowerCase().equals("update") == true) {

				// converting the date into this format YYYY-MM-DD
				String[] tempArr = searchKey.split("/");
				searchKey = tempArr[2];
				searchKey += "-" + tempArr[1];
				searchKey += "-" + tempArr[0];

				query = "SELECT attendance.ID,users.name as 'Student Name',attendance.studentID as 'Student ID',attendance.attendanceStatus FROM attendance INNER JOIN student ON student.studentID = attendance.studentID INNER JOIN users ON users.ID = student.ID where attendance.createdAt like '"
						+ searchKey + "%' and attendance.courseName='" + cbCourseName.getSelectedItem().toString()
						+ "' order by users.name asc;";
				btnSaveAttendance.setText("Cancel");
			}

			AppConfig.pst = AppConfig.con.prepareStatement(query);

			Object[] columnNames = { "ID","Student Name", "Student ID", "Attendance" };
			Object[][] data = null;

			DefaultTableModel model = new DefaultTableModel(data, columnNames);

			AppConfig.rs = AppConfig.pst.executeQuery();
			while (AppConfig.rs.next()) {

				if (loadType.toLowerCase().equals("update") == true)
					model.addRow(new Object[] { AppConfig.rs.getString("ID"),AppConfig.rs.getString("Student Name"),
							AppConfig.rs.getString("Student ID"), AppConfig.rs.getBoolean(4) });
				else
					model.addRow(new Object[] { "ID",AppConfig.rs.getString("Student Name"),
							AppConfig.rs.getString("Student ID"), true });
			}

			table.setModel(model);
			
			JavaWindowsFormJControlsHandling.show_hide_column(0, "hide",table);// Hiding the ID column
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean checkControls() {

		if (!JavaWindowsFormValidations.validateComboBox("Course Name", cbCourseName.getSelectedIndex()))
			return false;
		else
			return true;
	}
	
	private static void checkForLoginAuthentication() {

		if(!AppConfig.isUserLoginFlag())
		{
			frame.setVisible(false);
			LoginForm.main(null);
		}
	}
}
