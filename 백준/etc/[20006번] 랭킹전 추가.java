import java.util.*;


public class Main {
    public static void main(String[] args) {
       ex a = new ex();
       a.execute();
    }
}



class ex{
	Scanner s = new Scanner(System.in);
	int countPlayer, countRoom;
	int level; String name;
	List<Room> roomList = new ArrayList<>();
	void execute() {
		countPlayer = s.nextInt();	countRoom = s.nextInt();
		
		for(int i=0; i < countPlayer; i++) { // 유저 입장 반복문 
			level = s.nextInt(); name = s.next();	
			find_Room();
		}
		
		for(int i = 0; i < roomList.size(); i++) {
			roomList.get(i).print();
		}
		
	}
	
	int find_Room() {
		for(int i =0; i<roomList.size(); i++) {
			Room a = roomList.get(i);
			if( a.rowLevel <= level && level <= a.topLevel && !a.flag) {
				a.input(name, level);
				return 0;
			}
		}
		Room r = new Room(name,level,countRoom);
		roomList.add(r);
		
		return 0;
	}
	
	
	
	
}


class Room{
	int rowLevel, topLevel, count=0, full;
	List<String> NameList = new ArrayList<>();
	List<Integer> LevelList = new ArrayList<>();
	Map<String, Integer> Playlist = new HashMap<String, Integer>();
	boolean flag = false;
	Room(String s, int l, int fc){
		NameList.add(s);
	//	LevelList.add(l);
		Playlist.put(s,l);
		rowLevel = l - 10;
		topLevel = l + 10;
		full = fc;
		count++;
		isFull();
		}
	
	void input(String s , int l) {
		NameList.add(s);
//		LevelList.add(l);
		Playlist.put(s,l);
		count++;
		isFull();
	}
	
	void isFull() {
		if(count == full) {
			flag = true;
		}
	}
	
	void print() {
		if(flag) { System.out.println("Started!"); }
		else {	System.out.println("Waiting!");	}
		Collections.sort(NameList);
		for(int i = 0; i < count; i++) {
			System.out.println(Playlist.get(NameList.get(i)) + " " + NameList.get(i) );
		}
	}
}