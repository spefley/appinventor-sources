// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 MIT, All rights reserved.

package com.google.appinventor.client.editor.iot;

import com.google.appinventor.client.Ode;
import com.google.appinventor.client.editor.simple.ComponentDatabase;
import com.google.appinventor.client.properties.json.ClientJsonParser;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

import java.util.HashMap;
import java.util.Map;

/**
 * Database holding information about IOT devices.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
public final class IotDeviceDatabase extends ComponentDatabase {

  private static final Map<Long, IotDeviceDatabase> instances =
    new HashMap<Long, IotDeviceDatabase>();

  public static IotDeviceDatabase getInstance(long projectId) {
    if (instances.containsKey(projectId)) {
      return instances.get(projectId);
    }
    IotDeviceDatabase db = new IotDeviceDatabase();
    instances.put(projectId, db);
    return db;
  }

  public static IotDeviceDatabase getInstance() {
    return getInstance(Ode.getInstance().getCurrentYoungAndroidProjectId());
  }

  public interface DeviceResource extends ClientBundle {
    @Source("com/google/appinventor/iot-devices.json")
    TextResource getIotDevices();
  }

  private static final DeviceResource deviceResources = GWT.create(DeviceResource.class);

  private IotDeviceDatabase() {
    super(new ClientJsonParser().parse(deviceResources.getIotDevices().getText()).asArray());
  }
}
