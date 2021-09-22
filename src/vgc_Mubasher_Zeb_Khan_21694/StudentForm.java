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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class StudentForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static StudentForm frame;
	private JTable table;
	String studentID="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new StudentForm();
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
	public StudentForm() {
		
		// Setting icon for window
		ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("virtual_global_campus_logo_small.png")));
		setIconImage(logo.getImage());
		setTitle("Student Form");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Dashboard");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(197, 24, 162, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lbPicLogout = new JLabel("");
		lbPicLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.exit(0);//closing the application with success flag
			}
		});
		// Setting image
		logo = new ImageIcon(getClass().getClassLoader().getResource("logout_64px.png"));
		lbPicLogout.setIcon(logo);
		lbPicLogout.setForeground(Color.WHITE);
		lbPicLogout.setBackground(Color.WHITE);
		lbPicLogout.setBounds(415, 112, 64, 64);
		contentPane.add(lbPicLogout);
		
		JLabel lbLogout = new JLabel("Logout");
		lbLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.exit(0);//closing the application with success flag
			}
		});
		lbLogout.setToolTipText("Create, Edit, Delete, View");
		lbLogout.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbLogout.setBounds(425, 182, 52, 14);
		contentPane.add(lbLogout);
		
		JLabel lbPicTimeTable = new JLabel("");
		lbPicTimeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				loadTimeTableAndAssignmentsRecords("TimeTable","");
			}
		});
		// Setting image
		logo = new ImageIcon(getClass().getClassLoader().getResource("time_table_64px.png"));
		lbPicTimeTable.setIcon(logo);
		lbPicTimeTable.setForeground(Color.WHITE);
		lbPicTimeTable.setBackground(Color.WHITE);
		lbPicTimeTable.setBounds(251, 112, 64, 64);
		contentPane.add(lbPicTimeTable);
		
		JLabel lbTimeTable = new JLabel("Time Table");
		lbTimeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				loadTimeTableAndAssignmentsRecords("TimeTable","");
			}
		});
		lbTimeTable.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbTimeTable.setBounds(251, 182, 89, 14);
		contentPane.add(lbTimeTable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 211, 529, 186);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lbAssignmentAndExams = new JLabel("Assignment & Exams");
		lbAssignmentAndExams.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				loadTimeTableAndAssignmentsRecords("Assignments","");
			}
		});
		lbAssignmentAndExams.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbAssignmentAndExams.setBounds(63, 182, 117, 14);
		contentPane.add(lbAssignmentAndExams);
		
		JLabel lbPicAssignmentAndExams = new JLabel("");
		lbPicAssignmentAndExams.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				loadTimeTableAndAssignmentsRecords("Assignments","");
			}
		});
		// Setting image
		logo = new ImageIcon(getClass().getClassLoader().getResource("exam_quiz_assignment.png"));
		lbPicAssignmentAndExams.setIcon(logo);
		lbPicAssignmentAndExams.setForeground(Color.WHITE);
		lbPicAssignmentAndExams.setBackground(Color.WHITE);
		lbPicAssignmentAndExams.setBounds(81, 112, 64, 64);
		contentPane.add(lbPicAssignmentAndExams);
		
		// Start My function calls at the load time
		AppConfig.Connect();
		loadTimeTableAndAssignmentsRecords("TimeTable","");
		// End My function calls at the load time
	}
	
	public void loadTimeTableAndAssignmentsRecords(String loadType, String searchKey) {
		try {
			String query = null;
			if (loadType.toLowerCase().equals("timetable") == true)
				query = "select name as 'Name',courseFee as 'Fee($)',courseYear as 'Course Year',classRoomNo as 'Class Room',classTiming as 'Timing' from courses where studentID='"+getStudentIDAgainstUserID()+"' and isActive=1 order by name asc;";
			else if (loadType.toLowerCase().equals("assignments") == true)
				query = "select testType as 'Test Type',marks as 'Marks',name as 'Course Name' from courses INNER JOIN exams on name=courseName where studentID='"+studentID+"' and exams.isActive='1' order by testType ASC;";

			AppConfig.pst = AppConfig.con.prepareStatement(query);
			AppConfig.rs = AppConfig.pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(AppConfig.rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getStudentIDAgainstUserID() {
		
		String query = "select studentID from student where ID='"+AppConfig.getUserID()+"';";
		try {
			AppConfig.pst = AppConfig.con.prepareStatement(query);
			AppConfig.rs = AppConfig.pst.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			AppConfig.rs.next();
			studentID=AppConfig.rs.getString("studentID");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentID;
	}
	
	private static void checkForLoginAuthentication() {

		if(!AppConfig.isUserLoginFlag())
		{
			frame.setVisible(false);
			LoginForm.main(null);
		}
	}
}
