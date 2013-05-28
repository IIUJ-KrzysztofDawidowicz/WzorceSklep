/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WzorceSklep.GUI;

import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author user
 */
public class Sprzedawca extends javax.swing.JFrame {

    String Login;
    int aktywne_okno;
    Component okno;
    Connection connection = null;
    final JFrame thisframe=this;
    javax.swing.JDialog dialog;
    public Sprzedawca(Connection con, String login) {
        connection = con;
        Login = login;
                
        initComponents();
        panel_Klienci.setVisible(false);
        panel_Moje_Dane.setVisible(false);
        panel_Produkty.setVisible(false);
        panel_Zamowienia.setVisible(false);
        okno = panel_Klienci;
        Warstwy.moveToFront(okno);
    }

        
//-------------------------------------------------------------------------------------------------------------------
    void show_panel(Component c){
        Warstwy.moveToBack(okno);
        Warstwy.moveToFront(c);
        okno.setVisible(false);
        c.setVisible(true);
        okno = c;
    }
    
//-------------------------------------------------------------------------------------------------------------------
    void refresh_zamowienia_klient()
    {
        String nazwisko,szukaj,sortuj;
        
        szukaj = zam_klienci_szukaj_co.getText();
        if(!szukaj.equals("")){
            szukaj = " where  KImie like '%" + szukaj + "%' or KNazwisko like '%" + szukaj + "%' or Nazwa like '%" + szukaj
                    + "%' or Typ like '%" + szukaj + "%' or Ilosc like '%" + szukaj + "%' or Data like '%" + szukaj
                    + "%' or Kwota like '%" + szukaj + "%' or PImie like '%" + szukaj + "%' or PNazwisko like '%" + szukaj + "%'";   
        }
        
        sortuj = zam_klienci_sortuj.getSelectedItem().toString();
        if(sortuj.equals("Klient")){
            sortuj = "KNazwisko";
        }else if(sortuj.equals("Produkt")){
            sortuj = "Nazwa";
        }else if(sortuj.equals("Ilość")){
            sortuj = "Ilosc";
        }else if(sortuj.equals("Pracownik")){
            sortuj = "PNazwisko";
        }
        
        int i=0;
        try
        {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from v_zamow_klient" + szukaj);
            rs.next();
            TableModel model=new DefaultTableModel(new String[]{
                "Klient","Produkt","Typ","Ilość","Data","Kwota","Pracownik"            
            }, rs.getInt(1))
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from v_zamow_klient" + szukaj + " order by " + sortuj);
            while(rs.next())
            {
                nazwisko=rs.getString("KNazwisko");
                nazwisko+=" "+rs.getString("KImie");
                model.setValueAt(nazwisko, i, 0);
                
                nazwisko=rs.getString("PNazwisko");
                nazwisko+=" "+rs.getString("PImie");
                
                model.setValueAt(rs.getString("Nazwa"), i, 1);
                model.setValueAt(rs.getString("Typ"), i, 2);
                model.setValueAt(rs.getInt("Ilosc"), i, 3);
                model.setValueAt(rs.getString("Data"), i, 4);
                model.setValueAt(rs.getInt("Kwota"), i, 5);
                model.setValueAt(nazwisko, i, 6);
                
                i++;
            }
            zam_klienci_table.setModel(model);
            zam_klienci_szukaj_co.setText("");
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
    }
//-------------------------------------------------------------------------------------------------------------------
    void refresh_zamowienia_hurt()
    {
        String szukaj,sortuj;
        
        szukaj = zam_hurt_szukaj_co.getText();
        if(!szukaj.equals("")){
                szukaj = " where Hurtownia like '%" + szukaj + "%' or Nazwa like '%" + szukaj + "%' or Typ like '%" + szukaj
                        + "%' or Ilosc like '%" + szukaj + "%' or Cena like '%" + szukaj
                        + "%' or Data_zamowienia like '%" + szukaj + "%' or Data_odebrania like '%" + szukaj + "%'";
        }
        
        sortuj = zam_hurt_sortuj.getSelectedItem().toString();
        if(sortuj.equals("Produkt")){
            sortuj = "Nazwa";
        }else if(sortuj.equals("Ilość")){
            sortuj = "Ilosc";
        }else if(sortuj.equals("Kwota")){
            sortuj = "Cena";
        }else if(sortuj.equals("Data zamowienia")){
            sortuj = "Data_zamowienia";
        }else if(sortuj.equals("Data odebrania")){
            sortuj = "Data_odebrania";
        }
        
        int i=0;
        try
        {
            Statement stat = connection.createStatement();
            
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from v_zamow_hurtownia" + szukaj);
            rs.next();
            
            TableModel model=new DefaultTableModel(new String[]{
                "Hurtownia","Produkt","Typ","Ilość","Kwota","Data zamowienia","Data odebrania"
            }, rs.getInt(1))
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            
            
            rs = stat.executeQuery("use Sklep;select * from v_zamow_hurtownia" + szukaj + " order by " + sortuj);
            while(rs.next())
            {
                model.setValueAt(rs.getString("Hurtownia"), i, 0);
                model.setValueAt(rs.getString("Nazwa"), i, 1);
                model.setValueAt(rs.getString("Typ"), i, 2);
                model.setValueAt(rs.getInt("Ilosc"), i, 3);
                model.setValueAt(rs.getInt("Cena"), i, 4);
                model.setValueAt(rs.getString("Data_zamowienia"), i, 5);
                model.setValueAt(rs.getString("Data_odebrania"), i, 6);
                i++;
            }
            zam_hurt_table.setModel(model);
            zam_hurt_szukaj_co.setText("");
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
    }
//-------------------------------------------------------------------------------------------------------------------
    void refresh_klienci() //Rysuje klienci_tabela
    {
        String nazwisko;
        String order_by=klienci_sortuj.getSelectedItem().toString();
        String search_by=klienci_szukaj_co.getText();
        if(order_by.equals("ID")) order_by="ID_Klienta";
        if(!search_by.isEmpty()) 
            search_by=" where Imie like '%"+search_by+"%' or Nazwisko like '%"+search_by+"%' or Login like '%"+search_by+"%' or Telefon like '%"+search_by+"%'";
        int indeks_tabeli=0;
        int rowcount=0;
        try
        {
            Statement stat = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stat.executeQuery("use Sklep;select ID_Klienta, Imie, Nazwisko, Login, Telefon from Klienci"+search_by+" order by "+order_by);
            if(rs.last())
            {
                rowcount=rs.getRow();
                rs.beforeFirst();
            }
            TableModel model=new DefaultTableModel(new String[]{"ID","Nazwisko","login","Telefon"}, rowcount)
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            
            while(rs.next())
            {
                nazwisko=rs.getString("Nazwisko");
                nazwisko+=" "+rs.getString("Imie");
                model.setValueAt(rs.getInt("ID_Klienta"), indeks_tabeli, 0);
                model.setValueAt(nazwisko, indeks_tabeli, 1);
                model.setValueAt(rs.getString("Login"), indeks_tabeli, 2);
                model.setValueAt(rs.getString("Telefon"),indeks_tabeli,3);
                indeks_tabeli++;
            }
            klienci_table.setModel(model);
            klienci_szukaj_co.setText("");
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
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

        jLabel1 = new javax.swing.JLabel();
        Klienci = new javax.swing.JButton();
        Zamowienia = new javax.swing.JButton();
        Dane_Osobowe = new javax.swing.JButton();
        Produkty = new javax.swing.JButton();
        Warstwy = new javax.swing.JLayeredPane();
        panel_Klienci = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        klienci_table = new javax.swing.JTable();
        klienci_szukaj_co = new javax.swing.JTextField();
        klienci_sortuj = new javax.swing.JComboBox();
        klienci_szukaj = new javax.swing.JButton();
        panel_Produkty = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        prod_table = new javax.swing.JTable();
        prod_szukaj_co = new javax.swing.JTextField();
        prod_sortuj = new javax.swing.JComboBox();
        prod_szukaj = new javax.swing.JButton();
        panel_Zamowienia = new javax.swing.JPanel();
        tabbed_zamowienia = new javax.swing.JTabbedPane();
        zam_klienci_panel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        zam_klienci_table = new javax.swing.JTable();
        zam_klienci_szukaj_co = new javax.swing.JTextField();
        zam_klienci_szukaj = new javax.swing.JButton();
        zam_klienci_sortuj = new javax.swing.JComboBox();
        zam_klienci_zamow = new javax.swing.JButton();
        zam_hurt_panel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        zam_hurt_table = new javax.swing.JTable();
        zam_hurt_szukaj_co = new javax.swing.JTextField();
        zam_hurt_szukaj = new javax.swing.JButton();
        zam_hurt_sortuj = new javax.swing.JComboBox();
        panel_Moje_Dane = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe Script", 3, 24)); // NOI18N
        jLabel1.setText("Sklep");

        Klienci.setText("Klienci");
        Klienci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KlienciActionPerformed(evt);
            }
        });

        Zamowienia.setText("Zamównienia");
        Zamowienia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZamowieniaActionPerformed(evt);
            }
        });

        Dane_Osobowe.setText("Moje Dane");
        Dane_Osobowe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dane_OsoboweActionPerformed(evt);
            }
        });

        Produkty.setText("Produkty");
        Produkty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProduktyActionPerformed(evt);
            }
        });

        klienci_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(klienci_table);

        klienci_sortuj.setModel(new javax.swing.DefaultComboBoxModel(new String[]  { "ID", "Nazwisko", "Login" }));
        klienci_sortuj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                klienci_sortujActionPerformed(evt);
            }
        });

        klienci_szukaj.setText("Szukaj");
        klienci_szukaj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                klienci_szukajMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel_KlienciLayout = new javax.swing.GroupLayout(panel_Klienci);
        panel_Klienci.setLayout(panel_KlienciLayout);
        panel_KlienciLayout.setHorizontalGroup(
            panel_KlienciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_KlienciLayout.createSequentialGroup()
                .addGroup(panel_KlienciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_KlienciLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_KlienciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_KlienciLayout.createSequentialGroup()
                                .addComponent(klienci_szukaj_co, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(klienci_szukaj, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(klienci_sortuj, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_KlienciLayout.setVerticalGroup(
            panel_KlienciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_KlienciLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_KlienciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(klienci_szukaj)
                    .addComponent(klienci_szukaj_co, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(klienci_sortuj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        panel_Klienci.setBounds(0, 0, 820, 440);
        Warstwy.add(panel_Klienci, javax.swing.JLayeredPane.DEFAULT_LAYER);

        prod_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(prod_table);

        prod_sortuj.setModel(new javax.swing.DefaultComboBoxModel(new String[]  { "ID", "Nazwisko", "Login" }));

        prod_szukaj.setText("Szukaj");
        prod_szukaj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prod_szukajMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel_ProduktyLayout = new javax.swing.GroupLayout(panel_Produkty);
        panel_Produkty.setLayout(panel_ProduktyLayout);
        panel_ProduktyLayout.setHorizontalGroup(
            panel_ProduktyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ProduktyLayout.createSequentialGroup()
                .addGroup(panel_ProduktyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ProduktyLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_ProduktyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_ProduktyLayout.createSequentialGroup()
                                .addComponent(prod_szukaj_co, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(prod_szukaj, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(prod_sortuj, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_ProduktyLayout.setVerticalGroup(
            panel_ProduktyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ProduktyLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_ProduktyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prod_szukaj)
                    .addComponent(prod_szukaj_co, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prod_sortuj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        panel_Produkty.setBounds(0, 0, 820, 440);
        Warstwy.add(panel_Produkty, javax.swing.JLayeredPane.DEFAULT_LAYER);

        zam_klienci_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(zam_klienci_table);

        zam_klienci_szukaj.setText("Szukaj");
        zam_klienci_szukaj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zam_klienci_szukajMouseClicked(evt);
            }
        });

        zam_klienci_sortuj.setModel(new javax.swing.DefaultComboBoxModel(new String[]  { "Klient","Produkt","Typ","Ilość","Data","Kwota","Pracownik" }));
        zam_klienci_sortuj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zam_klienci_sortujActionPerformed(evt);
            }
        });

        zam_klienci_zamow.setText("Zamów");

        javax.swing.GroupLayout zam_klienci_panelLayout = new javax.swing.GroupLayout(zam_klienci_panel);
        zam_klienci_panel.setLayout(zam_klienci_panelLayout);
        zam_klienci_panelLayout.setHorizontalGroup(
            zam_klienci_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, zam_klienci_panelLayout.createSequentialGroup()
                .addGroup(zam_klienci_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5)
                    .addGroup(zam_klienci_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(zam_klienci_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(zam_klienci_panelLayout.createSequentialGroup()
                                .addComponent(zam_klienci_szukaj_co, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(zam_klienci_szukaj, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(zam_klienci_sortuj, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 331, Short.MAX_VALUE)
                        .addComponent(zam_klienci_zamow, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        zam_klienci_panelLayout.setVerticalGroup(
            zam_klienci_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zam_klienci_panelLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(zam_klienci_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(zam_klienci_panelLayout.createSequentialGroup()
                        .addGroup(zam_klienci_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(zam_klienci_szukaj)
                            .addComponent(zam_klienci_szukaj_co, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zam_klienci_sortuj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(zam_klienci_zamow, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        tabbed_zamowienia.addTab("Klienci", zam_klienci_panel);

        zam_hurt_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(zam_hurt_table);

        zam_hurt_szukaj.setText("Szukaj");
        zam_hurt_szukaj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zam_hurt_szukajMouseClicked(evt);
            }
        });

        zam_hurt_sortuj.setModel(new javax.swing.DefaultComboBoxModel(new String[]  { "Hurtownia","Produkt","Typ","Ilość","Kwota","Data zamowienia","Data odebrania" }));
        zam_hurt_sortuj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zam_hurt_sortujActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout zam_hurt_panelLayout = new javax.swing.GroupLayout(zam_hurt_panel);
        zam_hurt_panel.setLayout(zam_hurt_panelLayout);
        zam_hurt_panelLayout.setHorizontalGroup(
            zam_hurt_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zam_hurt_panelLayout.createSequentialGroup()
                .addGroup(zam_hurt_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(zam_hurt_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(zam_hurt_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(zam_hurt_panelLayout.createSequentialGroup()
                                .addComponent(zam_hurt_szukaj_co, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(zam_hurt_szukaj, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(zam_hurt_sortuj, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE))
                .addContainerGap())
        );
        zam_hurt_panelLayout.setVerticalGroup(
            zam_hurt_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zam_hurt_panelLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(zam_hurt_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(zam_hurt_szukaj)
                    .addComponent(zam_hurt_szukaj_co, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zam_hurt_sortuj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        tabbed_zamowienia.addTab("Hurtownie", zam_hurt_panel);

        javax.swing.GroupLayout panel_ZamowieniaLayout = new javax.swing.GroupLayout(panel_Zamowienia);
        panel_Zamowienia.setLayout(panel_ZamowieniaLayout);
        panel_ZamowieniaLayout.setHorizontalGroup(
            panel_ZamowieniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbed_zamowienia)
        );
        panel_ZamowieniaLayout.setVerticalGroup(
            panel_ZamowieniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbed_zamowienia, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );

        panel_Zamowienia.setBounds(0, 0, 820, 460);
        Warstwy.add(panel_Zamowienia, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panel_Moje_DaneLayout = new javax.swing.GroupLayout(panel_Moje_Dane);
        panel_Moje_Dane.setLayout(panel_Moje_DaneLayout);
        panel_Moje_DaneLayout.setHorizontalGroup(
            panel_Moje_DaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        panel_Moje_DaneLayout.setVerticalGroup(
            panel_Moje_DaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        panel_Moje_Dane.setBounds(0, 0, 820, 440);
        Warstwy.add(panel_Moje_Dane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Zamowienia, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Dane_Osobowe, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Klienci, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Produkty, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Warstwy, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Warstwy)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Klienci, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Produkty, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Zamowienia, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Dane_Osobowe, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 241, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void klienci_szukajMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_klienci_szukajMouseClicked
        refresh_klienci();
        klienci_szukaj_co.setText("");
    }//GEN-LAST:event_klienci_szukajMouseClicked

    private void prod_szukajMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prod_szukajMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_prod_szukajMouseClicked

    private void zam_klienci_szukajMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zam_klienci_szukajMouseClicked
        refresh_zamowienia_klient();
        zam_klienci_szukaj_co.setText("");
    }//GEN-LAST:event_zam_klienci_szukajMouseClicked

    private void zam_hurt_szukajMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zam_hurt_szukajMouseClicked
        refresh_zamowienia_hurt();
        zam_hurt_szukaj_co.setText("");
    }//GEN-LAST:event_zam_hurt_szukajMouseClicked

    private void KlienciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KlienciActionPerformed
        show_panel(panel_Klienci);
        refresh_klienci();
    }//GEN-LAST:event_KlienciActionPerformed

    private void ProduktyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProduktyActionPerformed
        show_panel(panel_Produkty);
    }//GEN-LAST:event_ProduktyActionPerformed

    private void ZamowieniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZamowieniaActionPerformed
        show_panel(panel_Zamowienia);
        refresh_zamowienia_hurt();
        refresh_zamowienia_klient();
    }//GEN-LAST:event_ZamowieniaActionPerformed

    private void Dane_OsoboweActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dane_OsoboweActionPerformed
        show_panel(panel_Moje_Dane);
    }//GEN-LAST:event_Dane_OsoboweActionPerformed

    private void zam_hurt_sortujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zam_hurt_sortujActionPerformed
        refresh_zamowienia_hurt();
    }//GEN-LAST:event_zam_hurt_sortujActionPerformed

    private void zam_klienci_sortujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zam_klienci_sortujActionPerformed
        refresh_zamowienia_klient();
    }//GEN-LAST:event_zam_klienci_sortujActionPerformed

    private void klienci_sortujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_klienci_sortujActionPerformed
        refresh_klienci();
    }//GEN-LAST:event_klienci_sortujActionPerformed

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
            java.util.logging.Logger.getLogger(Sprzedawca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sprzedawca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sprzedawca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sprzedawca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Sprzedawca().setVisible(true);
            }
        });
        */
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Dane_Osobowe;
    private javax.swing.JButton Klienci;
    private javax.swing.JButton Produkty;
    private javax.swing.JLayeredPane Warstwy;
    private javax.swing.JButton Zamowienia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JComboBox klienci_sortuj;
    private javax.swing.JButton klienci_szukaj;
    private javax.swing.JTextField klienci_szukaj_co;
    private javax.swing.JTable klienci_table;
    private javax.swing.JPanel panel_Klienci;
    private javax.swing.JPanel panel_Moje_Dane;
    private javax.swing.JPanel panel_Produkty;
    private javax.swing.JPanel panel_Zamowienia;
    private javax.swing.JComboBox prod_sortuj;
    private javax.swing.JButton prod_szukaj;
    private javax.swing.JTextField prod_szukaj_co;
    private javax.swing.JTable prod_table;
    private javax.swing.JTabbedPane tabbed_zamowienia;
    private javax.swing.JPanel zam_hurt_panel;
    private javax.swing.JComboBox zam_hurt_sortuj;
    private javax.swing.JButton zam_hurt_szukaj;
    private javax.swing.JTextField zam_hurt_szukaj_co;
    private javax.swing.JTable zam_hurt_table;
    private javax.swing.JPanel zam_klienci_panel;
    private javax.swing.JComboBox zam_klienci_sortuj;
    private javax.swing.JButton zam_klienci_szukaj;
    private javax.swing.JTextField zam_klienci_szukaj_co;
    private javax.swing.JTable zam_klienci_table;
    private javax.swing.JButton zam_klienci_zamow;
    // End of variables declaration//GEN-END:variables
}
