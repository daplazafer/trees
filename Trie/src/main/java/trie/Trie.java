package trie;

/**
 * Representa un árbol de prefijos. Permite construir el árbol a partir de un
 * set de cadenas y realizar búsquedas.
 * 
 * @author Daniel Plaza Fernández
 *
 */
public class Trie {

	// Símbolo que marca el final de un prefijo
	public final static String TERMINAL = "#";

	// - ATRIBUTOS -
	
	// Nodo raíz del árbol de prefijos
	private Node root;
	
	// Contador de palabras
	private int wordCounter;

	// - CONSTRUCTORES

	/**
	 * Construye un Trie a partir de una serie cadenas dada
	 * 
	 * @param inputStrings
	 *            Cadenas para generar el árbol
	 */
	public Trie(String... inputStrings) {
		// Inicializa el nodo raíz
		this.root = new Node();
		
		// Contador de palabras a 0
		wordCounter = 0;
		
		for(String word: inputStrings){
			// Añade el caracter de escape a la cadena
			word += TERMINAL + wordCounter++;
			// Construye el árbol con la cadena dada desde el nodo inicial
			build(root, word);
		}

		// ATENCION: El siguiente paso es opcional, algunas implementaciones consideran
		// el input vacío como sufijo de cualquier cadena y otras no
		
		// Borra la arista a TERMINAL del primer nodo
		for (int i = 0; i < root.getChildren().size(); i++) {
			if (root.getChildren().get(i).getPrefix().startsWith(TERMINAL)) {
				root.getChildren().remove(i);
			}
		}

	}

	// - GETTERS/SETTERS -

	// Getter necesario para exportar el árbol
	Node getRoot() {
		return root;
	}

	// - METODOS -

	/**
	 * Construye el árbol con la cadena dada a partir del nodo dado
	 * 
	 * @param currentNode
	 *            Nodo a partir del cual se añade la cadena
	 * @param inputString
	 *            Cadena para ser añadida
	 */
	private void build(Node currentNode, String inputString) {
		// Coge el primer caracter de la cadena en first
		boolean isTerminal;
		String first ;
		if(inputString.startsWith(TERMINAL)){
			first = inputString;
			isTerminal = true;
		}else{
			first = inputString.charAt(0) + "";
			isTerminal = false;
		} 
		// Si el nodo no tiene un hijo con el caracter first se lo crea
		if (currentNode.hasChildren(first) == null)
			currentNode.addChildren(first);
		// Si la cadena aún tiene más caracteres
		if (!isTerminal) {
			// Añade el resto de la cadena al nodo hijo con el valor de first
			build(currentNode.hasChildren(first), inputString.substring(1));
		}
	}

	/**
	 * Busca si el prefijo está en el arbol
	 * 
	 * @param prefix
	 *            prefijo a buscar
	 * @return  true si el prefijo está
	 */
	public boolean search(String prefix) {
		Node currentNode = root;
		char[] chars = prefix.toCharArray();
		for(char c: chars){
			currentNode = currentNode.hasChildren(c + "");
			if(currentNode == null){
				return false;
			}
		}
		return true;
	}
	
}
