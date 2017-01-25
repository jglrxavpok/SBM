package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.sampler.ImageOperands;
import org.jglr.sbm.types.Type;

import java.util.Map;

public class ImageSampleImplicitLodInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private final long sampledImage;
    private final long coordinate;
    private final ImageOperands operands;
    private final Map<Integer, long[]> splitOperands;
    private Type resultType;
    private String sampledImageName;
    private String coordinateName;

    public ImageSampleImplicitLodInstruction(long resultTypeID, long resultID, long sampledImage, long coordinate, ImageOperands operands, Map<Integer, long[]> splitOperands) {
        super(OpImageSampleImplicitLod, 5+(operands.getMask() == 0 ? 0 : 1+operands.getOperandCount()), resultID);
        this.resultTypeID = resultTypeID;
        this.sampledImage = sampledImage;
        this.coordinate = coordinate;
        this.operands = operands;
        this.splitOperands = splitOperands;
    }

    public long getCoordinate() {
        return coordinate;
    }

    public ImageOperands getOperands() {
        return operands;
    }

    public long getResultTypeID() {
        return resultTypeID;
    }

    public long getSampledImage() {
        return sampledImage;
    }

    public Map<Integer, long[]> getSplitOperands() {
        return splitOperands;
    }

    public String getCoordinateName() {
        return coordinateName;
    }

    public Type getResultType() {
        return resultType;
    }

    public String getSampledImageName() {
        return sampledImageName;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        resultType = infoPool.getType(resultTypeID);
        sampledImageName = infoPool.getName(sampledImage);
        coordinateName = infoPool.getName(coordinate);
    }

    @Override
    public String toString() {
        String operandStr;
        if(operands.getMask() == 0) {
            operandStr = "";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("0x").append(Long.toHexString(operands.getMask())).append('(');
            splitOperands.entrySet().forEach(e -> {
                builder.append(Integer.toHexString(e.getKey()));
                builder.append("(");
                long[] values = e.getValue();
                for (int i = 0; i < values.length; i++) {
                    long v = values[i];
                    if(i != 0)
                        builder.append(", ");
                    builder.append("0x").append(Long.toHexString(v));
                }
                builder.append("), ");
            });
            builder.append(')');
            operandStr = builder.toString();
        }
        return "OpImageSampleImplicitLod ("+nameOrID(resultTypeID, resultType)+") "+nameOrID(sampledImage, sampledImageName)+" "+nameOrID(coordinate, coordinateName)+operandStr;
    }
}
