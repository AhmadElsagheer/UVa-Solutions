//package v102;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.util.StringTokenizer;
//
//
//public class CommonPermutation﻿_UVa10252 {
//
//	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
//		PrintWriter out = new PrintWriter(System.out);
//
//		while(sc.ready())
//		{
//			String s = sc.nextLine(), t = sc.nextLine();
//			int[] fs = new int[26], ft = new int[26];
//			for(int i = 0; i < s.length(); ++i)
//				fs[s.charAt(i) - 'a']++;
//
//			for(int i = 0; i < t.length(); ++i)
//				ft[t.charAt(i) - 'a']++;
//
//			StringBuilder ans = new StringBuilder();
//			for(int i = 0; i < 26; ++i)
//			{
//				int cnt = Math.min(fs[i], ft[i]);
//				while(cnt-->0)
//					ans.append((char)(i + 'a'));
//			}
//			out.println(ans);
//		}
//		out.flush();
//		out.close();
//	}
//
//	static class Scanner
//	{
//		StringTokenizer st;
//		BufferedReader br;
//
//		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
//
//		public String next() throws IOException
//		{
//			while (st == null || !st.hasMoreTokens())
//				st = new StringTokenizer(br.readLine());
//			return st.nextToken();
//		}
//
//		public int nextInt() throws IOException {return Integer.parseInt(next());}
//
//		public long nextLong() throws IOException {return Long.parseLong(next());}
//
//		public String nextLine() throws IOException {return br.readLine();}
//
//		public double nextDouble() throws IOException { return Double.parseDouble(next()); }
//
//		public boolean ready() throws IOException {return br.ready();}
//	}
//}