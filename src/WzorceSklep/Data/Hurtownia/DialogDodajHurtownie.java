/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WzorceSklep.Data.Hurtownia;

import WzorceSklep.DAOFactory;

import java.awt.*;
import java.math.BigDecimal;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Ariel
 */
public class DialogDodajHurtownie extends javax.swing.JDialog {
    private JFormattedTextField.AbstractFormatter format;

    /**
     * Creates new form DialogDodajHurtownie
     */
    public DialogDodajHurtownie(Frame parent) {
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
            System.out.println("Proszę nie grzebać w konstruktorze DialogDodajHurtownie");
        }
        initComponents();
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

        kodPocztowyField = new javax.swing.JFormattedTextField(format);
        pocztaField = new javax.swing.JTextField();
        titleLabel = new javax.swing.JLabel();
        Nazwa = new javax.swing.JTextField();
        kodLabel = new javax.swing.JLabel();
        mailField = new javax.swing.JTextField();
        krajField = new javax.swing.JTextField();
        miejscowoscField = new javax.swing.JTextField();
        telefonField = new javax.swing.JTextField();
        Kontakt = new javax.swing.JTextField();
        utworz = new javax.swing.JButton();
        nrLokalu = new javax.swing.JTextField();
        ulicaField = new javax.swing.JTextField();
        wypelnijPola = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        titleLabel.setFont(new java.awt.Font("Vijaya", 0, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Dodawanie nowej hurtowni");

        Nazwa.setText("Nazwa");
        Nazwa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                NazwaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                NazwaFocusLost(evt);
            }
        });

        kodLabel.setText("Kod pocztowy:");

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

        Kontakt.setText("Osoba kontaktowa");
        Kontakt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                KontaktFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                KontaktFocusLost(evt);
            }
        });

        utworz.setText("Dodaj");
        utworz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utworzActionPerformed(evt);
            }
        });

        nrLokalu.setText("Nr lokalu");
        nrLokalu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nrLokaluFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nrLokaluFocusLost(evt);
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

        wypelnijPola.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        wypelnijPola.setForeground(new java.awt.Color(255, 0, 0));
        wypelnijPola.setText("Wypełnij wszystkie pola!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Nazwa, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(Kontakt, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(ulicaField)
                            .addGap(18, 18, 18)
                            .addComponent(nrLokalu, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(76, 76, 76))
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
                        .addComponent(wypelnijPola, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(374, 374, 374)
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
                    .addComponent(Nazwa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Kontakt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ulicaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nrLokalu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(wypelnijPola)
                .addGap(40, 40, 40)
                .addComponent(utworz)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kodPocztowyFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_kodPocztowyFieldFocusGained
        kodPocztowyField.selectAll();
    }//GEN-LAST:event_kodPocztowyFieldFocusGained

    private void pocztaFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pocztaFieldFocusGained
        pocztaField.selectAll();
    }//GEN-LAST:event_pocztaFieldFocusGained

    private void pocztaFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pocztaFieldFocusLost
        if (pocztaField.getText().equals("")) {
            pocztaField.setText("Poczta");
        }
    }//GEN-LAST:event_pocztaFieldFocusLost

    private void NazwaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NazwaFocusGained
        Nazwa.selectAll();
    }//GEN-LAST:event_NazwaFocusGained

    private void NazwaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NazwaFocusLost
        if (Nazwa.getText().equals("")) {
            Nazwa.setText("Imię");
        }
    }//GEN-LAST:event_NazwaFocusLost

    private void mailFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mailFieldFocusGained
        mailField.selectAll();
    }//GEN-LAST:event_mailFieldFocusGained

    private void mailFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mailFieldFocusLost
        if (mailField.getText().equals("")) {
            mailField.setText("Mail");
        }
    }//GEN-LAST:event_mailFieldFocusLost

    private void krajFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_krajFieldFocusGained
        krajField.selectAll();
    }//GEN-LAST:event_krajFieldFocusGained

    private void krajFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_krajFieldFocusLost
        if (krajField.getText().equals("")) {
            krajField.setText("Kraj");
        }
    }//GEN-LAST:event_krajFieldFocusLost

    private void miejscowoscFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_miejscowoscFieldFocusGained
        miejscowoscField.selectAll();
    }//GEN-LAST:event_miejscowoscFieldFocusGained

    private void miejscowoscFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_miejscowoscFieldFocusLost
        if (miejscowoscField.getText().equals("")) {
            miejscowoscField.setText("Miejscowość");
        }
    }//GEN-LAST:event_miejscowoscFieldFocusLost

    private void telefonFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonFieldFocusGained
        telefonField.selectAll();
    }//GEN-LAST:event_telefonFieldFocusGained

    private void telefonFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonFieldFocusLost
        if (telefonField.getText().equals("")) {
            telefonField.setText("Telefon");
        }
    }//GEN-LAST:event_telefonFieldFocusLost

    private void KontaktFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_KontaktFocusGained
        Kontakt.selectAll();
    }//GEN-LAST:event_KontaktFocusGained

    private void KontaktFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_KontaktFocusLost
        if (Kontakt.getText().equals("")) {
            Kontakt.setText("Nazwisko");
        }
    }//GEN-LAST:event_KontaktFocusLost

    private void utworzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utworzActionPerformed

        wypelnijPola.setVisible(false);
        Hurtownia hurtownia = loadDataFromTextFields();

        //System.out.println(inputStrings[7]);


        try {
            hurtownia.telefon = new BigDecimal(telefonField.getText());
            hurtownia.adres.nrDomu = Integer.valueOf(nrLokalu.getText());
            DAOFactory.getHurtowniaDAO().insert(hurtownia);
        } catch (Exception e) {
            wypelnijPola.setVisible(true);
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

    private Hurtownia loadDataFromTextFields() {
        Hurtownia hurtownia = new Hurtownia();
        hurtownia.adres = new AdresHurtowni();

        hurtownia.nazwa = Nazwa.getText();
        hurtownia.osobaKontaktowa = Kontakt.getText();
        hurtownia.mail = mailField.getText();
        hurtownia.adres.ulica = ulicaField.getText();
        hurtownia.adres.kodPocztowy = kodPocztowyField.getText();
        hurtownia.adres.poczta = pocztaField.getText();
        hurtownia.adres.miejscowosc = miejscowoscField.getText();
        hurtownia.adres.kraj = krajField.getText();
        return hurtownia;
    }

    private void nrLokaluFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrLokaluFocusGained
        nrLokalu.selectAll();
    }//GEN-LAST:event_nrLokaluFocusGained

    private void nrLokaluFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrLokaluFocusLost
        if (nrLokalu.getText().equals("")) {
            nrLokalu.setText("Nr lokalu");
        }
    }//GEN-LAST:event_nrLokaluFocusLost

    private void ulicaFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ulicaFieldFocusGained
        ulicaField.selectAll();
    }//GEN-LAST:event_ulicaFieldFocusGained

    private void ulicaFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ulicaFieldFocusLost
        if (ulicaField.getText().equals("")) {
            ulicaField.setText("Ulica");
        }
    }//GEN-LAST:event_ulicaFieldFocusLost

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
            java.util.logging.Logger.getLogger(DialogDodajHurtownie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogDodajHurtownie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogDodajHurtownie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogDodajHurtownie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                DialogDodajHurtownie dialog = new DialogDodajHurtownie(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField Kontakt;
    private javax.swing.JTextField Nazwa;
    private javax.swing.JLabel kodLabel;
    private javax.swing.JFormattedTextField kodPocztowyField;
    private javax.swing.JTextField krajField;
    private javax.swing.JTextField mailField;
    private javax.swing.JTextField miejscowoscField;
    private javax.swing.JTextField nrLokalu;
    private javax.swing.JTextField pocztaField;
    private javax.swing.JTextField telefonField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField ulicaField;
    private javax.swing.JButton utworz;
    private javax.swing.JLabel wypelnijPola;
    // End of variables declaration//GEN-END:variables
}
