package v106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class TheDecadaryWatch_UVa10683 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		while(br.ready())
		{
			String line = br.readLine();
			int hh = Integer.parseInt(line.substring(0, 2));
			int mm = Integer.parseInt(line.substring(2, 4));
			int ss = Integer.parseInt(line.substring(4, 6));
			int cc = Integer.parseInt(line.substring(6));
		
			cc =  125 * (hh*60*60*100 + mm*60*100 + ss*100 + cc) /108;
	
			hh = cc / 1000000;
			cc %= 1000000;
			mm = cc / 10000;
			cc %= 10000;
			ss = cc / 100;
			cc %= 100;
			sb.append(new DecimalFormat("0").format(hh)).append(new DecimalFormat("00").format(mm)).append(new DecimalFormat("00").format(ss)).append(new DecimalFormat("00").format(cc)).append("\n");
		}
		out.print(sb);
		out.flush();
		
	}
}
