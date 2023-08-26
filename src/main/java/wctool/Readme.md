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

`wc -c test.txt`
335182 test.txt

`wc -l test.txt`
7188 test.txt

`wc -w test.txt`
58163 test.txt

`wc -m test.txt`
332286 test.txt

