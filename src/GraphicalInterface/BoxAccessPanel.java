package GraphicalInterface;

import javax.swing.*;
import java.awt.*;

public class BoxAccessPanel extends JPanel {
    public BoxAccessPanel() {
        setLayout(new BorderLayout());
        initPanel();
    }

    private void initPanel() {
        add(languagePanel());
    }

    private JPanel languagePanel() {
        JPanel langPan = new JPanel();
        langPan.setLayout(new BoxLayout(langPan, BoxLayout.PAGE_AXIS));

        /*
        Si potrebbe creare una classe Language in cui
        inserire per ogni linguaggio le varie scritte
        da mettere nel pannello e altre funzioni.
        Sarebbe anche comodo da gestire con un for-each.
         */

        /*
        La parte seguente è da fare meglio, possibilmente
        con la classe menzionata sopra.
         */

        JPanel panBox = new JPanel();

        JComboBox langBox = new JComboBox();
        langBox.addItem("English");
        langBox.addItem("Italian");

        panBox.add(new JLabel("Language:"));
        panBox.add(langBox);
        langPan.add(panBox);

        return langPan;
    }
}
