package org.jglr.sbm;

public class ExecutionMode {

    public enum Type {
        Invocations,
        SpacingEqual,
        SpacingFractionalEven,
        SpacingFractionalOdd,
        VertexOrderCw,
        VertexOrderCcw,
        PixelCenterInteger,
        OriginUpperLeft,
        OriginLowerLeft,
        EarlyFragmentTests,
        PointMode,
        Xfb,
        DepthReplacing,
        DepthGreater,
        DepthLess,
        DepthUnchanged,
        LocalSize,
        LocalSizeHint,
        InputPoints,
        InputLines,
        InputLinesAdjacency,
        Triangles,
        InputTrianglesAdjacency,
        Quads,
        Isolines,
        OutputVertices,
        OutputPoints,
        OutputLineStrip,
        OutputTriangleStrip,
        VecTypeHint,
        ContractionOff;
    }

    private final Type type;
    private final long[] operands;

    public ExecutionMode(Type type, long... operands) {
        this.type = type;
        this.operands = operands;
    }

    public long[] getOperands() {
        return operands;
    }

    public int getOperandCount() {
        return operands.length;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.name();
    }
}
