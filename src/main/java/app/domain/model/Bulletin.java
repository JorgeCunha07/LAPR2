
package app.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;


    /**
     * The Bulletin is the object that is used to record the administration of a vaccine.
     */
    public class Bulletin implements Serializable {

        /**
         * Vaccine taken by the user in previous appointment
         */
        private final Vaccine vaccine;

        /**
         * Date and Time of when the user took the last dose of the vaccine
         */
        private final LocalDateTime dateTimeOfLastDose;

        /**
         * Tracks the number of doses the user has taken so far
         */
        private int doseNumber;

        private final String lotNumber;

        /**
         * Instantiates a new Vaccine bulletin.
         *
         * @param vaccine   the vaccine
         * @param dateTime  the date time
         * @param doses     the doses
         * @param lotNumber the lot number
         */
        public Bulletin(Vaccine vaccine, LocalDateTime dateTime, int doses, String lotNumber) {
            this.vaccine = vaccine;
            this.dateTimeOfLastDose = dateTime;
            this.doseNumber = doses;
            this.lotNumber = lotNumber;
        }

        /**
         * Gets vaccine the user took.
         *
         * @return the vaccine taken by the user
         */
        public Vaccine getVaccine() {
            return vaccine;
        }

        /**
         * Gets the date and time
         *
         * @return the date and time of when the user took the previous vaccine
         */
        public LocalDateTime getDateTimeOfLastDose() {
            return dateTimeOfLastDose;
        }

        /**
         * Gets number of doses
         *
         * @return the number of doses the user has taken
         */
        public int getDose() {
            return doseNumber;
        }

        /**
         * Gets lot number.
         *
         * @return the lot number
         */
        public String getLotNumber() {
            return lotNumber;
        }

        /**
         * Sets number of doses taken by the user.
         *
         * @param doses the doses
         */
        public void setDose(int doses) {
            this.doseNumber = doses;
        }

        /**
         * Checks if it is the last dose.
         *
         * @param ageGroupIndex the age group index
         * @return the boolean
         */
       public boolean isLastDose(int ageGroupIndex) {
            int totalDoses = vaccine.getAdministrationProcess().get(ageGroupIndex).getNumberOfDoses();
            return doseNumber == totalDoses;
        }


    }

