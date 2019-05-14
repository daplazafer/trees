package suffixtree;

import java.util.ArrayList;
import java.util.List;

/**
 * Nodo de un Árbol de Sufijos
 * 
 * @author Daniel Plaza Fernández
 *
 */
class Node {

	// - ATRIBUTOS -
	
	// Palabra asociada al sufijo
	private int word;

	// Sufijo del nodo
	private String suffix;
	
	// Donde empieza el sufijo
	private int start;

	// Hijos del nodo
	private List<Node> children;

	// - CONSTRUCTORES

	public Node(String suffix, int start, int word) {
		children = new ArrayList<>();
		this.suffix = suffix;
		this.start = start;
		this.word = word;
	}

	public Node() {
		this("",-1,-1);
	}

	// - GETTERS/SETTERS -
	
	public int getWord() {
		return word;
	}

	public void setWord(int word) {
		this.word = word;
	}
	
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	// - METODOS -

	/**
	 * Añade un Nodo a la lista del hijos
	 * 
	 * @param newNode
	 *            Sufijo para el nuevo Nodo
	 */
	public void addChildren(String newNode, int start, int word) {
		children.add(new Node(newNode, start, word));
	}

	/**
	 * Busca entre los hijos un Nodo con el sufijo dado
	 * 
	 * @param next
	 *            Sufijo a buscar
	 * @return El Nodo hijo con el sufijo next o null si no existe
	 */
	public Node hasChildren(String next) {
		for (Node c : children){
			if (next.equals(c.suffix))
				return c;
		}
		return null;
	}

	/**
	 * Comprueba si este nodo es un nodo hoja
	 * 
	 * @return true Si es hoja
	 */
	public boolean isLeaf() {
		return children.size() == 0;
	}

}
