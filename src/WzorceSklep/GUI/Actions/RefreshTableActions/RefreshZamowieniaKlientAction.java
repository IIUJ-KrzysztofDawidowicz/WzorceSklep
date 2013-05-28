package WzorceSklep.GUI.Actions.RefreshTableActions;

import WzorceSklep.GUI.RefreshTableAction;

import java.io.Serializable;

public class RefreshZamowieniaKlientAction implements Serializable, RefreshTableAction {
    public RefreshZamowieniaKlientAction() {
    }//-------------------------------------------------------------------------------------------------------------------

    public void refresh() {
        /*
         * TODO: WzorceSklep.GUI.Actions.RefreshTableActions.AbstractTableConverter.getTable
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
}