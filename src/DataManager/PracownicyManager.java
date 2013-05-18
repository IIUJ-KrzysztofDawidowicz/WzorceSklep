package DataManager;

import DataEntity.Pracownik;

public interface PracownicyManager extends DataManager<Pracownik>
{
    public Pracownik getSzczgoly(int id);
    public void zmienDanePracownika(Pracownik noweDane);
}
