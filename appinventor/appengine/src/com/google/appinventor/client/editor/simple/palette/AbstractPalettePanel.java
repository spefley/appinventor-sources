// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.client.editor.simple.palette;

import com.google.appinventor.client.ComponentsTranslation;
import com.google.appinventor.client.editor.designer.DesignerEditor;
import com.google.appinventor.client.editor.simple.components.MockComponent;
import com.google.appinventor.client.editor.simple.components.utils.PropertiesUtil;
import com.google.appinventor.common.version.AppInventorFeatures;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.shared.simple.ComponentDatabaseChangeListener;
import com.google.appinventor.shared.simple.ComponentDatabaseInterface;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Base implementation for the palette panel.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
public abstract class AbstractPalettePanel<T extends ComponentDatabaseInterface, U extends DesignerEditor<?, ?, ?, T>> extends Composite implements SimplePalettePanel, ComponentDatabaseChangeListener {
  // Component database: information about components (including their properties and events)
  private final T componentDatabase;
  // Associated editor
  protected final U editor;
  protected final Map<ComponentCategory, PaletteHelper> paletteHelpers;
  private final StackPanel stackPalette;
  protected final Map<ComponentCategory, VerticalPanel> categoryPanels;
  // store Component Type along with SimplePaleteItem to enable removal of components
  private final Map<String, SimplePaletteItem> simplePaletteItems;
  private final ComponentFactory factory;
  private DropTargetProvider dropTargetProvider;

  protected AbstractPalettePanel(U editor, ComponentFactory componentFactory) {
    this(editor, componentFactory, ComponentCategory.values());
  }

  protected AbstractPalettePanel(U editor, ComponentFactory componentFactory,
                                 ComponentCategory... categories) {
    paletteHelpers = new HashMap<ComponentCategory, PaletteHelper>();
    categoryPanels = new HashMap<ComponentCategory, VerticalPanel>();
    simplePaletteItems = new HashMap<String, SimplePaletteItem>();
    componentDatabase = editor.getComponentDatabase();
    this.editor = editor;
    stackPalette = new StackPanel();
    factory = componentFactory;

    for (ComponentCategory category : categories) {
      if (showCategory(category)) {
        VerticalPanel categoryPanel = new VerticalPanel();
        categoryPanel.setWidth("100%");
        categoryPanels.put(category, categoryPanel);
        stackPalette.add(categoryPanel,
            ComponentsTranslation.getCategoryName(category.getName()));
      }
    }

    stackPalette.setWidth("100%");
    initWidget(stackPalette);
  }

  private static boolean showCategory(ComponentCategory category) {
    if (category == ComponentCategory.UNINITIALIZED) {
      return false;
    } else if (category == ComponentCategory.INTERNAL &&
        !AppInventorFeatures.showInternalComponentsCategory()) {
      return false;
    }
    return true;
  }

  /**
   * Loads all components to be shown on this palette.  Specifically, for
   * each component (except for those whose category is UNINITIALIZED, or
   * whose category is INTERNAL and we're running on a production server,
   * or who are specifically marked as not to be shown on the palette),
   * this creates a corresponding {@link SimplePaletteItem} with the passed
   * {@link DropTargetProvider} and adds it to the panel corresponding to
   * its category.
   *
   * @param dropTargetProvider provider of targets that palette items can be
   *                           dropped on
   */
  @Override
  public void loadComponents(DropTargetProvider dropTargetProvider) {
    this.dropTargetProvider = dropTargetProvider;
    for (String component : componentDatabase.getComponentNames()) {
      this.addComponent(component);
    }
  }

  private void loadComponents() {
    for (String component : componentDatabase.getComponentNames()) {
      this.addComponent(component);
    }
  }

  @Override
  public void configureComponent(MockComponent mockComponent) {
    String componentType = mockComponent.getType();
    PropertiesUtil.populateProperties(mockComponent, componentDatabase.getPropertyDefinitions(componentType), editor);

  }

  /**
   *  Loads a single Component to Palette. Used for adding Components.
   */
  @Override
  public void addComponent(String componentTypeName) {
    if (simplePaletteItems.containsKey(componentTypeName)) { // We are upgrading
      removeComponent(componentTypeName);
    }
    String helpString = componentDatabase.getHelpString(componentTypeName);
    String categoryDocUrlString = componentDatabase.getCategoryDocUrlString(componentTypeName);
    String categoryString = componentDatabase.getCategoryString(componentTypeName);
    Boolean showOnPalette = componentDatabase.getShowOnPalette(componentTypeName);
    Boolean nonVisible = componentDatabase.getNonVisible(componentTypeName);
    Boolean external = componentDatabase.getComponentExternal(componentTypeName);
    ComponentCategory category = ComponentCategory.valueOf(categoryString);
    if (showOnPalette && showCategory(category)) {
      SimplePaletteItem item = new SimplePaletteItem(
          new SimpleComponentDescriptor(componentTypeName, helpString, categoryDocUrlString,
              showOnPalette, nonVisible, external, factory),
            dropTargetProvider);
      simplePaletteItems.put(componentTypeName, item);
      addPaletteItem(item, category);
    }
  }

  public void removeComponent(String componentTypeName) {
    String categoryString = componentDatabase.getCategoryString(componentTypeName);
    ComponentCategory category = ComponentCategory.valueOf(categoryString);
    removePaletteItem(simplePaletteItems.get(componentTypeName), category);
  }

  /*
     * Adds a component entry to the palette.
     */
  private void addPaletteItem(SimplePaletteItem component, ComponentCategory category) {
    VerticalPanel panel = categoryPanels.get(category);
    PaletteHelper paletteHelper = paletteHelpers.get(category);
    if (paletteHelper != null) {
      paletteHelper.addPaletteItem(panel, component);
    } else {
      panel.add(component);
    }
  }

  private void removePaletteItem(SimplePaletteItem component, ComponentCategory category) {
    VerticalPanel panel = categoryPanels.get(category);
    panel.remove(component);
  }

  @Override
  public void onComponentTypeAdded(List<String> componentTypes) {
    for (String componentType : componentTypes) {
      this.addComponent(componentType);
    }
  }

  @Override
  public boolean beforeComponentTypeRemoved(List<String> componentTypes) {
    boolean result = true;
    for (String componentType : componentTypes) {
      this.removeComponent(componentType);
    }
    return result;
  }

  @Override
  public void onComponentTypeRemoved(Map<String, String> componentTypes) {

  }

  @Override
  public void onResetDatabase() {
    reloadComponents();
  }

  @Override
  public void clearComponents() {
    for (ComponentCategory category : categoryPanels.keySet()) {
      VerticalPanel panel = categoryPanels.get(category);
      panel.clear();
    }
  }

  @Override
  public void reloadComponents() {
    clearComponents();
    loadComponents();
  }

  @Override
  public Widget getWidget() {
    return this;
  }

  @Override
  public abstract SimplePalettePanel copy();

  @Override
  public MockComponent createMockComponent(String name) {
    return factory.createMockComponent(name);
  }
}
