package app.domain.model;

public class AgeGroup {
    private  int minAge;
    private  int maxAge;
    private  int numberOfDoses;
    private  double dosage;
    private  int daysBetweenDoses;

    // This is a constructor. It is a special method that is called when an object is created.
    public AgeGroup(int minAge, int maxAge, int numberOfDoses, double dosage, int daysBetweenDoses){
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.numberOfDoses = numberOfDoses;
        this.dosage = dosage;
        this.daysBetweenDoses = daysBetweenDoses;
    }

    // This is a default constructor.
    public AgeGroup(){
    }

    /**
     * This function returns the minimum age.
     *
     * @return The minimum age of the person.
     */
    public int getMinAge() {
        return minAge;
    }

    /**
     * This function sets the minimum age of the user.
     *
     * @param minAge The minimum age of the user.
     */
    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    /**
     * This function returns the maxAge variable.
     *
     * @return The maxAge variable is being returned.
     */
    public int getMaxAge() {
        return maxAge;
    }

    /**
     * Sets the maximum age of the cache in seconds.
     *
     * @param maxAge The maximum age of the cookie in seconds.
     */
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * This function returns the number of doses of the drug
     *
     * @return The number of doses.
     */
    public int getNumberOfDoses() {
        return numberOfDoses;
    }

    /**
     * This function sets the number of doses of a drug
     *
     * @param numberOfDoses The number of doses that the user has taken.
     */
    public void setNumberOfDoses(int numberOfDoses) {
        this.numberOfDoses = numberOfDoses;
    }

    /**
     * This function returns the dosage of the drug
     *
     * @return The dosage of the medicine.
     */
    public double getDosage() {
        return dosage;
    }

    /**
     * This function sets the dosage of the drug.
     *
     * @param dosage The dosage of the drug.
     */
    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    /**
     * This function returns the number of days between doses
     *
     * @return daysBetweenDoses
     */
    public int getDaysBetweenDoses() {
        return daysBetweenDoses;
    }

    /**
     * This function sets the number of days between doses
     *
     * @param daysBetweenDoses The number of days between doses.
     */
    public void setDaysBetweenDoses(int daysBetweenDoses) {
        this.daysBetweenDoses = daysBetweenDoses;
    }



    /**
     * Check if the given age belongs in the age group.
     *
     * @param age the age of the person
     * @return A boolean value.
     */
    public boolean checkIfBelongsInAgeGroup(int age) {
        return (minAge <= age && age <= maxAge);
    }

    /**
     * The toString() function returns a string that contains the minimum age, maximum age, number of doses, dosage, and
     * days between doses
     *
     * @return The string representation of the object.
     */
    @Override
    public String toString (){
        return String.format("Minimum age: %d | Maximum age: %d \n Number of Doses: %d | Dosage: %f \n Days between doses: %d \n",minAge,maxAge,numberOfDoses,dosage,daysBetweenDoses);
    }
}
