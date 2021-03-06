/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WzorceSklep.Data.Pracownik;

import WzorceSklep.DAOFactory;
import WzorceSklep.Data.AdresOsoby;
import WzorceSklep.DataAccessObject;
import WzorceSklep.Util;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

/**
 *
 * @author Krzysztof
 */
public class DialogDodajPracownika extends javax.swing.JDialog 
{
    public static final String UMOWA_PLACEHOLDER = "Umowa";
    public static final String LOGIN_PLACEHOLDER = "Login";
    private JFormattedTextField.AbstractFormatter format;
    private Pracownik pracownik;
    public static final String MIEJSCOWOSC_PLACEHOLDER = "Miejscowość";
    public static final String IMIE_PLACEHOLDER = "Imię";
    public static final String MAIL_PLACEHOLDER = "Mail";
    public static final String KRAJ_PLACEHOLDER = "Kraj";
    public static final String ULICA_PLACEHOLDER = "Ulica";
    public static final String NAZWISKO_PLACEHOLDER = "Nazwisko";
    public static final String NR_LOKALU_PLACEHOLDER = "Nr lokalu";
    public static final String NR_DOMU_PLACEHOLDER = "Nr domu";
    public static final String POCZTA_PLACEHOLDER = "Poczta";
    public static final String TELEFON_PLACEHOLDER = "Telefon";

    private void utworzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utworzActionPerformed

        niezgodneHasla.setVisible(false);
        wypelnijPola.setVisible(false);
        pracownik = new Pracownik();
        pracownik.setAdres(new AdresOsoby());
        if (!imieField.getText().equals(IMIE_PLACEHOLDER)) {
            pracownik.setImie(imieField.getText());
        }
        pracownik.setNazwisko(nazwiskoField.getText());
        pracownik.getAdres().setKodPocztowy(kodPocztowyField.getText());
        if (!ulicaField.getText().equals(ULICA_PLACEHOLDER)) {
            pracownik.getAdres().setUlica(ulicaField.getText());
        }
        if (!miejscowoscField.getText().equals(MIEJSCOWOSC_PLACEHOLDER)) {
            pracownik.getAdres().setMiejscowosc(miejscowoscField.getText());
        }
        if (!pocztaField.getText().equals(POCZTA_PLACEHOLDER)) {
            pracownik.getAdres().setPoczta(pocztaField.getText());
        }
        else {
            pracownik.getAdres().setPoczta(pracownik.getAdres().getMiejscowosc());
        }
        pracownik.getAdres().setKraj(krajField.getText());
        pracownik.setLogin(loginField.getText());
        pracownik.setPassword(new String((hasloField.getPassword())));
        pracownik.setMail(mailField.getText());
        pracownik.setUmowa(umowaField.getText());
        pracownik.setStatusWithString(statusField.getSelectedItem().toString());

//        String[] inputStrings = new String[12];
//        inputStrings[0] = imieField.getText();
//        inputStrings[1] = nazwiskoField.getText();
//        inputStrings[2] = ulicaField.getText();
//        inputStrings[3] = kodPocztowyField.getText();
//        inputStr/ings[4] = pocztaField.getText();
//        inputStrings[5] = miejscowoscField.getText();
//        inputStrings[6] = krajField.getText();
//        inputStrings[7] = loginField.getText();
//        inputStrings[8] = new String(hasloField.getPassword());
//        inputStrings[9] = mailField.getText();
//        inputStrings[10] = umowaField.getText();
//        inputStrings[11] = statusField.getSelectedItem().toString();
        //System.out.println(inputStrings[7]);


//        Integer[] inputInts = new Integer[3];
        try {
            pracownik.setTelefon(telefonField.getText());
            if (!nrdomuField.getText().equals(NR_DOMU_PLACEHOLDER)) {
                pracownik.getAdres().setNrDomu(Integer.valueOf(nrdomuField.getText()));
            }
            if (!nrlokaluField.getText().equals(NR_LOKALU_PLACEHOLDER)) {
                pracownik.getAdres().setNrLokalu(Integer.valueOf(nrlokaluField.getText()));
            }
            DataAccessObject<Pracownik> dao = new DAOFactory().getPracownikDAO();
            dao.insert(pracownik);
//            inputInts[0] = new Integer(telefonField.getText());
//            inputInts[1] = new Integer(nrdomuField.getText());
//            inputInts[2] = new Integer(nrlokaluField.getText());
        } catch (Exception e) {
            wypelnijPola.setVisible(true);
            hasloField.setText("");
            powtorzField.setText("");
            e.printStackTrace();
            Util.showErrorDialog(this,e);
            return;
        }
        dispose();
/*        try {
            CallableStatement proc = connection.prepareCall("{call DodajPracownika(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            for (int i = 0; i < 12; i++) {
                //System.out.println(inputStrings[i]);
                if (inputStrings[i].equals("")) {
                    wypelnijPola.setVisible(true);
                    hasloField.setText("");
                    powtorzField.setText("");
                    return;
                }
                proc.setString(i + 1, inputStrings[i]);
            }
            for (int i = 0; i < 3; i++) {
                proc.setInt(i + 13, inputInts[i]);
            }

            if (!inputStrings[8].equals(new String(powtorzField.getPassword()))) {
                System.out.println("Hasło" + inputStrings[8] + " != " + powtorzField.getPassword().toString());
                niezgodneHasla.setVisible(true);
                hasloField.setText("");
                powtorzField.setText("");
                return;
            }

            proc.execute();
            proc.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dispose();
        }*/
    }//GEN-LAST:event_utworzActionPerformed

    /**
     * Creates new form DialogDodajPracownika
     */
    public DialogDodajPracownika(Frame parent)
    {
        super(parent, true);
        try
        {
            format = new MaskFormatter("##-###");
            //final OknoDodajPracownika thisframe=this;

            /*
            * addWindowListener(new WindowAdapter()
                {
                @Override
                    public void windowClosing(WindowEvent e)
                    {
                        //thisframe.setVisible(false);
                    }
                });*/
        }
        catch(ParseException e)
        {
            System.out.println("Proszę nie grzebać w konstruktorze DialogDodajPracownika");
        }
        initComponents();
        niezgodneHasla.setVisible(false);
        wypelnijPola.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        imieField = new javax.swing.JTextField();
        nazwiskoField = new javax.swing.JTextField();
        ulicaField = new javax.swing.JTextField();
        nrdomuField = new javax.swing.JTextField();
        nrlokaluField = new javax.swing.JTextField();
        kodLabel = new javax.swing.JLabel();
        kodPocztowyField = new javax.swing.JFormattedTextField(format);
        pocztaField = new javax.swing.JTextField();
        miejscowoscField = new javax.swing.JTextField();
        telefonField = new javax.swing.JTextField();
        mailField = new javax.swing.JTextField();
        krajField = new javax.swing.JTextField();
        umowaField = new javax.swing.JTextField();
        wypelnijPola = new javax.swing.JLabel();
        loginField = new javax.swing.JTextField();
        hasloLabel = new javax.swing.JLabel();
        hasloLabel1 = new javax.swing.JLabel();
        hasloField = new javax.swing.JPasswordField();
        powtorzField = new javax.swing.JPasswordField();
        niezgodneHasla = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        statusField = new javax.swing.JComboBox();
        utworz = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Vijaya", 0, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Dodawanie nowego pracownika");

        imieField.setText("Imię");
        imieField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                imieFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                imieFieldFocusLost(evt);
            }
        });

        nazwiskoField.setText("Nazwisko");
        nazwiskoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nazwiskoFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nazwiskoFieldFocusLost(evt);
            }
        });

        ulicaField.setText("Ulica");
        ulicaField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ulicaFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ulicaFieldFocusLost(evt);
            }
        });

        nrdomuField.setText("Nr domu");
        nrdomuField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nrdomuFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nrdomuFieldFocusLost(evt);
            }
        });

        nrlokaluField.setText("Nr lokalu");
        nrlokaluField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nrlokaluFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nrlokaluFieldFocusLost(evt);
            }
        });

        kodLabel.setText("Kod pocztowy:");

        kodPocztowyField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kodPocztowyFieldFocusGained(evt);
            }
        });

        pocztaField.setText("Poczta");
        pocztaField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pocztaFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pocztaFieldFocusLost(evt);
            }
        });

        miejscowoscField.setText("Miejscowość");
        miejscowoscField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                miejscowoscFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                miejscowoscFieldFocusLost(evt);
            }
        });

        telefonField.setText("Telefon");
        telefonField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                telefonFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                telefonFieldFocusLost(evt);
            }
        });

        mailField.setText("Mail");
        mailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mailFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mailFieldFocusLost(evt);
            }
        });

        krajField.setText("Kraj");
        krajField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                krajFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                krajFieldFocusLost(evt);
            }
        });

        umowaField.setText(UMOWA_PLACEHOLDER);
        umowaField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                umowaFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                umowaFieldFocusLost(evt);
            }
        });

        wypelnijPola.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        wypelnijPola.setForeground(new java.awt.Color(255, 0, 0));
        wypelnijPola.setText("Wprowadzone dane są niepoprawne lub niekompletne.");

        loginField.setText("Login");
        loginField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                loginFieldFocusGained(evt);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                loginFieldFocusLost(evt);
            }
        });

        hasloLabel.setText("Hasło:");

        hasloLabel1.setText("Powtórz hasło:");

        hasloField.setText("Hasło");
        hasloField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hasloFieldFocusGained(evt);
            }
        });

        powtorzField.setText("Hasło");
        powtorzField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                powtorzFieldFocusGained(evt);
            }
        });

        niezgodneHasla.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        niezgodneHasla.setForeground(new java.awt.Color(255, 0, 0));
        niezgodneHasla.setText("Podane hasła się nie zgadzają.");

        statusLabel.setText("Status:");

        statusField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "Sprzedawca" }));
        /*statusField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusFieldActionPerformed(evt);
            }
        });*/

        utworz.setText("Zapisz");
        utworz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utworzActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(imieField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(nazwiskoField, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(ulicaField)
                            .addGap(18, 18, 18)
                            .addComponent(nrdomuField, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(nrlokaluField, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(telefonField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
                                    .addComponent(mailField))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(kodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(kodPocztowyField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(pocztaField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(krajField, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(miejscowoscField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(umowaField, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(wypelnijPola))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(hasloField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(hasloLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(hasloLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(powtorzField)
                                .addComponent(niezgodneHasla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(249, 249, 249)
                            .addComponent(utworz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imieField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nazwiskoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ulicaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nrdomuField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nrlokaluField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kodPocztowyField)
                    .addComponent(pocztaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(miejscowoscField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefonField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(krajField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(umowaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wypelnijPola))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hasloLabel1)
                    .addComponent(hasloLabel))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasloField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(powtorzField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(niezgodneHasla)
                    .addComponent(statusLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(utworz))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imieFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_imieFieldFocusGained
        imieField.selectAll();
    }//GEN-LAST:event_imieFieldFocusGained

    private void imieFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_imieFieldFocusLost
        if (imieField.getText().equals("")) {
            imieField.setText(IMIE_PLACEHOLDER);
        }
    }//GEN-LAST:event_imieFieldFocusLost

    private void nazwiskoFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nazwiskoFieldFocusGained
        nazwiskoField.selectAll();
    }//GEN-LAST:event_nazwiskoFieldFocusGained

    private void nazwiskoFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nazwiskoFieldFocusLost
        if (nazwiskoField.getText().equals("")) {
            nazwiskoField.setText(NAZWISKO_PLACEHOLDER);
        }
    }//GEN-LAST:event_nazwiskoFieldFocusLost

    private void ulicaFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ulicaFieldFocusGained
        ulicaField.selectAll();
    }//GEN-LAST:event_ulicaFieldFocusGained

    private void ulicaFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ulicaFieldFocusLost
        if (ulicaField.getText().equals("")) {
            ulicaField.setText(ULICA_PLACEHOLDER);
        }
    }//GEN-LAST:event_ulicaFieldFocusLost

    private void nrdomuFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrdomuFieldFocusGained
        nrdomuField.selectAll();
    }//GEN-LAST:event_nrdomuFieldFocusGained

    private void nrdomuFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrdomuFieldFocusLost
        if (nrdomuField.getText().equals("")) {
            nrdomuField.setText(NR_DOMU_PLACEHOLDER);
        }
    }//GEN-LAST:event_nrdomuFieldFocusLost

    private void nrlokaluFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrlokaluFieldFocusGained
        nrlokaluField.selectAll();
    }//GEN-LAST:event_nrlokaluFieldFocusGained

    private void nrlokaluFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrlokaluFieldFocusLost
        if (nrlokaluField.getText().equals("")) {
            nrlokaluField.setText(NR_LOKALU_PLACEHOLDER);
        }
    }//GEN-LAST:event_nrlokaluFieldFocusLost

    private void kodPocztowyFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kodPocztowyFieldFocusGained
        kodPocztowyField.selectAll();
    }//GEN-LAST:event_kodPocztowyFieldFocusGained

    private void pocztaFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pocztaFieldFocusGained
        pocztaField.selectAll();
    }//GEN-LAST:event_pocztaFieldFocusGained

    private void pocztaFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pocztaFieldFocusLost
        if (pocztaField.getText().equals("")) {
            pocztaField.setText(POCZTA_PLACEHOLDER);
        }
    }//GEN-LAST:event_pocztaFieldFocusLost

    private void miejscowoscFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_miejscowoscFieldFocusGained
        miejscowoscField.selectAll();
    }//GEN-LAST:event_miejscowoscFieldFocusGained

    private void miejscowoscFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_miejscowoscFieldFocusLost
        if (miejscowoscField.getText().equals("")) {
            miejscowoscField.setText(MIEJSCOWOSC_PLACEHOLDER);
        }
    }//GEN-LAST:event_miejscowoscFieldFocusLost

    private void telefonFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonFieldFocusGained
        telefonField.selectAll();
    }//GEN-LAST:event_telefonFieldFocusGained

    private void telefonFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonFieldFocusLost
        if (telefonField.getText().equals("")) {
            telefonField.setText(TELEFON_PLACEHOLDER);
        }
    }//GEN-LAST:event_telefonFieldFocusLost

    private void mailFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mailFieldFocusGained
        mailField.selectAll();
    }//GEN-LAST:event_mailFieldFocusGained

    private void mailFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mailFieldFocusLost
        if (mailField.getText().equals("")) {
            mailField.setText(MAIL_PLACEHOLDER);
        }
    }//GEN-LAST:event_mailFieldFocusLost

    private void krajFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_krajFieldFocusGained
        krajField.selectAll();
    }//GEN-LAST:event_krajFieldFocusGained

    private void krajFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_krajFieldFocusLost
        if (krajField.getText().equals("")) {
            krajField.setText(KRAJ_PLACEHOLDER);
        }
    }//GEN-LAST:event_krajFieldFocusLost

    private void umowaFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_umowaFieldFocusGained
        umowaField.selectAll();
    }//GEN-LAST:event_umowaFieldFocusGained

    private void umowaFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_umowaFieldFocusLost
        if (umowaField.getText().equals("")) {
            umowaField.setText(UMOWA_PLACEHOLDER);
        }
    }//GEN-LAST:event_umowaFieldFocusLost

    private void loginFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginFieldFocusGained
        loginField.selectAll();
    }//GEN-LAST:event_loginFieldFocusGained

    private void loginFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginFieldFocusLost
        if (loginField.getText().equals("")) {
            loginField.setText(LOGIN_PLACEHOLDER);
        }
    }//GEN-LAST:event_loginFieldFocusLost

    private void hasloFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hasloFieldFocusGained
        hasloField.selectAll();
    }//GEN-LAST:event_hasloFieldFocusGained

    private void powtorzFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_powtorzFieldFocusGained
        powtorzField.selectAll();
    }//GEN-LAST:event_powtorzFieldFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DialogDodajPracownika.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogDodajPracownika.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogDodajPracownika.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogDodajPracownika.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        /*java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                DialogDodajPracownika dialog = new DialogDodajPracownika(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });*/
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField hasloField;
    private javax.swing.JLabel hasloLabel;
    private javax.swing.JLabel hasloLabel1;
    private javax.swing.JTextField imieField;
    private javax.swing.JLabel kodLabel;
    private javax.swing.JFormattedTextField kodPocztowyField;
    private javax.swing.JTextField krajField;
    private javax.swing.JTextField loginField;
    private javax.swing.JTextField mailField;
    private javax.swing.JTextField miejscowoscField;
    private javax.swing.JTextField nazwiskoField;
    private javax.swing.JLabel niezgodneHasla;
    private javax.swing.JTextField nrdomuField;
    private javax.swing.JTextField nrlokaluField;
    private javax.swing.JTextField pocztaField;
    private javax.swing.JPasswordField powtorzField;
    private javax.swing.JComboBox statusField;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextField telefonField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField ulicaField;
    private javax.swing.JTextField umowaField;
    private javax.swing.JButton utworz;
    private javax.swing.JLabel wypelnijPola;
    // End of variables declaration//GEN-END:variables
}
