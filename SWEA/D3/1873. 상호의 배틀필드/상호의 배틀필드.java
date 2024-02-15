import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static String[][] matrix;
    public static String[] direction = new String[]{"^", ">", "v", "<"};
    public static int[] dx = new int[]{-1, 0, 1, 0};
    public static int[] dy = new int[]{0, 1, 0, -1};
    public static int carX = 0;
    public static int carY = 0;

    //0부터 3까지 시계방향
    public static int carDirection = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            String[] arr = br.readLine().split(" ");

            int h = Integer.parseInt(arr[0]);
            int w = Integer.parseInt(arr[1]);

            matrix = new String[h][w];


            for(int i=0;i<h;i++){
                arr = br.readLine().split("");

                for(int j=0;j<arr.length;j++){
                    matrix[i][j] = arr[j];

                    if(arr[j].equals("^") || arr[j].equals("v") || arr[j].equals("<") || arr[j].equals(">")){
                        carX = i;
                        carY = j;

                        for(int k=0;k<direction.length;k++){
                            if(matrix[i][j].equals(direction[k])){
                                carDirection = k;
                                break;
                            }
                        }
                    }

                }

            }


            br.readLine();
            arr = br.readLine().split("");

            for (String command : arr){
                // 방향 바꾸고 이동

                if(command.equals("U")){
                    carDirection = 0;
                    carMove();
                } else if(command.equals("R")){
                    carDirection = 1;
                    carMove();
                } else if(command.equals("D")){
                    carDirection = 2;
                    carMove();
                } else if(command.equals("L")){
                    carDirection = 3;
                    carMove();
                } else if(command.equals("S")){
                    carShoot();
                }

            }

            System.out.print("#" + t + " ");

            for(int i=0;i<matrix.length;i++){
                for(int j=0;j<matrix[i].length;j++){
                    System.out.print(matrix[i][j]);
                }
                System.out.println();
            }


        }

    }

    public static void carMove(){
        int newX = carX + dx[carDirection];     int newY = carY + dy[carDirection];

        //방향 전환
        matrix[carX][carY] = direction[carDirection];

        //맵 밖으로 이동했을때
        if(newX < 0 || newY < 0 || newX >= matrix.length || newY >= matrix[0].length)
            return;

        //평지 이동
        if(matrix[newX][newY].equals(".")){
            //이동 전에 평지로
            matrix[carX][carY] = ".";
            carX = newX;
            carY = newY;
        }

        matrix[carX][carY] = direction[carDirection];

    }

    public static void carShoot(){
        //벽에 맞거나 맞지 않을때까지 쏜다.

        Queue<Integer> queue = new LinkedList<>();
        queue.add(carX);
        queue.add(carY);

        //막힐때까지 이동한다!
        while(!queue.isEmpty()){
            int newX = queue.poll() + dx[carDirection];     int newY = queue.poll() + dy[carDirection];

            if(newX >= 0 && newY >= 0 && newX < matrix.length && newY < matrix[0].length){

                if(matrix[newX][newY].equals(".") || matrix[newX][newY].equals("-")){ // 평지
                    queue.add(newX);
                    queue.add(newY);
                }
                else if(matrix[newX][newY].equals("*")){ // 벽돌
                    matrix[newX][newY] = ".";
                }
            }
        }

    }
}