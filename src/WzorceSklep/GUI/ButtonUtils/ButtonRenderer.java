package WzorceSklep.GUI.ButtonUtils;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

/**
 * @version 1.0 11/09/98
 */
//Od tad zaczyna sie potrzebny dla nas kod do dodawania guzikow do tabeli
//Mozna oczywiscie poszukac czegos bardziej zrozumialego :]


public class ButtonRenderer extends JButton implements TableCellRenderer {

  public ButtonRenderer() {
    setOpaque(true);
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else {
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));
    }
    setText((value == null) ? "" : value.toString());
    return this;
  }
}

/**
 * @version 1.0 11/09/98
 */

class ButtonEditor extends DefaultCellEditor {
  protected JButton button;
  private String label;
  private boolean isPushed;

  public ButtonEditor(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();    
    button.setText("Szczegóły");
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }
    
    @Override
  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else {
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    isPushed = true;
    return button;
  }

    @Override
  protected void fireEditingStopped() {     //chyba zbedne
    super.fireEditingStopped();
  }
    
 
    @Override
  public Object getCellEditorValue() {
    if (isPushed) {
      // 
      //Narazie wyswietla "random" komunikat
      //Tu bedziemy musieli umiescic nasze okno "Wiecej"
      //...i pomyslec jak tu dolaczyc informacje z mssql
      JOptionPane.showMessageDialog(button, label + ": Ouch!");
    }
    isPushed = false;
    return label;
  }
    
//To ponizej nie mam (jeszcze) pojecia to czego to(, bo jakos mnie nie interesuje :)
    @Override
  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }
}