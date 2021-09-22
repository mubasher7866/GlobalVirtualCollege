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

import javax.swing.JOptionPane;

public class JavaWindowsFormUserInformers {

	public static void showMsgWithJPane(String msg)
	{
		JOptionPane.showMessageDialog(null, msg);
	}
	public static int showMsgWithConfirmJPane(String msg)
	{
        int confirmFlag = JOptionPane.showConfirmDialog(null, msg);
        return confirmFlag;// 0=yes, 1=no, 2=cancel
	}
}
