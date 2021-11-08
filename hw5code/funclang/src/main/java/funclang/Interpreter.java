package funclang;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import funclang.AST.*;



/**
 * This main class implements the Read-Eval-Print-Loop of the interpreter with
 * the help of Reader, Evaluator, and Printer classes. 
 * 
 * @author hridesh
 *
 */
public class Interpreter {
	public static void main(String[] args) throws AWTException {
		System.out.println("Type a program to evaluate and press the enter key," +
							" e.g. ((lambda (av bv cv) (let ((a av) (b bv) (c cv) (d 279) (e 277)) (+ (* a b) (/ c (- d e))))) 3 100 84) \n" +
							"Press Ctrl + C to exit.");

		bashemulator bash = new bashemulator();
		Reader reader = new Reader(bash);
		Evaluator eval = new Evaluator(reader);
		Printer printer = new Printer();
		RobotKeyboard keyboard = new RobotKeyboard();
		System.out.println("Working Directory = " + System.getProperty("user.dir"));

		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new GlobalKeyListener(bash, keyboard));
		bash.history.add("");

		REPL: while (true) { // Read-Eval-Print-Loop (also known as REPL)
			java.util.List<Program> p = new ArrayList<>();
			try {

					p = reader.read();
					bash.resetCounter();

					for (int i = 0; i < p.size(); i++) {
						Program c=p.get(i);
						if (c._e == null) continue REPL;
						Value val = eval.valueOf(c);
						printer.print(val);
					}
			} catch (Env.LookupException e) {
				printer.print(e);
			} catch (IOException e) {
				System.out.println("Error reading input:" + e.getMessage());
			} catch (NullPointerException e) {
				System.out.println("Error:" + e.getMessage());
			}
		}
	}


}
