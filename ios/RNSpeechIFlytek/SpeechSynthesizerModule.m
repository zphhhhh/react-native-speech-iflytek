//
//  SpeechSynthesizerModule.m
//  RNSpeechIFlytek
//
//  Created by 张棚贺 on 2018/1/4.
//  Copyright © 2018年 张棚贺. All rights reserved.
//

#import "SpeechSynthesizerModule.h"
#import <Foundation/Foundation.h>
#import <iflyMSC/IFlyMSC.h>

@implementation SpeechSynthesizerModule

RCT_EXPORT_MODULE(SpeechSynthesizerModule);

- (void) startObserving {
    hasListeners = YES;
}

- (void) stopObserving {
    hasListeners = NO;
}

- (NSArray <NSString *> *) supportedEvents {
    return @[
             @"onSynthesizerSpeakCompletedEvent",
             @"onSynthesizerBufferCompletedEvent"
             ];
}

RCT_EXPORT_METHOD(init: (NSString *) AppId) {
    if (self.iFlySpeechSynthesizer != nil) {
        return;
    }

    NSString * initIFlytekString = [[NSString alloc] initWithFormat: @"appid=%@", AppId];

    [IFlySpeechUtility createUtility: initIFlytekString];

    self.iFlySpeechSynthesizer = [IFlySpeechSynthesizer sharedInstance];
    self.iFlySpeechSynthesizer.delegate = self;
}

RCT_EXPORT_METHOD(start: (NSString *) content) {
    if ([self.iFlySpeechSynthesizer isSpeaking]) {
        [self.iFlySpeechSynthesizer stopSpeaking];
    }
    
    self.content = content;
    self.startTime = [[NSDate date] timeIntervalSince1970];
    
    [self.iFlySpeechSynthesizer startSpeaking:content];
}

RCT_EXPORT_METHOD(synthesizeToFile: (NSString *) content
                  filename: (NSString *) filename
                  resolver: (RCTPromiseResolveBlock) resolve
                  rejecter: (RCTPromiseRejectBlock) reject) {
    if ([self.iFlySpeechSynthesizer isSpeaking]) {
        [self.iFlySpeechSynthesizer stopSpeaking];
    }

    self.content = content;
    self.filename = [self getAbsolutePath: filename];

    [self.iFlySpeechSynthesizer synthesize: self.content toUri: self.filename];
    resolve(0);
}

RCT_EXPORT_METHOD(stop) {
    if ([self.iFlySpeechSynthesizer isSpeaking]) {
        [self.iFlySpeechSynthesizer stopSpeaking];
    }
}

RCT_EXPORT_METHOD(isSpeaking: (RCTPromiseResolveBlock) resolve
                  rejecter: (RCTPromiseRejectBlock) reject) {
    BOOL isSpeaking = [self.iFlySpeechSynthesizer isSpeaking];
    resolve([NSNumber numberWithBool: isSpeaking]);
}

RCT_EXPORT_METHOD(pause) {
    if ([self.iFlySpeechSynthesizer isSpeaking]) {
        [self.iFlySpeechSynthesizer pauseSpeaking];
    }
}

RCT_EXPORT_METHOD(resume) {
    if ([self.iFlySpeechSynthesizer isSpeaking]) {
        [self.iFlySpeechSynthesizer resumeSpeaking];
    }
}

RCT_EXPORT_METHOD(setParameter: (NSString *) parameter
                  value: (NSString *) value) {
    if ([parameter isEqualToString: IFlySpeechConstant.TTS_AUDIO_PATH]) {
        value = [self getAbsolutePath: value];
    }
    [self.iFlySpeechSynthesizer setParameter: value forKey: parameter];
}

RCT_EXPORT_METHOD(getParameter: (NSString *) parameter
                  resolver: (RCTPromiseResolveBlock) resolve
                  rejecter: (RCTPromiseRejectBlock) reject) {
    @try {
        NSString * value = [self.iFlySpeechSynthesizer parameterForKey: parameter];
        resolve(value);
    } @catch (NSException *exception) {
        reject(@"100", @"参数不存在", nil);
    }
}

- (void) onCompleted: (IFlySpeechError *)error {
    self.endTime = [[NSDate date] timeIntervalSince1970];
    NSNumber * duration = [NSNumber numberWithDouble: self.endTime - self.startTime];

    NSMutableDictionary * result = [NSMutableDictionary new];
    result[@"content"] = self.content;
    result[@"duration"] = duration;

    if (error != nil) {
        result[@"error"] = @{
                             @"errorCode": [NSNumber numberWithInt: error.errorCode],
                             @"errorType": [NSNumber numberWithInt: error.errorType],
                             @"errorDesc": error.errorDesc,
                             };
    }

    if (hasListeners) {
        [self sendEventWithName: @"onSynthesizerSpeakCompletedEvent" body: result];
    }
}

- (void) onBufferProgress: (int) progress message: (NSString *) msg {
    if (progress == 100) {
        self.endTime = [[NSDate date] timeIntervalSince1970];
        NSNumber * duration = [NSNumber numberWithDouble: self.endTime - self.startTime];
        
        NSMutableDictionary * result = [NSMutableDictionary new];
        result[@"content"] = self.content;
        result[@"duration"] = duration;
        result[@"msg"] = msg;
        
        if (self.filename != nil) {
            result[@"filename"] = self.filename;
        }
        self.filename = nil;
        
        if (hasListeners) {
            [self sendEventWithName: @"onSynthesizerBufferCompletedEvent" body: result];
        }
    }
}

- (NSString *) getAbsolutePath: (NSString *) path {
    NSString * homePath = NSHomeDirectory();

    path = [path stringByTrimmingCharactersInSet: [NSCharacterSet characterSetWithCharactersInString:@"/"]];

    return [NSString stringWithFormat:@"%@/%@", homePath, path];
}

@end

