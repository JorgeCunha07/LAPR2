package app.ui.console;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class MenuItem {
    // A private variable that is used to store the description of the menu item.
    private String description;
    // A reference to a function that will be executed when the menu item is selected.
    private Runnable ui;

    // A constructor.
    public MenuItem(String description,  Runnable ui)
    {
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("MenuItem description cannot be null or empty.");
        if (Objects.isNull(ui))
            throw new IllegalArgumentException("MenuItem does not support a null UI.");

        this.description = description;
        this.ui = ui;
    }

    /**
     * This function runs the UI.
     */
    public void run()
    {
        this.ui.run();
    }

    /**
     * This function returns true if the description of the current object is equal to the description passed in as a
     * parameter.
     *
     * @param description The description of the item.
     * @return A boolean value.
     */
    public boolean hasDescription(String description)
    {
        return this.description.equals(description);
    }

    /**
     * The toString() function returns a string representation of the object
     *
     * @return The description of the object.
     */
    public String toString()
    {
        return this.description;
    }

}
