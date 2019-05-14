package main;

import static org.junit.Assert.*;

import org.junit.Test;

import suffixtree.SuffixTree;

/**
 * Pruebas unitarias para la clase SuffixTree con JUnit.
 * 
 * @author Daniel Plaza Fernández
 *
 */
public class TestSuffixTree {
	
	// Texto para construir el Árbol
	private static final String INPUT_TEXT = "BANANA";
	
	// Sufijos que SI debe contener el Árbol
	private static final String[] SEARCH_TRUE = {	
			"BANANA",
			"ANANA",
			"NANA",
			"ANA",
			"NA",
			"A",
			};
	
	// Sufijos que NO debe contener el Árbol
	private static final String[] SEARCH_FALSE = {
			"",
			"B",
			"BA",
			"BAN",
			"BANA",
			"BANAN",
			"AN",
			"N",
			"BNA",
			"BAXNA",
			"BANANANA",
			"BAANA",
			"a",
			"na",
			"ana",
			"anana",
			"banana",
			};

	@Test
	public void testSuffixTree() {
		
		// Crea el Árbol a partir del texto
		SuffixTree st = new SuffixTree(INPUT_TEXT);

		// Comprueba los sufijos que SI deben estar
		for(int i = 0 ; i<SEARCH_TRUE.length;i++){
			int value = st.search(SEARCH_TRUE[i]);
			assertTrue("\""+SEARCH_TRUE[i]+"\" devuelve un índice incorrecto "+ value+ " cuando se esperaba "+ i,value==i);
		}
		
		// Comprueba los sufijos que NO deben estar
		for(int i = 0 ; i<SEARCH_FALSE.length;i++){
			int value = st.search(SEARCH_FALSE[i]);
			assertTrue("\""+SEARCH_FALSE[i]+"\" devuelve un índice incorrecto "+ value+ " cuando se esperaba "+ -1,st.search(SEARCH_FALSE[i])==-1);
		}
		
	}

}
