//a class for writing a string passed to it

import java.io.File;
import java.io.FileWriter;

public class FileWriting {
    
    private String path;
    private String content;
    
    //the constructor get the string that is the path of output file
    public FileWriting(String s){
        setPath(s);
    }
    
    //setting the path of output file
    private void setPath(String s){
        path = s;
    }
    
    //setting the content which should be write in file
    public void setContent(String c){
        //System.out.println(c);
        content = c;
        writeFile();
    }
    
    //writes the content on output file
    private void writeFile(){
        FileWriter fileWriter;
        File file = new File(path);
        try{
            fileWriter = new FileWriter(file, true);
            fileWriter.write(content);
            fileWriter.close();
        }catch(Exception ex){
            System.out.println("An Error occured while writing the file");
        }
    }
}
