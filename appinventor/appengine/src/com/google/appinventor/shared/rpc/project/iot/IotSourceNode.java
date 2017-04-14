// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.shared.rpc.project.iot;

import com.google.appinventor.shared.rpc.project.SourceNode;
import com.google.appinventor.shared.youngandroid.YoungAndroidSourceAnalyzer;

/**
 * Base class for all Internet of Things related source objects.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
public class IotSourceNode extends SourceNode {

  protected static final String IOT_SRC_PREFIX = YoungAndroidSourceAnalyzer.IOT_SRC_FOLDER + '/';

  IotSourceNode() {
  }

  IotSourceNode(String name, String fileId) {
    super(name, fileId);
  }
}
