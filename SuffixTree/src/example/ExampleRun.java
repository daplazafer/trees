package example;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;

import suffixtree.GraphvizExporter;
import suffixtree.SuffixTree;

/**
 * Clase de prueba para exportar un árbol
 * 
 * @author dpf
 *
 */
public class ExampleRun {

	// Archivo para exportar el arbol en formato Grapviz
	private static final String DEFAULT_OUTPUT_FILE = "C:\\Users\\dpf\\Desktop\\output.gv";
	private static final String OUTPUT_FILE = "output.gv";

	// Cadenas de entrada para construir el suffix tree
	private static final String[] DEFAULT_INPUT_TEXT = {"BABA","ABAB"};

	public static void main(String[] args) {		

		String output_file;
		String[] input_text;
		
		if(args.length == 0){
			output_file = DEFAULT_OUTPUT_FILE;
			input_text = DEFAULT_INPUT_TEXT;
		}else{
			output_file = OUTPUT_FILE;
			input_text = args;
		}

		// Se crea el arbol a partir de texto
		SuffixTree st = new SuffixTree(input_text);
		System.out.println("SuffixTree generado a partir de las cadenas " + Arrays.asList(input_text));

		// GraphvizExporter para exportar el suffix tree a un fichero
		GraphvizExporter stp = new GraphvizExporter(st);
		try {
			// Se escribe en el fichero fichero
			stp.print(new PrintStream(output_file));
			System.out.println("Guardado en el fichero \"" + output_file + "\". Importar este archivo a Graphviz.");
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: No se pudo escribir en el fichero \"" + output_file + "\".");
			System.exit(-1);
		}

	}

}
