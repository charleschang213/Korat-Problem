package com.example;

import korat.finitization.IArraySet;
import korat.finitization.IFinitization;
import korat.finitization.IIntSet;
import korat.finitization.IObjSet;
import korat.finitization.impl.FinitizationFactory;

import java.util.*;

public class Graph {
    Vertex root;
    ArrayList<Vertex> visited = null;
    int size;
    public static class Vertex{
        Vertex []outgoingEdges;
    }
    public boolean repOK(){
        if ((root==null)||(root.outgoingEdges==null)){
            return false;
        }
        visited = new ArrayList<>();
        visited.add(root);
        ArrayList<Vertex> recStack = new ArrayList<>();
        boolean isCyclic = isCyclicHelper(root,recStack);
        int visitSize = visited.size();
        visited.clear();
        return (!isCyclic)&&(visitSize==this.size);
    }

    private boolean isCyclicHelper(Vertex currentVertex, ArrayList<Vertex> recStack){

        if ((currentVertex==null)||(currentVertex.outgoingEdges==null)){
            return true;
        }

        recStack.add(currentVertex);


        Set<Vertex> sameChecker = new HashSet<>();
        int tmpHash = -1;
        for (int i=0;i<currentVertex.outgoingEdges.length;i++){
            Vertex adjacent = currentVertex.outgoingEdges[i];
            if (adjacent.hashCode()<tmpHash){
                return true;
            }
            tmpHash = adjacent.hashCode();
            if (sameChecker.contains(adjacent)){
                return true;
            }
            sameChecker.add(adjacent);

            if (recStack.contains(adjacent)){
                return true;
            }
            if (!(visited.contains(adjacent))){
                visited.add(adjacent);
                if (isCyclicHelper(adjacent,recStack)) {
                    return true;
                }
            }
        }

        recStack.remove(currentVertex);
        return false;
    }

    public static IFinitization finGraph(int nodeNum){
        IFinitization f = FinitizationFactory.create(Graph.class);
        IObjSet vertexSet = f.createObjSet(Vertex.class,nodeNum,false);
        f.set("root",vertexSet);
        IIntSet sizeSet = f.createIntSet(0,nodeNum-1);

        IIntSet gSizeSet = f.createIntSet(nodeNum);

        IArraySet outgoingSet = f.createArraySet(Vertex[].class,sizeSet,vertexSet,nodeNum);

        f.set("Vertex.outgoingEdges",outgoingSet);
        f.set("size",gSizeSet);
        return f;
    }
}
