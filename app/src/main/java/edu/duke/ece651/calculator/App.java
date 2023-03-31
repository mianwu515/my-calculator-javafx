package edu.duke.ece651.calculator;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class App extends Application {

  @Override
  public void start(Stage stage) {
    String javaVersion = System.getProperty("java.version");
    String javafxVersion = System.getProperty("javafx.version");
    Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");

    Circle circ = new Circle(40, 40, 30);
    //Group root = new Group(circ, l);

    //Scene scene = new Scene(root, 640, 480);

    Scene scene = new Scene(new StackPane(l, circ), 640, 480);
    stage.setTitle("My Calculator Application");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

}
