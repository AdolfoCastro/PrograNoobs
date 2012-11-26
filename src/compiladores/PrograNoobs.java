package compiladores;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.util.ArrayList;

//Used Action Listner for JMenuItem & JRadioButtonMenuItem
//Used Item Listner for JCheckBoxMenuItem
public class PrograNoobs implements ActionListener, MouseListener, MouseMotionListener {

	DrawPanel programmingPanel;
	ArrayList<JTextArea> parameterVariables = new ArrayList<JTextArea> ();
	JPanel jplContentPane, jplProgramName, jplProgramData, jplFunctionPane, globalVariablesPane, parameterVariablesPane;
	ArrayList<DrawPanel> jplProgrammingPanes;
	JTextArea jtAreaOutput, globalVariables, parameterVariablesFixed;
	JScrollPane logPane, globalVariablesScrollPane, parameterVariablesScrollPane, programmingScrollPane, functionsScrollPane;
	JTextField textField;
	JLabel programNameLabel, globalVariablesLabel, parameterVariablesLabel;
	JButton compileButton, addFunctionButton;
	ArrayList<JButton> functionButtons;
	char numberOfFunctions = 'A';
	int currentFunction = 0;
	private static final int mitad = 450;
	
	/* Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = PrograNoobs.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find image file: " + path);
			return null;
		}
	}
	
	// Rellena el contenedor para el usuario con todos los aspectos
	public Container createContentPane() {
		// Crea el contenedor
		jplContentPane = new JPanel(new BorderLayout(1,1));
		jplContentPane.setOpaque(true);
		
		// Crea el arreglo de botones que representan las funciones
		functionButtons = new ArrayList<JButton>();
		JButton button = new JButton("Main");
		button.addActionListener(this);
		functionButtons.add(button);
		
		// Agrega el encabezado al contenedor
		jplProgramData = new JPanel(new GridLayout(0, 1));
		jplProgramData.setOpaque(true);
		jplProgramData.setPreferredSize(new Dimension(0,80));
		
		// Campos del encabezado
		jplProgramName = new JPanel(new BorderLayout());
		jplProgramName.setOpaque(true);
		programNameLabel = new JLabel("Nombre del programa: ");
		textField = new JTextField ("Programa");
		compileButton = new JButton ("Compilar y Ejecutar");
		compileButton.addActionListener(this);
		jplProgramName.add(programNameLabel, BorderLayout.WEST);
		jplProgramName.add(textField, BorderLayout.CENTER);
		jplProgramName.add(compileButton, BorderLayout.EAST);
		jplProgramData.add(jplProgramName);
		
		// Campos para navegar entre las funciones del usuario
		jplFunctionPane = new JPanel();
		jplFunctionPane.add(functionButtons.get(0));
		addFunctionButton = new JButton("+");
		addFunctionButton.addActionListener(this);
		jplFunctionPane.add(addFunctionButton);
		functionsScrollPane = new JScrollPane(jplFunctionPane);
		jplProgramData.add(functionsScrollPane);
		
		// Area donde se muestran las variables globales y par�metros
		globalVariablesLabel = new JLabel("Variables Globales");
		globalVariables = new JTextArea(0, 20);
		globalVariablesScrollPane = new JScrollPane(globalVariables);
		globalVariablesPane = new JPanel(new BorderLayout());
		globalVariablesPane.add(globalVariablesLabel, BorderLayout.PAGE_START);
		globalVariablesPane.add(globalVariablesScrollPane, BorderLayout.CENTER);
		parameterVariablesLabel = new JLabel("Par�metros");
		parameterVariablesFixed = new JTextArea(20, 20);
		parameterVariablesFixed.setOpaque(false);
		parameterVariablesFixed.setEditable(false);
		parameterVariables.add(new JTextArea(0, 20));
		parameterVariablesScrollPane = new JScrollPane(parameterVariablesFixed);
		parameterVariablesPane = new JPanel(new BorderLayout());
		parameterVariablesPane.add(parameterVariablesLabel, BorderLayout.PAGE_START);
		parameterVariablesPane.add(parameterVariablesScrollPane, BorderLayout.CENTER);
		globalVariablesPane.add(parameterVariablesPane, BorderLayout.SOUTH);
		
		// Area donde se muestra las actividades del usuario "el System.out.print" del GUI
		jtAreaOutput = new JTextArea(5, 30);
		jtAreaOutput.setEditable(false);
		logPane = new JScrollPane(jtAreaOutput);
		
		// Area donde se va a programar
		jplProgrammingPanes = new ArrayList<DrawPanel>();
		DrawPanel jplProgrammingPaneMain = new DrawPanel();
		programmingPanel = new DrawPanel();
		jplProgrammingPanes.add(jplProgrammingPaneMain);
		programmingPanel.setComponentes(jplProgrammingPanes.get(0).getComponentes());
		programmingPanel.setPreferredSize(new Dimension (900, 2000));
		programmingScrollPane = new JScrollPane(programmingPanel);
		programmingScrollPane.addMouseListener(this);
		programmingScrollPane.addMouseMotionListener(this);
		
		// Agrega los campos al contenedor principal
		jplContentPane.add(jplProgramData, BorderLayout.PAGE_START);
		jplContentPane.add(globalVariablesPane, BorderLayout.WEST);
		jplContentPane.add(programmingScrollPane, BorderLayout.CENTER);
		jplContentPane.add(logPane, BorderLayout.PAGE_END);
		return jplContentPane;
	}
	
	// Crea el contenedor principal para el usuario
	private static void createGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		// Create and set up the window.
		JFrame frame = new JFrame("PrograNoobs");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 900);
		PrograNoobs app = new PrograNoobs();
		frame.setContentPane(app.createContentPane());
		frame.setVisible(true);
	}
	
	// maneja los eventos principales de todo el GUI
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == compileButton) // en caso de que presiona el bot�n "Compilar"
		{
			parameterVariables.get(currentFunction).setText(parameterVariablesFixed.getText());
			jplProgrammingPanes.get(currentFunction).setComponentes(programmingPanel.getComponentes());
			String s = "El programa se est� compilando";
			jtAreaOutput.append(s + "\n");
			jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument().getLength());
			generateOutputCode();
			try {
				Process p; //TODO invocar a la(s) funcion(es) correcta
                        p = Runtime.getRuntime().exec("./compilador");
				BufferedReader input = new BufferedReader (new InputStreamReader(p.getInputStream()));
				String line;
				if ((line = input.readLine()).equals("5000")) {
					s = "El programa se compil� exitosamente";
					jtAreaOutput.append(s + "\n");
					jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument().getLength());
					MaquinaVirtual pn_vm = new MaquinaVirtual(jtAreaOutput);
					pn_vm.ejecutar("codigointermedio.txt");
				}
				else {
					s = line;
					jtAreaOutput.append(s + "\n");
					jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument().getLength());
				}
			}
			catch (Exception e1) {
				s = e1.toString();
				jtAreaOutput.append(s + "\n");
				jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument().getLength());
			}
			
		}
		else if (e.getSource() == addFunctionButton) // en caso de que presiona el bot�n "+"
		{
			// agrega el bot�n de la nueva funci�n a la barra de funciones
			JPanel panel = new JPanel (new BorderLayout());
			panel.add(new JLabel("Nombre de la funci�n:"), BorderLayout.WEST);
			JTextField functionName = new JTextField();
			panel.add(functionName, BorderLayout.CENTER);
			String[] items = {"int", "float", "string", "boolean"};
            JComboBox combo = new JComboBox(items);
            panel.add(combo,BorderLayout.EAST);
			int resultado = JOptionPane.showConfirmDialog(null, panel, "Nuevo",
    	            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    		if (resultado == JOptionPane.OK_OPTION) {
    			parameterVariables.get(currentFunction).setText(parameterVariablesFixed.getText());
    			parameterVariablesFixed.setOpaque(true);
    			parameterVariablesFixed.setEditable(true);
    			JButton button;
    			if (functionName.getText().trim().equals(""))
    				button = new JButton("Funcion"+numberOfFunctions + " " + combo.getSelectedItem().toString());
    			else
    				button = new JButton(functionName.getText().trim());
    			button.addActionListener(this);
				jplFunctionPane.remove(addFunctionButton);
				jplFunctionPane.add(button);
				jplFunctionPane.add(addFunctionButton);
				jplProgramData.revalidate();
				functionButtons.add(button);
				// crea la nuevo panel de programaci�n para la nueva funci�n
				DrawPanel newFunction = new DrawPanel ();
				newFunction.setPreferredSize(new Dimension (900, 2000));
				// agrega la funci�n y cambia el apuntador a la funci�n actual
				jplProgrammingPanes.add(newFunction);
				numberOfFunctions++;
				jplProgrammingPanes.get(currentFunction).setComponentes(programmingPanel.getComponentes());
				currentFunction++;
				// pone el panel de par�metros de la funci�n
				parameterVariables.add(new JTextArea(20,20));
				parameterVariablesFixed.setText(parameterVariables.get(currentFunction).getText());
				// cambia al panel de programaci�n de la nueva funci�n
				programmingPanel.setComponentes(jplProgrammingPanes.get(currentFunction).getComponentes());
				programmingPanel.repaint();
				String s = "Se agreg� una funci�n";
				jtAreaOutput.append(s + "\n");
				jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument().getLength());
    		}
		}
		else // en caso de que seleccione una funci�n
		{
			int i = 0;
			boolean borrado = false;
			while (e.getSource() != functionButtons.get(i))
				i++;
			// para cambiar el nombre de la funci�n o borrarla
			if (i == currentFunction && i > 0) {
				JPanel panel = new JPanel (new BorderLayout());
				panel.add(new JLabel("Nombre de la funci�n:"), BorderLayout.WEST);
				JTextField functionName = new JTextField(functionButtons.get(currentFunction).getText().substring(0, functionButtons.get(currentFunction).getText().indexOf(" ")));
				JPanel panelCentral = new JPanel (new GridLayout(1, 0));
				String[] items = {"int", "float", "string", "boolean"};
	            JComboBox combo = new JComboBox(items);
	            panelCentral.add(functionName);
	            panelCentral.add(combo);
	            panel.add(panelCentral, BorderLayout.CENTER);
				JCheckBox checkbox1 = new JCheckBox("DELETE");
				panel.add(checkbox1, BorderLayout.EAST);
				int resultado = JOptionPane.showConfirmDialog(null, panel, functionButtons.get(currentFunction).getText(),
        	            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        		if (resultado == JOptionPane.OK_OPTION) {
        			if (!functionName.getText().trim().equals("")) {
        				functionButtons.get(currentFunction).setText(functionName.getText().trim()+" "+combo.getSelectedItem().toString());
        				jplFunctionPane.revalidate();
        			}
        			if (checkbox1.isSelected()) {
        				functionButtons.remove(currentFunction);
        				jplProgrammingPanes.remove(currentFunction);
        				jplFunctionPane.remove(currentFunction);
        				jplProgramData.revalidate();
        				numberOfFunctions--;
        				currentFunction = 0;
        				borrado = true;
        				programmingPanel.setComponentes(jplProgrammingPanes.get(currentFunction).getComponentes());
        				programmingPanel.repaint();
        				jplFunctionPane.revalidate();
        			}
        		}
			}			
			// pone el panel de par�metros de la funci�n
			parameterVariables.get(currentFunction).setText(parameterVariablesFixed.getText());
			jplProgrammingPanes.get(currentFunction).setComponentes(programmingPanel.getComponentes());
			if (!borrado) // por si fue borrada la funci�n 
				currentFunction = i;
			parameterVariablesFixed.setText(parameterVariables.get(currentFunction).getText());
			parameterVariablesFixed.setOpaque(true);
			parameterVariablesFixed.setEditable(true);
			if (currentFunction == 0) {
    			parameterVariablesFixed.setOpaque(false);
    			parameterVariablesFixed.setEditable(false);
			}
			// cambia a la funci�n que se eligi�
			programmingPanel.setComponentes(jplProgrammingPanes.get(currentFunction).getComponentes());
			programmingPanel.repaint();
			String s = "Se cambi� a la funci�n " + functionButtons.get(currentFunction).getText();
			jtAreaOutput.append(s + "\n");
			jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument().getLength());
		}
	}

	// maneja los eventos para el canvas de programaci�n
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        // Verifica si dio click cerca de alg�n componente
        Componente componente = programmingPanel.checarClick(x, y);
        if (componente != null) {
        	if (componente.getTipo().equals("linea")) {
        		// Generar el popup con los datos que necesita ingresar el usuario
        		String[] items = {"Estatutos", "Condici�n", "Ciclo", "Lectura/Despliegue de Datos", "Llamada a Funci�n"};
                JComboBox combo = new JComboBox(items);
                JTextField field1 = new JTextField("Componente");
                JTextArea field2 = new JTextArea(5, 20);
                JPanel panel = new JPanel(new BorderLayout());
                JPanel panelHeader = new JPanel();
                panelHeader.add(new JLabel("Tipo de Componente:"));
                panelHeader.add(combo);
                JPanel panelCenter = new JPanel();
                panelCenter.add(new JLabel("Nombre del Componente:"));
                panelCenter.add(field1);
                JPanel panelFooter = new JPanel();
                panelFooter.add(new JLabel("Contenido:"));
                panelFooter.add(field2);
                panel.add(panelHeader, BorderLayout.NORTH);
                panel.add(panelCenter, BorderLayout.CENTER);
                panel.add(panelFooter, BorderLayout.SOUTH);
                // Crea el popup para que el usuario escoga su opci�n
        		int resultado = JOptionPane.showConfirmDialog(null, panel, "Nuevo",
        	            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        		if (resultado == JOptionPane.OK_OPTION) {
        			int x_componente = componente.getX();
        			int y_componente = componente.getY2();
        			if (combo.getSelectedItem().toString().equals("Estatutos"))
        			{
        				// Crea el dibujo
        				int [] xPoints = {x_componente-50, x_componente+50, x_componente+50, x_componente-50};
        				int [] yPoints = {y_componente, y_componente, y_componente+50, y_componente+50};
        				Polygon p = new Polygon (xPoints, yPoints, 4);
        				// Agrega el dibujo a los componentes
        				programmingPanel.agregaComponente(componente, combo.getSelectedItem().toString(), field1.getText(), field2.getText(), p);
        				programmingPanel.repaint();
        			}
        			else if (combo.getSelectedItem().toString().equals("Condici�n"))
        			{
        				// Crea el dibujo
        				int [] xPoints = {x_componente, x_componente+50, x_componente, x_componente-50};
        				int [] yPoints = {y_componente, y_componente+25, y_componente+50, y_componente+25};
        				Polygon p = new Polygon (xPoints, yPoints, 4);
        				// Agrega el dibujo a los componentes
        				programmingPanel.agregaComponente(componente, combo.getSelectedItem().toString(), field1.getText(), field2.getText(), p);
        				programmingPanel.repaint();
        			}
        			else if (combo.getSelectedItem().toString().equals("Ciclo"))
        			{
        				// Crea el dibujo
        				int [] xPoints = {x_componente-10, x_componente-50, x_componente-50, x_componente+50, x_componente+50, x_componente+15, 
        						x_componente+15, x_componente+10, x_componente+15, x_componente+15, x_componente+40, x_componente+40, 
        						x_componente-40, x_componente-40, x_componente-10};
        				int [] yPoints = {y_componente, y_componente, y_componente+50, y_componente+50, y_componente, y_componente, y_componente-5,
        						y_componente+5, y_componente+15, y_componente+10, y_componente+10, y_componente+40, y_componente+40, 
        						y_componente+10, y_componente+10};
        				Polygon p = new Polygon (xPoints, yPoints, 15);
        				// Agrega el dibujo a los componentes
        				programmingPanel.agregaComponente(componente, combo.getSelectedItem().toString(), field1.getText(), field2.getText(), p);
        				programmingPanel.repaint();
        			}
        			else if (combo.getSelectedItem().toString().equals("Lectura/Despliegue de Datos"))
        			{
        				// Crea el dibujo
        				int [] xPoints = {x_componente-35, x_componente+50, x_componente+35, x_componente-50};
        				int [] yPoints = {y_componente, y_componente, y_componente+50, y_componente+50};
        				Polygon p = new Polygon (xPoints, yPoints, 4);
        				// Agrega el dibujo a los componentes
        				programmingPanel.agregaComponente(componente, combo.getSelectedItem().toString(), field1.getText(), field2.getText(), p);
        				programmingPanel.repaint();
        			}
        			else if (combo.getSelectedItem().toString().equals("Llamada a Funci�n"))
        			{
        				// Crea el dibujo
        				int [] xPoints = {x_componente-50, x_componente-50, x_componente+40, x_componente+40, x_componente+45, x_componente+45,
        						x_componente+50, x_componente+45, x_componente+45, x_componente+40, x_componente+40};
        				int [] yPoints = {y_componente, y_componente+50, y_componente+50, y_componente+30, y_componente+30, y_componente+35,
        						y_componente+25, y_componente+15, y_componente+20, y_componente+20, y_componente};
        				Polygon p = new Polygon (xPoints, yPoints, 11);
        				// Agrega el dibujo a los componentes
        				programmingPanel.agregaComponente(componente, combo.getSelectedItem().toString(), field1.getText(), field2.getText(), p);
        				programmingPanel.repaint();
        			}
                    String s = "Se cre� un nuevo componente de tipo " + combo.getSelectedItem();
                    jtAreaOutput.append(s + "\n");
            		jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument().getLength());
                } else {
                	String s = "Acci�n cancelada";
                    jtAreaOutput.append(s + "\n");
            		jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument().getLength());
                }
        	}
        	// Se seleccion� un componente modificable
        	else {
        		JTextField field1 = new JTextField(componente.getNombre());
        		JTextComponent field2;
        		if (componente.getTipo().equals("Condici�n") || componente.getTipo().equals("Ciclo")) {
        			field2 = new JTextField(componente.getContenido());
        		}
        		else {
        			field2 = new JTextArea(componente.getContenido(), 5, 20);
        		}
                JCheckBox checkbox1 = new JCheckBox("DELETE");
                JPanel panel = new JPanel(new BorderLayout());
                JPanel panelHeader = new JPanel();
                panelHeader.add(new JLabel("Nombre del Componente:"));
                panelHeader.add(field1);
                panelHeader.add(checkbox1);
                JPanel panelCenter = new JPanel(new BorderLayout());
                panelCenter.add(new JLabel("Contenido:"), BorderLayout.PAGE_START);
                panelCenter.add(field2, BorderLayout.CENTER);
                panel.add(panelHeader, BorderLayout.NORTH);
                panel.add(panelCenter, BorderLayout.CENTER);
                // Crea el popup para que el usuario modifique a su componente
        		int resultado = JOptionPane.showConfirmDialog(null, panel, componente.getNombre(),
        	            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        		if (resultado == JOptionPane.OK_OPTION) {
        			// Checkbox para Borrar al componente
        			if (checkbox1.isSelected()) {
        				programmingPanel.borraComponente(componente);
        				programmingPanel.repaint();
        				String s = "Se borr� al componente " + componente.getNombre() + " de tipo " + componente.getTipo();
            	        jtAreaOutput.append(s + "\n");
            			jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument().getLength());
        			}
        			else {
        				programmingPanel.actualizaComponente(componente, field1.getText(), field2.getText());
        				programmingPanel.repaint();
        				String s = "Se modific� al componente " + componente.getNombre() + " de tipo " + componente.getTipo();
            	        jtAreaOutput.append(s + "\n");
            			jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument().getLength());
        			}
        		}
        		else {
        			String s = "Modificaci�n cancelada";
        	        jtAreaOutput.append(s + "\n");
        			jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument().getLength());
        		}
        	}
        }
    }

    // M�todos que se necesitan implementar por el MouseListener y MouseMotionListener
    public void mouseClicked (MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered (MouseEvent e) { }
    public void mouseExited  (MouseEvent e) { }
    public void mouseDragged (MouseEvent e) { }
    public void mouseMoved   (MouseEvent e) { }
	
    // M�todo invocado cuando se presiona el bot�n de compilar
    // Crea el archivo .ca (.patito) a compilar y correr
	private void generateOutputCode()
	{
		try{
			FileWriter ryt=new FileWriter(textField.getText()+".ac");
			BufferedWriter out=new BufferedWriter(ryt);
			String indentizacion = "\t";
			out.write("program " + textField.getText() + " {\n\n");
			String[] s = globalVariables.getText().split("\n");
			if (!globalVariables.getText().equals("")) {
				for (int i=0; i < s.length; i++)
					out.write(indentizacion + s[i] + ";\n");
			}
			out.write("\n");
			for (int i = 0; i < functionButtons.size(); i++)
			{
				String [] parametros = parameterVariables.get(i).getText().split("\n");
				String parametrosLiearizados = parametros[0];
				for (int j = 1; j < parametros.length; j++)
					parametrosLiearizados = parametrosLiearizados + ", " + parametros[j];
				out.write(indentizacion + "function " + functionButtons.get(i).getText() + "(" + parametrosLiearizados + "){");
				indentizacion = "\n\t" + indentizacion;
				ArrayList<Componente> componentes = jplProgrammingPanes.get(i).getComponentes();
				int nivelAnterior = 0;
				int faltaCerrar = 0;
				for (int j = 0; j < componentes.size(); j++) {
					if (!componentes.get(j).getTipo().equals("linea") && !componentes.get(j).getTipo().equals("oval")) {
						int nivelActual = componentes.get(j).getNivel();
						String indentizacionInterna = "";
						for (int k = 0; k < nivelActual; k++)
							indentizacionInterna += "\t";
						if (nivelActual < nivelAnterior || componentes.get(j).getPolygon().xpoints[0] > mitad) {
							out.write(indentizacion + indentizacionInterna + "}");
							if (componentes.get(j).getPolygon().xpoints[0] > mitad) {
								if (indentizacionInterna.length() > 2)
									indentizacionInterna = indentizacionInterna.substring(0, indentizacionInterna.length()-2);
								out.write(indentizacion + indentizacionInterna + "else {");
								indentizacionInterna += "\t";
								if (componentes.get(j).getTipo().equals("Ciclo")) {
									String contenido = componentes.get(j).getContenido();
									out.write(indentizacion + indentizacionInterna + "while (" + contenido + ") {");
									faltaCerrar++;
								} 
								else if (componentes.get(j).getTipo().equals("Condici�n")) {
									String contenido = componentes.get(j).getContenido();
									out.write(indentizacion + indentizacionInterna + "if (" + contenido + ") {");
									faltaCerrar++;
								}
								else {
									String [] contenido = componentes.get(j).getContenido().split("\n");
									for (int c = 0; c < contenido.length; c++) {
										out.write(indentizacion + indentizacionInterna + contenido[c] + ";");
									}
								}
							}
							else
								faltaCerrar--;
						}
						if (componentes.get(j).getTipo().equals("Ciclo")) {
							String contenido = componentes.get(j).getContenido();
							out.write(indentizacion + indentizacionInterna + "while (" + contenido + ") {");
							faltaCerrar++;
						} 
						else if (componentes.get(j).getTipo().equals("Condici�n")) {
							String contenido = componentes.get(j).getContenido();
							out.write(indentizacion + indentizacionInterna + "if (" + contenido + ") {");
							faltaCerrar++;
						}
						else {
							String [] contenido = componentes.get(j).getContenido().split("\n");
							for (int c = 0; c < contenido.length; c++) {
								out.write(indentizacion + indentizacionInterna + contenido[c] + ";");
							}
						}
						nivelAnterior = nivelActual;
					}
				}
				while (faltaCerrar > 0) {
					String indentizacionInterna = "";
					for (int k = 0; k < faltaCerrar-1; k++)
						indentizacionInterna += "\t";
					out.write(indentizacion + indentizacionInterna + "}");
					faltaCerrar--;
				}
				indentizacion = "\t";
				out.write("\n" + indentizacion + "}\n");
			}
			out.write("\n}");
			out.close();
		}
		catch (Exception e)
		{
			String s = e.toString();
			jtAreaOutput.append(s + "\n");
			jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument().getLength());
		}
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				createGUI();
			}
		});
	}
}