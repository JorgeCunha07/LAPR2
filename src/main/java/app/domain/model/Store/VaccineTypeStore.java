package app.domain.model.Store;

import app.domain.model.VaccineType;

import java.util.ArrayList;
import java.util.List;

public class VaccineTypeStore {
    public static List<VaccineType> vaccinetypes = new ArrayList<VaccineType>();


    /**
     * Creates a new vaccine type
     * @return the vaccine type created
     */
    public VaccineType createVaccineType(VaccineType vaccinetype){
        return new VaccineType(vaccinetype.getCode(), vaccinetype.getDescription(), vaccinetype.getVaccinetechnology());
    }

    /**
     *
     * @param vaccinetype the atribute to be validated
     * @return true if the vaccine type is valid
     */
    public boolean validateVaccineType(VaccineType vaccinetype){
        if(vaccinetype.validateCode() && !vaccinetype.getDescription().isBlank()){
            return true;
        }
        return false;
    }

    /**
     *
     * @param vaccinetype the atribute to be saved in the vaccine types list
     * @return true if the vaccine type was saved succesfully
     */

    public boolean saveVaccineType(VaccineType vaccinetype) {
        if (validateVaccineType(vaccinetype)) {
            return this.vaccinetypes.add(vaccinetype);
        }
        else return false;
    }


}
