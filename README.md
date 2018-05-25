# react-native-speech-iflytek &middot;  [![npm](https://img.shields.io/npm/v/react-native-speech-iflytek.svg)](https://www.npmjs.com/package/react-native-speech-iflytek) [![npm](https://img.shields.io/npm/dm/react-native-speech-iflytek.svg)](https://www.npmjs.com/package/react-native-speech-iflytek) [![GitHub closed issues](https://img.shields.io/github/issues-closed/zphhhhh/react-native-speech-iflytek.svg)](https://github.com/zphhhhh/react-native-speech-iflytek/issues?q=is%3Aissue+is%3Aclosed)
react-native-speech-iflytek 是一个 React Native 下的科大讯飞语音库，可以进行语音识别与语音合成。

## Support
- React Native >= 0.47.0 from 0.2.0
- React Native >= 0.42.0 from 0.1.2
- Android
- iOS from 1.0.0

## Install
```
yarn add react-native-speech-iflytek
react-native link
```
安装、链接后还须进行下面两步（以 `Example` 工程为例）：
1. 在 [讯飞开放平台](http://www.xfyun.cn/sdk/dispatcher) 下载组合服务 SDK （选择`语音听写`与`在线语音合成`），分别下载 Android 与 iOS 平台 SDK。
2. 替换 SDK 文件：
    1. 使用下载 Android SDK 的 `Android_voice_xxxx_xxxxxxxx/libs` 文件夹替换 `Example/node_modules/react-native-speech-iflytek/android/libs` 文件夹；
    2. 使用下载 iOS SDK 的 `iOS_voice_xxxx_xxxxxxxx/libs` 文件夹替换 `Example/node_modules/react-native-speech-iflytek/ios/libs` 文件夹。
    
3. iOS 平台还需手动添加部分依赖库：
    1. 在 XCode 中打开 `Example/ios/YourProject.xcodeproj`；
    2. 将讯飞框架文件 `Example/node_modules/react-native-speech-iflytek/ios/libs/iflyMSC.framework` 拖入 Project navigator 的 `Frameworks` 下，注意选择 `Copy items if needed`；
    3. 添加讯飞依赖的系统库（见：[科大讯飞MSC开发指南-iOS-集成流程](http://doc.xfyun.cn/msc_ios/%E9%9B%86%E6%88%90%E6%B5%81%E7%A8%8B.html)）:
        - CoreLocation.framework
        - CoreTelephony.framework
        - AVFoundation.framework
        - AddressBook.framework
        - Contacts.framework
        - AudioToolbox.framework
        - SystemConfiguration.framework
        - QuartzCore.framework
        - UIKit.framework
        - Foundation.framework
        - CoreGraphics.framework
        
4.android平台权限
   ```
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.RECORD_AUDIO" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <!--读取网络信息状态 -->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <!--获取当前wifi状态 -->
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <!--允许程序改变网络连接状态 -->
    <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE"/>
    <!--读取手机信息权限 -->
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
   ```
## Usage
（详见 Example）引入包：
```
import { Recognizer, Synthesizer, SpeechConstant } from "react-native-speech-iflytek";
```
语音识别：
```
Recognizer.init("57c7c5b0");
this.recognizerEventEmitter = new NativeEventEmitter(Recognizer);
this.recognizerEventEmitter.addListener('onRecognizerResult', this.onRecognizerResult);
Recognizer.start();
```
处理识别结果：
```
onRecognizerResult(e) {
    if (!e.isLast) {
        return;
    }
    this.setState({ text: e.result });
}
```
## API

### Recognizer
#### Methods
- `Recognizer.init(String AppId)`  
初始化语音识别
- `Recognizer.start()`  
开始语音识别
- `Recognizer.cancel()`  
取消语音识别
- `Recognizer.isListening()`  
检测当前是否正在语音识别。返回 `Promise`，结果为 `bool` 类型，表示当前是否正在语音识别
- `Recognizer.stop()`  
如果正在语音识别，则结束语音识别
- `Recognizer.setParameter(String parameter, String value)`  
语音识别设置，详见讯飞语音文档
- `Recognizer.getParameter(String param)`  
获取语音识别设置，详见讯飞语音文档。返回 `Promise`，结果为 `String` 类型，表示语音识别设置值
#### Events
- `onRecognizerResult(JSON result)`  
语音识别结果，在语音识别时会不断触发该事件，`result` 为 `JSON` 类型，其值：

    - `text`：当次识别结果
    - `result`：当前识别结果，最常使用
    - `isLast`：是否是最后一次识别，调用 `Recognizer.stop()` 后，`isLast` 值为 `true`，否则一直为 `false`
    - `duration`：当前识别时间长度
- `onRecognizerVolumeChanged(JSON result)`  
语音识别的音量大小，当识别的语音改变音量时会触发该事件，`result` 为 `JSON` 类型，其值：

    - `volume`: 当前音量大小

- `onRecognizerError(JSON error)`  
语音识别出现错误，错误信息与讯飞文档保持一致，其值：

    - `errorCode`: 获取错误码，关于错误码请见官方文档 [MSC错误码](http://www.xfyun.cn/index.php/default/doccenter/doccenterInner?itemTitle=ZmFx&anchor=Y29udGl0bGU2Ng==) ：
    - `errorType`: （仅 iOS）获取错误码类型
    - `errorDesc`: （仅 iOS）获取错误描述
    - `errorDescription`: （仅 Android）获取错误描述，不包含错误码的描述信息
    - `plainDescription`: （仅 Android）获取错误描述，包含错误码的描述信息

### Synthesizer
#### Methods
- `Synthesizer.init(String AppId)`  
初始化语音合成
- `Synthesizer.start(String content)`  
开始语音合成
- `Synthesizer.stop()`  
如果正在语音合成，则结束语音合成
- `Synthesizer.isSpeaking()`  
检测当前是否正在语音合成。返回 `Promise`，结果为 `bool` 类型，表示当前是否正在语音合成
- `Synthesizer.pause()`  
如果正在语音合成，则暂停语音合成（对应 resume）
- `Synthesizer.resume()`  
如果正在语音合成，则开始语音合成（对应 pause）
- `Synthesizer.setParameter(String parameter, String value)`  
语音合成设置，详见讯飞语音文档
- `Synthesizer.getParameter(String param)`  
获取语音合成设置，详见讯飞语音文档。返回 `Promise`，结果为 `String` 类型，表示语音合成设置值
#### Events
- `onSynthesizerBufferCompletedEvent()`  
语音合成缓冲完成时触发该事件
- `onSynthesizerSpeakCompletedEvent()`  
语音合成播放完成时触发该事件

### SpeechConstant
本模块包含讯飞接口的所有常量，如设置发言人、发言速度等，详见讯飞文档，使用示例：
```
Synthesizer.setParameter(SpeechConstant.VOICE_NAME, "xiaoyu");
```

## Maybe
- (Native Android) Android Studio 2.3.3 接入讯飞语音接口: http://www.jianshu.com/p/caf50402d31c

## FAQ
1. iOS 提示 `iflytek.framework not found` ？  （见 [issue 15](https://github.com/zphhhhh/react-native-speech-iflytek/issues/15)）  
  - 解决方法一（建议）：Xcode 没有找到讯飞库，在项目的 `Building Settings` -> `Search Paths` -> `Framework Search Paths` 中手动添加 `iflytek.framework` 的目录 `$(SRCROOT)/../node_modules/react-native-speech-iflytek/ios/libs`。
  - 解决方法二：将 `iflytek.framework` 拖入时注意选择 `Copy items if needed`。如忘记选择，最好的方式是在工程中删除讯飞框架，将讯飞框架移至其他文件夹，并重新拖入工程，选择 `Copy items if needed`。（Xcode 的缓存会记住上次选择，若第一次没有`Copy items if needed`，在同一位置下重新拖入不会再次出现选择框。）
2. `react-native [command]` 命令失效？  
  同时使用 yarn 和 npm 时可能会出现这个问题，建议再敲一次 `yarn` 命令解决依赖。

## Contribute
期待提出有关建议，欢迎做出贡献，感谢 star。  
Github: [https://github.com/zphhhhh/react-native-speech-iflytek](https://github.com/zphhhhh/react-native-speech-iflytek)
