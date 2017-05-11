// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2009-2011 Google, All Rights reserved
// Copyright © 2011-2017 Massachusetts Institute of Technology, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.jsdesigner;

import com.google.gwt.user.client.ui.HTMLPanel;

public class JSDesignerPanel extends HTMLPanel {
  public JSDesignerPanel(String html) {
    super(html);
  }

  public JSDesignerPanel() {
    super("<div>jsdesigner default div</div>");
  }
}
