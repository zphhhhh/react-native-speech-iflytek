package com.zphhhhh.speech;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zph on 2017/8/5.
 */

public class SpeechConstantModule extends ReactContextBaseJavaModule {

    public SpeechConstantModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "SpeechConstantModule";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("APPID", com.iflytek.cloud.SpeechConstant.APPID);
        constants.put("NET_TYPE", com.iflytek.cloud.SpeechConstant.NET_TYPE);
        constants.put("FORCE_LOGIN", com.iflytek.cloud.SpeechConstant.FORCE_LOGIN);
        constants.put("LIB_NAME", com.iflytek.cloud.SpeechConstant.LIB_NAME);
        constants.put("RESULT_TYPE", com.iflytek.cloud.SpeechConstant.RESULT_TYPE);
        constants.put("RESULT_LEVEL", com.iflytek.cloud.SpeechConstant.RESULT_LEVEL);
        constants.put("LANGUAGE", com.iflytek.cloud.SpeechConstant.LANGUAGE);
        constants.put("ACCENT", com.iflytek.cloud.SpeechConstant.ACCENT);
        constants.put("DOMAIN", com.iflytek.cloud.SpeechConstant.DOMAIN);
        constants.put("VAD_ENABLE", com.iflytek.cloud.SpeechConstant.VAD_ENABLE);
        constants.put("VAD_BOS", com.iflytek.cloud.SpeechConstant.VAD_BOS);
        constants.put("VAD_EOS", com.iflytek.cloud.SpeechConstant.VAD_EOS);
        constants.put("SAMPLE_RATE", com.iflytek.cloud.SpeechConstant.SAMPLE_RATE);
        constants.put("PARAMS", com.iflytek.cloud.SpeechConstant.PARAMS);
        constants.put("NET_CHECK", com.iflytek.cloud.SpeechConstant.NET_CHECK);
        constants.put("NET_TIMEOUT", com.iflytek.cloud.SpeechConstant.NET_TIMEOUT);
        constants.put("KEY_SPEECH_TIMEOUT", com.iflytek.cloud.SpeechConstant.KEY_SPEECH_TIMEOUT);
        constants.put("ENGINE_MODE", com.iflytek.cloud.SpeechConstant.ENGINE_MODE);
        constants.put("ENGINE_TYPE", com.iflytek.cloud.SpeechConstant.ENGINE_TYPE);
        constants.put("AUDIO_SOURCE", com.iflytek.cloud.SpeechConstant.AUDIO_SOURCE);
        constants.put("ASR_SOURCE_PATH", com.iflytek.cloud.SpeechConstant.ASR_SOURCE_PATH);
        constants.put("FILTER_AUDIO_TIME", com.iflytek.cloud.SpeechConstant.FILTER_AUDIO_TIME);
        constants.put("LOCAL_GRAMMAR", com.iflytek.cloud.SpeechConstant.LOCAL_GRAMMAR);
        constants.put("CLOUD_GRAMMAR", com.iflytek.cloud.SpeechConstant.CLOUD_GRAMMAR);
        constants.put("GRAMMAR_TYPE", com.iflytek.cloud.SpeechConstant.GRAMMAR_TYPE);
        constants.put("GRAMMAR_NAME", com.iflytek.cloud.SpeechConstant.GRAMMAR_NAME);
        constants.put("GRAMMAR_LIST", com.iflytek.cloud.SpeechConstant.GRAMMAR_LIST);
        constants.put("LOCAL_GRAMMAR_PACKAGE", com.iflytek.cloud.SpeechConstant.LOCAL_GRAMMAR_PACKAGE);
        constants.put("NOTIFY_RECORD_DATA", com.iflytek.cloud.SpeechConstant.NOTIFY_RECORD_DATA);
        constants.put("MIXED_THRESHOLD", com.iflytek.cloud.SpeechConstant.MIXED_THRESHOLD);
        constants.put("MIXED_TYPE", com.iflytek.cloud.SpeechConstant.MIXED_TYPE);
        constants.put("MIXED_TIMEOUT", com.iflytek.cloud.SpeechConstant.MIXED_TIMEOUT);
        constants.put("ASR_THRESHOLD", com.iflytek.cloud.SpeechConstant.ASR_THRESHOLD);
        constants.put("LEXICON_TYPE", com.iflytek.cloud.SpeechConstant.LEXICON_TYPE);
        constants.put("ASR_NBEST", com.iflytek.cloud.SpeechConstant.ASR_NBEST);
        constants.put("ASR_WBEST", com.iflytek.cloud.SpeechConstant.ASR_WBEST);
        constants.put("ASR_PTT", com.iflytek.cloud.SpeechConstant.ASR_PTT);
        constants.put("ASR_SCH", com.iflytek.cloud.SpeechConstant.ASR_SCH);
        constants.put("ASR_DWA", com.iflytek.cloud.SpeechConstant.ASR_DWA);
        constants.put("NLP_VERSION", com.iflytek.cloud.SpeechConstant.NLP_VERSION);
        constants.put("SCENE", com.iflytek.cloud.SpeechConstant.SCENE);
        constants.put("TYPE_LOCAL", com.iflytek.cloud.SpeechConstant.TYPE_LOCAL);
        constants.put("TYPE_CLOUD", com.iflytek.cloud.SpeechConstant.TYPE_CLOUD);
        constants.put("TYPE_MIX", com.iflytek.cloud.SpeechConstant.TYPE_MIX);
        constants.put("TYPE_DISTRIBUTED", com.iflytek.cloud.SpeechConstant.TYPE_DISTRIBUTED);
        constants.put("TYPE_AUTO", com.iflytek.cloud.SpeechConstant.TYPE_AUTO);
        constants.put("ISV_SST", com.iflytek.cloud.SpeechConstant.ISV_SST);
        constants.put("ISV_PWDT", com.iflytek.cloud.SpeechConstant.ISV_PWDT);
        constants.put("ISV_VID", com.iflytek.cloud.SpeechConstant.ISV_VID);
        constants.put("ISV_RGN", com.iflytek.cloud.SpeechConstant.ISV_RGN);
        constants.put("ISV_PWD", com.iflytek.cloud.SpeechConstant.ISV_PWD);
        constants.put("ISV_AUDIO_PATH", com.iflytek.cloud.SpeechConstant.ISV_AUDIO_PATH);
        constants.put("ISV_CMD", com.iflytek.cloud.SpeechConstant.ISV_CMD);
        constants.put("ISV_INTERRUPT_ERROR", com.iflytek.cloud.SpeechConstant.ISV_INTERRUPT_ERROR);
        constants.put("WFR_SST", com.iflytek.cloud.SpeechConstant.WFR_SST);
        constants.put("ISE_USER_MODEL_ID", com.iflytek.cloud.SpeechConstant.ISE_USER_MODEL_ID);
        constants.put("ISE_CATEGORY", com.iflytek.cloud.SpeechConstant.ISE_CATEGORY);
        constants.put("ISE_PARSED", com.iflytek.cloud.SpeechConstant.ISE_PARSED);
        constants.put("ISE_AUTO_TRACKING", com.iflytek.cloud.SpeechConstant.ISE_AUTO_TRACKING);
        constants.put("ISE_TRACK_TYPE", com.iflytek.cloud.SpeechConstant.ISE_TRACK_TYPE);
        constants.put("ISE_INTERRUPT_ERROR", com.iflytek.cloud.SpeechConstant.ISE_INTERRUPT_ERROR);
        constants.put("ISE_AUDIO_PATH", com.iflytek.cloud.SpeechConstant.ISE_AUDIO_PATH);
        constants.put("ISE_SOURCE_PATH", com.iflytek.cloud.SpeechConstant.ISE_SOURCE_PATH);
        constants.put("IVW_SST", com.iflytek.cloud.SpeechConstant.IVW_SST);
        constants.put("IVW_WORD_PATH", com.iflytek.cloud.SpeechConstant.IVW_WORD_PATH);
        constants.put("IVW_THRESHOLD", com.iflytek.cloud.SpeechConstant.IVW_THRESHOLD);
        constants.put("KEEP_ALIVE", com.iflytek.cloud.SpeechConstant.KEEP_ALIVE);
        constants.put("IVW_SHOT_WORD", com.iflytek.cloud.SpeechConstant.IVW_SHOT_WORD);
        constants.put("IVW_ENROLL_RES_PATH", com.iflytek.cloud.SpeechConstant.IVW_ENROLL_RES_PATH);
        constants.put("IVW_ENROLL_DEST_PATH", com.iflytek.cloud.SpeechConstant.IVW_ENROLL_DEST_PATH);
        constants.put("IVW_ENROLL_TMIN", com.iflytek.cloud.SpeechConstant.IVW_ENROLL_TMIN);
        constants.put("IVW_ENROLL_TMAX", com.iflytek.cloud.SpeechConstant.IVW_ENROLL_TMAX);
        constants.put("IVW_VOL_CHECK", com.iflytek.cloud.SpeechConstant.IVW_VOL_CHECK);
        constants.put("IVW_ENROLL_TIMES", com.iflytek.cloud.SpeechConstant.IVW_ENROLL_TIMES);
        constants.put("IVW_RES_PATH", com.iflytek.cloud.SpeechConstant.IVW_RES_PATH);
        constants.put("IVW_NET_MODE", com.iflytek.cloud.SpeechConstant.IVW_NET_MODE);
        constants.put("IVW_CHANNEL_NUM", com.iflytek.cloud.SpeechConstant.IVW_CHANNEL_NUM);
        constants.put("IVW_RESET_AIMIC", com.iflytek.cloud.SpeechConstant.IVW_RESET_AIMIC);
        constants.put("IVW_ALSA_CARD", com.iflytek.cloud.SpeechConstant.IVW_ALSA_CARD);
        constants.put("IVW_ALSA_RATE", com.iflytek.cloud.SpeechConstant.IVW_ALSA_RATE);
        constants.put("IVW_AUDIO_PATH", com.iflytek.cloud.SpeechConstant.IVW_AUDIO_PATH);
        constants.put("VOICE_NAME", com.iflytek.cloud.SpeechConstant.VOICE_NAME);
        constants.put("EMOT", com.iflytek.cloud.SpeechConstant.EMOT);
        constants.put("NEXT_TEXT", com.iflytek.cloud.SpeechConstant.NEXT_TEXT);
        constants.put("LOCAL_SPEAKERS", com.iflytek.cloud.SpeechConstant.LOCAL_SPEAKERS);
        constants.put("SPEED", com.iflytek.cloud.SpeechConstant.SPEED);
        constants.put("PITCH", com.iflytek.cloud.SpeechConstant.PITCH);
        constants.put("VOLUME", com.iflytek.cloud.SpeechConstant.VOLUME);
        constants.put("BACKGROUND_SOUND", com.iflytek.cloud.SpeechConstant.BACKGROUND_SOUND);
        constants.put("TTS_BUFFER_TIME", com.iflytek.cloud.SpeechConstant.TTS_BUFFER_TIME);
        constants.put("TTS_PLAY_STATE", com.iflytek.cloud.SpeechConstant.TTS_PLAY_STATE);
        constants.put("TTS_DATA_NOTIFY", com.iflytek.cloud.SpeechConstant.TTS_DATA_NOTIFY);
        constants.put("TTS_INTERRUPT_ERROR", com.iflytek.cloud.SpeechConstant.TTS_INTERRUPT_ERROR);
        constants.put("TTS_SPELL_INFO", com.iflytek.cloud.SpeechConstant.TTS_SPELL_INFO);
        constants.put("AUDIO_FORMAT", com.iflytek.cloud.SpeechConstant.AUDIO_FORMAT);
        constants.put("STREAM_TYPE", com.iflytek.cloud.SpeechConstant.STREAM_TYPE);
        constants.put("KEY_REQUEST_FOCUS", com.iflytek.cloud.SpeechConstant.KEY_REQUEST_FOCUS);
        constants.put("TTS_AUDIO_PATH", com.iflytek.cloud.SpeechConstant.TTS_AUDIO_PATH);
        constants.put("DATA_TYPE", com.iflytek.cloud.SpeechConstant.DATA_TYPE);
        constants.put("SUBJECT", com.iflytek.cloud.SpeechConstant.SUBJECT);
        constants.put("ASR_AUDIO_PATH", com.iflytek.cloud.SpeechConstant.ASR_AUDIO_PATH);
        constants.put("ASR_INTERRUPT_ERROR", com.iflytek.cloud.SpeechConstant.ASR_INTERRUPT_ERROR);
        constants.put("ASR_NOMATCH_ERROR", com.iflytek.cloud.SpeechConstant.ASR_NOMATCH_ERROR);
        constants.put("ASR_NET_PERF", com.iflytek.cloud.SpeechConstant.ASR_NET_PERF);
        constants.put("ENG_ASR", com.iflytek.cloud.SpeechConstant.ENG_ASR);
        constants.put("ENG_TTS", com.iflytek.cloud.SpeechConstant.ENG_TTS);
        constants.put("ENG_NLU", com.iflytek.cloud.SpeechConstant.ENG_NLU);
        constants.put("ENG_IVW", com.iflytek.cloud.SpeechConstant.ENG_IVW);
        constants.put("ENG_IVP", com.iflytek.cloud.SpeechConstant.ENG_IVP);
        constants.put("ENG_WFR", com.iflytek.cloud.SpeechConstant.ENG_WFR);
        constants.put("ENG_EVA", com.iflytek.cloud.SpeechConstant.ENG_EVA);
        constants.put("MODE_MSC", com.iflytek.cloud.SpeechConstant.MODE_MSC);
        constants.put("MODE_PLUS", com.iflytek.cloud.SpeechConstant.MODE_PLUS);
        constants.put("MODE_AUTO", com.iflytek.cloud.SpeechConstant.MODE_AUTO);
        constants.put("TEXT_ENCODING", com.iflytek.cloud.SpeechConstant.TEXT_ENCODING);
        constants.put("TEXT_BOM", com.iflytek.cloud.SpeechConstant.TEXT_BOM);
        constants.put("AUTH_ID", com.iflytek.cloud.SpeechConstant.AUTH_ID);
        constants.put("MFV_SST", com.iflytek.cloud.SpeechConstant.MFV_SST);
        constants.put("MFV_VCM", com.iflytek.cloud.SpeechConstant.MFV_VCM);
        constants.put("MFV_SCENES", com.iflytek.cloud.SpeechConstant.MFV_SCENES);
        constants.put("MFV_AFC", com.iflytek.cloud.SpeechConstant.MFV_AFC);
        constants.put("MFV_DATA_PATH", com.iflytek.cloud.SpeechConstant.MFV_DATA_PATH);
        constants.put("MFV_INTERRUPT_ERROR", com.iflytek.cloud.SpeechConstant.MFV_INTERRUPT_ERROR);
        constants.put("PROT_TYPE", com.iflytek.cloud.SpeechConstant.PROT_TYPE);
        constants.put("PLUS_LOCAL_TTS", com.iflytek.cloud.SpeechConstant.PLUS_LOCAL_TTS);
        constants.put("PLUS_LOCAL_ASR", com.iflytek.cloud.SpeechConstant.PLUS_LOCAL_ASR);
        constants.put("PLUS_LOCAL_IVW", com.iflytek.cloud.SpeechConstant.PLUS_LOCAL_IVW);
        constants.put("PLUS_LOCAL_ALL", com.iflytek.cloud.SpeechConstant.PLUS_LOCAL_ALL);
        constants.put("IST_SESSION_ID", com.iflytek.cloud.SpeechConstant.IST_SESSION_ID);
        constants.put("IST_SYNC_ID", com.iflytek.cloud.SpeechConstant.IST_SYNC_ID);
        constants.put("IST_AUDIO_UPLOADED", com.iflytek.cloud.SpeechConstant.IST_AUDIO_UPLOADED);
        constants.put("IST_AUDIO_PATH", com.iflytek.cloud.SpeechConstant.IST_AUDIO_PATH);
        constants.put("IST_SESSION_TRY", com.iflytek.cloud.SpeechConstant.IST_SESSION_TRY);
        return constants;
    }
}