package app.domain.model.Store;

import app.domain.model.VaccineAdministration;
import app.domain.model.VaccineType;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;

public class VaccineAdministrationStore {

    // Creating a new ArrayList of VaccineAdministration objects.
    private ArrayList <VaccineAdministration> vaccineAdministrationArrayList ;

    // Creating a new ArrayList of VaccineAdministration objects.
    public VaccineAdministrationStore() {
        this.vaccineAdministrationArrayList = new ArrayList <>();
    }


    /**
     * This function returns the vaccineAdministrationArrayList
     * @return An ArrayList of VaccineAdministration objects.
     */
    public ArrayList<VaccineAdministration> getVaccineAdministrationArrayList() {
        return vaccineAdministrationArrayList;
    }

    /**
     * This function sets the vaccineAdministrationArrayList to the vaccineAdministrationArrayList that is passed in as a
     * parameter
     * @param vaccineAdministrationArrayList This is the ArrayList that will hold the data that will be displayed in the
     * RecyclerView.
     */
    public void setVaccineAdministrationArrayList(ArrayList<VaccineAdministration> vaccineAdministrationArrayList) {
        this.vaccineAdministrationArrayList = vaccineAdministrationArrayList;
    }

    /**
     * This function adds a vaccine administration to the vaccine administration array list
     * @param exVaccineAdministration The VaccineAdministration object that you want to add to the ArrayList.
     * @return A boolean value.
     */
    public boolean addVaccineAdministrationArrayList(VaccineAdministration exVaccineAdministration) {
        return vaccineAdministrationArrayList.add(exVaccineAdministration);
    }

    /**
     * This function checks if a SNS User has an administration of a specific vaccine type
     * @param snsNumber The SNS number of the user.
     * @param vaccineType The type of vaccine that the user has been administered.
     * @return The vaccine administration of the specified vaccine type.
     */
    public VaccineAdministration checkIfSNSUserHasAdministrationVaccineType(String snsNumber, VaccineType vaccineType) throws NoSuchObjectException {
        for (VaccineAdministration aux:vaccineAdministrationArrayList ) {
            if (aux.getSnsNumber().equals(snsNumber) && aux.getVaccine().getVaccineType().equals(vaccineType)) {
                return aux;
            }
        }
        throw new NoSuchObjectException("SNS User doesn´t have an administration of the specified vaccine type.");
    }

}
