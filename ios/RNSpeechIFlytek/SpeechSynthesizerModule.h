//
//  SpeechSynthesizerModule.h
//  RNSpeechIFlytek
//
//  Created by 张棚贺 on 2018/1/4.
//  Copyright © 2018年 张棚贺. All rights reserved.
//

#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>
#import <iflyMSC/IFlyMSC.h>

@interface SpeechSynthesizerModule : RCTEventEmitter <RCTBridgeModule, IFlySpeechSynthesizerDelegate> {
    BOOL hasListeners;
}

@property (nonatomic, strong) IFlySpeechSynthesizer * iFlySpeechSynthesizer;
@property (nonatomic) NSTimeInterval startTime;
@property (nonatomic) NSTimeInterval endTime;
@property (nonatomic, strong) NSString * content;
@property (nonatomic, strong) NSString * filename;

@end

