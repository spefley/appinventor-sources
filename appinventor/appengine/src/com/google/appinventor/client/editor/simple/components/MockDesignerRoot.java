// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.client.editor.simple.components;

import com.google.appinventor.client.editor.designer.DesignerChangeListener;
import com.google.appinventor.client.editor.designer.DesignerRootComponent;
import com.google.appinventor.client.editor.simple.SimpleEditor;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.TreeItem;

import java.util.HashSet;
import java.util.Set;

/**
 * Base container implementation for those components that serve as the root of a designer view.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
public abstract class MockDesignerRoot extends MockContainer implements DesignerRootComponent {
  private final Set<DesignerChangeListener> changeListeners = new HashSet<>();
  private MockComponent selectedComponent;

  MockDesignerRoot(SimpleEditor editor, String type, ImageResource icon, MockLayout layout) {
    super(editor, type, icon, layout);
    selectedComponent = this;
  }

  public abstract void refresh();

  @Override
  public DesignerRootComponent getRoot() {
    return this;
  }

  @Override
  public boolean isRoot() {
    return true;
  }

  @Override
  public TreeItem buildComponentsTree() {
    return buildTree();
  }

  @Override
  public MockComponent getSelectedComponent() {
    return selectedComponent;
  }

  @Override
  public void addDesignerChangeListener(DesignerChangeListener listener) {
    changeListeners.add(listener);
  }

  @Override
  public void removeDesignerChangeListener(DesignerChangeListener listener) {
    changeListeners.remove(listener);
  }

  @Override
  public MockComponent asMockComponent() {
    return this;
  }

  /**
   * Changes the component that is currently selected in the root.
   * <p>
   * There will always be exactly one component selected in a root
   * at any given time.
   */
  public final void setSelectedComponent(MockComponent newSelectedComponent) {
    MockComponent oldSelectedComponent = selectedComponent;

    if (newSelectedComponent == null) {
      throw new IllegalArgumentException("at least one component must always be selected");
    }
    if (newSelectedComponent == oldSelectedComponent) {
      return;
    }

    selectedComponent = newSelectedComponent;

    if (oldSelectedComponent != null) {     // Can be null initially
      oldSelectedComponent.onSelectedChange(false);
    }
    newSelectedComponent.onSelectedChange(true);
  }

  /**
   * Triggers a component property change event to be sent to the listener on the listener list.
   */
  public void fireComponentPropertyChanged(MockComponent component,
                                           String propertyName, String propertyValue) {
    for (DesignerChangeListener listener : changeListeners) {
      listener.onComponentPropertyChanged(component, propertyName, propertyValue);
    }
  }

  /**
   * Triggers a component removed event to be sent to the listener on the listener list.
   */
  public void fireComponentRemoved(MockComponent component, boolean permanentlyDeleted) {
    for (DesignerChangeListener listener : changeListeners) {
      listener.onComponentRemoved(component, permanentlyDeleted);
    }
  }

  /**
   * Triggers a component added event to be sent to the listener on the listener list.
   */
  public void fireComponentAdded(MockComponent component) {
    for (DesignerChangeListener listener : changeListeners) {
      listener.onComponentAdded(component);
    }
  }

  /**
   * Triggers a component renamed event to be sent to the listener on the listener list.
   */
  public void fireComponentRenamed(MockComponent component, String oldName) {
    for (DesignerChangeListener listener : changeListeners) {
      listener.onComponentRenamed(component, oldName);
    }
  }

  /**
   * Triggers a component selection change event to be sent to the listener on the listener list.
   */
  public void fireComponentSelectionChange(MockComponent component, boolean selected) {
    for (DesignerChangeListener listener : changeListeners) {
      listener.onComponentSelectionChange(component, selected);
    }
  }
}
