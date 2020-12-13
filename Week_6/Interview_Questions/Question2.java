/*
*  Hashing with wrong hashCode() or equals().
*  https://stackoverflow.com/questions/2265503/why-do-i-need-to-override-the-equals-and-hashcode-methods-in-java
*  https://github.com/mincong-h/java-examples/issues/6
*
*   Suppose that you implement a data type OlympicAthlete for use in a java.util.HashMap
*
*   1Q. Describe what happens if you override hashCode() but not equals().
*   The same object will be hash into one bucket but we can't find the match through search.
*   Collision happens. Two non-equal objects have the same hash code.
*   This will show down the performance of the hash table.
*   Since multiple objects have the same hash code, java need to iterate them the find the right one.
*   Time complexity should be O(n), where n is the number of objects having the same hash code.
*
*
*   2Q. Describe what happens if you override equals() but not hashCode().
*   2A. if we don't override hashcode two instances that evaluate to equal would have different hashcode
*     if we use our instance as the key for a hashmap, we will run into a problem where two instance
*     which evaluate as equal will be store with different values
*  RULE 1: Equal instances always produce the same hashcode
*  RULE 2: Equal hashcode do not mean equal instances
 *
 *  3Q. Describe what happens if you override hashCode() but implement public boolean equals(OlympicAthlete that)
 *  instead of public boolean equals(Object that).
 *
 * Error: The method equals(Athlete) of type Athlete must override or implement a supertype method.
 *
 *
 */

public class Question2 {
}
