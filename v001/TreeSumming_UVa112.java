package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TreeSumming_UVa112 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String line = "";
	static int idx = 0;
	static Node root;
	
	static void generate(Node cur) throws IOException
	{
		while(idx>=line.length())
			prepare();
		String next = "";
		while(line.charAt(idx) == '-' || line.charAt(idx)>='0' && line.charAt(idx)<='9')
		{
			next += line.charAt(idx++);
			while(idx==line.length())
				prepare();
		}
		if(!next.isEmpty())
		{		
			Node left = new Node(Integer.parseInt(next));
			idx++;
			generate(left);
			cur.left = left;
		}
		if(cur==root)
			return;
		idx++;
		idx++;
		while(idx>=line.length())
			prepare();
		
		next = "";
		while(line.charAt(idx) == '-' || line.charAt(idx)>='0' && line.charAt(idx)<='9')
		{
			next += line.charAt(idx++);
			while(idx>=line.length())
				prepare();
		}
		if(!next.isEmpty())
		{
			Node right = new Node(Integer.parseInt(next));
			idx++;
			generate(right);
			cur.right = right;
		}
		idx++;
		while(idx>=line.length())
			prepare();
	}
	
	static void prepare() throws IOException
	{
		idx -= line.length();
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.countTokens()==0)
			st = new StringTokenizer(br.readLine());
		line = "";
		while(st.hasMoreTokens())
			line += st.nextToken();
	}
	public static void main(String[] args) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		while(br.ready())
		{
		
			StringTokenizer st = new StringTokenizer(br.readLine());
			while(st.countTokens()==0)
				st = new StringTokenizer(br.readLine());
			int val = Integer.parseInt(st.nextToken());
			root = new Node(0);
			
			idx = 1;
			line = "";
			while(st.hasMoreTokens())
				line += st.nextToken();
			
			generate(root);
			if(root.left == null && root.right == null || !possible(root,0,val))
				sb.append("no\n");
			else
				sb.append("yes\n");
			
		}
		System.out.print(sb);
	}
	
	static boolean possible(Node cur, int sum, int val)
	{
	
		if(cur.left == null && cur.right == null)
			return sum + cur.val == val;
		if(cur.left != null && possible(cur.left, sum + cur.val,val))
			return true;
		
		if(cur.right != null && possible(cur.right, sum + cur.val,val))
			return true;
		return false;
	}
}

class Node
{
	int val;
	Node left, right;
	
	Node(int x)
	{
		val = x;
	}
}