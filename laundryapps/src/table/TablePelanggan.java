package table;

import model.Pelanggan;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TablePelanggan extends AbstractTableModel {
    private List<Pelanggan> list;
    private String[] column = {"ID", "Nama", "Alamat", "No Telp"};

    public TablePelanggan(List<Pelanggan> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() { return list.size(); }

    @Override
    public int getColumnCount() { return column.length; }

    @Override
    public String getColumnName(int col) { return column[col]; }

    @Override
    public Object getValueAt(int row, int col) {
        Pelanggan p = list.get(row);
        switch (col) {
            case 0: return p.getId();
            case 1: return p.getNama();
            case 2: return p.getAlamat();
            case 3: return p.getNoTelp();
            default: return null;
        }
    }
}
