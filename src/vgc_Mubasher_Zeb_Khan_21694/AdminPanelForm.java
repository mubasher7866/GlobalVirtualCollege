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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class AdminPanelForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static AdminPanelForm frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AdminPanelForm();
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
	public AdminPanelForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 357);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Setting icon for window
		ImageIcon imageSetter = new ImageIcon(getClass().getClassLoader().getResource("virtual_global_campus_logo_small.png"));
		setIconImage(imageSetter.getImage());
		setTitle("Admin Panel");
		
		
		JLabel lblAdminPanel = new JLabel("Admin Panel");
		lblAdminPanel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAdminPanel.setBounds(312, 25, 112, 20);
		contentPane.add(lblAdminPanel);
		
		
		JLabel lbPicCreateCourse = new JLabel("");
		lbPicCreateCourse.setToolTipText("Create, Edit, Delete, View");
		lbPicCreateCourse.setForeground(Color.WHITE);
		lbPicCreateCourse.setBackground(Color.WHITE);
		// Setting image
		imageSetter = new ImageIcon(getClass().getClassLoader().getResource("courses_small.png"));
		lbPicCreateCourse.setIcon(imageSetter);
		lbPicCreateCourse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				showCoursesForm();
			}
		});
		lbPicCreateCourse.setBounds(309, 98, 141, 138);
		contentPane.add(lbPicCreateCourse);
		
		JLabel lbCreateCourse = new JLabel("Courses");
		lbCreateCourse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				showCoursesForm();
			}
		});
		lbCreateCourse.setToolTipText("Create, Edit, Delete, View");
		lbCreateCourse.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbCreateCourse.setBounds(352, 233, 48, 14);
		contentPane.add(lbCreateCourse);
		
		JLabel lbPicUsers = new JLabel("");
		// Setting image
		imageSetter = new ImageIcon(getClass().getClassLoader().getResource("users.png"));
		lbPicUsers.setIcon(imageSetter);
		lbPicUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showUsersForm();
			}
		});
		lbPicUsers.setToolTipText("Create, Edit, Delete, View");
		lbPicUsers.setForeground(Color.WHITE);
		lbPicUsers.setBackground(Color.WHITE);
		lbPicUsers.setBounds(31, 98, 141, 138);
		contentPane.add(lbPicUsers);
		
		JLabel lbUsers = new JLabel("Users");
		lbUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showUsersForm();
			}
		});
		lbUsers.setToolTipText("Create, Edit, Delete, View");
		lbUsers.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbUsers.setBounds(76, 233, 48, 14);
		contentPane.add(lbUsers);
		
		JLabel lbLogout = new JLabel("Logout");
		lbLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.exit(0);//closing the application with success flag
			}
		});
		lbLogout.setToolTipText("Create, Edit, Delete, View");
		lbLogout.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbLogout.setBounds(594, 233, 52, 14);
		contentPane.add(lbLogout);
		
		JLabel lbPicLogout = new JLabel("");
		lbPicLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.exit(0);//closing the application with success flag
			}
		});
		// Setting image
		imageSetter = new ImageIcon(getClass().getClassLoader().getResource("logout_128px.png"));
		lbPicLogout.setIcon(imageSetter);
		lbPicLogout.setForeground(Color.WHITE);
		lbPicLogout.setBackground(Color.WHITE);
		lbPicLogout.setBounds(561, 98, 128, 138);
		contentPane.add(lbPicLogout);
		
		
	}
	
	private void showUsersForm()
	{
		UsersForm.main(null);
		setVisible(false);
	}
	
	private void showCoursesForm()
	{
		EnrollCoursesForm.main(null);
		setVisible(false);
	}
	
	private static void checkForLoginAuthentication() {

		if(!AppConfig.isUserLoginFlag())
		{
			frame.setVisible(false);
			LoginForm.main(null);
		}
	}
}
