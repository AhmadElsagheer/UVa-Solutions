package v100;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
 
 
public class WhatsCryptanalysis_UVa10008 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int l = sc.nextInt();
		Freq[] f = new Freq[26];
		for(int i = 0; i < 26; ++i)
			f[i] = new Freq(i);
		while(l-->0)
		{
			String s = sc.nextLine().toUpperCase();
			for(int i = 0; i < s.length(); ++i)
			{
				int c = s.charAt(i) - 'A';
				if(c >= 0 && c < 26)
					f[c].f++;
			}
		}
		Arrays.sort(f);
		for(Freq fr: f)
			if(fr.f != 0)
				out.printf("%c %d\n", (char)(fr.i + 'A'), fr.f);
		out.flush();
		out.close();
	}
	
	static class Freq implements Comparable<Freq>
	{
		int i, f;
		
		Freq(int x) { i = x; }
		
		public int compareTo(Freq fr)
		{
			if(fr.f != f)
				return fr.f - f;
			return i - fr.i;
		}
	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;
 
		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
		
		public Scanner(FileReader s){	br = new BufferedReader(s);}
 
		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
 
		public int nextInt() throws IOException {return Integer.parseInt(next());}
 
		public long nextLong() throws IOException {return Long.parseLong(next());}
 
		public String nextLine() throws IOException {return br.readLine();}
 
		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}
 
		public boolean ready() throws IOException {return br.ready();}
 
 
	}
} 