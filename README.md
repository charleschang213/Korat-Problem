# Korat Problem on Generating DAGs

## Introductions
This repository contains Java code that uses the Korat Library to test the number of DAGs which have a single source(root) with a given number of vertices.


## Current Result  
The number of graphs generated for n=0..7 are 0,1,1,4,40,104,328,18304

## Current Problem
The problem that the generated number is larger than the theoretical number might be that the program will regard outgoingEdge_i and outgoing_j as different topological relationship between vertices when $i\neq j$ (actually the two relationships should be equal)

## Libraries Used 
+ Korat 1.0
+ Alloy4viz
+ javassist
+ commons-cli
+ graphViz

