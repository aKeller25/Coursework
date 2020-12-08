package graph;

/*Name: Alexander Keller
 * Class: CMSC132
 * TA: Qian (10 am)
 * Section: 303
 * Time last edited: 5/3/2015
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;

/**
 * This class was made to hold a graph. This class can be used to add a vertex
 * to the graph, check if a vertex is in the graph, return a collection of the
 * vertices in the graph, remove a vertex from the graph, add an edge between
 * two vertices, return the cost of an edge, change the cost of an edge, remove
 * an edge, return a collection of the neighbors of a vertex (The parameter),
 * return a collection of the vertices which have edges pointing toward the
 * parameter vertex
 */

public class Graph<V extends Comparable<V>> {
	// Make a visited set.

	private Map<V, Map<V, Integer>> graph;

	/**
	 * A constructor that makes a graph object an empty HashMap, with the keys
	 * being vertices and the values being a Map of vertices that V has an
	 * edge
	 * and their associated edge costs
	 */
	public Graph() {
		graph = new HashMap<V, Map<V, Integer>>();
	}

	/**
	 * Adds a vertex to the graph, if it isn't already in the graph, by making
	 * an empty map (that will hold the vertexes that the current vertex will
	 * have edges toward and their associated costs) and adding it to the
	 * graph along with a key (the vertex vertex)*/
	public void addVertex(V vertex) throws IllegalArgumentException {
		if (graph.containsKey(vertex)) {
			throw new IllegalArgumentException();
		}
		Map<V, Integer> vertexConnect = new HashMap<V, Integer>();
		graph.put(vertex, vertexConnect);
	}

	public boolean isVertex(V vertex) {
		return graph.containsKey(vertex);
	}

	public Collection<V> getVertices() {
		return graph.keySet();
	}

	public void removeVertex(V vertex) throws NoSuchElementException {
		if (!isVertex(vertex)) {
			throw new NoSuchElementException();
		}
		// Remove all edges to vertex's neighbors (vertex's inner map)
		graph.remove(vertex);
		// Remove all edges to vertex
		for (V vert : getVertices()) { // Iterates through all of the vertices
			// if (graph.get(vert).containsKey(vertex)){ // Should I use this?

			// If vert has an edge to the vertex to be removed,
			// remove that key-value combination
			graph.get(vert).remove(vertex);
		}
	}

	public void addEdge(V source, V dest, int cost)
			throws IllegalArgumentException {
		if (cost < 0 || graph.get(source).containsKey(dest)) {
			// If cost is negative or source already has an edge to dest
			throw new IllegalArgumentException();
		}
		if (!isVertex(source)) {
			addVertex(source);
		}
		if (!isVertex(dest)) {
			addVertex(dest);
		}
		// Puts the dest vertex and its associated cost into the vertex
		// source's vertexConnect hashMap
		graph.get(source).put(dest, cost);
	}

	public int getEdgeCost(V source, V dest) {
		// What does it mean by no edge cost?
		int cost = -1;

		if (isVertex(source) && isVertex(dest)
				&& graph.get(source).containsKey(dest)) {
			cost = graph.get(source).get(dest);
		}

		return cost;
	}

	public void changeEdgeCost(V source, V dest, int newCost)
			throws IllegalArgumentException, NoSuchElementException {
		// Not sure if this is correct
		if (newCost < 0) {
			throw new IllegalArgumentException();
		}
		if (isVertex(source) && isVertex(dest)
				&& graph.get(source).containsKey(dest)) {
			graph.get(source).replace(dest, newCost);
		} else {
			throw new NoSuchElementException();
		}
	}

	public void removeEdge(V source, V dest) throws NoSuchElementException {
		if (isVertex(source) && isVertex(dest)
				&& graph.get(source).containsKey(dest)) {
			graph.get(source).remove(dest);
		} else {
			throw new NoSuchElementException();
		}
	}

	public Collection<V> getNeighbors(V vertex) throws 
	IllegalArgumentException {
		if (!isVertex(vertex)) { // Vertexes can have self-references
			throw new IllegalArgumentException();
		}
		return graph.get(vertex).keySet();
	}

	public Collection<V> getPredecessors(V vertex)
			throws IllegalArgumentException {
		Set<V> predecessors = new HashSet<V>();
		if (!isVertex(vertex)) { // Vertexes can have self-references
			throw new IllegalArgumentException();
		}
		for (V vert : getVertices()) {
			if (graph.get(vert).containsKey(vertex)) {
				predecessors.add(vert);
			}
		}
		return predecessors;
	}

	public boolean isClique() { // Could compare getPredecessors and
								// getNeighbors
		for (V vert : getVertices()) {
			for (V otherVert : getVertices()) {
				if (graph.get(vert).containsKey(otherVert) == false) {
					return false;
				}
			}
		}
		return true;
	}

	public int dijkstra(V sourceVertex, V destVertex, List<V> shortestPath)
			throws IllegalArgumentException { // Make a visited set

		Set<V> processed = new HashSet<V>();
		shortestPath = new ArrayList<V>();
		int pathCost = -1;

		if (!isVertex(sourceVertex) || !isVertex(destVertex)) {
			throw new IllegalArgumentException();
		}

		if (sourceVertex.compareTo(destVertex) == 0) {
			pathCost = 0;
		} else {
			
			/*
			 * Predecessors[all vertices]= none MinCosts[start]= 0 and
			 * MinCosts[all other vertices]= while (there are some vertices
			 * that are not in Processed) find the vertex Min that's not in
			 * Processed that has the smallest value of MinCosts[Min] add Min
			 * to Processed for each neighbor N of Min that's not already in
			 * Processed if (MinCosts[N] > MinCosts[Min] + cost of edge Min ->
			 * N) MinCosts[N]= MinCosts[Min] + cost of edge Min -> N
			 * Predecessors[N]= Min
			 */
		}

		return pathCost;
	}

}
