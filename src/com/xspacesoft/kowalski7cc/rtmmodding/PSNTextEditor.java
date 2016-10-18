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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import it.sauronsoftware.ftp4j.FTPClient;

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

	/**
	 * Launch the application.
	 */
	public static void main(FTPClient ftpClient) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PSNTextEditor frame = new PSNTextEditor();
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
	public PSNTextEditor() {
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
		
		JLabel lblInsertHereYour = new JLabel(Messages.getString("PSNTextEditor.lblInsertHereYour.text")); //$NON-NLS-1$
		contentPane.add(lblInsertHereYour, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton(Messages.getString("PSNTextEditor.btnSave.text")); //$NON-NLS-1$
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Save logic
			}
		});
		panel.add(btnSave);
		
		JButton btnCancel = new JButton(Messages.getString("PSNTextEditor.btnCalcel.text")); //$NON-NLS-1$
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PSNTextEditor.this.setVisible(false);
			}
		});
		panel.add(btnCancel);
		
		JTextArea textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
	}

}
