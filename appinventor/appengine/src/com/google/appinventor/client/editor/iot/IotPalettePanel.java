// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.client.editor.iot;

import com.google.appinventor.client.editor.simple.palette.SimplePalettePanel;
import com.google.appinventor.client.editor.youngandroid.palette.AbstractPalettePanel;

/**
 * Panel showing IOT components that can be dropped onto the IOT designer panel.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
public class IotPalettePanel extends AbstractPalettePanel<IotDeviceDatabase, IotMicrocontrollerEditor> {

  public IotPalettePanel(IotMicrocontrollerEditor editor) {
    super(editor);
  }

  // AbstractPalettePanel implementation
  @Override
  public SimplePalettePanel copy() {
    IotPalettePanel copy = new IotPalettePanel(editor);
    copy.setSize("100%", "100%");
    return copy;
  }
}
