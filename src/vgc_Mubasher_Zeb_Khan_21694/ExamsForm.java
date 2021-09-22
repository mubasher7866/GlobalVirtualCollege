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
import java.util.Objects;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExamsForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> cbTestType;
	private JTextField tbTestName;
	private JTextField tbTotalMarks;
	private JComboBox<String> cbCourseName;
	private JTable table;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnDelete;
	private JTextField tbSearchRecord;
	private String recordID;
	private static ExamsForm frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ExamsForm();
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
	public ExamsForm() {
		// Setting icon for window
		ImageIcon logo = new ImageIcon(Objects
				.requireNonNull(getClass().getClassLoader().getResource("virtual_global_campus_logo_small.png")));
		setIconImage(logo.getImage());
		setTitle("Exams Form");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] itemsForExamCombo = { "Exam", "Quiz", "Test", "Assignment" };
		cbTestType = new JComboBox<String>();
		cbTestType.setBounds(26, 47, 140, 22);
		contentPane.add(cbTestType);
		cbTestType.setModel(new DefaultComboBoxModel<String>(itemsForExamCombo));
		cbTestType.setSelectedIndex(-1);

		JLabel testTyp = new JLabel("Test Type");
		testTyp.setFont(new Font("Times New Roman", Font.BOLD, 12));
		testTyp.setBounds(26, 25, 92, 14);
		contentPane.add(testTyp);

		tbTestName = new JTextField();
		tbTestName.setBounds(204, 47, 117, 22);
		contentPane.add(tbTestName);
		tbTestName.setColumns(10);

		
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
		btnBack.setBounds(335, 374, 89, 23);
		contentPane.add(btnBack);
		
		
		JLabel testNam = new JLabel("Test Name");
		testNam.setFont(new Font("Times New Roman", Font.BOLD, 12));
		testNam.setBounds(204, 25, 92, 14);
		contentPane.add(testNam);

		tbTotalMarks = new JTextField();
		tbTotalMarks.setColumns(10);
		tbTotalMarks.setBounds(357, 47, 92, 22);
		contentPane.add(tbTotalMarks);

		JLabel lblTotalMarks = new JLabel("Total Marks");
		lblTotalMarks.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblTotalMarks.setBounds(357, 25, 92, 14);
		contentPane.add(lblTotalMarks);

		cbCourseName = new JComboBox<String>();
		cbCourseName.setSelectedIndex(-1);
		cbCourseName.setBounds(478, 43, 158, 22);
		contentPane.add(cbCourseName);

		JLabel lblNewLabel = new JLabel("Course Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(478, 25, 85, 14);
		contentPane.add(lblNewLabel);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(checkControls())
				{
					if (btnSave.getText().toLowerCase().equals("save")) {
						try {

							// Start saving records in exams table
							AppConfig.pst = AppConfig.con.prepareStatement(
									"insert into exams(testType,marks,courseName,createdBy,isActive)values(?,?,?,?,?)");
							AppConfig.pst.setString(1, cbTestType.getSelectedItem()+"-"+tbTestName.getText());
							AppConfig.pst.setString(2, tbTotalMarks.getText());
							AppConfig.pst.setString(3, cbCourseName.getSelectedItem().toString());
							AppConfig.pst.setString(4, "System");
							AppConfig.pst.setBoolean(5, true);
							AppConfig.pst.executeUpdate();
							// End saving records in exams table
							
							JOptionPane.showMessageDialog(null, "Record is added successfully!");
							clearAllControls();
							loadExamsRecords("List", "");
						} catch (SQLException exe) {
							exe.printStackTrace();
						}
					}
					else if (btnSave.getText().toLowerCase().equals("update")) {
						try {

							AppConfig.pst = AppConfig.con.prepareStatement(
									"update exams set testType= ?,marks=?,courseName=?,updatedBy=? where id =?");
							AppConfig.pst.setString(1, cbTestType.getSelectedItem()+"-"+tbTestName.getText());
							AppConfig.pst.setString(2, tbTotalMarks.getText());
							AppConfig.pst.setString(3, cbCourseName.getSelectedItem().toString());
							AppConfig.pst.setString(4, "System");
							AppConfig.pst.setString(5, recordID);
							AppConfig.pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Record is updated successfully!");
							clearAllControls();
							loadExamsRecords("List", "");
						} catch (SQLException exe) {
							exe.printStackTrace();
						}
					}
				}
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSave.setBounds(264, 80, 89, 23);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clearAllControls();
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCancel.setBounds(359, 80, 89, 23);
		contentPane.add(btnCancel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 145, 763, 218);
		contentPane.add(scrollPane);

		table = new JTable() {
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
				String tempStr=model.getValueAt(rowIndex, 1).toString();
				String[] tempArr=tempStr.split("-");
				
				cbTestType.setSelectedItem(tempArr[0]);
				tbTestName.setText(tempArr[1]);
				tbTotalMarks.setText(model.getValueAt(rowIndex, 2).toString());
				cbCourseName.setSelectedItem(model.getValueAt(rowIndex, 3).toString());
				
				btnSave.setText("Update");
				// making btnDelete hidden
				btnDelete.setVisible(true);
				
			}
		});
		scrollPane.setViewportView(table);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					AppConfig.pst = AppConfig.con
							.prepareStatement("update exams set isActive='0', updatedBy='System' where id ='"+recordID+"'");
					AppConfig.pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record is deleted successfully!");
					clearAllControls();
					loadExamsRecords("List", "");
				} catch (SQLException exe) {
					exe.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnDelete.setBounds(458, 80, 89, 23);
		contentPane.add(btnDelete);

		tbSearchRecord = new JTextField();
		tbSearchRecord.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				loadExamsRecords("Search", tbSearchRecord.getText());
			}
		});
		tbSearchRecord.setText("");
		tbSearchRecord.setColumns(10);
		tbSearchRecord.setBounds(10, 121, 763, 22);
		contentPane.add(tbSearchRecord);

		// Start My function calls at the load time
		AppConfig.Connect();
		addCoursesInComboBox();
		loadExamsRecords("List", "");
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
		cbTestType.setSelectedIndex(-1);
		tbTestName.setText("");
		tbTotalMarks.setText("");
		cbCourseName.setSelectedIndex(-1);
		tbSearchRecord.setText("");
		loadExamsRecords("List", "");

		btnSave.setText("Save");
		// making btnDelete hidden
		btnDelete.setVisible(false);
	}

	public void loadExamsRecords(String loadType, String searchKey) {
		try {
			String query = null;
			if (loadType.toLowerCase().equals("search") == true)
				query = "select ID, testType as 'Test Type',marks as 'Total Marks',courseName as 'Course Name' from exams where testType like '"
						+ searchKey + "%' and isActive=1 order by testType asc;";
			else if (loadType.toLowerCase().equals("list") == true)
				query = "select ID, testType as 'Test Type',marks as 'Total Marks',courseName as 'Course Name' from exams where isActive=1  order by testType asc";

			AppConfig.pst = AppConfig.con.prepareStatement(query);
			AppConfig.rs = AppConfig.pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(AppConfig.rs));
			JavaWindowsFormJControlsHandling.show_hide_column(0, "hide", table);// Hiding the ID column
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkControls() {

		if (!JavaWindowsFormValidations.validateComboBox("Test Type", cbTestType.getSelectedIndex()))
				return false;
		else if (!JavaWindowsFormValidations.validateOnlyLength("Test Name", tbTestName.getText(), 1, 255))
			return false;
		else if (!JavaWindowsFormValidations.validateOnlyTextBoxDigits("Total Marks", tbTotalMarks.getText(), 1, 10))
			return false;
		else if (!JavaWindowsFormValidations.validateComboBox("Course Name", cbCourseName.getSelectedIndex()))
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
