package app.domain.model;

/**
 * The type Vaccine.
 */
public class Vaccine {

    private  int id;
    private String name;
    private VaccineType vaccineType;
    private AdministrationProcess administrationProcess;
    private static final int ID_BY_OMISSION = 0;
    private static final String NAME_BY_OMISSION = "no name";
    private static final VaccineType TYPE_BY_OMISSION = null;
    private static final AdministrationProcess ADMINISTRATION_PROCESS = null;

    /**
     * Instantiates a new Vaccine.
     *
     * @param id                    the id
     * @param name                  the name
     * @param vaccineType           the vaccine type
     * @param administrationProcess the administration process
     */
    public Vaccine(int id, String name, VaccineType vaccineType, AdministrationProcess administrationProcess){
        this.id = id;
        this.name = name;
        this.vaccineType = vaccineType;
        this.administrationProcess = administrationProcess;
    }

    /**
     * Instantiates a new Vaccine.
     */
    public Vaccine(){
        this.id = ID_BY_OMISSION;
        this.name = NAME_BY_OMISSION;
        this.vaccineType = TYPE_BY_OMISSION;
        this.administrationProcess = ADMINISTRATION_PROCESS;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets vaccine type.
     *
     * @return the vaccine type
     */
    public VaccineType getVaccineType() {
        return vaccineType;
    }

    /**
     * Sets vaccine type.
     *
     * @param vaccineType the vaccine type
     */
    public void setVaccineType(VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }

    /**
     * Gets administration process.
     *
     * @return the administration process
     */
    public AdministrationProcess getAdministrationProcess() {
        return administrationProcess;
    }

    /**
     * Sets administration process.
     *
     * @param administrationProcess the administration process
     */
    public void setAdministrationProcess(AdministrationProcess administrationProcess) {
        this.administrationProcess = administrationProcess;
    }

    /**
     * The toString() method returns a string representation of the object
     *
     * @return The id, name, vaccineType, and administrationProcess of the vaccine.
     */
    @Override
    public String toString() {
        return String.format("Vaccine [%d] %s \n %s \n %s",id,name,vaccineType,administrationProcess);
    }


    /*public boolean validateVaccine(){
        if (this.administrationProcess.getListOfAgeGroups(). && this.vaccineType.validateCode()){
            return true;
        }else return false;
    }

     */
}
