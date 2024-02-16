package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccineType;


public class AddnewVaccinetypeController {

    // A reference to the company object in the App class.
    private Company company = App.getInstance().getCompany();

    // Creating a new VaccineType object.
    public AddnewVaccinetypeController() {
        VaccineType vt = new VaccineType();
    }

    /**
     * > This function creates a new vaccine type in the database
     *
     * @param vaccinetype The vaccine type to be created.
     * @return A new vaccine type is being returned.
     */
    public VaccineType specifyNewVaccineType(VaccineType vaccinetype) {
        return company.createVaccineType(vaccinetype);
    }

    /**
     * This function saves a vaccine type to the database
     *
     * @param vaccinetype The vaccine type object that you want to save.
     * @return A boolean value.
     */
    public boolean saveVaccineType(VaccineType vaccinetype) {
        return company.saveVaccineType(vaccinetype);
    }



}
