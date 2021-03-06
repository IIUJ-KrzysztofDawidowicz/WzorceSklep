/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Login.java
 *
 * Created on 2012-02-12, 15:11:20
 */

package WzorceSklep.GUI;

import WzorceSklep.Data.Pracownik.PracownikDAO;
import WzorceSklep.Data.DataAdapter.DataAdapterFactory;
import WzorceSklep.Data.Pracownik.Pracownik;
import WzorceSklep.Main;

import java.util.HashMap;
import java.util.Map;
//import sklep.Login;
/**
 *
 * @author user
 */
public class Login extends javax.swing.JFrame {
    private Map<Pracownik.Statusy,LoginAction> loginActions;

    private void initMap()
    {
        loginActions = new HashMap<Pracownik.Statusy, LoginAction>();
        loginActions.put(Pracownik.Statusy.Admin, new Main.LoginAdminAction());
        loginActions.put(Pracownik.Statusy.Hurtownik, new Main.LoginHurtownikAction());
        loginActions.put(Pracownik.Statusy.Sprzedawca, new Main.LoginSprzedawcaAction());
    }


    /** Creates new form Login */
    public Login() {
        //LoginAdmin();
        initMap();
        initComponents();
        ZleHaslo.setVisible(false);
/*        try {   //czy mozna polaczyc
	    Class.forName(dbDriverClass);
	    //System.out.println("Connection string := "+dbDriverSelect + dbAddress);
            
            //Za login, haslo nalezy podstawic konkretne wartosci
	    connection = DriverManager.getConnection(dbDriverSelect + dbAddress, Login, Haslo);
	} catch (Exception e) {
            ZleHaslo.setVisible(true);  //pokazuje informacje o bledzie
	    System.out.println("Problem z połączeniem z bazą danych");
	    e.printStackTrace();
	}*/
    }


    //Automatycznie generowanyy kod
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        ZleHaslo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LoginText = new javax.swing.JTextField();
        HasloText = new javax.swing.JPasswordField();
        ZalogujButton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 69, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 197, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 201, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 31, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ZleHaslo.setForeground(new java.awt.Color(255, 0, 0));
        ZleHaslo.setText("Problem z polaczeniem z baza danych");

        jLabel1.setFont(new java.awt.Font("Vijaya", 1, 24)); // NOI18N
        jLabel1.setText("Logowanie do sklepu");

        jLabel2.setText("Login: ");

        jLabel3.setText("Hasło:");

        LoginText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginTextActionPerformed(evt);
            }
        });

        HasloText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HasloTextActionPerformed(evt);
            }
        });

        ZalogujButton.setText("Zaloguj");
        ZalogujButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZalogujActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LoginText)
                                    .addComponent(HasloText, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(ZleHaslo, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 51, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(ZalogujButton)))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(ZleHaslo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(LoginText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HasloText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(ZalogujButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ZalogujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZalogujActionPerformed
        final String login = LoginText.getText();
        final String haslo = HasloText.getText();
        try {
            Pracownik pracownik = new PracownikDAO(DataAdapterFactory.getDatabaseAdapter()).getByLogin(login, haslo);
            if (pracownik != null) {
                dispose();
                loginActions.get(pracownik.getStatus()).Login(pracownik);
            }
            else
                ZleHaslo.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            ZleHaslo.setVisible(true);
        }

        //Bedziemy laczyc od razu, i tylko przelaczac okna wzgledem osoby ktora sie zalogowala
        
        /*/przedwczesna połączenie z oknem Admin
        dispose();  //wylaczani okna
        java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            new Admin(connection).setVisible(true);
                        }
                    });
        *///----------------------------------------
        
/*            try{
                //sprawdzic jako kto i otworzyc okno Bibliotekarza lub Uzytkownika lub Admina
*//*                statement = connection.createStatement();

                //Bedzie kolumna status w bazie danych
                ResultSet uzytkownik = statement.executeQuery(
                        "use Sklep;select Status from Pracownicy where Login = '" + login +"' AND Haslo = '" + haslo + "'"
                        );*//*
                if(uzytkownik.next()){  //jesli istnieje uzytkownik z tym haslem;
                    String kto = uzytkownik.getString("Status");    //ustalamy uprawnienia

                    if(kto.contentEquals("Admin")){
                        Login();
                    }else if(kto.contentEquals("Sprzedawca")){
                        LoginSprzedawca(login);
                    }else if(kto.contentEquals("Hurtownik")){
                        LoginHurtownik();
                    }
                }else{
                    ZleHaslo.setVisible(true);
                }
            }catch(Exception e) {
                ZleHaslo.setVisible(true);
                e.printStackTrace();
            }*/
        
    }//GEN-LAST:event_ZalogujActionPerformed

    private void HasloTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HasloTextActionPerformed
        ZalogujButton.doClick();
    }//GEN-LAST:event_HasloTextActionPerformed

    private void LoginTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginTextActionPerformed
        ZalogujButton.doClick();
    }//GEN-LAST:event_LoginTextActionPerformed

    /**
    * @param args the command line arguments
    */
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WzorceSklep.GUI.Login().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField HasloText;
    private javax.swing.JTextField LoginText;
    private javax.swing.JButton ZalogujButton;
    private javax.swing.JLabel ZleHaslo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    // End of variables declaration//GEN-END:variables

}
