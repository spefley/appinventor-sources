// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.client.editor.youngandroid.palette;

import com.google.appinventor.client.Images;
import com.google.appinventor.client.Ode;
import com.google.appinventor.client.editor.designer.DesignerEditor;
import com.google.appinventor.client.editor.simple.palette.BaseComponentFactory;
import com.google.common.collect.Maps;
import com.google.gwt.resources.client.ImageResource;

import java.util.Map;

/**
 * Factory to create MockComponent instances for App Inventor applications.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
class YoungAndroidComponentFactory extends BaseComponentFactory {

  private static final Map<String, ImageResource> bundledImages;

  static {
    Images images = Ode.getImageBundle();
    bundledImages = Maps.newHashMap();
    bundledImages.put("images/accelerometersensor.png", images.accelerometersensor());
    bundledImages.put("images/gyroscopesensor.png", images.gyroscopesensor());
    bundledImages.put("images/nearfield.png", images.nearfield());
    bundledImages.put("images/activityStarter.png", images.activitystarter());
    bundledImages.put("images/barcodeScanner.png", images.barcodeScanner());
    bundledImages.put("images/bluetooth.png", images.bluetooth());
    bundledImages.put("images/camera.png", images.camera());
    bundledImages.put("images/camcorder.png", images.camcorder());
    bundledImages.put("images/clock.png", images.clock());
    bundledImages.put("images/fusiontables.png", images.fusiontables());
    bundledImages.put("images/gameClient.png", images.gameclient());
    bundledImages.put("images/locationSensor.png", images.locationSensor());
    bundledImages.put("images/notifier.png", images.notifier());
    bundledImages.put("images/legoMindstormsNxt.png", images.legoMindstormsNxt());
    bundledImages.put("images/legoMindstormsEv3.png", images.legoMindstormsEv3());
    bundledImages.put("images/orientationsensor.png", images.orientationsensor());
    bundledImages.put("images/pedometer.png", images.pedometerComponent());
    bundledImages.put("images/phoneip.png", images.phonestatusComponent());
    bundledImages.put("images/phoneCall.png", images.phonecall());
    bundledImages.put("images/player.png", images.player());
    bundledImages.put("images/soundEffect.png", images.soundeffect());
    bundledImages.put("images/soundRecorder.png", images.soundRecorder());
    bundledImages.put("images/speechRecognizer.png", images.speechRecognizer());
    bundledImages.put("images/textToSpeech.png", images.textToSpeech());
    bundledImages.put("images/texting.png", images.texting());
    bundledImages.put("images/datePicker.png", images.datePickerComponent());
    bundledImages.put("images/timePicker.png", images.timePickerComponent());
    bundledImages.put("images/tinyDB.png", images.tinyDB());
    bundledImages.put("images/file.png", images.file());
    bundledImages.put("images/tinyWebDB.png", images.tinyWebDB());
    bundledImages.put("images/firebaseDB.png", images.firebaseDB());
    bundledImages.put("images/twitter.png", images.twitterComponent());
    bundledImages.put("images/voting.png", images.voting());
    bundledImages.put("images/web.png", images.web());
    bundledImages.put("images/mediastore.png", images.mediastore());
    bundledImages.put("images/sharing.png", images.sharingComponent());
    bundledImages.put("images/spinner.png", images.spinner());
    bundledImages.put("images/listView.png", images.listview());
    bundledImages.put("images/yandex.png", images.yandex());
    bundledImages.put("images/proximitysensor.png", images.proximitysensor());
    bundledImages.put("images/extension.png", images.extension());
  }

  YoungAndroidComponentFactory(DesignerEditor<?, ?, ?, ?> editor) {
    super(editor, bundledImages);
  }

}
