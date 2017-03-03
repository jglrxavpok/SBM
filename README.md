SBM - SPIR-V Binary Manipulation library
=======

![SBM Logo](logoSD.png)

SBM is an ObjectWeb's ASM like library to manipulate SPIR-V binary code with Java.

The library uses a visitor model, meaning that SPIR-V modules will be
generated/read with objects having a function for each instruction possible
in the SPIR-V Specifications (up to 1.1 as to 2017/03/03). Those objects are what are called visitors.

Each visitor function name contains the corresponding instruction name with a few variations:

* OpType&lt;TypeName&gt; are converted to CodeVisitor::visit&lt;TypeName&gt;Type(*args...*)
* OpExecutionMode does not have the exact same arguments as the corresponding instruction:
the operands array is directly accessible in the ExecutionMode object.
