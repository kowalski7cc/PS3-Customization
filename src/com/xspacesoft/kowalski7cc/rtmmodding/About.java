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
		setTitle(Messages.getString("About.Title"));
		setResizable(false);
		setBounds(100, 100, 383, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(About.class.getResource("/com/xspacesoft/kowalski7cc/rtmmodding/resources/Title_347x100.png")));
		logo.setBounds(10, 11, 355, 109);
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
		btnVisitOurSite.setBounds(8, 170, 258, 23);
		contentPane.add(btnVisitOurSite);
		
		JButton btnClose = new JButton(Messages.getString("About.Close"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnClose.setBounds(276, 170, 89, 23);
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
