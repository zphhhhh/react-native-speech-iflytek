/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from "react";
import { AppRegistry, StyleSheet, Text, TextInput, View, DeviceEventEmitter, ToastAndroid } from "react-native";
import Button from "react-native-button";
import { Recognizer, Synthesizer } from "react-native-speech-iflytek";

class Example extends Component {
  constructor(props) {
    super(props);

    this.state = {
      text: "",
      recordBtnText: "Press to record"
    };

    this.onRecordStart = this.onRecordStart.bind(this);
    this.onRecordEnd = this.onRecordEnd.bind(this);
    this.onSpeechRecognizerResult = this.onSpeechRecognizerResult.bind(this);
    this.onSyntheBtnPress = this.onSyntheBtnPress.bind(this);
  }

  componentDidMount() {
    Synthesizer.init("57c7c5b0");
    Recognizer.init("57c7c5b0");
    DeviceEventEmitter.addListener("onSpeechRecognizerResult", this.onSpeechRecognizerResult);
    DeviceEventEmitter.addListener("onSpeechRecognizerVolumeChanged", this.onSpeechRecognizerVolumeChanged);
  }

  componentWillUnmount() {
    DeviceEventEmitter.removeListener("onSpeechRecognizerResult", this.onSpeechRecognizerResult);
    DeviceEventEmitter.addListener("onSpeechRecognizerVolumeChanged", this.onSpeechRecognizerVolumeChanged);
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
        <Button
          containerStyle={styles.containerStyle}
          style={{ color: "white" }}
          onPress={this.onSyntheBtnPress}
          activeOpacity={0.8}
        >
          Tap to speak
        </Button>
      </View>
    );
  }

  onRecordStart() {
    ToastAndroid.show("Begin to record", ToastAndroid.SHORT);
    this.setState({ recordBtnText: "Release to stop" });
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

  onSpeechRecognizerResult(e) {
    if (!e.isLast) {
      return;
    }
    this.setState({ text: e.result });
  }

  onSpeechRecognizerVolumeChanged = () => {};

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

export default Example;
AppRegistry.registerComponent("Example", () => Example);
