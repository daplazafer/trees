package trie;

import java.io.PrintStream;

/**
 * Permite exportar en formato Graphviz un árbol de prefijos.
 * 
 * Para más informaci�n: http://www.graphviz.org
 * 
 * @author Daniel Plaza Fernández
 *
 */
public class GraphvizExporter {

	// Tipo de grafo
	private final static String TYPE = "digraph finite_state_machine";
	
	// Etiqueta que tendrán todos los nodos
	private final static String NODE_LABEL = "";
	
	// Dirección del árbol
	private final static String RANKDIR = "TB";
	
	// Forma de los nodos
	private final static String NODE_SHAPE_RECTANGLE = "rectangle";
	private final static String NODE_SHAPE_CIRCLE = "circle";
	

	// �rbol para ser exportado
	private Trie pt;

	public GraphvizExporter(Trie pt) {
		this.pt = pt;
	}

	// Contador que se emplea para dar una id única a cada nodo del árbol
	private int index;

	private String format() {
		index = 0;
		String fstree;
		fstree = TYPE + " {\n" + "rankdir=" + RANKDIR + ";\n" + "node [label = \"" + NODE_LABEL + "\"];\n"
				+ "node [shape = " + NODE_SHAPE_CIRCLE + "];\n";
		fstree += formatNodes(pt.getRoot(), "");
		fstree += "\n}";
		return fstree;
	}

	private String formatNodes(Node n, String prefix) {
		String ftree = "";
		int i = index;
		for (Node c : n.getChildren()) {
			index++;
			if(!c.isLeaf()){
				ftree += i + " -> " + index + " [ label = \"" + c.getPrefix() + "\" ];\n";
				ftree += index + " [label = \""+ prefix + c.getPrefix() +"\"];\n";
				ftree += formatNodes(c, prefix + c.getPrefix());
			}else{
				ftree += i + " -> " + index + " [ label = \"" + c.getPrefix().charAt(0) + "\" ];\n";
				ftree += index + " [shape = " + NODE_SHAPE_RECTANGLE + "];\n";
				ftree += index + " [label = \""+c.getPrefix().substring(1) +"\"];\n";
			}
			
			/*
			index++;
			ftree += i + " -> " + index + " [ label = \"" + c.getPrefix() + "\" ];\n";
			if(c.isLeaf()){
				ftree += index + " [shape = " + NODE_SHAPE_RECTANGLE + "];\n";
				ftree += index + " [label = \""+ prefix + c.getPrefix() +"\"];\n";
			}
			ftree += formatNodes(c, prefix + c.getPrefix());
			*/
		}
		return ftree;
	}

	/**
	 * Imprime el texto formateado para ser representado con Graphviz.
	 * 
	 * @param ps
	 *            PrintStream por el cual escribir el texto
	 */
	public void print(PrintStream ps) {
		ps.println(format());
	}

}
