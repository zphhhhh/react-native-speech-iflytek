/**
* Sample React Native App
* https://github.com/facebook/react-native
* @flow
*/

import React, { Component } from "react";
import { StyleSheet, View, TextInput, Platform, NativeEventEmitter } from "react-native";
import { Recognizer, Synthesizer, SpeechConstant } from "react-native-speech-iflytek";
import Button from "react-native-button";

export default class App extends Component {
  constructor(props) {
    super(props);

    if (Platform.OS === 'android') {
      Synthesizer.init("57c7c5b0");
      Recognizer.init("57c7c5b0");
    } else if (Platform.OS === 'ios') {
      Synthesizer.init("59a4161e");
      Recognizer.init("59a4161e");
    }

    this.state = {
      text: "",
      recordBtnText: "Press to record"
    };

    this.onRecordStart = this.onRecordStart.bind(this);
    this.onRecordEnd = this.onRecordEnd.bind(this);
    this.onRecordCancel = this.onRecordCancel.bind(this);
    this.onRecognizerResult = this.onRecognizerResult.bind(this);
    this.onRecognizerError = this.onRecognizerError.bind(this);
    this.onRecognizerVolumeChanged = this.onRecognizerVolumeChanged.bind(this);
    this.onSyntheBtnPress = this.onSyntheBtnPress.bind(this);
    this.onIsSpeakingBtnPress = this.onIsSpeakingBtnPress.bind(this);
    this.onResumeBtnPress = this.onResumeBtnPress.bind(this);
  }

  componentDidMount() {
    this.recognizerEventEmitter = new NativeEventEmitter(Recognizer);
    this.recognizerEventEmitter.addListener('onRecognizerResult', this.onRecognizerResult)
    this.recognizerEventEmitter.addListener('onRecognizerError', this.onRecognizerError)
    this.recognizerEventEmitter.addListener('onRecognizerVolumeChanged', this.onRecognizerVolumeChanged)

    this.synthesizerEventEmitter = new NativeEventEmitter(Synthesizer);
    this.synthesizerEventEmitter.addListener('onSynthesizerSpeakCompletedEvent', this.onSynthesizerSpeakCompletedEvent);
    this.synthesizerEventEmitter.addListener('onSynthesizerBufferCompletedEvent', this.onSynthesizerBufferCompletedEvent);
  }

  componentWillUnmount() {
    this.recognizerEventEmitter.removeAllListeners();
    this.synthesizerEventEmitter.removeAllListeners();
  }

  render() {
    return (
      <View style={styles.container} onStartShouldSetResponder={() => true}>
        <TextInput onChangeText={text => this.setState({ text })} value={this.state.text} />
        <Button
          containerStyle={styles.containerStyle}
          style={{ color: "white" }}
          onPress={this.onRecordEnd}
          onPressIn={this.onRecordStart}
          activeOpacity={0.8}
          onResponderTerminationRequest={() => true}
          onResponderTerminate={this.onRecordCancel}
        >
          {this.state.recordBtnText}
        </Button>
        <Button containerStyle={styles.containerStyle} style={{ color: "white" }} onPress={this.onSyntheBtnPress} activeOpacity={0.8}>
          Tap to speak
        </Button>
        <Button containerStyle={styles.containerStyle} style={{ color: "white" }} onPress={this.onIsSpeakingBtnPress} activeOpacity={0.8}>
          is Speaking?
        </Button>
        <Button containerStyle={styles.containerStyle} style={{ color: "white" }} onPress={this.onPauseBtnPress} activeOpacity={0.8}>
          Pause
        </Button>
        <Button containerStyle={styles.containerStyle} style={{ color: "white" }} onPress={this.onResumeBtnPress} activeOpacity={0.8}>
          Resume
        </Button>
      </View>
    );
  }

  onRecordStart() {
    this.setState({ recordBtnText: "Release to stop" });

    Recognizer.start();
  }

  onRecordEnd() {
    this.setState({ recordBtnText: "Press to record" });
    Recognizer.stop();
  }

  onRecordCancel(evt) {
    // setTimeout(() => {
    //   Recognizer.cancel();
    // }, 500);
  }

  onRecognizerResult(e) {
    if (!e.isLast) {
      return;
    }
    this.setState({ text: e.result });
  }

  onRecognizerError(result) {
    if (result.errorCode !== 0) {
      alert(JSON.stringify(result));
    }
  }

  onRecognizerVolumeChanged() {

  }

  async onSyntheBtnPress() {
    Synthesizer.start(this.state.text);
  }

  async onPauseBtnPress() {
    Synthesizer.pause();
  }

  onResumeBtnPress() {
    Synthesizer.resume();
  }

  async onIsSpeakingBtnPress() {
    let isSpeaking = await Synthesizer.isSpeaking();
    alert(isSpeaking);
  }

  onSynthesizerSpeakCompletedEvent(result) {
    alert('onSynthesizerSpeakCompletedEvent\n\n' + JSON.stringify(result));
  }

  onSynthesizerBufferCompletedEvent(result) {
    // alert('onSynthesizerBufferCompletedEvent\n\n' + JSON.stringify(result));
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "stretch",
    padding: 5
  },
  result: {
    fontSize: 20,
    textAlign: "center",
    margin: 10
  },
  recordBtn: {
    height: 34,
    fontSize: 16,
    textAlign: "center",
    textAlignVertical: "center",
    borderWidth: 1,
    borderRadius: 4,
    borderColor: "#ccc"
  },
  containerStyle: {
    backgroundColor: "#0275d8",
    margin: 4,
    padding: 4,
    borderRadius: 2
  }
});
