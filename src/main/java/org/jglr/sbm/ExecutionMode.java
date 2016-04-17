package org.jglr.sbm;

public abstract class ExecutionMode {

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

    public ExecutionMode(Type type) {
        this.type = type;
    }

    public abstract int getOperandCount();

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.name();
    }
}
