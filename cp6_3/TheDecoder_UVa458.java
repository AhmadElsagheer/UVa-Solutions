package cp6_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TheDecoder_UVa458 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(br.ready())
		{
			String in = br.readLine();
			for(int i = 0; i < in.length(); i++)
				sb.append((char)(in.charAt(i)-7));
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
