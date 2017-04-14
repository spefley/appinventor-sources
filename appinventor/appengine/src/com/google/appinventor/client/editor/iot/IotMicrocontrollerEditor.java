// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.client.editor.iot;

import com.google.appinventor.client.editor.ProjectEditor;
import com.google.appinventor.client.editor.designer.DesignerEditor;
import com.google.appinventor.client.editor.simple.components.MockMicrocontroller;
import com.google.appinventor.client.editor.simple.palette.DropTargetProvider;
import com.google.appinventor.client.widgets.dnd.DropTarget;
import com.google.appinventor.shared.rpc.project.iot.IotMicrocontrollerNode;
import com.google.gwt.user.client.Command;

import java.util.List;

/**
 * Design editor for IOT sketches.
 *
 * @author ewpatton@mit.edu (Evan w. Patton)
 */
public class IotMicrocontrollerEditor extends DesignerEditor<IotMicrocontrollerNode, MockMicrocontroller, IotPalettePanel, IotDeviceDatabase> {
  public IotMicrocontrollerEditor(ProjectEditor projectEditor, IotMicrocontrollerNode sourceNode) {
    super(projectEditor, sourceNode, IotDeviceDatabase.getInstance(sourceNode.getProjectId()));

    palettePanel = new IotPalettePanel(this);
    palettePanel.loadComponents(new DropTargetProvider() {
      @Override
      public DropTarget[] getDropTargets() {
        List<DropTarget> dropTargets = root.getDropTargetsWithin();
        dropTargets.add(getNonVisibleComponentsPanel());
        return dropTargets.toArray(new DropTarget[dropTargets.size()]);
      }
    });
    palettePanel.setSize("100%", "100%");
    componentDatabaseChangeListeners.add(palettePanel);
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
