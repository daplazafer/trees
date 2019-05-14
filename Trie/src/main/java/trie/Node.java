package trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Nodo de un �rbol de Sufijos
 * 
 * @author Daniel Plaza Fern�ndez
 *
 */
class Node {

	// - ATRIBUTOS -

	// Prefijo del nodo
	private String prefix;

	// Hijos del nodo
	private List<Node> children;

	// - CONSTRUCTORES

	public Node(String prefix) {
		children = new ArrayList<>();
		this.prefix = prefix;
	}

	public Node() {
		this("");
	}

	// - GETTERS/SETTERS -

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	// - METODOS -

	/**
	 * Añade un Nodo a la lista del hijos
	 * 
	 * @param newNode
	 *            Sufijo para el nuevo Nodo
	 */
	public void addChildren(String newNode) {
		children.add(new Node(newNode));
	}

	/**
	 * Busca entre los hijos un Nodo con el sufijo dado
	 * 
	 * @param next
	 *            Prefijo a buscar
	 * @return El Nodo hijo con el sufijo next o null si no existe
	 */
	public Node hasChildren(String next) {
		for (Node c : children){
			if (next.equals(c.prefix))
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
