//index.js
import React, { NativeModules } from "react-native";

module.exports = {
  SpeechConstant: NativeModules.SpeechConstantModule,
  Recognizer: NativeModules.SpeechRecognizerModule,
  Synthesizer: NativeModules.SpeechSynthesizerModule
};
