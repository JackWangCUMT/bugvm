/*
 * Copyright (C) 2013 Trillian AB
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
package org.robovm.rt.bro;

import java.lang.reflect.Array;

import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.Marshaler;
import org.robovm.rt.bro.annotation.Marshalers;

/**
 * Defines builtin marshalers. This is the final fallback when searching for
 * a marshaler.
 */
@Marshalers({
    @Marshaler(type = byte[].class, value = BuiltinMarshalers.Arrays.ByteArrayMarshaler.class),
    @Marshaler(type = byte[][].class, value = BuiltinMarshalers.Arrays.ByteArrayMarshaler.class),
    @Marshaler(type = byte[][][].class, value = BuiltinMarshalers.Arrays.ByteArrayMarshaler.class),
    @Marshaler(type = short[].class, value = BuiltinMarshalers.Arrays.ShortArrayMarshaler.class),
    @Marshaler(type = short[][].class, value = BuiltinMarshalers.Arrays.ShortArrayMarshaler.class),
    @Marshaler(type = short[][][].class, value = BuiltinMarshalers.Arrays.ShortArrayMarshaler.class),
    @Marshaler(type = char[].class, value = BuiltinMarshalers.Arrays.CharArrayMarshaler.class),
    @Marshaler(type = char[][].class, value = BuiltinMarshalers.Arrays.CharArrayMarshaler.class),
    @Marshaler(type = char[][][].class, value = BuiltinMarshalers.Arrays.CharArrayMarshaler.class),
    @Marshaler(type = int[].class, value = BuiltinMarshalers.Arrays.IntArrayMarshaler.class),
    @Marshaler(type = int[][].class, value = BuiltinMarshalers.Arrays.IntArrayMarshaler.class),
    @Marshaler(type = int[][][].class, value = BuiltinMarshalers.Arrays.IntArrayMarshaler.class),
    @Marshaler(type = long[].class, value = BuiltinMarshalers.Arrays.LongArrayMarshaler.class),
    @Marshaler(type = long[][].class, value = BuiltinMarshalers.Arrays.LongArrayMarshaler.class),
    @Marshaler(type = long[][][].class, value = BuiltinMarshalers.Arrays.LongArrayMarshaler.class),
    @Marshaler(type = float[].class, value = BuiltinMarshalers.Arrays.FloatArrayMarshaler.class),
    @Marshaler(type = float[][].class, value = BuiltinMarshalers.Arrays.FloatArrayMarshaler.class),
    @Marshaler(type = float[][][].class, value = BuiltinMarshalers.Arrays.FloatArrayMarshaler.class),
    @Marshaler(type = double[].class, value = BuiltinMarshalers.Arrays.DoubleArrayMarshaler.class),
    @Marshaler(type = double[][].class, value = BuiltinMarshalers.Arrays.DoubleArrayMarshaler.class),
    @Marshaler(type = double[][][].class, value = BuiltinMarshalers.Arrays.DoubleArrayMarshaler.class),
    @Marshaler(type = Struct[].class, value = BuiltinMarshalers.Arrays.StructArrayMarshaler.class),
    @Marshaler(type = Struct[][].class, value = BuiltinMarshalers.Arrays.StructArrayMarshaler.class),
    @Marshaler(type = Struct[][][].class, value = BuiltinMarshalers.Arrays.StructArrayMarshaler.class),
})
public class BuiltinMarshalers {

    public static class Arrays {
        
        private static void checkDimensions(String type, String suffix, int actual, int expected) {
            if (actual != expected) {
                throw new IllegalArgumentException("Expected " + type + "[" 
                        + expected + "]" + suffix + ". Got " 
                        + type + "[" + actual + "]" + suffix);
            }
        }

        private static void checkDimensions(Class<?> baseType, String format, int actual, int expected) {
            if (actual != expected) {
                String suffixActual = String.format(format, actual);
                String suffixExpected = String.format(format, expected);
                throw new IllegalArgumentException(
                        "Expected " + baseType.getName() + suffixExpected 
                        + ". Got " + baseType.getName() + suffixActual);
            }
        }

        /**
         * Builtin marshalers for {@code byte[]}, {@code byte[][]} and
         * {@code byte[][][]}.
         */
        public static class ByteArrayMarshaler {
            private static final String TYPE = "byte";
            private static final int SHIFT = 0;
            public static Object toObject(Class<?> arrayClass, long handle, int d1) {
                final byte[] o = new byte[d1];
                final int off = d1 << SHIFT;
                VM.memcpy(VM.getArrayValuesAddress(o), handle, off);
                return o;
            }
            public static void toNative(Object object, long handle, int d1) {
                final byte[] o = (byte[]) object;
                final int off = d1 << SHIFT;
                checkDimensions(TYPE, "", o.length, d1);
                VM.memcpy(handle, VM.getArrayValuesAddress(o), off);
            }
            public static Object toObject(Class<?> arrayClass, long handle, int d1, int d2) {
                final byte[][] o = new byte[d1][d2];
                final int off = d2 << SHIFT;
                for (int i = 0; i < d1; i++) {
                    VM.memcpy(VM.getArrayValuesAddress(o[i]), handle, off);
                    handle += off;
                }
                return o;
            }
            public static void toNative(Object object, long handle, int d1, int d2) {
                final byte[][] o = (byte[][]) object;
                final int off = d2 << SHIFT;
                checkDimensions(TYPE, "[]", o.length, d1);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    checkDimensions(TYPE + "[]", "", o[i].length, d2);
                }
                for (int i = 0; i < len1; i++) {
                    VM.memcpy(handle, VM.getArrayValuesAddress(o[i]), off);
                    handle += off;
                }
            }
            public static Object toObject(Class<?> arrayClass, long handle, int d1, int d2, int d3) {
                final byte[][][] o = new byte[d1][d2][d3];
                final int off = d3 << SHIFT;
                for (int i = 0; i < d1; i++) {
                    for (int j = 0; j < d2; j++) {
                        VM.memcpy(VM.getArrayValuesAddress(o[i][j]), handle, off);
                        handle += off;
                    }
                }
                return o;
            }
            public static void toNative(Object object, long handle, int d1, int d2, int d3) {
                final byte[][][] o = (byte[][][]) object;
                byte[][] p;
                final int off = d3 << SHIFT;
                checkDimensions(TYPE, "[][]", o.length, d1);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    p = o[i];
                    checkDimensions(TYPE + "[]", "[]", p.length, d2);
                    int len2 = p.length;
                    for (int j = 0; j < len2; j++) {
                        checkDimensions(TYPE + "[][]", "", p[j].length, d3);
                    }
                }
                for (int i = 0; i < len1; i++) {
                    p = o[i];
                    int len2 = p.length;
                    for (int j = 0; j < len2; j++) {
                        VM.memcpy(handle, VM.getArrayValuesAddress(p[j]), off);
                        handle += off;
                    }
                }
            }
        }
        
        /**
         * Builtin marshalers for {@code short[]}, {@code short[][]} and
         * {@code short[][][]}.
         */
        public static class ShortArrayMarshaler {
            private static final String TYPE = "short";
            private static final int SHIFT = 1;
            public static Object toObject(Class<?> arrayClass, long handle, int d1) {
                final short[] o = new short[d1];
                final int off = d1 << SHIFT;
                VM.memcpy(VM.getArrayValuesAddress(o), handle, off);
                return o;
            }
            public static void toNative(Object object, long handle, int d1) {
                final short[] o = (short[]) object;
                final int off = d1 << SHIFT;
                checkDimensions(TYPE, "", o.length, d1);
                VM.memcpy(handle, VM.getArrayValuesAddress(o), off);
            }
            public static Object toObject(Class<?> arrayClass, long handle, int d1, int d2) {
                final short[][] o = new short[d1][d2];
                final int off = d2 << SHIFT;
                for (int i = 0; i < d1; i++) {
                    VM.memcpy(VM.getArrayValuesAddress(o[i]), handle, off);
                    handle += off;
                }
                return o;
            }
            public static void toNative(Object object, long handle, int d1, int d2) {
                final short[][] o = (short[][]) object;
                final int off = d2 << SHIFT;
                checkDimensions(TYPE, "[]", o.length, d1);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    checkDimensions(TYPE + "[]", "", o[i].length, d2);
                }
                for (int i = 0; i < len1; i++) {
                    VM.memcpy(handle, VM.getArrayValuesAddress(o[i]), off);
                    handle += off;
                }
            }
            public static Object toObject(Class<?> arrayClass, long handle, int d1, int d2, int d3) {
                final short[][][] o = new short[d1][d2][d3];
                final int off = d3 << SHIFT;
                for (int i = 0; i < d1; i++) {
                    for (int j = 0; j < d2; j++) {
                        VM.memcpy(VM.getArrayValuesAddress(o[i][j]), handle, off);
                        handle += off;
                    }
                }
                return o;
            }
            public static void toNative(Object object, long handle, int d1, int d2, int d3) {
                final short[][][] o = (short[][][]) object;
                short[][] p;
                final int off = d3 << SHIFT;
                checkDimensions(TYPE, "[][]", o.length, d1);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    p = o[i];
                    checkDimensions(TYPE + "[]", "[]", p.length, d2);
                    int len2 = p.length;
                    for (int j = 0; j < len2; j++) {
                        checkDimensions(TYPE + "[][]", "", p[j].length, d3);
                    }
                }
                for (int i = 0; i < len1; i++) {
                    p = o[i];
                    int len2 = p.length;
                    for (int j = 0; j < len2; j++) {
                        VM.memcpy(handle, VM.getArrayValuesAddress(p[j]), off);
                        handle += off;
                    }
                }
            }
        }
        
        /**
         * Builtin marshalers for {@code char[]}, {@code char[][]} and
         * {@code char[][][]}.
         */
        public static class CharArrayMarshaler {
            private static final String TYPE = "char";
            private static final int SHIFT = 1;
            public static Object toObject(Class<?> arrayClass, long handle, int d1) {
                final char[] o = new char[d1];
                final int off = d1 << SHIFT;
                VM.memcpy(VM.getArrayValuesAddress(o), handle, off);
                return o;
            }
            public static void toNative(Object object, long handle, int d1) {
                final char[] o = (char[]) object;
                final int off = d1 << SHIFT;
                checkDimensions(TYPE, "", o.length, d1);
                VM.memcpy(handle, VM.getArrayValuesAddress(o), off);
            }
            public static Object toObject(Class<?> arrayClass, long handle, int d1, int d2) {
                final char[][] o = new char[d1][d2];
                final int off = d2 << SHIFT;
                for (int i = 0; i < d1; i++) {
                    VM.memcpy(VM.getArrayValuesAddress(o[i]), handle, off);
                    handle += off;
                }
                return o;
            }
            public static void toNative(Object object, long handle, int d1, int d2) {
                final char[][] o = (char[][]) object;
                final int off = d2 << SHIFT;
                checkDimensions(TYPE, "[]", o.length, d1);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    checkDimensions(TYPE + "[]", "", o[i].length, d2);
                }
                for (int i = 0; i < len1; i++) {
                    VM.memcpy(handle, VM.getArrayValuesAddress(o[i]), off);
                    handle += off;
                }
            }
            public static Object toObject(Class<?> arrayClass, long handle, int d1, int d2, int d3) {
                final char[][][] o = new char[d1][d2][d3];
                final int off = d3 << SHIFT;
                for (int i = 0; i < d1; i++) {
                    for (int j = 0; j < d2; j++) {
                        VM.memcpy(VM.getArrayValuesAddress(o[i][j]), handle, off);
                        handle += off;
                    }
                }
                return o;
            }
            public static void toNative(Object object, long handle, int d1, int d2, int d3) {
                final char[][][] o = (char[][][]) object;
                char[][] p;
                final int off = d3 << SHIFT;
                checkDimensions(TYPE, "[][]", o.length, d1);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    p = o[i];
                    checkDimensions(TYPE + "[]", "[]", p.length, d2);
                    int len2 = p.length;
                    for (int j = 0; j < len2; j++) {
                        checkDimensions(TYPE + "[][]", "", p[j].length, d3);
                    }
                }
                for (int i = 0; i < len1; i++) {
                    p = o[i];
                    int len2 = p.length;
                    for (int j = 0; j < len2; j++) {
                        VM.memcpy(handle, VM.getArrayValuesAddress(p[j]), off);
                        handle += off;
                    }
                }
            }
        }
        
        /**
         * Builtin marshalers for {@code int[]}, {@code int[][]} and
         * {@code int[][][]}.
         */
        public static class IntArrayMarshaler {
            private static final String TYPE = "int";
            private static final int SHIFT = 2;
            public static Object toObject(Class<?> arrayClass, long handle, int d1) {
                final int[] o = new int[d1];
                final int off = d1 << SHIFT;
                VM.memcpy(VM.getArrayValuesAddress(o), handle, off);
                return o;
            }
            public static void toNative(Object object, long handle, int d1) {
                final int[] o = (int[]) object;
                final int off = d1 << SHIFT;
                checkDimensions(TYPE, "", o.length, d1);
                VM.memcpy(handle, VM.getArrayValuesAddress(o), off);
            }
            public static Object toObject(Class<?> arrayClass, long handle, int d1, int d2) {
                final int[][] o = new int[d1][d2];
                final int off = d2 << SHIFT;
                for (int i = 0; i < d1; i++) {
                    VM.memcpy(VM.getArrayValuesAddress(o[i]), handle, off);
                    handle += off;
                }
                return o;
            }
            public static void toNative(Object object, long handle, int d1, int d2) {
                final int[][] o = (int[][]) object;
                final int off = d2 << SHIFT;
                checkDimensions(TYPE, "[]", o.length, d1);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    checkDimensions(TYPE + "[]", "", o[i].length, d2);
                }
                for (int i = 0; i < len1; i++) {
                    VM.memcpy(handle, VM.getArrayValuesAddress(o[i]), off);
                    handle += off;
                }
            }
            public static Object toObject(Class<?> arrayClass, long handle, int d1, int d2, int d3) {
                final int[][][] o = new int[d1][d2][d3];
                final int off = d3 << SHIFT;
                for (int i = 0; i < d1; i++) {
                    for (int j = 0; j < d2; j++) {
                        VM.memcpy(VM.getArrayValuesAddress(o[i][j]), handle, off);
                        handle += off;
                    }
                }
                return o;
            }
            public static void toNative(Object object, long handle, int d1, int d2, int d3) {
                final int[][][] o = (int[][][]) object;
                int[][] p;
                final int off = d3 << SHIFT;
                checkDimensions(TYPE, "[][]", o.length, d1);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    p = o[i];
                    checkDimensions(TYPE + "[]", "[]", p.length, d2);
                    int len2 = p.length;
                    for (int j = 0; j < len2; j++) {
                        checkDimensions(TYPE + "[][]", "", p[j].length, d3);
                    }
                }
                for (int i = 0; i < len1; i++) {
                    p = o[i];
                    int len2 = p.length;
                    for (int j = 0; j < len2; j++) {
                        VM.memcpy(handle, VM.getArrayValuesAddress(p[j]), off);
                        handle += off;
                    }
                }
            }
        }
        
        /**
         * Builtin marshalers for {@code long[]}, {@code long[][]} and
         * {@code long[][][]}.
         */
        public static class LongArrayMarshaler {
            private static final String TYPE = "long";
            private static final int SHIFT = 3;
            public static Object toObject(Class<?> arrayClass, long handle, int d1) {
                final long[] o = new long[d1];
                final int off = d1 << SHIFT;
                VM.memcpy(VM.getArrayValuesAddress(o), handle, off);
                return o;
            }
            public static void toNative(Object object, long handle, int d1) {
                final long[] o = (long[]) object;
                final int off = d1 << SHIFT;
                checkDimensions(TYPE, "", o.length, d1);
                VM.memcpy(handle, VM.getArrayValuesAddress(o), off);
            }
            public static Object toObject(Class<?> arrayClass, long handle, int d1, int d2) {
                final long[][] o = new long[d1][d2];
                final int off = d2 << SHIFT;
                for (int i = 0; i < d1; i++) {
                    VM.memcpy(VM.getArrayValuesAddress(o[i]), handle, off);
                    handle += off;
                }
                return o;
            }
            public static void toNative(Object object, long handle, int d1, int d2) {
                final long[][] o = (long[][]) object;
                final int off = d2 << SHIFT;
                checkDimensions(TYPE, "[]", o.length, d1);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    checkDimensions(TYPE + "[]", "", o[i].length, d2);
                }
                for (int i = 0; i < len1; i++) {
                    VM.memcpy(handle, VM.getArrayValuesAddress(o[i]), off);
                    handle += off;
                }
            }
            public static Object toObject(Class<?> arrayClass, long handle, int d1, int d2, int d3) {
                final long[][][] o = new long[d1][d2][d3];
                final int off = d3 << SHIFT;
                for (int i = 0; i < d1; i++) {
                    for (int j = 0; j < d2; j++) {
                        VM.memcpy(VM.getArrayValuesAddress(o[i][j]), handle, off);
                        handle += off;
                    }
                }
                return o;
            }
            public static void toNative(Object object, long handle, int d1, int d2, int d3) {
                final long[][][] o = (long[][][]) object;
                long[][] p;
                final int off = d3 << SHIFT;
                checkDimensions(TYPE, "[][]", o.length, d1);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    p = o[i];
                    checkDimensions(TYPE + "[]", "[]", p.length, d2);
                    int len2 = p.length;
                    for (int j = 0; j < len2; j++) {
                        checkDimensions(TYPE + "[][]", "", p[j].length, d3);
                    }
                }
                for (int i = 0; i < len1; i++) {
                    p = o[i];
                    int len2 = p.length;
                    for (int j = 0; j < len2; j++) {
                        VM.memcpy(handle, VM.getArrayValuesAddress(p[j]), off);
                        handle += off;
                    }
                }
            }
        }
        
        /**
         * Builtin marshalers for {@code float[]}, {@code float[][]} and
         * {@code float[][][]}.
         */
        public static class FloatArrayMarshaler {
            private static final String TYPE = "float";
            private static final int SHIFT = 2;
            public static Object toObject(Class<?> arrayClass, long handle, int d1) {
                final float[] o = new float[d1];
                final int off = d1 << SHIFT;
                VM.memcpy(VM.getArrayValuesAddress(o), handle, off);
                return o;
            }
            public static void toNative(Object object, long handle, int d1) {
                final float[] o = (float[]) object;
                final int off = d1 << SHIFT;
                checkDimensions(TYPE, "", o.length, d1);
                VM.memcpy(handle, VM.getArrayValuesAddress(o), off);
            }
            public static Object toObject(Class<?> arrayClass, long handle, int d1, int d2) {
                final float[][] o = new float[d1][d2];
                final int off = d2 << SHIFT;
                for (int i = 0; i < d1; i++) {
                    VM.memcpy(VM.getArrayValuesAddress(o[i]), handle, off);
                    handle += off;
                }
                return o;
            }
            public static void toNative(Object object, long handle, int d1, int d2) {
                final float[][] o = (float[][]) object;
                final int off = d2 << SHIFT;
                checkDimensions(TYPE, "[]", o.length, d1);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    checkDimensions(TYPE + "[]", "", o[i].length, d2);
                }
                for (int i = 0; i < len1; i++) {
                    VM.memcpy(handle, VM.getArrayValuesAddress(o[i]), off);
                    handle += off;
                }
            }
            public static Object toObject(Class<?> arrayClass, long handle, int d1, int d2, int d3) {
                final float[][][] o = new float[d1][d2][d3];
                final int off = d3 << SHIFT;
                for (int i = 0; i < d1; i++) {
                    for (int j = 0; j < d2; j++) {
                        VM.memcpy(VM.getArrayValuesAddress(o[i][j]), handle, off);
                        handle += off;
                    }
                }
                return o;
            }
            public static void toNative(Object object, long handle, int d1, int d2, int d3) {
                final float[][][] o = (float[][][]) object;
                float[][] p;
                final int off = d3 << SHIFT;
                checkDimensions(TYPE, "[][]", o.length, d1);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    p = o[i];
                    checkDimensions(TYPE + "[]", "[]", p.length, d2);
                    int len2 = p.length;
                    for (int j = 0; j < len2; j++) {
                        checkDimensions(TYPE + "[][]", "", p[j].length, d3);
                    }
                }
                for (int i = 0; i < len1; i++) {
                    p = o[i];
                    int len2 = p.length;
                    for (int j = 0; j < len2; j++) {
                        VM.memcpy(handle, VM.getArrayValuesAddress(p[j]), off);
                        handle += off;
                    }
                }
            }
        }
        
        /**
         * Builtin marshalers for {@code double[]}, {@code double[][]} and
         * {@code double[][][]}.
         */
        public static class DoubleArrayMarshaler {
            private static final String TYPE = "double";
            private static final int SHIFT = 3;
            public static Object toObject(Class<?> arrayClass, long handle, int d1) {
                final double[] o = new double[d1];
                final int off = d1 << SHIFT;
                VM.memcpy(VM.getArrayValuesAddress(o), handle, off);
                return o;
            }
            public static void toNative(Object object, long handle, int d1) {
                final double[] o = (double[]) object;
                final int off = d1 << SHIFT;
                checkDimensions(TYPE, "", o.length, d1);
                VM.memcpy(handle, VM.getArrayValuesAddress(o), off);
            }
            public static Object toObject(Class<?> arrayClass, long handle, int d1, int d2) {
                final double[][] o = new double[d1][d2];
                final int off = d2 << SHIFT;
                for (int i = 0; i < d1; i++) {
                    VM.memcpy(VM.getArrayValuesAddress(o[i]), handle, off);
                    handle += off;
                }
                return o;
            }
            public static void toNative(Object object, long handle, int d1, int d2) {
                final double[][] o = (double[][]) object;
                final int off = d2 << SHIFT;
                checkDimensions(TYPE, "[]", o.length, d1);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    checkDimensions(TYPE + "[]", "", o[i].length, d2);
                }
                for (int i = 0; i < len1; i++) {
                    VM.memcpy(handle, VM.getArrayValuesAddress(o[i]), off);
                    handle += off;
                }
            }
            public static Object toObject(Class<?> arrayClass, long handle, int d1, int d2, int d3) {
                final double[][][] o = new double[d1][d2][d3];
                final int off = d3 << SHIFT;
                for (int i = 0; i < d1; i++) {
                    for (int j = 0; j < d2; j++) {
                        VM.memcpy(VM.getArrayValuesAddress(o[i][j]), handle, off);
                        handle += off;
                    }
                }
                return o;
            }
            public static void toNative(Object object, long handle, int d1, int d2, int d3) {
                final double[][][] o = (double[][][]) object;
                double[][] p;
                final int off = d3 << SHIFT;
                checkDimensions(TYPE, "[][]", o.length, d1);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    p = o[i];
                    checkDimensions(TYPE + "[]", "[]", p.length, d2);
                    int len2 = p.length;
                    for (int j = 0; j < len2; j++) {
                        checkDimensions(TYPE + "[][]", "", p[j].length, d3);
                    }
                }
                for (int i = 0; i < len1; i++) {
                    p = o[i];
                    int len2 = p.length;
                    for (int j = 0; j < len2; j++) {
                        VM.memcpy(handle, VM.getArrayValuesAddress(p[j]), off);
                        handle += off;
                    }
                }
            }
        }
        
        /**
         * Builtin marshalers for {@code Struct[]}, {@code Struct[][]} and
         * {@code Struct[][][]}.
         */
        public static class StructArrayMarshaler {
            @SuppressWarnings("unchecked")
            public static <T extends Struct<T>> Object toObject(Class<T[]> arrayClass, long handle, int d1) {
                T s = Struct.toStruct((Class<T>) arrayClass.getComponentType(), handle);
                return s.toArray(d1);
            }
            @SuppressWarnings("unchecked")
            public static <T extends Struct<T>> void toNative(Object object, long handle, int d1) {
                final T[] o = (T[]) object;
                Class<T> structClass = (Class<T>) object.getClass().getComponentType();
                checkDimensions(structClass, "[%d]", o.length, d1);
                Struct<T> s = Struct.toStruct((Class<T>) structClass, handle);
                s.update(o);
            }
            @SuppressWarnings("unchecked")
            public static <T extends Struct<T>> Object toObject(Class<T[]> arrayClass, long handle, int d1, int d2) {
                Class<T> structClass = (Class<T>) arrayClass.getComponentType().getComponentType();
                T[][] o = (T[][]) Array.newInstance(structClass, d1, d2);
                T s = Struct.toStruct((Class<T>) structClass, handle);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    o[i] = s.toArray(d2);
                    s = s.next(d2);
                }
                return o;
            }
            @SuppressWarnings("unchecked")
            public static <T extends Struct<T>> void toNative(Object object, long handle, int d1, int d2) {
                final T[][] o = (T[][]) object;
                Class<T> structClass = (Class<T>) object.getClass().getComponentType().getComponentType();
                checkDimensions(structClass, "[%d][]", o.length, d1);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    checkDimensions(structClass, "[][%d]", o[i].length, d2);
                }
                Struct<T> s = Struct.toStruct((Class<T>) structClass, handle);
                for (int i = 0; i < len1; i++) {
                    s.update(o[i]);
                    s = s.next(d2);
                }
            }
            @SuppressWarnings("unchecked")
            public static <T extends Struct<T>> Object toObject(Class<T[]> arrayClass, long handle, int d1, int d2, int d3) {
                Class<T> structClass = (Class<T>) arrayClass.getComponentType().getComponentType().getComponentType();
                T[][][] o = (T[][][]) Array.newInstance(structClass, d1, d2, d3);
                T s = Struct.toStruct((Class<T>) structClass, handle);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    int len2 = o[i].length;
                    for (int j = 0; j < len2; j++) {
                        o[i][j] = s.toArray(d3);
                        s = s.next(d3);
                    }
                }
                return o;
            }
            @SuppressWarnings("unchecked")
            public static <T extends Struct<T>> void toNative(Object object, long handle, int d1, int d2, int d3) {
                final T[][][] o = (T[][][]) object;
                Class<T> structClass = (Class<T>) object.getClass().getComponentType().getComponentType().getComponentType();
                checkDimensions(structClass, "[%d][][]", o.length, d1);
                int len1 = o.length;
                for (int i = 0; i < len1; i++) {
                    T[][] p = o[i];
                    checkDimensions(structClass, "[][%d][]", p.length, d2);
                    int len2 = p.length;
                    for (int j = 0; j < len2; j++) {
                        checkDimensions(structClass, "[][][%d]", p[j].length, d3);
                    }
                }
                Struct<T> s = Struct.toStruct((Class<T>) structClass, handle);
                for (int i = 0; i < len1; i++) {
                    T[][] p = o[i];
                    int len2 = p.length;
                    for (int j = 0; j < len2; j++) {
                        s.update(o[i][j]);
                        s = s.next(d3);
                    }
                }
            }
        }
    }
    
}