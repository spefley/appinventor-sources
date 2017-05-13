// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.jsdesigner;

import static com.google.appinventor.client.Ode.MESSAGES;
import com.google.appinventor.client.widgets.boxes.Box;

/**
 * Box implementation for source structure explorer.
 *
 */
public final class JSDesignerBox extends Box {
    private static final JSDesignerBox INSTANCE = new JSDesignerBox();

    private final JSDesignerPanel jsDesignerPanel;

    public static JSDesignerBox getJSDesignerBox() {
        return INSTANCE;
    }

    private JSDesignerBox() {
        super(MESSAGES.jsDesignerBoxCaption(),
            300,    // height
            false,  // minimizable
            false); // highlightCaption
        
        jsDesignerPanel = new JSDesignerPanel();

        setContent(jsDesignerPanel);
    }

   public JSDesignerPanel getJSDesignerPanel() {
       return jsDesignerPanel;
   } 
}