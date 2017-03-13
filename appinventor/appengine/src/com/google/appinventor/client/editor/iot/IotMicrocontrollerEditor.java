// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.
package com.google.appinventor.client.editor.iot;

import com.google.appinventor.client.editor.ProjectEditor;
import com.google.appinventor.client.editor.designer.DesignerEditor;
import com.google.appinventor.client.editor.simple.components.MockMicrocontroller;
import com.google.appinventor.shared.rpc.project.iot.IotMicrocontrollerNode;
import com.google.gwt.user.client.Command;

/**
 * Created by ewpatton on 3/31/17.
 */
public class IotMicrocontrollerEditor extends DesignerEditor<IotMicrocontrollerNode, MockMicrocontroller, IotPalettePanel> {
  public IotMicrocontrollerEditor(ProjectEditor projectEditor, IotMicrocontrollerNode sourceNode) {
    super(projectEditor, sourceNode, IotDeviceDatabase.getInstance(sourceNode.getProjectId()));
  }

  @Override
  public String getRawFileContent() {
    return null;
  }

  @Override
  public String getJson() {
    return null;
  }

  @Override
  protected void upgradeFile(FileContentHolder fileContentHolder, Command afterUpgradeCompleted) {

  }
}
