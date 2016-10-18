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
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class About extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6437412108719322029L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public About() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/com/xspacesoft/kowalski7cc/rtmmodding/resources/LogoXSS_512.png"))); //$NON-NLS-1$
		setTitle(Messages.getString("About.Title") + " - " + Costants.APPLICATION_NAME);
		setResizable(false);
		setBounds(100, 100, 689, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{154, 154, 154, 0};
		gbl_contentPane.rowHeights = new int[]{54, 0, 54, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(About.class.getResource("/com/xspacesoft/kowalski7cc/rtmmodding/resources/Title_347x100.png")));
		GridBagConstraints gbc_logo = new GridBagConstraints();
		gbc_logo.gridwidth = 3;
		gbc_logo.fill = GridBagConstraints.VERTICAL;
		gbc_logo.insets = new Insets(0, 0, 5, 0);
		gbc_logo.gridx = 0;
		gbc_logo.gridy = 0;
		contentPane.add(logo, gbc_logo);
		
		JButton btnVisitOurSite = new JButton(Messages.getString("About.Site"));
		btnVisitOurSite.setEnabled(false);
		btnVisitOurSite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					openWebpage(new URL("http://xspacesoft.com/").toURI());
				} catch (MalformedURLException | URISyntaxException e1) {
					Toolkit.getDefaultToolkit().beep();
					if(Costants.DEBUG) {
						e1.printStackTrace();
					}
				}
			}
		});
		GridBagConstraints gbc_btnVisitOurSite = new GridBagConstraints();
		gbc_btnVisitOurSite.fill = GridBagConstraints.BOTH;
		gbc_btnVisitOurSite.insets = new Insets(0, 0, 5, 5);
		gbc_btnVisitOurSite.gridx = 0;
		gbc_btnVisitOurSite.gridy = 2;
		contentPane.add(btnVisitOurSite, gbc_btnVisitOurSite);
		
		JButton btnClose = new JButton(Messages.getString("About.Close"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		JButton btnEula = new JButton(Messages.getString("About.btnEula.text")); //$NON-NLS-1$
		btnEula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eula.start();
			}
		});
		GridBagConstraints gbc_btnEula = new GridBagConstraints();
		gbc_btnEula.fill = GridBagConstraints.BOTH;
		gbc_btnEula.insets = new Insets(0, 0, 5, 5);
		gbc_btnEula.gridx = 1;
		gbc_btnEula.gridy = 2;
		contentPane.add(btnEula, gbc_btnEula);
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.insets = new Insets(0, 0, 5, 0);
		gbc_btnClose.fill = GridBagConstraints.BOTH;
		gbc_btnClose.gridx = 2;
		gbc_btnClose.gridy = 2;
		contentPane.add(btnClose, gbc_btnClose);
		
		JLabel lblDevelopedByKowalskicc = new JLabel(Messages.getString("About.Description") + " Kowalski7cc");
		lblDevelopedByKowalskicc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblDevelopedByKowalskicc = new GridBagConstraints();
		gbc_lblDevelopedByKowalskicc.gridwidth = 3;
		gbc_lblDevelopedByKowalskicc.fill = GridBagConstraints.BOTH;
		gbc_lblDevelopedByKowalskicc.gridx = 0;
		gbc_lblDevelopedByKowalskicc.gridy = 3;
		contentPane.add(lblDevelopedByKowalskicc, gbc_lblDevelopedByKowalskicc);
	}
	
	public static void openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
}
