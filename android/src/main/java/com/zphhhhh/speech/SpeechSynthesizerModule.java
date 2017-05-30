package com.zphhhhh.speech;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by cn_pa on 2016/11/25.
 * 提供讯飞语音合成 React Native 接口
 */

public class SpeechSynthesizerModule extends ReactContextBaseJavaModule {
    private Context context;

    private SpeechSynthesizer mTts;
    private SynthesizerListener mTtsListener;

    private static long startTime;
    private static long endTime;
    private String content;
    private String filename;


    public SpeechSynthesizerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    @Override
    public String getName() {
        return "SpeechSynthesizerModule";
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("PARAM", SpeechConstant.PARAMS);
        constants.put("ENGINE_TYPE", SpeechConstant.ENGINE_TYPE);
        constants.put("RESULT_TYPE", SpeechConstant.RESULT_TYPE);
        constants.put("LANGUAGE", SpeechConstant.LANGUAGE);
        constants.put("ACCENT", SpeechConstant.ACCENT);
        constants.put("VAD_BOS", SpeechConstant.VAD_BOS);
        constants.put("VAD_EOS", SpeechConstant.VAD_EOS);
        constants.put("ASR_PTT", SpeechConstant.ASR_PTT);
        constants.put("ASR_PTT", SpeechConstant.ASR_PTT);
        constants.put("AUDIO_FORMAT", SpeechConstant.AUDIO_FORMAT);
        constants.put("ASR_AUDIO_PATH", SpeechConstant.ASR_AUDIO_PATH);
        constants.put("TTS_AUDIO_PATH", SpeechConstant.TTS_AUDIO_PATH);

        return constants;
    }

    @ReactMethod
    public void init(String AppId) {
        if (mTts != null) {
            return;
        }

        SpeechUtility.createUtility(this.context, SpeechConstant.APPID + "=" + AppId);

        mTts = SpeechSynthesizer.createSynthesizer(this.context, null);
        mTtsListener = new SynthesizerListener() {

            @Override
            public void onSpeakBegin() {

            }

            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {
                // 合成进度
                if(i == 100) {
                    WritableMap params = Arguments.createMap();
                    params.putString("content", content);
                    params.putString("filename", filename);
                    onJSEvent(getReactApplicationContext(), "onSpeechSynthesizerBufferCompletedEvent", params);
                }
            }

            @Override
            public void onSpeakPaused() {

            }

            @Override
            public void onSpeakResumed() {

            }

            @Override
            public void onSpeakProgress(int i, int i1, int i2) {
                // 播放进度
            }

            @Override
            public void onCompleted(SpeechError speechError) {
                // 播放完成

            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }
        };
        setTtsParam();
    }

    @ReactMethod
    public void start(String content) {
        startTime = System.currentTimeMillis();

        if (mTts.isSpeaking()) {
            mTts.stopSpeaking();
        }

        mTts.startSpeaking(content, mTtsListener);
    }

    @ReactMethod
    public void synthesizeToFile(String content, String filename, Promise promise) {
        if (mTts.isSpeaking()) {
            mTts.stopSpeaking();
        }

        this.content = content;
        this.filename = filename;

        setTtsParam();
        int result = mTts.synthesizeToUri(content, Environment.getExternalStorageDirectory() + filename, mTtsListener);
        try{
            promise.resolve(result);
        } catch (IllegalViewOperationException e) {
            promise.reject("Error: synthesizeToFile()", e);
        }
    }

    @ReactMethod
    public void stop() {
        if (mTts.isSpeaking()) {
            mTts.stopSpeaking();
        }
    }

    @ReactMethod
    public void isSpeaking(Promise promise) {
        try {
            promise.resolve(mTts.isSpeaking());
        } catch (IllegalViewOperationException e) {
            promise.reject("Error : isSpeaking()", e);
        }
    }

    @ReactMethod
    public void pause() {
        if (mTts.isSpeaking()) {
            mTts.pauseSpeaking();
        }
    }

    @ReactMethod
    public void setParameter(String parameter, String value) {
        if (parameter.equals(SpeechConstant.TTS_AUDIO_PATH)) {
            value = Environment.getExternalStorageDirectory() + value;
        }
        mTts.setParameter(parameter, value);
    }

    private void setTtsParam() {
        // 清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);

        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置在线合成发音人
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaofeng");
        //设置合成语速
        mTts.setParameter(SpeechConstant.SPEED, "50");
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, "50");
        //设置合成音量
        mTts.setParameter(SpeechConstant.VOLUME, "50");

        //设置播放器音频流类型
        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/App/SpeechSynthesizer");
    }

    private void onTtsCompleted() {
        endTime = System.currentTimeMillis();
        int duration = (int) (endTime - startTime);

        WritableMap params = Arguments.createMap();
        params.putInt("duration", duration);

        onJSEvent(getReactApplicationContext(), "onSpeechSynthesizerSpeakCompletedEvent", params);
    }

    private void showTip(final String str) {
        Toast.makeText(this.context, str, Toast.LENGTH_SHORT).show();
    }

    private void onJSEvent(ReactContext reactContext,
                         String eventName,
                         @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }
}
