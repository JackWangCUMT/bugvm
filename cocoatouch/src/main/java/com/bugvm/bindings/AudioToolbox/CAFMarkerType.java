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
package com.bugvm.bindings.AudioToolbox;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import com.bugvm.objc.*;
import com.bugvm.objc.annotation.*;
import com.bugvm.objc.block.*;
import com.bugvm.rt.*;
import com.bugvm.rt.annotation.*;
import com.bugvm.rt.bro.*;
import com.bugvm.rt.bro.annotation.*;
import com.bugvm.rt.bro.ptr.*;
import com.bugvm.apple.foundation.*;
import com.bugvm.apple.corefoundation.*;
import com.bugvm.apple.audiounit.*;
import com.bugvm.apple.coreaudio.*;
import com.bugvm.apple.coremidi.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/CAFMarkerType/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Generic(0L),
    ProgramStart(1885496679L),
    ProgramEnd(1885695588L),
    TrackStart(1952605543L),
    TrackEnd(1952804452L),
    Index(1768842360L),
    RegionStart(1919051111L),
    RegionEnd(1919250020L),
    RegionSyncPoint(1920170339L),
    SelectionStart(1935828327L),
    SelectionEnd(1936027236L),
    EditSourceBegin(1667392871L),
    EditSourceEnd(1667591780L),
    EditDestinationBegin(1684170087L),
    EditDestinationEnd(1684368996L),
    SustainLoopStart(1936482919L),
    SustainLoopEnd(1936483694L),
    ReleaseLoopStart(1919705703L),
    ReleaseLoopEnd(1919706478L),
    SavedPlayPosition(1936747641L),
    Tempo(1953329263L),
    TimeSignature(1953720679L),
    KeySignature(1802725735L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/CAFMarkerType/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CAFMarkerType/*</name>*/ valueOf(long n) {
        for (/*<name>*/CAFMarkerType/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CAFMarkerType/*</name>*/.class.getName());
    }
}
