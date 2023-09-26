package fileManager;

import java.io.*;

public class FileManager {
    private BufferedWriter writer;
    private BufferedReader reader;
    private String path;

    public FileManager(){}

    public FileManager(String path){
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    public boolean write(String xml){
      boolean success= true;
      try {
          writer = new BufferedWriter(new FileWriter(path));
          writer.write(xml);
          writer.close();
      }catch (IOException exception){
          System.err.println("file exception ");
          success =false;
      }
      return success;
    }
    public String read(){
      File file = new File(path);
      if(!file.exists()) System.out.println("File doesn't excists!!!");
        try {
            reader = new BufferedReader(new FileReader(path));
            String data="";
            while(reader.ready()){
                data+=reader.readLine();
            }
            reader.close();
            return data;
        }catch(IOException exception){
            throw new RuntimeException();
        }
    }
}
