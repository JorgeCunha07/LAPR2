package app.domain.model;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Vaccination center slot.
 */
public class VaccinationCenterSlot  {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    private LocalTime time;
    private int capacity;
    private int scheduled = 0;

    private static final int HOUR = 0;
    private static final int MINUTE = 0;
    private static final int CAPACITY = 0;

    /**
     * Instantiates a new Vaccination center slot.
     *
     * @param time      the time
     * @param capacity  the capacity
     * @param scheduled the scheduled
     */
    public VaccinationCenterSlot(LocalTime time, int capacity, int scheduled){
        this.time = time;
        this.capacity = capacity;
        this.scheduled = scheduled;
    }


    /**
     * Get time local time.
     *
     * @return the local time
     */
    public LocalTime getTime(){
        return time;
    }

    /**
     * Set time.
     *
     * @param time the time
     */
    public void setTime(LocalTime time){
        this.time = time;
    }

    /**
     * Gets capacity.
     *
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets capacity.
     *
     * @param capacity the capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets scheduled.
     *
     * @return the scheduled
     */
    public int getScheduled() {
        return scheduled;
    }

    /**
     * Sets scheduled.
     *
     * @param scheduled the scheduled
     */
    public void setScheduled(int scheduled) {
        this.scheduled = scheduled;
    }

    /**
     * Add schedule boolean.
     *
     * @return the boolean
     */
    public boolean addSchedule()
    {
        boolean result = true;
        if (this.scheduled < this.capacity){
            this.scheduled++;
        }else {
            result = false;
            System.out.println("Slot full");
        }
        return result;

    }

    /**
     * Check full boolean.
     *
     * @param slot the slot
     * @return the boolean
     */
    public boolean checkFull(VaccinationCenterSlot slot){
        if (slot.getScheduled() == slot.getCapacity()){
            return true;
        }else
            return false;
    }

    @Override
    public String toString() {
        return String.format("Time: %s \n Capacity: %d | Scheduled: %d ",time.format(formatter),capacity,scheduled);
    }
}
