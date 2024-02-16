package app.ui.console;

import app.controller.AuthController;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class AuthUI implements Runnable{
    private AuthController ctrl;

    public AuthUI()
    {
        ctrl = new AuthController();
    }

    /**
     * The user is asked to select a role, and then the UI for that role is displayed
     */
    public void run()
    {
        boolean success = doLogin();

        if (success)
        {
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
            if ( (roles == null) || (roles.isEmpty()) )
            {
                System.out.println("User has not any role assigned.");
            }
            else
            {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role))
                {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    this.redirectToRoleUI(rolesUI,role);
                }
                else
                {
                    System.out.println("User did not select a role.");
                }
            }
        }
        this.logout();
    }

    /**
     * It returns a list of menu items, each of which contains a role and a RoleUI
     *
     * @return A list of MenuItem objects.
     */
    private List<MenuItem> getMenuItemForRoles()
    {
        List<MenuItem> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItem(Constants.ROLE_ADMIN, new AdminUI()));
        // To complete with other user roles and related RoleUI
        rolesUI.add(new MenuItem(Constants.ROLE_RECEPTIONIST, new ReceptionistUI()));
        //
        rolesUI.add(new MenuItem(Constants.ROLE_SNS_USER, new SNSUserUI()));
        //
        rolesUI.add(new MenuItem(Constants.ROLE_NURSE, new NurseUI()));
        //
        rolesUI.add(new MenuItem(Constants.ROLE_CENTER_COORDINATOR, new CenterCoordinatorUI()));
        return rolesUI;
    }

    /**
     * > The function prompts the user to enter his/her userid and password, and then calls the controller's doLogin()
     * function to perform the login
     *
     * @return A boolean value.
     */
    private boolean doLogin()
    {
        System.out.println("\nLogin UI:");

        int maxAttempts = 3;
        boolean success = false;
        do
        {
            maxAttempts--;
            String id = Utils.readLineFromConsole("Enter UserId/Email: ");
            String pwd = Utils.readLineFromConsole("Enter Password: ");

            success = ctrl.doLogin(id, pwd);
            if (!success)
            {
                System.out.println("Invalid UserId and/or Password. \n You have  " + maxAttempts + " more attempt(s).");
            }

        } while (!success && maxAttempts > 0);
        return success;
    }

    private void logout()
    {
        ctrl.doLogout();
    }

    /**
     * It takes a list of menu items and a user role, and it runs the menu item that has the same description as the user
     * role
     *
     * @param rolesUI a list of MenuItem objects that represent the UI for each role.
     * @param role the role of the user that is currently logged in
     */
    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role)
    {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found)
        {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found)
                item.run();
        }
        if (!found)
            System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
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


}
