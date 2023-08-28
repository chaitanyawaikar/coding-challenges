# Write Your Own wc Tool

This challenge is to build your own version of the Unix customCommand line tool wc!

The Unix customCommand line tools are a great metaphor for good software engineering and they follow the Unix
Philosophies of:

Writing simple parts connected by clean interfaces - each tool does just one thing and provides a simple CLI that
handles text input from either files or file streams.
Design programs to be connected to other programs - each tool can be easily connected to other tools to create
incredibly powerful compositions.
Following these philosophies has made the simple unix customCommand line tools some of the most widely used software
engineering tools - allowing us to create very complex text data processing pipelines from simple customCommand line
tools. There’s even a Coursera course on Linux and Bash for Data Engineering.

## How to run

We can use the following command which would start reading the user input
`mvn clean compile exec:java`

## Expected Output

`ccwc -c test.txt`
335182 test.txt

`ccwc -l test.txt`
7188 test.txt

`ccwc -w test.txt`
58163 test.txt

`ccwc -m test.txt`
332286 test.txt

To pass in the text via the standard input, we could use the pipe `|` separator to pass the result of the first command
to `ccwc` utility.

`cat test.txt | ccwc -m`
Characters: 325098

## Solution Design

The design uses simple factory pattern to fetch the validators and executors. The design is open for extension, and we
can add support for many commands by adding its custom validator and custom executor.

There are two possible modes when we are running the program - Single Command Mode for (`ccwc -m file_name`) or Pipe
Command Mode which receives input from the standard input and passes it to the `ccwc` utility
i.e `cat test.txt | ccwc -m`.

## Tech Debt

1. Only one level of piped command processing is supported in current implementation. Multiple level piped commands are
   not supported. For example `cat test.txt | grep -i "hello" | ccwc -l` contains two levels `cat test.txt`
   and `grep -i "hello"`. So the current implementation does not support more than one level of parsing.

2. The base path for fetching the files is hardcoded. It needs to be removed to support reading files from anywhere.

3. Only the console printer which prints to standard output is implemented. Additional support for printing messages to
   logs via `LogPrinter` should be added.

4. There is no dependency injection library used, we need to add it.

5. More unit test should be added, pitest should be introduced to run mutation tests on the existing test cases to check
   their strength.
