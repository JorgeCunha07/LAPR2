package app.domain.model;

import app.domain.model.EntryRecord;
import app.domain.model.LeavingRecord;
import app.domain.shared.Constants;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;


/**
 * It calculates the maximum sum contiguous sublist of the input list
 */
public class PerformanceCenter {


    /**
     * This function calculates the difference between the number of people who entered the vaccination center and the
     * number of people who left the vaccination center in a given time interval
     *
     * @param entryRecords   an ArrayList of EntryRecord objects
     * @param leavingRecords ArrayList of LeavingRecord objects
     * @param timeInterval   the time interval in minutes
     * @return The difference between the number of people who entered the vaccination center and the number of people who
     * left the vaccination center.
     */
    public int[] calculateDifferencesBetweenEntryAndLeavingRecordsOfVaccinationCenter(ArrayList<EntryRecord> entryRecords, ArrayList<LeavingRecord> leavingRecords, int timeInterval) {

        int[] entryRecordByTime = recordByTimeIntervalEntry(entryRecords, timeInterval);

        int[] leavingRecordByTime = recordByTimeIntervalLeaving(leavingRecords, timeInterval);
        int[] differences = new int[leavingRecordByTime.length];

        for (int i = 0; i < differences.length; i++) {
            differences[i] = entryRecordByTime[i] - leavingRecordByTime[i];
        }

        return differences;
    }


    /**
     * This function takes a LocalDateTime object and returns the time in milliseconds
     *
     * @param entryLeave The time of entry or leave
     * @return The time in milliseconds.
     */
    private long getTimeMS(LocalDateTime entryLeave) {
        long timeMS = entryLeave.getHour() * Constants.HOUR_TO_MS +
                entryLeave.getMinute() * Constants.MINUTE_TO_MS +
                entryLeave.getSecond();

        return timeMS;
    }


    /**
     * This function takes an ArrayList of EntryRecord objects and an integer timeInterval as parameters. It returns an
     * array of integers where each element represents the number of EntryRecord objects that occurred within the time
     * interval
     *
     * @param entryRecords ArrayList of EntryRecord objects
     * @param timeInterval the time interval in minutes that you want to group the records by.
     * @return An array of integers.
     */
    private int[] recordByTimeIntervalEntry(ArrayList<EntryRecord> entryRecords, int timeInterval) {
        int[] recordsByTimeInterval = new int[720 / timeInterval];
        Time[] hoursInterval = new Time[720 / timeInterval];
        long millisecondsSum = 0;


        for (int i = 0; i < hoursInterval.length; i++) {
            Time time = new Time(8, 0, 0);
            millisecondsSum = millisecondsSum + (timeInterval * 60000L);
            hoursInterval[i] = time;
            time.setTime(time.getTime() + millisecondsSum);
        }

        for (EntryRecord record : entryRecords) {
            Time actualTime = new Time(getTimeMS(record.getEntryDateTime()));
            for (int i = 0; i < hoursInterval.length; i++) {
                if ((actualTime.getTime()) <= hoursInterval[i].getTime()) {
                    recordsByTimeInterval[i]++;
                    break;
                }
            }
        }

        return recordsByTimeInterval;
    }

    /**
     * This function takes an ArrayList of LeavingRecord objects and an integer timeInterval as parameters and returns an
     * array of integers
     *
     * @param entryRecords ArrayList of EntryRecord objects
     * @param timeInterval the time interval in minutes that you want to divide the day into.
     * @return An array of integers.
     */
    private int[] recordByTimeIntervalLeaving(ArrayList<LeavingRecord> entryRecords, int timeInterval) {
        int[] recordsByTimeInterval = new int[720 / timeInterval];
        Time[] hoursInterval = new Time[720 / timeInterval];
        long millisecondsSum = 0;


        for (int i = 0; i < hoursInterval.length; i++) {
            Time time = new Time(8, 0, 0);
            millisecondsSum = millisecondsSum + (timeInterval * 60000L);
            hoursInterval[i] = time;
            time.setTime(time.getTime() + millisecondsSum);
        }

        for (LeavingRecord record : entryRecords) {
            Time actualTime = new Time(getTimeMS(record.getLeavingDateTime()));
            for (int i = 0; i < hoursInterval.length; i++) {
                if ((actualTime.getTime()) <= hoursInterval[i].getTime()) {
                    recordsByTimeInterval[i]++;
                    break;
                }
            }
        }

        return recordsByTimeInterval;
    }
    /**
     * It calculates the sum of all the elements in the array.
     *
     * @param maximumSum This is the array that contains the contiguous sublist that has the maximum sum.
     * @return The sum of the contiguous sublist.
     */
    public long calculateSumofContiguousSublist(int[] maximumSum) {
        int sum = 0;
        for (int i = 0; i < maximumSum.length; i++) {
            sum += maximumSum[i];
        }
        return sum;
    }


    /**
     * The function calculates the maximum sum contiguous sublist of the input list
     *
     * @param differences the array of differences between the prices of the stock on consecutive days
     * @return The maximum sum of a contiguous sublist.
     */
    public  int[] calculateMaximumSumContiguousSublist(int[] differences) {
        try(FileReader reader =  new FileReader("config.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            String algorithm = properties.getProperty("algorithm");

            if(algorithm.equals("1")) {
                return bruteForceAlgorithm(differences);
            } else if(algorithm.equals("2")) {
                return benchmarkAlgorithm(differences);
            } else
                throw new IllegalArgumentException("The algorithm you chose is not valid. Please check your configurations file.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * The function takes an array of integers and returns the subarray with the largest sum
     *
     * @param differences The array of differences between the two arrays.
     * @return The subarray with the maximum sum.
     */
    private static int[] bruteForceAlgorithm(int[] differences) {
        long beginning = System.nanoTime();

        if (differences.length <= 1) {
            return differences;
        }

        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;
        int start = 0, end = 0;
        int beg = 0;

        for (int i = 0; i < differences.length; i++)
        {
            maxEndingHere = maxEndingHere + differences[i];

            if (maxEndingHere < differences[i])
            {
                maxEndingHere = differences[i];
                beg = i;
            }

            if (maxSoFar < maxEndingHere)
            {
                maxSoFar = maxEndingHere;
                start = beg;
                end = i;
            }
        }

        System.out.println(System.nanoTime() - beginning);

        return Arrays.copyOfRange(differences, start, end + 1);

        //https://www.techiedelight.com/pt/print-continuous-subarray-with-maximum-sum/
    }

    /**
     * > The function takes an array of integers and returns the subarray with the largest sum
     *
     * @param seq the sequence of integers to be analyzed
     * @return The maximum subarray.
     */
    private static int[] benchmarkAlgorithm(final int[] seq) {
        long beginning = System.nanoTime();

        int maxSoFar = 0;
        int maxEndingHere = 0;
        int startMaxSoFar = 0;
        int endMaxSoFar = 0;
        int startMaxEndingHere = 0;
        for (int i = 0; i < seq.length; ++i) {
            final int elem = seq[i];
            final int endMaxEndingHere = i + 1;
            if (maxEndingHere + elem < 0) {
                maxEndingHere = 0;
                startMaxEndingHere = i + 1;
            }
            else {
                maxEndingHere += elem;
            }
            if (maxSoFar < maxEndingHere) {
                maxSoFar = maxEndingHere;
                startMaxSoFar = startMaxEndingHere;
                endMaxSoFar = endMaxEndingHere;
            }
        }

        System.out.println(System.nanoTime() - beginning);

        return Arrays.copyOfRange(seq, startMaxSoFar, endMaxSoFar);
    }

    /**
     * It takes in a time interval in minutes and returns an array of Time objects that are spaced out by the time interval
     *
     * @param timeInterval the interval of time in minutes that you want to generate.
     * @return An array of Time objects.
     */
    public  Time[] generateTimeInterval(int timeInterval) {
        Time[] hoursInterval = new Time[720/timeInterval];
        long millisecondsSum = 0;

        for(int i = 0; i < hoursInterval.length; i++) {
            Time time = new Time(8,0,0);
            millisecondsSum = millisecondsSum + (timeInterval * 60000L);
            hoursInterval[i] = time;
    /**
     * It takes an array of differences and a time interval, and returns an array of strings containing the start and end
     * times of the maximum sum sublist
     *
     * @param differences an array of integers representing the differences between the number of people entering and
     * exiting the building at each hour.
     * @param timeInterval the number of hours in the day
     * @return The start and end of the maximum sum sublist.
     */
            time.setTime(time.getTime() + millisecondsSum);
        }

        return hoursInterval;
    /**
     * "The start and end of the maximum sum sublist is the start and end of the maximum sum sublist of the differences
     * between adjacent elements."
     *
     * The above function is a standard Kadane's algorithm
     *
     * @param differences the array of differences between the elements of the array.
     * @return The start and end indices of the maximum sum sublist.
     */
    }

    public String[] getHoursPeriodOfMaximumSumSublist(int[] differences, int timeInterval) {
        Time[] hoursInterval = generateTimeInterval(timeInterval);
        int[] startAndEnd = getStartAndEndOfMaximumSumSublist(differences);

        return new String[]{hoursInterval[startAndEnd[0]].toString(), hoursInterval[startAndEnd[1]].toString()};
    }

    private int[] getStartAndEndOfMaximumSumSublist(int[] differences) {
        int maxSoFar = 0;
        int maxEndingHere = 0;
        int startMaxSoFar = 0;
        int endMaxSoFar = 0;
        int startMaxEndingHere = 0;
        for (int i = 0; i < differences.length; ++i) {
            final int elem = differences[i];
            final int endMaxEndingHere = i + 1;
            if (maxEndingHere + elem < 0) {
                maxEndingHere = 0;
                startMaxEndingHere = i + 1;
            }
            else {
                maxEndingHere += elem;
            }
            if (maxSoFar < maxEndingHere) {
                maxSoFar = maxEndingHere;
                startMaxSoFar = startMaxEndingHere;
                endMaxSoFar = endMaxEndingHere;
            }
        }

        return new int[]{startMaxSoFar, endMaxSoFar-1};
    }
}
