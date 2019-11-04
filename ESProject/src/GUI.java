import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class GUI extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Table table;
	private Table table_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public GUI(Composite parent, int style) {
		super(parent, style);
		setToolTipText("");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(76, 7, 368, 26);
		
		Label lblFicheiro = new Label(this, SWT.NONE);
		lblFicheiro.setBounds(10, 10, 60, 20);
		lblFicheiro.setText("Ficheiro:");
		
		Button btnPesquisar = new Button(this, SWT.NONE);
		btnPesquisar.setBounds(450, 7, 81, 30);
		btnPesquisar.setText("Pesquisar");
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(592, 13, 34, 20);
		lblNewLabel.setText("LOC:");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(589, 47, 124, 20);
		lblNewLabel_1.setText("Operador L\u00F3gico:");
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setBounds(807, 13, 45, 20);
		lblNewLabel_2.setText("CYCLO:");
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(632, 10, 81, 26);
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(869, 11, 81, 26);
		
		Combo combo = new Combo(this, SWT.NONE);
		combo.setItems(new String[] {"AND", "OR"});
		combo.setBounds(722, 44, 228, 28);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(592, 94, 409, 272);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("New Column");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("New Column");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("New Column");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("New Column");
		
		table_1 = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(23, 445, 978, 205);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setBounds(76, 656, 296, 30);
		btnNewButton.setText("New Button");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
