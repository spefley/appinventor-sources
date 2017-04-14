// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.
package com.google.appinventor.shared.rpc.project.iot;

import com.google.appinventor.shared.storage.StorageUtil;
import com.google.appinventor.shared.youngandroid.YoungAndroidSourceAnalyzer;

/**
 * Created by ewpatton on 3/31/17.
 */
public final class IotBlocksNode extends IotSourceNode {

  public IotBlocksNode() {
  }

  /**
   * Creates a new Young Android source file project node.
   *
   * @param fileId file id
   */
  public IotBlocksNode(String fileId) {
    super(StorageUtil.basename(fileId), fileId);
  }

  public static String getBlocksFileId(String qualifiedSketchName) {
    return IOT_SRC_PREFIX + qualifiedSketchName.replace('.', '/')
        + YoungAndroidSourceAnalyzer.IOTVM_BLOCKS_EXTENSION;
  }
}
