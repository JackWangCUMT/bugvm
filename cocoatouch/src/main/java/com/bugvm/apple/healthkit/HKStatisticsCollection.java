/*
 * Copyright (C) 2013-2015 RoboVM AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bugvm.apple.healthkit;

/*<imports>*/

import com.bugvm.apple.foundation.NSArray;
import com.bugvm.apple.foundation.NSDate;
import com.bugvm.apple.foundation.NSObject;
import com.bugvm.apple.foundation.NSSet;
import com.bugvm.objc.ObjCRuntime;
import com.bugvm.objc.annotation.Block;
import com.bugvm.objc.annotation.Method;
import com.bugvm.objc.annotation.NativeClass;
import com.bugvm.objc.block.VoidBlock2;
import com.bugvm.objc.*;
import com.bugvm.rt.*;
import com.bugvm.rt.annotation.*;
import com.bugvm.rt.bro.*;
import com.bugvm.rt.bro.annotation.*;
import com.bugvm.rt.bro.ptr.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("HealthKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKStatisticsCollection/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class HKStatisticsCollectionPtr extends Ptr<HKStatisticsCollection, HKStatisticsCollectionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(HKStatisticsCollection.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public HKStatisticsCollection() {}
    protected HKStatisticsCollection(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "statisticsForDate:")
    public native HKStatistics getStatisticsForDate(NSDate date);
    @Method(selector = "enumerateStatisticsFromDate:toDate:withBlock:")
    public native void enumerateStatistics(NSDate startDate, NSDate endDate, @Block VoidBlock2<HKStatistics, Boolean> block);
    @Method(selector = "statistics")
    public native NSArray<HKStatistics> getStatistics();
    @Method(selector = "sources")
    public native NSSet<HKSource> getSources();
    /*</methods>*/
}