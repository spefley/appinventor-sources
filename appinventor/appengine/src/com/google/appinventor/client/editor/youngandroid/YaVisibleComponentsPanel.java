// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.client.editor.youngandroid;

import com.google.appinventor.client.editor.ProjectEditor;
import com.google.appinventor.client.editor.simple.SimpleNonVisibleComponentsPanel;
import com.google.appinventor.client.editor.simple.SimpleVisibleComponentsPanel;
import com.google.appinventor.client.editor.simple.components.MockForm;
import com.google.appinventor.shared.settings.SettingsConstants;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import static com.google.appinventor.client.Ode.MESSAGES;

/**
 * An implementation of SimpleVisibleComponentsPanel for the MockForm designer.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
public class YaVisibleComponentsPanel extends SimpleVisibleComponentsPanel<MockForm> {
  // UI elements
  private final VerticalPanel phoneScreen;
  private final CheckBox checkboxShowHiddenComponents;
  private final CheckBox checkboxPhoneTablet; // A CheckBox for Phone/Tablet preview sizes

  /**
   * Creates new component design panel for visible components.
   *
   * @param projectEditor
   * @param nonVisibleComponentsPanel corresponding panel for non-visible
   */
  public YaVisibleComponentsPanel(final ProjectEditor projectEditor,
                                  SimpleNonVisibleComponentsPanel<MockForm> nonVisibleComponentsPanel) {
    super(nonVisibleComponentsPanel);
    // Initialize UI
    phoneScreen = new VerticalPanel();
    phoneScreen.setStylePrimaryName("ode-SimpleFormDesigner");

    checkboxShowHiddenComponents = new CheckBox(MESSAGES.showHiddenComponentsCheckbox()) {
      @Override
      protected void onLoad() {
        // onLoad is called immediately after a widget becomes attached to the browser's document.
        boolean showHiddenComponents = Boolean.parseBoolean(
            projectEditor.getProjectSettingsProperty(
                SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
                SettingsConstants.YOUNG_ANDROID_SETTINGS_SHOW_HIDDEN_COMPONENTS));
        checkboxShowHiddenComponents.setValue(showHiddenComponents);
      }
    };
    checkboxShowHiddenComponents.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        boolean isChecked = event.getValue(); // auto-unbox from Boolean to boolean
        projectEditor.changeProjectSettingsProperty(
            SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
            SettingsConstants.YOUNG_ANDROID_SETTINGS_SHOW_HIDDEN_COMPONENTS,
            isChecked ? "True" : "False");
        if (root != null) {
          root.refresh();
        }
      }
    });
    phoneScreen.add(checkboxShowHiddenComponents);

    checkboxPhoneTablet = new CheckBox(MESSAGES.previewPhoneSize()) {
      @Override
      protected void onLoad() {
        // onLoad is called immediately after a widget becomes attached to the browser's document.
        boolean showPhoneTablet = Boolean.parseBoolean(
            projectEditor.getProjectSettingsProperty(
                SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
                SettingsConstants.YOUNG_ANDROID_SETTINGS_PHONE_TABLET));
        checkboxPhoneTablet.setValue(showPhoneTablet);
        changeFormPreviewSize(showPhoneTablet);
      }
    };
    checkboxPhoneTablet.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        boolean isChecked = event.getValue(); // auto-unbox from Boolean to boolean
        projectEditor.changeProjectSettingsProperty(
            SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
            SettingsConstants.YOUNG_ANDROID_SETTINGS_PHONE_TABLET,
            isChecked ? "True" : "False");
        changeFormPreviewSize(isChecked);
      }
    });
    phoneScreen.add(checkboxPhoneTablet);

    initWidget(phoneScreen);
  }

  /**
   * Associates a Simple root component with this panel.
   *
   * @param form  backing mocked root component
   */
  @Override
  public void setRoot(MockForm form) {
    this.root = form;
    phoneScreen.add(form);
  }

  private void changeFormPreviewSize(boolean isChecked) {
    if (root != null){
      if (isChecked){
        root.changePreviewSize(true);
        checkboxPhoneTablet.setText(MESSAGES.previewPhoneSize());
      }
      else {
        root.changePreviewSize(false);
        checkboxPhoneTablet.setText(MESSAGES.previewTabletSize());
      }
    }
  }

  public void enableTabletPreviewCheckBox(boolean enable){
    if (root != null){
      if (!enable){
        root.changePreviewSize(false);
        checkboxPhoneTablet.setText(MESSAGES.previewTabletSize());
        checkboxPhoneTablet.setChecked(false);
      }
    }
    checkboxPhoneTablet.setEnabled(enable);
  }
}
