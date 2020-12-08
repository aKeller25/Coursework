package twitbook;

import java.util.Collection;
import java.util.HashSet;

/*Name: Alexander Keller
 * Class: CMSC132
 * TA: Qian (10 am)
 * Section: 303
 * Time last edited: 2:00 PM 5/11/2015
 */

/**
 * This class was made to hold a Twitbook. This class can be used to add a user
 * to Twitbook, get a collection of all users on Twitbook, create a friendship
 * between two users, get a collection of all of the friends of a specific
 * user, get rid of a friendship between two users, and get a collection of
 * the friends of the friends of the user (1 friend detached)
 */

public class Twitbook {

	private Graph<String> twitGraph = new Graph<String>();

	/**
	 * @param name
	 * 
	 * Takes in the parameter, the user's name, and adds the user to the
	 * Twitbook graph only if the user hasn't already been added.
	 * 
	 * @return true if the user was added, false if not
	 */
	public boolean addUser(String name) {
		boolean added = false;
		if (!twitGraph.isVertex(name)) {
			twitGraph.addVertex(name);
			added = true;
		}

		return added;
	}

	/** 
	 * @return all the users (vertices) in the Twitbook graph
	 */
	public Collection<String> getAllUsers() {
		return twitGraph.getVertices();
	}
	/**
	 * @param user1 
	 * @param user2
	 * 
	 * Takes in user1 and user2 and if they're not already in the Twitbook
	 * graph, then it adds them. After that it creates a mutual friendship 
	 * (edge, with a cost of 1), if they don't already have one between the
	 * two. 
	 * 
	 * @return true if a friendship was created, false if not 
	 */
	public boolean friend(String user1, String user2) {
		boolean friended = false;
		if (!twitGraph.isVertex(user1)) {
			addUser(user1);
		}
		if (!twitGraph.isVertex(user2)) {
			addUser(user2);
		}
		if (user1.compareTo(user2) != 0
				&& !twitGraph.getNeighbors(user1).contains(user2)) {
			twitGraph.addEdge(user1, user2, 1); // Sets the graph's costs as 1
			twitGraph.addEdge(user2, user1, 1);
			friended = true;
		}

		return friended;
	}

	/**
	 * @param user
	 * @return (If the user exists (is in the graph)) a collection of all of
	 * the friends of the user
	 */
	public Collection<String> getFriends(String user) {
		Collection<String> friends = new HashSet<String>();
		if (twitGraph.isVertex(user)) {
			friends = twitGraph.getNeighbors(user);
		}

		return friends;
	}

	/**
	 * @param user1
	 * @param user2
	 * 
	 * Terminates the friendship (edge) between the two users
	 * 
	 * @return true if the users have mutually unfriended each other, false if
	 * not 
	 */
	public boolean unfriend(String user1, String user2) {
		boolean unfriended = false;
		if (twitGraph.isVertex(user1) && twitGraph.isVertex(user2)
				&& twitGraph.getNeighbors(user1).contains(user2)) {
			twitGraph.removeEdge(user1, user2);
			twitGraph.removeEdge(user2, user1);
			unfriended = true;
		}

		return unfriended;
	}

	/**
	 * @param user
	 * @return an array of user's friends of his friends, excluding the
	 * user's friends
	 */
	public Collection<String> peopleYouMayKnow(String user) {
		Collection<String> peopleYouMayKnow = new HashSet<String>();
		for (String friends : getFriends(user)) {
			peopleYouMayKnow.addAll(getFriends(friends));
		}
		peopleYouMayKnow.remove(user); // Remove the user
		// There might be a better way to do this
		peopleYouMayKnow.removeAll(getFriends(user));
		// Remove friends of the user

		return peopleYouMayKnow;
	}

	/**
	 * @param urls
	 * 
	 * Multithreaded-ly reads the table on the web-page that the url refers
	 * to, based on the html code. After it reads the html code, it parses
	 * through it to get the instructions and the users / new users involved,
	 * and uses synchronization while operating with the instructions and
	 * users given.
	 */
	public void readFriendData(String[] urls) {
		Thread[] friendsThread = new Thread[urls.length];
		int i = 0;

		for (i = 0; i < friendsThread.length; i++)
			friendsThread[i] = new Thread(new TwitThread(this, urls[i]));

		for (Thread t : friendsThread) {
			t.start();
		}

		for (Thread t : friendsThread) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
