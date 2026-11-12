# Brainrot Compiler

A compiler for **Brainrot**, a small programming language whose keywords are internet slang. It is the final project for CS 153 (Concepts of Compiler Design). The compiler is written in Java with ANTLR 4 and generates Jasmin assembly, which is assembled into `.class` files that run on the JVM.

## Requirements

- Java 17 or newer
- ANTLR 4.13.2 and Jasmin, both included in `lib/`

## Build

Run all commands from the project root.

```bash
javac -cp lib/antlr-4.13.2-complete.jar -d build $(find src -name '*.java')
```

The generated ANTLR parser is checked in under `src/intermediate/antlr4`. If you change `Brainrot.g4`, regenerate it before building:

```bash
java -jar lib/antlr-4.13.2-complete.jar -visitor -no-listener -o src/intermediate/antlr4 Brainrot.g4
```

## Compile and run a Brainrot program

There are three steps: compile the source to Jasmin, assemble the Jasmin to a class file, and run it.

```bash
java -cp build:lib/antlr-4.13.2-complete.jar Brainrot -compile examples/Factorial.br
```

```bash
java -jar lib/jasmin.jar Factorial.j
```

```bash
java Factorial
```

The compiler prints a source listing, then runs its passes. The `.j` file is named after the program (the name after `quandale dingle`) and is written to the current directory. If there are syntax or semantic errors, they are all listed and no `.j` file is written.

## Example programs

| File | What it shows |
|---|---|
| `examples/Factorial.br` | Recursion, both data types, precedence, if / else if / else, while and for loops, break and continue, constants |
| `examples/Misc.br` | Nested loops, string escapes, a function declared after main, `skibidi` (exit) |
| `examples/ElseNewline.br` | `seven` (else) on its own line |
| `examples/Skibidi.br` | Runtime error when a function ends without returning a value |
| `examples/DivZero.br` | Runtime error on division by zero |
| `examples/TypeErrors.br` | Type checking: 17 semantic errors reported in one run |
| `examples/SyntaxErrors.br` | Error recovery: 8 syntax errors reported in one run |

## The language

A program is a named class containing a main method and any number of functions, which may be declared before or after main and called before they are declared.

```
quandale dingle Hello:

rizz dabloons square(dabloons n):
    yeet n aura farm n
fade

tung tung sahur:
    bet dabloons x is giving 7
    six (x mogging 5):
        yo gurt("square is", john pork square(x))
    fade
    seven:
        yo gurt("too small")
    fade
fade

thank you thank you thank you
```

Statements are separated by newlines or semicolons. Every block opens with `:` and closes with `fade`.

| Brainrot | Meaning |
|---|---|
| `quandale dingle Name:` | Start of program |
| `thank you thank you thank you` | End of program |
| `tung tung sahur:` | Main method |
| `dabloons` | Integer type |
| `chat` | String type |
| `sigma` | Void return type |
| `bet dabloons x is giving 5` | Variable declaration (the initial value is optional) |
| `24k gold labubu dabloons MAX is giving 10` | Constant declaration |
| `x is giving 5` or `x = 5` | Assignment |
| `rizz dabloons f(dabloons a, chat b):` | Function declaration; parameters are passed by value |
| `john pork f(1, "two")` | Function call |
| `yeet x` | Return |
| `six (cond):` ... `seven six (cond):` ... `seven:` | If, else if, else |
| `tralalero tralala (cond):` | While loop |
| `ballerina cappuccina (init; cond; step):` | For loop |
| `ohio` | Break |
| `tonka jahari` | Continue |
| `skibidi` | Exit the program |
| `yo gurt(a, b, ...)` | Print the arguments separated by spaces |
| `mewing ...` | Comment to end of line |
| `tim cheese java.util.*` | Import (parsed but currently ignored) |

Operators, from lowest to highest precedence. Each has a slang form and a symbol form.

| Brainrot | Symbol | Meaning |
|---|---|---|
| `mogging`, `mogged by`, `lowk mogging`, `lowk mogged by`, `no cap`, `cap` | `>`, `<`, `>=`, `<=`, `==`, `!=` | Comparison |
| `aura gain`, `aura loss` | `+`, `-` | Addition (or string concatenation), subtraction |
| `aura farm`, `chopped cheese` | `*`, `/` | Multiplication, integer division |
| | `+`, `-` | Unary sign |

## Architecture

The compiler has three tiers.

- **Front end** (`src/frontend`, `Brainrot.g4`): the ANTLR 4 lexer and parser, the source listing, and syntax error reporting.
- **Intermediate tier** (`src/intermediate`): the ANTLR parse tree, the symbol table stack (`symtab`), type specifications and type checking (`type`), and the semantic pass (`semantics`), which fills in the symbol tables and decorates the parse tree with types.
- **Back end** (`src/backend`): the code generator (`compiler`), a parse tree visitor that emits Jasmin.

The driver, `src/Brainrot.java`, runs the passes in order: syntax, semantics, code generation. Because each back end is a visitor over the same decorated parse tree and symbol tables, a source-to-source converter or an interpreter can be added as another visitor. The driver already accepts `-convert` and `-execute` options for them, but only `-compile` is implemented.

## Error handling

- **Syntax errors**: the parser recovers after each error and keeps parsing, so one run reports every syntax error in the file.
- **Semantic errors**: the semantic pass reports all type and declaration errors, such as mismatched types, undeclared or redeclared identifiers, wrong argument counts, and assignment to a constant.
- **Runtime errors**: division by zero and a non-void function ending without a `yeet` stop the program with a message.
