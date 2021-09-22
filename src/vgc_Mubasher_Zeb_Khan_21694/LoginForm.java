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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class LoginForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tbUsername;
	private JPasswordField tbPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {

		AppConfig.Connect();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 409, 255);
		// Setting icon for window
		ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("virtual_global_campus_logo_small.png")));
		setIconImage(logo.getImage());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tbUsername = new JTextField();
		tbUsername.setFont(new Font("Times New Roman", Font.BOLD, 12));
		tbUsername.setColumns(10);
		tbUsername.setBounds(97, 51, 283, 31);
		contentPane.add(tbUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblPassword.setBounds(25, 129, 62, 14);
		contentPane.add(lblPassword);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(25, 53, 62, 14);
		contentPane.add(lblNewLabel);

		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Check all controls or fields has some data
				if (checkControls()) {

					try {
						
						String pass=JavaWindowsFormSecurityLayer.encryptPassword(String.copyValueOf(tbPassword.getPassword()));
						String query="select * from users where username='"+tbUsername.getText()+"' and password='"+pass+"'";
						AppConfig.pst = AppConfig.con.prepareStatement(query);
						AppConfig.rs = AppConfig.pst.executeQuery();

						//check for any record, if the first call to next() returns false then there is no data in the ResultSet.
						if (!AppConfig.rs.next())
							JavaWindowsFormUserInformers.showMsgWithJPane("Username or password is wrong.");
						//Show the main screen or something else
						else
						{
							saveLoginInfo();
							
							//if user is administrator then goto his respective screen
							if(AppConfig.rs.getString("roleID").equals("0"))
							{
								AdminPanelForm.main(null);
								setVisible(false);
							}
							//if user is manager then goto his respective screen
							else if(AppConfig.rs.getString("roleID").equals("1"))
							{
								ManagerForm.main(null);
								setVisible(false);
							}
							//if user is simple user then goto his respective screen
							else if(AppConfig.rs.getString("roleID").equals("2"))
							{
								SimpleUserForm.main(null);
								setVisible(false);
							}
							//if user is student then goto his respective screen
							else if(AppConfig.rs.getString("roleID").equals("3"))
							{
								StudentForm.main(null);
								setVisible(false);
							}
							//if user is faculty then goto his respective screen
							else if(AppConfig.rs.getString("roleID").equals("4"))
							{
								FacultyForm.main(null);
								setVisible(false);
							}
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
		btnLogin.setBounds(95, 168, 89, 23);
		contentPane.add(btnLogin);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});
		btnCancel.setBounds(194, 168, 89, 23);
		contentPane.add(btnCancel);

		tbPassword = new JPasswordField();
		tbPassword.setFont(new Font("Times New Roman", Font.BOLD, 12));
		tbPassword.setBounds(97, 121, 283, 31);
		contentPane.add(tbPassword);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(logo);
		lblNewLabel_1.setBounds(0, 0, 403, 226);
		contentPane.add(lblNewLabel_1);
	}

	public boolean checkControls() {
		if (!JavaWindowsFormValidations.validateOnlyLength("Username", tbUsername.getText(), 1, 250))
			return false;
		else if (!JavaWindowsFormValidations.validateOnlyLength("Password", String.copyValueOf(tbPassword.getPassword()), 1, 250))
			return false;
		else
			return true;
	}
	
	private void saveLoginInfo()
	{
		try {
			AppConfig.setUsername(AppConfig.rs.getString("username"));
			AppConfig.setPassword(AppConfig.rs.getString("password"));
			AppConfig.setUserID(AppConfig.rs.getString("ID"));
			AppConfig.setRoleID(AppConfig.rs.getString("roleID"));
			AppConfig.setUserLoginFlag(true);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
