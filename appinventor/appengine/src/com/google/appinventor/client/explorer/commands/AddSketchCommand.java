// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.client.explorer.commands;

import com.google.appinventor.client.Ode;
import com.google.appinventor.client.widgets.LabeledTextBox;
import com.google.appinventor.client.youngandroid.TextValidators;
import com.google.appinventor.shared.rpc.project.ProjectNode;
import com.google.appinventor.shared.rpc.project.iot.IotMicrocontrollerNode;
import com.google.appinventor.shared.rpc.project.iot.IotPackageNode;
import com.google.appinventor.shared.rpc.project.youngandroid.YoungAndroidProjectNode;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.HashSet;
import java.util.Set;

import static com.google.appinventor.client.Ode.MESSAGES;

public class AddSketchCommand extends ChainableCommand {
  public AddSketchCommand() {
  }

  @Override
  protected boolean willCallExecuteNextCommand() {
    return true;
  }

  @Override
  protected void execute(ProjectNode node) {
    if (node instanceof YoungAndroidProjectNode) {
      new NewSketchDialog((YoungAndroidProjectNode) node).center();
    } else {
      executionFailedOrCanceled();
      throw new IllegalArgumentException("node must be a YoungAndroidProjectNode");
    }
  }

  private class NewSketchDialog extends DialogBox {
    private final LabeledTextBox newNameTextBox;

    private final Set<String> otherSketchNames;

    NewSketchDialog(final YoungAndroidProjectNode projectRootNode) {
      super(false, true);

      setStylePrimaryName("ode-DialogBox");
      setText(MESSAGES.newSketchTitle());
      VerticalPanel contentPanel = new VerticalPanel();

      final String prefix = "Sketch";
      final int prefixLength = prefix.length();
      int highIndex = 0;
      otherSketchNames = new HashSet<>();

      for (ProjectNode source : projectRootNode.getAllSourceNodes()) {
        if (source instanceof IotMicrocontrollerNode) {
          String sketchName = ((IotMicrocontrollerNode) source).getEntityName();
          otherSketchNames.add(sketchName);

          if (sketchName.startsWith(prefix)) {
            try {
              highIndex = Math.max(highIndex, Integer.parseInt(sketchName.substring(prefixLength)));
            } catch (NumberFormatException e) {
              continue;
            }
          }
        }
      }

      String defaultSketchName = prefix + (highIndex + 1);

      newNameTextBox = new LabeledTextBox(MESSAGES.sketchNameLabel());
      newNameTextBox.setText(defaultSketchName);
      newNameTextBox.getTextBox().addKeyUpHandler(new KeyUpHandler() {
        @Override
        public void onKeyUp(KeyUpEvent event) {
          int keyCode = event.getNativeKeyCode();
          if (keyCode == KeyCodes.KEY_ENTER) {
            handleOkClick(projectRootNode);
          } else if (keyCode == KeyCodes.KEY_ESCAPE) {
            hide();
            executionFailedOrCanceled();
          }
        }
      });
      contentPanel.add(newNameTextBox);

      String cancelText = MESSAGES.cancelButton();
      String okText = MESSAGES.okButton();

      Button cancelButton = new Button(cancelText);
      cancelButton.addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          hide();
          executionFailedOrCanceled();
        }
      });
      Button okButton = new Button(okText);
      okButton.addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          handleOkClick(projectRootNode);
        }
      });
      HorizontalPanel buttonPanel = new HorizontalPanel();
      buttonPanel.add(cancelButton);
      buttonPanel.add(okButton);
      buttonPanel.setSize("100%", "24px");
      contentPanel.add(buttonPanel);
      contentPanel.setSize("320px", "100%");

      add(contentPanel);
    }

    private void handleOkClick(YoungAndroidProjectNode projectRootNode) {
      String newSketchName = newNameTextBox.getText();
      if (validate(newSketchName)) {
        hide();
        addSketchAction(projectRootNode, newSketchName);
      } else {
        newNameTextBox.setFocus(true);
      }
    }

    private boolean validate(String newSketchName) {
      if (!TextValidators.isValidIdentifier(newSketchName)) {
        Window.alert(MESSAGES.malformedFormNameError());
        return false;
      }

      if (otherSketchNames.contains(newSketchName)) {
        Window.alert(MESSAGES.duplicateFormNameError());
        return false;
      }

      return true;
    }
    protected void addSketchAction(final YoungAndroidProjectNode projectRootNode,
                                   final String sketchName) {
      final Ode ode = Ode.getInstance();
      final IotPackageNode packageNode = projectRootNode.getIotPackageNode();
      String qualifiedSketchName = packageNode.getPackageName() + '.' + sketchName;
    }
  }
}
