import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.net.*;

/**
 * When a song is deleted, the artist and the album name will still appear if it
 * is the only one with that particular name.
 * 
 * The song will be erased from the album name however. Upon closing and reopening the program,
 * the unused names will disappear.
 * 
 * @author John
 *
 */

/*This program is a music library
 * 
 * There are no instructions included in the GUI because it is meant to be straightforward
 * 
 * You can switch between songs, playlists, albums and artists using the bottom buttons
 * 
 * The songs are ordered alphabetically
 * 
 * The artists are also ordered alphabetically and clicking one of them
 * will list all of the songs that have the common artist(This is the same principle for
 * albums)
 * 
 * You can click the play/ stop button to play/ stop music
 * 
 * You can add playlists, delete playlists and add songs to playlists
 * 
 * Everything is stored and saved in multiple IO textfile after the program is closed
 * 
 * Each song can hold a name, artist, album, music file, date and a picture
 * The music should be in .wav format
 * The pictures should be in .jpg 
 * 
 */

public class Main extends JFrame implements ActionListener, Comparable {//ActionListener and comparable are implemented

	/*Several variables are initialized
	 * 
	 *Most of them are JButtons
	 *
	 *The Button arrays are meant to parallel(represent, they are essentially the same)
	 * the Song/Playlist arrays
	 * 
	 * Songs parallels songObject
	 * 
	 * Songs is also used to represent albumSongArray, artistAongArray and playlistSongArray
	 * 
	 * Artists parallels artistSongArray
	 * Albums parallels albumSongArray
	 * 
	 * Playlist parallels playlistArray
	 * 
	 * playlistSongArray is a special case, however it is related to playlistArray
	 * 
	 * All Songs and playlists WILL have different names
	 */
	
	private Player player;
	private File []  FileNames = new File [9];
	private String[] names = new String [9], ArtistNames = new String [9],
	AlbumNames = new String [9], Dates = new String [9], playlistNames = new String[9];
	private URL [] FilePicNames = new URL [9];
	private Playlist[] playlistArray = new Playlist [9];
	private Song[] songObject = new Song[9], artistSongArray = new Song[9], albumSongArray = new Song[9], playlistSongArray = new Song[9];
	private JButton[] Songs =  new JButton [9], Playlists =  new JButton [9], Artists =  new JButton [9], Album =  new JButton [9];
	private JButton songs, playlists, artists, albums, play, stop, editSong, editPlaylist, deleteSong, cancelPlaylistEdit, cancelSongEdit,
	addSong, addPlaylist, addSongToPlaylist, deletePlaylist;
	private JLabel title, nowPlaying, original, image,
	artistDisplay, albumDisplay, songNameDisplay, dateDisplay;
	
	public Main(){
	
		/*This GUI layout is mapped Pixel by pixel
		 * 
		 */
		
		/*The size of the GUI, its title and its background are set
		 * 
		 */
		this.setSize(965,725);
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("Music Library");
		
		setContentPane(new JLabel(new ImageIcon("images/background.jpg")));
		
		Container c = getContentPane();
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		/*Several ImageIcons are set
		 * 
		 * These will be used to create the appearance of the buttons/labels in the GUI
		 */
		
		ImageIcon button = new ImageIcon("images/button.jpg");
		ImageIcon bottomButton = new ImageIcon("images/bottomButton.jpg");
		ImageIcon label = new ImageIcon("images/label.jpg");
		ImageIcon nowPlayingButton = new ImageIcon("images/nowPlaying.jpg");
		ImageIcon sampleImageBackground = new ImageIcon("images/image.jpg");
		ImageIcon playButton = new ImageIcon("images/play.jpg");
		ImageIcon stopButton = new ImageIcon("images/stop.jpg");
		
		
		/*Main points of the layout;
		 * 
		 *the original is the sub title (The one that says Song Name when first opening it)
		 *The original Label only ever changes text. It is a universal label in this program
		 * 
		 * The same goes with the main title label. Whenever a "Bottom button is pressed",
		 * the title label changes to show the section that is currently being used
		 * 
		 * The image holds the image
		 * The Display JLabels are the ones that show the text underneath the image
		 * 
		 * There is a JButton array for songs, playlists, albums and artists
		 * 
		 * The bottom buttons change which arrays are shown
		 * 
		 * only the songs and the playlists can be edited
		 * 
		 * When a button in the playlist array is clicked, You can add songs to them
		 * 
		 * The play/stop buttons are located in the bottom left corner(facing the screen)
		 * 
		 */
		
		original = new JLabel(label);
		original.setBounds(374, 90, 565, 35);
		original.setFont(new Font("Arial",Font.BOLD,18));
		original.setForeground(Color.WHITE);
		original.setText("Song Name");
		original.setHorizontalTextPosition(JLabel.CENTER);
		original.setVerticalTextPosition(JLabel.CENTER);
		original.setVisible(true);
		c.add(original);
		
		title = new JLabel();
		title.setBounds(0, 0, getWidth(), 90);
		title.setFont(new Font("Arial",Font.BOLD,26));
		title.setForeground(Color.WHITE);
		title.setText("Songs");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalTextPosition(JLabel.CENTER);
		c.add(title);
		
		nowPlaying = new JLabel(nowPlayingButton);
		nowPlaying.setBounds(40, 40, 250, 100);
		nowPlaying.setFont(new Font("Arial",Font.BOLD,18));
		nowPlaying.setForeground(Color.WHITE);
		nowPlaying.setText("Now Playing");
		nowPlaying.setHorizontalTextPosition(JLabel.CENTER);
		nowPlaying.setVerticalTextPosition(JLabel.CENTER);
		c.add(nowPlaying);
		
		image = new JLabel(sampleImageBackground);
		image.setBounds(63, 165, 200, 200);
		image.setFont(new Font("Arial",Font.BOLD,18));
		image.setForeground(Color.WHITE);
		image.setText("No Image");
		image.setHorizontalTextPosition(JLabel.CENTER);
		image.setVerticalTextPosition(JLabel.CENTER);
		c.add(image);
		
		songNameDisplay = new JLabel();
		songNameDisplay.setBounds(63, 405, 200, 45);
		songNameDisplay.setFont(new Font("Arial",Font.BOLD,15));
		songNameDisplay.setForeground(Color.WHITE);
		songNameDisplay.setText("Name:");
		songNameDisplay.setHorizontalTextPosition(JLabel.CENTER);
		songNameDisplay.setVerticalTextPosition(JLabel.CENTER);
		c.add(songNameDisplay);
		
		artistDisplay = new JLabel();
		artistDisplay.setBounds(63, 425, 200, 45);
		artistDisplay.setFont(new Font("Arial",Font.BOLD,15));
		artistDisplay.setForeground(Color.WHITE);
		artistDisplay.setText("Artist:");
		artistDisplay.setHorizontalTextPosition(JLabel.CENTER);
		artistDisplay.setVerticalTextPosition(JLabel.CENTER);
		c.add(artistDisplay);
		
		albumDisplay = new JLabel();
		albumDisplay.setBounds(63, 445, 200, 45);
		albumDisplay.setFont(new Font("Arial",Font.BOLD,15));
		albumDisplay.setForeground(Color.WHITE);
		albumDisplay.setText("Album:");
		albumDisplay.setHorizontalTextPosition(JLabel.CENTER);
		albumDisplay.setVerticalTextPosition(JLabel.CENTER);
		c.add(albumDisplay);
		
		
		dateDisplay = new JLabel();
		dateDisplay.setBounds(63, 505, 200, 45);
		dateDisplay.setFont(new Font("Arial",Font.BOLD,15));
		dateDisplay.setForeground(Color.WHITE);
		dateDisplay.setText("Date Added:");
		dateDisplay.setHorizontalTextPosition(JLabel.CENTER);
		dateDisplay.setVerticalTextPosition(JLabel.CENTER);
		c.add(dateDisplay);
		
		playlists = new JButton(bottomButton);
		playlists.setBounds(10, 643, 220, 45);
		playlists.setVisible(true);
		playlists.setFont(new Font("Arial", Font.BOLD,18));
		playlists.setForeground(Color.WHITE);
		playlists.setHorizontalTextPosition(JButton.CENTER);
		playlists.setVerticalTextPosition(JButton.CENTER);
		playlists.setText("Playlists");
		playlists.addActionListener(this);
		c.add(playlists);
		
		songs = new JButton(bottomButton);
		songs.setBounds(245, 643, 220, 45);
		songs.setVisible(true);
		songs.setFont(new Font("Arial", Font.BOLD,18));
		songs.setForeground(Color.WHITE);
		songs.setHorizontalTextPosition(JButton.CENTER);
		songs.setVerticalTextPosition(JButton.CENTER);
		songs.setText("Songs");
		songs.addActionListener(this);
		c.add(songs);
		
		artists = new JButton(bottomButton);
		artists.setBounds(480, 643, 220, 45);
		artists.setVisible(true);
		artists.setFont(new Font("Arial", Font.BOLD,18));
		artists.setForeground(Color.WHITE);
		artists.setHorizontalTextPosition(JButton.CENTER);
		artists.setVerticalTextPosition(JButton.CENTER);
		artists.setText("Artists");
		artists.addActionListener(this);
		c.add(artists);
		
		albums = new JButton(bottomButton);
		albums.setBounds(715, 643, 220, 45);
		albums.setVisible(true);
		albums.setFont(new Font("Arial", Font.BOLD,18));
		albums.setForeground(Color.WHITE);
		albums.setHorizontalTextPosition(JButton.CENTER);
		albums.setVerticalTextPosition(JButton.CENTER);
		albums.setText("Albums");
		albums.addActionListener(this);
		c.add(albums);
		
		editSong = new JButton(bottomButton);
		editSong.setBounds(850, 585, 65, 35);
		editSong.setVisible(true);
		editSong.setFont(new Font("Arial", Font.BOLD,15));
		editSong.setForeground(Color.WHITE);
		editSong.setHorizontalTextPosition(JButton.CENTER);
		editSong.setVerticalTextPosition(JButton.CENTER);
		editSong.setText("Edit");
		editSong.addActionListener(this);
		c.add(editSong);
		
		editPlaylist = new JButton(bottomButton);
		editPlaylist.setBounds(850, 585, 65, 35);
		editPlaylist.setVisible(false);
		editPlaylist.setFont(new Font("Arial", Font.BOLD,15));
		editPlaylist.setForeground(Color.WHITE);
		editPlaylist.setHorizontalTextPosition(JButton.CENTER);
		editPlaylist.setVerticalTextPosition(JButton.CENTER);
		editPlaylist.setText("Edit");
		editPlaylist.addActionListener(this);
		c.add(editPlaylist);
		
		cancelSongEdit = new JButton(bottomButton);
		cancelSongEdit.setBounds(905, 585, 45, 35);
		cancelSongEdit.setVisible(false);
		cancelSongEdit.setFont(new Font("Arial", Font.BOLD,15));
		cancelSongEdit.setForeground(Color.WHITE);
		cancelSongEdit.setHorizontalTextPosition(JButton.CENTER);
		cancelSongEdit.setVerticalTextPosition(JButton.CENTER);
		cancelSongEdit.setText("X");
		cancelSongEdit.addActionListener(this);
		c.add(cancelSongEdit);
		
		cancelPlaylistEdit = new JButton(bottomButton);
		cancelPlaylistEdit.setBounds(905, 585, 45, 35);
		cancelPlaylistEdit.setVisible(false);
		cancelPlaylistEdit.setFont(new Font("Arial", Font.BOLD,15));
		cancelPlaylistEdit.setForeground(Color.WHITE);
		cancelPlaylistEdit.setHorizontalTextPosition(JButton.CENTER);
		cancelPlaylistEdit.setVerticalTextPosition(JButton.CENTER);
		cancelPlaylistEdit.setText("X");
		cancelPlaylistEdit.addActionListener(this);
		c.add(cancelPlaylistEdit);
		
		addSong = new JButton(bottomButton);
		addSong.setBounds(385, 585, 105, 35);
		addSong.setVisible(false);
		addSong.setFont(new Font("Arial", Font.BOLD,15));
		addSong.setForeground(Color.WHITE);
		addSong.setHorizontalTextPosition(JButton.CENTER);
		addSong.setVerticalTextPosition(JButton.CENTER);
		addSong.setText("Add Song");
		addSong.addActionListener(this);
		c.add(addSong);
		
		deleteSong = new JButton(bottomButton);
		deleteSong.setBounds(755, 585, 140, 35);//535
		deleteSong.setVisible(false);
		deleteSong.setFont(new Font("Arial", Font.BOLD,15));
		deleteSong.setForeground(Color.WHITE);
		deleteSong.setHorizontalTextPosition(JButton.CENTER);
		deleteSong.setVerticalTextPosition(JButton.CENTER);
		deleteSong.setText("Delete Song");
		deleteSong.addActionListener(this);
		c.add(deleteSong);
		
		deletePlaylist = new JButton(bottomButton);
		deletePlaylist.setBounds(750, 585, 140, 35);//535
		deletePlaylist.setVisible(false);
		deletePlaylist.setFont(new Font("Arial", Font.BOLD,15));
		deletePlaylist.setForeground(Color.WHITE);
		deletePlaylist.setHorizontalTextPosition(JButton.CENTER);
		deletePlaylist.setVerticalTextPosition(JButton.CENTER);
		deletePlaylist.setText("Delete Playlist");
		deletePlaylist.addActionListener(this);
		c.add(deletePlaylist);
		
		addPlaylist = new JButton(bottomButton);
		addPlaylist.setBounds(610, 585, 125, 35);//535
		addPlaylist.setVisible(false);
		addPlaylist.setFont(new Font("Arial", Font.BOLD,15));
		addPlaylist.setForeground(Color.WHITE);
		addPlaylist.setHorizontalTextPosition(JButton.CENTER);
		addPlaylist.setVerticalTextPosition(JButton.CENTER);
		addPlaylist.setText("Add Playlist");
		addPlaylist.addActionListener(this);
		c.add(addPlaylist);
		
		addSongToPlaylist = new JButton(bottomButton);
		addSongToPlaylist.setBounds(875, 585, 45, 35);//535
		addSongToPlaylist.setVisible(false);
		addSongToPlaylist.setFont(new Font("Arial", Font.BOLD,18));
		addSongToPlaylist.setForeground(Color.WHITE);
		addSongToPlaylist.setHorizontalTextPosition(JButton.CENTER);
		addSongToPlaylist.setVerticalTextPosition(JButton.CENTER);
		addSongToPlaylist.setText("+");
		addSongToPlaylist.addActionListener(this);
		c.add(addSongToPlaylist);
		
		play = new JButton(playButton);
		play.setBounds(25, 575, 50, 50);//535
		play.setVisible(true);
		play.setFont(new Font("Arial", Font.BOLD,15));
		play.setForeground(Color.WHITE);
		play.setHorizontalTextPosition(JButton.CENTER);
		play.setVerticalTextPosition(JButton.CENTER);
		play.addActionListener(this);
		c.add(play);
		
		stop = new JButton(stopButton);
		stop.setBounds(25, 575, 50, 50);//535
		stop.setVisible(false);
		stop.setFont(new Font("Arial", Font.BOLD,15));
		stop.setForeground(Color.WHITE);
		stop.setHorizontalTextPosition(JButton.CENTER);
		stop.setVerticalTextPosition(JButton.CENTER);
		stop.addActionListener(this);
		c.add(stop);
		
		for(int i = 0; i < Songs.length; i++){
			
		Songs[i] = new JButton(button);
		Songs[i].setBounds(382, 175+(i*45), 562, 35);//535
		Songs[i].setVisible(true);
		Songs[i].setFont(new Font("Arial", Font.BOLD,18));
		Songs[i].setForeground(Color.WHITE);
		Songs[i].setHorizontalTextPosition(JButton.CENTER);
		Songs[i].setVerticalTextPosition(JButton.CENTER);
		Songs[i].addActionListener(this);
		c.add(Songs[i]);
		
		}
		
		for(int i = 0; i < Songs.length; i++){
			
			Playlists[i] = new JButton(button);
			Playlists[i].setBounds(382, 175+(i*45), 562, 35);//535
			Playlists[i].setVisible(false);
			Playlists[i].setFont(new Font("Arial", Font.BOLD,18));
			Playlists[i].setForeground(Color.WHITE);
			Playlists[i].setHorizontalTextPosition(JButton.CENTER);
			Playlists[i].setVerticalTextPosition(JButton.CENTER);
			Playlists[i].addActionListener(this);
			c.add(Playlists[i]);
			
			}
		
		for(int i = 0; i < Songs.length; i++){
			
			Album[i] = new JButton(button);
			Album[i].setBounds(382, 175+(i*45), 562, 35);//535
			Album[i].setVisible(false);
			Album[i].setFont(new Font("Arial", Font.BOLD,18));
			Album[i].setForeground(Color.WHITE);
			Album[i].setHorizontalTextPosition(JButton.CENTER);
			Album[i].setVerticalTextPosition(JButton.CENTER);
			Album[i].addActionListener(this);
			c.add(Album[i]);
			
			}
		
		for(int i = 0; i < Songs.length; i++){
			
			Artists[i] = new JButton(button);
			Artists[i].setBounds(382, 175+(i*45), 562, 35);//535
			Artists[i].setVisible(false);
			Artists[i].setFont(new Font("Arial", Font.BOLD,18));
			Artists[i].setForeground(Color.WHITE);
			Artists[i].setHorizontalTextPosition(JButton.CENTER);
			Artists[i].setVerticalTextPosition(JButton.CENTER);
			Artists[i].addActionListener(this);
			c.add(Artists[i]);
			
			}
		
		/*There is an IO textfile for the following;
		 * 
		 * SongNames
		 * Artists
		 * Albums
		 * Dates
		 * MusicFileNames
		 * ImageFileNames
		 * Playlists
		 * Playlist song array SongNames(9 of them)
		 * 
		 * These ones read the textfiles at the before the program is shown and initialize the
		 * song array + the playlist array
		 * 
		 */
		
		IOTextFile.openInputFile("SongNames.txt");
		
		for(int i = 0; i < names.length; i++){

			names [i] = IOTextFile.readLine();	
			
		}
		
		IOTextFile.closeInputFile();
		
		IOTextFile.openInputFile("ArtistNames.txt");
		
		for(int i = 0; i < ArtistNames.length; i++){

			ArtistNames [i] = IOTextFile.readLine();	
			
		}
		
		IOTextFile.closeInputFile();
		
		IOTextFile.openInputFile("AlbumNames.txt");
		
		for(int i = 0; i < AlbumNames.length; i++){

			AlbumNames [i] = IOTextFile.readLine();	
			
		}
		
		IOTextFile.closeInputFile();
		
		IOTextFile.openInputFile("Dates.txt");
		
		for(int i = 0; i < Dates.length; i++){

			Dates [i] = IOTextFile.readLine();	
			
		}
		
		IOTextFile.closeInputFile();
		
		IOTextFile.openInputFile("ImageFiles.txt");
		
		for(int i = 0; i < FilePicNames.length; i++){

			String s = IOTextFile.readLine();
			
			try {
				
				FilePicNames [i] = new URL(s);
				
				} catch(Exception e) {
			
			}
			
		}
		
		IOTextFile.closeInputFile();
		
		IOTextFile.openInputFile("MusicFiles.txt");
		
		for(int i = 0; i < FileNames.length; i++){

			String s = IOTextFile.readLine();
			
			if(s != null){
			
				FileNames [i] = new File(s);
				
			}else{
				
				FileNames [i] = null;
				
			}
				
		}
		
		IOTextFile.closeInputFile();
		
		IOTextFile.openInputFile("Playlists.txt");
		
		for(int i = 0; i < playlistArray.length; i++){

				playlistNames [i] = IOTextFile.readLine();	
		
		}
		
		IOTextFile.closeInputFile();
		
		for(int i = 0; i < songObject.length; i++){
				
				if(names[i] != null || ArtistNames[i] != null || AlbumNames[i] != null || Dates[i] != null || FileNames[i] != null || FilePicNames[i] != null){
				
				Song tempSong = new Song(names[i], ArtistNames[i], AlbumNames[i] , Dates[i], FileNames[i], FilePicNames[i]);
				addSong(tempSong);
				
			}
		}
		
		for(int i = 0; i < playlistArray.length; i++){
			
			if(playlistNames[i] != null){
				
				playlistArray[i] = new Playlist(playlistNames[i]);
				
			}
		}
		
		for(int i = 0; i < playlistArray.length; i++){
			
			if(playlistArray[i] != null){
				
				Playlists[i].setText(playlistArray[i].getName());
				
			}
		}
		
	}
	
	/*This is a comparable method that returns the greater String value(ASCII table)
	 * 
	 * It is used for sorting Alphabetically
	 * 
	 */
	public Comparable getMax(Comparable one, Comparable two){
		
		if(one.compareTo(two)<0)
			return two;
		else if(one.compareTo(two)>0)
			return one;
		else
			return null;
		
	}
	
	/*There are two selection sorts;
	 * 
	 * Playlist array
	 * Song array
	 * 
	 * Both are very similar in creation and are meant to organize names alphabetically.
	 * 
	 * These ones were inspired from a wikipedia example:
	 * source: https://en.wikipedia.org/wiki/Selection_sort
	 * 
	 * They use the comparable method as part of their sorting
	 * 
	 */
	
	/*Two nested for loops will shift the position of the songs in the array
	 * passed through so that they start at index 0 and there are no null spaces
	 * in between them
	 */
	
	public void nullSpaceDelete(Song [] s){
	
	for(int a = 0; a < s.length; a++){
		
		if(s[a] != null){
			
			for(int b = 0; b < s.length; b++){
				
				if(s[b] == null){
					 
					s[b] = s[a];
					s[a] = null;
					b = s.length;
					
					}
				}
				
			}
			
		}
		
	}
	
	public void selectionSortPlaylist(Playlist [] s){
		
	String [] name = new String [s.length];//First a string array is declared
		
	for(int i = 0; i < s.length; i++){
		
		if(s[i] != null){
		name[i] = s[i].getName(); //The array is then initialized with each playlist name
		
		}
	}
	
		/*In this section,
		 * 
		 * There are two nested for loops
		 * 
		 * min represents the "minimun value"
		 * 
		 * The name at the index of "a" in the second for loop is compared to the minimum value
		 * using comparable and if the greater value returned is the index of min, then
		 * the new lowest index (a) is set.
		 * 
		 */
	
		int n = 0;

		for(int i = 0; i < s.length; i++){

			int min = i;
			n++;
			
			for(int a = n-1; a < s.length; a++){
				
				if(name[a] != null && name[min] != null){
				
				String one = name[min].toUpperCase();
				String two = name[a].toUpperCase();
				
				String result = (String)getMax(one, two);
				
				if(result != null){
				
				if(result.equalsIgnoreCase((String)one)){
					
					min = a;
					
						}
					}
				}
			}
			
			if(min != i){
				
				Playlist first = s[i];
				Playlist second = s[min];
				
				s [min] = first;
				s [i] = second;
				
				}
		}
		
	}
	public void selectionSortSong(Song [] s){
	
	String [] name = new String [s.length];
		
	for(int i = 0; i < s.length; i++){
		
		if(s[i] != null){
		name[i] = s[i].getName();
		
		}
		
	}
	
		int n = 0;

		for(int i = 0; i < s.length; i++){

			int min = i;
			n++;
			
			for(int a = n-1; a < s.length; a++){
				
				if(name[a] != null && name[min] != null){
				
				String one = name[min].toUpperCase();
				String two = name[a].toUpperCase();
				
				String result = (String)getMax(one, two);
				
				if(result != null){
				
				if(result.equalsIgnoreCase((String)one)){
					
					min = a;
					
						}
					}
				}
			}
			
			if(min != i){
				
				Song first = s[i];
				Song second = s[min];
				
				s [min] = first;
				s [i] = second;
				
				}
		}
		
	}
	
	//Shows the main GUI
	public static void main(String[]args){
		
		Main one = new Main();
		one.show();
		
	}
	
	/*Addsong Method;
	 * 
	 * A for loop checks if a name already exists or not, to which it adds an underscore
	 * 
	 * A for loop searches for the first null element in the songObject array,
	 * 
	 * It is put through a selection sort to order it alphabetically
	 * The song button array is synced with the songObject array
	 * 
	 */
	public void addSong(Song s){
		
		for(int i = 0; i < songObject.length; i++){
			
			if(songObject[i] != null && songObject[i].getName().equalsIgnoreCase(s.getName())){
				
				s.setName(s.getName()+"_");
				
			}
			
		}
		
		for(int i = 0; i < songObject.length; i++){
			
			if(songObject[i] == null){
				
				songObject[i] = s;	
				i = songObject.length;
				
			}
			
		}
		
		selectionSortSong(songObject);
		
		for(int i = 0; i < Songs.length; i++){
			
			if(songObject[i] != null){
				
				Songs[i].setText(songObject[i].getName());
				
			}
		}
		
		/* These next two sections deal with Artists and Albums 
		 * 
		 * First, the Artist button array is emptied of text,  and n(which models the position
		 * of the Artist button array that is being filled) is set equal to 0
		 * 
		 * Next, two for loops and a boolean flag choose a position in the songObject array(i),
		 * then scan the entire Artist button array for the same text as the songObject's
		 * getArtist. If the text is the same, then the artist is the same and doesn't
		 * need to be stored twice.
		 * 
		 * The same principle is applied to Albums
		 * 
		 */
		
		int n = 0;
		
		for(int i = 0; i < Artists.length; i++){
			
			Artists[i].setText("");
			
		}
		
		for(int i = 0; i < songObject.length; i++){
			
			if(songObject[i] != null){
				
				boolean flag = false;
				
				for(int a = 0; a < Artists.length; a++){
				
					if(Artists[a].getText().equalsIgnoreCase(songObject[i].getArtist())){
					
						flag = true;
						
					}
					
				}
				
				if(!flag){	
					
					Artists[n].setText(songObject[i].getArtist());
					n++;
					
				}
				
			}
			
		}
		
		n = 0;
		
		for(int i = 0; i < Album.length; i++){
			
			Album[i].setText("");
			
		}
		
		for(int i = 0; i < songObject.length; i++){
			
			if(songObject[i] != null){
				
				boolean flag = false;
				
				for(int a = 0; a < Album.length; a++){
				
					if(Album[a].getText().equalsIgnoreCase(songObject[i].getAlbum())){
					
						flag = true;
						
					}
					
				}
				
				if(!flag){	
					
					Album[n].setText(songObject[i].getAlbum());
					n++;
					
				}
				
			}
			
		}
		

		/*Everytime a new song is created
		 * 
		 * It should be saved to the text file.
		 * 
		 * The songObject at each position is split up to store its
		 * 
		 * Name (String)
		 * Artist (String)
		 * Album (String)
		 * Date (String)
		 * Music File (File)
		 * Image File (URL)
		 * 
		 * There is no song that is not in the songObject Array. Almost everything ends
		 * up referencing the array.
		 * 
		 */
		
		IOTextFile.createOutputFile("SongNames.txt", false);
		
		for(int i = 0; i < songObject.length; i++){

			if(songObject[i] != null)
			IOTextFile.println(songObject[i].getName());
			
		}
		
		IOTextFile.closeOutputFile();
		
		IOTextFile.createOutputFile("ArtistNames.txt", false);
		
		for(int i = 0; i < songObject.length; i++){

			if(songObject[i] != null)
			IOTextFile.println(songObject[i].getArtist());
			
		}
		
		IOTextFile.closeOutputFile();
		
		IOTextFile.createOutputFile("AlbumNames.txt", false);
		
		for(int i = 0; i < songObject.length; i++){

			if(songObject[i] != null)
			IOTextFile.println(songObject[i].getAlbum());
			
		}
		
		IOTextFile.closeOutputFile();
		
		IOTextFile.createOutputFile("Dates.txt", false);
		
		for(int i = 0; i < songObject.length; i++){

			if(songObject[i] != null)
			IOTextFile.println(songObject[i].getDate());
			
		}
		
		IOTextFile.closeOutputFile();
		
		IOTextFile.createOutputFile("ImageFiles.txt", false);
		
		for(int i = 0; i < songObject.length; i++){

				if(songObject[i] != null){
				
				try{
				
				IOTextFile.println(songObject[i].getFileNamePic());
			
				}catch(Exception e){
				
				}
			}
		}
		
		IOTextFile.closeOutputFile();
		
		IOTextFile.createOutputFile("MusicFiles.txt", false);
		
		for(int i = 0; i < songObject.length; i++){

			if(songObject[i] != null){
			
			try{
				
				IOTextFile.println(songObject[i].getFileName());
				
				}catch(Exception e){
					
				}
			}
		}
		
		IOTextFile.closeOutputFile();
		
	}

	
	/*Action listener method
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
	
		/*For loop scans the Song button array and checks if a button is pressed
		 * 
		 * If pressed, all music is halted(because it is a new song)
		 * 
		 * The main title(which changes based on which section you are under) is checked
		 * and its appropriate songArray(songObject, albumSong, artistSong, or playlistSong)
		 * is synced with the Song button array.
		 * 
		 * All of the data about the song is shown under the Now playing label
		 * 
		 */
		
		for(int i = 0; i < Songs.length; i++){
	
			if(e.getSource() == Songs[i]){
				
				stop.setVisible(false);
				play.setVisible(true);
				
				if(player != null){
				player.stopMusic();
				}
				
				if(title.getText().equalsIgnoreCase("Songs")){
				
				/*Songs is the title label so the songObject array is used
				 * 
				 * The labels are set to display the name, artist, album and date
				 * 
				 * If an image is present, it will display the image
				 * 	
				 */
					
				if(songObject[i] != null){
				
				songNameDisplay.setText("Name: "+songObject[i].getName());
				artistDisplay.setText("Artist: "+songObject[i].getArtist());
				albumDisplay.setText("Album: "+songObject[i].getAlbum());
				dateDisplay.setText("Date: "+songObject[i].getDate());
			
				}
				
				if(songObject[i] != null && songObject[i].getFileNamePic() != null){
					
					ImageIcon picture = new ImageIcon(songObject[i].getFileNamePic());
					image.setIcon(picture);
					image.setText("");
					
				}else{
					
					ImageIcon sampleImageBackground = new ImageIcon("images/image.jpg");
					image.setIcon(sampleImageBackground);
					image.setText("No Image");
					
					}
				
				}else if(title.getText().equalsIgnoreCase("Artists")){
					
					if(artistSongArray[i] != null){
						
						songNameDisplay.setText("Name: "+artistSongArray[i].getName());
						artistDisplay.setText("Artist: "+artistSongArray[i].getArtist());
						albumDisplay.setText("Album: "+artistSongArray[i].getAlbum());
						dateDisplay.setText("Date: "+artistSongArray[i].getDate());
						
						}
						
						if(artistSongArray[i] != null && artistSongArray[i].getFileNamePic() != null){
							
							ImageIcon picture = new ImageIcon(artistSongArray[i].getFileNamePic());
							image.setIcon(picture);
							image.setText("");
							
						}else{
							
							ImageIcon sampleImageBackground = new ImageIcon("images/image.jpg");
							image.setIcon(sampleImageBackground);
							image.setText("No Image");
							
							}
					
				}else if(title.getText().equalsIgnoreCase("Albums")){
					
					if(albumSongArray[i] != null){
						
						songNameDisplay.setText("Name: "+albumSongArray[i].getName());
						artistDisplay.setText("Artist: "+albumSongArray[i].getArtist());
						albumDisplay.setText("Album: "+albumSongArray[i].getAlbum());
						dateDisplay.setText("Date: "+albumSongArray[i].getDate());
					
						}
						
						if(albumSongArray[i] != null && albumSongArray[i].getFileNamePic() != null){
							
							ImageIcon picture = new ImageIcon(albumSongArray[i].getFileNamePic());
							image.setIcon(picture);
							image.setText("");
							
						}else{
							
							ImageIcon sampleImageBackground = new ImageIcon("images/image.jpg");
							image.setIcon(sampleImageBackground);
							image.setText("No Image");
							
							}
					
				}else if(title.getText().equalsIgnoreCase("Playlists")){
					
						if(playlistSongArray[i] != null){
						
						songNameDisplay.setText("Name: "+playlistSongArray[i].getName());
						artistDisplay.setText("Artist: "+playlistSongArray[i].getArtist());
						albumDisplay.setText("Album: "+playlistSongArray[i].getAlbum());
						dateDisplay.setText("Date: "+playlistSongArray[i].getDate());
					
						}
						
						if(playlistSongArray[i] != null && playlistSongArray[i].getFileNamePic() != null){
							
							ImageIcon picture = new ImageIcon(playlistSongArray[i].getFileNamePic());
							image.setIcon(picture);
							image.setText("");
							
						}else{
							
							ImageIcon sampleImageBackground = new ImageIcon("images/image.jpg");
							image.setIcon(sampleImageBackground);
							image.setText("No Image");
							
					}
				}
				
			}
		
		/*The next 4 action listeners apply to the bottom buttons
		 * 
		 * They change the main title label accordingly
		 * 
		 * They set the appropriate buttons invisible and the appropriate buttons visible
		 * They are very simple actionlisteners
		 * 
		 */
		}if(e.getSource() == playlists){
			
			title.setText("Playlists");
		
			for(int i = 0; i < Songs.length; i++){
				
				Songs[i].setVisible(false);
				Artists[i].setVisible(false);
				Album[i].setVisible(false);
				Playlists[i].setVisible(true);
				
			}
			
			original.setText("Playlists");
			original.setVisible(true);
			
			editSong.setVisible(false);
			addSong.setVisible(false);
			deleteSong.setVisible(false);
			cancelSongEdit.setVisible(false);
			
			editPlaylist.setVisible(true);
			addPlaylist.setVisible(false);
			deletePlaylist.setVisible(false);
			cancelPlaylistEdit.setVisible(false);
			
			addSongToPlaylist.setVisible(false);
			
		}if(e.getSource() == songs){
			
			title.setText("Songs");
	
			/* Since the Songs buttons are modified a lot,
			 * They need to be synced with the songObject array everytime
			 * its bottom button is pressed
			 * 
			 */
			
			for(int i = 0; i < Songs.length; i++){
				
				if(songObject[i] != null){
				
					Songs[i].setText(songObject[i].getName());
					
				}
			}
			
			for(int i = 0; i < Songs.length; i++){
				
				Songs[i].setVisible(true);
				Artists[i].setVisible(false);
				Album[i].setVisible(false);
				Playlists[i].setVisible(false);	
				
			}
			
			original.setText("Song Name");
			original.setVisible(true);
			
			editSong.setVisible(true);
			addSong.setVisible(false);
			deleteSong.setVisible(false);
			cancelSongEdit.setVisible(false);
			
			editPlaylist.setVisible(false);
			addPlaylist.setVisible(false);
			deletePlaylist.setVisible(false);
			cancelPlaylistEdit.setVisible(false);
			
			addSongToPlaylist.setVisible(false);
			
		}if(e.getSource() == artists){
			
			title.setText("Artists");

			for(int i = 0; i < Songs.length; i++){
				
				Songs[i].setVisible(false);
				Artists[i].setVisible(true);
				Album[i].setVisible(false);
				Playlists[i].setVisible(false);	
				
			}

			original.setText("Artists");
			original.setVisible(true);
			
			editSong.setVisible(false);
			addSong.setVisible(false);
			deleteSong.setVisible(false);
			cancelSongEdit.setVisible(false);
			
			editPlaylist.setVisible(false);
			addPlaylist.setVisible(false);
			deletePlaylist.setVisible(false);
			cancelPlaylistEdit.setVisible(false);
			
			addSongToPlaylist.setVisible(false);
			
		}if(e.getSource() == albums){
			
			title.setText("Albums");
		
			for(int i = 0; i < Songs.length; i++){
				
				Songs[i].setVisible(false);
				Artists[i].setVisible(false);
				Album[i].setVisible(true);
				Playlists[i].setVisible(false);	
				
			}
			
			original.setText("Albums");
			original.setVisible(true);
			
			editSong.setVisible(false);
			addSong.setVisible(false);
			deleteSong.setVisible(false);
			cancelSongEdit.setVisible(false);
			
			editPlaylist.setVisible(false);
			addPlaylist.setVisible(false);
			deletePlaylist.setVisible(false);
			cancelPlaylistEdit.setVisible(false);
			
			addSongToPlaylist.setVisible(false);
			
		}
		
		/*The artists actionlistener and the album actionlistener are basically identical,
		 * 
		 * First a for loop and an two if else statements check if any the buttons are pressed
		 * and if they actually have a name.
		 * 
		 * Due to the way songs are added, if a button doesn't have a name, there is nothing there
		 * 
		 */
		
		for(int i = 0; i < Artists.length; i++){
			
		if(Artists[i].getText().equalsIgnoreCase("")){
			
		}else{	
			
			if(e.getSource() == Artists[i]){
				
				
				/*The sub title label is changed to Artists
				 * 
				 * the Songs button array and the artistSong array are cleared
				 * 
				 * Two nested for loops scan the songObject array and pick out songs with
				 * artists of the same name as the button pressed
				 * 
				 * the songs are stored in the artistSongArray in a relative manner
				 * (songObject[i] = artistSongArray[i]) so there are many null spaces
				 * 
				 */
				
				original.setText(Artists[i].getText());
				original.setVisible(true);
				
				for(int a = 0; a < Songs.length; a++){
					
					Songs[a].setText("");;
				
				}
				
				for(int a = 0; a < artistSongArray.length; a++){
					
						artistSongArray[a] = null;
					
				}
				
				for(int a = 0; a < songObject.length; a++){
					
					if(songObject[a] != null){
					
						if(songObject[a].getArtist().equalsIgnoreCase("")){	
						
						}else{
					
							if(songObject[a].getArtist().equalsIgnoreCase(Artists[i].getText())){
						
								artistSongArray[a] = songObject[a];
							
							}
						}
					}
				}

				/*The artistSongArray is put through tthe null space deleter which will 
				 * 
				 * delete its empty spaces
				 * 
				 */
				
				nullSpaceDelete(artistSongArray);
				
				/*Everything is an unorganized mess at this point
				 * 
				 * so it need to be put through the Song Selection sort
				 * 
				 */
				
				selectionSortSong(artistSongArray);
				
				//The Songs button array is shown and the Artist button array is hidden
				
				for(int a = 0; a < Artists.length; a++){
					
					Artists[a].setVisible(false);
					Songs[a].setVisible(true);
					
				}
				
				//The Songs button array is synced with the artistSongArray to display songs
				//Of the same artist

				for(int a = 0; a < artistSongArray.length; a++){
					
					if(artistSongArray[a] != null){
						
						if(artistSongArray[a].getName() != null){
							
								Songs[a].setText(artistSongArray[a].getName());			
								
							}
						}
					}
				}
			}
		}
		
		/*Same principle as the Artists actionlistener
		 * 
		 */
		
		for(int i = 0; i < Album.length; i++){
			
			if(Album[i].getText().equalsIgnoreCase("")){
				
			}else{	
				
				if(e.getSource() == Album[i]){

					original.setText(Album[i].getText());
					original.setVisible(true);
					
					for(int a = 0; a < Songs.length; a++){
						
						Songs[a].setText("");;
					
					}
					
					for(int a = 0; a < albumSongArray.length; a++){
						
						albumSongArray[a] = null;
						
					}
					
					for(int a = 0; a < songObject.length; a++){
						
						if(songObject[a] != null){
						
							if(songObject[a].getAlbum().equalsIgnoreCase("")){	
							
							}else{
						
								if(songObject[a].getAlbum().equalsIgnoreCase(Album[i].getText())){
							
									albumSongArray[a] = songObject[a];
								
								}
							}
						}
					}

					nullSpaceDelete(albumSongArray);
					
					selectionSortSong(albumSongArray);
					
					for(int a = 0; a < Album.length; a++){
						
						Album[a].setVisible(false);
						Songs[a].setVisible(true);
						
					}
					
					//Sets the text on the buttons accordingly

					for(int a = 0; a < albumSongArray.length; a++){
						
						if(albumSongArray[a] != null){
							
							if(albumSongArray[a].getName() != null){
								
									Songs[a].setText(albumSongArray[a].getName());						
									//System.out.println("Check");
									
								}
							}
						}
					}
				}
		}			
		
		/*Most of the playlist actionlistener is very similar to that of the artist/album
		 * actionlisteners
		 */
		
		for(int i = 0; i < Playlists.length; i++){
		 
		if(Playlists[i].getText().equalsIgnoreCase("")){
			
		}else{	
			
			if(e.getSource() == Playlists[i]){
				
				original.setText(Playlists[i].getText());
				original.setVisible(true);
				
				/*A new button -> Add song to playlist
				 * 
				 * is set visible
				 */
				addSongToPlaylist.setVisible(true);
				addPlaylist.setVisible(false);
				editPlaylist.setVisible(false);
				deletePlaylist.setVisible(false);
				cancelPlaylistEdit.setVisible(false);
				
				/*song button array and the playlistSongArray are cleared
				 * 
				 */
				
				for(int a = 0; a < Songs.length; a++){
					
					Songs[a].setText("");;
				
				}
				
				for(int a = 0; a < playlistSongArray.length; a++){
					
					playlistSongArray[a] = null;
				
				}
				
				for(int a = 0; a < Playlists.length; a++){
					
					Playlists[a].setVisible(false);
					Songs[a].setVisible(true);
					
				}
				
				/*Instead of checking songObjects for certain properties because it can't
				 * 
				 * It opens up an IO text file which is named in a way that parallels
				 * playlistArray(playlistArray index 0 = PlaylistSongNames0.txt)
				 * 
				 * Since the textfile only stores song names, the songObjectArray has to be
				 * scanned to pick out matching song names so it can be synced with the playlistSongArray
				 * 
				 */

				IOTextFile.openInputFile("PlaylistSongNames"+i+".txt");
				
				for(int a = 0; a < playlistSongArray.length; a++){
					
					String tempName = IOTextFile.readLine();
	
						for(int b = 0; b < songObject.length; b++){
							
							if(songObject[b] != null && songObject[b].getName().equalsIgnoreCase(tempName)){
					
								playlistSongArray[b] = songObject[b];	
						
						}
					}		
				}
				
				IOTextFile.closeInputFile();
		
				/*The rest of this actionlistener gets rid of null spaces
				 * and orders alphabetically
				 */
				
				nullSpaceDelete(playlistSongArray);
				
				selectionSortSong(playlistSongArray);
				
				for(int a = 0; a < playlistSongArray.length; a++){
					
					if(playlistSongArray[a] != null){
							
							Songs[a].setText(playlistSongArray[a].getName());
							
						}
					}
				}
			}
		
		/*This section allows the user to edit songs
		 * 
		 * You can add/delete songs
		 * This only appears when the bottom button "Songs" is pressed
		 * 
		 */
		
		}if(e.getSource() == editSong){//Simple, set appropriate buttons visible and invisible
			
			editSong.setVisible(false);
			addSong.setVisible(true);
			deleteSong.setVisible(true);
			cancelSongEdit.setVisible(true);
			
		}if(e.getSource() == addSong){//Opens Input GUI, which creates a new song based on user Input
			
			InputGUI one = new InputGUI(this);
			one.show();
	
			this.dispose();
			
		}if(e.getSource() == deleteSong){
			
			/*Delete Song Method;
			 * 
			 * A song name is entered,
			 * 
			 * The songObject array is searched to see if the song exists(search by getName())
			 * 
			 * If the song exists, the song at that point in the array is set equal to null
			 * The next two for loops get rid of the null spaces and order the song array
			 * alphabetically again
			 * 
			 */
			
			String songName = JOptionPane.showInputDialog("Enter the name of the Song:");
			
			for(int i = 0; i < songObject.length; i++){
				
				if(songObject[i] != null && songObject[i].getName().equalsIgnoreCase(songName)){
				
						songObject[i] = null;
			
				}
			}
			
			nullSpaceDelete(songObject);
			
			selectionSortSong(songObject);
			
			/*The IO textfiles are updated in the same way as in the addSong method
			 * 
			 */
			
			IOTextFile.createOutputFile("SongNames.txt", false);
			
			for(int i = 0; i < songObject.length; i++){

				if(songObject[i] != null)
				IOTextFile.println(songObject[i].getName());
				
			}
			
			IOTextFile.closeOutputFile();
			
			IOTextFile.createOutputFile("ArtistNames.txt", false);
			
			for(int i = 0; i < songObject.length; i++){

				if(songObject[i] != null)
				IOTextFile.println(songObject[i].getArtist());
				
			}
			
			IOTextFile.closeOutputFile();
			
			IOTextFile.createOutputFile("AlbumNames.txt", false);
			
			for(int i = 0; i < songObject.length; i++){

				if(songObject[i] != null)
				IOTextFile.println(songObject[i].getAlbum());
				
			}
			
			IOTextFile.closeOutputFile();
			
			IOTextFile.createOutputFile("Dates.txt", false);
			
			for(int i = 0; i < songObject.length; i++){

				if(songObject[i] != null)
				IOTextFile.println(songObject[i].getDate());
				
			}
			
			IOTextFile.closeOutputFile();
			
			IOTextFile.createOutputFile("ImageFiles.txt", false);
			
			for(int i = 0; i < songObject.length; i++){

				if(songObject[i] != null){
					
				try{
					
				IOTextFile.println(songObject[i].getFileNamePic());
				
				}catch(Exception p){
					
					}
				}
			}
			
			IOTextFile.closeOutputFile();
			
			IOTextFile.createOutputFile("MusicFiles.txt", false);
			
			for(int i = 0; i < songObject.length; i++){

				if(songObject[i] != null){
				
				try{
					
					IOTextFile.println(songObject[i].getFileName());
					
					}catch(Exception p){
						
					}
				
			}
		}
			
			IOTextFile.closeOutputFile();
			
			/*The song button array is emptied and synced with the
			 * songObject array
			 * 
			 */
			
			for(int i = 0; i < Songs.length; i++){
				
					Songs[i].setText("");
				
			}
			
			for(int i = 0; i < Songs.length; i++){
				
				if(songObject[i] != null){
					
					Songs[i].setText(songObject[i].getName());
					
				}
				
			}
			
		}
		
		if(e.getSource() == cancelSongEdit){//Simple, sets appropriate button visible/invisible
			
			editSong.setVisible(true);
			addSong.setVisible(false);
			deleteSong.setVisible(false);
			cancelSongEdit.setVisible(false);
			
		}if(e.getSource() == editPlaylist){//Simple, sets appropriate button visible/invisible
			
			editPlaylist.setVisible(false);
			addPlaylist.setVisible(true);
			deletePlaylist.setVisible(true);
			cancelPlaylistEdit.setVisible(true);
			
		}
		if(e.getSource() == addPlaylist){
			
		/*When addPlaylist is pressed,
		 * 
		 * The user can input a name as long as it isn't null(while loop will keep on asking if it is)
		 * 
		 * A for loop scans the playlistArray to see if the name already exists, if so, it will add
		 * an underscore to the name 
		 */

		String file = "";
		
		while(file.equalsIgnoreCase("")){
			
			file = JOptionPane.showInputDialog("Enter the name of the Playlist:");

		}
		
		for(int i = 0; i < playlistArray.length; i++){
			
			if(playlistArray[i] != null && playlistArray[i].getName().equalsIgnoreCase(file)){
				
				file+= "_";
				
			}
			
		}
		
		/*The playlistArray(That stores playlist objects)
		 * 
		 * is checked and the first null element is filled with a new playlist
		 * initialized by the name entered
		 * 
		 */
		
		for(int i = 0; i < Playlists.length; i++){
			
			if(playlistArray[i] == null){
				
				Playlist newPlay = new Playlist(file);
				playlistArray[i] = newPlay;
				Playlists[i].setText(file);
				i = Playlists.length;
				
				}
			
			}
		
		
		/*The IO text file that stores playlist names is synced with the
		 * new playlistArray
		 */
		
		IOTextFile.createOutputFile("Playlists.txt", false);
		
		for(int i = 0; i < playlistArray.length; i++){

			if(playlistArray[i] != null)
			IOTextFile.println(playlistArray[i].getName());
			
		}
		
		IOTextFile.closeOutputFile();

		}
		
		/*The delete playlist method will erase a playlist from the playlistArray and
		 * it will clear the appropriate PlaylistSongName textfile
		 */
		
		if(e.getSource() == deletePlaylist){
			
			
			//User inputs a name
			
			String playlistName = JOptionPane.showInputDialog("Enter the name of the Playlist:");
			
			/*A for loop checks if the name of the playlist actually exists
			 *
			 * If so, another for loop deals with the textfile updates
			 */
			
			for(int i = 0; i < playlistArray.length; i++){
				
				if(playlistArray[i] != null && playlistArray[i].getName().equalsIgnoreCase(playlistName)){
				
					playlistArray[i] = null;
					
					/*Starting from the null element in the playlistArray,
					 * 
					 * a is set equal to one after it.
					 * 
					 * The IO text file in that position is opened and the names are
					 * temporarily moved to a array that holds strings.
					 * 
					 * The textfile in the null position(a-1) is filled with the names
					 * from the string array
					 * 
					 * This repeats until every textfile is updated
					 */
					
					for(int a = i+1; a < playlistArray.length; a++){
						
						String [] array = new String[9];
						
						IOTextFile.openInputFile("PlaylistSongNames"+a+".txt");
						
						for(int b = 0; b < array.length; b++){
							
							array[b] = IOTextFile.readLine();
							
						}
						
						IOTextFile.closeInputFile();
						
						IOTextFile.createOutputFile("PlaylistSongNames"+(a-1)+".txt", false);
						
						for(int b = 0; b < array.length; b++){
							
							IOTextFile.println(array[b]);
							
						}
						
						IOTextFile.closeOutputFile();
							
					}
					
				}
				
			}
			
			/*The playlistArray is removed of null spaces (This is done in the same format as the text files)
			 */
			
				for(int i = 0; i < playlistArray.length; i++){
					
					if(playlistArray[i] == null && i < 8){
						
						playlistArray[i] = playlistArray[i+1];
						playlistArray[i+1] = null;
						
					}
			}
				
			/*The updated playlist names are saved in the Playlists textfile,
			 * 
			 * The Playlist button array is emptied of text and synced with the updated
			 * playlistArray
			 * 
			 */
			
			IOTextFile.createOutputFile("Playlists.txt", false);
				
				for(int a = 0; a < playlistArray.length; a++){
					
					if(playlistArray[a] != null){
						
						IOTextFile.println(playlistArray[a].getName());
						
					}	
				}
			
			IOTextFile.closeOutputFile();
				
			for(int i = 0; i < Playlists.length; i++){
					
					Playlists[i].setText("");
					
			}	
				
			for(int i = 0; i < Playlists.length; i++){
				
				if(playlistArray[i] != null){
				
					Playlists[i].setText(playlistArray[i].getName());
					
				}
			}


		}if(e.getSource() == addSongToPlaylist){
			
		/*The addSong to playlist actionlistener allows the user to input a name
		 * 
		 * A for loop checks if the song is already existent in the textfile by scanning the 
		 * Songs button array
		 * 
		 */
			
		boolean flag = false;
	
			String file = JOptionPane.showInputDialog("Enter the name of the Song:");
			
		for(int checkDouble = 0; checkDouble < Songs.length; checkDouble++){
			
			if(Songs[checkDouble].getText().equalsIgnoreCase(file)){

				flag = true;	
				
			}

		}
			
		if(!flag){
		
		/*If the song isn't on a Song button,
		 * 
		 * A for loop scans the playlistArray to see which index needs to be edited by checking
		 * if the the text is equal to the that of the sub title(original Jlabel)
		 * 
		 * the playlist number is then recorded
		 * 	
		 */
			
		int playlistNumber = 0;
		
		for(int i = 0; i < playlistArray.length; i++){
		
			if(playlistArray[i] != null && playlistArray[i].getName().equalsIgnoreCase(original.getText())){
				
				playlistNumber = i;
				
			}
			
		}	
		
		/* The songObject array is checked to see if the song name entered actually exists
		 * 
		 * If it does exist, the first null element in the playlistSongArray is found and
		 * set equal to the appropriate songObject
		 * 
		 */
		
		for(int i = 0; i < songObject.length; i++){
			
			if(songObject[i] != null && songObject[i].getName().equalsIgnoreCase(file)){
				
				for(int a = 0; a < playlistSongArray.length; a++){
					
					if(playlistSongArray[a] == null){
						
						playlistSongArray[a] = songObject[i];
						a = playlistSongArray.length;
						
					}
				}
			}
		}
		
		/*The null spaces are gotten rid of and the array is arranged alphabetically
		 * 
		 */
		
		nullSpaceDelete(playlistSongArray);
		
		selectionSortSong(playlistSongArray);
		
		/*Finally, based on the playlistNumber, the playlistSongArray
		 * will be saved to an appropriate IO textfile
		 * 
		 * The Songs button array is synced to the playlistSongArray
		 */
		
		/**This was a horribly inefficient section of code, however I had a logic error that I couldn't
		 * solve when I tried using two dimensional arrays.
		 * 
		 */
		
		if(playlistNumber == 0){
			
			IOTextFile.createOutputFile("PlaylistSongNames0.txt", false);
			
			for(int i = 0; i < playlistSongArray.length; i++){

				if(playlistSongArray[i] != null)
				IOTextFile.println(playlistSongArray[i].getName());
				
			}
			
			IOTextFile.closeOutputFile();
		
			for(int i = 0; i < playlistSongArray.length; i++){
				
				if(playlistSongArray[i] != null){
					
					Songs[i].setText(playlistSongArray[i].getName());
					
				}
			}
		
			
		}if(playlistNumber == 1){
			

			IOTextFile.createOutputFile("PlaylistSongNames1.txt", false);
			
			for(int i = 0; i < playlistSongArray.length; i++){

				if(playlistSongArray[i] != null)
				IOTextFile.println(playlistSongArray[i].getName());
				
			}
			
			IOTextFile.closeOutputFile();
		
			for(int i = 0; i < playlistSongArray.length; i++){
			
				if(playlistSongArray[i] != null){
					
				Songs[i].setText(playlistSongArray[i].getName());
				
				}
			}
			
			
		}if(playlistNumber == 2){

			IOTextFile.createOutputFile("PlaylistSongNames2.txt", false);
			
			for(int i = 0; i < playlistSongArray.length; i++){

				if(playlistSongArray[i] != null)
				IOTextFile.println(playlistSongArray[i].getName());
				
			}
			
			IOTextFile.closeOutputFile();
		
			for(int i = 0; i < playlistSongArray.length; i++){
				
				if(playlistSongArray[i] != null){
					
					Songs[i].setText(playlistSongArray[i].getName());
				
				}	
			}
			
		}if(playlistNumber == 3){
			

			IOTextFile.createOutputFile("PlaylistSongNames3.txt", false);
			
			for(int i = 0; i < playlistSongArray.length; i++){

				if(playlistSongArray[i] != null)
				IOTextFile.println(playlistSongArray[i].getName());
				
			}
			
			IOTextFile.closeOutputFile();
		
			for(int i = 0; i < playlistSongArray.length; i++){
				
				if(playlistSongArray[i] != null){
					
					Songs[i].setText(playlistSongArray[i].getName());
					
				}
			}
			
		}if(playlistNumber == 4){
			

			IOTextFile.createOutputFile("PlaylistSongNames4.txt", false);
			
			for(int i = 0; i < playlistSongArray.length; i++){

				if(playlistSongArray[i] != null)
				IOTextFile.println(playlistSongArray[i].getName());
				
			}
			
			IOTextFile.closeOutputFile();
		
			for(int i = 0; i < playlistSongArray.length; i++){
				
				if(playlistSongArray[i] != null){
					
					Songs[i].setText(playlistSongArray[i].getName());
				
				}
			}
			
		}if(playlistNumber == 5){
			

			IOTextFile.createOutputFile("PlaylistSongNames5.txt", false);
			
			for(int i = 0; i < playlistSongArray.length; i++){

				if(playlistSongArray[i] != null)
				IOTextFile.println(playlistSongArray[i].getName());
				
			}
			
			IOTextFile.closeOutputFile();
		
			for(int i = 0; i < playlistSongArray.length; i++){
				
				if(playlistSongArray[i] != null){
				
				Songs[i].setText(playlistSongArray[i].getName());
				
				}
				
			}
			
		}if(playlistNumber == 6){
			

			IOTextFile.createOutputFile("PlaylistSongNames6.txt", false);
			
			for(int i = 0; i < playlistSongArray.length; i++){

				if(playlistSongArray[i] != null)
				IOTextFile.println(playlistSongArray[i].getName());
				
			}
			
			IOTextFile.closeOutputFile();
		
			for(int i = 0; i < playlistSongArray.length; i++){
				
				if(playlistSongArray[i] != null){
					
				Songs[i].setText(playlistSongArray[i].getName());
				
				}
			}
			
			
		}if(playlistNumber == 7){
			

			IOTextFile.createOutputFile("PlaylistSongNames7.txt", false);
			
			for(int i = 0; i < playlistSongArray.length; i++){

				if(playlistSongArray[i] != null)
				IOTextFile.println(playlistSongArray[i].getName());
				
			}
			
			IOTextFile.closeOutputFile();
		
			for(int i = 0; i < playlistSongArray.length; i++){
				
				if(playlistSongArray[i] != null){
					
					Songs[i].setText(playlistSongArray[i].getName());
				
				}
			}
			
		}if(playlistNumber == 8){
			

			IOTextFile.createOutputFile("PlaylistSongNames8.txt", false);
			
			for(int i = 0; i < playlistSongArray.length; i++){

				if(playlistSongArray[i] != null)
				IOTextFile.println(playlistSongArray[i].getName());
				
			}
			
			IOTextFile.closeOutputFile();
		
			for(int i = 0; i < playlistSongArray.length; i++){
				
				if(playlistSongArray[i] != null){
					
				Songs[i].setText(playlistSongArray[i].getName());
				
						}
					}
				}
			}
		}
		if(e.getSource() == cancelPlaylistEdit){//Simple method set appropriate buttons visible/invisible
			
			editPlaylist.setVisible(true);
			addPlaylist.setVisible(false);
			deletePlaylist.setVisible(false);
			cancelPlaylistEdit.setVisible(false);
			
		}if(e.getSource() == play){
			
			/*Set play invisible
			 * Sets stop invisible
			 */
			
			stop.setVisible(true);
			play.setVisible(false);
			
			//initialize a File called musicFile
			File musicFile = null;
			
			//Checks if the name in songNameDisplay has a visible name(!= null)
			//substring 6 is after the initial text:(name:_)
			
			if(songNameDisplay.getText().substring(6) != null){
				
			/*If the substring exists, then a temporary string called check is set equal to it
			 * 
			 * A for loop finds the songObject with matching name
			 * and the File is retrieved via getFileName()
			 * 
			 * musicFile is set equal to the getFileName()
			 * 
			 */
				
			String check = songNameDisplay.getText().substring(6);
			
			for(int i = 0; i < songObject.length; i++){
				
				if(songObject[i] != null && songObject[i].getName().equalsIgnoreCase(check)){
				
					musicFile = songObject[i].getFileName();
					
				}
			}
		}
			
			//A new player is declared using the musicFile retrieved
			player = new Player(musicFile);
			
			//Music is played by calling on the Player class
			player.playMusic();
			
		}if(e.getSource() == stop){
			
			//stop is set invisible
			//play is set visible
			stop.setVisible(false);
			play.setVisible(true);
			
			//If there is an existing player object, it will stop the music
			//by calling on the player class
			if(player != null){
			player.stopMusic();
			}
		}
	}

	@Override
	public int compareTo(Object o) {//This is here to satisfy the Comparable implement
		// TODO Auto-generated method stub
		return 0;
	}	
	
}



