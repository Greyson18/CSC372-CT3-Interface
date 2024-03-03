//Imports for JavaFX
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

//Regular Java imports
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class InterfaceExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Interface Example");

        // Create a menu bar and the different menu options
        MenuBar menuBar = new MenuBar();
        Menu dateMenu = new Menu("Option 1: Date/Time");
        Menu fileMenu = new Menu("Option 2: Write to File");
        Menu randomBackgroundMenu = new Menu("Option 3: Background");
        Menu exitMenu = new Menu("Option 4: Exit");

        // Create menu items that will go under the menu options
        MenuItem printDateTimeItem = new MenuItem("Print Date and Time");
        MenuItem writeToLogFileItem = new MenuItem("Write to Log.txt File");
        MenuItem randomBackgroundItem = new MenuItem("Change Background Color");
        MenuItem exitItem = new MenuItem("Click here to exit");

        // Add menu items to their respective menus
        dateMenu.getItems().add(printDateTimeItem);
        fileMenu.getItems().add(writeToLogFileItem);
        randomBackgroundMenu.getItems().add(randomBackgroundItem);
        exitMenu.getItems().add(exitItem);

        // Create a text area to display content
        TextArea textArea = new TextArea();
        textArea.setPrefWidth(300);
        textArea.setPrefHeight(300);

        //Create action handlers for Date and Time
        printDateTimeItem.setOnAction(event -> {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-YYYY HH:mm:ss");
            textArea.appendText(formatter.format(now) + "\n");
        });
        
        //Create action handlers for writing to a text file
        writeToLogFileItem.setOnAction(event -> {
            try {
                FileWriter writer = new FileWriter("log.txt", true);
                writer.write(textArea.getText());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //Create action handler to randomly change background color to a shade of green
        randomBackgroundItem.setOnAction(event -> {
            Random random = new Random();
            Color randomColor = Color.color(random.nextDouble(), 1.0, random.nextDouble());
            textArea.setStyle("-fx-control-inner-background: " + toHex(randomColor) + ";");
        });

        //Create action handler to exit the program
        exitItem.setOnAction(event -> {
            primaryStage.close();
        });

        // Add all menus to the menu bar
        menuBar.getMenus().addAll(dateMenu, fileMenu, randomBackgroundMenu, exitMenu);

        // Create the layout
        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(textArea);

        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Convert Color to HEX for CSS styling
    private String toHex(Color color) {
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);
        return String.format("#%02X%02X%02X", r, g, b);
    }
}