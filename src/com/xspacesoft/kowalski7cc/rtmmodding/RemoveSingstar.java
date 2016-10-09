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

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class RemoveSingstar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7927232510551673876L;
	private JPanel contentPane;
	protected FTPClient ftp;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void show(FTPClient ftp) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveSingstar frame = new RemoveSingstar(ftp);
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
	 * @param ftp 
	 */
	public RemoveSingstar(FTPClient ftp) {
		this.ftp=ftp;
		setTitle(Messages.getString("RemoveSingstar.Title") + " - " + Costants.APPLICATION_NAME);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ConnectionDialog.class.getResource(
						"/com/xspacesoft/kowalski7cc/rtmmodding/resources/" + Utils.getIconSystem() + ".png")));  //$NON-NLS-1$
		setBounds(100, 100, 450, 121);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setMaximumSize(new Dimension(500, 130));
		setMinimumSize(new Dimension(300, 120));
		JLabel lblDescription = new JLabel(Messages.getString("RemoveSingstar.Description")); //$NON-NLS-1$
		contentPane.add(lblDescription, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnRun = new JButton(Messages.getString("RemoveSingstar.Run")); //$NON-NLS-1$
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ftp.changeDirectory(comboBox.getSelectedItem().toString());
					ftp.changeDirectory("c");
					String[] folds = ftp.listNames();
					for (int i = 0; i < folds.length; i++) {
						if(!folds[i].contains(".")) {
							ftp.changeDirectory(folds[i]);
							if(Costants.DEBUG)
								System.out.println("Current ftp dir: " + ftp.currentDirectory());
							String[] files = ftp.listNames();
							for (int j = 0; j < files.length; j++) {
								if((files[j].contains(".xml"))&&(!files[j].contains(".xml.stock"))){
									if(Costants.DEBUG)
										System.out.println(files[j] + " found!");
									ftp.setPassive(false);
									if(Costants.DEBUG)
										System.out.println("Passive: " + ftp.isPassive());
									if(!Arrays.asList(files[j]).contains(files[j]+".stock")) {
										File tmp = new File(files[j]+".stock");
										if(!tmp.exists())
											tmp.createNewFile();
										ftp.download(files[j], tmp);
										ftp.upload(tmp);
										tmp.delete();
										if(Costants.DEBUG)
											System.out.println(files[j] + " backupped!");
									}
									File tmp = new File(files[j]);
									tmp = new File(files[j]);
									tmp.createNewFile();
									ftp.upload(tmp);
									tmp.delete();
									if(Costants.DEBUG)
										System.out.println(files[j] + " edited!");
									setVisible(false);
									return;
								}
							}
						}
					}
					setVisible(false);
				} catch (IllegalStateException | IOException
						| FTPIllegalReplyException | FTPException | FTPDataTransferException 
						| FTPAbortedException | FTPListParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnRun);
		
		JButton btnClose = new JButton(Messages.getString("Window.Close")); //$NON-NLS-1$
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel.add(btnClose);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		comboBox = new JComboBox<String>();
		try {
			if(ftp.isConnected()) {
				while(!ftp.currentDirectory().equalsIgnoreCase("/")) {
					ftp.changeDirectoryUp();
					if(Costants.DEBUG)
						System.out.println("Current ftp dir: " + ftp.currentDirectory());
				}
				ftp.changeDirectory("dev_hdd0");
				if(Costants.DEBUG)
					System.out.println("Current ftp dir: " + ftp.currentDirectory());
				ftp.changeDirectory("tmp");
				if(Costants.DEBUG)
					System.out.println("Current ftp dir: " + ftp.currentDirectory());
				ftp.changeDirectory("explore");
				if(Costants.DEBUG)
					System.out.println("Current ftp dir: " + ftp.currentDirectory());
				ftp.changeDirectory("xil2");
				if(Costants.DEBUG)
					System.out.println("Current ftp dir: " + ftp.currentDirectory());
				ftp.changeDirectory("game");
				if(Costants.DEBUG)
					System.out.println("Current ftp dir: " + ftp.currentDirectory());
				String[] langs = ftp.listNames();
				boolean myLang = false;
				for (String string : langs) {
					if(!string.contains("."))
						comboBox.addItem(string);
					if(string.equals(Locale.getDefault().getLanguage()))
						myLang=true;
				}
				if(myLang)
					comboBox.setSelectedItem(Locale.getDefault().getLanguage());
			} else {
				btnRun.setEnabled(false);
				comboBox.addItem(Messages.getString("RemoveSingstar.Error"));
			}
		} catch (IllegalStateException | IOException | FTPIllegalReplyException
				| FTPException | FTPDataTransferException | FTPAbortedException | FTPListParseException e) {
			btnRun.setEnabled(false);
			comboBox.addItem(Messages.getString("RemoveSingstar.Error"));
			if(Costants.DEBUG)
				if(e.getMessage().contains("FTPConnection closed"))
					System.out.println("FTP Connection closed");
				else
					e.printStackTrace();
		}
		panel_1.add(comboBox);
	}

}
