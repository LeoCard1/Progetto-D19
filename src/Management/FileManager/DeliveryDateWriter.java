package Management.FileManager;

import java.io.*;
import java.util.StringTokenizer;

public class DeliveryDateWriter {
    private File file;

    public DeliveryDateWriter(){
         file = new File("src/Archive/DeliveryDate");
    }

    public void insertText(String text) throws IOException {
        String finalText = getText();
        finalText += text;
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(finalText);
        out.close();
    }

    public void removeText(int boxCode) throws IOException {
        String finalText = "";
        String line;
        BufferedReader in = new BufferedReader(new FileReader(file));
        while((line = in.readLine()) != null){
            StringTokenizer st = new StringTokenizer(line);
            if(Integer.parseInt(st.nextToken())!=boxCode) {
                finalText += line + "\n";
            }
        }
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(finalText);
        in.close();
        out.close();
    }

    public String getText() throws IOException {
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

