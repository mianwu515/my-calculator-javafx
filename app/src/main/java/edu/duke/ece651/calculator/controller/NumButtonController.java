package edu.duke.ece651.calculator.controller;

import edu.duke.ece651.calculator.model.RPNStack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NumButtonController {

  RPNStack model;

  public NumButtonController(RPNStack model) {
    this.model = model;
  }

  /* Handle calculation on buttons */
  void pushCurrentNumIfAny() {
    String s = currentNumber.getText().trim();
    if (!s.equals("")) {
      double d = Double.parseDouble(s);
      model.pushNum(d);
    }
    currentNumber.setText("");
  }

  @FXML
  TextField currentNumber;

  @FXML
  public void onNumberButton(ActionEvent ae) {
    Object source = ae.getSource();
    if (source instanceof Button) {
      Button btn = (Button) source;
      currentNumber.setText(currentNumber.getText() + btn.getText());
    } else {
      throw new IllegalArgumentException("Invalid source " +
          source +
          " for ActionEvent");
    }
  }

  public void onEnter(ActionEvent ae) {
    pushCurrentNumIfAny();
  }

  public void onPlus(ActionEvent ae) {
    pushCurrentNumIfAny();
    model.add();
  }

  public void onSubtract(ActionEvent ae) {
    pushCurrentNumIfAny();
    model.subtract();
  }

  public void onTimes(ActionEvent ae) {
    pushCurrentNumIfAny();
    model.times();
  }

  public void onDivide(ActionEvent ae) {
    pushCurrentNumIfAny();
    model.divide();
  }

}
