package xyz.upperlevel.game.launcher.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;

public class GELGUILauncher extends Application {

    @Getter private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        try {
            GELGUIResource.save();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot setup resources");
            alert.setHeaderText("Error during resources creation");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new GELGUIController(this));
        Parent root = loader.load(getClass().getClassLoader().getResourceAsStream("resources/gui/main-gui.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}