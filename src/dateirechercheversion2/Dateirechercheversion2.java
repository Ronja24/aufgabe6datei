

package dateirechercheversion2;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Dateirechercheversion2 extends Application{
    private String untersuchenderOrdner;
    private int anzd;
    private int anzO;
    private FilewithL[] dirs;
    private FilewithL[] files;
    long laenge= 0; 
 @Override
    public void start(final Stage primaryStage) {
      
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = directoryChooser.showDialog(null);
                untersuchenderOrdner =(selectedDirectory.getAbsolutePath());
                System.out.println(untersuchenderOrdner);
              
                
                Scanner scanner = new Scanner(System.in);
                System.out.println("Anzahl Ordner:" );
                String anzOrdner = scanner.next();
                anzO = Integer.parseInt(anzOrdner);
                dirs =new FilewithL[anzO];
                
                System.out.println("Anzahl Dateien:");
                String anzDateien = scanner.next();
                anzd = Integer.parseInt(anzDateien);
                files=new FilewithL[anzd];
                
                
                long verzeichnisgrosse=untersuche(new File(untersuchenderOrdner));
                 printAndWriteFiles(new File(untersuchenderOrdner));
                 
            
    }
  
        private long untersuche(File file) {
         
         if (file.isFile()){
           FilewithL fil = new FilewithL(file, file.getAbsolutePath(),file.length());
             checkfullArrayandsort(fil, files);
            return file.length();
            }
         else if (file.isDirectory()) { 
              laenge=0;
             for (File f : file.listFiles()){
                 laenge +=untersuche(f);
             }
             FilewithL dir =new FilewithL(file,file.getAbsolutePath(),laenge);
               checkfullArrayandsort(dir, dirs);
            }
            
        
            
         return laenge;
        }

            
        
        public void checkfullArrayandsort(FilewithL temp, FilewithL[] arr){
         if(arr[arr.length-1] == null){
             int m=0;
            while(arr[m] !=null){
            m++;
            }
         arr[m]=temp;
         
         }
         else{
                if(arr[arr.length-1].getLength()< temp.getLength()){
                arr[arr.length-1]=temp;
                }
         }
        if(arr[arr.length-1] != null){
        	FilewithL tem;
		for(int n=1; n<arr.length; n++) {
			for(int j=arr.length-1; j>0; j--) {
				if(arr[j].getLength()>arr[j-1].getLength()) {
					tem=arr[j];
					arr[j]=arr[j-1];
					arr[j-1]=tem;
				}
				
			}

		
                }
        }
        }
        
    public static void main(String[] args) {
      
  Application.launch(args);
    }//methode
  

private void printAndWriteFiles( final File directory ) {
		
	final File textFile = new File( "C:\\temp\\a3_out.txt" );//Speierort? Reicht Dateinmane wenn ja wo landet er?
	
	try (
		final PrintWriter printWriter= new PrintWriter( textFile );
	){ 
		
		final DecimalFormat formatter = new DecimalFormat( "###,###,###,###,###,###,###,###,###" ); 
		
	  	System.out.printf( "analyzing: %s\n\n", directory.getAbsolutePath() );
   		printWriter.printf( "analyzing: %s\r\n\r\n", directory.getAbsolutePath() );
	    	
	    	System.out.printf( "The top %d directories:\n", anzO);
	    	printWriter.printf( "The top %d directories:\r\n", anzO );
	    	System.out.printf( "=======================\n" );
	    	printWriter.printf( "=======================\r\n" );
	    	
	    	printLayout( printWriter );
	    	
	    	for( final FilewithL dir : dirs) {
	    		final String length = formatter.format( dir.getLength() );
	    		final String path = dir.getpath();
	    		System.out.printf( "%35s Bytes	%s\n", length, path );
	    		printWriter.printf( "%35s Bytes	%s\r\n", length, path );
	    	}//for        	   	
	    	
	    	System.out.printf( "\n\n\n" );
	    	printWriter.printf( "\r\n\r\n\r\n" );        	
	    	
	    	System.out.printf( "The top %d files:\n", anzd );
	    	printWriter.printf( "The top %d files:\r\n",anzd);
	    	System.out.printf( "=================\n" );
	    	printWriter.printf( "=================\r\n" );
	    	
	    	printLayout( printWriter );
	    	
	    	for( final FilewithL file : files ) {
	    		final String length = formatter.format( file.getLength() );
	    		final String path = file.getpath();
	    		System.out.printf( "%35s Bytes	%s\n", length, path );
	    		printWriter.printf( "%35s Bytes	%s\r\n", length, path );
	    	}
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
	   		printWriter.print( layout[i][j] );
	   	}//for
	   	System.out.printf( "\n" );
	   	printWriter.printf( "\r\n" );
	   }//for
	   	
}//method();
}
