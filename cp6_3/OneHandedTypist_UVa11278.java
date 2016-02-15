package cp6_3;
import java.util.*;
import java.io.*;

public class OneHandedTypist_UVa11278 {

	static HashMap<Character, Character> keyboard;
	
	public static void map()
	{
		keyboard = new HashMap<Character,Character>();
		keyboard.put('4', 'q');keyboard.put('q', '4');keyboard.put('a', '7');
		keyboard.put('5', 'j');keyboard.put('w', '5');keyboard.put('s', '8');
		keyboard.put('6', 'l');keyboard.put('e', '6');keyboard.put('d', '9');
		keyboard.put('7', 'm');keyboard.put('r', '.');keyboard.put('f', 'a');
		keyboard.put('8', 'f');keyboard.put('t', 'o');keyboard.put('g', 'e');
		keyboard.put('9', 'p');keyboard.put('y', 'r');keyboard.put('h', 'h');
		keyboard.put('0', '/');keyboard.put('u', 's');keyboard.put('j', 't');
		keyboard.put('-', '[');keyboard.put('i', 'u');keyboard.put('k', 'd');
		keyboard.put('=', ']');keyboard.put('o', 'y');keyboard.put('l', 'c');
		keyboard.put('z', '0');keyboard.put('p', 'b');keyboard.put(';', 'k');
		keyboard.put('x', 'z');keyboard.put('[', ';');keyboard.put('\'', '-');
		keyboard.put('c', 'x');keyboard.put(']', '=');keyboard.put('Q', '$');
		keyboard.put('v', ',');keyboard.put('$', 'Q');keyboard.put('W', '%');
		keyboard.put('b', 'i');keyboard.put('&', 'M');keyboard.put('E', '^');
		keyboard.put('n', 'n');keyboard.put('%', 'J');keyboard.put('R', '>');
		keyboard.put('m', 'w');keyboard.put('^', 'L');keyboard.put('T', 'O');
		keyboard.put(',', 'v');keyboard.put('*', 'F');keyboard.put('Y', 'R');
		keyboard.put('.', 'g');keyboard.put('(', 'P');keyboard.put('U', 'S');
		keyboard.put('/', '\'');keyboard.put(')', '?');keyboard.put('I', 'U');
		keyboard.put('A', '&');keyboard.put('_', '{');keyboard.put('O', 'Y');
		keyboard.put('S', '*');keyboard.put('+', '}');keyboard.put('P', 'B');
		keyboard.put('D', '(');keyboard.put('H', 'H');keyboard.put('{', ':');
		keyboard.put('F', 'A');keyboard.put('J', 'T');keyboard.put('}', '+');
		keyboard.put('G', 'E');keyboard.put('K', 'D');keyboard.put('L', 'C');
		keyboard.put(':', 'K');keyboard.put('Z', ')');keyboard.put('C', 'X');
		keyboard.put('\"', '_');keyboard.put('X', 'Z');keyboard.put('V', '<');
		keyboard.put('B', 'I');keyboard.put('N', 'N');keyboard.put('M', 'W');
		keyboard.put('<', 'V');keyboard.put('>', 'G');keyboard.put('?', '\"');
	}
	
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		map();
		
		
		while(br.ready())
		{
			String line = br.readLine();
			for(int i =0, size = line.length(); i < size; i++)
			{
				char c = line.charAt(i);
				if(keyboard.containsKey(c))
					sb.append(keyboard.get(c));
				else
					sb.append(c);
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
		
	}
}
