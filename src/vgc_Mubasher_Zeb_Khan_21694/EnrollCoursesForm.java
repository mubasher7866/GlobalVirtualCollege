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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JYearChooser;

import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class EnrollCoursesForm extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JTable table;
	private JTextField tbSearchRecords;
	private JTextField tbCourseFee;
	private JTextField tbClassStartTime;
	private JComboBox<String> cbAssignRoomNo;
	private JComboBox<String> cbStudentID;
	private JYearChooser yearChooser;
	private JTextArea tbDescription;
	private String recordID;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnClearSubject;
	private JComboBox<String> cbCourseName;
	private static StudentForm frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnrollCoursesForm frame = new EnrollCoursesForm();
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
	public EnrollCoursesForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 765, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 134, 749, 209);
		contentPane.add(scrollPane);
		table = new JTable(){
			private static final long serialVersionUID = 1L;

			// This will make a non editable JTabel.
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// getting the selected row
				int rowIndex = table.getSelectedRow();
				TableModel model = table.getModel();

				recordID = model.getValueAt(rowIndex, 0).toString();
				cbCourseName.setSelectedItem(model.getValueAt(rowIndex, 1).toString());
				tbCourseFee.setText(model.getValueAt(rowIndex, 2).toString());
				yearChooser.setYear(Integer.parseInt(model.getValueAt(rowIndex, 3).toString()));
				cbStudentID.setSelectedItem(model.getValueAt(rowIndex, 4).toString());
				tbDescription.setText(model.getValueAt(rowIndex, 5).toString());
				cbAssignRoomNo.setSelectedItem(model.getValueAt(rowIndex, 6).toString());
				tbClassStartTime.setText(model.getValueAt(rowIndex, 7).toString());
				
				btnSave.setText("Update");
				// making btnDelete hidden
				btnClearSubject.setVisible(true);
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Course Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 5, 85, 14);
		contentPane.add(lblNewLabel);
		
		tbSearchRecords = new JTextField();
		tbSearchRecords.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				loadCoursesRecords("Search", tbSearchRecords.getText());
			}
		});
		tbSearchRecords.setColumns(10);
		tbSearchRecords.setBounds(-1, 112, 750, 20);
		contentPane.add(tbSearchRecords);
		
		JLabel lblCourseFee_1 = new JLabel("Course Fee");
		lblCourseFee_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblCourseFee_1.setBounds(127, 5, 85, 14);
		contentPane.add(lblCourseFee_1);
		
		tbCourseFee = new JTextField();
		tbCourseFee.setColumns(10);
		tbCourseFee.setBounds(127, 24, 96, 20);
		contentPane.add(tbCourseFee);
		
		JLabel lblCourseYear = new JLabel("Course Year");
		lblCourseYear.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblCourseYear.setBounds(227, 5, 86, 14);
		contentPane.add(lblCourseYear);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblStudentId.setBounds(323, 5, 85, 14);
		contentPane.add(lblStudentId);
		
		tbClassStartTime = new JTextField();
		tbClassStartTime.setText("");
		tbClassStartTime.setColumns(10);
		tbClassStartTime.setBounds(180, 81, 137, 20);
		contentPane.add(tbClassStartTime);
		
		JLabel lblAssignClassRoom = new JLabel("Assign Class Room#");
		lblAssignClassRoom.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblAssignClassRoom.setBounds(15, 63, 160, 14);
		contentPane.add(lblAssignClassRoom);
		
		JLabel lblClassTiming = new JLabel("Class Start Time");
		lblClassTiming.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblClassTiming.setBounds(180, 63, 160, 14);
		contentPane.add(lblClassTiming);
		
		tbDescription = new JTextArea();
		tbDescription.setBounds(445, 24, 294, 46);
		contentPane.add(tbDescription);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblDescription.setBounds(445, 5, 85, 14);
		contentPane.add(lblDescription);
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (checkControls()) {
					if (btnSave.getText().toLowerCase().equals("save")) {
						try {

							// Start saving records in course table
							AppConfig.pst = AppConfig.con.prepareStatement(
									"insert into courses(name,courseFee,courseYear,studentID,description,classRoomNo,classTiming,createdBy,isActive)values(?,?,?,?,?,?,?,?,?)");
							AppConfig.pst.setString(1, cbCourseName.getSelectedItem().toString());
							AppConfig.pst.setString(2, tbCourseFee.getText());
							AppConfig.pst.setLong(3, yearChooser.getYear());
							AppConfig.pst.setString(4, (String) cbStudentID.getSelectedItem());
							AppConfig.pst.setString(5, tbDescription.getText());
							AppConfig.pst.setString(6, (String) cbAssignRoomNo.getSelectedItem());
							AppConfig.pst.setString(7, tbClassStartTime.getText());
							AppConfig.pst.setString(8, "System");
							AppConfig.pst.setBoolean(9, true);
							AppConfig.pst.executeUpdate();
							// End saving records in course table

							// Start saving records in fee table
							String query="insert into fee(feeAmount,studentID,enrollCourseID,createdBy,isActive)values('"+tbCourseFee.getText()+"','"+cbStudentID.getSelectedItem()+"','"+getLastIDFromCourseTable()+"','System','1')";
							AppConfig.pst = AppConfig.con.prepareStatement(query);
							AppConfig.pst.executeUpdate();
							// End saving records in fee table
							
							JOptionPane.showMessageDialog(null, "Record is added successfully!");
							clearAllControls();
							loadCoursesRecords("List", "");
						} catch (SQLException exe) {
							exe.printStackTrace();
						}
					} else if (btnSave.getText().toLowerCase().equals("update")) {
						try {

							AppConfig.pst = AppConfig.con.prepareStatement(
									"update courses set name= ?,courseFee=?,courseYear=?,studentID=?,description=?,classRoomNo=?,classTiming=?,updatedBy=? where id =?");
							AppConfig.pst.setString(1, cbCourseName.getSelectedItem().toString());
							AppConfig.pst.setString(2, tbCourseFee.getText());
							AppConfig.pst.setLong(3, yearChooser.getYear());
							AppConfig.pst.setString(4, (String) cbStudentID.getSelectedItem());
							AppConfig.pst.setString(5, tbDescription.getText());
							AppConfig.pst.setString(6, (String) cbAssignRoomNo.getSelectedItem());
							AppConfig.pst.setString(7, tbClassStartTime.getText());
							AppConfig.pst.setString(8, "System");
							AppConfig.pst.setString(9, recordID);
							AppConfig.pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Record is updated successfully!");
							clearAllControls();
							loadCoursesRecords("List", "");
						} catch (SQLException exe) {
							exe.printStackTrace();
						}
					}
				}
			}
		});
		btnSave.setBounds(350, 80, 89, 23);
		contentPane.add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clearAllControls();
			}
		});
		btnCancel.setBounds(445, 80, 89, 23);
		contentPane.add(btnCancel);
		
		btnClearSubject = new JButton("Clear Subject");
		btnClearSubject.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnClearSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					AppConfig.pst = AppConfig.con
							.prepareStatement("update courses set isActive=?, updatedBy=? where id =?");
					AppConfig.pst.setBoolean(1, false);
					AppConfig.pst.setString(2, "System");
					AppConfig.pst.setString(3, recordID);
					AppConfig.pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record is cleared successfully!");
					clearAllControls();
					loadCoursesRecords("List", "");
				} catch (SQLException exe) {
					exe.printStackTrace();
				}
			}
		});
		btnClearSubject.setBounds(542, 80, 137, 23);
		contentPane.add(btnClearSubject);
		
		cbAssignRoomNo = new JComboBox<String>();
		cbAssignRoomNo.setBounds(10, 79, 165, 22);
		contentPane.add(cbAssignRoomNo);
		
		yearChooser = new JYearChooser();
		yearChooser.setBounds(240, 24, 48, 20);
		contentPane.add(yearChooser);
		
		cbStudentID = new JComboBox<String>();
		cbStudentID.setBounds(312, 23, 127, 22);
		contentPane.add(cbStudentID);
		
		cbCourseName = new JComboBox<String>();
		cbCourseName.setBounds(10, 23, 104, 22);
		contentPane.add(cbCourseName);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					//if user is administrator then goto his respective screen
					if(AppConfig.getRoleID().equals("0"))
					{
						AdminPanelForm.main(null);
						setVisible(false);
					}
					//if user is manager then goto his respective screen
					else if(AppConfig.getRoleID().equals("1"))
					{
						ManagerForm.main(null);
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
		btnBack.setBounds(323, 352, 89, 23);
		contentPane.add(btnBack);
		
		
		// Setting icon for window
		ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("virtual_global_campus_logo_small.png"));
		setIconImage(logo.getImage());
		setTitle("Enroll Course Form");
		
		// Start My function calls at the load time
		AppConfig.Connect();
		addCoursesInComboBox();
		addRolesInComboBox();
		addStudentIDsInComboBox();
		loadCoursesRecords("list", "");
		clearAllControls();
		// End My function calls at the load time
	}


	private void clearAllControls() {
		tbCourseFee.setText("");
		yearChooser.setYear(2020);
		cbCourseName.setSelectedIndex(-1);
		cbStudentID.setSelectedIndex(-1);
		tbDescription.setText("");
		cbAssignRoomNo.setSelectedIndex(-1);
		tbClassStartTime.setText("");

		btnSave.setText("Save");
		// making btnClearSubject hidden
		btnClearSubject.setVisible(false);
	}
	public void loadCoursesRecords(String loadType, String searchKey) {
		try {
			String query = null;
			if (loadType.toLowerCase().equals("search") == true)
				query = "select ID, name as 'Name',courseFee as 'Fee($)',courseYear as 'Course Year',studentID as 'Student ID',description as 'Description',classRoomNo as 'Class Room',classTiming as 'Class Timing' from courses where name like '"
						+ searchKey + "%' and isActive=1 order by name asc;";
			else if (loadType.toLowerCase().equals("list") == true)
				query = "select ID, name as 'Name',courseFee as 'Fee($)',courseYear as 'Course Year',studentID as 'Student ID',description as 'Description',classRoomNo as 'Class Room',classTiming as 'Class Timing' from courses where isActive=1  order by name asc";

			AppConfig.pst = AppConfig.con.prepareStatement(query);
			AppConfig.rs = AppConfig.pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(AppConfig.rs));
			JavaWindowsFormJControlsHandling.show_hide_column(0, "hide",table);// Hiding the ID column
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addRolesInComboBox() {
		try {

			String query = "select * from room where isActive=1";
			AppConfig.pst = AppConfig.con.prepareStatement(query);
			AppConfig.rs = AppConfig.pst.executeQuery();

			while (AppConfig.rs.next()) {

				cbAssignRoomNo.addItem(AppConfig.rs.getString("classRoomNo"));
			}

			cbAssignRoomNo.setSelectedIndex(-1);
		} catch (SQLException exe) {
			exe.printStackTrace();
		}
	}
	public void addStudentIDsInComboBox() {
		try {

			String query = "select * from student where isActive=1";
			AppConfig.pst = AppConfig.con.prepareStatement(query);
			AppConfig.rs = AppConfig.pst.executeQuery();

			while (AppConfig.rs.next()) {

				cbStudentID.addItem(AppConfig.rs.getString("studentID"));
			}

			cbStudentID.setSelectedIndex(-1);
		} catch (SQLException exe) {
			exe.printStackTrace();
		}
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
	public boolean checkControls() {

		if (!JavaWindowsFormValidations.validateComboBox("Course Name", cbCourseName.getSelectedIndex()))
			return false;
		else if (!JavaWindowsFormValidations.validateOnlyTextBoxDigits("Course Fee", tbCourseFee.getText(), 1, 10))
			return false;
		else if (!JavaWindowsFormValidations.validateComboBox("Student ID", cbStudentID.getSelectedIndex()))
				return false;
		else if (!JavaWindowsFormValidations.validateOnlyTextBoxLetters("Course Description", tbDescription.getText(),0, 500))
			return false;
		else if (!JavaWindowsFormValidations.validateComboBox("Assign Room#", cbAssignRoomNo.getSelectedIndex()))
			return false;
		else if (!JavaWindowsFormValidations.validateOnly12HoursFormatTime("Class Start Time", tbClassStartTime.getText()))
		{
			JavaWindowsFormUserInformers.showMsgWithJPane("Please enter correct class start time.");			
			return false;
		}
		else
			return true;
	}
	
	public static int getLastIDFromCourseTable() {
		int lastID=0;
		String query = "SELECT max(ID) as 'ID' FROM courses";
		try {
			AppConfig.pst = AppConfig.con.prepareStatement(query);
			AppConfig.rs = AppConfig.pst.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			if (AppConfig.rs.next() == true) {
				lastID=AppConfig.rs.getInt("ID");//getting the ID
				return lastID;
			} else
				return 1;//if users table empty then return 1
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return 1;//if users table empty then return 1
	}
	private static void checkForLoginAuthentication() {

		if(!AppConfig.isUserLoginFlag())
		{
			frame.setVisible(false);
			LoginForm.main(null);
		}
	}
}
