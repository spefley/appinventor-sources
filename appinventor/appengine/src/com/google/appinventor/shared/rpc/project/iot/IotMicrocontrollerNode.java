// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.shared.rpc.project.iot;

import com.google.appinventor.shared.storage.StorageUtil;
import com.google.appinventor.shared.youngandroid.YoungAndroidSourceAnalyzer;

/**
 * IotMicrocontrollerNode is an abstract representation for a Microcontroller configuration
 * (designer) for the AIM for Things project.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
public class IotMicrocontrollerNode extends IotSourceNode {

  IotMicrocontrollerNode() {
  }

  public IotMicrocontrollerNode(String fileId) {
    super(StorageUtil.basename(fileId), fileId);
  }

  public static String getMicrocontrollerFileId(String qualifiedName) {
    return IOT_SRC_PREFIX + qualifiedName.replace('.', '/')
        + YoungAndroidSourceAnalyzer.MICROCONTROLLER_PROPERTIES_EXTENSION;
  }
}
