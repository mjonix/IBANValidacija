package ibanvalidacija;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class GrafineSasaja extends JFrame {

    private Validatorius validatorius = new Validatorius();

    GrafineSasaja() {
        super("IBAN Validatorius");
        setSize(700, 250);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JButton ivestiIban = new JButton("Įvesti IBAN kodą");
        ivestiIban.setLocation(240, 80);
        ivestiIban.setSize(220, 25);
        add(ivestiIban);

        final JButton nuskaitytiIsFailo = new JButton("Nuskaityti IBAN kodus iš failo");
        nuskaitytiIsFailo.setLocation(240, 120);
        nuskaitytiIsFailo.setSize(220, 25);
        add(nuskaitytiIsFailo);

        final JButton validuotiIban = new JButton("Validuoti");
        validuotiIban.setLocation(250, 120);
        validuotiIban.setSize(200, 25);
        add(validuotiIban);

        final JButton validuotiFaila = new JButton("Validuoti");
        validuotiFaila.setLocation(250, 120);
        validuotiFaila.setSize(200, 25);
        add(validuotiFaila);

        final JLabel informacija = new JLabel("");
        informacija.setLocation(200, 60);
        informacija.setSize(600, 15);
        add(informacija);

        final JTextField ibanLaukas = new JTextField();
        ibanLaukas.setLocation(250, 90);
        ibanLaukas.setSize(200, 25);
        ibanLaukas.setToolTipText("Validuojamo IBAN kodas");
        add(ibanLaukas);

        final JTextField failoPavadinimoLaukas = new JTextField();
        failoPavadinimoLaukas.setLocation(250, 90);
        failoPavadinimoLaukas.setSize(200, 25);
        failoPavadinimoLaukas.setToolTipText("Failo su validuojamais IBAN kodais lokacija ir pavadinimas");
        add(failoPavadinimoLaukas);

        ibanLaukas.setVisible(false);
        failoPavadinimoLaukas.setVisible(false);
        validuotiIban.setVisible(false);
        validuotiFaila.setVisible(false);

        ivestiIban.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ibanLaukas.setVisible(true);
                validuotiIban.setVisible(true);
                nuskaitytiIsFailo.setVisible(true);
                validuotiFaila.setVisible(false);
                ivestiIban.setVisible(false);
                failoPavadinimoLaukas.setVisible(false);
                nuskaitytiIsFailo.setLocation(370, 180);
                informacija.setText("Prašome įvesti IBAN kodą, kurį norite validuoti.");
                failoPavadinimoLaukas.setText("");
            }
        });

        nuskaitytiIsFailo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                failoPavadinimoLaukas.setVisible(true);
                validuotiFaila.setVisible(true);
                ivestiIban.setVisible(true);
                validuotiIban.setVisible(false);
                nuskaitytiIsFailo.setVisible(false);
                ibanLaukas.setVisible(false);
                ivestiIban.setLocation(370, 180);
                informacija.setText("Nurodykite kelią iki failo bei failo pavadinimą.");
                ibanLaukas.setText("");
            }
        });

        validuotiIban.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validuotiIban.setVisible(false);
                ibanLaukas.setVisible(false);
                ivestiIban.setVisible(true);
                ivestiIban.setLocation(370, 150);
                informacija.setText(ibanLaukas.getText() + ";" + validatorius.validuotiKoda(ibanLaukas.getText()));
            }
        });

        validuotiFaila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validuotiFaila.setVisible(false);
                failoPavadinimoLaukas.setVisible(false);
                nuskaitytiIsFailo.setVisible(true);
                ivestiIban.setLocation(370, 150);
                nuskaitytiIsFailo.setLocation(370, 180);
                informacija.setText(validatorius.validuotiIsFailo(failoPavadinimoLaukas.getText()));
            }
        });
    }
}
