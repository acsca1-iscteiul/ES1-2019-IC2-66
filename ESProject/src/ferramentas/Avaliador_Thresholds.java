package ferramentas;

import javax.swing.table.DefaultTableModel;

public class Avaliador_Thresholds {
	private int loc;
	private int cyclo;
	private int atfd;
	private double laa;
	private DefaultTableModel origem;
	private DefaultTableModel destino;
	private String op;


	public Avaliador_Thresholds(int loc, int cyclo, int atfd, double laa, DefaultTableModel origem, DefaultTableModel destino, String op) {
		this.loc = loc;
		this.cyclo = cyclo;
		this.atfd = atfd;
		this.laa = laa;
		this.origem = origem;
		this.destino = destino;
		this.op = op;
		preenche_tabela();
	}


	private void preenche_tabela() {
		

		for (int i = 0; i < origem.getRowCount(); i++) {
			String[] info = new String[3];
			info[0]= String.valueOf(origem.getValueAt(i, 0));
			if(op.equals("AND")) {
				info[1]=String.valueOf(!(loc>Double.parseDouble(String.valueOf(origem.getValueAt(i, 4))) && cyclo>Double.parseDouble(String.valueOf(origem.getValueAt(i, 5)))));
				info[2]=String.valueOf(!(atfd>Double.parseDouble(String.valueOf(origem.getValueAt(i, 6))) && laa>Double.parseDouble(String.valueOf(origem.getValueAt(i, 7)))));
			}
			if(op.equals("OR")) {
				info[1]=String.valueOf(!(loc>Double.parseDouble(String.valueOf(origem.getValueAt(i, 4))) || cyclo>Double.parseDouble(String.valueOf(origem.getValueAt(i, 5)))));
				info[2]=String.valueOf(!(atfd>Double.parseDouble(String.valueOf(origem.getValueAt(i, 6))) || laa>Double.parseDouble(String.valueOf(origem.getValueAt(i, 7)))));
			}
			destino.addRow(info);
		}
	}


}
