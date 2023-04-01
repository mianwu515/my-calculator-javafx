package edu.duke.ece651.calculator;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import edu.duke.ece651.calculator.controller.CalculatorController;
import edu.duke.ece651.calculator.controller.NumButtonController;
import edu.duke.ece651.calculator.model.RPNStack;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {

  /**
   * @throws IOException
   */
  @Override
  public void start(Stage stage) throws IOException {
    String javaVersion = System.getProperty("java.version");
    String javafxVersion = System.getProperty("javafx.version");
    // Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " +
    // javaVersion + ".");

    // Circle circ = new Circle(40, 40, 30);
    // Group root = new Group(circ, l);

    // Scene scene = new Scene(root, 640, 480);

    // gp.add(new Button("+"), 0, 0);
    // gp.add(new Button("-"), 1, 0);
    // gp.add(new Button("*"), 2, 0);
    // gp.add(new Button("/"), 3, 0);

    // gp.add(new Button("7"), 0, 1);
    // gp.add(new Button("8"), 1, 1);
    // gp.add(new Button("9"), 2, 1);
    // gp.add(new Button("4"), 0, 2);
    // gp.add(new Button("5"), 1, 2);
    // gp.add(new Button("6"), 2, 2);
    // gp.add(new Button("1"), 0, 3);
    // gp.add(new Button("2"), 1, 3);
    // gp.add(new Button("3"), 2, 3);

    // String[] labels = new String[] { "+", "-", "*", "/", "7", "8", "9", "4", "5",
    // "6", "1", "2", "3", "." };

    // int rows[] = new int[] { 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4 };
    // int cols[] = new int[] { 0, 1, 2, 3, 0, 1, 2, 0, 1, 2, 0, 1, 2, 2 };

    // for (int i = 0; i < labels.length; i++) {
    // Button b = new Button(labels[i]);
    // gp.add(b, cols[i], rows[i]);
    // b.setMaxWidth(Double.MAX_VALUE);
    // b.setMaxHeight(Double.MAX_VALUE);
    // }

    RPNStack model = new RPNStack();
    // XML
    URL xmlResource = getClass().getResource("/ui/calc-split.xml");
    FXMLLoader loader = new FXMLLoader(xmlResource);

    HashMap<Class<?>, Object> controllers = new HashMap<>();
    controllers.put(NumButtonController.class, new NumButtonController(model));
    controllers.put(CalculatorController.class, new CalculatorController());
    loader.setControllerFactory((c) -> { // on set the call back func here. the real call happens when loader calls its
                                         // load() method
      return controllers.get(c);
    });

    // GridPane gp = FXMLLoader.load(xmlResource);
    GridPane gp = loader.load();
    // for (int i = 0; i < 4; i++) {
    // ColumnConstraints cc = new ColumnConstraints();
    // cc.setPercentWidth(25);
    // gp.getColumnConstraints().add(cc);
    // }
    // for (int i = 0; i < 5; i++) {
    // RowConstraints rc = new RowConstraints();
    // rc.setPercentHeight(20);
    // gp.getRowConstraints().add(rc);
    // }

    // Button b = new Button("0");
    // b.setMaxWidth(Double.MAX_VALUE);
    // b.setMaxHeight(Double.MAX_VALUE);
    // gp.add(b, 0, 4, 2, 1);

    // b = new Button("E\nn\nt\ne\nr");
    // b.setMaxWidth(Double.MAX_VALUE); // Note this change
    // b.setMaxHeight(Double.MAX_VALUE); // Note this change
    // gp.add(b, 3, 1, 1, 3);

    // Create a scene
    Scene scene = new Scene(gp, 640, 480);

    // Load the CSS stylesheet
    URL cssResource = getClass().getResource("/ui/calcbuttons.css");
    scene.getStylesheets().add(cssResource.toString());

    @SuppressWarnings("unchecked")
    ListView<Double> operands = (ListView<Double>) scene.lookup("#rpnstack");
    operands.setItems(model.getList());

    // Scene scene = new Scene(new StackPane(l, circ), 640, 480);
    stage.setTitle("My Calculator Application");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

}
