// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.client.editor.designer;

import com.google.appinventor.client.editor.simple.components.MockComponent;
import com.google.appinventor.client.widgets.dnd.DropTarget;
import com.google.gwt.user.client.ui.TreeItem;

import java.util.List;
import java.util.Map;

/**
 * DesignerRootComponent is an interface that should be implemented by any MockComponent that
 * acts as the root component of a designer's hierarchy.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
public interface DesignerRootComponent {
  /**
   * Builds a tree of the component hierarchy of the root for display in the
   * {@code SourceStructureExplorer}.
   *
   * @return  tree showing the component hierarchy of the root
   */
  TreeItem buildComponentsTree();

  /**
   * Gets the currently selected component in the designer.
   *
   * @return  the currently selected component
   */
  MockComponent getSelectedComponent();

  /**
   * Adds an {@link DesignerChangeListener} to the listener set if it isn't already in there.
   *
   * @param listener  the {@code DesignerChangeListener} to be added
   */
  void addDesignerChangeListener(DesignerChangeListener listener);

  /**
   * Removes an {@link DesignerChangeListener} from the listener list.
   *
   * @param listener  the {@code DesignerChangeListener} to be removed
   */
  void removeDesignerChangeListener(DesignerChangeListener listener);

  /**
   * If this component isn't a Form, and this component's type isn't already in typesAndIcons,
   * adds this component's type name as a key to typesAndIcons, mapped to the HTML string used
   * to display the component type's icon. Subclasses that contain components should override
   * this to add their own info as well as that for their contained components.
   *
   * @param typesAndIcons
   */
  void collectTypesAndIcons(Map<String, String> typesAndIcons);

  /**
   * View the DesignerRootComponent as a MockComponent.
   *
   * @return the MockComponent at the root of the designer tree
   */
  MockComponent asMockComponent();

  /**
   * Add a component, typically a non-visible component, to the designer's root component.
   *
   * @param sourceComponent the component to add
   */
  void addComponent(MockComponent sourceComponent);

  /**
   * Changes the component that is currently selected in the root.
   * <p>
   * There will always be exactly one component selected in a root
   * at any given time.
   */
  void setSelectedComponent(MockComponent component);

  List<DropTarget> getDropTargetsWithin();

  void fireComponentAdded(MockComponent component);

  void fireComponentSelectionChange(MockComponent component, boolean selected);

  void fireComponentPropertyChanged(MockComponent component, String propertyName,
                                    String propertyValue);

  void fireComponentRenamed(MockComponent component, String oldName);

  void fireComponentRemoved(MockComponent component, boolean permanentlyDeleted);

  void select();
}
