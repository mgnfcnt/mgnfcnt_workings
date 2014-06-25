package snacks;

import java.sql.Timestamp;

/**
 * This class created to learn comparasion on java
 *
 * @author Mehmet BEBEK
 * @since 26.6.2014
 */
public class Comparasion {

    private static void stringCompare() {
        String str1 = "12";
        String str2 = "50";
        String str3 = "123";
        //At integer 123 is greater than all but at string comparasion it compares char by char.
        System.out.println(str1.compareTo(str3)); //-1
        System.out.println(str2.compareTo(str3)); //4 -> 50 is greater than 123 at string
        System.out.println(str3.compareTo(str1)); //1
    }

    private static boolean isDateBetweenRange(Timestamp time1, Timestamp time2, Timestamp time3) {
        return time1.compareTo(time3) * time3.compareTo(time2) > 0;
        //return time3.after(time1) && time3.before(time2); //alternative
    }
}
