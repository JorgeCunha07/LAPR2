package app.ui.gui;

import app.controller.AnalyseCenterPerformanceController;
import app.controller.App;
import app.controller.AuthController;
import app.domain.model.VaccinationCenter;
import app.domain.shared.Constants;
import app.ui.Main;
import app.ui.console.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.net.URL;
import java.util.*;

public class LoginUI implements Initializable {

    private Main mainApp;

    @FXML
    public TextField txtEmail;

    @FXML
    public TextField txtPassword;

    @FXML
    public Button btnLogin;

    @FXML
    public ComboBox cbVaccinationCenter;

    private int maxAttempts = 3;

    private AuthController controller;

    private UserRoleDTO userRoleDTO;

    private List<VaccinationCenter> list;

    private AnalyseCenterPerformanceController analyseCenterPerformanceController = new AnalyseCenterPerformanceController();

    /**
     * > This function returns the value of the variable `analyseCenterPerformanceController`
     *
     * @return The AnalyseCenterPerformanceController object.
     */
    public AnalyseCenterPerformanceController getAnalyseCenterPerformanceController() {
        return analyseCenterPerformanceController;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new AuthController();
       /*
        list = App.getInstance().getCompany().getVaccinationCenterStore().getVaccinationCenters();

        List<String> strings = new ArrayList<>();


       for (VaccinationCenter vaccinationCenter : list ) {
            strings.add(vaccinationCenter.getName());
        }

        String[] stringList = new String[strings.size()];

        for (int row = 0; row < strings.size(); row++) {
            stringList[row] = strings.get(row);
        }
*/
        //cbVaccinationCenter.setItems(FXCollections.observableArrayList(stringList));
    }

    /**
     * It checks the user id and password and then runs the program.
     *
     * @param mouseEvent The event that triggered the method.
     */
    public void onMouseClickedHandleBtnLogin(MouseEvent mouseEvent) {
        try {
            checkUserIdEmail();
            checkPassword();
            run();
        } catch (Exception exception) {
            createAlert(Alert.AlertType.ERROR, "Error", "Error", exception.getMessage()).show();
            exception.printStackTrace();
        }
    }

    /**
     * If the password is null, empty, or blank, throw an exception.
     */
    private void checkPassword() {
        if (txtPassword == null)
            throw new IllegalArgumentException("The Object TextBox cannot be null!");
        if (txtPassword.getText() == null)
            throw new IllegalArgumentException("The Password cannot be null!");
        if (txtPassword.getText().isEmpty() || txtEmail.getText().isBlank())
            throw new IllegalArgumentException("The Password cannot be blank!");
    }

    /**
     * This function checks if the email is null, empty, or blank.
     */
    private void checkUserIdEmail() {
        if (txtEmail == null)
            throw new IllegalArgumentException("The Object TextBox cannot be null!");
        if (txtEmail.getText() == null)
            throw new IllegalArgumentException("The Email cannot be null!");
        if (txtEmail.getText().isEmpty() || txtEmail.getText().isBlank())
            throw new IllegalArgumentException("The Email cannot be blank!");

    }

    /**
     * The function is used to login to the application and redirect to the appropriate UI based on the role of the user
     */
    public void run() throws Exception {
        boolean success = doLogin();
        if (success)
        {
            List<UserRoleDTO> roles = this.controller.getUserRoles();
            if ( (roles == null) || (roles.isEmpty()) )
            {
                System.out.println("User has not any role assigned.");
            }
            else
            {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role))
                {
                    boolean flagRedirect = true;
                    List<String[]> rolesUI = getMenuItemForRoles();
                    if (role.getDescription().equals(Constants.ROLE_NURSE)) {
                        try {
                           /* String name = (String) cbVaccinationCenter.getValue();
                            VaccinationCenter vaccinationCenter = null;
                            for (int row = 0; row < list.size() ; row++) {
                                if (name.equalsIgnoreCase(list.get(row).getName())) {
                                    vaccinationCenter = list.get(row);
                                }
                            }
                            flagRedirect = vaccinationCenter != null;
                            rolesUI = getMenuItemForRoles();*/
                            mainApp.replaceSceneContent("/FXML/" + "NurseMenu");
                            flagRedirect = false;

                        } catch (Exception e) {
                            System.out.println("Error: The login was not possible due to technical issues! Report to the administrators.");
                        }
                    }
                    if (flagRedirect)
                        this.redirectToRoleUI(rolesUI,role);
                }
                else
                {
                    System.out.println("User did not select a role");
                }
                this.userRoleDTO = role;
            }
        }
        this.logout();
    }

    /**
     * > It returns a list of String arrays, where each String array contains a role and a UI name
     *
     * @return A list of String arrays.
     */
    private List<String[]> getMenuItemForRoles() {
        List<String[]> rolesUI = new ArrayList<>();
        rolesUI.add(new String[]{Constants.ROLE_NURSE, "NurseMenu"});
        rolesUI.add(new String[]{Constants.ROLE_CENTER_COORDINATOR, "CenterCoordinatorUi",});
        return rolesUI;
    }

    /**
     * The function takes in the user's email and password, and sends it to the controller to check if the user exists in
     * the database
     *
     * @return A boolean value.
     */
    private boolean doLogin()
    {
        System.out.println("\nLogin UI:");
        boolean success = false;
        maxAttempts--;
        String id = txtEmail.getText();
        String pwd = txtPassword.getText();

        success = controller.doLogin(id, pwd);
        System.out.println(success);
        if (!success)
        {
            createAlert(Alert.AlertType.WARNING, "Warning", "Warning", "Invalid User Id and/or Password \n You have " + maxAttempts + "more attempt(s).").show();
        }
        return success;
    }

    /**
     * When the user clicks the logout button, call the controller's doLogout() function.
     */
    private void logout()
    {
        controller.doLogout();
    }

    /**
     * It takes a list of roles and a user role, and if the user role is in the list of roles, it redirects to the UI
     * associated with that role
     *
     * @param rolesUI a list of String arrays, where each array has two elements: the first is the role description, and
     * the second is the name of the FXML file to load.
     * @param role The role of the user
     */
    private void redirectToRoleUI(List<String[]> rolesUI, UserRoleDTO role) throws Exception {
        boolean found = false;
        Iterator<String[]> it = rolesUI.iterator();
        while (it.hasNext() && !found)
        {
            String[] item = it.next();
            found = item[0].equals(role.getDescription());
            System.out.println("/FXML/" + item[1]);
            if (found) {
                mainApp.replaceSceneContent("/FXML/" + item[1] );



            }

        }
        if (!found)
            createAlert(Alert.AlertType.ERROR, "Error", "Error", "There is no UI for users with role '" + role.getId() + "'").show();

    }

    /**
     * This function sets the mainApp variable to the mainApp variable in the main class.
     *
     * @param mainApp This is the main application class. It's used to get access to the application's main stage.
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }


    /**
     * If there's only one role, return it, otherwise show the user a list of roles and let them select one.
     *
     * @param roles a list of UserRoleDTO objects, each of which represents a role that the user can adopt in the current
     * session.
     * @return A UserRoleDTO object
     */
    private UserRoleDTO selectsRole(List<UserRoleDTO> roles)
    {
        if (roles.size() == 1)
            return roles.get(0);
        else
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
    }


    /**
     * This function returns the userRoleDTO object
     *
     * @return The userRoleDTO object.
     */
    public UserRoleDTO getUserRoleDTO() {
        return userRoleDTO;
    }

    /**
     * It creates an alert with the given parameters
     *
     * @param alertType The type of alert. This can be one of the following:
     * @param title The title of the alert.
     * @param header The text to be displayed in the header area of the dialog.
     * @param message The message to be displayed in the alert.
     * @return An alert object.
     */
    private Alert createAlert(Alert.AlertType alertType, String title, String header, String message){
        Alert alert = new Alert(alertType);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        return alert;
    }
}