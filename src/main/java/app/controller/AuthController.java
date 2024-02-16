package app.controller;

import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class AuthController {

    // Creating a new instance of the App class.
    private  App app;

    // Creating a new instance of the App class.
    public AuthController()
    {
        this.app = App.getInstance();
    }

    /**
     * If the user is not logged in, try to log in with the given email and password. If the login fails, return false. If
     * the login succeeds, return true.
     *
     * @param email The email of the user
     * @param pwd The password of the user
     * @return A boolean value.
     */
    public boolean doLogin(String email, String pwd)
    {
        try {
            return this.app.doLogin(email, pwd);
        } catch(IllegalArgumentException ex)
        {
            return false;
        }
    }

    /**
     * If the user is logged in, return the user's roles
     *
     * @return A list of UserRoleDTO objects.
     */
    public List<UserRoleDTO> getUserRoles()
    {
        if (this.app.getCurrentUserSession().isLoggedIn())
        {
            return this.app.getCurrentUserSession().getUserRoles();
        }
        return null;
    }

    /**
     * This function calls the doLogout() function in the app object.
     */
    public void doLogout()
    {
        this.app.doLogout();
    }
}
