package v106;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ILoveStrings_UVa10679 {

    public static void main(String[] args) throws IOException {
    
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        int tc = sc.nextInt();
        while(tc-->0)
        {
        	char[] T = sc.next().toCharArray();
        	int Q = sc.nextInt();
        	AhoCorasick aho = new AhoCorasick((int)1e6 + 1);
        	char[][] subs = new char[Q][];
        	for(int i = 0; i < Q; ++i)
        		aho.addString(subs[i] = sc.next().toCharArray());
        	aho.dfs(T);
        	for(int i = 0; i < Q; ++i)
        		out.println(aho.good(subs[i]) ? "y" : "n");
        }
        out.flush();
        out.close();
    }
    
    static int getChar(char c)
    {
    	if(c >= 'a' && c <= 'z')
    		return c - 'a';
    	return 26 + c - 'A';
    }
    
    static class AhoCorasick
    {

    	static final int k = 52;
    	
    	static class Node
    	{
    		int p, c, link = -1;
    		boolean leaf, good;
    		
    		Node(int a, int b) { p = a; c = b; }
    		
    		int[] next = new int[k], go = new int[k];
    		{
    			Arrays.fill(next, -1);
    			Arrays.fill(go, -1);
    		}
    	}
    	
    	Node[] nodes;
    	int nodeCount;
    	
    	AhoCorasick(int maxNodes)
    	{
    		nodes = new Node[maxNodes];
    		nodes[nodeCount++] = new Node(0, -1);
    	}
    	
    	void addString(char[] s)
    	{
    		int cur = 0;
    		for(char ch: s)
    		{
    			int c = getChar(ch);
    			if(nodes[cur].next[c] == -1)
    			{
    				nodes[nodeCount] = new Node(cur, c);
    				nodes[cur].next[c] = nodeCount++;
    			}
    			cur = nodes[cur].next[c];
    		}
    		nodes[cur].leaf = true;
    	}
    	
    	int link(int vIdx)
    	{
    		Node v = nodes[vIdx];
    		if(v.link == -1)
    			v.link = v.p == 0 ? 0 : go(link(v.p), v.c);
    		return v.link;
    	}
    	
    	int go(int vIdx, int c)
    	{
    		Node v = nodes[vIdx];
    		if(v.go[c] == -1)
    			v.go[c] = v.next[c] != -1 ? v.next[c] : vIdx == 0 ? 0 : go(link(vIdx), c);
    		return v.go[c];
    	}
    	
    	void dfs(char[] s)
    	{
    		int cur = 0;
    		for(char c: s)
    		{
    			cur = go(cur, getChar(c));
    			nodes[cur].good = true;
    		}
    		for(int i = 1; i < nodeCount; ++i)
	    		if(nodes[i].good)
	    		{
	    			cur = link(i);
	    			while(!nodes[cur].good)
	    			{
	    				nodes[cur].good = true;
	    				cur = link(cur);
	    			}
	    		}
    	}
    	
    	boolean good(char[] s)
    	{
    		int cur = 0;
    		for(char c: s)
    			cur = go(cur, getChar(c));
    		return nodes[cur].good;
    	}
    }

    static class Scanner 
    {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s){  br = new BufferedReader(new InputStreamReader(s));}

        public String next() throws IOException 
        {
            while (st == null || !st.hasMoreTokens()) 
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
        
        public int nextInt() throws IOException {return Integer.parseInt(next());}

        public long nextLong() throws IOException {return Long.parseLong(next());}

        public String nextLine() throws IOException {return br.readLine();}

        public boolean ready() throws IOException {return br.ready(); }


    }
    
}