package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.SourceLanguage;

public class SourceInstruction extends SpvInstruction implements ResolvableInstruction {
    private final SourceLanguage language;
    private final long version;
    private final long filenameStringID;
    private final String sourceCode;
    private String filename;

    public SourceInstruction(SourceLanguage language, long version, long filenameStringID, String sourceCode) {
        super(SOURCE, 3 + (filenameStringID != -1 ? 1 : 0) + (sourceCode != null ? getWordCount(sourceCode) : 0));
        this.language = language;
        this.version = version;
        this.filenameStringID = filenameStringID;
        this.sourceCode = sourceCode;
    }

    public long getFilenameStringID() {
        return filenameStringID;
    }

    public SourceLanguage getLanguage() {
        return language;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public long getVersion() {
        return version;
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public String toString() {
        String result = "Source "+language+" v"+version;
        if(filename != null)
            result += " ("+filename+")";
        else if(filenameStringID != -1)
            result += " (filenameID: "+filenameStringID+")";
        return result;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        filename = infoPool.getString(filenameStringID);
    }
}
