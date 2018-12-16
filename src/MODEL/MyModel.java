package MODEL;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel {

    private int nblig = 0;
    private int nbcol;
    private String[] columns;
    private ArrayList<Vector<String>> lignes = new ArrayList<Vector<String>>();

    public MyModel(ResultSet res) {
        try {
            ResultSetMetaData rsmd = res.getMetaData();
            nbcol = rsmd.getColumnCount();
            columns = new String[nbcol];
            for (int i = 0; i < nbcol; i++) {
                columns[i] = rsmd.getColumnLabel(i + 1);
            }
            Vector<String> Ligne;
            while (res.next()) {
                Ligne = new Vector<String>();
                for (int i = 0; i < nbcol; i++) {
                    Ligne.add(res.getObject(i + 1).toString());
                }
                lignes.add(Ligne);
                nblig++;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public int getRowCount() {
        return nblig;
    }

    @Override
    public int getColumnCount() {
        return nbcol;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return lignes.get(rowIndex).get(columnIndex);
    }

}
