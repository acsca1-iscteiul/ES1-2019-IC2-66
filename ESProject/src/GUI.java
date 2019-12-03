import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField TF_CYCLO;
	private JTextField TF_LOC;
	private JTable tabela_info;
	private JTable tabela_resultado;
	private JTable tabela_excel;
	private int locInt;
	private int cycloInt;
	private XSSFCell[] cells;
	private XSSFRow excelRow;

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
		setBounds(100, 100, 870, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel label_ficheiro = new JLabel("Ficheiro:");
		label_ficheiro.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JLabel label_LOC = new JLabel("LOC:");
		label_LOC.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JLabel label_CYCLO = new JLabel("CYCLO:");
		label_CYCLO.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		TF_CYCLO = new JTextField();
		TF_CYCLO.setColumns(10);

		TF_LOC = new JTextField();
		TF_LOC.setColumns(10);

		tabela_info = new JTable();
		tabela_info.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		tabela_info.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
				},
				new String[] {
						"New column", "New column", "New column"
				}
				));

		tabela_resultado = new JTable();
		tabela_resultado.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		tabela_resultado.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
				},
				new String[] {
						"New column", "New column", "New column", "New column", "New column", "New column", "New column"
				}
				));

		JButton botao_iniciar = new JButton("Iniciar");
		botao_iniciar.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));
		botao_iniciar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				locInt = Integer.parseInt(TF_LOC.getText());
				cycloInt = Integer.parseInt(TF_CYCLO.getText());
				
				for (int i =0 ; i<cells.length; i++) {
					CellReference locReference = new CellReference("LOC");
				
						
					
				}
				
			}
		});

		JLabel label_OL = new JLabel("Operador L\u00F3gico:");
		label_OL.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JComboBox CB_OL = new JComboBox();
		CB_OL.setForeground(Color.BLACK);
		CB_OL.setModel(new DefaultComboBoxModel(new String[] {"AND", "OR"}));

		DefaultTableModel model = new DefaultTableModel();
		//		model.addColumn("Code");
		//		model.addColumn("Name");
		//		model.addColumn("Quantity");
		//		model.addColumn("Unit Price");
		//		model.addColumn("Price");

		JButton botao_pesquisar = new JButton("Pesquisar");
		botao_pesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				File excelFile;
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
						for (int row = 0; row < excelSheet.getLastRowNum(); row++) {
							excelRow = excelSheet.getRow(row);
							cells = new XSSFCell[excelRow.getLastCellNum()];
							for(int i=0; i<excelRow.getLastCellNum(); i++) {
								//							XSSFCell excelName = excelRow.getCell(0);
								//							XSSFCell excelGender = excelRow.getCell(1);
								//							XSSFCell excelProgrammingLanguage = excelRow.getCell(2);
								//							XSSFCell excelSubject = excelRow.getCell(3);
								//							XSSFCell excelImage = excelRow.getCell(4);
								cells[i]=excelRow.getCell(i);
							}
							if(first_line) {
								for (XSSFCell xssfCell : cells) {
									model.addColumn(xssfCell);
								}
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
			}

		});
		

		JScrollPane scrollPane = new JScrollPane();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(tabela_resultado, GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
								.addComponent(botao_iniciar, GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(label_ficheiro, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(botao_pesquisar, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
														.addGap(93))
												.addGroup(gl_contentPane.createSequentialGroup()
														.addGap(12)
														.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
														.addGap(35)))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(label_OL)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(CB_OL, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_contentPane.createSequentialGroup()
																.addGap(17)
																.addComponent(label_LOC)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(TF_LOC, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(label_CYCLO, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(TF_CYCLO, 0, 85, Short.MAX_VALUE)
																.addGap(34)))
												.addComponent(tabela_info, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE))))
						.addGap(0))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_CYCLO)
								.addComponent(TF_CYCLO, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_ficheiro, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(TF_LOC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_LOC)
								.addComponent(botao_pesquisar))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(label_OL)
												.addComponent(CB_OL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(12)
										.addComponent(tabela_info, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(28)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)))
						.addGap(18)
						.addComponent(tabela_resultado, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(botao_iniciar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);
		tabela_excel = new JTable(model);
		scrollPane.setViewportView(tabela_excel);
		tabela_excel.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		contentPane.setLayout(gl_contentPane);
	}
}

