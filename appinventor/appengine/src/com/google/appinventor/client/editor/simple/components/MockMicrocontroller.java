// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.client.editor.simple.components;

import com.google.appinventor.client.editor.designer.DesignerChangeListener;
import com.google.appinventor.client.editor.designer.DesignerRootComponent;
import com.google.appinventor.client.editor.simple.SimpleEditor;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by ewpatton on 3/31/17.
 */
public class MockMicrocontroller extends MockContainer implements DesignerRootComponent {
  /**
   * Creates a new component container.
   * <p>
   * Implementations are responsible for constructing their own visual appearance
   * and calling {@link #initWidget(Widget)}.
   * This appearance should include {@link #rootPanel} so that children
   * components are displayed correctly.
   *
   * @param editor editor of source file the component belongs to
   * @param type
   * @param icon
   * @param layout
   */
  MockMicrocontroller(SimpleEditor editor, String type, ImageResource icon, MockLayout layout) {
    super(editor, type, icon, layout);
  }

  @Override
  public TreeItem buildComponentsTree() {
    return null;
  }

  @Override
  public MockComponent getSelectedComponent() {
    return null;
  }

  @Override
  public void addDesignerChangeListener(DesignerChangeListener listener) {

  }

  @Override
  public void removeDesignerChangeListener(DesignerChangeListener listener) {

  }

  @Override
  public MockComponent asMockComponent() {
    return null;
  }
}
