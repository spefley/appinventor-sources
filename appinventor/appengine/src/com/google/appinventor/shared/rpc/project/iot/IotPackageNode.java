// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reseved.

package com.google.appinventor.shared.rpc.project.iot;

import com.google.appinventor.shared.rpc.project.PackageNode;

/**
 * Internet of Things sketch directory in project tree.
 */
public class IotPackageNode extends PackageNode {

  IotPackageNode() {
  }

  public IotPackageNode(String name, String packageId) {
    super(name, packageId);
  }

  public String getPackageName() {
    return getName();
  }
}
