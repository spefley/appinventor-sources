// -*- mode: javascript; js-indent-level: 2; -*-
// Copyright © MIT, All rights reserved
/**
 * @license
 * @fileoverview math.js translates the Blockly math blocks into opcodes for the App Inventor IOT
 * Embedded Companion.
 * @author Evan W. Patton (ewpatton@mit.edu)
 */

'use strict';

goog.provide('AI.Blockly.IOT.math');

AI.Blockly.IOT.ORDER_ATOMIC = 0;
AI.Blockly.IOT.ORDER_GROUPING = 1;
AI.Blockly.IOT.ORDER_UNARY = 2;
AI.Blockly.IOT.ORDER_MULTIPLY = 3;
AI.Blockly.IOT.ORDER_ADD = 4;
AI.Blockly.IOT.ORDER_SHIFT = 5;
AI.Blockly.IOT.ORDER_COMPARISON = 6;
AI.Blockly.IOT.ORDER_EQUALITY = 7;
AI.Blockly.IOT.ORDER_BITAND = 8;
AI.Blockly.IOT.ORDER_BITXOR = 9;
AI.Blockly.IOT.ORDER_BITOR = 10;
AI.Blockly.IOT.ORDER_AND = 11;
AI.Blockly.IOT.ORDER_OR = 12;
AI.Blockly.IOT.ORDER_TERNARY = 13;
AI.Blockly.IOT.ORDER_ASSIGNMENT = 14;
AI.Blockly.IOT.ORDER_COMMA = 15;
AI.Blockly.IOT.ORDER_NONE = 99;

AI.Blockly.IOT['math_number'] = function() {
  var num = this.getFieldValue('NUM');
  var parsedNum = window.parseFloat(num);
  if (num.indexOf('.') >= 0) {
    // floating point
    if (parsedNum == 0.0) {
      return [0x0B, AI.Blockly.IOT.ORDER_ATOMIC];
    } else if (parsedNum == 1.0) {
      return [0x0C, AI.Blockly.IOT.ORDER_ATOMIC];
    } else if (parsedNum == 2.0) {
      return [0x0D, AI.Blockly.IOT.ORDER_ATOMIC];
    }
  } else {
    if (parsedNum == -1) {
      return [0x02, AI.Blockly.IOT.ORDER_ATOMIC];
    } else if (parsedNum == 0) {
      return [0x03, AI.Blockly.IOT.ORDER_ATOMIC];
    } else if (parsedNum == 1) {
      return [0x04, AI.Blockly.IOT.ORDER_ATOMIC];
    } else if (parsedNum == 2) {
      return [0x05, AI.Blockly.IOT.ORDER_ATOMIC];
    } else if (parsedNum == 3) {
      return [0x06, AI.Blockly.IOT.ORDER_ATOMIC];
    } else if (parsedNum == 4) {
      return [0x07, AI.Blockly.IOT.ORDER_ATOMIC];
    } else if (parsedNum == 5) {
      return [0x08, AI.Blockly.IOT.ORDER_ATOMIC];
    }
  }
};

AI.Blockly.IOT['math_compare'] = function() {
  return ['compare', AI.Blockly.IOT.ORDER_NONE];
};

AI.Blockly.IOT['math_arithmetic'] = function(mode) {
  var tuple = AI.Blockly.IOT.math_arithmetic.OPERATORS[mode],
      operator = tuple[0],
      order = tuple[1],
      code = [],
      argument0, argument1;
  argument0 = AI.Blockly.IOT.valueToCode(this, 'A', order) || [0x03, AI.Blockly.IOT.ORDER_ATOMIC];
  argument1 = AI.Blockly.IOT.valueToCode(this, 'B', order) || [0x03, AI.Blockly.IOT.ORDER_ATOMIC];
  argument0.pop();
  argument1.pop();
  code.concat(argument0, argument1, operator, order);
};

AI.Blockly.IOT.math_arithmetic.OPERATORS = {
  ADD: [0x60],
  SUBTRACT: [0x64],
  MULTIPLY: [0x68],
  DIVIDE: [0x6C],
  POWER: []
};

AI.Blockly.IOT['math_arithmetic_list'] = function(mode) {
  var tuple = AI.Blockly.IOT.math_arithmetic.OPERATORS[mode],
      operator = tuple[0],
      order = tuple[1],
      code = [],
      i;
  for (i = 0; i < this.itemCount_; i++) {
    var value = AI.Blockly.IOT.valueToCode(this, 'NUM' + i, order);
    value.pop();
    code.concat(value);
  }
  for (i = 0; i < this.itemCount_ - 1; i++) {
    code.push(operator);
  }
  code.push(order);
};

AI.Blockly.IOT['math_add'] = function() {
  return AI.Blockly.IOT['math_arithmetic_list'].call(this, 'ADD');
};

AI.Blockly.IOT['math_subtract'] = function() {
  return AI.Blockly.IOT['math_arithmetic'].call(this, 'SUBTRACT');
};

AI.Blockly.IOT['math_multiply'] = function() {
  return AI.Blockly.IOT['math_arithmetic_list'].call(this, 'MULTIPLY');
};

AI.Blockly.IOT['math_divide'] = function() {
  return AI.Blockly.IOT['math_arithmetic'].call(this, 'DIVIDE');
};

AI.Blockly.IOT['math_power'] = function() {
  return AI.Blockly.IOT['math_arithmetic'].call(this, 'POWER');
};
