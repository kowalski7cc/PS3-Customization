/*This file is part of PS3 Customization.

PS3 Customization is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PS3 Customization is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with PS3 Customization.  If not, see <http://www.gnu.org/licenses/>.*/

package com.xspacesoft.kowalski7cc.rtmmodding;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class RebootAlert extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5063476403600307844L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void show(String address) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RebootAlert frame = new RebootAlert(address);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					Toolkit.getDefaultToolkit().beep();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RebootAlert(String address) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ConnectionDialog.class.getResource(
						"/com/xspacesoft/kowalski7cc/rtmmodding/resources/" + Utils.getIconSystem() + ".png")));  //$NON-NLS-1$
		setTitle(Messages.getString("RebootAlert.Title") + " " + ((address!=null)?address:"disconnected")); //$NON-NLS-1$
		setResizable(false);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYouAreGoing = new JLabel("<html>" + Messages.getString("RebootAlert.Row1") +"<br>"
				+ Messages.getString("RebootAlert.Row2") + "<br>"
				+ Messages.getString("RebootAlert.Row3") + "</html>");
		lblYouAreGoing.setVerticalAlignment(SwingConstants.TOP);
		lblYouAreGoing.setBounds(10, 11, 424, 55);
		contentPane.add(lblYouAreGoing);
		
		JButton btnYes = new JButton(Messages.getString("Window.Yes"));
		if(address == null)
			btnYes.setEnabled(false);
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HttpURLConnection req = (HttpURLConnection)
							new URL("http://" + address + "/restart.ps3").openConnection();
					req.setRequestMethod("GET");
					req.connect();
					InputStream stream = req.getInputStream();
					while(stream.available()>0) {
						stream.read();
					}
					req.disconnect();
					setVisible(false);
				} catch (IOException e1) {
					// WebMan not available?
				}
			}
		});
		btnYes.setBounds(10, 77, 89, 23);
		contentPane.add(btnYes);
		
		JButton btnNo = new JButton(Messages.getString("Window.No"));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNo.setBounds(345, 77, 89, 23);
		contentPane.add(btnNo);
	}
}
