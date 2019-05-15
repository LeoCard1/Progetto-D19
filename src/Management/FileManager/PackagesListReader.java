package Management.FileManager;

import ObserverPattern.Observer;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PackagesListReader {
    private File file;
    private ArrayList<Observer> observers = new ArrayList<>();

    public PackagesListReader(){
        file = new File("src/Archive/PackagesList");
    }

    public String getLineFromID(String id) throws IOException {
        String text = getText();
        StringTokenizer st1 = new StringTokenizer(text, "\n");
        while(st1.hasMoreTokens()){
            String riga = st1.nextToken();
            StringTokenizer st2 = new StringTokenizer(riga,"    ");
            if(st2.nextToken().equals(id)){
                return riga;
            }
        }
        return null;
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

    public void insertText(String text) throws IOException {
        String finalText = getText();
        finalText += text;
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(finalText);
        out.close();
        notifyObservers();
    }

    public void removeText(String id) throws IOException {
        String finalText = "";
        String line;
        String lineToRemove = getLineFromID(id);
        BufferedReader in = new BufferedReader(new FileReader(file));
        while((line = in.readLine()) != null){
            if(!line.equals(lineToRemove)){
                finalText+=line+"\n";
            }
        }
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(finalText);
        in.close();
        out.close();
        notifyObservers();
    }

    public void addObserver(Observer ob){
        observers.add(ob);
    }

    public void notifyObservers(){
        for(Observer ob : observers){
            ob.update();
        }
    }
}
