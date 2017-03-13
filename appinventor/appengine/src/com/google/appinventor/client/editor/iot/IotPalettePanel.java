// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.
package com.google.appinventor.client.editor.iot;

import com.google.appinventor.client.editor.simple.components.MockComponent;
import com.google.appinventor.client.editor.simple.palette.DropTargetProvider;
import com.google.appinventor.client.editor.simple.palette.SimplePalettePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by ewpatton on 3/31/17.
 */
public class IotPalettePanel extends Composite implements SimplePalettePanel {

  private final IotMicrocontrollerEditor editor;

  public IotPalettePanel(IotMicrocontrollerEditor editor) {
    this.editor = editor;
  }

  @Override
  public void loadComponents(DropTargetProvider dropTargetProvider) {

  }

  @Override
  public void configureComponent(MockComponent mockComponent) {

  }

  @Override
  public void addComponent(String componentTypeName) {

  }

  @Override
  public void clearComponents() {

  }

  @Override
  public void reloadComponents() {

  }

  @Override
  public Widget getWidget() {
    return this;
  }

  @Override
  public SimplePalettePanel copy() {
    IotPalettePanel copy = new IotPalettePanel(editor);
    copy.setSize("100%", "100%");
    return copy;
  }
}
