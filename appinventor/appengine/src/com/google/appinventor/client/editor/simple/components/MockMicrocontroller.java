// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.client.editor.simple.components;

import com.google.appinventor.client.Ode;
import com.google.appinventor.client.editor.designer.DesignerChangeListener;
import com.google.appinventor.client.editor.designer.DesignerRootComponent;
import com.google.appinventor.client.editor.simple.SimpleEditor;
import com.google.appinventor.client.properties.json.ClientJsonParser;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.shared.properties.json.JSONArray;
import com.google.appinventor.shared.properties.json.JSONObject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MockMicrocontroller provides the root of IOT-related editors.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
public class MockMicrocontroller extends MockDesignerRoot {
  public static final String TYPE = "Microcontroller";

  private static final String PROPERTY_NAME_TARGET = "Target";

  public interface MicrocontrollerDefinitions extends ClientBundle {
    @Source("com/google/appinventor/microcontrollers.json")
    TextResource getMicrocontrollers();
  }

  private static class Microcontroller {
    String name;
    int memory;
    int progMemory;
    int digitalPins;
    List<Integer> pwmPins;
    int analogPins;
    String imagePath;
  }

  private static final Map<String, Microcontroller> microcontrollers = new TreeMap<>();

  static {
    MicrocontrollerDefinitions definitions = GWT.create(MicrocontrollerDefinitions.class);
    try {
      JSONArray mcJson = new ClientJsonParser().parse(definitions.getMicrocontrollers().getText()).asArray();
      for (int i = 0; i < mcJson.size(); i++) {
        JSONObject o = mcJson.get(i).asObject();
        Microcontroller mc = new Microcontroller();
        mc.name = o.get("name").asString().getString();
        mc.memory = o.get("memory").asNumber().getInt();
        mc.progMemory = o.get("progMemory").asNumber().getInt();
        mc.digitalPins = o.get("digitalPins").asNumber().getInt();
        mc.analogPins = o.get("analogPins").asNumber().getInt();
        mc.imagePath = o.get("image").asString().getString();
        mc.pwmPins = new ArrayList<>();
        JSONArray pwmPins = o.get("pwmPins").asArray();
        for (int j = 0; j < pwmPins.size(); j++) {
          mc.pwmPins.add(pwmPins.get(i).asNumber().getInt());
        }
        microcontrollers.put(mc.name, mc);
      }
    } catch(JSONException e) {
      throw new IllegalStateException(e);
    }
  }

  // Microcontroller UI components
  private final Image image;
  private final ListBox microcontrollerList;

  /**
   * Creates a new component container.
   * <p>
   * Implementations are responsible for constructing their own visual appearance
   * and calling {@link #initWidget(Widget)}.
   * This appearance should include {@link #rootPanel} so that children
   * components are displayed correctly.
   *
   * @param editor editor of source file the component belongs to
   */
  public MockMicrocontroller(SimpleEditor editor) {
    super(editor, TYPE, Ode.getImageBundle().microcontroller(),
        new MockHVLayout(ComponentConstants.LAYOUT_ORIENTATION_VERTICAL));

    AbsolutePanel microcontrollerWidget = new AbsolutePanel();
    microcontrollerWidget.setStylePrimaryName("ode-SimpleMockMicrocontroller");

    image = new Image();
    image.setWidth("404px");
    microcontrollerList = new ListBox();
    for (String id : microcontrollers.keySet()) {
      microcontrollerList.addItem(id);
    }
    microcontrollerList.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        changeProperty(PROPERTY_NAME_TARGET, microcontrollerList.getSelectedItemText());
      }
    });
    microcontrollerWidget.add(microcontrollerList);

    image.setUrl(microcontrollers.get(microcontrollerList.getSelectedItemText()).imagePath);
    getRootPanel().add(image);
    microcontrollerWidget.add(getRootPanel());

    initComponent(microcontrollerWidget);

    unsinkEvents(Event.ONMOUSEDOWN);  // to allow dropdown to function
  }

  @Override
  public void refresh() {
  }

  @Override
  public boolean isPropertyVisible(String propertyName) {
    if (PROPERTY_NAME_WIDTH.equals(propertyName) ||
        PROPERTY_NAME_HEIGHT.equals(propertyName) ||
        PROPERTY_NAME_TARGET.equals(propertyName)) {
      return false;
    }
    return super.isPropertyVisible(propertyName);
  }

  @Override
  public void onPropertyChange(String propertyName, String newValue) {
    if (PROPERTY_NAME_TARGET.equals(propertyName)) {
      image.setUrl(microcontrollers.get(microcontrollerList.getSelectedItemText()).imagePath);
    }
    super.onPropertyChange(propertyName, newValue);
  }

}
