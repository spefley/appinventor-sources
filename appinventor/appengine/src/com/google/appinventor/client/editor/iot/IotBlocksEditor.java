// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved
package com.google.appinventor.client.editor.iot;

import com.google.appinventor.client.editor.blocks.BlocksCategory;
import com.google.appinventor.client.editor.blocks.BlocksCodeGenerationTarget;
import com.google.appinventor.client.editor.blocks.BlocksEditor;
import com.google.appinventor.client.editor.blocks.BlocksLanguage;
import com.google.appinventor.client.editor.youngandroid.YaProjectEditor;
import com.google.appinventor.shared.rpc.project.iot.IotBlocksNode;

/**
 * Editor for the IOT Blocks (.ibx) files.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
public final class IotBlocksEditor extends BlocksEditor<IotBlocksNode, IotMicrocontrollerEditor> {

  private static final BlocksLanguage IOT =
      new BlocksLanguage("IOT",
          new BlocksCategory("Control", IMAGES.control()),
          new BlocksCategory("Logic", IMAGES.logic()),
          new BlocksCategory("Math", IMAGES.math()),
          new BlocksCategory("Text", IMAGES.text()),
          new BlocksCategory("Lists", IMAGES.lists()),
          new BlocksCategory("Colors", IMAGES.colors()),
          new BlocksCategory("Variables", IMAGES.variables()),
          new BlocksCategory("Procedures", IMAGES.procedures()),
          new BlocksCategory("Input/Output", IMAGES.inputOutput()));

  IotBlocksEditor(YaProjectEditor projectEditor, IotBlocksNode blocksNode) {
    super(projectEditor, blocksNode, IOT, BlocksCodeGenerationTarget.IOTVM,
        IotDeviceDatabase.getInstance(blocksNode.getProjectId()));
  }

}
