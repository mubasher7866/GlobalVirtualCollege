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

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppConfig {

	//start variables for database handling
	public static java.sql.Connection con;
	public static PreparedStatement pst;
	public static ResultSet rs;
	//end variables for database handling
	
	//start variables for user login info handling
	private static String username;
	private static String password;
	private static String userID;
	private static String roleID;
	private static boolean userLoginFlag;
	//start variables for user login info handling
	
	AppConfig()
	{
		userLoginFlag=false;
	}
	
	/*
	 * This function will be used for connection with database
	 */
	public static void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/db_vgc_mubasher_zeb_khan_21694?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");

		} catch (ClassNotFoundException | SQLException exe) {
			exe.printStackTrace();
		}

	}

	/**
	 * @return the userID
	 */
	public static String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public static void setUserID(String userID) {
		AppConfig.userID = userID;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		AppConfig.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		AppConfig.password = password;
	}

	public static String getRoleID() {
		return roleID;
	}

	public static void setRoleID(String roleID) {
		AppConfig.roleID = roleID;
	}

	public static boolean isUserLoginFlag() {
		return userLoginFlag;
	}

	public static void setUserLoginFlag(boolean userLoginFlag) {
		AppConfig.userLoginFlag = userLoginFlag;
	}
}
