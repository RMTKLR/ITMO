package Manager;

import java.io.*;

public class FileManager {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String path;

    public FileManager(String path){
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void write(String xml)  {
        try {

            bufferedWriter = new BufferedWriter(new FileWriter(path));
            bufferedWriter.write(xml);
            bufferedWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public String reader(){
        File file = new File(path);
        try{
            if (!file.exists()) {
              throw new RuntimeException("file doesn't exit!!!");
            }
            bufferedReader = new BufferedReader(new FileReader(path));
            String data = "";
            int i ;
            while((i = bufferedReader.read()) != -1){
                data += (char)i;
            }
            bufferedReader.close();
            return data;

        }catch (IOException ioException){
            System.out.println(ioException.getMessage());
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    return "";}
}
