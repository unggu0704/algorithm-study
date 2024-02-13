import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int count = 0;
        
		while (n > 2) {		
			if (n % 5 != 0) { // 5배수가 아니라면
				n -= 3;
				count++;
                
				if(n < 5 && n % 3 != 0) {
					count = -1;
					n=count;
				}
			} else { // 5배수라면							
				count += n / 5;
				n=0;				
			}
		}
        
        
		System.out.println(count);
	}
}