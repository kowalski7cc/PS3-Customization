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

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JScrollPane;

/**
 * @author kowal
 *
 */
public class PSNTextEditor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6787523484501592722L;
	private JPanel contentPane;
	private JLabel lblError;

	/**
	 * Launch the application.
	 */
	public static void main(FTPClient ftpClient) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PSNTextEditor frame = new PSNTextEditor(ftpClient);
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
	public PSNTextEditor(FTPClient ftp) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ConnectionDialog.class.getResource(
						"/com/xspacesoft/kowalski7cc/rtmmodding/resources/" + Utils.getIconSystem() + ".png")));  //$NON-NLS-1$
		setType(Type.POPUP);
		setTitle(Messages.getString("PSNTextEditor.this.title")); //$NON-NLS-1$
		setBounds(100, 100, 480, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton(Messages.getString("PSNTextEditor.btnSave.text")); //$NON-NLS-1$
//		if(ftp!=null&&ftp.isConnected())
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Save logic
				try {
					ftp.changeDirectory("/");
				} catch (NullPointerException | IllegalStateException | IOException | FTPIllegalReplyException | FTPException e1) {
					lblError.setVisible(true);
				}
			}
		});
//		else
//			btnSave.setEnabled(false);
		panel.add(btnSave);
		
		JButton btnCancel = new JButton(Messages.getString("PSNTextEditor.btnCalcel.text")); //$NON-NLS-1$
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PSNTextEditor.this.setVisible(false);
			}
		});
		panel.add(btnCancel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{266, 0};
		gbl_panel_1.rowHeights = new int[]{26, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblInsertHereYour = new JLabel(Messages.getString("PSNTextEditor.lblInsertHereYour.text"));
		GridBagConstraints gbc_lblInsertHereYour = new GridBagConstraints();
		gbc_lblInsertHereYour.insets = new Insets(0, 0, 5, 0);
		gbc_lblInsertHereYour.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblInsertHereYour.gridx = 0;
		gbc_lblInsertHereYour.gridy = 0;
		panel_1.add(lblInsertHereYour, gbc_lblInsertHereYour);
		
		lblError = new JLabel(Messages.getString("PSNTextEditor.lblError.text")); //$NON-NLS-1$
		lblError.setVisible(false);
		lblError.setForeground(Color.RED);
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.anchor = GridBagConstraints.WEST;
		gbc_lblError.gridx = 0;
		gbc_lblError.gridy = 1;
		panel_1.add(lblError, gbc_lblError);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

	protected JLabel getLblError() {
		return lblError;
	}
}
