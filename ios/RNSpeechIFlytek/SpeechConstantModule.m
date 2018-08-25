//
//  SpeechConstantModule.m
//  RNSpeechIFlytek
//
//  Created by 张棚贺 on 2018/1/5.
//  Copyright © 2018年 张棚贺. All rights reserved.
//

#import "SpeechConstantModule.h"
#import <Foundation/Foundation.h>
#import <iflyMSC/IFlyMSC.h>

@implementation SpeechConstantModule

RCT_EXPORT_MODULE(SpeechConstantModule);

+ (BOOL)requiresMainQueueSetup
{
    return YES;
}

- (NSDictionary *) constantsToExport {
    return @{
             @"APPID": [IFlySpeechConstant APPID],
             @"ACCENT": [IFlySpeechConstant ACCENT],
             @"ACCENT_MANDARIN": [IFlySpeechConstant ACCENT_MANDARIN],
             @"ACCENT_HENANESE": [IFlySpeechConstant ACCENT_HENANESE],
             @"ACCENT_SICHUANESE": [IFlySpeechConstant ACCENT_SICHUANESE],
             @"ACCENT_CANTONESE": [IFlySpeechConstant ACCENT_CANTONESE],
             @"LANGUAGE": [IFlySpeechConstant LANGUAGE],
             @"LANGUAGE_CHINESE": [IFlySpeechConstant LANGUAGE_CHINESE],
             @"LANGUAGE_CHINESE_TW": [IFlySpeechConstant LANGUAGE_CHINESE_TW],
             @"LANGUAGE_ENGLISH": [IFlySpeechConstant LANGUAGE_ENGLISH],
             @"RESULT_TYPE": [IFlySpeechConstant RESULT_TYPE],
             @"IFLY_DOMAIN": [IFlySpeechConstant IFLY_DOMAIN],
             @"DATA_TYPE": [IFlySpeechConstant DATA_TYPE],
             @"SPEECH_TIMEOUT": [IFlySpeechConstant SPEECH_TIMEOUT],
             @"NET_TIMEOUT": [IFlySpeechConstant NET_TIMEOUT],
             @"SUBJECT": [IFlySpeechConstant SUBJECT],
             @"PARAMS": [IFlySpeechConstant PARAMS],
             @"PROT_TYPE": [IFlySpeechConstant PROT_TYPE],
             @"SSL_CERT": [IFlySpeechConstant SSL_CERT],
             @"POWER_CYCLE": [IFlySpeechConstant POWER_CYCLE],
             @"SAMPLE_RATE": [IFlySpeechConstant SAMPLE_RATE],
             @"SAMPLE_RATE_8K": [IFlySpeechConstant SAMPLE_RATE_8K],
             @"SAMPLE_RATE_16K": [IFlySpeechConstant SAMPLE_RATE_16K],
             @"ENGINE_TYPE": [IFlySpeechConstant ENGINE_TYPE],
             @"TYPE_LOCAL": [IFlySpeechConstant TYPE_LOCAL],
             @"TYPE_CLOUD": [IFlySpeechConstant TYPE_CLOUD],
             @"TYPE_MIX": [IFlySpeechConstant TYPE_MIX],
             @"TYPE_AUTO": [IFlySpeechConstant TYPE_AUTO],
             @"TEXT_ENCODING": [IFlySpeechConstant TEXT_ENCODING],
             @"RESULT_ENCODING": [IFlySpeechConstant RESULT_ENCODING],
             @"PLAYER_INIT": [IFlySpeechConstant PLAYER_INIT],
             @"PLAYER_DEACTIVE": [IFlySpeechConstant PLAYER_DEACTIVE],
             @"RECORDER_INIT": [IFlySpeechConstant RECORDER_INIT],
             @"RECORDER_DEACTIVE": [IFlySpeechConstant RECORDER_DEACTIVE],
             @"SPEED": [IFlySpeechConstant SPEED],
             @"PITCH": [IFlySpeechConstant PITCH],
             @"TTS_AUDIO_PATH": [IFlySpeechConstant TTS_AUDIO_PATH],
             @"VAD_ENABLE": [IFlySpeechConstant VAD_ENABLE],
             @"VAD_BOS": [IFlySpeechConstant VAD_BOS],
             @"VAD_EOS": [IFlySpeechConstant VAD_EOS],
             @"VOICE_NAME": [IFlySpeechConstant VOICE_NAME],
             @"VOICE_ID": [IFlySpeechConstant VOICE_ID],
             @"VOICE_LANG": [IFlySpeechConstant VOICE_LANG],
             @"VOLUME": [IFlySpeechConstant VOLUME],
             @"TTS_BUFFER_TIME": [IFlySpeechConstant TTS_BUFFER_TIME],
             @"TTS_DATA_NOTIFY": [IFlySpeechConstant TTS_DATA_NOTIFY],
             @"NEXT_TEXT": [IFlySpeechConstant NEXT_TEXT],
             @"MPPLAYINGINFOCENTER": [IFlySpeechConstant MPPLAYINGINFOCENTER],
             @"AUDIO_SOURCE": [IFlySpeechConstant AUDIO_SOURCE],
             @"ASR_AUDIO_PATH": [IFlySpeechConstant ASR_AUDIO_PATH],
             @"ASR_SCH": [IFlySpeechConstant ASR_SCH],
             @"ASR_PTT": [IFlySpeechConstant ASR_PTT],
             @"ASR_PTT_HAVEDOT": [IFlySpeechConstant ASR_PTT_HAVEDOT],
             @"ASR_PTT_NODOT": [IFlySpeechConstant ASR_PTT_NODOT],
             @"LOCAL_GRAMMAR": [IFlySpeechConstant LOCAL_GRAMMAR],
             @"CLOUD_GRAMMAR": [IFlySpeechConstant CLOUD_GRAMMAR],
             @"GRAMMAR_TYPE": [IFlySpeechConstant GRAMMAR_TYPE],
             @"GRAMMAR_CONTENT": [IFlySpeechConstant GRAMMAR_CONTENT],
             @"LEXICON_CONTENT": [IFlySpeechConstant LEXICON_CONTENT],
             @"LEXICON_NAME": [IFlySpeechConstant LEXICON_NAME],
             @"GRAMMAR_LIST": [IFlySpeechConstant GRAMMAR_LIST],
             @"NLP_VERSION": [IFlySpeechConstant NLP_VERSION],
             @"IVW_THRESHOLD": [IFlySpeechConstant IVW_THRESHOLD],
             @"IVW_SST": [IFlySpeechConstant IVW_SST],
             @"IVW_ONESHOT": [IFlySpeechConstant IVW_ONESHOT],
             @"KEEP_ALIVE": [IFlySpeechConstant KEEP_ALIVE],
             @"ISE_CATEGORY": [IFlySpeechConstant ISE_CATEGORY],
             @"ISE_RESULT_LEVEL": [IFlySpeechConstant ISE_RESULT_LEVEL],
             @"ISE_RESULT_TYPE": [IFlySpeechConstant ISE_RESULT_TYPE],
             @"ISE_AUDIO_PATH": [IFlySpeechConstant ISE_AUDIO_PATH],
             @"ISE_AUTO_TRACKING": [IFlySpeechConstant ISE_AUTO_TRACKING],
             @"ISE_TRACK_TYPE": [IFlySpeechConstant ISE_TRACK_TYPE],
             @"PLUS_LOCAL_ALL": [IFlySpeechConstant PLUS_LOCAL_ALL],
             @"PLUS_LOCAL_TTS": [IFlySpeechConstant PLUS_LOCAL_TTS],
             @"PLUS_LOCAL_ASR": [IFlySpeechConstant PLUS_LOCAL_ASR],
             @"PLUS_LOCAL_IVW": [IFlySpeechConstant PLUS_LOCAL_IVW],
             @"MFV_AUTH_ID": [IFlySpeechConstant MFV_AUTH_ID],
             @"MFV_SUB": [IFlySpeechConstant MFV_SUB],
             @"MFV_SST": [IFlySpeechConstant MFV_SST],
             @"MFV_VCM": [IFlySpeechConstant MFV_VCM],
             @"MFV_SCENES": [IFlySpeechConstant MFV_SCENES],
             @"MFV_AFC": [IFlySpeechConstant MFV_AFC],
             @"MFV_DATA_PATH": [IFlySpeechConstant MFV_DATA_PATH],
             @"MFV_RGN": [IFlySpeechConstant MFV_RGN],
             @"MFV_TSD": [IFlySpeechConstant MFV_TSD],
             @"MFV_PTXT": [IFlySpeechConstant MFV_PTXT],
             @"MFV_PWDT": [IFlySpeechConstant MFV_PWDT],
             @"MFV_FIN": [IFlySpeechConstant MFV_FIN],
             @"MFV_WTT": [IFlySpeechConstant MFV_WTT],
             @"MFV_DATA_FORMAT": [IFlySpeechConstant MFV_DATA_FORMAT],
             @"MFV_DATA_ENCODING": [IFlySpeechConstant MFV_DATA_ENCODING],
             @"FACE_SUB": [IFlySpeechConstant FACE_SUB],
             @"FACE_WFR": [IFlySpeechConstant FACE_WFR],
             @"FACE_SST": [IFlySpeechConstant FACE_SST],
             @"FACE_REG": [IFlySpeechConstant FACE_REG],
             @"FACE_VERIFY": [IFlySpeechConstant FACE_VERIFY],
             @"FACE_DETECT": [IFlySpeechConstant FACE_DETECT],
             @"FACE_ALIGN": [IFlySpeechConstant FACE_ALIGN],
             @"FACE_ATTR": [IFlySpeechConstant FACE_ATTR],
             @"FACE_AUE": [IFlySpeechConstant FACE_AUE],
             @"FACE_RAW": [IFlySpeechConstant FACE_RAW],
             @"FACE_PSET": [IFlySpeechConstant FACE_PSET],
             @"FACE_SKIP": [IFlySpeechConstant FACE_SKIP],
             @"FACE_GID": [IFlySpeechConstant FACE_GID],
             @"FACE_AUTH_ID": [IFlySpeechConstant FACE_AUTH_ID],
             @"FACE_DVC": [IFlySpeechConstant FACE_DVC]
             };
}

@end

