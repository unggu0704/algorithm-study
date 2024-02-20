import java.util.*;

class color_blind{
	Scanner s = new Scanner(System.in);
	pixel[][] picture;
	int n;
	int normal , blind = 0;
	String str;
	color_blind(){
		n = s.nextInt();
		picture = new pixel[n][n];
		for(int i =0; i< n; i++) {
			str = s.next();
			for(int j =0; j<n; j++) {
				pixel p = new pixel(str.substring(j,j+1));
				picture[i][j] = p;
			}
		}
		normal = init_dfs();
		change_red();

		blind = init_dfs();
		
		System.out.println(normal + " " + blind);
	}
	
	int init_dfs() {
		int count = 0;
		for(int i =0; i< n; i++) {
			for(int j =0; j<n; j++) {
				if(!picture[i][j].visited) {
					dfs(picture[i][j],i,j);
					count++;
				//	System.out.println("dfs 1회 탈출 ");
				}
			}
		}
		return count;
	}
	void dfs(pixel p , int x, int y) {
//		System.out.println("dfs call" + " color : " +  p.color + " |  x: " + x + " y: " + y);
		p.visited = true;
		if(x != 0 && picture[x-1][y].color.equals(p.color) && !picture[x-1][y].visited) {
			dfs(picture[x-1][y],x-1,y);
		}	
		if(y != 0 && picture[x][y-1].color.equals(p.color) && !picture[x][y-1].visited) {
			dfs(picture[x][y-1],x,y-1);
		}
		if(x != n-1 && picture[x+1][y].color.equals(p.color) && !picture[x+1][y].visited) {
			dfs(picture[x+1][y],x+1,y);
		}
		if( y != n-1 && picture[x][y+1].color.equals(p.color) && !picture[x][y+1].visited) {
			dfs(picture[x][y+1],x,y+1);
		}
		
	}
	
	void change_red() {
		for(int i =0; i< n; i++) {
			for(int j =0; j<n; j++) {
				picture[i][j].visited = false;
				if(picture[i][j].color.equals("R")) {
					picture[i][j].color = "G";
				}
			}
		}
	}
	
	void print() {
		for(int i =0; i< n; i++) {
			for(int j =0; j<n; j++) {
				System.out.print(picture[i][j].color);
			}
			System.out.println();
		}
	}
	
}

class pixel{
	String color;
	boolean visited;
	
	pixel(String  s){
		color = s;
		visited = false;
	}
	
	
}



	

	

public class Main {
	public static void main(String args[]) {
		long beforeTime = System.currentTimeMillis();
		color_blind c = new color_blind();
		//time(beforeTime);
	}
	
	public static void time(long beforeTime) {
		long afterTime = System.currentTimeMillis(); 
		long secDiffTime = (afterTime - beforeTime);
		System.out.println("\n" + secDiffTime + "ms");

	}

}	

