

package dateirechercheversion2;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Dateirechercheversion2 extends Application{
    private String untersuchenderOrdner;
   private ArrayList<File> files= new ArrayList<>();
 @Override
    public void start(final Stage primaryStage) {
      
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = directoryChooser.showDialog(null);
                untersuchenderOrdner =(selectedDirectory.getAbsolutePath());
                System.out.println(untersuchenderOrdner);
                
                
                untersuche(new File(untersuchenderOrdner));
                 printAndWriteFiles();
                 
            
    }
  
        private void untersuche(File file) {
         
         if (file.isFile()){
          check(file);
            }
         else if (file.isDirectory()) {   
             for (File f : file.listFiles()){
                 untersuche(f);
             }
            }
            
        
            
        
        }

          public void check(File f){
          //Pattern p = Pattern.compile( ".*Windows.*fonts.*\\.(ttc|ttf|otf)" );
          //Test so alle pptx Dateien
           Pattern p = Pattern.compile( ".*\\.pptx" );
            Matcher m = p.matcher( f.getAbsolutePath() );   
            
          if(m.matches()){
              System.out.println("true");
              files.add(f);
          }
          }  
        
    public static void main(String[] args) {
      
  Application.launch(args);
    }//methode
  

private void printAndWriteFiles() {
             final File textFile = new File( "C:\\temp\\a3_out.txt" );
             try{
                PrintWriter printWriter =new PrintWriter(textFile);
		final DecimalFormat formatter = new DecimalFormat( "###,###,###,###,###,###,###,###,###" ); 
		
	  	System.out.printf( "analyzing: %s\n\n", untersuchenderOrdner );

	    	System.out.printf( "=======================\n" );
	    	
	    	printLayout( printWriter );
	    	for( final File File : files) {
	    		final String length = formatter.format( File.length() );
	    		final String path = File.getAbsolutePath();
	    		System.out.printf( "%35s Bytes	%s\n", length, path );
	    	
	    	}//for        	   	
	    	
	    	System.out.printf( "\n\n\n" );
	    
	    	printWriter.flush(); 
	    	
	} catch( final IOException e ) {
		e.printStackTrace();
	}//catch 	
		
}//method()

private void printLayout( final PrintWriter printWriter ) {
    	
    	final String[][] layout = { { " "," ","Y"," "," ", " ", "Z"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," " },
				 { " "," ","o"," "," ", " ", "e"," "," "," "," "," "," "," ","P"," "," "," ","T"," "," "," ","G"," "," "," ","M"," "," "," ","K"," "," "," ","B" },
				 { " "," ","t"," "," ", " ", "t"," "," "," ","E"," "," "," ","e"," "," "," ","e"," "," "," ","i"," "," "," ","e"," "," "," ","i"," "," "," ","y" },
				 { " "," ","t"," "," ", " ", "t"," "," "," ","x"," "," "," ","t"," "," "," ","r"," "," "," ","g"," "," "," ","g"," "," "," ","l"," "," "," ","t" },
				 { ".",".","a"," ",".", ".", "a"," ",".",".","a"," ",".",".","a"," ",".",".","a"," ",".",".","a"," ",".",".","a"," ",".",".","0"," ",".",".","e" }
    	};//String[]
   	
	   for( int i = 0; i < layout.length; i++ ) {
	   	for( int j = 0; j < layout[i].length; j++ ) {
	   		System.out.print( layout[i][j] );
	   	}//for
	   	System.out.printf( "\n" );
	   }//for
	   	
}//method();
}
