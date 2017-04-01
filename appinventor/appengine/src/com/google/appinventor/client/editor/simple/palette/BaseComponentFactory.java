// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.client.editor.simple.palette;

import com.google.appinventor.client.editor.designer.DesignerEditor;
import com.google.appinventor.client.editor.simple.components.MockBall;
import com.google.appinventor.client.editor.simple.components.MockButton;
import com.google.appinventor.client.editor.simple.components.MockCanvas;
import com.google.appinventor.client.editor.simple.components.MockCheckBox;
import com.google.appinventor.client.editor.simple.components.MockComponent;
import com.google.appinventor.client.editor.simple.components.MockContactPicker;
import com.google.appinventor.client.editor.simple.components.MockDatePicker;
import com.google.appinventor.client.editor.simple.components.MockEmailPicker;
import com.google.appinventor.client.editor.simple.components.MockFirebaseDB;
import com.google.appinventor.client.editor.simple.components.MockHorizontalArrangement;
import com.google.appinventor.client.editor.simple.components.MockImage;
import com.google.appinventor.client.editor.simple.components.MockImagePicker;
import com.google.appinventor.client.editor.simple.components.MockImageSprite;
import com.google.appinventor.client.editor.simple.components.MockLabel;
import com.google.appinventor.client.editor.simple.components.MockListPicker;
import com.google.appinventor.client.editor.simple.components.MockListView;
import com.google.appinventor.client.editor.simple.components.MockNonVisibleComponent;
import com.google.appinventor.client.editor.simple.components.MockPasswordTextBox;
import com.google.appinventor.client.editor.simple.components.MockPhoneNumberPicker;
import com.google.appinventor.client.editor.simple.components.MockRadioButton;
import com.google.appinventor.client.editor.simple.components.MockScrollHorizontalArrangement;
import com.google.appinventor.client.editor.simple.components.MockScrollVerticalArrangement;
import com.google.appinventor.client.editor.simple.components.MockSlider;
import com.google.appinventor.client.editor.simple.components.MockSpinner;
import com.google.appinventor.client.editor.simple.components.MockTableArrangement;
import com.google.appinventor.client.editor.simple.components.MockTextBox;
import com.google.appinventor.client.editor.simple.components.MockTimePicker;
import com.google.appinventor.client.editor.simple.components.MockVerticalArrangement;
import com.google.appinventor.client.editor.simple.components.MockVideoPlayer;
import com.google.appinventor.client.editor.simple.components.MockWebViewer;
import com.google.appinventor.shared.simple.ComponentDatabaseInterface;
import com.google.common.collect.Maps;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

import java.util.Map;

/**
 * Base implementation of ComponentFactory that can be subclassed for specific App Inventor editors.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
public class BaseComponentFactory implements ComponentFactory {
  /* We keep a static map of image names to images in the image bundle so
     * that we can avoid making individual calls to the server for static image
     * that are already in the bundle. This is purely an efficiency optimization
     * for mock non-visible components.
     */
  protected final DesignerEditor<?, ?, ?, ?> editor;
  private final Map<String, ImageResource> bundledImages;
  private final ComponentDatabaseInterface componentDatabase;
  private final Map<String, MockComponent> cachedComponents;

  public BaseComponentFactory(DesignerEditor<?, ?, ?, ?> editor,
                              Map<String, ImageResource> bundledImages) {
    this.editor = editor;
    this.bundledImages = bundledImages;
    componentDatabase = editor.getComponentDatabase();
    cachedComponents = Maps.newHashMap();
  }

  @Override
  public MockComponent createMockComponent(String name) {
    if (componentDatabase.getNonVisible(name)) {
      if(name.equals(MockFirebaseDB.TYPE)) {
        return new MockFirebaseDB(editor, name, getImage(name));
      } else {
        return new MockNonVisibleComponent(editor, name, getImage(name));
      }
    } else if (name.equals(MockButton.TYPE)) {
      return new MockButton(editor);
    } else if (name.equals(MockCanvas.TYPE)) {
      return new MockCanvas(editor);
    } else if (name.equals(MockCheckBox.TYPE)) {
      return new MockCheckBox(editor);
    } else if (name.equals(MockImage.TYPE)) {
      return new MockImage(editor);
    } else if (name.equals(MockLabel.TYPE)) {
      return new MockLabel(editor);
    } else if (name.equals(MockListView.TYPE)) {
      return new MockListView(editor);
    } else if (name.equals(MockSlider.TYPE)) {
      return new MockSlider(editor);
    } else if (name.equals(MockPasswordTextBox.TYPE)) {
      return new MockPasswordTextBox(editor);
    } else if (name.equals(MockRadioButton.TYPE)) {
      return new MockRadioButton(editor);
    } else if (name.equals(MockTextBox.TYPE)) {
      return new MockTextBox(editor);
    } else if (name.equals(MockContactPicker.TYPE)) {
      return new MockContactPicker(editor);
    } else if (name.equals(MockPhoneNumberPicker.TYPE)) {
      return new MockPhoneNumberPicker(editor);
    } else if (name.equals(MockEmailPicker.TYPE)) {
      return new MockEmailPicker(editor);
    } else if (name.equals(MockListPicker.TYPE)) {
      return new MockListPicker(editor);
    } else if (name.equals(MockDatePicker.TYPE)) {
      return new MockDatePicker(editor);
    } else if (name.equals(MockTimePicker.TYPE)) {
      return new MockTimePicker(editor);
    } else if (name.equals(MockHorizontalArrangement.TYPE)) {
      return new MockHorizontalArrangement(editor);
    } else if (name.equals(MockScrollHorizontalArrangement.TYPE)) {
      return new MockScrollHorizontalArrangement(editor);
    } else if (name.equals(MockVerticalArrangement.TYPE)) {
      return new MockVerticalArrangement(editor);
    } else if (name.equals(MockScrollVerticalArrangement.TYPE)) {
      return new MockScrollVerticalArrangement(editor);
    } else if (name.equals(MockTableArrangement.TYPE)) {
      return new MockTableArrangement(editor);
    } else if (name.equals(MockImageSprite.TYPE)) {
      return new MockImageSprite(editor);
    } else if (name.equals(MockBall.TYPE)) {
      return new MockBall(editor);
    } else if (name.equals(MockImagePicker.TYPE)) {
      return new MockImagePicker(editor);
    } else if (name.equals(MockVideoPlayer.TYPE)) {
      return new MockVideoPlayer(editor);
    } else if (name.equals(MockWebViewer.TYPE)) {
      return new MockWebViewer(editor);
    } else if (name.equals(MockSpinner.TYPE)) {
      return new MockSpinner(editor);
    } else {
      // TODO(user): add 3rd party mock component proxy here
      throw new UnsupportedOperationException("unknown component: " + name);
    }
  }

  public Image getImage(String componentName) {
    if (componentDatabase.getNonVisible(componentName)) {
      return getImageFromPath(componentDatabase.getIconName(componentName));
    } else {
      return getCachedMockComponent(componentName).getIconImage();
    }
  }

  private Image getImageFromPath(String iconPath) {
    if (bundledImages.containsKey(iconPath)) {
      return new Image(bundledImages.get(iconPath));
    } else {
      return new Image(iconPath);
    }
  }

  private MockComponent getCachedMockComponent(String name) {
    MockComponent cached = cachedComponents.get(name);
    if (cached != null) {
      return cached;
    }
    cached = createMockComponent(name);
    cachedComponents.put(name, cached);
    return cached;
  }
}
