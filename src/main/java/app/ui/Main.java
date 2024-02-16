package app.ui;

import app.controller.App;
import app.ui.console.*;
import app.ui.console.utils.Utils;
import app.ui.gui.LoginUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.ui.console.MainMenuUI;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */


public class Main extends Application {


    private Scene scene;

    private Stage stage;

    /**
     * The main function of the program. It is the first function that is called when the program is run.
     */
    public static void main(String[] args)
    {
//        VaccinatedUsersUI vaccinatedUsersUI = new VaccinatedUsersUI();

        List<MenuItem> options = getOptions();
        int option = Utils.showAndSelectIndex(options, "\n\nType of App Menu");

        if (option == 0){
            try
            {
                MainMenuUI menu = new MainMenuUI();
                menu.run();
            }
            catch( Exception e )
            {
                e.printStackTrace();
            }
        } else if (option == 1) {
            launch(args);
        } else {
            System.out.println("\nClosing Application!");
            System.exit(0);
        }
    }

    /**
     * It returns a list of menu items
     *
     * @return A list of menu items.
     */
    public static List<MenuItem> getOptions(){
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Console Application (Some features)", new ShowTextUI("Console Application (Some features)")));
        options.add(new MenuItem("Graphical Application (Some features)", new ShowTextUI("Graphical Application (Some features)")));
        return options;
    }

    private final double MINIMUM_WINDOW_WIDTH = 400.0;
    private final double MINIMUM_WINDOW_HEIGHT = 300.0;
    private final double SCENE_WIDTH = 540.0;
    private final double SCENE_HEIGHT = 278.0;

    /**
     * The start function is called when the application is launched. It sets the title of the window, the minimum width
     * and height of the window, and then calls the toMainScene function
     *
     * @param stage The stage is the window that will be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("DGS Application");
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        toMainScene();
        this.stage.show();
    }

    /**
     * This function returns the stage of the current scene.
     *
     * @return The stage.
     */
    public Stage getStage() {
        return this.stage;
    }

    /**
     * It loads the FXML file, creates a new instance of the controller class, injects the main application into the
     * controller, and then returns the controller instance
     */
    public void toMainScene() {
        try {
            LoginUI mainUI = (LoginUI) replaceSceneContent("/FXML/LoginScene");
            mainUI.setMainApp(this);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * It loads the FXML file, creates a scene, sets the scene on the stage, and returns the controller
     *
     * @param fxml The name of the FXML file to load.
     * @return The controller of the fxml file.
     */
    public Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = App.class.getResourceAsStream(fxml + ".fxml");
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(App.class.getResource(fxml + ".fxml"));
        Pane page;
        try {
            page = (Pane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, SCENE_WIDTH, SCENE_HEIGHT);
//        scene.getStylesheets().add("/styles/Styles.css");
        this.stage.setScene(scene);
        this.stage.sizeToScene();

        return loader.getController();
    }

    /**
     * Loads the FXML file with the given name and sets it as the root of the scene.
     *
     * @param fxml The name of the FXML file to load.
     */
    public void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        scene.setRoot(root);
    }

    /**
     * It loads an FXML file and returns the root node of the FXML file
     *
     * @param fxml The name of the FXML file to load.
     * @return The Parent object is being returned.
     */
    public static Parent loadFXML(String fxml) throws IOException {
        URL fxmlLocation = Main.class.getResource(fxml + ".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        return fxmlLoader.load();
    }
}