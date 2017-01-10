
public class Playlist {

	//The playlist class is simple. It only holds one name and represents a playlist.
	//It can change the name and return it
	private String name;
	
	public Playlist(String n){
		
		if(n != null){
			
			name = n;
			
		}
	}
	
	public String getName(){
		
		return name;
		
	}
	
	public void setName(String n){
		
		name = n;
		
	}
	
}
