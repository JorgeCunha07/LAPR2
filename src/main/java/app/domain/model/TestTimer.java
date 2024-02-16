package app.domain.model;

import app.controller.VaccineAdministrationController;
import app.domain.model.dto.EmployeeDTO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;


public class TestTimer {
    // Creating a new instance of the Timer class.
    static Timer timer = new Timer();

    /**
     * SendSMSMessage() is a class that extends TimerTask, and it will be executed after the specified number of minutes.
     *
     * @param minutes The number of minutes to wait before sending the SMS message.
     */
    public void testTimer(int minutes) {
        timer.schedule(new SendSMSMessage(), minutes * 1000 * 60);
    }
}

class SendSMSMessage extends TimerTask {
    /**
     * The function will create a text file called SMS_Recovery.txt and write a message to it
     */
    public void run() {
//terminate the timer thread
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("SMS_Recovery.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
        writer.println("30 mins has passed! You can leave the center!");
        writer.close();
        TestTimer.timer.cancel();

    }

}