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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Eula extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2388130121266333532L;
	private static final String EULA = "eula.txt";
	private JPanel contentPane;	

	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Eula frame = new Eula();
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
	public Eula() {
		setTitle(Messages.getString("Eula.Title") + " - " + Costants.APPLICATION_NAME); //$NON-NLS-1$
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{61, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnOk = new JButton(Messages.getString("Window.Close")); //$NON-NLS-1$
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eula.this.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 0;
		panel.add(btnOk, gbc_btnOk);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		JTextArea lblEula = new JTextArea();
		lblEula.setLineWrap(true);
			lblEula.setWrapStyleWord(true);
			lblEula.setEditable(false);
			scrollPane.setViewportView(lblEula);
			
		try {
			InputStream eulaInputStream = this.getClass().getResourceAsStream(EULA);
			Scanner eulaScanner = new Scanner(eulaInputStream);
			StringBuilder eulaText = new StringBuilder();
			while(eulaScanner.hasNext()) {
				eulaText.append(eulaScanner.nextLine());
			}
			eulaScanner.close();
			lblEula.setText(eulaText.toString());
		} catch (Exception e) {
			lblEula.setText(Messages.getString("Eula.Error"));
		}
	}

}
