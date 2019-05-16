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
        StringBuilder finalText = new StringBuilder();
        String line;
        BufferedReader in = new BufferedReader(new FileReader(file));
        while((line = in.readLine()) != null){
            StringTokenizer st = new StringTokenizer(line);
            if(Integer.parseInt(st.nextToken())!=boxCode) {
                finalText.append(line).append("\n");
            }
        }
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(finalText.toString());
        in.close();
        out.close();
    }

    public String getText() throws IOException {
        StringBuilder text = new StringBuilder();
        String line;
        BufferedReader in = new BufferedReader(new FileReader(file));
        while((line = in.readLine()) != null){
            text.append(line).append("\n");
        }
        in.close();
        return text.toString();
    }
}

