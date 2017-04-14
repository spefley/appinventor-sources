// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.client.editor.iot.palette;

import com.google.appinventor.client.Images;
import com.google.appinventor.client.Ode;
import com.google.appinventor.client.editor.designer.DesignerEditor;
import com.google.appinventor.client.editor.simple.palette.BaseComponentFactory;
import com.google.common.collect.Maps;
import com.google.gwt.resources.client.ImageResource;

import java.util.Map;

/**
 * ComponentFactory implementation for Internet of Things.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
class IotDeviceFactory extends BaseComponentFactory {

  private static Map<String, ImageResource> bundledImages;

  static {
    Images images = Ode.getImageBundle();
    bundledImages = Maps.newHashMap();
    bundledImages.put("images/", images.bluetooth());
  }

  IotDeviceFactory(DesignerEditor<?, ?, ?, ?> editor) {
    super(editor, bundledImages);
  }
}
