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
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FacultyForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static FacultyForm frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FacultyForm();
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
	public FacultyForm() {
		
		// Setting icon for window
		ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("virtual_global_campus_logo_small.png")));
		setIconImage(logo.getImage());
		setTitle("Faculty Form");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFacultyDashboard = new JLabel("Faculty Dashboard");
		lblFacultyDashboard.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblFacultyDashboard.setBounds(241, 40, 162, 20);
		contentPane.add(lblFacultyDashboard);
		
		JLabel lbPicAttendance = new JLabel("");		
		lbPicAttendance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				showAttendanceForm();
			}
		});
		// Setting image
		logo = new ImageIcon(getClass().getClassLoader().getResource("attendance.png"));
		lbPicAttendance.setIcon(logo);
		lbPicAttendance.setToolTipText("Create, Edit, Delete, View");
		lbPicAttendance.setForeground(Color.WHITE);
		lbPicAttendance.setBackground(Color.WHITE);
		lbPicAttendance.setBounds(87, 110, 64, 64);
		contentPane.add(lbPicAttendance);
		
		JLabel lbAttendance = new JLabel("Attendance");
		lbAttendance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				showAttendanceForm();
			}
		});
		lbAttendance.setToolTipText("Create, Edit, Delete, View");
		lbAttendance.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbAttendance.setBounds(87, 180, 64, 14);
		contentPane.add(lbAttendance);
		
		JLabel lbPicExaminations = new JLabel("");
		lbPicExaminations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				showExamForm();
			}
		});
		// Setting image
		logo = new ImageIcon(getClass().getClassLoader().getResource("exam_quiz_assignment.png"));
		lbPicExaminations.setIcon(logo);
		lbPicExaminations.setToolTipText("Create, Edit, Delete, View");
		lbPicExaminations.setForeground(Color.WHITE);
		lbPicExaminations.setBackground(Color.WHITE);
		lbPicExaminations.setBounds(299, 110, 64, 64);
		contentPane.add(lbPicExaminations);
		
		JLabel lbExaminations = new JLabel("Examinations");
		lbExaminations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				showExamForm();
			}
		});
		lbExaminations.setToolTipText("Create, Edit, Delete, View");
		lbExaminations.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbExaminations.setBounds(299, 180, 79, 14);
		contentPane.add(lbExaminations);
		
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
		lbPicLogout.setBounds(504, 110, 64, 64);
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
		lbLogout.setBounds(514, 180, 52, 14);
		contentPane.add(lbLogout);
	}
	private static void checkForLoginAuthentication() {

		if(!AppConfig.isUserLoginFlag())
		{
			frame.setVisible(false);
			LoginForm.main(null);
		}
	}
	
	private void showAttendanceForm()
	{
		StudentAttendance.main(null);
		setVisible(false);
	}
	
	private void showExamForm()
	{
		ExamsForm.main(null);
		setVisible(false);
	}
}
