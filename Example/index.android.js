/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from "react";
import { AppRegistry, StyleSheet, View, TextInput, ToastAndroid, DeviceEventEmitter } from "react-native";
import { Recognizer, Synthesizer, SpeechConstant } from "react-native-speech-iflytek";
import Button from "react-native-button";

export default class Example extends Component {
  constructor(props) {
    super(props);

    this.state = {
      text: "",
      recordBtnText: "Press to record"
    };

    this.onRecordStart = this.onRecordStart.bind(this);
    this.onRecordEnd = this.onRecordEnd.bind(this);
    this.onRecordCancel = this.onRecordCancel.bind(this);
    this.onRecognizerResult = this.onRecognizerResult.bind(this);
    this.onRecognizerVolumeChanged = this.onRecognizerVolumeChanged.bind(this);
    this.onSyntheBtnPress = this.onSyntheBtnPress.bind(this);
  }

  componentDidMount() {
    Synthesizer.init("57c7c5b0");
    Recognizer.init("57c7c5b0");
    DeviceEventEmitter.addListener("onRecognizerResult", this.onRecognizerResult);
    DeviceEventEmitter.addListener("onRecognizerVolumeChanged", this.onRecognizerVolumeChanged);
  }

  componentWillUnmount() {
    DeviceEventEmitter.removeListener("onRecognizerResult", this.onRecognizerResult);
    DeviceEventEmitter.addListener("onRecognizerVolumeChanged", this.onRecognizerVolumeChanged);
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
      </View>
    );
  }

  onRecordStart() {
    ToastAndroid.show("Begin to record", ToastAndroid.SHORT);
    this.setState({ recordBtnText: "Release to stop" });
    Synthesizer.setParameter(SpeechConstant.VOICE_NAME, "xiaoyu");
    Recognizer.start();
  }

  onRecordEnd() {
    ToastAndroid.show("End to record", ToastAndroid.SHORT);
    this.setState({ recordBtnText: "Press to record" });
    Recognizer.stop();
  }

  onRecordCancel(evt) {
    ToastAndroid.show("cancel", ToastAndroid.SHORT);
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

  onRecognizerVolumeChanged() {

  }

  async onSyntheBtnPress() {
    let isSpeaking = await Synthesizer.isSpeaking();
    isSpeaking ? Synthesizer.stop() : Synthesizer.start(this.state.text);
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

AppRegistry.registerComponent("Example", () => Example);
