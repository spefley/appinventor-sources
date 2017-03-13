// -*- mode: javascript; js-indent-level: 2; -*-
// Copyright © 2017 MIT, All rights reserved
/**
 * @license
 * @fileoverview iot-device.js defines Device blocks for the IOT sketch editor in App Inventor.
 * @author Evan W. Patton (ewpatton@mit.edu)
 */

'use strict';

goog.provide('AI.Blockly.Blocks.Devices');
goog.provide('AI.Blockly.DeviceBlock');
goog.require('Blockly.Blocks.Utilities');

AI.Blockly.DeviceBlock.COLOUR_EVENT = Blockly.CONTROL_CATEGORY_HUE;
AI.Blockly.DeviceBlock.COLOUR_METHOD = Blockly.PROCEDURE_CATEGORY_HUE;
AI.Blockly.DeviceBlock.COLOUR_GET = '#439970';
AI.Blockly.DeviceBlock.COLOUR_SET = '#266643';
AI.Blockly.DeviceBlock.COLOUR_COMPONENT = '#439970';

AI.Blockly.DeviceBlock.DEVICE_SELECTOR = 'DEVICE_SELECTOR';

/**
 * Block definition for IOT devices.
 * @type {Object.<string, *>}
 * @extends Blockly.BlockSvg
 */
Blockly.Blocks.iot_device_event = {
  category: 'Device',
  blockType: 'event',
  mutationToDom: function() {
    var container = document.createElement('mutation');
    container.setAttribute('device_type', this.typeName);
    container.setAttribute('instance_id', this.instanceId);
    container.setAttribute('event_name', this.eventName);
    if (!this.horizontalParameters) {
      container.setAttribute('vertical_parameters', 'true');
    }
    return container;
  },
  /**
   * Transform an XML element for <code>&lt;mutation&gt;</code> into a block mutation.
   * @param {Element} xmlElement
   */
  domToMutation: function(xmlElement) {
    this.typeName = xmlElement.getAttribute('device_type');
    this.instanceId = xmlElement.getAttribute('instance_id');
    this.eventName = xmlElement.getAttribute('event_name');
    this.setColour(AI.Blockly.DeviceBlock.COLOUR_EVENT);

    var localizedEventName,
      eventType = this.getEventTypeObject(),
      deviceDb = this.getTopWorkspace().getDeviceDatabase();
    if (eventType) {
      localizedEventName = deviceDb.getInternationalizedEventName(eventType.name);
    } else {
      localizedEventName = deviceDb.getInternationalizedEventName(this.eventName);
    }

    this.deviceDropDown = Blockly.DeviceBlock.createDeviceDropDown(this);
    this.appendDummyInput('WHENTITLE').appendField(Blockly.Msg.LANG_COMPONENT_BLOCK_TITLE_WHEN)
      .appendField(this.deviceDropDown, AI.Blockly.DeviceBlock.DEVICE_SELECTOR)
      .appendField('.' + localizedEventName);
    this.deviceDropDown.setValue(this.instanceId);
    this.setParameterOrientation(xmlElement.getAttribute('vertical_parameters') !== 'true');
    this.setTooltip(eventType ? eventType.description : Blockly.Msg.UNDEFINED_BLOCK_TOOLTIP);
    this.setPreviousStatement(false, null);
    this.setNextStatement(false, null);

    if (!eventType || eventType.deprecated === true || eventType.deprecated === 'true' &&
        this.workspace == Blockly.mainWorkspace) {
      this.badBlock();
      this.setDisabled(true);
    }
    this.verify();
  },
  setParameterOrientation: function(isHorizontal) {
    var params = this.getParameters();
    if (!params) {
      params = [];
    }
    var deviceDb = this.getTopWorkspace().getDeviceDatabase();
    var oldDoInput = this.getInput('DO');
    if (!oldDoInput || (isHorizontal !== this.horizontalParameters && params.length > 0)) {
      this.horizontalParameters = isHorizontal;

      var bodyConnection = null, i, param, newDoInput;
      if (oldDoInput) {
        bodyConnection = oldDoInput.connection.targetConnection;
      }
      if (this.horizontalParameters) {
        if (oldDoInput) {
          for (i = 0; i < params.length; i++) {
            this.removeInput('VAR' + i);  // vertical parameters
          }
          this.removeInput('DO');
        }

        if (params.length > 0) {
          var paramInput = this.appendDummyInput('PARAMETERS')
            .appendField(" ")
            .setAlign(Blockly.ALIGN_LEFT);
          for (i = 0; param = params[i]; i++) {
            paramInput.appendField(new Blockly.FieldParameterFlydown(deviceDb.getInternationalizedParameterName(param.name), false, null, null, param.name),
              'VAR' + i)
              .appendField(' ');
          }
        }

        newDoInput = this.appendStatementInput('DO')
          .appendField(Blockly.Msg.LANG_COMPONENT_BLOCK_TITLE_DO);
        if (bodyConnection) {
          newDoInput.connection.connect(bodyConnection);
        }
      } else {
        if (oldDoInput) {
          this.removeInput('PARAMETERS');
          this.removeInput('DO');
        }

        for (i = 0; param = params[i]; i++) {
          this.appendDummyInput('VAR' + i)
            .appendField(new Blockly.FieldParameterFlydown(deviceDb.getInternationalizedPropertyName(para.name), false, null, null, param.name),
              'VAR' + i)
            .setAlign(Blockly.ALIGN_RIGHT);
        }

        newDoInput = this.appendStatementInput('DO')
          .appendField(Blockly.Msg.LANG_COMPONENT_BLOCK_TITLE_DO);
        if (bodyConnection) {
          newDoInput.connection.connect(bodyConnection);
        }
      }
    }
  },
  getParameters: function() {
    /** @type {EventDescriptor} */
    var eventType = this.getEventTypeObject();
    return eventType && eventType.parameters;
  },
  rename: function(id, newName) {
    if (this.instanceId == id) {
      this.deviceDropDown.setText(newName);
      Blockly.Blocks.Utilities.renameCollapsed(this, 0);
      return true;
    }
    return false;
  },
  renameVar: function(oldName, newName) {

  },
  helpUrl: function() {
    return Blockly.Blocks.METHODS_HELPURLS[this.typeName];
  },
  getVars: function() {
    var varList = [];
    for (var i = 0, input; input = this.getFieldValue('VAR' + i); i++) {
      varList.push(input);
    }
    return varList;
  },
  getVarString: function() {
    var varString = '';
    for (var i = 0, param; param = this.getFieldValue('VAR' + i); i++) {
      if (i != 0) {
        varString += ' ';
      }
      varString += param;
    }
    return varString;
  },
  declaredNames: function() {
    return this.getVars();
  },
  blocksInScope: function() {
    var doBlock = this.getInputTargetBlock('DO');
    if (doBlock) {
      return [doBlock];
    } else {
      return [];
    }
  },
  getEventTypeObject: function() {
    return this.getTopWorkspace().getDeviceDatabase().getEventForType(this.typeName, this.eventName);
  },
  typeblock: function() {

  },
  customContextMenu: function(options) {
    Blockly.FieldParameterFlydown.addHorizontalVerticalOption(this, options);
  },
  verify: function() {

  },
  errors: [{name:'checkIfUndefinedBlock'},{name:'checkIfIAmADuplicateEventHandler'},
    {name:'checkComponentNotExistsError'}],
  onchange: function(e) {
    if (e.isTransient) {
      return false;
    }
    return this.workspace.getWarningHandler() &&
      this.workspace.getWarningHandler().checkErrors(this);
  }
};

Blockly.DeviceBlock.DEVICE_SELECTOR = 'DEVICE_SELECTOR';
