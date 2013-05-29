/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WzorceSklep.Data.Pracownik;

import WzorceSklep.DataAccessObject;
import WzorceSklep.DAOFactory;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class DialogUsunPracownika extends javax.swing.JDialog
{
    private final int id;

    /**
     * Creates new form DialogUsunPracownika
     */
    public DialogUsunPracownika(Frame parent, int id, String nazwisko) {
        super(parent, true);
        this.id=id;
        initComponents();
        idnazwisko.setText(Integer.toString(id)+" "+nazwisko);
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
        takButton = new javax.swing.JButton();
        nieButton = new javax.swing.JButton();
        czyjestespewienLabel = new javax.swing.JLabel();
        idnazwisko = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Vijaya", 0, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Usuwanie pracownika");

        takButton.setText("Tak");
        takButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                takButtonActionPerformed(evt);
            }
        });

        nieButton.setText("Nie");
        nieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nieButtonActionPerformed(evt);
            }
        });

        czyjestespewienLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        czyjestespewienLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        czyjestespewienLabel.setText("Czy jesteś pewien że chcesz usunąć");

        idnazwisko.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        idnazwisko.setText("[ID] [Nazwisko] [Imie]");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(takButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(idnazwisko, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(czyjestespewienLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(czyjestespewienLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idnazwisko)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(takButton)
                    .addComponent(nieButton))
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nieButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nieButtonActionPerformed
        dispose();
    }//GEN-LAST:event_nieButtonActionPerformed

    private void takButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_takButtonActionPerformed
        try {
            DataAccessObject<Pracownik> dao = new DAOFactory().getPracownikDAO();
            dao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        /*try
        {
            CallableStatement proc = connection.prepareCall("{call UsunPracownika(?)}");
            proc.setInt(1, id);
            proc.execute();
            proc.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally*/
//        {
            dispose();
//        }
    }//GEN-LAST:event_takButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel czyjestespewienLabel;
    private javax.swing.JLabel idnazwisko;
    private javax.swing.JButton nieButton;
    private javax.swing.JButton takButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
