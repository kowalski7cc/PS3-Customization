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
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class Splash extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = -459660910713560886L;
	private JPanel contentPane;
	protected float fade = 0.0f;
	
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		Splash frame = new Splash();
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		frame.fadeIn();
//
//		frame.fadeOut();
//	}

	/**
	 * Create the frame.
	 */
	public Splash() {
		setName("Starting up...");
		setType(Type.UTILITY);
//		setWindowPosition(this);
		setBounds(100, 100, 450, 275);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(53, 100, 183));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(15, 15));
		
		JLabel lblLogo_1 = new JLabel("\r\n");
		lblLogo_1.setIcon(new ImageIcon(Splash.class.getResource("/com/xspacesoft/kowalski7cc/rtmmodding/resources/PS3_Custom_Win10.png")));
		lblLogo_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLogo_1, BorderLayout.CENTER);
		
		JLabel lblTitle_1 = new JLabel("Customization");
		lblTitle_1.setVerticalAlignment(SwingConstants.TOP);
		lblTitle_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblTitle_1.setForeground(Color.WHITE);
		lblTitle_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle_1, BorderLayout.SOUTH);
		
		JLabel lblOther = new JLabel(Costants.DEBUG?"XSpaceSoft Confidential - Kowalski7cc":"XSpaceSoft(C) 2008-" + Calendar.getInstance().get(Calendar.YEAR) + " - By Kowalski7cc");
		lblOther.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOther.setForeground(Color.WHITE);
		lblOther.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblOther, BorderLayout.NORTH);
	}
	
	@SuppressWarnings("unused")
	private void fadeIn() {
		while(fade<1) {
			fade = fade + 0.05f;
			if(fade>1)
				fade = 1;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// IDGAF
			}
			setOpacity(fade);
		}
	}
	
	@SuppressWarnings("unused")
	private void fadeOut() {
		while(fade>0) {
			fade = fade - 0.1f;
			if(fade<0)
				fade = 0;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// IDGAF
			}
			setOpacity(fade);
		}
	}

	@Override
	public void setVisible(boolean b) {
		if(b) {
			setOpacity(0);
			super.setVisible(b);
			Utils.setWindowPosition(this);
		} else {
			super.setVisible(b);
		}
	}
	

}
