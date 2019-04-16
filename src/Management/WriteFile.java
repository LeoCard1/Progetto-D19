package Management;

import java.io.*;

public class WriteFile {

    public WriteFile(String path, String text) throws IOException {
        String finalText = "";
        String line;
        File file = new File(path);
        BufferedReader in = new BufferedReader(new FileReader(file));
        while((line = in.readLine()) != null){
            finalText+= line + "\n";
        }
        finalText += text;
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(finalText);
        in.close();
        out.close();
    }
}

