package suffixtree;

import java.io.PrintStream;

/**
 * Permite exportar en formato Graphviz un Árbol de Sufijos.
 * 
 * Para más información: http://www.graphviz.org
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
	

	// Árbol para ser exportado
	private SuffixTree st;

	public GraphvizExporter(SuffixTree st) {
		this.st = st;
	}

	// Contador que se emplea para dar una id única a cada nodo del árbol
	private int index;

	private String format() {
		index = 0;
		String fstree;
		fstree = TYPE + " {\n" + "rankdir=" + RANKDIR + ";\n" + "node [label = \"" + NODE_LABEL + "\"];\n"
				+ "node [shape = " + NODE_SHAPE_CIRCLE + "];\n";
		fstree += formatNodes(st.getRoot());
		fstree += "\n}";
		return fstree;
	}

	private String formatNodes(Node n) {
		String ftree = "";
		int i = index;
		for (Node c : n.getChildren()) {
			index++;
			ftree += i + " -> " + index + " [ label = \"" + c.getSuffix() + "\" ];\n";
			if(c.isLeaf()){
				ftree += index + " [shape = " + NODE_SHAPE_RECTANGLE + "];\n";
				ftree += index + " [label = \"";
				if(st.isGeneralized())
					ftree += c.getWord()+":";
				ftree += c.getStart() + "\"];\n";
			}
			ftree += formatNodes(c);
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
