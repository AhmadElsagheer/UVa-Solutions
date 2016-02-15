<<<<<<< HEAD
package cp2_2;
	
import java.io.*; 
import java.util.*;
	
public class Borrowers{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s;
		ArrayList<Book> books = new ArrayList<Book>();
		while(!(s=br.readLine()).equals("END"))
		{
			int index = s.indexOf("by");
			String title = s.substring(0, index-1); String author = s.substring(index+3);
			Book cur = new Book(title, author);
			books.add(cur);
		}
		Collections.sort(books);
		ArrayList<Book> stock = new ArrayList<Book>();
		ArrayList<Book> out = new ArrayList<Book>();
		while(!(s=br.readLine()).equals("END"))
		{
			
			if(s.charAt(0)=='B')
			{
				String curBook = s.substring(7);
				int index = 0;
				while(true)
				{
					if(books.get(index).title.equals(curBook))
						break;
					index++;
				}
				out.add(books.remove(index));
			}
			else
				if(s.charAt(0)=='R')
				{
					String curBook = s.substring(7);
					int index = 0;
					while(true)
					{
						if(out.get(index).title.equals(curBook))
							break;
						index++;
					}
					stock.add(out.remove(index));
				}
				else
				{
					Collections.sort(stock);
					while(!stock.isEmpty())
					{
						Book cur = stock.remove(0);
						int index = 0;
						while(index<books.size()&&books.get(index).compareTo(cur)<=0)
							index++;
						if(index==0)
							sb.append("Put "+cur.title+" first\n");
						else
							sb.append("Put "+cur.title+" after "+books.get(index-1).title+"\n");
						books.add(index, cur);
						
						
					}
					sb.append("END\n");
				}
			
		}
		
		System.out.print(sb);
		
	}
	
	
private static class Book implements Comparable<Book>{
	
	String title;
	String  author;
	public Book(String title, String author)
	{
		this.title = title;
		this.author = author;
	}
	
	public int compareTo(Book other)
	{
		int firstTry = author.compareTo(other.author);
		if(firstTry==0)
			return title.compareTo(other.title);
		return firstTry;
	}
}
	
	

=======
package cp2_2;
	
import java.io.*; 
import java.util.*;
	
public class Borrowers{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s;
		ArrayList<Book> books = new ArrayList<Book>();
		while(!(s=br.readLine()).equals("END"))
		{
			int index = s.indexOf("by");
			String title = s.substring(0, index-1); String author = s.substring(index+3);
			Book cur = new Book(title, author);
			books.add(cur);
		}
		Collections.sort(books);
		ArrayList<Book> stock = new ArrayList<Book>();
		ArrayList<Book> out = new ArrayList<Book>();
		while(!(s=br.readLine()).equals("END"))
		{
			
			if(s.charAt(0)=='B')
			{
				String curBook = s.substring(7);
				int index = 0;
				while(true)
				{
					if(books.get(index).title.equals(curBook))
						break;
					index++;
				}
				out.add(books.remove(index));
			}
			else
				if(s.charAt(0)=='R')
				{
					String curBook = s.substring(7);
					int index = 0;
					while(true)
					{
						if(out.get(index).title.equals(curBook))
							break;
						index++;
					}
					stock.add(out.remove(index));
				}
				else
				{
					Collections.sort(stock);
					while(!stock.isEmpty())
					{
						Book cur = stock.remove(0);
						int index = 0;
						while(index<books.size()&&books.get(index).compareTo(cur)<=0)
							index++;
						if(index==0)
							sb.append("Put "+cur.title+" first\n");
						else
							sb.append("Put "+cur.title+" after "+books.get(index-1).title+"\n");
						books.add(index, cur);
						
						
					}
					sb.append("END\n");
				}
			
		}
		
		System.out.print(sb);
		
	}
	
	
private static class Book implements Comparable<Book>{
	
	String title;
	String  author;
	public Book(String title, String author)
	{
		this.title = title;
		this.author = author;
	}
	
	public int compareTo(Book other)
	{
		int firstTry = author.compareTo(other.author);
		if(firstTry==0)
			return title.compareTo(other.title);
		return firstTry;
	}
}
	
	

>>>>>>> 39f869b7a0b14772c795150d9bb52127d820fece
}	