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

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SimpleUserForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static SimpleUserForm frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new SimpleUserForm();
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
	public SimpleUserForm() {
		
		// Setting icon for window
		ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("virtual_global_campus_logo_small.png")));
		setIconImage(logo.getImage());
		setTitle("User Form");
				
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFacultyDashboard = new JLabel("User Dashboard");
		lblFacultyDashboard.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblFacultyDashboard.setBounds(207, 30, 140, 20);
		contentPane.add(lblFacultyDashboard);
		
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
		
	}
	private static void checkForLoginAuthentication() {

		if(!AppConfig.isUserLoginFlag())
		{
			frame.setVisible(false);
			LoginForm.main(null);
		}
	}
}
