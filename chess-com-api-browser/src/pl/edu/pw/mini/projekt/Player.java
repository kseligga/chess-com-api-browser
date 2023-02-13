package pl.edu.pw.mini.projekt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;

import pl.edu.pw.mini.projekt.stats.Stats;

public class Player {
	protected URL id;
	protected URL url;
	protected String username;
	protected long player_id;
	protected String title;
	protected String status;
	protected String name;
	protected URL avatar;
	protected String location;
	protected URL country;
	protected long joined;
	protected long last_online;
	protected int followers;
	protected boolean is_streamer;
	protected URL twitch_url;
	protected boolean verified;

	protected Stats stats;

	public Stats getStats() {
		return stats;
	}

	public boolean onlineStatus() {

		boolean is_online = false;
		long unixTime = System.currentTimeMillis();

		if (this.last_online + 3600000 > unixTime) {
			is_online = true;
		}

		return is_online;
	}

	public Player(String username) throws MalformedURLException {
		this.username = username;
		URL apiUrl = new URL("https://api.chess.com/pub/player/" + username);

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(apiUrl.openStream()));
			String inputLine;
			inputLine = in.readLine();
			in.close();

			Gson g = new Gson();
			Player player = g.fromJson(inputLine, Player.class);

			id = player.id;
			url = player.url;
			player_id = player.player_id;
			title = player.title;
			status = player.status;
			name = player.name;
			avatar = player.avatar;
			location = player.location;
			country = player.country;
			joined = player.joined;
			last_online = player.last_online * 1000;
			followers = player.followers;
			is_streamer = player.is_streamer;
			twitch_url = player.twitch_url;
			verified = player.verified;

			stats = new Stats(username);

		} catch (Exception e) {
			throw new MalformedURLException();
		}

	}

	public long getLast_online() {
		return last_online;
	}

	public String getUsername() {
		return username;
	}

	public String getTitle() {
		return title;
	}

	public String getName() {
		return name;
	}

	public URL getAvatar() {
		return avatar;
	}

	public String getLocation() {
		return location;
	}

	public long getJoined() {
		return joined;
	}

	public boolean isVerified() {
		return verified;
	}

	public int getFollowers() {
		return followers;
	}

}
