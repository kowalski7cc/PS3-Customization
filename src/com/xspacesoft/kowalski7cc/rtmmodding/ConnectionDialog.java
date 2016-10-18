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

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

public class ConnectionDialog extends JFrame {

	private static final long serialVersionUID = 5972815054388239511L;
	private static final Logger LOGGER = Logger.getLogger("Logger");
	private JPanel contentPane;
	private JTextField txtConsoleaddress;
	private JPanel panel;
	private JButton btnConnect;
	private JButton btnCancel;
	private JLabel lblConnectError;
	private Preferences prefs;

	/**
	 * Create the frame.
	 */
	public ConnectionDialog(FTPClient ftp) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ConnectionDialog.class.getResource(
						"/com/xspacesoft/kowalski7cc/rtmmodding/resources/" + Utils.getIconSystem() + ".png")));  //$NON-NLS-1$
		prefs = Preferences.userNodeForPackage(getClass());
		String recent = prefs.get(Costants.RECENT_CONNECTIONS_KEY, null);
		setResizable(false);
		setTitle(Messages.getString("ConnectionDialog.Title") + " - " + Costants.APPLICATION_NAME);  //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{623, 0};
		gbl_contentPane.rowHeights = new int[]{42, 42, 0, 42, 42, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		lblConnectError = new JLabel(Messages.getString("ConnectionDialog.Error")); //$NON-NLS-1$
		lblConnectError.setForeground(Color.RED);
		lblConnectError.setVisible(false);

		JLabel lblWelcometext = new JLabel(Messages.getString("ConnectionDialog.Description")); //$NON-NLS-1$
		GridBagConstraints gbc_lblWelcometext = new GridBagConstraints();
		gbc_lblWelcometext.fill = GridBagConstraints.BOTH;
		gbc_lblWelcometext.insets = new Insets(0, 0, 5, 0);
		gbc_lblWelcometext.gridx = 0;
		gbc_lblWelcometext.gridy = 0;
		contentPane.add(lblWelcometext, gbc_lblWelcometext);
		GridBagConstraints gbc_lblConnectError = new GridBagConstraints();
		gbc_lblConnectError.fill = GridBagConstraints.BOTH;
		gbc_lblConnectError.insets = new Insets(0, 0, 5, 0);
		gbc_lblConnectError.gridx = 0;
		gbc_lblConnectError.gridy = 1;
		contentPane.add(lblConnectError, gbc_lblConnectError);
		txtConsoleaddress = new JTextField();

		GridBagConstraints gbc_txtConsoleaddress = new GridBagConstraints();
		gbc_txtConsoleaddress.fill = GridBagConstraints.BOTH;
		gbc_txtConsoleaddress.insets = new Insets(0, 0, 5, 0);
		gbc_txtConsoleaddress.gridx = 0;
		gbc_txtConsoleaddress.gridy = 3;
		contentPane.add(txtConsoleaddress, gbc_txtConsoleaddress);
		txtConsoleaddress.setColumns(10);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		btnConnect = new JButton(Messages.getString("ConnectionDialog.Connect")); //$NON-NLS-1$
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblConnectError.setVisible(false);
				if(txtConsoleaddress.getText().isEmpty()) {
					return;
				} else if(txtConsoleaddress.getText().equals("HiddenTest")) {
					RtmDialog.show(null);
					setVisible(false);
				}
				try {

					ftp.connect(txtConsoleaddress.getText());
					ftp.login("anonymus", "anonymus");
					// Check if hdd exists
					ftp.changeDirectory("dev_hdd0");
					ftp.changeDirectoryUp();
					// Check for dev_blind or dev_rebug
					try {
						if(Arrays.asList(ftp.listNames()).contains("dev_blind"))
							ftp.changeDirectory("dev_blind");
						else if(Arrays.asList(ftp.listNames()).contains("dev_rebug"))
							ftp.changeDirectory("dev_rebug");
						else {
							lblConnectError.setText("Please open Rebug toolbox or Multiman and set dev_flash writable");
							lblConnectError.setVisible(true);
							//							return;
						}
					} catch (FTPDataTransferException | FTPAbortedException
							| FTPListParseException e1) {
						lblConnectError.setText("Something went wrong during conenction. Are you using WebMan's FTP?");
						lblConnectError.setVisible(true);
					}
					//						lblConnectError.setVisible(true);
					// If we are connected, open tool and save choice.
					prefs.put(Costants.RECENT_CONNECTIONS_KEY, txtConsoleaddress.getText());
					RtmDialog.show(ftp);
					setVisible(false);
				} catch (FTPException | IllegalStateException | IOException | FTPIllegalReplyException e1) {
					lblConnectError.setVisible(true);
					if(Costants.DEBUG)
						e1.printStackTrace();
				}
			}
		});
		panel.add(btnConnect);

		btnCancel = new JButton(Messages.getString("Window.Close")); //$NON-NLS-1$
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(btnCancel);
		if(recent != null) {
			txtConsoleaddress.setText(recent);
		} else {
			txtConsoleaddress.setText("");
		}
		LOGGER.fine("Connection dialog loaded");
	}

	protected JTextField getTxtConsoleaddress() {
		return txtConsoleaddress;
	}
	protected JLabel getLblErroreImpossibileEffettuare() {
		return lblConnectError;
	}

}
