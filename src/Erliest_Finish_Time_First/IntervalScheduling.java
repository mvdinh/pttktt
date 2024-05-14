package Erliest_Finish_Time_First;

import java.util.Arrays;
import java.util.LinkedList;

public class IntervalScheduling {
    public static void findOptimalJobSchedule(Interval[] intervals) {
        System.out.println("Input Intervals: \t" + Arrays.toString(intervals));
        Arrays.sort(intervals); // Sort intervals by finish time

        LinkedList<Interval> intervalsSelected = new LinkedList<>();
        intervalsSelected.add(intervals[0]); // add 1st interval
        Interval lastIntervalAdded = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].time_start >= lastIntervalAdded.time_finish) { // check if interval is compatible
                intervalsSelected.add(intervals[i]);
                lastIntervalAdded = intervals[i]; // update for the interval that was just added
            }
        }

        System.out.println("\nSelected " + intervalsSelected.size() + " Intervals: ");
        for (Interval interval : intervalsSelected) {
            System.out.println(interval);
        }
    }

    public static void main(String[] args) {
        Interval[] intervals = {
            new Interval(0, 6),
            new Interval(1, 4),
            new Interval(3, 5),
            new Interval(3, 8 ),
            new Interval(4, 7),
            new Interval(5, 9),
            new Interval(6, 10),
            new Interval(8, 11),
        };
        IntervalScheduling.findOptimalJobSchedule(intervals);
    }
}
