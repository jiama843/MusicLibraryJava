import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/*This is the inputGUI,
 * 
 * You can enter;
 * 
 * a song name
 * Artist
 * Album
 * Date
 * 
 * Upload a music file
 * Upload an image file
 * 
 * And by clicking done, it will create a new song
 * By clicking cancel, nothing will happen
 * 
 */

public class InputGUI extends JFrame implements ActionListener{

	//Variables initialized
	private String name, artist, album, date;
	private File fileName;
	private URL fileNamePic;
	private JButton nameB, artistB, albumB, dateB, fileNameB, fileNamePicB, done, cancel;
	private JLabel newSong;
	private Main main;//This is an important variable
	
	public InputGUI(Main one){//The InputGUI accepts a Main class
		
		/*Layout is done pixel by pixel
		 * 
		 */
		
		this.setSize(685,525);
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("New Song");
		
		setContentPane(new JLabel(new ImageIcon("images/inputBackground.jpg")));
		
		Container c = getContentPane();
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ImageIcon button = new ImageIcon("images/button.jpg");
		ImageIcon bottomButton = new ImageIcon("images/bottomButton.jpg");
		ImageIcon label = new ImageIcon("images/label.jpg");
		ImageIcon inputButton = new ImageIcon("images/nowPlaying.jpg");
		ImageIcon imageButton = new ImageIcon("images/image.jpg");
		
		newSong = new JLabel();
		newSong.setBounds( 0, 0, getWidth(), 70);
		newSong.setFont(new Font("Georgia",Font.BOLD,18));
		newSong.setForeground(Color.WHITE);
		newSong.setText("New Song");
		newSong.setHorizontalAlignment(JLabel.CENTER);
		newSong.setVerticalTextPosition(JLabel.CENTER);
		//title.setVisible(false);
		c.add(newSong);
		
		nameB = new JButton(inputButton);
		nameB.setBounds(50, 90, 150, 30);//535
		nameB.setVisible(true);
		nameB.setFont(new Font("Georgia", Font.BOLD,12));
		nameB.setForeground(Color.WHITE);
		nameB.setHorizontalTextPosition(JButton.CENTER);
		nameB.setVerticalTextPosition(JButton.CENTER);
		nameB.setText("Song Name");
		nameB.addActionListener(this);
		c.add(nameB);
		
		artistB = new JButton(inputButton);
		artistB.setBounds(50, 130, 150, 30);//535
		artistB.setVisible(true);
		artistB.setFont(new Font("Georgia", Font.BOLD,12));
		artistB.setForeground(Color.WHITE);
		artistB.setHorizontalTextPosition(JButton.CENTER);
		artistB.setVerticalTextPosition(JButton.CENTER);
		artistB.setText("Add Artist");
		artistB.addActionListener(this);
		c.add(artistB);
		
		albumB = new JButton(inputButton);
		albumB.setBounds(50, 170, 150, 30);//535
		albumB.setVisible(true);
		albumB.setFont(new Font("Georgia", Font.BOLD,12));
		albumB.setForeground(Color.WHITE);
		albumB.setHorizontalTextPosition(JButton.CENTER);
		albumB.setVerticalTextPosition(JButton.CENTER);
		albumB.setText("Add Album");
		albumB.addActionListener(this);
		c.add(albumB);
		
		dateB = new JButton(inputButton);
		dateB.setBounds(50, 210, 150, 30);//535
		dateB.setVisible(true);
		dateB.setFont(new Font("Georgia", Font.BOLD,12));
		dateB.setForeground(Color.WHITE);
		dateB.setHorizontalTextPosition(JButton.CENTER);
		dateB.setVerticalTextPosition(JButton.CENTER);
		dateB.setText("Add Date");
		dateB.addActionListener(this);
		c.add(dateB);
		
		fileNameB = new JButton(inputButton);
		fileNameB.setBounds(50, 310, 150, 30);//535
		fileNameB.setVisible(true);
		fileNameB.setFont(new Font("Georgia", Font.BOLD,12));
		fileNameB.setForeground(Color.WHITE);
		fileNameB.setHorizontalTextPosition(JButton.CENTER);
		fileNameB.setVerticalTextPosition(JButton.CENTER);
		fileNameB.setText("Upload File");
		fileNameB.addActionListener(this);
		c.add(fileNameB);
		
		fileNamePicB = new JButton(imageButton);
		fileNamePicB.setBounds(250, 100, 200, 200);//535
		fileNamePicB.setVisible(true);
		fileNamePicB.setFont(new Font("Georgia", Font.BOLD,12));
		fileNamePicB.setForeground(Color.WHITE);
		fileNamePicB.setHorizontalTextPosition(JButton.CENTER);
		fileNamePicB.setVerticalTextPosition(JButton.CENTER);
		fileNamePicB.setText("+Add Image");
		fileNamePicB.addActionListener(this);
		c.add(fileNamePicB);
		
		done = new JButton(inputButton);
		done.setBounds(10, 460, 150, 30);//535
		done.setVisible(true);
		done.setFont(new Font("Georgia", Font.BOLD,12));
		done.setForeground(Color.WHITE);
		done.setHorizontalTextPosition(JButton.CENTER);
		done.setVerticalTextPosition(JButton.CENTER);
		done.setText("Done");
		done.addActionListener(this);
		c.add(done);
		
		cancel = new JButton(inputButton);
		cancel.setBounds(515, 460, 150, 30);//535
		cancel.setVisible(true);
		cancel.setFont(new Font("Georgia", Font.BOLD,12));
		cancel.setForeground(Color.WHITE);
		cancel.setHorizontalTextPosition(JButton.CENTER);
		cancel.setVerticalTextPosition(JButton.CENTER);
		cancel.setText("Cancel");
		cancel.addActionListener(this);
		c.add(cancel);
		
		main = one;//This is set equal to the imported main class. It prevents data loss when the main is closed
		
	}

	public static void main(String[]args){
		
		//InputGUI one = new InputGUI(Main);
		//one.show();
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		/*In the next 4 methods, the user is allowed to input a String of their choice.
		 * 
		 * It will set a private variable equal to that of the user's input
		 */
		
		if(e.getSource() == nameB){
			
			String file = JOptionPane.showInputDialog("Enter the name of the Song:");
			name = file;
			
		}if(e.getSource() == artistB){
			
			String file = JOptionPane.showInputDialog("Enter the name of the Artist:");
			artist = file;
			
		}if(e.getSource() == albumB){
			
			String file = JOptionPane.showInputDialog("Enter the name of the Album:");
			album = file;
			
		}if(e.getSource() == dateB){
			
			String file = JOptionPane.showInputDialog("Enter the Date:");
			date = file;
			
		/*In this method, a JFileChooser allows the user to browse for files on their computer
		 * 
		 * It saves the file as type File
		 */
			
		}if(e.getSource() == fileNameB){			

			JFileChooser chooser = new JFileChooser();
			
			int choice = chooser.showOpenDialog(null);

			if (choice != JFileChooser.APPROVE_OPTION) return;
				
			File chosenFile = chooser.getSelectedFile();
			
			fileName = chosenFile;
			
			/*In this method, a JFileChooser allows the user to browse for files on their computer
			 * 
			 * It saves the file as type URL
			 */
			
		}if(e.getSource() == fileNamePicB){

			JFileChooser chooser = new JFileChooser();
			
			int choice = chooser.showOpenDialog(null);

			if (choice != JFileChooser.APPROVE_OPTION) return;
				
			File chosenFile = chooser.getSelectedFile();
			
			try {
				
				fileNamePic = chosenFile.toURI().toURL();
				
			} catch (Exception p) {
			
			}
			
			/*This replaces the image button with the image uploaded
			 * This allows the user to sample the image as it isn't resizable
			 */
			ImageIcon sample = new ImageIcon(fileNamePic);
			fileNamePicB.setIcon(sample);
			
		}if(e.getSource() == done){

			//A new Song is created and sent to the main
			Song newSong = new Song(name, artist, album, date, fileName, fileNamePic);
			
			main.addSong(newSong);
			main.show();
			
			//Everything is erased
			name = null;
			artist = null;
			album = null;
			date = null;
			fileName = null;
			fileNamePic = null;
			
			ImageIcon imageButton = new ImageIcon("images/image.jpg");
			fileNamePicB.setIcon(imageButton);
			
			this.dispose();//The input GUI is closed
			
		}if(e.getSource() == cancel){
			
			//Everything is erased
			name = null;
			artist = null;
			album = null;
			date = null;
			fileName = null;
			fileNamePic = null;
			
			main.show();
			this.dispose();//The input GUI is closed
			
		}
		
	}
	
}
