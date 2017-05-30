/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from "react";
import { AppRegistry, StyleSheet, Text, TextInput, Button, View, DeviceEventEmitter, ToastAndroid } from "react-native";
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
      <View style={styles.container}>
        <TextInput
          style={{ height: 40, borderColor: "gray", borderWidth: 1 }}
          onChangeText={text => this.setState({ text })}
          value={this.state.text}
        />
        <Text
          onStartShouldSetResponder={this.onRecordStart}
          onResponderRelease={this.onRecordEnd}
          onResponderTerminationRequest={() => true}
          onPress={this.onRecordBtnPress}
          style={styles.recordBtn}
        >
          {this.state.recordBtnText}
        </Text>
        <Button title="Tap to speak" onPress={this.onSyntheBtnPress} />
      </View>
    );
  }

  onRecordStart() {
    ToastAndroid.show("开始录音", ToastAndroid.SHORT);
    this.setState({ recordBtnText: "Release to stop" });
    Recognizer.start();

    // setTimeout(() => {
    //   this.setState({ recordBtnText: "Press to record" });
    //   Recognizer.stop();
    // }, 5000);
  }

  onRecordEnd() {
    ToastAndroid.show("结束录音", ToastAndroid.SHORT);
    this.setState({ recordBtnText: "Press to record" });
    Recognizer.stop();
  }

  onSpeechRecognizerResult(e) {
    if (!e.isLast) {
      return;
    }
    ToastAndroid.show(e.result, ToastAndroid.SHORT);
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
    paddingLeft: 5,
    paddingRight: 5
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
  }
});

export default Example;
AppRegistry.registerComponent("Example", () => Example);
