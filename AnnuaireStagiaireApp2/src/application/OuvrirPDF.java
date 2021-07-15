package application;

import java.io.File;
import java.io.IOException;

public class OuvrirPDF {
	public void open(String filename) {
		File file = new File(filename);
		try {
			Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
			p.waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
}