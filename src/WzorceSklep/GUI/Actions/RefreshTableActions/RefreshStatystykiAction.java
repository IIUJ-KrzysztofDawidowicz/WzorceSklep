package WzorceSklep.GUI.Actions.RefreshTableActions;

import WzorceSklep.GUI.Admin;
import WzorceSklep.GUI.RefreshTableAction;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;

public class RefreshStatystykiAction implements Serializable, RefreshTableAction {
    private final Admin admin;

    public RefreshStatystykiAction(Admin admin) {
        this.admin = admin;
    }

    public void refresh() {
        //PRODUKTY

        //OGOLEM
        int i = 0;
        try {
            Statement stat = admin.getConnection().createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_produkty");
            rs.next();
            TableModel model1 = new DefaultTableModel(new String[]{
                    "ID", "Nazwa", "Zamowienia", "Ilość", "Kwota"
            }, rs.getInt(1)) {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_produkty");
            while (rs.next()) {
                ;
                model1.setValueAt(rs.getString("ID_Produktu"), i, 0);
                model1.setValueAt(rs.getString("Nazwa"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);
                model1.setValueAt(rs.getInt("Ilosc"), i, 3);
                model1.setValueAt(rs.getString("Kwota"), i, 4);

                i++;
            }
            admin.getStatystka_produkty_ogolem().setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //TYGODNIOWO
        i = 0;
        try {
            Statement stat = admin.getConnection().createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_produkty_tygodniowo");
            rs.next();
            TableModel model1 = new DefaultTableModel(new String[]{
                    "ID", "Nazwa", "Zamowienia", "Ilość", "Kwota"
            }, rs.getInt(1)) {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_produkty_tygodniowo");
            while (rs.next()) {
                ;
                model1.setValueAt(rs.getString("ID_Produktu"), i, 0);
                model1.setValueAt(rs.getString("Nazwa"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);
                model1.setValueAt(rs.getInt("Ilosc"), i, 3);
                model1.setValueAt(rs.getString("Kwota"), i, 4);

                i++;
            }
            admin.getStatystka_produkty_tygodniowo().setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //MIESIECZNIE
        i = 0;
        try {
            Statement stat = admin.getConnection().createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_produkty_miesiecznie");
            rs.next();
            TableModel model1 = new DefaultTableModel(new String[]{
                    "ID", "Nazwa", "Zamowienia", "Ilość", "Kwota"
            }, rs.getInt(1)) {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_produkty_miesiecznie");
            while (rs.next()) {
                ;
                model1.setValueAt(rs.getString("ID_Produktu"), i, 0);
                model1.setValueAt(rs.getString("Nazwa"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);
                model1.setValueAt(rs.getInt("Ilosc"), i, 3);
                model1.setValueAt(rs.getString("Kwota"), i, 4);

                i++;
            }
            admin.getStatystka_produkty_miesiecznie().setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //ROCZNIE
        i = 0;
        try {
            Statement stat = admin.getConnection().createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_produkty_rocznie");
            rs.next();
            TableModel model1 = new DefaultTableModel(new String[]{
                    "ID", "Nazwa", "Zamowienia", "Ilość", "Kwota"
            }, rs.getInt(1)) {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_produkty_rocznie");
            while (rs.next()) {
                ;
                model1.setValueAt(rs.getString("ID_Produktu"), i, 0);
                model1.setValueAt(rs.getString("Nazwa"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);
                model1.setValueAt(rs.getInt("Ilosc"), i, 3);
                model1.setValueAt(rs.getString("Kwota"), i, 4);

                i++;
            }
            admin.getStatystka_produkty_rocznie().setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //KLIENCI

        //OGOLEM
        i = 0;
        try {
            Statement stat = admin.getConnection().createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_klienci");
            rs.next();
            TableModel model1 = new DefaultTableModel(new String[]{
                    "ID", "Login", "Zamowienia"
            }, rs.getInt(1)) {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_klienci");
            while (rs.next()) {
                ;
                model1.setValueAt(rs.getString("ID_Klienta"), i, 0);
                model1.setValueAt(rs.getString("Login"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);

                i++;
            }
            admin.getStatystka_klienci_ogolem().setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TYGODNIOWO
        i = 0;
        try {
            Statement stat = admin.getConnection().createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_klienci_tygodniowo");
            rs.next();
            TableModel model1 = new DefaultTableModel(new String[]{
                    "ID", "Login", "Zamowienia"
            }, rs.getInt(1)) {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_klienci_tygodniowo");
            while (rs.next()) {
                ;
                model1.setValueAt(rs.getString("ID_Klienta"), i, 0);
                model1.setValueAt(rs.getString("Login"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);

                i++;
            }
            admin.getStatystka_klienci_tygodniowo().setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //MIESIECZNIE
        i = 0;
        try {
            Statement stat = admin.getConnection().createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_klienci_miesiecznie");
            rs.next();
            TableModel model1 = new DefaultTableModel(new String[]{
                    "ID", "Login", "Zamowienia"
            }, rs.getInt(1)) {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_klienci_miesiecznie");
            while (rs.next()) {
                ;
                model1.setValueAt(rs.getString("ID_Klienta"), i, 0);
                model1.setValueAt(rs.getString("Login"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);

                i++;
            }
            admin.getStatystka_klienci_miesiecznie().setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //ROCZNIE
        i = 0;
        try {
            Statement stat = admin.getConnection().createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_klienci_rocznie");
            rs.next();
            TableModel model1 = new DefaultTableModel(new String[]{
                    "ID", "Login", "Zamowienia"
            }, rs.getInt(1)) {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_klienci_rocznie");
            while (rs.next()) {
                ;
                model1.setValueAt(rs.getString("ID_Klienta"), i, 0);
                model1.setValueAt(rs.getString("Login"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);

                i++;
            }
            admin.getStatystka_klienci_rocznie().setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //HURTOWNIE

        //OGOLEM
        i = 0;
        try {
            Statement stat = admin.getConnection().createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_hurtownie");
            rs.next();
            TableModel model1 = new DefaultTableModel(new String[]{
                    "ID", "Nazwa", "Zamowienia"
            }, rs.getInt(1)) {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_hurtownie");
            while (rs.next()) {
                ;
                model1.setValueAt(rs.getString("ID_Hurtowni"), i, 0);
                model1.setValueAt(rs.getString("Nazwa"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);

                i++;
            }
            admin.getStatystka_hurtownie_ogolem().setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TYGODNIOWO
        i = 0;
        try {
            Statement stat = admin.getConnection().createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_hurtownie_tygodniowo");
            rs.next();
            TableModel model1 = new DefaultTableModel(new String[]{
                    "ID", "Nazwa", "Zamowienia"
            }, rs.getInt(1)) {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_hurtownie_tygodniowo");
            while (rs.next()) {
                ;
                model1.setValueAt(rs.getString("ID_Hurtowni"), i, 0);
                model1.setValueAt(rs.getString("Nazwa"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);

                i++;
            }
            admin.getStatystka_hurtownie_tygodniowo().setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //MIESIECZNIE
        i = 0;
        try {
            Statement stat = admin.getConnection().createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_hurtownie_miesiecznie");
            rs.next();
            TableModel model1 = new DefaultTableModel(new String[]{
                    "ID", "Nazwa", "Zamowienia"
            }, rs.getInt(1)) {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_hurtownie_miesiecznie");
            while (rs.next()) {
                ;
                model1.setValueAt(rs.getString("ID_Hurtowni"), i, 0);
                model1.setValueAt(rs.getString("Nazwa"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);

                i++;
            }
            admin.getStatystka_hurtownie_miesiecznie().setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //ROCZNIE
        i = 0;
        try {
            Statement stat = admin.getConnection().createStatement();
            ResultSet rs = stat.executeQuery("use Sklep;select count(*) from statystyka_hurtownie_rocznie");
            rs.next();
            TableModel model1 = new DefaultTableModel(new String[]{
                    "ID", "Nazwa", "Zamowienia"
            }, rs.getInt(1)) {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;   //Disallow the editing of any cell
                }
            };
            rs = stat.executeQuery("use Sklep;select * from statystyka_hurtownie_rocznie");
            while (rs.next()) {
                ;
                model1.setValueAt(rs.getString("ID_Hurtowni"), i, 0);
                model1.setValueAt(rs.getString("Nazwa"), i, 1);
                model1.setValueAt(rs.getString("Ilosc_Zamowien"), i, 2);

                i++;
            }
            admin.getStatystka_hurtownie_rocznie().setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}