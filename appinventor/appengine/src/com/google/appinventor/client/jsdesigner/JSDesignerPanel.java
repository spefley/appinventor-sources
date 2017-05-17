// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2009-2011 Google, All Rights reserved
// Copyright © 2011-2017 Massachusetts Institute of Technology, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.jsdesigner;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.user.client.Window;
import com.google.appinventor.shared.rpc.project.ProjectRootNode;
import com.google.appinventor.client.editor.designer.DesignerEditor;
import com.google.appinventor.client.editor.ProjectEditor;
import com.google.appinventor.client.editor.FileEditor;
import com.google.appinventor.client.Ode;


public class JSDesignerPanel extends HTMLPanel {
  public JSDesignerPanel() {
    super("<div id=\"root\"></div>");
    ScriptInjector.fromUrl("main.9de011e5.js").inject();
  }

  public void show() {
    Ode.getInstance().switchToJSDesignView();
  }

  public void loadFile(FileEditor fileEditor) {
    Window.alert(fileEditor.getRawFileContent());
  }
}
