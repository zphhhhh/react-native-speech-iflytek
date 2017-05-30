//index.js
import React, { NativeModules } from "react-native";

module.exports = {
  Recognizer: NativeModules.SpeechRecognizerModule,
  Synthesizer: NativeModules.SpeechSynthesizerModule
};
