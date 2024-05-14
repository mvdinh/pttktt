package HashMap;
import java.util.regex.*;
/**
 * Write a description of class VNDate here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class VNDate implements Comparable<VNDate>
{
    private static final int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    
    private final int day;     // day   (between 1 and DAYS[month]
    private final int month;   // month (between 1 and 12)
    private final int year;    // year

    /**
     * Constructor for objects of class VNDate
     */
    public VNDate(int day, int month, int year)
    {
        // initialise instance variables
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public VNDate(String date){
        // Try parsing date in different formats
        if (isValidFormat(date, "/")) {
            String[] fields = date.split("/");
            this.day = Integer.parseInt(fields[0]);
            this.month = Integer.parseInt(fields[1]);
            this.year = Integer.parseInt(fields[2]);
        } else if (isValidFormat(date, "-")) {
            String[] fields = date.split("-");
            this.day = Integer.parseInt(fields[0]);
            this.month = Integer.parseInt(fields[1]);
            this.year = Integer.parseInt(fields[2]);
        }else {
            String[] fields = date.split(" ");
            day = Integer.parseInt(fields[0]);
            month   = Integer.parseInt(fields[1]);
            year  = Integer.parseInt(fields[2]);
        }
        if (!isValid(day, month, year)) throw new IllegalArgumentException("Invalid date");
    }
    /**
     * Return the month.
     * @return the month (an integer between 1 and 12)
     */
    public int month() {
        return month;
    }

    /**
     * Returns the day.
     * @return the day (an integer between 1 and 31)
     */
    public int day() {
        return day;
    }

    /**
     * Returns the year.
     * @return the year
     */
    public int year() {
        return year;
    }
    private static boolean isValid(int d, int m, int y) {
        if (d < 1 || d > DAYS[m]) return false;
        if (m < 1 || m > 12)      return false;
        if (m == 2 && d == 29 && !isLeapYear(y)) return false;
        return true;
    }

    // is y a leap year?
    private static boolean isLeapYear(int y) {
        if (y % 400 == 0) return true;
        if (y % 100 == 0) return false;
        return y % 4 == 0;
    }
    /**
     * Returns the next date in the calendar.
     *
     * @return a date that represents the next day after this day
     */
    public VNDate next() {
        if (isValid(day+1, month, year))    return new VNDate(day+1, month, year);
        else if (isValid(1, month+1, year)) return new VNDate(1, month+1, year);
        else                                  return new VNDate(1, 1, year + 1);
    }
    /**
     * Compares two dates chronologically.
     *
     * @param  that the other date
     * @return {@code true} if this date is after that date; {@code false} otherwise
     */
    public boolean isAfter(VNDate that) {
        return compareTo(that) > 0;
    }

    /**
     * Compares two dates chronologically.
     *
     * @param  that the other date
     * @return {@code true} if this date is before that date; {@code false} otherwise
     */
    public boolean isBefore(VNDate that) {
        return compareTo(that) < 0;
    }
    /**
     * Compares two dates chronologically.
     *
     * @return the value {@code 0} if the argument date is equal to this date;
     *         a negative integer if this date is chronologically less than
     *         the argument date; and a positive ineger if this date is chronologically
     *         after the argument date
     */
    @Override
    public int compareTo(VNDate that) {
        if (this.year  < that.year)  return -1;
        if (this.year  > that.year)  return +1;
        if (this.month < that.month) return -1;
        if (this.month > that.month) return +1;
        if (this.day   < that.day)   return -1;
        if (this.day   > that.day)   return +1;
        return 0;
    }

    /**
     * Returns a string representation of this date.
     *
     * @return the string representation in the format MM/DD/YYYY
     */
    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    /**
     * Compares this date to the specified date.
     *
     * @param  other the other date
     * @return {@code true} if this date equals {@code other}; {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        VNDate that = (VNDate) other;
        return (this.month == that.month) && (this.day == that.day) && (this.year == that.year);
    }
    
    private boolean isValidFormat(String date, String separator) {
        String regex = "\\d{1,2}" + Pattern.quote(separator) + "\\d{1,2}" + Pattern.quote(separator) + "\\d{1,4}";
        return date.matches(regex);
    }

    /**
     * Returns an integer hash code for this date.
     *
     * @return an integer hash code for this date
     */
    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31*hash + month;
        hash = 31*hash + day;
        hash = 31*hash + year;
        return hash;
    }
    
    
}