/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WzorceSklep.Data.Pracownik;

import WzorceSklep.DAOFactory;
import com.sun.media.sound.InvalidDataException;

import java.awt.*;
import java.sql.SQLException;

public class DialogPracownikSzczegoly extends javax.swing.JDialog
{

    /**
     * Creates new form DialogPracownikSzczegoly
     */
    public DialogPracownikSzczegoly(Frame parent, int idPracownika) {
        super(parent, true);
        initComponents();
        main(idPracownika);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        kodLabel = new javax.swing.JLabel();
        imieLabel = new javax.swing.JLabel();
        imie = new javax.swing.JLabel();
        nazwiskoLabel = new javax.swing.JLabel();
        nazwisko = new javax.swing.JLabel();
        ardresLabel = new javax.swing.JLabel();
        adres = new javax.swing.JLabel();
        kodPocztowy = new javax.swing.JLabel();
        poczta = new javax.swing.JLabel();
        miejscowosc = new javax.swing.JLabel();
        pocztaLabel = new javax.swing.JLabel();
        telefon = new javax.swing.JLabel();
        telefonLabel = new javax.swing.JLabel();
        mailLabel = new javax.swing.JLabel();
        kraj = new javax.swing.JLabel();
        mail = new javax.swing.JLabel();
        umowaLabel = new javax.swing.JLabel();
        umowa = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        login = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        miasto = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        statusLabel.setText("Status:");

        kodLabel.setText("Kod pocztowy:");

        imieLabel.setText("Imię:");

        imie.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        imie.setText("[Imię]");

        nazwiskoLabel.setText("Nazwisko:");

        nazwisko.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nazwisko.setText("[Nazwisko]");

        ardresLabel.setText("Adres:");

        adres.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        adres.setText("ul. [Ulica], [Nr lokalu] m. [Nr domu] ");

        kodPocztowy.setText("##-###");

        poczta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        poczta.setText("[Poczta]");

        miejscowosc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        miejscowosc.setText("[Miejscowość]");

        pocztaLabel.setText("Poczta:");

        telefon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        telefon.setText("[Telefon]");

        telefonLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        telefonLabel.setText("Telefon:");

        mailLabel.setText("Mail:");

        kraj.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        kraj.setText("[Kraj]");

        mail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        mail.setText("[Mail]");

        umowaLabel.setText("Umowa w pliku:");

        umowa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        umowa.setText("[Ścieżka]");

        loginLabel.setText("Login:");

        login.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        login.setText("[Login]");

        status.setText("[Status]");

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Vijaya", 0, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Informacje o pracowniku");

        miasto.setText("Miejscowość:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(loginLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mailLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(umowaLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(umowa, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(telefonLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(telefon, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(207, 207, 207))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(statusLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(status)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nazwiskoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nazwisko, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(kodLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(kodPocztowy, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kraj, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(pocztaLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(poczta, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(miasto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(miejscowosc, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(ardresLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(adres, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(imieLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(imie, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imieLabel)
                    .addComponent(imie))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nazwiskoLabel)
                    .addComponent(nazwisko))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ardresLabel)
                    .addComponent(adres))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kodPocztowy)
                    .addComponent(pocztaLabel)
                    .addComponent(poczta))
                .addGap(4, 4, 4)
                .addComponent(kraj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(miasto)
                    .addComponent(miejscowosc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(okButton)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(telefon)
                            .addComponent(telefonLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mailLabel)
                            .addComponent(mail))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(umowaLabel)
                            .addComponent(umowa))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loginLabel)
                            .addComponent(login))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(statusLabel)
                            .addComponent(status))))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void main(int idPracownika)
    {
        try
        {
            Pracownik pracownik = new Pracownik();
            pracownik.setID(idPracownika);
            pracownik = new DAOFactory().getPracownikDAO().getById(idPracownika);
            imie.setText(pracownik.getImie());
            nazwisko.setText(pracownik.getNazwisko());
            adres.setText(String.format("ul. %s, %d m. %d", pracownik.getAdres().ulica, pracownik.getAdres().nrLokalu, pracownik.getAdres().nrDomu));
            kodPocztowy.setText(pracownik.getAdres().kodPocztowy);
            poczta.setText(pracownik.getAdres().poczta);
            miejscowosc.setText(pracownik.getAdres().miejscowosc);
            telefon.setText(pracownik.getTelefon().toPlainString());
            kraj.setText(pracownik.getAdres().kraj);
            mail.setText(pracownik.getMail());
            umowa.setText(pracownik.getUmowa());
            login.setText(pracownik.getLogin());
            status.setText(pracownik.getStatusString());
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adres;
    private javax.swing.JLabel ardresLabel;
    private javax.swing.JLabel imie;
    private javax.swing.JLabel imieLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel kodLabel;
    private javax.swing.JLabel kodPocztowy;
    private javax.swing.JLabel kraj;
    private javax.swing.JLabel login;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel mail;
    private javax.swing.JLabel mailLabel;
    private javax.swing.JLabel miasto;
    private javax.swing.JLabel miejscowosc;
    private javax.swing.JLabel nazwisko;
    private javax.swing.JLabel nazwiskoLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel poczta;
    private javax.swing.JLabel pocztaLabel;
    private javax.swing.JLabel status;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel telefon;
    private javax.swing.JLabel telefonLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel umowa;
    private javax.swing.JLabel umowaLabel;
    // End of variables declaration//GEN-END:variables
}