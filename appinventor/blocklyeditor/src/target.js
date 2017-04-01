// mode: javascript; js-indent-level: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

goog.provide('AI.Blockly.Target');
goog.require('Blockly.Blocks.Utilities');
goog.require('Blockly.Blocks.Yail');

AI.Blockly.Target['Yail'] = {
  blocks: Blockly.Blocks.Yail,
  generateCode: Blockly.Yail.getFormYail,
  typeMappingFunction: Blockly.Blocks.Utilities.YailTypeToBlocklyType
};
