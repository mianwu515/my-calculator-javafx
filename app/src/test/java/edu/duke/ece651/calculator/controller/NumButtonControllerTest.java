package edu.duke.ece651.calculator.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.TextInputControlMatchers;
import org.testfx.util.WaitForAsyncUtils;

import edu.duke.ece651.calculator.model.RPNStack;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class NumButtonControllerTest {
  private TextField testText;
  private NumButtonController cont;
  private RPNStack mockedModel;

  @Start
  private void start(Stage stage) {
    testText = new TextField();
    mockedModel = mock(RPNStack.class);
    cont = new NumButtonController(mockedModel);
    cont.currentNumber = testText;
  }

  @Test
  void test_enterButton(FxRobot robot) {
    Platform.runLater(() -> {
      testText.setText("1234.5");
      Button b = new Button("Enter");
      cont.onEnter(new ActionEvent(b, null));
    });
    WaitForAsyncUtils.waitForFxEvents();
    verify(mockedModel).pushNum(1234.5); // verify method pushNum was called
    verifyNoMoreInteractions(mockedModel); // verify that nothing else was done to the model
    FxAssert.verifyThat(testText, TextInputControlMatchers.hasText("")); // check states after the action
  }

  private void addNums(String... strs) {
    Platform.runLater(() -> {
      for (String s : strs) {
        Button b = new Button(s);
        cont.onNumberButton(new ActionEvent(b, null));
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void test_onNumberButton_7(FxRobot robot) {
    addNums("7");
    FxAssert.verifyThat(testText, TextInputControlMatchers.hasText("7"));
  }

  @Test
  public void test_onNumberButton_pi(FxRobot robot) {
    addNums("3", ".", "1", "4");
    FxAssert.verifyThat(testText, TextInputControlMatchers.hasText("3.14"));
  }

}
