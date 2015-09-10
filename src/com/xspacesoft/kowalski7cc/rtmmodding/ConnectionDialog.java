package com.xspacesoft.kowalski7cc.rtmmodding;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class ConnectionDialog extends JFrame {

	private static final long serialVersionUID = 5972815054388239511L;
	private JPanel contentPane;
	private JTextField txtConsoleaddress;
	private JPanel panel;
	private JButton btnConnect;
	private JButton btnCancel;
	private JLabel lblConnectError;
	private Preferences prefs;
//	private JPanel panel_1;
//	private JLabel lblConnessioniRecenti;
//	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		chackIntegrity();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectionDialog frame = new ConnectionDialog();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void chackIntegrity() {
		File file = new File("data");
		if(!file.exists()) {
			System.out.println(Messages.getString("ConnectionDialog.MissingFolderError")); //$NON-NLS-1$
			if(!file.mkdir())
				System.exit(1);
		}
	}

	/**
	 * Create the frame.
	 */
	public ConnectionDialog() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ConnectionDialog.class.getResource(
						"/com/xspacesoft/kowalski7cc/rtmmodding/resources/" + Utils.getIconSystem() + ".png")));  //$NON-NLS-1$
		try {
			javax.swing.UIManager.setLookAndFeel( javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e2) {
			// Not blocking
		}
		prefs = Preferences.userNodeForPackage(getClass());
		String recent = prefs.get(Costants.RECENT_CONNECTIONS_KEY, null);
		setResizable(false);
		setTitle(Messages.getString("ConnectionDialog.Title") + " - " + Costants.APPLICATION_NAME);  //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 131);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblWelcometext = new JLabel(Messages.getString("ConnectionDialog.Description")); //$NON-NLS-1$
		contentPane.add(lblWelcometext);
		
		lblConnectError = new JLabel(Messages.getString("ConnectionDialog.Error")); //$NON-NLS-1$
		lblConnectError.setForeground(Color.RED);
		lblConnectError.setVisible(false);
		contentPane.add(lblConnectError);
		
//		panel_1 = new JPanel();
//		panel_1.setMinimumSize(new Dimension(MAXIMIZED_HORIZ, 500));
//		contentPane.add(panel_1);
//		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
//		
//		lblConnessioniRecenti = new JLabel(Messages.getString("ConnectionDialog.lblConnessioniRecenti.text")); //$NON-NLS-1$
//		panel_1.add(lblConnessioniRecenti);
//		
//		list = new JList();
//		list.setMinimumSize(new Dimension(MAXIMIZED_HORIZ, 200));
//		panel_1.add(list);
		
		txtConsoleaddress = new JTextField();
		if(recent != null) {
			txtConsoleaddress.setText(recent);
		} else {
			txtConsoleaddress.setText("");
		}
		
		contentPane.add(txtConsoleaddress);
		txtConsoleaddress.setColumns(10);
		
		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnConnect = new JButton(Messages.getString("ConnectionDialog.Connect")); //$NON-NLS-1$
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblConnectError.setVisible(false);
				if(txtConsoleaddress.getText().isEmpty()) {
					return;
				} else if(txtConsoleaddress.getText().equals("HiddenTest")) {
					RtmDialog.show(new FTPClient());
					setVisible(false);
				}
				try {
					FTPClient ftp = new FTPClient();
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
	}

	protected JTextField getTxtConsoleaddress() {
		return txtConsoleaddress;
	}
	protected JLabel getLblErroreImpossibileEffettuare() {
		return lblConnectError;
	}
}
