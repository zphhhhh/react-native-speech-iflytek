//
//  SpeechRecognizerModule.m
//  RNSpeechIFlytek
//
//  Created by 张棚贺 on 2018/1/10.
//  Copyright © 2018年 zphhhhh. All rights reserved.
//

#import "SpeechRecognizerModule.h"
#import <Foundation/Foundation.h>
#import <iflyMSC/IFlyMSC.h>

@implementation SpeechRecognizerModule

RCT_EXPORT_MODULE(SpeechRecognizerModule);

- (void) startObserving {
    hasListeners = YES;
}

- (void) stopObserving {
    hasListeners = NO;
}

- (NSArray <NSString *> *) supportedEvents {
    return @[
             @"onRecognizerVolumeChanged",
             @"onRecognizerResult",
             @"onRecognizerError",
             ];
}

RCT_EXPORT_METHOD(init: (NSString *) AppId) {
    if (self.iFlySpeechRecognizer != nil) {
        return;
    }
    
    NSString * initIFlytekString = [[NSString alloc] initWithFormat: @"appid=%@", AppId];
    
    [IFlySpeechUtility createUtility: initIFlytekString];
    
    self.iFlySpeechRecognizer = [IFlySpeechRecognizer sharedInstance];
    self.iFlySpeechRecognizer.delegate = self;
}

RCT_EXPORT_METHOD(start) {
    if ([self.iFlySpeechRecognizer isListening]) {
        [self.iFlySpeechRecognizer cancel];
    }
    
    self.result = [NSMutableString new];
    self.startTime = [[NSDate date] timeIntervalSince1970];
    
    [self.iFlySpeechRecognizer startListening];
}

RCT_EXPORT_METHOD(cancel) {
    if ([self.iFlySpeechRecognizer isListening]) {
        [self.iFlySpeechRecognizer cancel];
    }
}

RCT_EXPORT_METHOD(isListening: (RCTPromiseResolveBlock) resolve
                  rejecter: (RCTPromiseRejectBlock) reject) {
    @try {
        BOOL isListening = [self.iFlySpeechRecognizer isListening];
        resolve([NSNumber numberWithBool: isListening]);
    } @catch (NSException * exception) {
        reject(@"101", @"Recognizer.isListening() ", nil);
    }
}

RCT_EXPORT_METHOD(stop) {
    if ([self.iFlySpeechRecognizer isListening]) {
        [self.iFlySpeechRecognizer stopListening];
    }
}

RCT_EXPORT_METHOD(setParameter: (NSString *) parameter
                  value: (NSString *) value) {
    if ([parameter isEqualToString: IFlySpeechConstant.ASR_AUDIO_PATH]) {
        value = [self getAbsolutePath: value];
    }
    [self.iFlySpeechRecognizer setParameter: value forKey: parameter];
}

RCT_EXPORT_METHOD(getParameter: (NSString *) parameter
                  resolver: (RCTPromiseResolveBlock) resolve
                  rejecter: (RCTPromiseRejectBlock) reject) {
    @try {
        NSString * value = [self.iFlySpeechRecognizer parameterForKey: parameter];
        resolve(value);
    } @catch (NSException *exception) {
        reject(@"100", @"参数不存在", nil);
    }
}

- (void) onError: (IFlySpeechError *) error {
    NSDictionary * result = @{
                              @"errorCode": [NSNumber numberWithInt: error.errorCode],
                              @"errorType": [NSNumber numberWithInt: error.errorType],
                              @"errorDesc": error.errorDesc,
                              };

    if (hasListeners) {
        [self sendEventWithName: @"onRecognizerError" body: result];
    }
}

- (void) onResults: (NSArray *) results isLast: (BOOL) isLast {
    self.endTime = [[NSDate date] timeIntervalSince1970];
    NSNumber * duration = [NSNumber numberWithDouble: self.endTime - self.startTime];
    
    NSMutableString * resultString = [NSMutableString new];
    NSDictionary * dic = results[0];
    
    for (NSString * key in dic) {
        [resultString appendFormat:@"%@",key];
    }

    NSString * resultFromJson = [self stringFromJson:resultString];
    
    [self.result appendString: resultFromJson];

    NSDictionary * result = @{
                              @"text": resultFromJson,
                              @"result": self.result,
                              @"isLast": [NSNumber numberWithBool: isLast],
                              @"duration": duration
                              };
    if (hasListeners) {
        [self sendEventWithName: @"onRecognizerResult" body: result];
    }
}

- (void) onVolumeChanged: (int)volume {
    NSDictionary * result = @{
                              @"volume": [NSNumber numberWithInt: volume]
                              };
    if (hasListeners) {
        [self sendEventWithName: @"onRecognizerVolumeChanged" body: result];
    }
}

- (NSString *) stringFromJson: (NSString *) params {
    if (params == NULL) {
        return nil;
    }
    
    NSMutableString *tempStr = [[NSMutableString alloc] init];
    NSDictionary *resultDic  = [NSJSONSerialization JSONObjectWithData:
                                [params dataUsingEncoding:NSUTF8StringEncoding] options:kNilOptions error:nil];
    
    if (resultDic!= nil) {
        NSArray *wordArray = [resultDic objectForKey:@"ws"];
        
        for (int i = 0; i < [wordArray count]; i++) {
            NSDictionary *wsDic = [wordArray objectAtIndex: i];
            NSArray *cwArray = [wsDic objectForKey:@"cw"];
            
            for (int j = 0; j < [cwArray count]; j++) {
                NSDictionary *wDic = [cwArray objectAtIndex:j];
                NSString *str = [wDic objectForKey:@"w"];
                [tempStr appendString: str];
            }
        }
    }
    return tempStr;
}

- (NSString *) getAbsolutePath: (NSString *) path {
    NSString * homePath = NSHomeDirectory();
    
    path = [path stringByTrimmingCharactersInSet: [NSCharacterSet characterSetWithCharactersInString:@"/"]];
    
    return [NSString stringWithFormat:@"%@/%@", homePath, path];
}


@end

