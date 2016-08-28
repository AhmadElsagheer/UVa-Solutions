package v115;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class PrefixLookup_UVa11590 {

    public static void main(String[] args) throws IOException {
    
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        while(true)
        {
        	int N = sc.nextInt(), M = sc.nextInt();
        	if(N == 0 && M == 0)
        		break;
        	BinaryTrie bt = new BinaryTrie();
        	while(N-->0)
        		bt.put(sc.next());
        	bt.process(M);
        	int K = sc.nextInt();
        	while(K-->0)
        		out.println(bt.query(sc.next()));
        	out.println();
        }

        out.flush();
        out.close();
    }
    
    static class Node { Node left, right; boolean isEnd; BigInteger ans; } 
    
    static class BinaryTrie
    {
    	Node root = new Node(); { root.isEnd = true; }
    	
    	void put(String s)
    	{
    		int idx = 0, n = s.length() - 1;
    		Node cur = root;
    		while(idx < n)
    		{
    			Node nxt = s.charAt(idx) == '0' ? cur.left : cur.right;
    			if(nxt == null)
    				if(s.charAt(idx) == '0')
    					cur.left = nxt = new Node();
    				else
    					cur.right = nxt = new Node();
    			cur = nxt;
    			++idx;
    		}
    		cur.isEnd = true;
    	}
    	
    	void process(int M) { dfs(root, M); }
    	    	
    	BigInteger dfs(Node cur, int bits)
    	{
    		BigInteger ret = BigInteger.ZERO;
    		if(cur.left != null)
    			ret = ret.add(dfs(cur.left, bits - 1));
    		if(cur.right != null)
    			ret = ret.add(dfs(cur.right, bits - 1));
    		cur.ans = cur.isEnd ?  BigInteger.ONE.shiftLeft(bits).subtract(ret) : BigInteger.ZERO;
    		return ret.add(cur.ans);
    	}
    	
    	BigInteger query(String s)
    	{
    		int idx = 0, n = s.length() - 1;
    		Node cur = root;
    		while(idx < n)
    			cur = s.charAt(idx++) == '0' ? cur.left : cur.right;
    		return cur.ans;
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