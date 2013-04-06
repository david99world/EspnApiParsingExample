package org.espn.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class EspnParser {

	/**
	 * @author David Gray
	 */
	public static void main(String[] args) {
		try {
			String apiKey = "apiKey";
			String apiUrl = "http://api.espn.com/v1/sports/soccer/eng.1/athletes?apikey=";
			String json = readUrl(apiUrl + apiKey);
			JsonArray sports = new JsonParser().parse(json).getAsJsonObject()
					.get("sports").getAsJsonArray();
			JsonElement league = sports.get(0);
			JsonArray athletes = league.getAsJsonObject().get("leagues")
					.getAsJsonArray().get(0).getAsJsonObject().get("athletes")
					.getAsJsonArray();
			for (final JsonElement athlete : athletes) {
				System.out.println(athlete.getAsJsonObject().get("fullName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

}
