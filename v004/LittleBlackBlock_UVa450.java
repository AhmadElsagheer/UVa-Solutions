package v004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class LittleBlackBlock_UVa450 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int D = sc.nextInt();
		ArrayList<Record> records = new ArrayList<Record>();
		while(D-->0)
		{
			String d = sc.nextLine();
			while(true)
			{
				String s = sc.nextLine();
				if(s == null || s.isEmpty())
					break;
				records.add(new Record(s, d));
			}
		}
		Collections.sort(records);
		for(Record r: records)
			out.print(r);
		out.flush();
		out.close();
	}
	
	static class Record implements Comparable<Record>
	{
		String title, fName, lName, address, dept, hPhone, wPhone, box;
		
		Record(String s, String d)
		{
			String[] in = s.split(",");
			title = in[0];
			fName = in[1];
			lName = in[2];
			address = in[3];
			hPhone = in[4];
			wPhone = in[5];
			box = in[6];
			dept = d;
		}
		
		public int compareTo(Record r) { return lName.compareTo(r.lName); }
		
		public String toString()
		{
			return String.format("----------------------------------------\n%s %s %s\n%s\nDepartment: %s\nHome Phone: %s\nWork Phone: %s\nCampus Box: %s\n", title, fName, lName, address, dept, hPhone, wPhone, box);
		}
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}