import java.awt.Point;
import java.io.Serializable;

/**
 * This creates the profile class which implements the serializable interface
 * This class creates the profile class  <br>
 * <br>
 * <b> Answers to question number 3 </b> <br>
 * <b>A) </b> When the table is automatically resized the its size is multiplied by 2 and adds one ex) newsize= oldsize*2 +1 <br>
 * <b>B) </b> Each entry contains a linked list of entries similar to chained hashing <br>
 * <b>C) </b> Serialization allows you to save the contents of the entire object into a file where the file itself represents the object. <br>
 * Text files usually only store the contents of the object and not the actual object itself. <br>
 * By reading from a serialized binary file, you can instantly recreate an object with its contents and state and use that object </br>
 * Often with text files, you must store the contents of the text file into a data structure or another object. <br>
 * Binary files are also far more efficient to process than text files.
 * @author Zeb Aslam ID# 108041523
 *
 */
public class Profile implements Serializable  {
//The full name of the person	
private String fullName;
//A point that represents location
private Point location;
//The integer value for the network range
private int networkRange;
/**
 * An empty constructor for the class
 */
public Profile(){
	
}
/**
 * This returns the value of the full name 
 * @return a String that represents the person's full name
 */
public String getFullName() {
	return fullName;
}
/**
 * Sets the full name of the person
 * @param fullName Takes in a string representing the person's name <br>
 * <b> Post Condition: </b> The person's full name has been capitalized to ignore case when searching for a person
 */
public void setFullName(String name) {
	name.toUpperCase();
	fullName= name;
}
/**
 * Returns the point value for location
 * @return	Returns the x,y coordinates for location
 */
public Point getLocation() {
	return location;
}
/**
 * <b> Precondition:</b> The x and y values for the point must be greater than or equal to -100 and less than or equal to 100 <br>
 * Sets the location for a user
 * @param location Takes in a point value to set as the location
 * 
 */
public void setLocation(Point location)  {
	this.location = location;
}
/**
 * Returns the value of the network range
 * @return an integer representing the network range
 */
public int getNetworkRange() {
	return networkRange;
}
/**
 * <b> Preconditon </b> The network range must be greater than 1 <br>
 * This method sets the network range 
 * @param networkRange takes in the network range
 * 
 */
public void setNetworkRange(int networkRange)  {
	this.networkRange = networkRange;
}

}
