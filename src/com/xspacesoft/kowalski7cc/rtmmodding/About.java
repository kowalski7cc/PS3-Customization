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

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
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
import java.awt.Color;

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
		setBounds(100, 100, 479, 256);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(About.class.getResource("/com/xspacesoft/kowalski7cc/rtmmodding/resources/Title_347x100.png")));
		logo.setBounds(10, 11, 375, 109);
		contentPane.add(logo);
		
		JButton btnVisitOurSite = new JButton(Messages.getString("About.Site"));
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
		btnVisitOurSite.setBounds(8, 170, 179, 23);
		contentPane.add(btnVisitOurSite);
		
		JButton btnClose = new JButton(Messages.getString("About.Close"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnClose.setBounds(296, 170, 89, 23);
		contentPane.add(btnClose);
		
		JLabel lblDevelopedByKowalskicc = new JLabel(Messages.getString("About.Description") + " Kowalski7cc");
		lblDevelopedByKowalskicc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDevelopedByKowalskicc.setBounds(10, 119, 355, 23);
		contentPane.add(lblDevelopedByKowalskicc);
		
		JLabel lblInternalPreview = new JLabel(Messages.getString("About.lblInternalPreview.text")); //$NON-NLS-1$
		lblInternalPreview.setVisible(Costants.DEBUG);
		lblInternalPreview.setForeground(Color.ORANGE);
		lblInternalPreview.setBounds(10, 145, 355, 14);
		contentPane.add(lblInternalPreview);
		
		JButton btnEula = new JButton(Messages.getString("About.btnEula.text")); //$NON-NLS-1$
		btnEula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eula.start();
			}
		});
		btnEula.setBounds(197, 170, 89, 23);
		contentPane.add(btnEula);
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
