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
package com.bugvm.apple.mapkit;

/*<imports>*/

import com.bugvm.apple.corelocation.CLLocationCoordinate2D;
import com.bugvm.apple.foundation.NSObject;
import com.bugvm.apple.foundation.NSRange;
import com.bugvm.objc.ObjCRuntime;
import com.bugvm.objc.annotation.Method;
import com.bugvm.objc.annotation.NativeClass;
import com.bugvm.objc.annotation.Property;
import com.bugvm.objc.*;
import com.bugvm.rt.*;
import com.bugvm.rt.annotation.*;
import com.bugvm.rt.bro.*;
import com.bugvm.rt.bro.annotation.*;
import com.bugvm.rt.bro.ptr.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MapKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKMultiPoint/*</name>*/ 
    extends /*<extends>*/MKShape/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MKMultiPointPtr extends Ptr<MKMultiPoint, MKMultiPointPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MKMultiPoint.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKMultiPoint() {}
    protected MKMultiPoint(long handle) { super(handle); }
    protected MKMultiPoint(NSObject.SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "pointCount")
    public native @MachineSizedUInt long getPointCount();
    /*</properties>*/
    /*<members>*//*</members>*/
    public MKMapPoint[] getPoints() {
        return getPoints0().toArray((int) getPointCount());
    }
    public CLLocationCoordinate2D[] getCoordinates(@ByVal NSRange range) {
        CLLocationCoordinate2D coords = Struct.allocate(CLLocationCoordinate2D.class, (int)range.getLength());
        getCoordinates0(coords, range);
        return coords.toArray((int) range.getLength());
    }
    /*<methods>*/
    @Method(selector = "points")
    protected native MKMapPoint getPoints0();
    @Method(selector = "getCoordinates:range:")
    protected native void getCoordinates0(CLLocationCoordinate2D coords, @ByVal NSRange range);
    /*</methods>*/
}