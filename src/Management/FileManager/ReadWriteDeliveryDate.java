package Management.FileManager;

import java.io.*;
import java.util.StringTokenizer;

public class ReadWriteDeliveryDate {
    private File file;

    public ReadWriteDeliveryDate(){
         file = new File("src/Management/Archive/DeliveryDate");
    }

    /*
     *  insertText: aggiunge al file DeliveryDate la stringa passata come argomento.
     *  removeText: rimuove dal file DeliveryDate la riga contenente il packID passato
     *  come argomento.
     *  getText: restituisce il testo del file DeliveryDate.
     */

    public void insertText(String text) throws IOException {
        String finalText = getText();
        finalText += text;
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(finalText);
        out.close();
    }

    public void removeText(String packID) throws IOException {
        StringBuilder finalText = new StringBuilder();
        String line;
        BufferedReader in = new BufferedReader(new FileReader(file));
        while((line = in.readLine()) != null){
            StringTokenizer st = new StringTokenizer(line);
            st.nextToken();
            if(!st.nextToken().equals(packID)) {
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

