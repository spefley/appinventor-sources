// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.jseditor;

import static com.google.appinventor.client.Ode.MESSAGES;
import com.google.appinventor.client.widgets.boxes.Box;

/**
 * Box implementation for source structure explorer.
 *
 */
public final class JSEditorBox extends Box {
    private static final JSEditorBox INSTANCE = new JSEditorBox();

    private final JSEditorPanel jsEditorPanel;

    public static JSEditorBox getJSEditorBox() {
        return INSTANCE;
    }

    private JSEditorBox() {
        super(MESSAGES.jsEditorBoxCaption(),
            300,    // height
            false,  // minimizable
            false); // highlightCaption
        
        jsEditorPanel = new JSEditorPanel("<span>HI IT'S ME SPEFLEY</span>");

        setContent(jsEditorPanel);
    }

   public JSEditorPanel getJSEditorPanel() {
       return jsEditorPanel;
   } 
}