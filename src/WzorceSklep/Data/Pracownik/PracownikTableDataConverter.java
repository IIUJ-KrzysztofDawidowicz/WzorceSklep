package WzorceSklep.Data.Pracownik;

import WzorceSklep.Data.AdresOsoby;
import WzorceSklep.Data.DataAdapter.TableRow;
import WzorceSklep.Data.DataAdapter.TableRowFactory;
import WzorceSklep.Data.TableDataConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PracownikTableDataConverter implements TableDataConverter<Pracownik>{
    private final String tableNamePracownik;
    private final String tableNameAdres;

    public PracownikTableDataConverter(String tableNamePracownik, String tableNameAdres) {
        this.tableNamePracownik = tableNamePracownik;
        this.tableNameAdres = tableNameAdres;
    }

    public TableRow convertToTableRow(Pracownik pracownik) throws SQLException {
        TableRow wynik = TableRowFactory.createTableRow(tableNamePracownik);

        wynik.setValue("ID", pracownik.getID());
        wynik.setValue("Imie", pracownik.getImie());
        wynik.setValue("Nazwisko", pracownik.getNazwisko());
        wynik.setValue("Telefon", pracownik.getTelefon());
        wynik.setValue("Mail", pracownik.getMail());
        wynik.setValue("Login", pracownik.getLogin());
        wynik.setValue("Password", pracownik.getPassword());
        wynik.setValue("Umowa", pracownik.getUmowa());
        String status = pracownik.getStatusString();
        wynik.setValue("Status", status);

        return wynik;
    }

    List<AdresOsoby> convertToAdres(ResultSet set) throws SQLException {
        List<AdresOsoby> wynik = new LinkedList<AdresOsoby>();
        while (set.next()) {
            AdresOsoby adres = new AdresOsoby();

            adres.setUlica(set.getString("Ulica"));
            adres.setNrDomu(set.getInt("NrDomu"));
            adres.setNrLokalu(set.getInt("NrLokalu"));
            adres.setKodPocztowy(set.getString("Kod"));
            adres.setMiejscowosc(set.getString("Miejscowosc"));
            adres.setPoczta(set.getString("Poczta"));
            adres.setKraj(set.getString("Kraj"));

            wynik.add(adres);
        }
        return wynik;
    }

    TableRow convertAdresToTableRow(AdresOsoby adres, int idPracownika) throws SQLException {
        TableRow wynik = TableRowFactory.createTableRow(tableNameAdres);
        wynik.setValue("ID", idPracownika);
        wynik.setValue("Ulica", adres.getUlica());
        wynik.setValue("NrDomu", adres.getNrDomu());
        wynik.setValue("NrLokalu", adres.getNrLokalu());
        wynik.setValue("Kod", adres.getKodPocztowy());
        wynik.setValue("Miejscowosc", adres.getMiejscowosc());
        wynik.setValue("Poczta", adres.getPoczta());
        wynik.setValue("Kraj", adres.getKraj());
        return wynik;
    }

    public List<Pracownik> convertResultSet(ResultSet set) throws SQLException {
        List<Pracownik> wynik = new LinkedList<Pracownik>();

        while (set.next()) {
            Pracownik pracownik = new Pracownik();

            pracownik.setID(set.getInt("ID"));
            pracownik.setImie(set.getString("Imie"));
            pracownik.setNazwisko(set.getString("Nazwisko"));
            pracownik.setTelefon(set.getString("Telefon"));
            pracownik.setMail(set.getString("Mail"));
            pracownik.setLogin(set.getString("Login"));
            pracownik.setPassword(set.getString("Password"));
            pracownik.setUmowa(set.getString("Umowa"));
            String status = set.getString("Status");
            pracownik.setStatusWithString(status);

            wynik.add(pracownik);
        }

        return wynik;
    }

    @Override
    public List<TableRow> convertToTableRows(Pracownik dataEntity) throws SQLException {
        List<TableRow> wynik = new ArrayList<TableRow>(2);
        wynik.add(convertToTableRow(dataEntity));
        wynik.add(convertAdresToTableRow(dataEntity.getAdres(), dataEntity.getID()));
        return wynik;
    }

}