// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.client.editor.iot;

import com.google.appinventor.client.editor.simple.SimpleNonVisibleComponentsPanel;
import com.google.appinventor.client.editor.simple.SimpleVisibleComponentsPanel;
import com.google.appinventor.client.editor.simple.components.MockMicrocontroller;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * Visible components panel for the IOT editor.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
public class IotVisibleComponentsPanel extends SimpleVisibleComponentsPanel<MockMicrocontroller> {

  private final AbsolutePanel panel;

  /**
   * Creates new component design panel for visible components.
   *
   * @param nonVisibleComponentsPanel corresponding panel for non-visible
   *                                  components
   */
  public IotVisibleComponentsPanel(SimpleNonVisibleComponentsPanel<MockMicrocontroller> nonVisibleComponentsPanel) {
    super(nonVisibleComponentsPanel);

    panel = new AbsolutePanel();
    initWidget(panel);
  }

  @Override
  public void setRoot(MockMicrocontroller root) {
    this.root = root;
    panel.add(root);
  }
}
