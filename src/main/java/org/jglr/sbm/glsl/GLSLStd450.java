package org.jglr.sbm.glsl;

/*
** Copyright (c) 2014-2016 The Khronos Group Inc.
**
** Permission is hereby granted, free of charge, to any person obtaining a copy
** of this software and/or associated documentation files (the "Materials"),
** to deal in the Materials without restriction, including without limitation
** the rights to use, copy, modify, merge, publish, distribute, sublicense,
** and/or sell copies of the Materials, and to permit persons to whom the
** Materials are furnished to do so, subject to the following conditions:
**
** The above copyright notice and this permission notice shall be included in
** all copies or substantial portions of the Materials.
**
** MODIFICATIONS TO THIS FILE MAY MEAN IT NO LONGER ACCURATELY REFLECTS KHRONOS
** STANDARDS. THE UNMODIFIED, NORMATIVE VERSIONS OF KHRONOS SPECIFICATIONS AND
** HEADER INFORMATION ARE LOCATED AT https://www.khronos.org/registry/
**
** THE MATERIALS ARE PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
** OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
** FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
** THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
** LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
** FROM,OUT OF OR IN CONNECTION WITH THE MATERIALS OR THE USE OR OTHER DEALINGS
** IN THE MATERIALS.
*/

/**
 * From <a href="https://raw.githubusercontent.com/KhronosGroup/glslang/master/SPIRV/GLSL.std.450.h">glsllang GLSL.std.450.h file</a>
 */
public enum GLSLStd450 {
    Bad,              // Don't use

    Round,
    RoundEven,
    Trunc,
    FAbs,
    SAbs,
    FSign,
    SSign,
    Floor,
    Ceil,
    Fract,

    Radians,
    Degrees,
    Sin,
    Cos,
    Tan,
    Asin,
    Acos,
    Atan,
    Sinh,
    Cosh,
    Tanh,
    Asinh,
    Acosh,
    Atanh,
    Atan2,

    Pow,
    Exp,
    Log,
    Exp2,
    Log2,
    Sqrt,
    InverseSqrt,

    Determinant,
    MatrixInverse,

    Modf,            // second operand needs an OpVariable to write to
    ModfStruct,      // no OpVariable operand
    FMin,
    UMin,
    SMin,
    FMax,
    UMax,
    SMax,
    FClamp,
    UClamp,
    SClamp,
    FMix,
    IMix,            // Reserved
    Step,
    SmoothStep,

    Fma,
    Frexp,            // second operand needs an OpVariable to write to
    FrexpStruct,      // no OpVariable operand
    Ldexp,

    PackSnorm4x8,
    PackUnorm4x8,
    PackSnorm2x16,
    PackUnorm2x16,
    PackHalf2x16,
    PackDouble2x32,
    UnpackSnorm2x16,
    UnpackUnorm2x16,
    UnpackHalf2x16,
    UnpackSnorm4x8,
    UnpackUnorm4x8,
    UnpackDouble2x32,

    Length,
    Distance,
    Cross,
    Normalize,
    FaceForward,
    Reflect,
    Refract,

    FindILsb,
    FindSMsb,
    FindUMsb,

    InterpolateAtCentroid,
    InterpolateAtSample,
    InterpolateAtOffset,

    NMin,
    NMax,
    NClamp;


    public static final int GLSLstd450Version = 100;
    public static final int GLSLstd450Revision = 1;

}