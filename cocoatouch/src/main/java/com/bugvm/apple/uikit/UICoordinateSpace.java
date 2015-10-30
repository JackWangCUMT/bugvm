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
package com.bugvm.apple.uikit;

/*<imports>*/

import com.bugvm.apple.coregraphics.CGPoint;
import com.bugvm.apple.coregraphics.CGRect;
import com.bugvm.apple.foundation.NSObjectProtocol;
import com.bugvm.objc.annotation.Method;
import com.bugvm.objc.annotation.Property;
import com.bugvm.objc.*;
import com.bugvm.rt.*;
import com.bugvm.rt.annotation.*;
import com.bugvm.rt.bro.*;
import com.bugvm.rt.bro.annotation.*;
import com.bugvm.rt.bro.ptr.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/UICoordinateSpace/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "bounds")
    @ByVal
    CGRect getBounds();
    /*</properties>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "convertPoint:toCoordinateSpace:")
    @ByVal
    CGPoint convertPointToCoordinateSpace(@ByVal CGPoint point, UICoordinateSpace coordinateSpace);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "convertPoint:fromCoordinateSpace:")
    @ByVal CGPoint convertPointFromCoordinateSpace(@ByVal CGPoint point, UICoordinateSpace coordinateSpace);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "convertRect:toCoordinateSpace:")
    @ByVal CGRect convertRectToCoordinateSpace(@ByVal CGRect rect, UICoordinateSpace coordinateSpace);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "convertRect:fromCoordinateSpace:")
    @ByVal CGRect convertRectFromCoordinateSpace(@ByVal CGRect rect, UICoordinateSpace coordinateSpace);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}