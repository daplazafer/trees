package suffixtree;

/**
 * Representa un �rbol de Sufijos. Permite construir el �rbol a partir de un
 * set de cadenas y realizar b�squedas.
 * 
 * @author Daniel Plaza Fern�ndez
 *
 */
public class SuffixTree {

	// S�mbolo que marca el final de un sufijo
	public final static String TERMINAL = "$";

	// - ATRIBUTOS -
	
	// Nodo ra�z del �rbol de sufijos
	private Node root;
	
	// Indica si el �rbol es generalizado
	private boolean generalized;

	// Tama�o inicial de la cadena se usa para construir el �rbol
	private int initialSize;

	// Tama�o actual de la cadena se usa para construir el �rbol
	private int currentSize;
	
	// Contador de palabras
	private int wordCounter = 0;

	// - CONSTRUCTORES

	/**
	 * Construye un SuffixTree a partir de una serie cadenas dada
	 * 
	 * @param inputStrings
	 *            Cadenas para generar el �rbol
	 */
	public SuffixTree(String... inputStrings) {
		// Inicializa el nodo ra�z
		this.root = new Node();
		
		if(inputStrings.length>1){
			generalized = true;
		}else{
			generalized = false;
		}
		
		// Contador de palabras a 0
		wordCounter = 0;
		
		for(String word: inputStrings){
			// A�ade el caracter de escape a la cadena
			word += TERMINAL;
			if(generalized)
				word += wordCounter;
			// Da valores a los tama�os
			initialSize = word.length();
			currentSize = initialSize;
			// Construye el �rbol con la cadena dada desde el nodo inicial
			build(root, word);	
			wordCounter++;
		}

		// ATENCION: El siguiente paso es opcional, algunas implementaciones consideran
		// el input vac�o como sufijo de cualquier cadena y otras no
		
		// Borra la arista a TERMINAL del primer nodo
		for (int i = 0; i < root.getChildren().size(); i++) {
			if (root.getChildren().get(i).getSuffix().startsWith(TERMINAL)) {
				root.getChildren().remove(i);
			}
		}

		// Realiza un merge desde el nodo incial
		mergeTree(root);

	}

	// - GETTERS/SETTERS -
	
	/**
	 * Indica si el �rbol es un suffix tree o un generalized suffix tree
	 * 
	 * @return true si se trata de un generalized suffix tree
	 */
	public boolean isGeneralized(){
		return generalized;
	}

	// Getter necesario para exportar el �rbol
	Node getRoot() {
		return root;
	}

	// - METODOS -

	/**
	 * Construye el �rbol con la cadena dada a partir del nodo dado
	 * 
	 * @param currentNode
	 *            Nodo a partir del cual se a�ade la cadena
	 * @param inputString
	 *            Cadena para ser a�adida
	 */
	private void build(Node currentNode, String inputString) {
		// Coge el primer caracter de la cadena en first
		boolean isTerminal;
		String first;
		if(inputString.startsWith(TERMINAL)){
			first = inputString;
			isTerminal = true;
		}else{
			first = inputString.charAt(0) + "";
			isTerminal = false;
		} 
		// Si el nodo no tiene un hijo con el caracter first se lo crea
		if (currentNode.hasChildren(first) == null)
			currentNode.addChildren(first, initialSize - currentSize, wordCounter);
		// Si la cadena a�n tiene m�s caracteres
		if (!isTerminal) {
			// A�ade el resto de la cadena al nodo hijo con el valor de first
			build(currentNode.hasChildren(first), inputString.substring(1));
			// A�ade el resto de la cadena a la ra�z
			currentSize = inputString.length() - 1;
			build(root, inputString.substring(1));
		}
	}

	/**
	 * Agrupa los nodos de un mismo camino que solo tienen un hijo. Entonces el
	 * camino: A -> B -> C -> $ Resultar�a de la forma: ABC$
	 * 
	 * @param currentNode
	 *            Nodo a partir del cual se realiza el merge
	 */
	private void mergeTree(Node currentNode) {
		// Para cada hijo de ini en c
		for (Node c : currentNode.getChildren()) {
			// Si c solo tiene solo un hijo
			if (c.getChildren().size() == 1) {
				// Agrupa c con su hijo
				c.setSuffix(c.getSuffix() + c.getChildren().get(0).getSuffix());
				c.setChildren(c.getChildren().get(0).getChildren());
				// Vuelve a hacer el merge desde ini
				mergeTree(currentNode);
			}
			// Hace el merge desde c
			mergeTree(c);
		}
	}

	/**
	 * Busca si el sufijo est� en el arbol
	 * 
	 * @param suffix
	 *            Sufijo a buscar
	 * @return indice donde empieza el sufijo en la cadena, -1 en caso de que no
	 *         est�
	 */
	public int search(String suffix) {
		// Llama al m�todo recursivo search desde el nodo ra�z y a�adiendo al
		// sufijo el s�mbolo terminal
		return search(root, suffix + TERMINAL);
	}
	
	private int search(Node currentNode, String suffix) {
		// n = Nodo inicial
		Node n = currentNode;
		// Para cada prefijo de la cadena
		for (int i = suffix.length(); i > 0; i--) {
			n = null;
			for(Node scn: currentNode.getChildren()){
				String fsuffix;
				if(scn.getSuffix().contains(TERMINAL)){
					fsuffix = scn.getSuffix().substring(0,scn.getSuffix().indexOf(TERMINAL)+1);
				}else{
					fsuffix = scn.getSuffix();
				}
				if(suffix.substring(0, i).equals(fsuffix)){
					n = scn;
					break;
				}
			}
			//n = currentNode.hasChildren(suffix.substring(0, i));
			// Si n tiene un hijo con el ese prefijo
			if (n != null) {
				// Si es hoja el suffijo est� en el arbol
				if (n.isLeaf())
					return n.getStart();
				// Si no seguimos profundizando con lo que queda de cadena
				return search(n, suffix.substring(i, suffix.length()));
			}
		}
		// Si llega aqu� es que no hay camino hasta un s�mbolo terminal
		return -1;
	}
	
}
