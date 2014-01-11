import java.io.Serializable;
import java.util.Scanner;
import java.util.Hashtable;
import java.io.*;
import java.awt.Point;

/**
 * This creates the mobile network class. It first checks the program to see if
 * a network.obj object is present. <br>
 * If the object is present, it saves its contents to an empty hash table. <br>
 * If the file does not exist, however, it creates an empty hash table and when
 * the user chooses the quit function, it saves the hash table to network.obj. <br>
 * This hash table contains all the user profiles <br>
 * 
 * @author Zeb Aslam 108041523
 * 
 */

public class MobileNetwork implements Serializable {
	/**
	 * This private method is used to print the menu for the user
	 */
	private static void printMenu() {
		System.out.println("I) Insert a new user profile into the table.");
		System.out.println("D) Delete a user profile from the table.");
		System.out
				.println("U) Update the information for a given profile in the table.");
		System.out
				.println("S) Search to see if two users are within each other's mobile social network.");
		System.out.println("Q) Quit Program");
		System.out.println("Enter your choice");
	}

	/**
	 * This method is used to convert a string to a point
	 * 
	 * @param z
	 *            Takes in a string that will be split by it's space and stored
	 *            in an array. The [0] value is x, the [1] value is y
	 * @return a point object representing a location
	 */
	private static Point convertToPoint(String z) {
		// System.out.println(z);
		String[] split = z.split(" ");
		int x = Integer.parseInt(split[0]);
		// System.out.println(x);
		int y = Integer.parseInt(split[1]);
		// System.out.println(y);
		Point point = new Point(x, y);
		// System.out.println(point);
		return point;

	}
/**
 * This class is used to create a user's profile from user input and store/read hashtables in/from a network.obj file
 */
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		File contents = new File("network.obj");
		boolean exists = contents.exists();
		Hashtable network = null;
		// if file doesnt exist create empty hashtable
		if (!exists) {
			network = new Hashtable();
		}
		// if it does exist, reads from file and stores in hashtable
		else if (exists == true) {
			FileInputStream file = new FileInputStream(contents);
			ObjectInputStream inStream = new ObjectInputStream(file);
			network = (Hashtable) inStream.readObject();
			inStream.close();
		}
		// used in the while loop to display menu and get user's choice after
		// every round
		boolean quit = false;
		while (quit != true) {
			printMenu();
			String letter = input.next().toUpperCase();
			char choice = letter.charAt(0);
			switch (choice) {
			case 'I': {
				boolean ignoreEntry = true;
				System.out.println("Enter your first name");
				String name = input.next();
				System.out.println("Enter your last name");
				name += " " + input.next();
				String key = name.toUpperCase();
				System.out
						.println("Enter a coordinate pair seperated by a space [x y]");
				input.nextLine();
				String coordinate = input.nextLine();
				Point location = convertToPoint(coordinate);
				System.out.println("Enter the preferred network range");
				Scanner input2 = new Scanner(System.in);
				int range = input2.nextInt();

				if (location.x < -100 || location.x > 100 || location.y < -100
						|| location.y > 100) {
					System.out.println("Error: User coordinates not valid, entry ignored");
					quit = false;
					break;
				}

				if (range < 1) {
					System.out.println("Error: Network Range cannot be less than 1");
					quit = false;
					break;
				}

				if (network.containsKey(key)) {
					System.out.println("Error: Duplicate Entry, entry ignored");
					quit = false;
					break;
				}

				Profile newEntry = new Profile();
				newEntry.setFullName(name);
				newEntry.setLocation(location);
				newEntry.setNetworkRange(range);
				network.put(key, newEntry);

				break;

			}
			case 'Q': {
				//saves nonempty hashtable to a network.obj file
				if (network != null) {
					FileOutputStream out = new FileOutputStream("network.obj");
					ObjectOutputStream outStream = new ObjectOutputStream(out);
					outStream.writeObject(network);
					outStream.close();
				}
				System.out.println("Exiting Program...");
				quit = true;
				System.exit(0);
				break;
			}
			default: {
				System.out.println("Error: Invalid Menu Option");
				System.out.println("");
				quit = false;
				break;
			}
			case 'D': {
				System.out.println("Enter your first name");
				String name = input.next();
				System.out.println("Enter your last name");
				name += " " + input.next();
				String name2 = name.toUpperCase();
				if (!network.containsKey(name2)) {
					System.out.println(name + " not found in table!");
					System.out.println("");
					quit = false;
					break;
				} else {
					network.remove(name2);
					System.out.println(name + "has been removed from the table");
					System.out.println("");
					quit = false;
					break;
				}

			}

			case 'U': {
				boolean ignoreEntry = false;
				System.out.println("Enter your first name");
				String name = input.next();
				System.out.println("Enter your last name");
				name += " " + input.next();
				String key = name.toUpperCase();
				if (!network.containsKey(key)) {
					System.out.println(name + " not found in table!");
					System.out.println("");
					quit = false;
					break;
				} else {
					System.out.println("Enter a coordinate pair seperated by a space [x y]");
					input.nextLine();
					String coordinate = input.nextLine();
					Point location = convertToPoint(coordinate);
					System.out.println("Enter the preferred network range");
					Scanner input2 = new Scanner(System.in);
					int range = input2.nextInt();

					if (location.x < -100 || location.x > 100
							|| location.y < -100 || location.y > 100) {
						ignoreEntry = true;
						System.out.println("Error: User coordinates not valid, entry ignored");
						System.out.println("");
						quit = false;
						break;
					}
					if (range < 1) {
						ignoreEntry = true;
						System.out.println("Error: Network Range cannot be less than 1");
						System.out.println("");
						quit = false;
						break;
					}
					if (ignoreEntry == false) {
						Profile reset = (Profile) network.get(key);
						reset.setLocation(location);
						reset.setNetworkRange(range);
						network.put(key, reset);
						System.out.println("Updated successfully");
						System.out.println(" ");
						quit = false;
						break;
					}
				}
			}
			case 'S': {
				System.out.println("Enter one's first name");
				String name = input.next();
				System.out.println("Enter one's last name");
				name += " " + input.next();
				String key = name.toUpperCase();
				System.out.println("Enter two's first name");
				String name2 = input.next();
				System.out.println("Enter two's last name");
				name2 += " " + input.next();
				String key2 = name2.toUpperCase();

				if (!network.containsKey(key)) {

					System.out.println(name + " cannnot be found in table!");
					System.out.println("");
					quit = false;
					break;
				}

				if (!network.containsKey(key2)) {
					System.out.println(name2 + " cannnot be found in table!");
					System.out.println("");
					quit = false;
					break;
				}

				Profile person1 = (Profile) network.get(key);
				Profile person2 = (Profile) network.get(key2);
				Point p1 = person1.getLocation();
				Point p2 = person2.getLocation();
				int distance = (int) p1.distance(p2);
				int d1 = person1.getNetworkRange();
				int d2 = person2.getNetworkRange();
				if (distance < d1 && distance < d2) {
					System.out.println(name + " and " + name2+ " are within each other's social network.");
					System.out.println(" ");
				} else {
					System.out.println(name + " and " + name2+ " are not within each other's social network.");
					System.out.println(" ");
				}
				quit = false;
				break;
			}

			}
		}
	}

}
