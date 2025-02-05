# Kaitai Struct: visualization GUI tool in Java

This is a Java-based GUI for data structure visualizations in binary
formats, based on [Kaitai Struct](http://kaitai.io) project. This vis
tool uses [Java's Swing](https://en.wikipedia.org/wiki/Swing_%28Java%29)
for its GUI elements.

![screenshot](screenshot.png)

Its functionality is akin to similar projects:

* [kaitai_struct_visualizer](https://github.com/kaitai-io/kaitai_struct_visualizer) in Ruby
* [WebIDE](https://ide.kaitai.io/) in TypeScript for web brosers
* [kaitai-struct-vscode](https://marketplace.visualstudio.com/items?itemName=fudgepops.kaitai-struct-vscode) in TypeScript for [VS Code](https://code.visualstudio.com/)

... but this Java version is significantly simpler and is no longer under
active development.

Kaitai Struct is a declarative language used to describe various
binary data structures, laid out in files or in memory: i.e. binary
file formats, network stream packet formats, etc.

The main idea is that a particular format is described in Kaitai
Struct language (`.ksy` files) only once and then can be compiled with
this compiler into source files in one of the supported programming
languages. These modules will include the generated code for a parser
that can read described data structure from a file / stream and give
access to it in a nice, easy-to-comprehend API.

## Build

Install Java (JDK 8 or newer) and Apache Maven. If on Windows, also install Git.

Download or clone this repository. Run this command:

```bash
mvn install
```

then the output will be in a subdirectory called `target`.

## Compilation steps

When a ksy file is selected in the gui, two things happen:

<!-- https://mermaid-js.github.io/mermaid/#/flowchart -->

```mermaid
flowchart LR
  KSYFile[Kaitai Struct YAML file\nmy_type.ksy]
  JavaSourceCode[Java source code file\nmyType.java]
  ClassFile[Java class file\nmyType.class]
  KSYFile -- kaitai-struct-compiler --> JavaSourceCode
  JavaSourceCode -- javac --> ClassFile
```

## Usage

Depending on the system, double-clicking on the jar file may launch the GUI.

Otherwise, launch the GUI via the command-line. The exact name of the jar file will vary. The form is:

```bash
java -jar kaitai_struct_visualizer_java.jar
```

The input files can be specified as arguments, in this order:

```bash
java -jar kaitai_struct_visualizer_java.jar binaryFileToParse ksyFile
```

## Licensing

This GUI vis tool project itself is copyright (C) 2016-2022 Kaitai
Project.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or (at
your option) any later version.

This program is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

### Libraries used

Vis tool depends on the following libraries:

* [kaitai_struct_compiler](https://github.com/kaitai_struct_compiler) — GPLv3+ license
  * [fastparse](http://www.lihaoyi.com/fastparse/) — MIT license
  * [snakeyaml](https://bitbucket.org/asomov/snakeyaml) — Apache 2.0 license
* [JHexView](https://github.com/Mingun/JHexView) — LGPL-2.1 license
