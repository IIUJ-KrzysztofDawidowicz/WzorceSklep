package WzorceSklep.Data.Hurtownia;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import WzorceSklep.DAOFactory;
import WzorceSklep.GUI.Admin;
import WzorceSklep.Util;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 *
 * @author KrzysztofD
 */
public class EdytujDostawceDialog extends javax.swing.JDialog {
    private JFormattedTextField.AbstractFormatter format;
    private Hurtownia hurtownia;
    private final DAOFactory daoFactory;

    /**
     * Creates new form EdytujDostawceDialog
     */
    public EdytujDostawceDialog(Frame parent, int idHurtowni, DAOFactory daoFactory) {
        super(parent, true);
        this.daoFactory = daoFactory;
        initComponents();
        nieWypelnioneLabel.setVisible(false);
        initHurtownia(idHurtowni);
    }

    private void initHurtownia(int id) {
        try {
            hurtownia = daoFactory.getHurtowniaDAO().getById(id);
            Nazwa1.setText(hurtownia.getNazwa());
            Kontakt1.setText(hurtownia.getOsobaKontaktowa());
            telefonField1.setText(hurtownia.getTelefon());
            mailField1.setText(hurtownia.getMail());
            AdresHurtowni adres = hurtownia.getAdres();
            ulicaField1.setText(adres.getUlica());
            if (adres.getNrDomu()!=null) {
                nrLokalu1.setText(adres.getNrDomu().toString());
            }
            kodPocztowyField1.setText(adres.getKodPocztowy());
            miejscowoscField1.setText(adres.getMiejscowosc());
            pocztaField1.setText(adres.getPoczta());
            krajField1.setText(adres.getKraj());

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */


    private void loadDataFromTextFields() {
        hurtownia.setNazwa(Nazwa1.getText());
        hurtownia.setOsobaKontaktowa(Kontakt1.getText());
        hurtownia.setMail(mailField1.getText());
        if (!ulicaField1.getText().equals("")) {
            hurtownia.getAdres().setUlica(ulicaField1.getText());
        }
        hurtownia.getAdres().setKodPocztowy(kodPocztowyField1.getText());
        if (!pocztaField1.getText().equals("")) {
            hurtownia.getAdres().setPoczta(pocztaField1.getText());
        }
        hurtownia.getAdres().setMiejscowosc(miejscowoscField1.getText());
        hurtownia.getAdres().setKraj(krajField1.getText());
    }

    private boolean nieWypelnione() {
        return Nazwa1.getText().equals("") || Kontakt1.getText().equals("") || !kodPocztowyField1.isEditValid() || miejscowoscField1.getText().equals("") || krajField1.getText().equals("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kodPocztowyField = new javax.swing.JFormattedTextField(format);
        Nazwa = new javax.swing.JTextField();
        kodLabel = new javax.swing.JLabel();
        pocztaField = new javax.swing.JTextField();
        titleLabel = new javax.swing.JLabel();
        ulicaField = new javax.swing.JTextField();
        mailField = new javax.swing.JTextField();
        Kontakt = new javax.swing.JTextField();
        miejscowoscField = new javax.swing.JTextField();
        telefonField = new javax.swing.JTextField();
        krajField = new javax.swing.JTextField();
        nrLokalu = new javax.swing.JTextField();
        utworz = new javax.swing.JButton();
        kodPocztowyField1 = new javax.swing.JFormattedTextField(format);
        Nazwa1 = new javax.swing.JTextField();
        kodLabel1 = new javax.swing.JLabel();
        pocztaField1 = new javax.swing.JTextField();
        titleLabel1 = new javax.swing.JLabel();
        ulicaField1 = new javax.swing.JTextField();
        mailField1 = new javax.swing.JTextField();
        Kontakt1 = new javax.swing.JTextField();
        miejscowoscField1 = new javax.swing.JTextField();
        telefonField1 = new javax.swing.JTextField();
        krajField1 = new javax.swing.JTextField();
        nrLokalu1 = new javax.swing.JTextField();
        utworz1 = new javax.swing.JButton();
        UsunButton = new javax.swing.JButton();
        nieWypelnioneLabel = new javax.swing.JLabel();

        kodPocztowyField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kodPocztowyFieldFocusGained(evt);
            }
        });

        Nazwa.setText("Nazwa");
        Nazwa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                NazwaFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                NazwaFocusGained(evt);
            }
        });

        kodLabel.setText("Kod pocztowy:");

        pocztaField.setText("Poczta");
        pocztaField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pocztaFieldFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                pocztaFieldFocusGained(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Vijaya", 0, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Dodawanie nowej hurtowni");

        ulicaField.setText("Ulica");
        ulicaField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ulicaFieldFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                ulicaFieldFocusGained(evt);
            }
        });

        mailField.setText("Mail");
        mailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                mailFieldFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                mailFieldFocusGained(evt);
            }
        });

        Kontakt.setText("Osoba kontaktowa");
        Kontakt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                KontaktFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                KontaktFocusGained(evt);
            }
        });

        miejscowoscField.setText("Miejscowość");
        miejscowoscField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                miejscowoscFieldFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                miejscowoscFieldFocusGained(evt);
            }
        });

        telefonField.setText("Telefon");
        telefonField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                telefonFieldFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                telefonFieldFocusGained(evt);
            }
        });

        krajField.setText("Kraj");
        krajField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                krajFieldFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                krajFieldFocusGained(evt);
            }
        });

        nrLokalu.setText("Nr lokalu");
        nrLokalu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nrLokaluFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                nrLokaluFocusGained(evt);
            }
        });

        utworz.setText("Dodaj");
        utworz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utworzActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        kodPocztowyField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kodPocztowyField1FocusGained(evt);
            }
        });

        Nazwa1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Nazwa1FocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                Nazwa1FocusGained(evt);
            }
        });

        kodLabel1.setText("Kod pocztowy:");

        pocztaField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pocztaField1ActionPerformed(evt);
            }
        });
        pocztaField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pocztaField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pocztaField1FocusLost(evt);
            }
        });

        titleLabel1.setFont(new java.awt.Font("Vijaya", 0, 24)); // NOI18N
        titleLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel1.setText("Edytowanie danych dostawcy");

        ulicaField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ulicaField1FocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                ulicaField1FocusGained(evt);
            }
        });

        mailField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mailField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mailField1FocusLost(evt);
            }
        });

        Kontakt1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Kontakt1FocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                Kontakt1FocusGained(evt);
            }
        });

        miejscowoscField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                miejscowoscField1FocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                miejscowoscField1FocusGained(evt);
            }
        });

        telefonField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                telefonField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                telefonField1FocusLost(evt);
            }
        });

        krajField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                krajField1FocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                krajField1FocusGained(evt);
            }
        });

        nrLokalu1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nrLokalu1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nrLokalu1FocusLost(evt);
            }
        });

        utworz1.setText("Zapisz");
        utworz1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utworz1ActionPerformed(evt);
            }
        });

        UsunButton.setText("Kasuj");
        UsunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsunButtonActionPerformed(evt);
            }
        });

        nieWypelnioneLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nieWypelnioneLabel.setForeground(new java.awt.Color(255, 51, 51));
        nieWypelnioneLabel.setText("Niepoprawne lub niekompletne dane - popraw!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(331, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(UsunButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(360, 360, 360))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(nieWypelnioneLabel)
                        .addGap(102, 102, 102))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(99, 99, 99)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titleLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Nazwa1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Kontakt1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ulicaField1)
                                .addGap(18, 18, 18)
                                .addComponent(nrLokalu1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(telefonField1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(mailField1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(kodLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(kodPocztowyField1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(pocztaField1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(krajField1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(miejscowoscField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(374, 374, 374)
                                .addComponent(utworz1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGap(100, 100, 100)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(276, Short.MAX_VALUE)
                .addComponent(nieWypelnioneLabel)
                .addGap(49, 49, 49)
                .addComponent(UsunButton)
                .addGap(70, 70, 70))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(71, 71, 71)
                    .addComponent(titleLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Nazwa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Kontakt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ulicaField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nrLokalu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(kodLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(kodPocztowyField1)
                        .addComponent(pocztaField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(miejscowoscField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(telefonField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mailField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(krajField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(72, 72, 72)
                    .addComponent(utworz1)
                    .addGap(71, 71, 71)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kodPocztowyFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kodPocztowyFieldFocusGained
        kodPocztowyField.selectAll();
    }//GEN-LAST:event_kodPocztowyFieldFocusGained

    private void NazwaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NazwaFocusLost
//        if (Nazwa.getText().equals("")) {
//            Nazwa.setText(hurtownia.getNazwa());
//        }
    }//GEN-LAST:event_NazwaFocusLost

    private void NazwaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NazwaFocusGained
        Nazwa.selectAll();
    }//GEN-LAST:event_NazwaFocusGained

    private void pocztaFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pocztaFieldFocusLost
//        if (pocztaField.getText().equals("")) {
//            pocztaField.setText(hurtownia.getAdres().getPoczta());
//        }
    }//GEN-LAST:event_pocztaFieldFocusLost

    private void pocztaFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pocztaFieldFocusGained
        pocztaField.selectAll();
    }//GEN-LAST:event_pocztaFieldFocusGained

    private void ulicaFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ulicaFieldFocusLost
//        if (ulicaField.getText().equals("")) {
//            ulicaField.setText(hurtownia.getAdres().getUlica());
//        }
    }//GEN-LAST:event_ulicaFieldFocusLost

    private void ulicaFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ulicaFieldFocusGained
        ulicaField.selectAll();
    }//GEN-LAST:event_ulicaFieldFocusGained

    private void mailFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mailFieldFocusLost
//        if (mailField.getText().equals("")) {
//            mailField.setText(hurtownia.getMail());
//        }
    }//GEN-LAST:event_mailFieldFocusLost

    private void mailFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mailFieldFocusGained
        mailField.selectAll();
    }//GEN-LAST:event_mailFieldFocusGained

    private void KontaktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_KontaktFocusLost
//        if (Kontakt.getText().equals("")) {
//            Kontakt.setText(hurtownia.getOsobaKontaktowa());
//        }
    }//GEN-LAST:event_KontaktFocusLost

    private void KontaktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_KontaktFocusGained
        Kontakt.selectAll();
    }//GEN-LAST:event_KontaktFocusGained

    private void miejscowoscFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_miejscowoscFieldFocusLost
//        if (miejscowoscField.getText().equals("")) {
//            miejscowoscField.setText(hurtownia.getAdres().getMiejscowosc());
//        }
    }//GEN-LAST:event_miejscowoscFieldFocusLost

    private void miejscowoscFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_miejscowoscFieldFocusGained
        miejscowoscField.selectAll();
    }//GEN-LAST:event_miejscowoscFieldFocusGained

    private void telefonFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonFieldFocusLost
//        if (telefonField.getText().equals("")) {
//            telefonField.setText(hurtownia.getTelefon());
//        }
    }//GEN-LAST:event_telefonFieldFocusLost

    private void telefonFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonFieldFocusGained
        telefonField.selectAll();
    }//GEN-LAST:event_telefonFieldFocusGained

    private void krajFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_krajFieldFocusLost
//        if (krajField.getText().equals("")) {
//            krajField.setText("Kraj");
//        }
    }//GEN-LAST:event_krajFieldFocusLost

    private void krajFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_krajFieldFocusGained
        krajField.selectAll();
    }//GEN-LAST:event_krajFieldFocusGained

    private void nrLokaluFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrLokaluFocusLost
//        if (nrLokalu.getText().equals("")) {
//            nrLokalu.setText("Nr lokalu");
//        }
    }//GEN-LAST:event_nrLokaluFocusLost

    private void nrLokaluFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrLokaluFocusGained
        nrLokalu.selectAll();
    }//GEN-LAST:event_nrLokaluFocusGained

    private void utworzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utworzActionPerformed

        loadDataFromTextFields();

        //System.out.println(inputStrings[7]);

        try {
            hurtownia.setTelefon(telefonField.getText());
            hurtownia.getAdres().setNrDomu(Integer.valueOf(nrLokalu.getText()));
            daoFactory.getHurtowniaDAO().update(hurtownia);
        } catch (Exception e) {
//            wypelnijPola.setVisible(true);
            e.printStackTrace();
            Util.showErrorDialog(this, e);
            return;
        }
        dispose();
        /*        try {
            CallableStatement proc = connection.prepareCall("{call DodajHurtownie(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            for (int i = 0; i < 8; i++) {
                //System.out.println(inputStrings[i]);
                if (inputStrings[i].equals("")) {
                    wypelnijPola.setVisible(true);
                    return;
                }
                proc.setString(i + 1, inputStrings[i]);
            }
            for (int i = 0; i < 2; i++) {
                proc.setInt(i + 9, inputInts[i]);
            }

            proc.execute();
            proc.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dispose();
        }*/
    }//GEN-LAST:event_utworzActionPerformed

    private void kodPocztowyField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kodPocztowyField1FocusGained
        kodPocztowyField.selectAll();
    }//GEN-LAST:event_kodPocztowyField1FocusGained

    private void Nazwa1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Nazwa1FocusLost
//        if (Nazwa.getText().equals("")) {
//            Nazwa.setText("ImiÄ™");
//        }
    }//GEN-LAST:event_Nazwa1FocusLost

    private void Nazwa1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Nazwa1FocusGained
        Nazwa.selectAll();
    }//GEN-LAST:event_Nazwa1FocusGained

    private void pocztaField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pocztaField1FocusLost
//        if (pocztaField.getText().equals("")) {
//            pocztaField.setText("Poczta");
//        }
    }//GEN-LAST:event_pocztaField1FocusLost

    private void pocztaField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pocztaField1FocusGained
        pocztaField.selectAll();
    }//GEN-LAST:event_pocztaField1FocusGained

    private void ulicaField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ulicaField1FocusLost
//        if (ulicaField.getText().equals("")) {
//            ulicaField.setText("Ulica");
//        }
    }//GEN-LAST:event_ulicaField1FocusLost

    private void ulicaField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ulicaField1FocusGained
        ulicaField.selectAll();
    }//GEN-LAST:event_ulicaField1FocusGained

    private void mailField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mailField1FocusLost
//        if (mailField.getText().equals("")) {
//            mailField.setText("Mail");
//        }
    }//GEN-LAST:event_mailField1FocusLost

    private void mailField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mailField1FocusGained
        mailField.selectAll();
    }//GEN-LAST:event_mailField1FocusGained

    private void Kontakt1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Kontakt1FocusLost
//        if (Kontakt.getText().equals("")) {
//            Kontakt.setText("Nazwisko");
//        }
    }//GEN-LAST:event_Kontakt1FocusLost

    private void Kontakt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Kontakt1FocusGained
        Kontakt.selectAll();
    }//GEN-LAST:event_Kontakt1FocusGained

    private void miejscowoscField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_miejscowoscField1FocusLost
//        if (miejscowoscField.getText().equals("")) {
//            miejscowoscField.setText("MiejscowoĹ›Ä‡");
//        }
    }//GEN-LAST:event_miejscowoscField1FocusLost

    private void miejscowoscField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_miejscowoscField1FocusGained
        miejscowoscField.selectAll();
    }//GEN-LAST:event_miejscowoscField1FocusGained

    private void telefonField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonField1FocusLost
//        if (telefonField.getText().equals("")) {
//            telefonField.setText("Telefon");
//        }
    }//GEN-LAST:event_telefonField1FocusLost

    private void telefonField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonField1FocusGained
        telefonField.selectAll();
    }//GEN-LAST:event_telefonField1FocusGained

    private void krajField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_krajField1FocusLost
//        if (krajField.getText().equals("")) {
//            krajField.setText("Kraj");
//        }
    }//GEN-LAST:event_krajField1FocusLost

    private void krajField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_krajField1FocusGained
        krajField.selectAll();
    }//GEN-LAST:event_krajField1FocusGained

    private void nrLokalu1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrLokalu1FocusLost
//        if (nrLokalu.getText().equals("")) {
//            nrLokalu.setText("Nr lokalu");
//        }
    }//GEN-LAST:event_nrLokalu1FocusLost

    private void nrLokalu1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrLokalu1FocusGained
        nrLokalu.selectAll();
    }//GEN-LAST:event_nrLokalu1FocusGained

    private void utworz1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utworz1ActionPerformed

//        wypelnijPola.setVisible(false);

        if(nieWypelnione())
        {
            nieWypelnioneLabel.setVisible(true);
            return;
        }
        nieWypelnioneLabel.setVisible(false);
        loadDataFromTextFields();

        //System.out.println(inputStrings[7]);

        try {
            try {
                hurtownia.setTelefon(telefonField1.getText());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Nieprawidłowy numer: " + telefonField1.getText());
            }
            if (!nrLokalu1.getText().equals("")) {
                hurtownia.getAdres().setNrDomu(Integer.valueOf(nrLokalu1.getText()));
            }
            daoFactory.getHurtowniaDAO().update(hurtownia);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        dispose();
        /*        try {
            CallableStatement proc = connection.prepareCall("{call DodajHurtownie(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            for (int i = 0; i < 8; i++) {
                //System.out.println(inputStrings[i]);
                if (inputStrings[i].equals("")) {
                    wypelnijPola.setVisible(true);
                    return;
                }
                proc.setString(i + 1, inputStrings[i]);
            }
            for (int i = 0; i < 2; i++) {
                proc.setInt(i + 9, inputInts[i]);
            }

            proc.execute();
            proc.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dispose();
        }*/
    }//GEN-LAST:event_utworz1ActionPerformed

    private void UsunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsunButtonActionPerformed
        int answer = Util.showDeleteConfirmationDialog(this, hurtownia.getName());
        if(answer==JOptionPane.YES_OPTION)
            try {
                daoFactory.getHurtowniaDAO().delete(hurtownia.getID());
                dispose();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_UsunButtonActionPerformed

    private void pocztaField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pocztaField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pocztaField1ActionPerformed


    public static void show(final DAOFactory daoFactory, final int idHurtowni, final Admin parent) {


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EdytujDostawceDialog dialog = new EdytujDostawceDialog(parent, idHurtowni, daoFactory);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        parent.refreshCurrentWindow();
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Kontakt;
    private javax.swing.JTextField Kontakt1;
    private javax.swing.JTextField Nazwa;
    private javax.swing.JTextField Nazwa1;
    private javax.swing.JButton UsunButton;
    private javax.swing.JLabel kodLabel;
    private javax.swing.JLabel kodLabel1;
    private javax.swing.JFormattedTextField kodPocztowyField;
    private javax.swing.JFormattedTextField kodPocztowyField1;
    private javax.swing.JTextField krajField;
    private javax.swing.JTextField krajField1;
    private javax.swing.JTextField mailField;
    private javax.swing.JTextField mailField1;
    private javax.swing.JTextField miejscowoscField;
    private javax.swing.JTextField miejscowoscField1;
    private javax.swing.JLabel nieWypelnioneLabel;
    private javax.swing.JTextField nrLokalu;
    private javax.swing.JTextField nrLokalu1;
    private javax.swing.JTextField pocztaField;
    private javax.swing.JTextField pocztaField1;
    private javax.swing.JTextField telefonField;
    private javax.swing.JTextField telefonField1;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel titleLabel1;
    private javax.swing.JTextField ulicaField;
    private javax.swing.JTextField ulicaField1;
    private javax.swing.JButton utworz;
    private javax.swing.JButton utworz1;
    // End of variables declaration//GEN-END:variables
}
