// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

'use strict';

goog.provide('AI.Blockly.Blocks.InputOutput');
goog.require('Blockly.Blocks.Utilities');

/**
 *
 * @type {Blockly.BlockSvg}
 * @this {Blockly.BlockSvg}
 */
Blockly.Blocks.io_pin_mode = {
  category: 'Input/Output',
  init: function() {
    this.setColour(Blockly.IO_CATEGORY_HUE);
    this.setOutput(true, 'Boolean');
    this.appendDummyInput()
        .appendField(new Blockly.FieldDropdown(this.OPERATORS), 'MODE');
    this.setTooltip(function() {
      var op = this.getFieldValue('MODE');
      return Blockly.Blocks.io_pin_mode.TOOLTIPS()[op];
    }.bind(this));
  },
  helpUrl: function() {
    var op = this.getFieldValue('MODE');
    return Blockly.Blocks.io_pin_mode.HELPURLS()[op];
  },
  OPERATORS: function() {
    return [
      [Blockly.Msg.LANG_INPUTOUTPUT_PIN_MODE_INPUT, 'INPUT'],
      [Blockly.Msg.LANG_INPUTOUTPUT_PIN_MODE_OUTPUT, 'OUTPUT']
    ];
  },
  TOOLTIPS: function() {
    return {
      'INPUT': Blockly.Msg.LANG_INPUTOUTPUT_PIN_MODE_INPUT_TOOLTIP,
      'OUTPUT': Blockly.Msg.LANG_INPUTOUTPUT_PIN_MODE_OUTPUT_TOOLTIP
    };
  },
  HELPURLS: function() {
    return {
      'INPUT': Blockly.Msg.LANG_INPUTOUTPUT_PIN_MODE_INPUT_HELPURL,
      'OUTPUT': Blockly.Msg.LANG_INPUTOUTPUT_PIN_MODE_OUTPUT_HELPURL
    };
  },
  typeblock: []
};

/**
 *
 * @type {Blockly.BlockSvg}
 * @this {Blockly.BlockSvg}
 */
Blockly.Blocks.io_set_pin_mode = {
  category: 'Input/Output',
  helpUrl: '',
  /**
   * @this {Blockly.BlockSvg}
   */
  init: function () {
    this.setColour(Blockly.IO_CATEGORY_HUE);
    this.appendValueInput('MODE')
        .setCheck('Boolean')
        .appendField(Blockly.Msg.LANG_INPUTOUTPUT_SET_PIN_MODE_TITLE)
        .appendField(Blockly.Msg.LANG_INPUTOUTPUT_PIN)
        .appendField(new Blockly.FieldTextInput('0', Blockly.Blocks.io_set_pin_mode.validator))
        .appendField(Blockly.Msg.LANG_INPUTOUTPUT_FIELD_TO);
    this.setPreviousStatement(true);
    this.setNextStatement(true);
  },
  validator: function (text) {
    var n = window.parseFloat(text);
    return window.isNaN(n) ? null : String(n);
  },
  typeblock: []
};

Blockly.Blocks.io_set_pin_from_var_mode = {
  category: 'Input/Output',
  helpUrl: '',
  /**
   * @this {Blockly.BlockSvg}
   */
  init: function() {
    this.setColour(Blockly.IO_CATEGORY_HUE);
    this.appendValueInput('PIN')
        .setCheck('Number')
        .appendField(Blockly.Msg.LANG_INPUTOUTPUT_SET_PIN_MODE_TITLE)
        .appendField(Blockly.Msg.LANG_INPUTOUTPUT_PIN)
        .setAlign(Blockly.ALIGN_RIGHT);
    this.appendValueInput('MODE')
        .setCheck('Boolean')
        .appendField(Blockly.Msg.LANG_INPUTOUTPUT_FIELD_TO)
        .setAlign(Blockly.ALIGN_RIGHT);
    this.setPreviousStatement(true);
    this.setNextStatement(true);
  },
  typeblock: []
};

Blockly.Blocks.io_read_pin = {
  category: 'Input/Output',
  helpUrl: '',
  /**
   * @this {Blockly.BlockSvg}
   */
  init: function () {
    this.setColour(Blockly.IO_CATEGORY_HUE);
    this.setOutput(['Number','Boolean']);
    this.appendDummyInput('PIN')
        .appendField(Blockly.Msg.LANG_INPUTOUTPUT_READ_FROM_PIN_TITLE)
        .appendField(Blockly.Msg.LANG_INPUTOUTPUT_PIN)
        .appendField(new Blockly.FieldTextInput('0', Blockly.Blocks.io_read_pin.validator));
  },
  validator: Blockly.Blocks.io_set_pin_mode.validator,
  typeblock: []
};

Blockly.Blocks.io_read_pin_from_var = {
  category: 'Input/Output',
  helpUrl: '',
  /**
   * @this {Blockly.BlockSvg}
   */
  init: function() {
    this.setColour(Blockly.IO_CATEGORY_HUE);
    this.setOutput(['Number','Boolean']);
    this.appendValueInput('PIN')
        .setCheck('Number')
        .appendField(Blockly.Msg.LANG_INPUTOUTPUT_READ_FROM_PIN_TITLE)
        .appendField(Blockly.Msg.LANG_INPUTOUTPUT_PIN)
        .setAlign(Blockly.ALIGN_RIGHT);
  },
  typeblock: []
};

Blockly.Blocks.io_write_pin = {
  category: 'Input/Output',
  helpUrl: '',
  /**
   * @this {Blockly.BlockSvg}
   */
  init: function () {
    this.setColour(Blockly.IO_CATEGORY_HUE);
    this.setPreviousStatement(true);
    this.setNextStatement(true);
    this.appendValueInput('VALUE')
        .setCheck(['Number','Boolean'])
        .appendField(Blockly.Msg.LANG_INPUTOUTPUT_WRITE_TO_PIN_TITLE)
        .appendField(Blockly.Msg.LANG_INPUTOUTPUT_PIN)
        .appendField(new Blockly.FieldTextInput('0', Blockly.Blocks.io_write_pin.validator))
        .appendField(Blockly.Msg.LANG_INPUTOUTPUT_FIELD_VALUE);
  },
  validator: Blockly.Blocks.io_set_pin_mode.validator,
  typeblock: []
};

Blockly.Blocks.io_write_pin_from_var = {
  category: 'Input/Output',
  helpUrl: '',
  /**
   * @this {Blockly.BlockSvg}
   */
  init: function () {
    this.setColour(Blockly.IO_CATEGORY_HUE);
    this.setPreviousStatement(true);
    this.setNextStatement(true);
    this.appendValueInput('PIN')
        .setCheck('Number')
        .appendField(Blockly.Msg.LANG_INPUTOUTPUT_WRITE_TO_PIN_TITLE)
        .appendField(Blockly.Msg.LANG_INPUTOUTPUT_PIN)
        .setAlign(Blockly.ALIGN_RIGHT);
    this.appendValueInput('VALUE')
        .setCheck(['Number','Boolean'])
        .appendField(Blockly.Msg.LANG_INPUTOUTPUT_FIELD_VALUE)
        .setAlign(Blockly.ALIGN_RIGHT);
  }
};
