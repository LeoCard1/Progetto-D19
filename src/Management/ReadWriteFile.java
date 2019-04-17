package Management;

import java.io.*;

public class ReadWriteFile {

    File file;

    public ReadWriteFile(String path){
         file = new File(path);
    }

    public void insertText(String text) throws IOException {
        String finalText = readText();
        finalText += text;
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(finalText);
        out.close();
    }

    public void removeText(String text) throws IOException{
        String finalText = "";
        String line;
        BufferedReader in = new BufferedReader(new FileReader(file));
        while((line = in.readLine()) != null){
            if(!line.equals(text)) {
                finalText += line + "\n";
            }
        }
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(finalText);
        in.close();
        out.close();
    }

    public String readText() throws IOException {
        String text = "";
        String line;
        BufferedReader in = new BufferedReader(new FileReader(file));
        while((line = in.readLine()) != null){
            text+= line + "\n";
        }
        in.close();
        return text;
    }
}

