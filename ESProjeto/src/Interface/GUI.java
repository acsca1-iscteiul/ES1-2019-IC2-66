package Interface;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ferramentas.Avaliador_Thresholds;
import ferramentas.Comparador_is_long_method;
import ferramentas.Comparador_is_long_method_Thresholds;

import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import javax.swing.JTabbedPane;

public class GUI extends JFrame {

	private JPanel contentPane_2;
	private JTextField TF_CYCLO;
	private JTextField TF_LOC;
	private JTable tabela_excel;
	private JTextField TF_ATFD;
	private JTextField TF_LAA;
	private JTable table_iPlasma;
	private JTable table_PMD;
	private JTable table_Threshold;
	private JTable table_Results;
	private JTable table_Threshold_results;
	private File excelFile;
	
	private DefaultTableModel model;
	private DefaultTableModel model_iPlasma;
	private DefaultTableModel model_PMD;
	private DefaultTableModel model_Threshold;
	private DefaultTableModel model_Threshold_results;
	private DefaultTableModel model_Results;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("ESProject");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 994, 643);
		contentPane_2 = new JPanel();
		contentPane_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_2);

		JLabel label_ficheiro = new JLabel("Ficheiro:");
		label_ficheiro.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JLabel label_LOC = new JLabel("LOC:");
		label_LOC.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JLabel label_CYCLO = new JLabel("CYCLO:");
		label_CYCLO.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		TF_CYCLO = new JTextField();
		TF_CYCLO.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		TF_CYCLO.setColumns(10);

		TF_LOC = new JTextField();
		TF_LOC.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		TF_LOC.setColumns(10);

		JButton botao_iniciar = new JButton("Iniciar");
		botao_iniciar.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));


		JLabel label_OL = new JLabel("Operador L\u00F3gico:");
		label_OL.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JComboBox CB_OL = new JComboBox();
		CB_OL.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		CB_OL.setForeground(Color.BLACK);
		CB_OL.setModel(new DefaultComboBoxModel(new String[] {"AND", "OR"}));

		model = new DefaultTableModel();
		DefaultTableModel model_iPlasma = new DefaultTableModel();
		DefaultTableModel model_PMD = new DefaultTableModel();
		DefaultTableModel model_Threshold = new DefaultTableModel();
		DefaultTableModel model_Threshold_results = new DefaultTableModel();
		DefaultTableModel model_Results = new DefaultTableModel();
		String[] columns = {"DCI","DII","ADCI","ADII"};
		for (String string : columns) {
			model_iPlasma.addColumn(string);
			model_PMD.addColumn(string);
			model_Threshold.addColumn(string);
		}
		model_Threshold_results.addColumn("M_ID");
		model_Threshold_results.addColumn("is_long_Method");
		model_Threshold_results.addColumn("is_feature_envy");
		model_Results.addColumn("M_ID");
		model_Results.addColumn("iPlasma");
		model_Results.addColumn("PMD");
		model_Results.addColumn("Thresholds");

		JButton botao_pesquisar = new JButton("Pesquisar");
		botao_pesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(model.getRowCount()>0)
					reset_tabela(model);


				FileInputStream excelFIS = null;
				BufferedInputStream excelBIS = null;
				XSSFWorkbook excelJTableImport = null;

				//select default path 
				String defaultCurrentDirectoryPath = "caminho_ate_excel";
				JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
				int excelChooser = excelFileChooser.showOpenDialog(null);


				//filter only excel files 
				FileNameExtensionFilter  fnef = new FileNameExtensionFilter ("EXCEL FILES", "xls", "xlsx", "xlsm");
				excelFileChooser.setFileFilter(fnef);

				//set dialogue title
				excelFileChooser.setDialogTitle("Select excel file");
				int excelchooser = excelFileChooser.showOpenDialog(null);

				// if button is clicked

				if(excelChooser==JFileChooser.APPROVE_OPTION)
				{
					try {
						excelFile = excelFileChooser.getSelectedFile();
						excelFIS = new FileInputStream(excelFile);
						excelBIS = new BufferedInputStream(excelFIS);
						excelJTableImport = new XSSFWorkbook(excelBIS);
						XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);

						// looping pelas linhas e colunas
						boolean first_line=true;
						for (int row = 0; row <= excelSheet.getLastRowNum(); row++) {
							XSSFRow excelRow = excelSheet.getRow(row);
							XSSFCell[] cells = new XSSFCell[excelRow.getLastCellNum()];
							for(int i=0; i<excelRow.getLastCellNum(); i++) {
								cells[i]=excelRow.getCell(i);
							}
							if(first_line) {
								//								for (XSSFCell xssfCell : cells) {
								//									model.addColumn(xssfCell);
								//								}
								model.setColumnIdentifiers(cells);
								first_line=false;
							}
							else
								model.addRow(cells);

							//							JOptionPane.showMessageDialog(null, "IMPORTED SUCCESSFULLY!");

						}
					} catch (FileNotFoundException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					} finally {
						if (excelFIS != null) {
							try {
								excelFIS.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (excelBIS != null) {
							try {
								excelBIS.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				new Comparador_is_long_method(model, model_iPlasma, 9).start();
				new Comparador_is_long_method(model, model_PMD, 10).start();
				reset_tabela(model_Results);
				reset_tabela(model_Threshold_results);
				reset_tabela(model_Threshold);
				
			
			}
		});
		botao_pesquisar.setFont(new Font("Verdana Pro", Font.PLAIN, 16));
		botao_pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();

		JLabel label_ATFD = new JLabel("ATFD:");
		label_ATFD.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JLabel label_LAA = new JLabel("LAA:");
		label_LAA.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		TF_ATFD = new JTextField();
		TF_ATFD.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		TF_ATFD.setColumns(10);

		TF_LAA = new JTextField();
		TF_LAA.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		TF_LAA.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();

		JLabel lbl_iPlasma = new JLabel("iPlasma");
		lbl_iPlasma.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JLabel lbl_Pmd = new JLabel("PMD");
		lbl_Pmd.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JLabel lblThreshold = new JLabel("Threshold");
		lblThreshold.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JScrollPane scrollPane_2 = new JScrollPane();

		JScrollPane scrollPane_3 = new JScrollPane();

		JScrollPane scrollPane_4 = new JScrollPane();

		JScrollPane scrollPane_Thresholds = new JScrollPane();

		JLabel label_Aviso = new JLabel("");
		label_Aviso.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 15));

		botao_iniciar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(Avaliador_Thresholds.isInteger(TF_LOC.getText()) && Avaliador_Thresholds.isInteger(TF_CYCLO.getText()) && Avaliador_Thresholds.isInteger(TF_ATFD.getText()) && Avaliador_Thresholds.isNumeric(TF_LAA.getText())) {
					label_Aviso.setText("");
					reset_tabela(model_Threshold_results);
					new Avaliador_Thresholds(Integer.parseInt(TF_LOC.getText()), Integer.parseInt(TF_CYCLO.getText()), Integer.parseInt(TF_ATFD.getText()), Double.parseDouble(TF_LAA.getText()), model, model_Threshold_results , String.valueOf(CB_OL.getSelectedItem()));
					new Comparador_is_long_method_Thresholds(model, model_Threshold_results, model_Threshold);
					preenche_tabela_final(model, model_Threshold_results, model_Results);
				}
				else {
					label_Aviso.setText("Thresholds incorretos");
				}
			}
		});


		JLabel lblIslongmethodIdentificadoPor = new JLabel("is_long_method identificado por cada ferramenta:");
		lblIslongmethodIdentificadoPor.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JLabel lblTabelaDeResultados = new JLabel("Tabela de resultados de Thresholds:");
		lblTabelaDeResultados.setFont(new Font("Verdana Pro Black", Font.PLAIN, 13));

		GroupLayout gl_contentPane_2 = new GroupLayout(contentPane_2);
		gl_contentPane_2.setHorizontalGroup(
			gl_contentPane_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane_2.createSequentialGroup()
					.addGroup(gl_contentPane_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane_2.createSequentialGroup()
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addComponent(label_ficheiro, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(botao_pesquisar, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
									.addComponent(label_Aviso, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(12)
									.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane_2.createSequentialGroup()
										.addGap(21)
										.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
											.addComponent(label_ATFD)
											.addComponent(label_LOC))
										.addGap(18)
										.addGroup(gl_contentPane_2.createParallelGroup(Alignment.TRAILING, false)
											.addGroup(gl_contentPane_2.createSequentialGroup()
												.addComponent(TF_LOC, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(label_CYCLO, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(TF_CYCLO, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_contentPane_2.createSequentialGroup()
												.addComponent(TF_ATFD, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(label_LAA)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(TF_LAA, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))))
									.addGroup(gl_contentPane_2.createSequentialGroup()
										.addGap(12)
										.addComponent(label_OL)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(CB_OL, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
										.addComponent(lblThreshold)
										.addGroup(gl_contentPane_2.createSequentialGroup()
											.addGap(21)
											.addComponent(lbl_Pmd))
										.addGroup(gl_contentPane_2.createSequentialGroup()
											.addGap(8)
											.addComponent(lbl_iPlasma)))
									.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane_2.createSequentialGroup()
											.addGap(32)
											.addGroup(gl_contentPane_2.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(scrollPane_3, 0, 0, Short.MAX_VALUE)
												.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)))
										.addGroup(gl_contentPane_2.createSequentialGroup()
											.addGap(33)
											.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE))))))
						.addComponent(botao_iniciar, GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
						.addGroup(gl_contentPane_2.createSequentialGroup()
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(13)
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
									.addGap(42))
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblIslongmethodIdentificadoPor)
									.addGap(87)))
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_Thresholds, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTabelaDeResultados)))))
					.addContainerGap())
		);
		gl_contentPane_2.setVerticalGroup(
			gl_contentPane_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane_2.createSequentialGroup()
					.addGroup(gl_contentPane_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_ficheiro, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(botao_pesquisar)
						.addComponent(label_Aviso, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(TF_CYCLO, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_CYCLO)
						.addComponent(TF_LOC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_LOC))
					.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane_2.createSequentialGroup()
							.addGap(13)
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane_2.createParallelGroup(Alignment.BASELINE)
									.addComponent(label_ATFD)
									.addComponent(TF_LAA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane_2.createParallelGroup(Alignment.BASELINE)
									.addComponent(label_LAA)
									.addComponent(TF_ATFD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_OL)
								.addComponent(CB_OL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(33)
									.addComponent(lbl_iPlasma))
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(18)
									.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(50)
									.addComponent(lbl_Pmd))
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(30)
									.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(40)
									.addComponent(lblThreshold))
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(26)
									.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane_2.createSequentialGroup()
							.addGap(28)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)))
					.addGap(31)
					.addGroup(gl_contentPane_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIslongmethodIdentificadoPor)
						.addComponent(lblTabelaDeResultados))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_Thresholds, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addComponent(botao_iniciar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
		);

		table_Threshold_results = new JTable(model_Threshold_results);
		table_Threshold_results.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		scrollPane_Thresholds.setViewportView(table_Threshold_results);

		table_iPlasma = new JTable(model_iPlasma);
		table_iPlasma.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		scrollPane_4.setViewportView(table_iPlasma);

		table_PMD = new JTable(model_PMD);
		table_PMD.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		scrollPane_3.setViewportView(table_PMD);

		table_Threshold = new JTable(model_Threshold);
		table_Threshold.setFont(new Font("Verdana", Font.PLAIN, 15));
		scrollPane_2.setViewportView(table_Threshold);

		table_Results = new JTable(model_Results);
		table_Results.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		scrollPane.setViewportView(table_Results);
		tabela_excel = new JTable(model);
		scrollPane_1.setViewportView(tabela_excel);
		tabela_excel.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		contentPane_2.setLayout(gl_contentPane_2);
	}

	private void reset_tabela(DefaultTableModel model) {
		for (int i = model.getRowCount()-1; i >=0; i--) {
			model.removeRow(i);
		}

	}

	private void preenche_tabela_final(DefaultTableModel excel, DefaultTableModel threshold, DefaultTableModel resultados) {
		if(resultados.getRowCount()>0)
			reset_tabela(resultados);
		for (int i = 0; i < excel.getRowCount(); i++) {
			String[] info = new String[4];
			info[0] = String.valueOf(excel.getValueAt(i, 0));
			info[1] = String.valueOf(excel.getValueAt(i, 9));
			info[2] = String.valueOf(excel.getValueAt(i, 10));
			info[3] = String.valueOf(threshold.getValueAt(i, 1));
			resultados.addRow(info);
		}
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	
}

