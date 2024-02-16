package app.domain.model;

import java.util.Arrays;

public class Analysis {

    // A variable that stores the name of the vaccination center.
    private String VaccinationCenter;

    // A variable that stores the differences between the number of vaccines that have been ordered and the number of
    // vaccines that have been used.
    private int[] differences;

    // A variable that stores the maximum sum of the differences.
    private int[] maximumSum;

    private String[] hoursPeriod;
    private long sum;



    // This is a constructor that is used to create an object of the class Analysis.
    public Analysis(String vaccinationCenter, int[] differences, int[] maximumSum, String[] hoursPeriod, long sum) {
        VaccinationCenter = vaccinationCenter;
        this.differences = differences;
        this.maximumSum = maximumSum;
        this.hoursPeriod = hoursPeriod;
        this.sum = sum;
    }

    /**
     * This function returns the vaccination center of the patient
     *
     * @return The VaccinationCenter is being returned.
     */
    public String getVaccinationCenter() {
        return VaccinationCenter;
    }

    /**
     * This function sets the vaccination center of the patient
     *
     * @param vaccinationCenter The name of the vaccination center
     */
    public void setVaccinationCenter(String vaccinationCenter) {
        VaccinationCenter = vaccinationCenter;
    }

    /**
     * This function returns the differences array.
     *
     * @return The differences array is being returned.
     */
    public int[] getDifferences() {
        return differences;
    }

    /**
     * This function sets the differences array to the value of the differences array passed in as a parameter.
     *
     * @param differences The differences between the two arrays.
     */
    public void setDifferences(int[] differences) {
        this.differences = differences;
    }

    /**
     * It returns the maximum sum of the array.
     *
     * @return The maxmimumSum array is being returned.
     */
    public int[] getMaximumSum() {
        return maximumSum;
    }

    /**
     * This function sets the maximumSum array to the maximumSum array passed in as a parameter
     *
     * @param maximumSum This is the array that will hold the maximum sum of the subarray.
     */
    public void setMaximumSum(int[] maximumSum) {
        this.maximumSum = maximumSum;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public String[] getHoursPeriod() {
        return hoursPeriod;
    }

    public void setHoursPeriod(String[] hoursPeriod) {
        this.hoursPeriod = hoursPeriod;
    }

    @Override
    public String toString() {
        return "Analysis: " + "\n" +
                "VaccinationCenter: " + VaccinationCenter + "\n" +
                "Differences: " + "\n" + Arrays.toString(differences) + "\n" +
                "MaximumSum: "  + "\n" + Arrays.toString(maximumSum)  + "\n" +
                "Hours Period: "  + "\n" + Arrays.toString(hoursPeriod)  + "\n" +
                "Sum: "   + sum + "\n";
    }
}

