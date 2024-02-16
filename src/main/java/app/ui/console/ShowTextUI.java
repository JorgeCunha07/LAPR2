package app.ui.console;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class ShowTextUI implements Runnable{

    private  String text;
    // A constructor that checks if the text is null or empty and if it is it throws an exception.
    public ShowTextUI(String text)
    {
        if (StringUtils.isBlank(text))
            throw new IllegalArgumentException("ShowTextUI does not support null or empty text");

        this.text = text;
    }
    /**
     * This function prints the text that is stored in the variable text
     */
    public void run()
    {
        System.out.println("\n");
        System.out.println(this.text);
        System.out.println("\n");
    }
}
