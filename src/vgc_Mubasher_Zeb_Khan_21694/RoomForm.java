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
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class RoomForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tbRoomName;
	private JTextField tbRoomNo;
	private JTable table;
	private JButton btnSave;
	private JButton btnCancel;
	private JTextArea tbRoomDescription;
	private JLabel lblNewLabel_2;
	private JTextField tbSearchRecords;
	private String recordID;
	private JButton btnDelete;
	private static RoomForm frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new RoomForm();
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
	public RoomForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Setting icon for window
		ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("virtual_global_campus_logo_small.png")));
		setIconImage(logo.getImage());
		setTitle("Rooms Form");

		tbRoomName = new JTextField();
		tbRoomName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		tbRoomName.setBounds(10, 36, 208, 20);
		contentPane.add(tbRoomName);
		tbRoomName.setColumns(10);

		tbRoomNo = new JTextField();
		tbRoomNo.setFont(new Font("Times New Roman", Font.BOLD, 12));
		tbRoomNo.setBounds(68, 61, 150, 20);
		contentPane.add(tbRoomNo);
		tbRoomNo.setColumns(10);

		JLabel lblNewLabel = new JLabel("Class / Room Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 11, 117, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Room #");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 64, 48, 14);
		contentPane.add(lblNewLabel_1);

		tbRoomDescription = new JTextArea();
		tbRoomDescription.setBounds(228, 34, 284, 48);
		contentPane.add(tbRoomDescription);

		JScrollPane dgvRooms = new JScrollPane();
		dgvRooms.setBounds(0, 142, 522, 178);
		contentPane.add(dgvRooms);

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
				tbRoomName.setText(model.getValueAt(rowIndex, 1).toString());
				tbRoomDescription.setText(model.getValueAt(rowIndex, 2).toString());
				tbRoomNo.setText(model.getValueAt(rowIndex, 3).toString());
				tbRoomNo.setEditable(false);
				
				btnSave.setText("Update");
				// making btnDelete hidden
				btnDelete.setVisible(true);

			}
		});

		dgvRooms.setViewportView(table);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkControls()) {
					if (btnSave.getText().toLowerCase().equals("save") && isClassRoomNoExist(tbRoomNo.getText())) {
						try {

							// Start saving records in room table
							AppConfig.pst = AppConfig.con.prepareStatement(
									"insert into room(name,description,classRoomNo,createdBy,isActive)values(?,?,?,?,?)");
							AppConfig.pst.setString(1, tbRoomName.getText());
							AppConfig.pst.setString(2, tbRoomDescription.getText());
							AppConfig.pst.setString(3, tbRoomNo.getText());
							AppConfig.pst.setString(4, "System");
							AppConfig.pst.setBoolean(5, true);
							AppConfig.pst.executeUpdate();
							// End saving records in room table

							JOptionPane.showMessageDialog(null, "Record is added successfully!");
							clearAllControls();
							loadRoomRecords("List", "");
						} catch (SQLException exe) {
							exe.printStackTrace();
						}
					} else if (btnSave.getText().toLowerCase().equals("update")) {
						try {

							AppConfig.pst = AppConfig.con.prepareStatement(
									"update room set name= ?,description=?,classRoomNo=?,updatedBy=? where id =?");
							AppConfig.pst.setString(1, tbRoomName.getText());
							AppConfig.pst.setString(2, tbRoomDescription.getText());
							AppConfig.pst.setString(3, tbRoomNo.getText());
							AppConfig.pst.setString(4, "System");
							AppConfig.pst.setBoolean(5, true);
							AppConfig.pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Record is updated successfully!");
							clearAllControls();
							loadRoomRecords("List", "");
						} catch (SQLException exe) {
							exe.printStackTrace();
						}
					}
				}

			}
		});
		btnSave.setBounds(163, 93, 89, 23);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				loadRoomRecords("list", "");
				clearAllControls();
			}
		});
		btnCancel.setBounds(262, 93, 89, 23);
		contentPane.add(btnCancel);

		lblNewLabel_2 = new JLabel("Enter Description...");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_2.setBounds(230, 11, 127, 14);
		contentPane.add(lblNewLabel_2);

		tbSearchRecords = new JTextField();
		tbSearchRecords.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				loadRoomRecords("Search", tbSearchRecords.getText());
			}
		});
		tbSearchRecords.setBounds(0, 122, 522, 20);
		contentPane.add(tbSearchRecords);
		tbSearchRecords.setColumns(10);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					AppConfig.pst = AppConfig.con
							.prepareStatement("update room set isActive=?, updatedBy=? where id =?");
					AppConfig.pst.setBoolean(1, false);
					AppConfig.pst.setString(2, "System");
					AppConfig.pst.setString(3, recordID);
					AppConfig.pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record is deleted successfully!");
					clearAllControls();
					loadRoomRecords("List", "");
				} catch (SQLException exe) {
					exe.printStackTrace();
				}

			}
		});
		btnDelete.setBounds(68, 93, 89, 23);
		contentPane.add(btnDelete);

		// Start My function calls at the load time
		AppConfig.Connect();
		loadRoomRecords("list", "");
		clearAllControls();
		// End My function calls at the load time
	}

	private void clearAllControls() {

		tbRoomName.setText("");
		tbRoomNo.setText("");
		tbRoomDescription.setText("");
		tbSearchRecords.setText("");
		tbRoomNo.setEditable(true);
		
		btnSave.setText("Save");
		btnDelete.setVisible(false);
	}

	public void loadRoomRecords(String loadType, String searchKey) {
		try {
			String query = null;
			if (loadType.toLowerCase().equals("search"))
				query = "select ID, name as 'Name',description as 'Description' ,classRoomNo as 'Room#' from room where name like '"
						+ searchKey + "%' and isActive=1 order by name asc;";
			else if (loadType.toLowerCase().equals("list"))
				query = "select ID, name as 'Name',description as 'Description' ,classRoomNo as 'Room#' from room where isActive=1  order by name asc";

			AppConfig.pst = AppConfig.con.prepareStatement(query);
			AppConfig.rs = AppConfig.pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(AppConfig.rs));
			JavaWindowsFormJControlsHandling.show_hide_column(0, "hide", table);// Hiding the ID column
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean checkControls() {

		if (!JavaWindowsFormValidations.validateOnlyTextBoxLetters("Room Name", tbRoomName.getText(), 1, 250))
			return false;
		else if (!JavaWindowsFormValidations.validateOnlyTextBoxDigits("Room #", tbRoomNo.getText(), 1, 6))
			return false;
		else if (!JavaWindowsFormValidations.validateOnlyTextBoxLetters("Room Description", tbRoomDescription.getText(),
				5, 500))
			return false;
		else
			return true;
	}
	
	public static boolean isClassRoomNoExist(String classRoomNo) {
		String query = "select classRoomNo from room where classRoomNo=?";
		try {
			AppConfig.pst = AppConfig.con.prepareStatement(query);
			AppConfig.pst.setString(1, classRoomNo);
			AppConfig.rs = AppConfig.pst.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			// check for any classRoomNo already in use or not
			// check for any record, if the first call to next() returns false then there is
			// no data in the ResultSet.
			if (AppConfig.rs.next()) {
				JavaWindowsFormUserInformers
						.showMsgWithJPane("This Room number is already registered, please choose the other one.");
				return false;
			} else
				return true;
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return false;
	}
	private static void checkForLoginAuthentication() {

		if(!AppConfig.isUserLoginFlag())
		{
			frame.setVisible(false);
			LoginForm.main(null);
		}
	}
}
