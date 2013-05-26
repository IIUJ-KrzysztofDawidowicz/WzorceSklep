/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Login.java
 *
 * Created on 2012-03-12, 18:57:04
 */

package GUI;

import DataAccessObject.DataAccessObject;
import DataEntity.*;
import DataAccessObject.DAOFactory;
import TableConverter.*;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author user
 */
public class Admin extends javax.swing.JFrame {

    int aktywne_okno;
    Component okno;
    Connection connection = null;
    final JFrame thisframe=this;
    javax.swing.JDialog dialog;
    Action akcjaUsunPracownika;
    Action akcjaPracownikSzczegoly;
    
    /** Creates new form Login */
    public Admin(Connection con) { //Login (Coccenction connect,String login)
        /* nalezy pobierac informacje o:
         * - connection po zalogowaniu do sql aby nie paskudzic kodu kolejnym logowaniem do sql-a
         * - login aby moc okreslic kto jest zalogowany (np. niezbedne do statystyk pracownika)
         */
        connection = con;
                
        initComponents();
        declareUsuwaniePracownika();
        declarePracownikSzczegoly();
        panel_produkty.setVisible(false);
        panel_hurtowni_szczegoly.setVisible(false);
        panel_zamowienia.setVisible(false);
        panel_statystyka.setVisible(false);
        panel_klienci.setVisible(false);
        panel_pracownicy.setVisible(false);
        panel_hurtowni.setVisible(false);
        okno = panel_hurtowni;
        Warstwy.moveToFront(okno);
        //!!Sam przyklad tworzenia tabeli z guzikami, zbedne w tym miejscu i czasie!!
        //Trzeba zrozumiec i dostosowac do naszych potrzeb plik ButtonRenderer
        //TableExample jest potrzebne tylko do debugu, mozna pozniej wywalic (klase, nie plik)
        hurtownia_tabela.getColumn("Title 4").setCellRenderer(new ButtonRenderer());
        
                
        //Koniec tworzenia guzikow w tabeli
    }

    
//-------------------------------------------------------------------------------------------------------------------
    void show_panel(Component c){
        Warstwy.moveToBack(okno);
        Warstwy.moveToFront(c);
        okno.setVisible(false);
        c.setVisible(true);
        okno = c;
        //TODO: Convert to switch, refactoring.
        if(c==panel_pracownicy)
            refresh_pracownicy();
        else if(c==panel_klienci)
            refresh_klienci();
        else if(c==panel_hurtowni)
            refresh_hurtownie();
        else if (c==panel_produkty)
            refresh_produkty();
        else if (c==panel_statystyka)
            refresh_statystyki();
    }
    
//-------------------------------------------------------------------------------------------------------------------
    void refresh_zamowienia_klient()
    {
        /*
         * TODO: TableConverter.getTable
         *
         */

        /*String nazwisko,szukaj,sortuj;
        
        szukaj = zam_szukaj_co_klient.getText();
        if(!szukaj.equals("")){
            szukaj = " where  KImie like '%" + szukaj + "%' or KNazwisko like '%" + szukaj + "%' or Nazwa like '%" + szukaj
                    + "%' or Typ like '%" + szukaj + "%' or Ilosc like '%" + szukaj + "%' or Data like '%" + szukaj
                    + "%' or Kwota like '%" + szukaj + "%' or PImie like '%" + szukaj + "%' or PNazwisko like '%" + szukaj + "%'";   
        }
        //"Klient","Produkt","Typ","Ilość","Data","Kwota","Pracownik"
        sortuj = zam_sort_klient.getSelectedItem().toString();
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
            zam_klient_tab.setModel(model);
            zam_szukaj_co_klient.setText("");
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }*/
    }
//-------------------------------------------------------------------------------------------------------------------
    void refresh_zamowienia_hurt()
    {
        String szukaj,sortuj;
        
        szukaj = zam_szukaj_co_hurt.getText();
        if(!szukaj.equals("")){
                szukaj = " where Hurtownia like '%" + szukaj + "%' or Nazwa like '%" + szukaj + "%' or Typ like '%" + szukaj
                        + "%' or Ilosc like '%" + szukaj + "%' or Cena like '%" + szukaj
                        + "%' or Data_zamowienia like '%" + szukaj + "%' or Data_odebrania like '%" + szukaj + "%'";
        }
        
        //"Hurtownia","Produkt","Typ","Ilość","Kwota","Data zamowienia","Data odebrania"
        sortuj = zam_sortuj_hurt.getSelectedItem().toString();
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
            zam_hurt_tab.setModel(model);
            zam_szukaj_co_hurt.setText("");
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
    }
//-------------------------------------------------------------------------------------------------------------------
    void refresh_pracownicy() //Rysuje pracownicy_tabela
    {
        String orderBy=pracownicy_sortuj.getSelectedItem().toString();
        String lookFor=pracownicy_szukaj_co.getText();
        DataAccessObject<Pracownik> dao = DAOFactory.getPracownikDAO();
        List<Pracownik> pracownikList = new LinkedList<Pracownik>();
        try {
            if(lookFor.isEmpty())
                pracownikList = dao.select(orderBy);
            else
                pracownikList = dao.select(lookFor, orderBy);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Pracownik[] pracownicy = new Pracownik[pracownikList.size()];
        pracownikList.toArray(pracownicy);
        TableConverter<Pracownik> converter = new PracownicyTableConverter();
        TableModel model = converter.getTableModel(pracownicy);

        pracownicy_tabela.setModel(model);
        pracownikSzczegolyColumnn=new ButtonColumn(pracownicy_tabela,akcjaPracownikSzczegoly,4);
        usunPracownikaColumnn=new ButtonColumn(pracownicy_tabela,akcjaUsunPracownika,5);
        pracownicy_szukaj_co.setText(""); //Czyści okienko
        /*if(order_by.equals("ID")) order_by="ID_Pracownika";
        if(!search_by.isEmpty()) 
            search_by=" where Imie like '%"+search_by+"%' or Nazwisko like '%"+search_by+"%' or Login like '%"+search_by+"%' or Telefon like '%"+search_by+"%'";
        
        int rowcount=0;
        int indeks_tabeli=0;
        try
        {
            Statement stat = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //Tak stworzont ResultSet będzie scrollable, czyli będzie się można po nim dowolnie poruszać
            ResultSet rs = stat.executeQuery("use Sklep;select ID_Pracownika, Imie, Nazwisko, Login, Telefon from Pracownicy"+search_by+" order by "+order_by);
            if(rs.last())
            {
                rowcount=rs.getRow(); //Pobieramy numer ostatniego rzędu, czyli rozmiar tablicy
                rs.beforeFirst();
            }
            TableModel model=new DefaultTableModel(new String[]{"ID","Nazwisko","login","Telefon", "", ""}, rowcount)
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) 
                {
                    if (colIndex<=4)
                        return true;   
                    else
                        return false;
                }
            };

            String nazwisko;
            while(rs.next())
            {
                nazwisko=rs.getString("Nazwisko");
                nazwisko+=" "+rs.getString("Imie");
                model.setValueAt(rs.getInt("ID_Pracownika"), indeks_tabeli, 0);
                model.setValueAt(nazwisko, indeks_tabeli, 1);
                model.setValueAt(rs.getString("Login"), indeks_tabeli, 2);
                model.setValueAt(rs.getString("Telefon"),indeks_tabeli,3);
                model.setValueAt("Szczegóły", indeks_tabeli, 4);
                model.setValueAt("Usuń", indeks_tabeli, 5);
                indeks_tabeli++;
            }
            pracownicy_tabela.setModel(model);
            pracownikSzczegolyColumnn=new ButtonColumn(pracownicy_tabela,akcjaPracownikSzczegoly,4);
            usunPracownikaColumnn=new ButtonColumn(pracownicy_tabela,akcjaUsunPracownika,5);
            pracownicy_szukaj_co.setText(""); //Czyści okienko
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }*/
    }
    
//-------------------------------------------------------------------------------------------------------------------
    void refresh_klienci() //Rysuje klienci_tabela
    {
        String orderBy=klienci_sortuj.getSelectedItem().toString();
        String lookFor=klienci_szukaj_co.getText();
        DataAccessObject<Klient> dataAccessObject = DAOFactory.getKlientDAO();
        List<Klient> list;
        try {
            if(lookFor.isEmpty())
                list=dataAccessObject.select(orderBy);
            else
                list = dataAccessObject.select(lookFor, orderBy);
        } catch (SQLException e) {
            e.printStackTrace();
            list = new LinkedList<Klient>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            list = new LinkedList<Klient>();
        }
        Klient[] klients = new Klient[list.size()];
        list.toArray(klients);
        TableConverter<Klient> tableConverter = new KlienciTableConverter();
        TableModel model= tableConverter.getTableModel(klients);
        klienci_tabela.setModel(model);
        klienci_szukaj_co.setText("");
    }

    void refresh_hurtownie() //Rysuje hurtownia_tabela
    {
        //String order_by=klienci_sortuj.getSelectedItem().toString();
        String search_by=hurtownia_szukaj_co.getText();
       // if(order_by.equals("ID")) order_by="ID_Hurtowni";
        if(!search_by.isEmpty()) 
            search_by=" where Nazwa like '%"+search_by+"%' or Osoba_kontaktowa like '%"+search_by+"%' or Telefon like '%"+search_by+"%' or Mail like '%"+search_by+"%'";
        int indeks_tabeli=0;
        int rowcount=0;
        try
        {
            Statement stat = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stat.executeQuery("use Sklep;select ID_Hurtowni, Nazwa, Osoba_kontaktowa, Telefon, Mail from Hurtownie"+search_by);
            if(rs.last())
            {
                rowcount=rs.getRow();
                rs.beforeFirst();
            }
            TableModel model=new DefaultTableModel(new String[]{"ID","Nazwa","Osoba kontaktowa","Telefon", "mail"}, rowcount)
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            
            while(rs.next())
            {
                model.setValueAt(rs.getInt("ID_Hurtowni"), indeks_tabeli, 0);
                model.setValueAt(rs.getString("Nazwa"), indeks_tabeli, 1);
                model.setValueAt(rs.getString("Osoba_kontaktowa"), indeks_tabeli, 2);
                model.setValueAt(rs.getString("Telefon"),indeks_tabeli,3);
                model.setValueAt(rs.getString("Mail"),indeks_tabeli,4);
                indeks_tabeli++;
            }
            hurtownia_tabela.setModel(model);
            hurtownia_szukaj_co.setText("");
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
    }
    
    void refresh_produkty() //Rysuje klienci_tabela
    {
        //String order_by=produkty_sortuj.getSelectedItem().toString();
        String search_by=produkty_szukaj_co.getText();
        /*if(order_by.equals("ID")) order_by="ID_Produktu";*/
        if(!search_by.isEmpty()) 
            search_by=" where Typ like '%"+search_by+"%' or Nazwa like '%"+search_by+"%'";
        int indeks_tabeli=0;
        int rowcount=0;
        try
        {
            Statement stat = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stat.executeQuery("use Sklep;select ID_Produktu, Typ, Cena, Nazwa, Ilosc, Specyfikacja from Produkty"+search_by);
            if(rs.last())
            {
                rowcount=rs.getRow();
                rs.beforeFirst();
            }
            TableModel model=new DefaultTableModel(new String[]{"ID","Typ","Cena","Nazwa", "Ilosc", "Specyfikacja"}, rowcount)
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            
            while(rs.next())
            {
                model.setValueAt(rs.getInt("ID_Produktu"), indeks_tabeli, 0);
                 model.setValueAt(rs.getString("Typ"), indeks_tabeli, 1);
                model.setValueAt(rs.getString("Cena"), indeks_tabeli, 2);
                model.setValueAt(rs.getString("Nazwa"),indeks_tabeli,3);
                 model.setValueAt(rs.getInt("Ilosc"), indeks_tabeli, 4);
                  model.setValueAt(rs.getString("Specyfikacja"), indeks_tabeli, 5);
                indeks_tabeli++;
            }
            produkty_tabela.setModel(model);
            produkty_szukaj_co.setText("");
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
    }
    
        
    void refresh_statystyki()
    {
        //PRODUKTY
        
        //OGOLEM
        int i=0;
        try
        {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_produkty");
            rs.next();
            TableModel model1=new DefaultTableModel(new String[]{
                "ID","Nazwa","Zamowienia","Ilość","Kwota"            
            }, rs.getInt(1))
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_produkty");
            while(rs.next())
            {;
                model1.setValueAt(rs.getString("ID_Produktu"), i, 0);
                model1.setValueAt(rs.getString("Nazwa"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);
                model1.setValueAt(rs.getInt("Ilosc"), i, 3);
                model1.setValueAt(rs.getString("Kwota"), i, 4);
                
                i++;
            }
            statystka_produkty_ogolem.setModel(model1);
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
        
        
        //TYGODNIOWO
        i=0;
        try
        {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_produkty_tygodniowo");
            rs.next();
            TableModel model1=new DefaultTableModel(new String[]{
                "ID","Nazwa","Zamowienia","Ilość","Kwota"            
            }, rs.getInt(1))
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_produkty_tygodniowo");
            while(rs.next())
            {;
                model1.setValueAt(rs.getString("ID_Produktu"), i, 0);
                model1.setValueAt(rs.getString("Nazwa"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);
                model1.setValueAt(rs.getInt("Ilosc"), i, 3);
                model1.setValueAt(rs.getString("Kwota"), i, 4);
                
                i++;
            }
            statystka_produkty_tygodniowo.setModel(model1);
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
        
        
        //MIESIECZNIE
           i=0;
        try
        {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_produkty_miesiecznie");
            rs.next();
            TableModel model1=new DefaultTableModel(new String[]{
                "ID","Nazwa","Zamowienia","Ilość","Kwota"            
            }, rs.getInt(1))
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_produkty_miesiecznie");
            while(rs.next())
            {;
                model1.setValueAt(rs.getString("ID_Produktu"), i, 0);
                model1.setValueAt(rs.getString("Nazwa"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);
                model1.setValueAt(rs.getInt("Ilosc"), i, 3);
                model1.setValueAt(rs.getString("Kwota"), i, 4);
                
                i++;
            }
            statystka_produkty_miesiecznie.setModel(model1);
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
        
        
        //ROCZNIE
           i=0;
        try
        {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_produkty_rocznie");
            rs.next();
            TableModel model1=new DefaultTableModel(new String[]{
                "ID","Nazwa","Zamowienia","Ilość","Kwota"            
            }, rs.getInt(1))
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_produkty_rocznie");
            while(rs.next())
            {;
                model1.setValueAt(rs.getString("ID_Produktu"), i, 0);
                model1.setValueAt(rs.getString("Nazwa"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);
                model1.setValueAt(rs.getInt("Ilosc"), i, 3);
                model1.setValueAt(rs.getString("Kwota"), i, 4);
                
                i++;
            }
            statystka_produkty_rocznie.setModel(model1);
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
        
        
        //KLIENCI
        
        //OGOLEM
        i=0;
        try
        {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_klienci");
            rs.next();
            TableModel model1=new DefaultTableModel(new String[]{
                "ID","Login","Zamowienia"           
            }, rs.getInt(1))
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_klienci");
            while(rs.next())
            {;
                model1.setValueAt(rs.getString("ID_Klienta"), i, 0);
                model1.setValueAt(rs.getString("Login"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);
                
                i++;
            }
            statystka_klienci_ogolem.setModel(model1);
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
        
        //TYGODNIOWO
                i=0;
        try
        {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_klienci_tygodniowo");
            rs.next();
            TableModel model1=new DefaultTableModel(new String[]{
                "ID","Login","Zamowienia"           
            }, rs.getInt(1))
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_klienci_tygodniowo");
            while(rs.next())
            {;
                model1.setValueAt(rs.getString("ID_Klienta"), i, 0);
                model1.setValueAt(rs.getString("Login"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);
                
                i++;
            }
            statystka_klienci_tygodniowo.setModel(model1);
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
        
        //MIESIECZNIE
                i=0;
        try
        {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_klienci_miesiecznie");
            rs.next();
            TableModel model1=new DefaultTableModel(new String[]{
                "ID","Login","Zamowienia"           
            }, rs.getInt(1))
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_klienci_miesiecznie");
            while(rs.next())
            {;
                model1.setValueAt(rs.getString("ID_Klienta"), i, 0);
                model1.setValueAt(rs.getString("Login"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);
                
                i++;
            }
            statystka_klienci_miesiecznie.setModel(model1);
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
        
        
       //ROCZNIE
                i=0;
        try
        {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_klienci_rocznie");
            rs.next();
            TableModel model1=new DefaultTableModel(new String[]{
                "ID","Login","Zamowienia"           
            }, rs.getInt(1))
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_klienci_rocznie");
            while(rs.next())
            {;
                model1.setValueAt(rs.getString("ID_Klienta"), i, 0);
                model1.setValueAt(rs.getString("Login"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);
                
                i++;
            }
            statystka_klienci_rocznie.setModel(model1);
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
        
        
        //HURTOWNIE
        
        //OGOLEM
        i=0;
        try
        {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_hurtownie");
            rs.next();
            TableModel model1=new DefaultTableModel(new String[]{
                "ID","Nazwa","Zamowienia"           
            }, rs.getInt(1))
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_hurtownie");
            while(rs.next())
            {;
                model1.setValueAt(rs.getString("ID_Hurtowni"), i, 0);
                model1.setValueAt(rs.getString("Nazwa"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);
                
                i++;
            }
            statystka_hurtownie_ogolem.setModel(model1);
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
        
        //TYGODNIOWO
                i=0;
        try
        {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_hurtownie_tygodniowo");
            rs.next();
            TableModel model1=new DefaultTableModel(new String[]{
                "ID","Nazwa","Zamowienia"           
            }, rs.getInt(1))
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_hurtownie_tygodniowo");
            while(rs.next())
            {;
                model1.setValueAt(rs.getString("ID_Hurtowni"), i, 0);
                model1.setValueAt(rs.getString("Nazwa"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);
                
                i++;
            }
            statystka_hurtownie_tygodniowo.setModel(model1);
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
        
        //MIESIECZNIE
                i=0;
        try
        {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_hurtownie_miesiecznie");
            rs.next();
            TableModel model1=new DefaultTableModel(new String[]{
                "ID","Nazwa","Zamowienia"           
            }, rs.getInt(1))
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_hurtownie_miesiecznie");
            while(rs.next())
            {;
                model1.setValueAt(rs.getString("ID_Hurtowni"), i, 0);
                model1.setValueAt(rs.getString("Nazwa"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);
                
                i++;
            }
            statystka_hurtownie_miesiecznie.setModel(model1);
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
        
        //ROCZNIE
                i=0;
        try
        {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_hurtownie_rocznie");
            rs.next();
            TableModel model1=new DefaultTableModel(new String[]{
                "ID","Nazwa","Zamowienia"           
            }, rs.getInt(1))
            {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_hurtownie_rocznie");
            while(rs.next())
            {;
                model1.setValueAt(rs.getString("ID_Hurtowni"), i, 0);
                model1.setValueAt(rs.getString("Nazwa"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);
                
                i++;
            }
            statystka_hurtownie_rocznie.setModel(model1);
        }
        catch(Exception e)
        {
            e.printStackTrace(); 
        }
    }
    //Odświerza tabele od odpowiedniego panelu
    {//można przyjmowac stringa (panel.getname()) czy jakos tak
    //void refresh(int i){}
    
    //Odswierzenie konkretnej tabeli
    //void refresh_nazwa_tabeli(){}
    //mozna pozamieniac refresh na odswierz by bylo bardziej w jednym jezyku
    /*Wewnatrz nalezy umiescic:
     * try{ - calosc nalezy sprawdzac try{}catch
     * Statement stat - funkcje wewnatrz sluza do wykonywania komend sql
     * stat = connection.createStatement(); - sluzy do stworzenia Statement
     * nalezy wykonac ResultSet rs = stat.executeQuery("SELECT count(*) FROM...), aby zliczyc wiersze
     * int ile_wierszy = rs.next().getInt(1) //nie mam pewnosci co mam byc w nawiasie, uzupelnie
     * TableModel model = new DefaultTableModel(new String[]{ "Tutuaj","nazwy",kolumn"}, int ile_wierszy){
     *      tutaj dajemy Override do funkcji ktore potrzebujemy:
     *      - brak edycji komorek
     *      - i chyba operacje na komurce z guzikiem (o ile jest), a jak nie to tak jak w konstruktorze
     * ResultSet rs = stat.executeQuery("kod sql") - slozy do zwracania wartosci z select
     * najlepiej iterowac while(rs.next()){} - w ten sposob bedziemy przemieszczac sie
     *                                         po kazdym wierszu jakie pobral
     * Przyklad: (tytul - jest to wczesniej stworzony String)
     * try{ -- nie mam pewnosci czy potzreba :P
     * tytul = rs.getString("TytulPrzetlumaczony"); - pobiera String z kolumny TytulPrzetlumaczony
     * model.setValueAt(tytul, i, 3); - ustawia wartosci w naszej tymczasowej tabeli (TableModel)
     * } catch (Exception e) { -- jesli oczekujemy null nalezy to wychwycic
     * }
     * (tytul, i, 3) - odpowiednio ( wrtosc do umieszczenia, wiersz do ktorego umieszczamy, nr kolumny [od 0])
     * i++ - nalezy samemu zapamietywac wiersz
     * 
     * za while(){}
     * table_nasza_juz stworzona_tabela = model; - tu juz nic nie zmieniamy, to model am byc w calosci dopracowany
     * } catch (Exception e) {
     *    e.printStackTrace(); -- wypisuje CALA sciezke w ktorej gdzies wystapil blad (nie wszystko tyczy sie samej javy)
     * }
    */}
    
    
    //Ponizej znajduje sie automatycznie generowany kod do tworzenia tabeli/paneli/przyciskow itp.
    //A pod nimi sa dodawane przez nas eventy
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jFrame3 = new javax.swing.JFrame();
        jFrame4 = new javax.swing.JFrame();
        jFrame5 = new javax.swing.JFrame();
        jFrame6 = new javax.swing.JFrame();
        jDialog1 = new javax.swing.JDialog();
        jFrame7 = new javax.swing.JFrame();
        jFrame8 = new javax.swing.JFrame();
        guzik_hurtownia = new javax.swing.JButton();
        guzik_produkty = new javax.swing.JButton();
        guzik_zamowienia = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        guzik_statystyka = new javax.swing.JButton();
        guzik_klienci = new javax.swing.JButton();
        guzik_pracownicy = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        Warstwy = new javax.swing.JLayeredPane();
        panel_hurtowni = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        hurtownia_tabela = new javax.swing.JTable();
        hurtownia_szukaj_co = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        hurtownia_dodaj = new javax.swing.JButton();
        panel_hurtowni_szczegoly = new javax.swing.JPanel();
        panel_produkty = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        produkty_tabela = new javax.swing.JTable();
        produkty_szukaj_co = new javax.swing.JTextField();
        produkty_szukaj = new javax.swing.JButton();
        produkty_dodaj = new javax.swing.JButton();
        panel_zamowienia = new javax.swing.JPanel();
        zamowenia_tabbed = new javax.swing.JTabbedPane();
        zam_klient = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        zam_klient_tab = new javax.swing.JTable();
        zam_szukaj_co_klient = new javax.swing.JTextField();
        zam_szukaj_klient = new javax.swing.JButton();
        zam_sort_klient = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        zam_hurt = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        zam_hurt_tab = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        zam_szukaj_co_hurt = new javax.swing.JTextField();
        zam_szukaj_hurt = new javax.swing.JButton();
        zam_sortuj_hurt = new javax.swing.JComboBox();
        panel_statystyka = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        staty_ogolem = new javax.swing.JPanel();
        Klienci8 = new javax.swing.JLabel();
        jScrollPane65 = new javax.swing.JScrollPane();
        statystka_hurtownie_ogolem = new javax.swing.JTable();
        jScrollPane66 = new javax.swing.JScrollPane();
        statystka_produkty_ogolem = new javax.swing.JTable();
        Hurtownie8 = new javax.swing.JLabel();
        Produkty8 = new javax.swing.JLabel();
        jScrollPane67 = new javax.swing.JScrollPane();
        statystka_klienci_ogolem = new javax.swing.JTable();
        staty_tygodniwo = new javax.swing.JPanel();
        Hurtownie9 = new javax.swing.JLabel();
        jScrollPane69 = new javax.swing.JScrollPane();
        statystka_hurtownie_tygodniowo = new javax.swing.JTable();
        jScrollPane68 = new javax.swing.JScrollPane();
        statystka_produkty_tygodniowo = new javax.swing.JTable();
        Produkty9 = new javax.swing.JLabel();
        jScrollPane70 = new javax.swing.JScrollPane();
        statystka_klienci_tygodniowo = new javax.swing.JTable();
        Klienci9 = new javax.swing.JLabel();
        staty_miesiecznie = new javax.swing.JPanel();
        Produkty10 = new javax.swing.JLabel();
        Hurtownie10 = new javax.swing.JLabel();
        jScrollPane73 = new javax.swing.JScrollPane();
        statystka_klienci_miesiecznie = new javax.swing.JTable();
        Klienci10 = new javax.swing.JLabel();
        jScrollPane72 = new javax.swing.JScrollPane();
        statystka_hurtownie_miesiecznie = new javax.swing.JTable();
        jScrollPane71 = new javax.swing.JScrollPane();
        statystka_produkty_miesiecznie = new javax.swing.JTable();
        staty_rocznie = new javax.swing.JPanel();
        jScrollPane74 = new javax.swing.JScrollPane();
        statystka_produkty_rocznie = new javax.swing.JTable();
        Hurtownie11 = new javax.swing.JLabel();
        Klienci11 = new javax.swing.JLabel();
        Produkty11 = new javax.swing.JLabel();
        jScrollPane75 = new javax.swing.JScrollPane();
        statystka_hurtownie_rocznie = new javax.swing.JTable();
        jScrollPane76 = new javax.swing.JScrollPane();
        statystka_klienci_rocznie = new javax.swing.JTable();
        panel_klienci = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        klienci_tabela = new javax.swing.JTable();
        klienci_szukaj_co = new javax.swing.JTextField();
        klienci_szukaj = new javax.swing.JButton();
        klienci_sortuj = new javax.swing.JComboBox();
        panel_pracownicy = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        pracownicy_tabela = new javax.swing.JTable();
        pracownicy_szukaj_co = new javax.swing.JTextField();
        pracownicy_szukaj = new javax.swing.JButton();
        pracownicy_sortuj = new javax.swing.JComboBox();
        pracownicy_dodaj = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame4Layout = new javax.swing.GroupLayout(jFrame4.getContentPane());
        jFrame4.getContentPane().setLayout(jFrame4Layout);
        jFrame4Layout.setHorizontalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame4Layout.setVerticalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame5Layout = new javax.swing.GroupLayout(jFrame5.getContentPane());
        jFrame5.getContentPane().setLayout(jFrame5Layout);
        jFrame5Layout.setHorizontalGroup(
            jFrame5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame5Layout.setVerticalGroup(
            jFrame5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame6Layout = new javax.swing.GroupLayout(jFrame6.getContentPane());
        jFrame6.getContentPane().setLayout(jFrame6Layout);
        jFrame6Layout.setHorizontalGroup(
            jFrame6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame6Layout.setVerticalGroup(
            jFrame6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame7Layout = new javax.swing.GroupLayout(jFrame7.getContentPane());
        jFrame7.getContentPane().setLayout(jFrame7Layout);
        jFrame7Layout.setHorizontalGroup(
            jFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame7Layout.setVerticalGroup(
            jFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame8Layout = new javax.swing.GroupLayout(jFrame8.getContentPane());
        jFrame8.getContentPane().setLayout(jFrame8Layout);
        jFrame8Layout.setHorizontalGroup(
            jFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame8Layout.setVerticalGroup(
            jFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        guzik_hurtownia.setText("Hurtownie");
        guzik_hurtownia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guzik_hurtowniaMouseClicked(evt);
            }
        });

        guzik_produkty.setText("Produkty");
        guzik_produkty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guzik_produktyMouseClicked(evt);
            }
        });
        guzik_produkty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guzik_produktyActionPerformed(evt);
            }
        });

        guzik_zamowienia.setText("Zamówienia");
        guzik_zamowienia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guzik_zamowieniaMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe Script", 3, 24)); // NOI18N
        jLabel1.setText("Sklep");

        guzik_statystyka.setText("Statystyka sprzedaży");
        guzik_statystyka.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guzik_statystykaMouseClicked(evt);
            }
        });

        guzik_klienci.setText("Klienci");
        guzik_klienci.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guzik_klienciMouseClicked(evt);
            }
        });

        guzik_pracownicy.setText("Pracownicy");
        guzik_pracownicy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guzik_pracownicyMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Raavi", 1, 12)); // NOI18N
        jLabel8.setText("Admin");

        hurtownia_tabela.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(hurtownia_tabela);

        jButton1.setText("Szukaj");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        hurtownia_dodaj.setText("Dodaj hurtownię");
        hurtownia_dodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hurtownia_dodajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_hurtowniLayout = new javax.swing.GroupLayout(panel_hurtowni);
        panel_hurtowni.setLayout(panel_hurtowniLayout);
        panel_hurtowniLayout.setHorizontalGroup(
            panel_hurtowniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(panel_hurtowniLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hurtownia_szukaj_co, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 330, Short.MAX_VALUE)
                .addComponent(hurtownia_dodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_hurtowniLayout.setVerticalGroup(
            panel_hurtowniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hurtowniLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(panel_hurtowniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hurtownia_szukaj_co, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(hurtownia_dodaj))
                .addContainerGap())
        );

        panel_hurtowni.setBounds(0, 0, 860, 580);
        Warstwy.add(panel_hurtowni, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panel_hurtowni_szczegolyLayout = new javax.swing.GroupLayout(panel_hurtowni_szczegoly);
        panel_hurtowni_szczegoly.setLayout(panel_hurtowni_szczegolyLayout);
        panel_hurtowni_szczegolyLayout.setHorizontalGroup(
            panel_hurtowni_szczegolyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 860, Short.MAX_VALUE)
        );
        panel_hurtowni_szczegolyLayout.setVerticalGroup(
            panel_hurtowni_szczegolyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        panel_hurtowni_szczegoly.setBounds(0, 0, 860, 580);
        Warstwy.add(panel_hurtowni_szczegoly, javax.swing.JLayeredPane.DEFAULT_LAYER);

        produkty_tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        jScrollPane2.setViewportView(produkty_tabela);

        produkty_szukaj.setText("Szukaj");
        produkty_szukaj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                produkty_szukajMouseClicked(evt);
            }
        });

        produkty_dodaj.setText("Dodaj produkt");
        produkty_dodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produkty_dodajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_produktyLayout = new javax.swing.GroupLayout(panel_produkty);
        panel_produkty.setLayout(panel_produktyLayout);
        panel_produktyLayout.setHorizontalGroup(
            panel_produktyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(panel_produktyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(produkty_szukaj_co, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(produkty_szukaj, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(produkty_dodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_produktyLayout.setVerticalGroup(
            panel_produktyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_produktyLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_produktyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(produkty_szukaj_co, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produkty_szukaj)
                    .addComponent(produkty_dodaj))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        panel_produkty.setBounds(0, 0, 860, 580);
        Warstwy.add(panel_produkty, javax.swing.JLayeredPane.DEFAULT_LAYER);

        zam_klient_tab.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(zam_klient_tab);

        zam_szukaj_klient.setText("Szukaj");

        zam_sort_klient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Klient","Produkt","Typ","Ilość","Data","Kwota","Pracownik" }));
        zam_sort_klient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zam_sort_klientActionPerformed(evt);
            }
        });

        jLabel2.setText("Szukaj wegług: ");

        javax.swing.GroupLayout zam_klientLayout = new javax.swing.GroupLayout(zam_klient);
        zam_klient.setLayout(zam_klientLayout);
        zam_klientLayout.setHorizontalGroup(
            zam_klientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
            .addGroup(zam_klientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(zam_szukaj_co_klient, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(zam_szukaj_klient, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zam_sort_klient, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        zam_klientLayout.setVerticalGroup(
            zam_klientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zam_klientLayout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(zam_klientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zam_szukaj_co_klient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zam_szukaj_klient)
                    .addComponent(zam_sort_klient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        zamowenia_tabbed.addTab("Klient", zam_klient);

        zam_hurt_tab.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(zam_hurt_tab);

        jLabel3.setText("Szukaj wegług: ");

        zam_szukaj_hurt.setText("Szukaj");
        zam_szukaj_hurt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zam_szukaj_hurtMouseClicked(evt);
            }
        });

        zam_sortuj_hurt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hurtownia","Produkt","Typ","Ilość","Kwota","Data zamowienia","Data odebrania" }));
        zam_sortuj_hurt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zam_sortuj_hurtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout zam_hurtLayout = new javax.swing.GroupLayout(zam_hurt);
        zam_hurt.setLayout(zam_hurtLayout);
        zam_hurtLayout.setHorizontalGroup(
            zam_hurtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, zam_hurtLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(zam_szukaj_co_hurt, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(zam_szukaj_hurt, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zam_sortuj_hurt, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        zam_hurtLayout.setVerticalGroup(
            zam_hurtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zam_hurtLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(zam_hurtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zam_szukaj_co_hurt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zam_szukaj_hurt)
                    .addComponent(zam_sortuj_hurt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(25, 25, 25))
        );

        zamowenia_tabbed.addTab("Hurtownia", zam_hurt);

        javax.swing.GroupLayout panel_zamowieniaLayout = new javax.swing.GroupLayout(panel_zamowienia);
        panel_zamowienia.setLayout(panel_zamowieniaLayout);
        panel_zamowieniaLayout.setHorizontalGroup(
            panel_zamowieniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_zamowieniaLayout.createSequentialGroup()
                .addComponent(zamowenia_tabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 872, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel_zamowieniaLayout.setVerticalGroup(
            panel_zamowieniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_zamowieniaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(zamowenia_tabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        panel_zamowienia.setBounds(0, 0, 860, 580);
        Warstwy.add(panel_zamowienia, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Klienci8.setText("Klienci");

        statystka_hurtownie_ogolem.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane65.setViewportView(statystka_hurtownie_ogolem);

        statystka_produkty_ogolem.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane66.setViewportView(statystka_produkty_ogolem);

        Hurtownie8.setText("Hurtownie");

        Produkty8.setText("Produkty");

        statystka_klienci_ogolem.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane67.setViewportView(statystka_klienci_ogolem);

        javax.swing.GroupLayout staty_ogolemLayout = new javax.swing.GroupLayout(staty_ogolem);
        staty_ogolem.setLayout(staty_ogolemLayout);
        staty_ogolemLayout.setHorizontalGroup(
            staty_ogolemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staty_ogolemLayout.createSequentialGroup()
                .addComponent(jScrollPane66, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane67, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane65, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(staty_ogolemLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(Produkty8, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179)
                .addComponent(Klienci8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(Hurtownie8)
                .addGap(201, 201, 201))
        );
        staty_ogolemLayout.setVerticalGroup(
            staty_ogolemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staty_ogolemLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(staty_ogolemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Produkty8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(staty_ogolemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Klienci8)
                        .addComponent(Hurtownie8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(staty_ogolemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane65, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(staty_ogolemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane67, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                        .addComponent(jScrollPane66, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(0, 37, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ogółem", staty_ogolem);

        Hurtownie9.setText("Hurtownie");

        statystka_hurtownie_tygodniowo.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane69.setViewportView(statystka_hurtownie_tygodniowo);

        statystka_produkty_tygodniowo.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane68.setViewportView(statystka_produkty_tygodniowo);

        Produkty9.setText("Produkty");

        statystka_klienci_tygodniowo.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane70.setViewportView(statystka_klienci_tygodniowo);

        Klienci9.setText("Klienci");

        javax.swing.GroupLayout staty_tygodniwoLayout = new javax.swing.GroupLayout(staty_tygodniwo);
        staty_tygodniwo.setLayout(staty_tygodniwoLayout);
        staty_tygodniwoLayout.setHorizontalGroup(
            staty_tygodniwoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staty_tygodniwoLayout.createSequentialGroup()
                .addComponent(jScrollPane68, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane70, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane69, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(staty_tygodniwoLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(Produkty9, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179)
                .addComponent(Klienci9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(Hurtownie9)
                .addGap(201, 201, 201))
        );
        staty_tygodniwoLayout.setVerticalGroup(
            staty_tygodniwoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staty_tygodniwoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(staty_tygodniwoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Produkty9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(staty_tygodniwoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Klienci9)
                        .addComponent(Hurtownie9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(staty_tygodniwoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane69, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(staty_tygodniwoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane70, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                        .addComponent(jScrollPane68, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(0, 37, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tygodniowo", staty_tygodniwo);

        Produkty10.setText("Produkty");

        Hurtownie10.setText("Hurtownie");

        statystka_klienci_miesiecznie.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane73.setViewportView(statystka_klienci_miesiecznie);

        Klienci10.setText("Klienci");

        statystka_hurtownie_miesiecznie.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane72.setViewportView(statystka_hurtownie_miesiecznie);

        statystka_produkty_miesiecznie.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane71.setViewportView(statystka_produkty_miesiecznie);

        javax.swing.GroupLayout staty_miesiecznieLayout = new javax.swing.GroupLayout(staty_miesiecznie);
        staty_miesiecznie.setLayout(staty_miesiecznieLayout);
        staty_miesiecznieLayout.setHorizontalGroup(
            staty_miesiecznieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staty_miesiecznieLayout.createSequentialGroup()
                .addComponent(jScrollPane71, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane73, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane72, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(staty_miesiecznieLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(Produkty10, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179)
                .addComponent(Klienci10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(Hurtownie10)
                .addGap(201, 201, 201))
        );
        staty_miesiecznieLayout.setVerticalGroup(
            staty_miesiecznieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staty_miesiecznieLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(staty_miesiecznieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Produkty10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(staty_miesiecznieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Klienci10)
                        .addComponent(Hurtownie10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(staty_miesiecznieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane72, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(staty_miesiecznieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane73, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                        .addComponent(jScrollPane71, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(0, 37, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Miesięcznie", staty_miesiecznie);

        statystka_produkty_rocznie.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane74.setViewportView(statystka_produkty_rocznie);

        Hurtownie11.setText("Hurtownie");

        Klienci11.setText("Klienci");

        Produkty11.setText("Produkty");

        statystka_hurtownie_rocznie.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane75.setViewportView(statystka_hurtownie_rocznie);

        statystka_klienci_rocznie.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane76.setViewportView(statystka_klienci_rocznie);

        javax.swing.GroupLayout staty_rocznieLayout = new javax.swing.GroupLayout(staty_rocznie);
        staty_rocznie.setLayout(staty_rocznieLayout);
        staty_rocznieLayout.setHorizontalGroup(
            staty_rocznieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staty_rocznieLayout.createSequentialGroup()
                .addComponent(jScrollPane74, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane76, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane75, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(staty_rocznieLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(Produkty11, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179)
                .addComponent(Klienci11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(Hurtownie11)
                .addGap(201, 201, 201))
        );
        staty_rocznieLayout.setVerticalGroup(
            staty_rocznieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staty_rocznieLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(staty_rocznieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Produkty11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(staty_rocznieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Klienci11)
                        .addComponent(Hurtownie11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(staty_rocznieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane75, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(staty_rocznieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane76, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                        .addComponent(jScrollPane74, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(0, 37, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Rocznie", staty_rocznie);

        javax.swing.GroupLayout panel_statystykaLayout = new javax.swing.GroupLayout(panel_statystyka);
        panel_statystyka.setLayout(panel_statystykaLayout);
        panel_statystykaLayout.setHorizontalGroup(
            panel_statystykaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        panel_statystykaLayout.setVerticalGroup(
            panel_statystykaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_statystykaLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );

        panel_statystyka.setBounds(0, 0, 860, 580);
        Warstwy.add(panel_statystyka, javax.swing.JLayeredPane.DEFAULT_LAYER);

        klienci_tabela.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(klienci_tabela);

        klienci_szukaj.setText("Szukaj");
        klienci_szukaj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                klienci_szukajMouseClicked(evt);
            }
        });

        klienci_sortuj.setModel(new javax.swing.DefaultComboBoxModel(new String[]  { "ID", "Nazwisko", "Login" }));

        javax.swing.GroupLayout panel_klienciLayout = new javax.swing.GroupLayout(panel_klienci);
        panel_klienci.setLayout(panel_klienciLayout);
        panel_klienciLayout.setHorizontalGroup(
            panel_klienciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
            .addGroup(panel_klienciLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_klienciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_klienciLayout.createSequentialGroup()
                        .addComponent(klienci_szukaj_co, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(klienci_szukaj, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(klienci_sortuj, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panel_klienciLayout.setVerticalGroup(
            panel_klienciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_klienciLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_klienciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(klienci_szukaj)
                    .addComponent(klienci_szukaj_co, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(klienci_sortuj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 28, Short.MAX_VALUE))
        );

        panel_klienci.setBounds(0, 0, 860, 580);
        Warstwy.add(panel_klienci, javax.swing.JLayeredPane.DEFAULT_LAYER);

        pracownicy_tabela.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(pracownicy_tabela);

        pracownicy_szukaj.setText("Szukaj");
        pracownicy_szukaj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pracownicy_szukajMouseClicked(evt);
            }
        });

        pracownicy_sortuj.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID", "Nazwisko", "Login" }));

        pracownicy_dodaj.setText("Dodaj pracownika");
        pracownicy_dodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pracownicy_dodajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_pracownicyLayout = new javax.swing.GroupLayout(panel_pracownicy);
        panel_pracownicy.setLayout(panel_pracownicyLayout);
        panel_pracownicyLayout.setHorizontalGroup(
            panel_pracownicyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(panel_pracownicyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_pracownicyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_pracownicyLayout.createSequentialGroup()
                        .addComponent(pracownicy_szukaj_co, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pracownicy_szukaj, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pracownicy_sortuj, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
                .addComponent(pracownicy_dodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_pracownicyLayout.setVerticalGroup(
            panel_pracownicyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pracownicyLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_pracownicyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pracownicy_szukaj_co, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pracownicy_szukaj)
                    .addComponent(pracownicy_dodaj))
                .addGap(18, 18, 18)
                .addComponent(pracownicy_sortuj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        panel_pracownicy.setBounds(0, 0, 860, 580);
        Warstwy.add(panel_pracownicy, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 860, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(guzik_pracownicy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(guzik_klienci, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(guzik_hurtownia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(guzik_zamowienia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(guzik_statystyka, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(guzik_produkty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Warstwy))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(guzik_hurtownia, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guzik_produkty, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guzik_zamowienia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guzik_statystyka, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guzik_klienci, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guzik_pracownicy, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Warstwy, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guzik_hurtowniaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guzik_hurtowniaMouseClicked
        show_panel(panel_hurtowni);
    }//GEN-LAST:event_guzik_hurtowniaMouseClicked

    private void guzik_produktyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guzik_produktyMouseClicked
        show_panel(panel_produkty);
    }//GEN-LAST:event_guzik_produktyMouseClicked

    private void guzik_zamowieniaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guzik_zamowieniaMouseClicked
        show_panel(panel_zamowienia);
        refresh_zamowienia_hurt();
        refresh_zamowienia_klient();
    }//GEN-LAST:event_guzik_zamowieniaMouseClicked

    private void guzik_statystykaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guzik_statystykaMouseClicked
        show_panel(panel_statystyka);
    }//GEN-LAST:event_guzik_statystykaMouseClicked

    private void guzik_klienciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guzik_klienciMouseClicked
        show_panel(panel_klienci);
    }//GEN-LAST:event_guzik_klienciMouseClicked

    private void guzik_pracownicyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guzik_pracownicyMouseClicked
        show_panel(panel_pracownicy);
    }//GEN-LAST:event_guzik_pracownicyMouseClicked

    private void guzik_produktyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guzik_produktyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guzik_produktyActionPerformed

    private void zam_szukaj_hurtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zam_szukaj_hurtMouseClicked
        refresh_zamowienia_hurt();
        zam_szukaj_co_hurt.setText("");
    }//GEN-LAST:event_zam_szukaj_hurtMouseClicked

    private void klienci_szukajMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_klienci_szukajMouseClicked
        refresh_klienci();
        klienci_szukaj_co.setText("");
    }//GEN-LAST:event_klienci_szukajMouseClicked

    private void pracownicy_szukajMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pracownicy_szukajMouseClicked
        pracownicy_szukaj_co.setText("");
        refresh_pracownicy();
    }//GEN-LAST:event_pracownicy_szukajMouseClicked

    private void pracownicy_dodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pracownicy_dodajActionPerformed
        
        java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            dialog=new DialogDodajPracownika(thisframe, true, connection);
                            dialog.setVisible(true);
                            dialog.addWindowListener(new WindowAdapter()//Sprawdza, czy okno dialogowe zostało zamknięte
                            {
                                @Override
                                public void windowClosed(WindowEvent e)
                                {
                                    refresh_pracownicy();
                                }
                            });
                        }
                    });
        
        
           /* @Override
        public void windowClosing(WindowEvent e)
        {
            System.out.println("jdialog window closing");
        }
        })*/
        
        /*Runnable okienko=new Runnable()
        {
            public void run() 
            {
                            new OknoDodajPracownika(connection).setVisible(true);
            }
        };*/
    }//GEN-LAST:event_pracownicy_dodajActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        refresh_hurtownie();
        hurtownia_szukaj_co.setText("");
    }//GEN-LAST:event_jButton1MouseClicked

    private void hurtownia_dodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hurtownia_dodajActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            dialog=new DialogDodajHurtownie(thisframe, true, connection);
                            dialog.setVisible(true);
                            dialog.addWindowListener(new WindowAdapter()//Sprawdza, czy okno dialogowe zostało zamknięte
                            {
                                @Override
                                public void windowClosed(WindowEvent e)
                                {
                                    refresh_hurtownie();
                                }
                            });
                        }
                    });
    }//GEN-LAST:event_hurtownia_dodajActionPerformed

    private void produkty_szukajMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produkty_szukajMouseClicked
        refresh_produkty();
        produkty_szukaj_co.setText("");
    }//GEN-LAST:event_produkty_szukajMouseClicked

    private void produkty_dodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produkty_dodajActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            dialog=new DialogDodajProdukt(thisframe, true, connection);
                            dialog.setVisible(true);
                            dialog.addWindowListener(new WindowAdapter()//Sprawdza, czy okno dialogowe zostało zamknięte
                            {
                                @Override
                                public void windowClosed(WindowEvent e)
                                {
                                    refresh_produkty();
                                }
                            });
                        }
                    });
    }//GEN-LAST:event_produkty_dodajActionPerformed

    private void zam_sortuj_hurtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zam_sortuj_hurtActionPerformed
        refresh_zamowienia_hurt();
    }//GEN-LAST:event_zam_sortuj_hurtActionPerformed

    private void zam_sort_klientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zam_sort_klientActionPerformed
        refresh_zamowienia_klient();
    }//GEN-LAST:event_zam_sort_klientActionPerformed

    private void zamowenia_tabbedMouseClicked(java.awt.event.MouseEvent evt) {                                              
        refresh_zamowienia_hurt();
        refresh_zamowienia_klient();
    }
    
    private void declareUsuwaniePracownika()
    {
        akcjaUsunPracownika=new AbstractAction() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    final int id=Integer.valueOf(pracownicy_tabela.getValueAt(Integer.valueOf(e.getActionCommand()), 0).toString()) ;
                    final String nazwisko=pracownicy_tabela.getValueAt(Integer.valueOf(e.getActionCommand()), 1).toString();
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            dialog=new DialogUsunPracownika(thisframe, true, connection, id, nazwisko);
                            dialog.setVisible(true);
                            dialog.addWindowListener(new WindowAdapter()//Sprawdza, czy okno dialogowe zostało zamknięte
                            {
                                @Override
                                public void windowClosed(WindowEvent e)
                                {
                                    refresh_pracownicy();
                                }
                            });
                        }
                    });
                }
            };
    }
    
    private void declarePracownikSzczegoly()
    {
        akcjaPracownikSzczegoly=new AbstractAction() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    final int id=Integer.valueOf(pracownicy_tabela.getValueAt(Integer.valueOf(e.getActionCommand()), 0).toString()) ;
                    
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() 
                        {
                            dialog=new DialogPracownikSzczegoly(thisframe, true, connection, id);
                            dialog.setVisible(true);
                        }
                    });
                }
            };
    }
    /**
    * @param args the command line arguments
    *//*
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }*/
    private ButtonColumn usunPracownikaColumnn;
    private ButtonColumn pracownikSzczegolyColumnn;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Hurtownie10;
    private javax.swing.JLabel Hurtownie11;
    private javax.swing.JLabel Hurtownie8;
    private javax.swing.JLabel Hurtownie9;
    private javax.swing.JLabel Klienci10;
    private javax.swing.JLabel Klienci11;
    private javax.swing.JLabel Klienci8;
    private javax.swing.JLabel Klienci9;
    private javax.swing.JLabel Produkty10;
    private javax.swing.JLabel Produkty11;
    private javax.swing.JLabel Produkty8;
    private javax.swing.JLabel Produkty9;
    private javax.swing.JLayeredPane Warstwy;
    private javax.swing.JButton guzik_hurtownia;
    private javax.swing.JButton guzik_klienci;
    private javax.swing.JButton guzik_pracownicy;
    private javax.swing.JButton guzik_produkty;
    private javax.swing.JButton guzik_statystyka;
    private javax.swing.JButton guzik_zamowienia;
    private javax.swing.JButton hurtownia_dodaj;
    private javax.swing.JTextField hurtownia_szukaj_co;
    private javax.swing.JTable hurtownia_tabela;
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JFrame jFrame4;
    private javax.swing.JFrame jFrame5;
    private javax.swing.JFrame jFrame6;
    private javax.swing.JFrame jFrame7;
    private javax.swing.JFrame jFrame8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane65;
    private javax.swing.JScrollPane jScrollPane66;
    private javax.swing.JScrollPane jScrollPane67;
    private javax.swing.JScrollPane jScrollPane68;
    private javax.swing.JScrollPane jScrollPane69;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane70;
    private javax.swing.JScrollPane jScrollPane71;
    private javax.swing.JScrollPane jScrollPane72;
    private javax.swing.JScrollPane jScrollPane73;
    private javax.swing.JScrollPane jScrollPane74;
    private javax.swing.JScrollPane jScrollPane75;
    private javax.swing.JScrollPane jScrollPane76;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox klienci_sortuj;
    private javax.swing.JButton klienci_szukaj;
    private javax.swing.JTextField klienci_szukaj_co;
    private javax.swing.JTable klienci_tabela;
    private javax.swing.JPanel panel_hurtowni;
    private javax.swing.JPanel panel_hurtowni_szczegoly;
    private javax.swing.JPanel panel_klienci;
    private javax.swing.JPanel panel_pracownicy;
    private javax.swing.JPanel panel_produkty;
    private javax.swing.JPanel panel_statystyka;
    private javax.swing.JPanel panel_zamowienia;
    private javax.swing.JButton pracownicy_dodaj;
    private javax.swing.JComboBox pracownicy_sortuj;
    private javax.swing.JButton pracownicy_szukaj;
    private javax.swing.JTextField pracownicy_szukaj_co;
    private javax.swing.JTable pracownicy_tabela;
    private javax.swing.JButton produkty_dodaj;
    private javax.swing.JButton produkty_szukaj;
    private javax.swing.JTextField produkty_szukaj_co;
    private javax.swing.JTable produkty_tabela;
    private javax.swing.JPanel staty_miesiecznie;
    private javax.swing.JPanel staty_ogolem;
    private javax.swing.JPanel staty_rocznie;
    private javax.swing.JPanel staty_tygodniwo;
    private javax.swing.JTable statystka_hurtownie_miesiecznie;
    private javax.swing.JTable statystka_hurtownie_ogolem;
    private javax.swing.JTable statystka_hurtownie_rocznie;
    private javax.swing.JTable statystka_hurtownie_tygodniowo;
    private javax.swing.JTable statystka_klienci_miesiecznie;
    private javax.swing.JTable statystka_klienci_ogolem;
    private javax.swing.JTable statystka_klienci_rocznie;
    private javax.swing.JTable statystka_klienci_tygodniowo;
    private javax.swing.JTable statystka_produkty_miesiecznie;
    private javax.swing.JTable statystka_produkty_ogolem;
    private javax.swing.JTable statystka_produkty_rocznie;
    private javax.swing.JTable statystka_produkty_tygodniowo;
    private javax.swing.JPanel zam_hurt;
    private javax.swing.JTable zam_hurt_tab;
    private javax.swing.JPanel zam_klient;
    private javax.swing.JTable zam_klient_tab;
    private javax.swing.JComboBox zam_sort_klient;
    private javax.swing.JComboBox zam_sortuj_hurt;
    private javax.swing.JTextField zam_szukaj_co_hurt;
    private javax.swing.JTextField zam_szukaj_co_klient;
    private javax.swing.JButton zam_szukaj_hurt;
    private javax.swing.JButton zam_szukaj_klient;
    private javax.swing.JTabbedPane zamowenia_tabbed;
    // End of variables declaration//GEN-END:variables

}
