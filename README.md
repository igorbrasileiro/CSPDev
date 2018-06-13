# CSPDev
An IDE for [CSP](https://en.wikipedia.org/wiki/Communicating_sequential_processes) integrated with FDR tool.  
[PT-BR] Uma IDE para [CSP](https://pt.wikipedia.org/wiki/CSP) integrada a ferramenta FDR.

## Requirements to use Process Analyser
1. Install FDR. [Download link here](https://www.cs.ox.ac.uk/projects/fdr/)
2. Add `fdr/bin` path to Path environment variable __Path__.  
   * For example: my fdr path is `C:\program files\fdr` add to __Path__ `C:\Program Files\fdr\bin;`
3. FDR Should has a valid license

## Instructions to use/install Process Analyser:
Open eclipse, `Window -> Show View -> Other` open `Other` folder and select `Assert List View` or `Counterexample View`  
- Assert List View: show all process from '.csp' open file. Right Click on process to __Check__ !  
- Counterexample View: press double click on process, if process failed it will open an __Eclipse Browser__ with counterexample.  

**OBS** This project uses CSP BNF grammar from this [repository](https://github.com/igorbrasileiro/csp-grammar)
