package WzorceSklep.GUI.Actions.RefreshTableActions;

import WzorceSklep.DataAccessObject;
import WzorceSklep.Data.Pracownik.Pracownik;
import WzorceSklep.GUI.Admin;
import WzorceSklep.GUI.ButtonUtils.ButtonColumn;
import WzorceSklep.GUI.RefreshTableAction;
import WzorceSklep.GUI.Actions.RefreshTableActions.TableConverters.PracownicyTableConverter;
import WzorceSklep.DAOFactory;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RefreshPracownicyAction extends AbstractRefreshTableAction<Pracownik> implements Serializable, RefreshTableAction {

    public RefreshPracownicyAction(Admin admin) {
       super(admin);
    }//-------------------------------------------------------------------------------------------------------------------

    public void refresh() //Rysuje pracownicy_tabela
    {
        String orderBy = getOrderBy();
        String lookFor = getSzukajTextField().getText();
        DataAccessObject<Pracownik> dao = null;
        try {
            dao = getDataAccessObject();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return;
        }
        List<Pracownik> pracownikList = new LinkedList<Pracownik>();
        try {
            if (lookFor.isEmpty())
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
        AbstractTableConverter<Pracownik> converter = getTableConverter();
        TableModel model = converter.getTableModel(pracownicy);

        getTable().setModel(model);
        setButtonColumns();
        getSzukajTextField().setText(""); //Czyści okienko
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

    protected void setButtonColumns() {
        admin.setPracownikSzczegolyColumnn(new ButtonColumn(getTable(), admin.getAkcjaPracownikSzczegoly(), 4));
        admin.setUsunPracownikaColumnn(new ButtonColumn(getTable(), admin.getAkcjaUsunPracownika(), 5));
    }

    protected JTable getTable() {
        return admin.getPracownicy_tabela();
    }

    protected PracownicyTableConverter getTableConverter() {
        return new PracownicyTableConverter();
    }

    protected DataAccessObject<Pracownik> getDataAccessObject() throws SQLException {
        return DAOFactory.getPracownikDAO();
    }

    protected JTextField getSzukajTextField() {
        return admin.getPracownicy_szukaj_co();
    }

    protected String getOrderBy() {
        return admin.getPracownicy_sortuj().getSelectedItem().toString();
    }
}