// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.shared.rpc.project.iot;

import com.google.appinventor.shared.rpc.project.SourceFolderNode;

/**
 * Internet of Things source folder node in project tree.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
public class IotSourceFolderNode extends SourceFolderNode {
  private static final long serialVersionUID = 1L;

  /**
   * Serialization constructor.
   */
  IotSourceFolderNode() {
    super(null, null);
  }

  /**
   * Creates a new source folder node.
   */
  public IotSourceFolderNode(String fileId) {
    super("Sketches", fileId);
  }
}
