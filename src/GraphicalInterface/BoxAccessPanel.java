package GraphicalInterface;

import PickupPointSystem.PickupPoint;

import javax.swing.*;

public class BoxAccessPanel extends JPanel {
    private PickupPoint piPo;

    public BoxAccessPanel(PickupPoint pipo) {
        piPo = pipo;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        initPanel();
    }

    private void initPanel() {
        add(languagePanel());
        add(new InsertCodePanel(piPo));
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
