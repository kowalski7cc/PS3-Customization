package test;

import it.sauronsoftware.ftp4j.FTPClient;

import java.util.Locale;
import java.util.Scanner;

import com.xspacesoft.kowalski7cc.rtmmodding.RebootAlert;
import com.xspacesoft.kowalski7cc.rtmmodding.RtmDialog;

public class Test {
	
	public static void main(String[] args) {
		System.out.println(Locale.getDefault().getLanguage());
		System.out.print("Inserd ip address: ");
		Scanner sc = new Scanner(System.in);
		if(sc.nextLine().equals("lol")) {
			RtmDialog.show(new FTPClient());
		} else {
			RebootAlert.show(sc.nextLine());
		}
		sc.close();
	}

}
