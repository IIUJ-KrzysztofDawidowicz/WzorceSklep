package WzorceSklep.Data.Pracownik;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import WzorceSklep.DAOFactory;
import WzorceSklep.GUI.Admin;

import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author KrzysztofD
 */
public class DialogEdytujPracownika extends javax.swing.JDialog {

    private Pracownik pracownik;

    /**
     * Creates new form DialogEdytujPracownika
     */
    public DialogEdytujPracownika(Frame parent, int idPracownika) {
        super(parent, true);
        initComponents();
        try {
            loadPracownik(idPracownika);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        utworz = new javax.swing.JButton();
        umowaField = new javax.swing.JTextField();
        mailField = new javax.swing.JTextField();
        telefonField = new javax.swing.JTextField();
        krajField = new javax.swing.JTextField();
        miejscowoscField = new javax.swing.JTextField();
        pocztaField = new javax.swing.JTextField();
        try {
            kodPocztowyField = new javax.swing.JFormattedTextField(new MaskFormatter("##-###"));
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        kodLabel = new javax.swing.JLabel();
        ulicaField = new javax.swing.JTextField();
        nrdomuField = new javax.swing.JTextField();
        nrlokaluField = new javax.swing.JTextField();
        nazwiskoField = new javax.swing.JTextField();
        imieField = new javax.swing.JTextField();
        titleLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        utworz.setText("Zapisz");
        utworz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utworzActionPerformed(evt);
            }
        });

        umowaField.setText("Umowa");
        umowaField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                umowaFieldFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                umowaFieldFocusGained(evt);
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

        miejscowoscField.setText("Miejscowość");
        miejscowoscField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                miejscowoscFieldFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                miejscowoscFieldFocusGained(evt);
            }
        });

        pocztaField.setText("Poczta");
        pocztaField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pocztaFieldFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                pocztaFieldFocusGained(evt);
            }
        });

        kodPocztowyField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                kodPocztowyFieldFocusGained(evt);
            }
        });

        kodLabel.setText("Kod pocztowy:");

        ulicaField.setText("Ulica");
        ulicaField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ulicaFieldFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                ulicaFieldFocusGained(evt);
            }
        });

        nrdomuField.setText("Nr domu");
        nrdomuField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nrdomuFieldFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                nrdomuFieldFocusGained(evt);
            }
        });

        nrlokaluField.setText("Nr lokalu");
        nrlokaluField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nrlokaluFieldFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                nrlokaluFieldFocusGained(evt);
            }
        });

        nazwiskoField.setText("Nazwisko");
        nazwiskoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nazwiskoFieldFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                nazwiskoFieldFocusGained(evt);
            }
        });

        imieField.setText("Imię");
        imieField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                imieFieldFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                imieFieldFocusGained(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Vijaya", 0, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Dodawanie nowego pracownika");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 641, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(umowaField, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(374, 374, 374)
                                .addComponent(utworz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(14, 14, 14)
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
                    .addComponent(umowaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(92, 92, 92)
                    .addComponent(utworz)
                    .addGap(14, 14, 14)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void loadPracownik(int idPracownika) throws SQLException {
        pracownik = new Pracownik();
        pracownik.setID(idPracownika);
        pracownik = new DAOFactory().getPracownikDAO().getById(idPracownika);
        imieField.setText(pracownik.getImie());
        nazwiskoField.setText(pracownik.getNazwisko());
//        adres.setText(String.format("ul. %s, %d m. %d", pracownik.getAdres().getUlica(), pracownik.getAdres().getNrLokalu(), pracownik.getAdres().getNrDomu()));
        ulicaField.setText(pracownik.getAdres().getUlica());
        nrdomuField.setText(String.valueOf(pracownik.getAdres().getNrDomu()));
        nrlokaluField.setText(String.valueOf(pracownik.getAdres().getNrLokalu()));
        kodPocztowyField.setText(pracownik.getAdres().getKodPocztowy());
        pocztaField.setText(pracownik.getAdres().getPoczta());
        miejscowoscField.setText(pracownik.getAdres().getMiejscowosc());
        telefonField.setText(pracownik.getTelefon());
        krajField.setText(pracownik.getAdres().getKraj());
        mailField.setText(pracownik.getMail());
        umowaField.setText(pracownik.getUmowa());
    }


    private void utworzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utworzActionPerformed
        pracownik.setImie(imieField.getText());
        pracownik.setNazwisko(nazwiskoField.getText());
        pracownik.getAdres().setUlica(ulicaField.getText());
        pracownik.getAdres().setKodPocztowy(kodPocztowyField.getText());
        pracownik.getAdres().setPoczta(pocztaField.getText());
        pracownik.getAdres().setMiejscowosc(miejscowoscField.getText());
        pracownik.getAdres().setKraj(krajField.getText());
        pracownik.setMail(mailField.getText());
        pracownik.setUmowa(umowaField.getText());
    }//GEN-LAST:event_utworzActionPerformed

    private void umowaFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_umowaFieldFocusLost
        /*if (umowaField.getText().equals("")) {
            umowaField.setText("Umowa");*/
//        }
    }//GEN-LAST:event_umowaFieldFocusLost

    private void umowaFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_umowaFieldFocusGained
        umowaField.selectAll();
    }//GEN-LAST:event_umowaFieldFocusGained

    private void mailFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mailFieldFocusLost
//        if (mailField.getText().equals("")) {
//            mailField.setText("Mail");
//        }
    }//GEN-LAST:event_mailFieldFocusLost

    private void mailFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mailFieldFocusGained
        mailField.selectAll();
    }//GEN-LAST:event_mailFieldFocusGained

    private void telefonFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonFieldFocusLost
//        if (telefonField.getText().equals("")) {
//            telefonField.setText("Telefon");
//        }
    }//GEN-LAST:event_telefonFieldFocusLost

    private void telefonFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonFieldFocusGained
        telefonField.selectAll();
    }//GEN-LAST:event_telefonFieldFocusGained

    private void krajFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_krajFieldFocusLost
        if (krajField.getText().equals("")) {
            krajField.setText("Kraj");
        }
    }//GEN-LAST:event_krajFieldFocusLost

    private void krajFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_krajFieldFocusGained
        krajField.selectAll();
    }//GEN-LAST:event_krajFieldFocusGained

    private void miejscowoscFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_miejscowoscFieldFocusLost
//        if (miejscowoscField.getText().equals("")) {
//            miejscowoscField.setText("Miejscowość");
//        }
    }//GEN-LAST:event_miejscowoscFieldFocusLost

    private void miejscowoscFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_miejscowoscFieldFocusGained
        miejscowoscField.selectAll();
    }//GEN-LAST:event_miejscowoscFieldFocusGained

    private void pocztaFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pocztaFieldFocusLost
//        if (pocztaField.getText().equals("")) {
//            pocztaField.setText("Poczta");
//        }
    }//GEN-LAST:event_pocztaFieldFocusLost

    private void pocztaFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pocztaFieldFocusGained
        pocztaField.selectAll();
    }//GEN-LAST:event_pocztaFieldFocusGained

    private void kodPocztowyFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kodPocztowyFieldFocusGained
        kodPocztowyField.selectAll();
    }//GEN-LAST:event_kodPocztowyFieldFocusGained

    private void ulicaFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ulicaFieldFocusLost
//        if (ulicaField.getText().equals("")) {
//            ulicaField.setText("Ulica");
//        }
    }//GEN-LAST:event_ulicaFieldFocusLost

    private void ulicaFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ulicaFieldFocusGained
        ulicaField.selectAll();
    }//GEN-LAST:event_ulicaFieldFocusGained

    private void nrdomuFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrdomuFieldFocusLost
//        if (nrdomuField.getText().equals("")) {
//            nrdomuField.setText("Nr domu");
//        }
    }//GEN-LAST:event_nrdomuFieldFocusLost

    private void nrdomuFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrdomuFieldFocusGained
        nrdomuField.selectAll();
    }//GEN-LAST:event_nrdomuFieldFocusGained

    private void nrlokaluFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrlokaluFieldFocusLost
//        if (nrlokaluField.getText().equals("")) {
//            nrlokaluField.setText("Nr lokalu");
//        }
    }//GEN-LAST:event_nrlokaluFieldFocusLost

    private void nrlokaluFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrlokaluFieldFocusGained
        nrlokaluField.selectAll();
    }//GEN-LAST:event_nrlokaluFieldFocusGained

    private void nazwiskoFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nazwiskoFieldFocusLost
//        if (nazwiskoField.getText().equals("")) {
//            nazwiskoField.setText("Nazwisko");
//        }
    }//GEN-LAST:event_nazwiskoFieldFocusLost

    private void nazwiskoFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nazwiskoFieldFocusGained
        nazwiskoField.selectAll();
    }//GEN-LAST:event_nazwiskoFieldFocusGained

    private void imieFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_imieFieldFocusLost
//        if (imieField.getText().equals("")) {
//            imieField.setText("Imię");
//        }
    }//GEN-LAST:event_imieFieldFocusLost

    private void imieFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_imieFieldFocusGained
        imieField.selectAll();
    }//GEN-LAST:event_imieFieldFocusGained

    public static void show(final Admin parent, final int idPracownika) {

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogEdytujPracownika dialog = new DialogEdytujPracownika(parent, idPracownika);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField imieField;
    private javax.swing.JLabel kodLabel;
    private javax.swing.JFormattedTextField kodPocztowyField;
    private javax.swing.JTextField krajField;
    private javax.swing.JTextField mailField;
    private javax.swing.JTextField miejscowoscField;
    private javax.swing.JTextField nazwiskoField;
    private javax.swing.JTextField nrdomuField;
    private javax.swing.JTextField nrlokaluField;
    private javax.swing.JTextField pocztaField;
    private javax.swing.JTextField telefonField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField ulicaField;
    private javax.swing.JTextField umowaField;
    private javax.swing.JButton utworz;
    // End of variables declaration//GEN-END:variables
}