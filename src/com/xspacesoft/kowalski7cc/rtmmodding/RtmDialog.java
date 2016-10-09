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
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class RtmDialog extends JFrame {

	private static final long serialVersionUID = 2981607864076342362L;
	private JPanel contentPane;
	protected FTPClient ftp;
	protected File data = Costants.DATA_PATH;
	private JList<String> listElements;
	private JButton btnInstall;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void show(FTPClient ftp) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RtmDialog frame = new RtmDialog(ftp);
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
	public RtmDialog(FTPClient ftp) {
		mkdirs();
		try {
			javax.swing.UIManager.setLookAndFeel( javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e2) {
			// Not blocking
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ConnectionDialog.class.getResource(
						"/com/xspacesoft/kowalski7cc/rtmmodding/resources/" + Utils.getIconSystem() + ".png")));  //$NON-NLS-1$
		setTitle((ftp.isConnected()?ftp.getHost():"Disconnescted") + " - " + Costants.APPLICATION_NAME);
		this.ftp = ftp;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 0));
		setContentPane(contentPane);

		lblStatus = new JLabel("Idle");
		lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblStatus, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPaneCustomizations = new JScrollPane();
		panel.add(scrollPaneCustomizations);
		scrollPaneCustomizations.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JList<String> listCustomizations = new JList<String>();
		listCustomizations.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String choice = listCustomizations.getSelectedValue();
				File current = new File(data, choice);
				if(!current.exists()) {
					current.mkdirs();
				}
				listElements.setListData(current.list());
				if(current.list().length==0) {
					btnInstall.setEnabled(false);
					if(Costants.DEBUG)
						System.out.println("[DEBG] - Folder empty");
				} else {
					listElements.setSelectedIndex(0);
				}
			}
		});
		scrollPaneCustomizations.setViewportView(listCustomizations);
		listCustomizations.setListData(Costants.MODS_SUPPORTED);

		JLabel lblCustomizations = new JLabel(Messages.getString("RtmDialog.Customizations"));
		scrollPaneCustomizations.setColumnHeaderView(lblCustomizations);

		JScrollPane scrollPaneElements = new JScrollPane();
		panel.add(scrollPaneElements);

		JLabel lblElements = new JLabel(Messages.getString("RtmDialog.Elements"));
		scrollPaneElements.setColumnHeaderView(lblElements);

		listElements = new JList<String>();
		listElements.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(listElements.getSelectedIndex()>-1) {
					if(ftp.isConnected())
						btnInstall.setEnabled(true);
					else
						btnInstall.setEnabled(false);
				}
				if(Costants.DEBUG)
					System.out.println(listElements.getSelectedValue());
			}
		});
		scrollPaneElements.setViewportView(listElements);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {10, 0};
		gbl_panel_1.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);

		btnInstall = new JButton(Messages.getString("RtmDialog.Install"));
		btnInstall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				installComponent(listCustomizations.getSelectedValue(), listElements.getSelectedValue());
			}
		});
		btnInstall.setEnabled(false);
		GridBagConstraints gbc_btnInstall = new GridBagConstraints();
		gbc_btnInstall.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInstall.anchor = GridBagConstraints.NORTH;
		gbc_btnInstall.insets = new Insets(0, 0, 5, 0);
		gbc_btnInstall.gridx = 0;
		gbc_btnInstall.gridy = 0;
		panel_1.add(btnInstall, gbc_btnInstall);

		JButton btnAllStock = new JButton(Messages.getString("RtmDialog.Stock"));
		if(!ftp.isConnected())
			btnAllStock.setEnabled(false);
		btnAllStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runStockInstall();
			}
		});
		GridBagConstraints gbc_btnAllStock = new GridBagConstraints();
		gbc_btnAllStock.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAllStock.anchor = GridBagConstraints.NORTH;
		gbc_btnAllStock.insets = new Insets(0, 0, 5, 0);
		gbc_btnAllStock.gridx = 0;
		gbc_btnAllStock.gridy = 1;
		panel_1.add(btnAllStock, gbc_btnAllStock);

		JButton btnReboot = new JButton(Messages.getString("RebootAlert.Title"));
		btnReboot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RebootAlert.show(ftp.isConnected()?ftp.getHost():null);
			}
		});
		
		JButton btnPerfectRecovery = new JButton(Messages.getString("PerfectRecovery.Title"));
		btnPerfectRecovery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PerfectRecovery.main(RtmDialog.this, ftp);
			}
		});
		GridBagConstraints gbc_btnPerfectRecovery = new GridBagConstraints();
		gbc_btnPerfectRecovery.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPerfectRecovery.insets = new Insets(0, 0, 5, 0);
		gbc_btnPerfectRecovery.gridx = 0;
		gbc_btnPerfectRecovery.gridy = 2;
		panel_1.add(btnPerfectRecovery, gbc_btnPerfectRecovery);
		
		JButton btnSingstar = new JButton(Messages.getString("RemoveSingstar.Title")); //$NON-NLS-1$
		btnSingstar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveSingstar.show(ftp);
			}
		});
		
		JButton btnPsnComment = new JButton(Messages.getString("RtmDialog.PsnComment")); //$NON-NLS-1$
		btnPsnComment.setEnabled(false);
		GridBagConstraints gbc_btnPsnComment = new GridBagConstraints();
		gbc_btnPsnComment.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPsnComment.insets = new Insets(0, 0, 5, 0);
		gbc_btnPsnComment.gridx = 0;
		gbc_btnPsnComment.gridy = 3;
		panel_1.add(btnPsnComment, gbc_btnPsnComment);
		GridBagConstraints gbc_btnSingstar = new GridBagConstraints();
		gbc_btnSingstar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSingstar.insets = new Insets(0, 0, 5, 0);
		gbc_btnSingstar.gridx = 0;
		gbc_btnSingstar.gridy = 4;
		panel_1.add(btnSingstar, gbc_btnSingstar);
		GridBagConstraints gbc_btnReboot = new GridBagConstraints();
		gbc_btnReboot.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReboot.insets = new Insets(0, 0, 5, 0);
		gbc_btnReboot.anchor = GridBagConstraints.NORTH;
		gbc_btnReboot.gridx = 0;
		gbc_btnReboot.gridy = 5;
		panel_1.add(btnReboot, gbc_btnReboot);

		JButton btnDisconnect = new JButton(
				Messages.getString((ftp.isConnected()?"RtmDialog.Disconnect":"Window.Close")));
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(ftp.isConnected())
						ftp.logout();
					ftp.disconnect(true);
				} catch (IllegalStateException | IOException
						| FTPIllegalReplyException | FTPException e1) {
				} finally {
					System.exit(0);
				}
			}
		});
		GridBagConstraints gbc_btnDisconnect = new GridBagConstraints();
		gbc_btnDisconnect.insets = new Insets(0, 0, 5, 0);
		gbc_btnDisconnect.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDisconnect.anchor = GridBagConstraints.NORTH;
		gbc_btnDisconnect.gridx = 0;
		gbc_btnDisconnect.gridy = 6;
		panel_1.add(btnDisconnect, gbc_btnDisconnect);

		JButton btnAbout = new JButton(Messages.getString("About.Title"));
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About.run();
			}
		});
		GridBagConstraints gbc_btnAbout = new GridBagConstraints();
		gbc_btnAbout.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAbout.gridx = 0;
		gbc_btnAbout.gridy = 7;
		panel_1.add(btnAbout, gbc_btnAbout);
		
		if(Costants.MODS_SUPPORTED.length>0) {
			listCustomizations.setSelectedIndex(0);
		}
	}

	private void mkdirs() {
		File newFold;
		for(int i = 0; i < Costants.PAIR.length; i++) {
			newFold = new File(data, Costants.PAIR[i][0]);
			if(!newFold.exists())
				newFold.mkdir();
		}
	}

	protected void runStockInstall() {
		setWindowStatus("Running stock job");
		String[] job = Costants.STOCKABLE;
		for (int i = 0; i < job.length; i++) {
			File current = new File(data, job[i]);
			if(Arrays.asList(current.list()).contains("Stock"))
				installComponent(job[i], "Stock");
		}
	}
	
	public void installComponent(String selectedValue, String selectedElement) {
		String installDir = Costants.INSTALL_PATH.get(selectedValue);
		if(installDir==null)
			return;
		String[] navig = installDir.split("\\/");
		try {
			ftp.setPassive(false);
			System.out.println("Passvive: " + ftp.isPassive());
			ftp.getConnector().setReadTimeout(500);
			lblStatus.setText("Navigating...");
			while(!ftp.currentDirectory().equalsIgnoreCase("/")) {
				ftp.changeDirectoryUp();
				if(Costants.DEBUG)
					System.out.println("Current ftp dir: " + ftp.currentDirectory());
			}
			if((navig[1].equalsIgnoreCase("nand"))||(navig[1].equalsIgnoreCase("nor"))) {
				if(Arrays.asList(ftp.listNames()).contains("dev_blind"))
					ftp.changeDirectory("dev_blind");
				else if(Arrays.asList(ftp.listNames()).contains("dev_rebug"))
					ftp.changeDirectory("dev_rebug");
				for (int i = 2; i < navig.length; i++) {
					ftp.changeDirectory(navig[i]);
					System.out.println("Current ftp dir: " + ftp.currentDirectory());
				}
			} else {
				for (int i = 1; i < navig.length; i++) {
					ftp.changeDirectory(navig[i]);
					System.out.println("Current ftp dir: " + ftp.currentDirectory());
				}
			}

			File custom = new File(new File(data, selectedValue), selectedElement);
			if(!custom.exists())
				return;
			if(Costants.DEBUG) {
				System.out.println("Current ftp dir: " + ftp.currentDirectory());
				System.out.println("Current loc dir: " + custom.getAbsolutePath());
			}
			setWindowStatus("Uploading");
			File[] data = custom.listFiles();
			for (int i = 0; i < data.length; i++) {
				if(!Arrays.asList(ftp.listNames()).contains(data[i].getName()+".stock")) {
					if(Arrays.asList(ftp.listNames()).contains(data[i].getName())) {
						setWindowStatus("Running backup");
						File tmp = new File(data[i].getName()+".stock");
						tmp.createNewFile();
						ftp.download(data[i].getName(), tmp);
						ftp.upload(tmp);
						tmp.delete();
					}
				}
				setWindowStatus("Uploading");
				ftp.upload(data[i]);
			}
			setWindowStatus("Idle");
		} catch (IllegalStateException | IOException | FTPIllegalReplyException
				| FTPException | FTPDataTransferException | FTPAbortedException | FTPListParseException e) {
			if((e.getMessage().equals("FTPConnection closed"))||(e.getMessage().contains("code=421"))) {
				String cHost = ftp.getHost();
				ftp = new FTPClient();
				try {
					ftp.connect(cHost);
					ftp.login("anonymus", "anonymus");
					if(ftp.isConnected()) {
						installComponent(selectedValue, selectedElement);
					}
				} catch (IllegalStateException | IOException
						| FTPIllegalReplyException | FTPException e1) {
					e1.printStackTrace();
				}
			} else {
				e.printStackTrace();
			}
		}
	}

	protected JButton getBtnInstall() {
		return btnInstall;
	}
	protected JLabel getLblStatus() {
		return lblStatus;
	}
	
	protected void setWindowStatus(String status) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				lblStatus.setText(status);
			}
		});
	}
}
