package org.jglr.sbm;

import org.jglr.sbm.instructions.CapabilityInstruction;
import org.jglr.sbm.instructions.ExtendedInstructionSetImportInstruction;
import org.jglr.sbm.instructions.SourceInstruction;
import org.jglr.sbm.instructions.SpvInstruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class SBMModule implements Opcodes {

    private final List<SpvInstruction> instructions;
    private final InfoPool infoPool;
    private final List<Capability> capabilities;
    private final List<String> setImports;
    private Map<Integer, Consumer<SpvInstruction>> handlers;
    private String source;
    private String filename;
    private long languageVersion;
    private SourceLanguage language;

    public SBMModule(List<SpvInstruction> instructions, InfoPool infoPool) {
        this.instructions = instructions;
        this.infoPool = infoPool;
        handlers = new HashMap<>();

        language = SourceLanguage.Unknown;
        languageVersion = 0;
        filename = null;
        source = null;
        capabilities = new ArrayList<>();
        setImports = new ArrayList<>();
        init();
    }

    private void init() {
        handlers.put(SOURCE, this::handleSource);
        handlers.put(CAPABILITY, this::handleCap);
        handlers.put(EXT_INST_IMPORT, this::handleSetImport);
        instructions.forEach(in -> getHandler(in.getOpcode()).accept(in));
    }

    private void handleSetImport(SpvInstruction spvInstruction) {
        ExtendedInstructionSetImportInstruction importInstruction = (ExtendedInstructionSetImportInstruction) spvInstruction;
        setImports.add(importInstruction.getName());
    }

    private void handleCap(SpvInstruction spvInstruction) {
        CapabilityInstruction instruction = (CapabilityInstruction) spvInstruction;
        capabilities.add(instruction.getCapability());
    }

    private void handleSource(SpvInstruction inst) {
        SourceInstruction instruction = (SourceInstruction) inst;
        this.language = instruction.getLanguage();
        this.languageVersion = instruction.getVersion();
        this.filename = instruction.getFilename();
        this.source = instruction.getSourceCode();
    }

    private Consumer<SpvInstruction> getHandler(int opcode) {
        return handlers.getOrDefault(opcode, i -> {});
    }

    public InfoPool getInfoPool() {
        return infoPool;
    }

    public List<SpvInstruction> getInstructions() {
        return instructions;
    }

    public String getFilename() {
        return filename;
    }

    public SourceLanguage getLanguage() {
        return language;
    }

    public long getLanguageVersion() {
        return languageVersion;
    }

    public String getSource() {
        return source;
    }

    public List<Capability> getCapabilities() {
        return capabilities;
    }

    public List<String> getSetImports() {
        return setImports;
    }
}
