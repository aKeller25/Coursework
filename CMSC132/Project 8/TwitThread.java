package twitbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TwitThread implements Runnable {

	private Twitbook twitThread;
	private String urlToUse;

	public TwitThread(Twitbook twitBook, String urlIn) {
		twitThread = twitBook;
		urlToUse = urlIn;
	}

	public void run() {
		try {
			URL url = new URL(urlToUse);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			// Whenever you call reader.readLine(); it will read the line and
			// go to the next line

			String currentLine = "";
			while (currentLine != null) {
				if (currentLine.contains("<tr>")
						&& !currentLine.contains("Operation")) {
					String html = currentLine;
					html = html.replace(" ", "");
					String instruction = html.substring(
							html.indexOf("<td>") + 4, html.indexOf("</td>"));
					String person1 = html.substring(
							html.indexOf(instruction) + instruction.length()
									+ 9,
							html.indexOf("</td>", html.indexOf(instruction)
									+ instruction.length() + 9));

					if (instruction.equals("addfriend")) {
						String person2 = html.substring(html.indexOf(person1)
								+ person1.length() + 9, html.length() - 10);

						synchronized (twitThread) {
							twitThread.friend(person1, person2);
						}

					} else { // We want to add a person
						synchronized (twitThread) {
							twitThread.addUser(person1);
						}
					}

				}
				currentLine = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
