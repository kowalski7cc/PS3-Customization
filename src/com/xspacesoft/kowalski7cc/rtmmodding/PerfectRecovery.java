package com.xspacesoft.kowalski7cc.rtmmodding;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

public class PerfectRecovery extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1095658437524837117L;
	private JPanel contentPane;
	protected JSONObject config;
	protected File load;
	protected String data;
	protected boolean error = false;
	private JList<String> list;
	private RtmDialog rtm;

	/**
	 * Launch the application.
	 */
	public static void main(RtmDialog rtm) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfectRecovery frame = new PerfectRecovery(rtm);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PerfectRecovery(RtmDialog rtm) {
		this.rtm = rtm;
		setTitle(Messages.getString("PerfectRecovery.Title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnRun = new JButton(Messages.getString("PerfectRecovery.Run")); //$NON-NLS-1$
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				installCustomization();
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
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		list = new JList<String>();
		loadFile();
		if(!error) {
			readFile();
			if(!error) {
				String[] reco = getRecoveries();
				list.setListData(reco);
				if(reco.length>0)
					list.setSelectedIndex(0);
			}
		}
		scrollPane.setViewportView(list);
		
		JLabel lblDescription = new JLabel(Messages.getString("PerfectRecovery.Description"));
		scrollPane.setColumnHeaderView(lblDescription);
	}

	protected void installCustomization() {
		Map<String, String> batch = new HashMap<String, String>();
		String[] step1 = Costants.STOCKABLE;
		boolean customs = true;
		String[] packages = null;
		boolean packs = true;
		try {
			JSONArray confs = config.getJSONArray("recoveries");
			JSONObject obj = confs.getJSONObject(list.getSelectedIndex());
			obj = obj.getJSONObject("customizations");
			for (int i = 0; i < step1.length; i++) {
				try {
					batch.put(step1[i], obj.getString(step1[i]));
				} catch (JSONException e1) {
					batch.put(step1[i], null);
					e1.printStackTrace();
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			customs = false;
		}
		
		try {
			JSONArray confs = config.getJSONArray("recoveries");
			JSONObject obj = confs.getJSONObject(list.getSelectedIndex());
			JSONArray pkgs = obj.getJSONArray("packages");
			packages = new String[pkgs.length()];
			for (int i = 0; i < pkgs.length(); i++) {
				packages[i] = pkgs.getJSONObject(i).getString("package");
			}
			
		} catch (JSONException e) {
			// No plugins?
			packs = false;
		}
		
		if(customs) {
			for(String string : Costants.STOCKABLE) {
				if(batch.get(string)!=null)
					rtm.installComponent(string, batch.get(string));
			}
		}
		
		if(packs) {
			for(String string : packages) {
				rtm.installComponent("Packages", string);
			}
		}
		setVisible(false);
	}

	private void loadFile() {
		load = new File(new File("data"), "PerfectRecovery.json");
		if(!load.exists()) {
			try {
				load.createNewFile();
			} catch (IOException e) {
				if(Costants.DEBUG)
					e.printStackTrace();
			} finally {
				error = true;
			}
		}
	}
	
	private void readFile() {
		data = "";
		try (Scanner fi = new Scanner(load)) {
			while(fi.hasNext()) {
				data = data + " " + fi.next();
			}
		} catch (FileNotFoundException e) {
			if(Costants.DEBUG)
				e.printStackTrace();
			error = true;
		}
	}

	private String[] getRecoveries() {
		try {
			config = new JSONObject(data);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			JSONArray entries = config.getJSONArray("recoveries");
			String[] ent = new String[entries.length()];
			for (int i = 0; i < entries.length(); i++) {
				ent[i] = entries.getJSONObject(i).getString("name");
			}
			return ent;
		} catch (JSONException e) {
			return new String[] {};
		}
	}

}
