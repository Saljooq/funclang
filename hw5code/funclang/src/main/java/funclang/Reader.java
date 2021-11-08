package funclang;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import funclang.AST.Program;
import funclang.parser.FuncLangLexer;
import funclang.parser.FuncLangParser;

public class Reader {

	bashemulator bash;

	public Reader (bashemulator bash){
		super();
		this.bash = bash;
	}
	
	List<Program> read() throws IOException {
		List<String> programText = readNextProgram();
		List<AST.Program> result = new ArrayList<>();
		for (int i = 0; i < programText.size(); i++){
			result.add(parse(programText.get(i)));
		}
		return result;
	}

	Program parse(String programText) {
		FuncLangLexer l = new FuncLangLexer(new org.antlr.v4.runtime.ANTLRInputStream(programText));
		FuncLangParser p = new FuncLangParser(new org.antlr.v4.runtime.CommonTokenStream(l));
		Program program = p.program().ast;
		return program;
	}
	
	static String readFile(String fileName) throws IOException {
		try (BufferedReader br = new BufferedReader(
				new FileReader(fileName))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				//sb.append(System.lineSeparator());
				sb.append("  ");
				line = br.readLine();
			}



			return sb.toString();
		}
	}


	static List<String> readFile(String fileName, Boolean b) throws IOException {
		try (BufferedReader br = new BufferedReader(
				new FileReader(fileName))) {
			StringBuilder sb = new StringBuilder();

			ArrayList<String> result = new ArrayList<String>();



			String programText = "";

			int counter = 0;
			Boolean keepGoing = true;



				while (true) {

					keepGoing = true;
					String current = "";

					while (keepGoing) {
						current = br.readLine();

						if (current==null || !current.startsWith("#")){
							keepGoing = false;
						}
					}

					//System.out.println(current);

					if (current==null){
						//System.out.println("Finished reading");
						return result;
					}

					sb.append(current);
					sb.append(" ");


					for (int i = 0; i < current.length(); i++) {
						if (current.charAt(i) == '(') {
							counter++;
						} else if (current.charAt(i) == ')') {
							counter--;

						}
					}

					if (counter == 0) {
						//keepGoing = false;
						result.add(sb.toString());
						sb = new StringBuilder();
					}

				}


		}
	}
	
	private List<String> readNextProgram() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int counter = 0;
		System.out.print("$ ");
		String programText = "";

		Boolean keepGoing = true;


		while (keepGoing) {
			String current = br.readLine();
			programText += current;


			for (int i = 0; i < current.length(); i++){
				if (current.charAt(i)=='('){
					counter++;
				}
				else if (current.charAt(i)==')'){
					counter--;

				}
			}

			if (counter == 0){
				keepGoing = false;
			}

		}

		List<String> programTextList = new ArrayList<>();
		programTextList.add(programText);

		try {


			String[] splitText = programText.split(" ");
			if (splitText.length >= 1 && splitText[0].equals("!read")) {
				if (splitText.length > 1)
					programTextList = readFile("funclang\\src\\main\\java\\funclang\\playground\\" + splitText[1], true);
				else
					programTextList = readFile("funclang\\src\\main\\java\\funclang\\playground\\playground.scm", true);
			}
		} catch (IOException e)
		{
			System.out.println("Problem finding file. Error: "+e);
		}
		//bash.addToHistory(programText);

		List<String> result = new ArrayList<>();
		for (int i = 0; i < programTextList.size(); i++){
			//bash.history.add(programTextList.get(i).replaceAll("\t", " "));
			bash.addToHistory(programTextList.get(i));
			result.add(runFile(programTextList.get(i)));
		}
		return result;

	}
	
	private String runFile(String programText) throws IOException {
		if(programText.startsWith("run ")){
			programText = readFile("funclang/src/main/java/funclang/examples/" + programText.substring(4));
		}
		return programText; 
	}
}
