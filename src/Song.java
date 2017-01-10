import java.io.File;
import java.net.URL;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.Object;

//This class represents one song
public class Song {

	private String name;
	private String artist;
	private String album;
	private File fileName;
	private URL fileNamePic;
	private String date;
	
	/*The song constructor accepts 4 Strings, 1 File and 1 URL
	 *
	 * If no name is inputed, the private variable name is set to Unknown as default
	 * If no Artist is inputed, the private variable Artist is set to Unknown Artist as default
	 * If no Album is inputed, the private variable Album is set to Unknown Album as default
	 * If no Date is inputed, the private variable Date is set to none as default
	 */
	
	Song(String n, String Artist, String Album, String d, File FileName, URL FileNameP){
		
		if(n == null){
			
			name = "Unknown";
			
		}else{
	
			name = n;
		
		}
	
		if(Artist == null){
			
			artist = "Unknown Artist";
			
		}else{
	
		artist = Artist;
		
		}
		
		if(Album == null){
			
			album = "Unknown Album";
			
		}else{

		album = Album;
		
		}
		
		if(FileName == null){
			
			fileName = null;
			
		}else{

		try{
			
			fileName = FileName;
		
		}catch(Exception e){
			
		}
		
		}
		
		if(FileNameP == null){
			
			fileNamePic = null;
			
		}else{
			
		try{
			
			fileNamePic = FileNameP;
			
		}catch(Exception e){
					
		}
		
		}
		
		if(d == null){
			
		date = "none";	
			
		}else{

		date = d;
		
		}
		
	}
	
	//These methods return parts of the Object such as name, artist, album, date, music file and image file
	public String getName(){
		
		return name;
		
	}
	
	public String getArtist(){
		
		return artist;
		
	}

	public String getAlbum(){
		
		return album;
		
	}

	public File getFileName(){
		
		return fileName;
		
	}

	public URL getFileNamePic(){
		
		return fileNamePic;
		
	}

	public String getDate(){
		
		return date;
		
	}
	
	
	//These methods allow for quick changes such as change of name, date, Artist, and album
	public void setName(String n){
		
		name = n;
		
	}
	
	public void setArtist(String a){
		
		artist = a;
		
	}

	public void setAlbum(String a){
		
		album = a;
		
	}
	
	public void setDate(String d){
		
		date = d;
		
	}
	
}
