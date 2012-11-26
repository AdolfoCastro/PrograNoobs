package compiladores;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MaquinaVirtual {
	
	public ArrayList<Quadruplo> quadruplos = new ArrayList<Quadruplo>(); //lista de los quádruplos cargados del código intermedio
	private ArrayList<VariableInt> contEntGlo = new ArrayList<VariableInt>(); //lista de las variables enteras globales cargadas del código intermedio
	private ArrayList<VariableFloat> contFlotGlo = new ArrayList<VariableFloat>(); //lista de las variables flotantes globales cargadas del código intermedio
	private ArrayList<VariableStr> contStrGlo = new ArrayList<VariableStr>(); //lista de las variables String globales cargadas del código intermedio
	private ArrayList<VariableBool> contBoolGlo = new ArrayList<VariableBool>(); //lista de las variables booleanas globales cargadas del código intermedio
	private ArrayList<VariableInt> contEntLoc = new ArrayList<VariableInt>(); //lista de las variables enteras locales cargadas del código intermedio
	private ArrayList<VariableFloat> contFlotLoc = new ArrayList<VariableFloat>(); //lista de las variables flotantes locales cargadas del código intermedio
	private ArrayList<VariableStr> contStrLoc = new ArrayList<VariableStr>(); //lista de las variables String locales cargadas del código intermedio
	private ArrayList<VariableBool> contBoolLoc = new ArrayList<VariableBool>(); //lista de las variables booleanas locales cargadas del código intermedio
	private ArrayList<VariableInt> contEntTmp = new ArrayList<VariableInt>(); //lista de las variables enteras temporales cargadas del código intermedio
	private ArrayList<VariableFloat> contFlotTmp = new ArrayList<VariableFloat>(); //lista de las variables flotantes temporales cargadas del código intermedio
	private ArrayList<VariableStr> contStrTmp = new ArrayList<VariableStr>(); //lista de las variables String temporales cargadas del código intermedio
	private ArrayList<VariableBool> contBoolTmp = new ArrayList<VariableBool>(); //lista de las variables booleanas temporales cargadas del código intermedio
	private ArrayList<VariableInt> contEntCons = new ArrayList<VariableInt>(); //lista de las variables enteras constantes cargadas del código intermedio
	private ArrayList<VariableFloat> contFlotCons = new ArrayList<VariableFloat>(); //lista de las variables flotantes constantes cargadas del código intermedio
	private ArrayList<VariableStr> contStrCons = new ArrayList<VariableStr>(); //lista de las variables String constantes cargadas del código intermedio
	private ArrayList<VariableBool> contBoolCons = new ArrayList<VariableBool>(); //lista de las variables booleanas constantes cargadas del código intermedio
	private ArrayList<Procedimiento> procedimientos = new ArrayList<Procedimiento>();
	private JTextArea jtAreaOutput; //área de texto donde va a imprimir
	private int [] apuntadoresInstruccion; //apunta a la instrucción a ejecutarse de los quádruplos
	private int apuntador; //apunta a la posición del arreglo de instruccinoes para ejecutar el quádruplo
	private int[] offsets = new int [8];;
	private int[] before = new int[8];
	private boolean enableOffsets;
	private ArrayList<String> procedimientoActual = new ArrayList<String>();
	
	// inicializa la maquina virtual
	public MaquinaVirtual (JTextArea jtAreaOutput) {
		this.jtAreaOutput = jtAreaOutput;
		apuntadoresInstruccion = new int [1000];
		for (int i = 0; i < 1000; i++)
			apuntadoresInstruccion[i] = 0;
		for (int i = 0; i < 8; i++)
			offsets[i] = 0;
		apuntador = 0;
		enableOffsets = false;
	}
	
	/* ejecuta el programa.
	 * params: String
	 */
	public void ejecutar (String fileName) {
		cargaArchivo(fileName);
		procedimientoActual.add("Main");
		cargaProcedimiento(procedimientoActual.get(procedimientoActual.size()-1));
		do {
			int comando = Integer.parseInt(quadruplos.get(apuntadoresInstruccion[apuntador]).getComando());
			int direccionVar1 = 0;
			if (comando != 40 && comando != 42)
				direccionVar1 = Integer.parseInt(quadruplos.get(apuntadoresInstruccion[apuntador]).getVariable1());
			int direccionVar2 = Integer.parseInt(quadruplos.get(apuntadoresInstruccion[apuntador]).getVariable2());
			int direccionDestino = Integer.parseInt(quadruplos.get(apuntadoresInstruccion[apuntador]).getDestino());
			String[] valores1 = new String[2];
			String[] valores2 = new String[2];
			if ((comando >= 1 && comando <= 4) || (direccionVar1 != 0 && comando != 20 && comando != 40 && comando != 42)) {
				if (apuntadoresInstruccion[apuntador] == 7)
					valores1 = getValor(direccionVar1, before);
				else
					valores1 = getValor(direccionVar1, offsets);
			}
			if ((comando >= 1 && comando <= 4) || (direccionVar2 != 0 && comando != 20 && comando != 40 && comando != 42))
				valores2 = getValor(direccionVar2, offsets);
			switch (comando)
			{
			case 1: // +
				if (direccionDestino >= 0 && direccionDestino < 5000) {
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
								
							}
							else {
								// error porque tiene que ser entero
							}
						}					
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) + Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						if (valores1[1].equals("float")) {
							if (valores2[1].equals("int")) {
								contFlotGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) + Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}	
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 5000 && direccionDestino < 10000) {
					direccionDestino -= 5000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}					
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) + Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						if (valores1[1].equals("float")) {
							if (valores2[1].equals("int")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Float.parseFloat(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Float.parseFloat(valores1[0]) + Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Float.parseFloat(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}	
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 10000 && direccionDestino < 15000) {
					direccionDestino -= 10000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
								
							}
							else {
								// error porque tiene que ser entero
							}
						}					
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) + Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						if (valores1[1].equals("float")) {
							if (valores2[1].equals("int")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Float.parseFloat(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Float.parseFloat(valores1[0]) + Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Float.parseFloat(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}	
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else {
					direccionDestino -= 15000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}					
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) + Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
				}
				break;
			case 2: // -
				if (direccionDestino >= 0 && direccionDestino < 5000) {
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
								
							}
							else {
								// error porque tiene que ser entero
							}
						}					
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						if (valores1[1].equals("float")) {
							if (valores2[1].equals("int")) {
								contFlotGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) - Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}	
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 5000 && direccionDestino < 10000) {
					direccionDestino -= 5000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}					
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) - Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						if (valores1[1].equals("float")) {
							if (valores2[1].equals("int")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Float.parseFloat(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Float.parseFloat(valores1[0]) - Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Float.parseFloat(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}	
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 10000 && direccionDestino < 15000) {
					direccionDestino -= 10000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
								
							}
							else {
								// error porque tiene que ser entero
							}
						}					
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) - Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						if (valores1[1].equals("float")) {
							if (valores2[1].equals("int")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Float.parseFloat(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Float.parseFloat(valores1[0]) - Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Float.parseFloat(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}	
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else {
					direccionDestino -= 15000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}					
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contFlotCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						if (valores1[1].equals("float")) {
							if (valores2[1].equals("int")) {
								contFlotCons.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotCons.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) - Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotCons.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}	
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) - Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				break;
			case 3: // *
				if (direccionDestino >= 0 && direccionDestino < 5000) {
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
								
							}
							else {
								// error porque tiene que ser entero
							}
						}					
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						if (valores1[1].equals("float")) {
							if (valores2[1].equals("int")) {
								contFlotGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) * Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}	
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 5000 && direccionDestino < 10000) {
					direccionDestino -= 5000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}					
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) * Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						if (valores1[1].equals("float")) {
							if (valores2[1].equals("int")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Float.parseFloat(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Float.parseFloat(valores1[0]) * Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Float.parseFloat(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}	
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 10000 && direccionDestino < 15000) {
					direccionDestino -= 10000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
								
							}
							else {
								// error porque tiene que ser entero
							}
						}					
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) * Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						if (valores1[1].equals("float")) {
							if (valores2[1].equals("int")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Float.parseFloat(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Float.parseFloat(valores1[0]) * Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Float.parseFloat(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}	
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else {
					direccionDestino -= 15000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}					
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contFlotCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						if (valores1[1].equals("float")) {
							if (valores2[1].equals("int")) {
								contFlotCons.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotCons.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) * Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotCons.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}	
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) * Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				break;
			case 4: // /
				if (direccionDestino >= 0 && direccionDestino < 5000) {
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
								
							}
							else {
								// error porque tiene que ser entero
							}
						}					
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						if (valores1[1].equals("float")) {
							if (valores2[1].equals("int")) {
								contFlotGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) / Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}	
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 5000 && direccionDestino < 10000) {
					direccionDestino -= 5000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}					
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) / Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						if (valores1[1].equals("float")) {
							if (valores2[1].equals("int")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Float.parseFloat(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Float.parseFloat(valores1[0]) / Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Float.parseFloat(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}	
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 10000 && direccionDestino < 15000) {
					direccionDestino -= 10000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
								
							}
							else {
								// error porque tiene que ser entero
							}
						}					
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) / Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						if (valores1[1].equals("float")) {
							if (valores2[1].equals("int")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Float.parseFloat(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Float.parseFloat(valores1[0]) / Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Float.parseFloat(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}	
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else {
					direccionDestino -= 15000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}					
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							if (valores2[1].equals("int")) {
								contFlotCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						if (valores1[1].equals("float")) {
							if (valores2[1].equals("int")) {
								contFlotCons.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("float")) {
								contFlotCons.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) / Float.parseFloat(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contFlotCons.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}	
						else if (valores1[1].equals("boolean")) {
							if (valores2[1].equals("int")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else if (valores2[1].equals("boolean")) {
								contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) / Integer.parseInt(valores2[0]));
							}
							else {
								// error porque tiene que ser entero
							}
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				break;
			case 8: // =
				if (direccionDestino >= 0 && direccionDestino < 5000) {
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]));
						}					
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]));
						}
						if (valores1[1].equals("float")) {
							contFlotGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]));
						}	
						else if (valores1[1].equals("boolean")) {
							contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]));
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						if (valores1[1].equals("string")) {
							contStrGlo.get(direccionDestino).setValor(valores1[0]);
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("boolean")) {
							contBoolGlo.get(direccionDestino).setValor(Boolean.parseBoolean(valores1[0]));
						}
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 5000 && direccionDestino < 10000) {
					direccionDestino -= 5000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]));
						}					
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]));
						}
						if (valores1[1].equals("float")) {
							contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Float.parseFloat(valores1[0]));
						}	
						else if (valores1[1].equals("boolean")) {
							contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]));
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						if (valores1[1].equals("string")) {
							contStrLoc.get(direccionDestino+(enableOffsets?offsets[2]:0)).setValor(valores1[0]);
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("boolean")) {
							contBoolLoc.get(direccionDestino+(enableOffsets?offsets[3]:0)).setValor(Boolean.parseBoolean(valores1[0]));
						}
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 10000 && direccionDestino < 15000) {
					direccionDestino -= 10000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]));
						}					
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]));
						}
						if (valores1[1].equals("float")) {
							contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Float.parseFloat(valores1[0]));
						}	
						else if (valores1[1].equals("boolean")) {
							contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]));
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						if (valores1[1].equals("string")) {
							contStrTmp.get(direccionDestino+(enableOffsets?offsets[6]:0)).setValor(valores1[0]);
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("boolean")) {
							contBoolTmp.get(direccionDestino+(enableOffsets?offsets[7]:0)).setValor(Boolean.parseBoolean(valores1[0]));
						}
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else {
					direccionDestino -= 15000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]));
						}					
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							contFlotCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]));
						}
						if (valores1[1].equals("float")) {
							contFlotCons.get(direccionDestino).setValor(Float.parseFloat(valores1[0]));
						}	
						else if (valores1[1].equals("boolean")) {
							contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]));
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						if (valores1[1].equals("string")) {
							contStrCons.get(direccionDestino).setValor(valores1[0]);
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("boolean")) {
							contBoolCons.get(direccionDestino).setValor(Boolean.parseBoolean(valores1[0]));
						}
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				break;
			case 11: // ==
				if (direccionDestino >= 0 && direccionDestino < 5000) {
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						// Error
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						// Error
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("int") && valores2[1].equals("int")) {
							contBoolGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) == Integer.parseInt(valores2[0]));
						}
						if (valores1[1].equals("float") && valores2[1].equals("float")) {
							contBoolGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) == Float.parseFloat(valores2[0]));
						}
						else if (valores1[1].equals("boolean") && valores2[1].equals("boolean")) {
							contBoolGlo.get(direccionDestino).setValor(Boolean.parseBoolean(valores1[0]) == Boolean.parseBoolean(valores2[0]));
						}
						else {
							// Error
						}
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 5000 && direccionDestino < 10000) {
					direccionDestino -= 5000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						// Error
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						// Error
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("int") && valores2[1].equals("int")) {
							contBoolLoc.get(direccionDestino+(enableOffsets?offsets[3]:0)).setValor(Integer.parseInt(valores1[0]) == Integer.parseInt(valores2[0]));
						}
						if (valores1[1].equals("float") && valores2[1].equals("float")) {
							contBoolLoc.get(direccionDestino+(enableOffsets?offsets[3]:0)).setValor(Float.parseFloat(valores1[0]) == Float.parseFloat(valores2[0]));
						}
						else if (valores1[1].equals("boolean") && valores2[1].equals("boolean")) {
							contBoolLoc.get(direccionDestino+(enableOffsets?offsets[3]:0)).setValor(Boolean.parseBoolean(valores1[0]) == Boolean.parseBoolean(valores2[0]));
						}
						else {
							// Error
						}
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 10000 && direccionDestino < 15000) {
					direccionDestino -= 10000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						// Error
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						// Error
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("int") && valores2[1].equals("int")) {
							contBoolTmp.get(direccionDestino+(enableOffsets?offsets[7]:0)).setValor(Integer.parseInt(valores1[0]) == Integer.parseInt(valores2[0]));
						}
						if (valores1[1].equals("float") && valores2[1].equals("float")) {
							contBoolTmp.get(direccionDestino+(enableOffsets?offsets[7]:0)).setValor(Float.parseFloat(valores1[0]) == Float.parseFloat(valores2[0]));
						}
						else if (valores1[1].equals("boolean") && valores2[1].equals("boolean")) {
							contBoolTmp.get(direccionDestino+(enableOffsets?offsets[7]:0)).setValor(Boolean.parseBoolean(valores1[0]) == Boolean.parseBoolean(valores2[0]));
						}
						else {
							// Error
						}
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else {
					direccionDestino -= 15000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						// Error
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						// Error
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("int") && valores2[1].equals("int")) {
							contBoolCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) == Integer.parseInt(valores2[0]));
						}
						if (valores1[1].equals("float") && valores2[1].equals("float")) {
							contBoolCons.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) == Float.parseFloat(valores2[0]));
						}
						else if (valores1[1].equals("boolean") && valores2[1].equals("boolean")) {
							contBoolCons.get(direccionDestino).setValor(Boolean.parseBoolean(valores1[0]) == Boolean.parseBoolean(valores2[0]));
						}
						else {
							// Error
						}
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				break;
			case 12: // >
				if (direccionDestino >= 0 && direccionDestino < 5000) {
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						// Error
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						// Error
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("int") && valores2[1].equals("int")) {
							contBoolGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) > Integer.parseInt(valores2[0]));
						}
						if (valores1[1].equals("float") && valores2[1].equals("float")) {
							contBoolGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) > Float.parseFloat(valores2[0]));
						}
						else {
							// Error
						}
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 5000 && direccionDestino < 10000) {
					direccionDestino -= 5000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						// Error
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						// Error
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("int") && valores2[1].equals("int")) {
							contBoolLoc.get(direccionDestino+(enableOffsets?offsets[3]:0)).setValor(Integer.parseInt(valores1[0]) > Integer.parseInt(valores2[0]));
						}
						if (valores1[1].equals("float") && valores2[1].equals("float")) {
							contBoolLoc.get(direccionDestino+(enableOffsets?offsets[3]:0)).setValor(Float.parseFloat(valores1[0]) > Float.parseFloat(valores2[0]));
						}
						else {
							// Error
						}
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 10000 && direccionDestino < 15000) {
					direccionDestino -= 10000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						// Error
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						// Error
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("int") && valores2[1].equals("int")) {
							contBoolTmp.get(direccionDestino+(enableOffsets?offsets[7]:0)).setValor(Integer.parseInt(valores1[0]) > Integer.parseInt(valores2[0]));
						}
						if (valores1[1].equals("float") && valores2[1].equals("float")) {
							contBoolTmp.get(direccionDestino+(enableOffsets?offsets[7]:0)).setValor(Float.parseFloat(valores1[0]) > Float.parseFloat(valores2[0]));
						}
						else {
							// Error
						}
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else {
					direccionDestino -= 15000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						// Error
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						// Error
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("int") && valores2[1].equals("int")) {
							contBoolCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) > Integer.parseInt(valores2[0]));
						}
						if (valores1[1].equals("float") && valores2[1].equals("float")) {
							contBoolCons.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) > Float.parseFloat(valores2[0]));
						}
						else {
							// Error
						}
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				break;
			case 13: // <
				if (direccionDestino >= 0 && direccionDestino < 5000) {
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						// Error
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						// Error
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("int") && valores2[1].equals("int")) {
							contBoolGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) < Integer.parseInt(valores2[0]));
						}
						if (valores1[1].equals("float") && valores2[1].equals("float")) {
							contBoolGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) < Float.parseFloat(valores2[0]));
						}
						else {
							// Error
						}
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 5000 && direccionDestino < 10000) {
					direccionDestino -= 5000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						// Error
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						// Error
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("int") && valores2[1].equals("int")) {
							contBoolLoc.get(direccionDestino+(enableOffsets?offsets[3]:0)).setValor(Integer.parseInt(valores1[0]) < Integer.parseInt(valores2[0]));
						}
						if (valores1[1].equals("float") && valores2[1].equals("float")) {
							contBoolLoc.get(direccionDestino+(enableOffsets?offsets[3]:0)).setValor(Float.parseFloat(valores1[0]) < Float.parseFloat(valores2[0]));
						}
						else {
							// Error
						}
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 10000 && direccionDestino < 15000) {
					direccionDestino -= 10000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						// Error
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						// Error
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("int") && valores2[1].equals("int")) {
							contBoolTmp.get(direccionDestino+(enableOffsets?offsets[7]:0)).setValor(Integer.parseInt(valores1[0]) < Integer.parseInt(valores2[0]));
						}
						if (valores1[1].equals("float") && valores2[1].equals("float")) {
							contBoolTmp.get(direccionDestino+(enableOffsets?offsets[7]:0)).setValor(Float.parseFloat(valores1[0]) < Float.parseFloat(valores2[0]));
						}
						else {
							// Error
						}
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else {
					direccionDestino -= 15000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						// Error
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						// Error
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("int") && valores2[1].equals("int")) {
							contBoolCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) < Integer.parseInt(valores2[0]));
						}
						if (valores1[1].equals("float") && valores2[1].equals("float")) {
							contBoolCons.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) < Float.parseFloat(valores2[0]));
						}
						else {
							// Error
						}
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				break;
			case 14: // <>
				if (direccionDestino >= 0 && direccionDestino < 5000) {
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						// Error
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						// Error
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("int") && valores2[1].equals("int")) {
							contBoolGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) != Integer.parseInt(valores2[0]));
						}
						if (valores1[1].equals("float") && valores2[1].equals("float")) {
							contBoolGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) != Float.parseFloat(valores2[0]));
						}
						else if (valores1[1].equals("boolean") && valores2[1].equals("boolean")) {
							contBoolGlo.get(direccionDestino).setValor(Boolean.parseBoolean(valores1[0]) != Boolean.parseBoolean(valores2[0]));
						}
						else {
							// Error
						}
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 5000 && direccionDestino < 10000) {
					direccionDestino -= 5000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						// Error
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						// Error
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("int") && valores2[1].equals("int")) {
							contBoolLoc.get(direccionDestino+(enableOffsets?offsets[3]:0)).setValor(Integer.parseInt(valores1[0]) != Integer.parseInt(valores2[0]));
						}
						if (valores1[1].equals("float") && valores2[1].equals("float")) {
							contBoolLoc.get(direccionDestino+(enableOffsets?offsets[3]:0)).setValor(Float.parseFloat(valores1[0]) != Float.parseFloat(valores2[0]));
						}
						else if (valores1[1].equals("boolean") && valores2[1].equals("boolean")) {
							contBoolLoc.get(direccionDestino+(enableOffsets?offsets[3]:0)).setValor(Boolean.parseBoolean(valores1[0]) != Boolean.parseBoolean(valores2[0]));
						}
						else {
							// Error
						}
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 10000 && direccionDestino < 15000) {
					direccionDestino -= 10000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						// Error
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						// Error
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("int") && valores2[1].equals("int")) {
							contBoolTmp.get(direccionDestino+(enableOffsets?offsets[7]:0)).setValor(Integer.parseInt(valores1[0]) != Integer.parseInt(valores2[0]));
						}
						if (valores1[1].equals("float") && valores2[1].equals("float")) {
							contBoolTmp.get(direccionDestino+(enableOffsets?offsets[7]:0)).setValor(Float.parseFloat(valores1[0]) != Float.parseFloat(valores2[0]));
						}
						else if (valores1[1].equals("boolean") && valores2[1].equals("boolean")) {
							contBoolTmp.get(direccionDestino+(enableOffsets?offsets[7]:0)).setValor(Boolean.parseBoolean(valores1[0]) != Boolean.parseBoolean(valores2[0]));
						}
						else {
							// Error
						}
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else {
					direccionDestino -= 15000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						// Error
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						// Error
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						// Error
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("int") && valores2[1].equals("int")) {
							contBoolCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]) != Integer.parseInt(valores2[0]));
						}
						if (valores1[1].equals("float") && valores2[1].equals("float")) {
							contBoolCons.get(direccionDestino).setValor(Float.parseFloat(valores1[0]) != Float.parseFloat(valores2[0]));
						}
						else if (valores1[1].equals("boolean") && valores2[1].equals("boolean")) {
							contBoolCons.get(direccionDestino).setValor(Boolean.parseBoolean(valores1[0]) != Boolean.parseBoolean(valores2[0]));
						}
						else {
							// Error
						}
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				break;
			case 20: // goto
				apuntadoresInstruccion[apuntador] = direccionVar1;
				break;
			case 21: // gotof
				if (!Boolean.parseBoolean(valores1[0]))
					apuntadoresInstruccion[apuntador] = direccionDestino-1;
				break;
			case 22: // gotov
				if (Boolean.parseBoolean(valores1[0]))
					apuntadoresInstruccion[apuntador] = direccionDestino;
				break;
			case 30: // print
				jtAreaOutput.append(valores1[0] + "\n");
				jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument().getLength());
				break;
			case 31: // read
				JPanel panel = new JPanel (new BorderLayout());
				JTextField functionName = new JTextField();
				panel.add(functionName, BorderLayout.CENTER);
				int resultado = JOptionPane.showConfirmDialog(null, panel, "Input",
	    	            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (resultado == JOptionPane.OK_OPTION) {
					if (direccionDestino >= 0 && direccionDestino < 5000) {
						if (direccionDestino >= 0 && direccionDestino < 1000) {
							contEntGlo.get(direccionDestino).setValor(Integer.parseInt(functionName.getText()));
						}
						else if (direccionDestino >= 1000 && direccionDestino < 2000) {
							direccionDestino -= 1000;
							contFlotGlo.get(direccionDestino).setValor(Float.parseFloat(functionName.getText()));
						}
						else if (direccionDestino >= 2000 && direccionDestino < 3000) {
							direccionDestino -= 2000;
							contStrGlo.get(direccionDestino).setValor(functionName.getText());
						}
						else if (direccionDestino >= 3000 && direccionDestino < 4000) {
							direccionDestino -= 3000;
							contBoolGlo.get(direccionDestino).setValor(Boolean.parseBoolean(functionName.getText()));
						}
						else {
							direccionDestino -= 4000;
							//No se usa
						}
					}
					else if (direccionDestino >= 5000 && direccionDestino < 10000) {
						direccionDestino -= 5000;
						if (direccionDestino >= 0 && direccionDestino < 1000) {
							contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(functionName.getText()));
						}
						else if (direccionDestino >= 1000 && direccionDestino < 2000) {
							direccionDestino -= 1000;
							contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Float.parseFloat(functionName.getText()));
						}
						else if (direccionDestino >= 2000 && direccionDestino < 3000) {
							direccionDestino -= 2000;
							contStrLoc.get(direccionDestino+(enableOffsets?offsets[2]:0)).setValor(functionName.getText());
						}
						else if (direccionDestino >= 3000 && direccionDestino < 4000) {
							direccionDestino -= 3000;
							contBoolLoc.get(direccionDestino+(enableOffsets?offsets[3]:0)).setValor(Boolean.parseBoolean(functionName.getText()));
						}
						else {
							direccionDestino -= 4000;
							//No se usa
						}
					}
					else if (direccionDestino >= 10000 && direccionDestino < 15000) {
						direccionDestino -= 10000;
						if (direccionDestino >= 0 && direccionDestino < 1000) {
							contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(functionName.getText()));
						}
						else if (direccionDestino >= 1000 && direccionDestino < 2000) {
							direccionDestino -= 1000;
							contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Float.parseFloat(functionName.getText()));
						}
						else if (direccionDestino >= 2000 && direccionDestino < 3000) {
							direccionDestino -= 2000;
							contStrTmp.get(direccionDestino+(enableOffsets?offsets[6]:0)).setValor(functionName.getText());
						}
						else if (direccionDestino >= 3000 && direccionDestino < 4000) {
							direccionDestino -= 3000;
							contBoolTmp.get(direccionDestino+(enableOffsets?offsets[7]:0)).setValor(Boolean.parseBoolean(functionName.getText()));
						}
						else {
							direccionDestino -= 4000;
							//No se usa
						}
					}
					else {
						direccionDestino -= 15000;
						if (direccionDestino >= 0 && direccionDestino < 1000) {
							contEntCons.get(direccionDestino).setValor(Integer.parseInt(functionName.getText()));
						}
						else if (direccionDestino >= 1000 && direccionDestino < 2000) {
							direccionDestino -= 1000;
							contFlotCons.get(direccionDestino).setValor(Float.parseFloat(functionName.getText()));
						}
						else if (direccionDestino >= 2000 && direccionDestino < 3000) {
							direccionDestino -= 2000;
							contStrCons.get(direccionDestino).setValor(functionName.getText());
						}
						else if (direccionDestino >= 3000 && direccionDestino < 4000) {
							direccionDestino -= 3000;
							contBoolCons.get(direccionDestino).setValor(Boolean.parseBoolean(functionName.getText()));
						}
						else {
							direccionDestino -= 4000;
							//No se usa
						}
					}					
				}
				break;
			case 40: // ERA
				offsets[0] = contEntLoc.size();
				offsets[1] = contFlotLoc.size();
				offsets[2] = contStrLoc.size();
				offsets[3] = contBoolLoc.size();
				offsets[4] = contEntTmp.size();
				offsets[5] = contFlotTmp.size();
				offsets[6] = contStrTmp.size();
				offsets[7] = contBoolTmp.size();
				cargaProcedimiento(quadruplos.get(apuntadoresInstruccion[apuntador]).getVariable1());
				break;
			case 41: // param
				enableOffsets = true;
				for (int i = 0; i < before.length; i++)
					before[i] = offsets[i];
				if (direccionDestino >= 0 && direccionDestino < 5000) {
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]));
						}					
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]));
						}
						if (valores1[1].equals("float")) {
							contFlotGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]));
						}	
						else if (valores1[1].equals("boolean")) {
							contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]));
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						if (valores1[1].equals("string")) {
							contStrGlo.get(direccionDestino).setValor(valores1[0]);
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("boolean")) {
							contBoolGlo.get(direccionDestino).setValor(Boolean.parseBoolean(valores1[0]));
						}
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 5000 && direccionDestino < 10000) {
					direccionDestino -= 5000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							contEntLoc.get(direccionDestino+(enableOffsets?offsets[0]:0)).setValor(Integer.parseInt(valores1[0]));
						}					
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]));
						}
						if (valores1[1].equals("float")) {
							contFlotLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Float.parseFloat(valores1[0]));
						}	
						else if (valores1[1].equals("boolean")) {
							contEntLoc.get(direccionDestino+(enableOffsets?offsets[1]:0)).setValor(Integer.parseInt(valores1[0]));
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						if (valores1[1].equals("string")) {
							contStrLoc.get(direccionDestino+(enableOffsets?offsets[2]:0)).setValor(valores1[0]);
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("boolean")) {
							contBoolLoc.get(direccionDestino+(enableOffsets?offsets[3]:0)).setValor(Boolean.parseBoolean(valores1[0]));
						}
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else if (direccionDestino >= 10000 && direccionDestino < 15000) {
					direccionDestino -= 10000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							contEntTmp.get(direccionDestino+(enableOffsets?offsets[4]:0)).setValor(Integer.parseInt(valores1[0]));
						}					
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]));
						}
						if (valores1[1].equals("float")) {
							contFlotTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Float.parseFloat(valores1[0]));
						}	
						else if (valores1[1].equals("boolean")) {
							contEntTmp.get(direccionDestino+(enableOffsets?offsets[5]:0)).setValor(Integer.parseInt(valores1[0]));
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						if (valores1[1].equals("string")) {
							contStrTmp.get(direccionDestino+(enableOffsets?offsets[6]:0)).setValor(valores1[0]);
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("boolean")) {
							contBoolTmp.get(direccionDestino+(enableOffsets?offsets[7]:0)).setValor(Boolean.parseBoolean(valores1[0]));
						}
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else {
					direccionDestino -= 15000;
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]));
						}					
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							contFlotCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]));
						}
						if (valores1[1].equals("float")) {
							contFlotCons.get(direccionDestino).setValor(Float.parseFloat(valores1[0]));
						}	
						else if (valores1[1].equals("boolean")) {
							contEntCons.get(direccionDestino).setValor(Integer.parseInt(valores1[0]));
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						if (valores1[1].equals("string")) {
							contStrCons.get(direccionDestino).setValor(valores1[0]);
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("boolean")) {
							contBoolCons.get(direccionDestino).setValor(Boolean.parseBoolean(valores1[0]));
						}
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				break;
			case 42: // gosub
				apuntador++;
				apuntadoresInstruccion[apuntador] = getNumeroInstruccion(quadruplos.get(apuntadoresInstruccion[apuntador-1]).getVariable1());
				procedimientoActual.add(quadruplos.get(apuntadoresInstruccion[apuntador-1]).getVariable1());
				break;
			case 50: // RETURN
				if (direccionDestino >= 0 && direccionDestino < 5000) {
					if (direccionDestino >= 0 && direccionDestino < 1000) {
						if (valores1[1].equals("int")) {
							contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]));
						}					
						else {
							// Error
						}
					}
					else if (direccionDestino >= 1000 && direccionDestino < 2000) {
						direccionDestino -= 1000;
						if (valores1[1].equals("int")) {
							contFlotGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]));
						}
						if (valores1[1].equals("float")) {
							contFlotGlo.get(direccionDestino).setValor(Float.parseFloat(valores1[0]));
						}	
						else if (valores1[1].equals("boolean")) {
							contEntGlo.get(direccionDestino).setValor(Integer.parseInt(valores1[0]));
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 2000 && direccionDestino < 3000) {
						direccionDestino -= 2000;
						if (valores1[1].equals("string")) {
							contStrGlo.get(direccionDestino).setValor(valores1[0]);
						}
						else {
							// Error
						}
					}
					else if (direccionDestino >= 3000 && direccionDestino < 4000) {
						direccionDestino -= 3000;
						if (valores1[1].equals("boolean")) {
							contBoolGlo.get(direccionDestino).setValor(Boolean.parseBoolean(valores1[0]));
						}
						// Error
					}
					else {
						direccionDestino -= 4000;
						//No se usa
					}
				}
				else {
					// Error
				}
				break;
			case 51: // RET
				for (int i = 0; i < before.length; i++) {
						offsets[i] = before[i];
						before[i] = 0;
				}
				apuntadoresInstruccion[apuntador] = 0;
				apuntador--;
				descargaProcedimiento(procedimientoActual.get(procedimientoActual.size()-1));
				procedimientoActual.remove(procedimientoActual.size()-1);
				break;
			case 100: // end
				apuntadoresInstruccion[apuntador] = quadruplos.size();
			}
			if (comando != 20 && comando != 42 && comando != 100)
				apuntadoresInstruccion[apuntador]++;
		} while (apuntadoresInstruccion[apuntador] < quadruplos.size());
		clearAll();
	}
	
	/* lee el archivo y coloca los quadruplos en el ArrayList quadruplos.
	 * params: String fileName
	 */
	private void cargaArchivo (String fileName)
	{
		try {
			BufferedReader archivo = new BufferedReader(new FileReader (fileName));
			String linea = "";
			// carga los cuádruplos
			while ((linea = archivo.readLine()) != null && !linea.equals("-"))
			{
				String[] quadruplo = linea.split(",");
				quadruplos.add(0, new Quadruplo(quadruplo[0],quadruplo[1],quadruplo[2],quadruplo[3].trim()));
			}
			// carga las constantes
			while ((linea = archivo.readLine()) != null && !linea.equals("-")) {
				String[] variable = linea.split("-");
				int direccion = Integer.parseInt(variable[0]) - 15000;
				if (direccion >= 0 && direccion < 1000) {
					contEntCons.add(0,new VariableInt(variable[1].trim()));
				}
				else if (direccion >= 1000 && direccion < 2000) {
					contFlotCons.add(0,new VariableFloat(variable[1].trim()));
				}
				else if (direccion >= 2000 && direccion < 3000) {
					contStrCons.add(0,new VariableStr(variable[1].trim()));
				}
				else if (direccion >= 3000 && direccion < 4000) {
					contBoolCons.add(0,new VariableBool(variable[1].trim()));
				}
			}
			// agrega las variables globales
			linea = archivo.readLine();
			String[] procedimiento = linea.split("-");
			for (int i = 0; i < Integer.parseInt(procedimiento[0]); i++)
				contEntGlo.add(new VariableInt());
			for (int i = 0; i < Integer.parseInt(procedimiento[1]); i++)
				contFlotGlo.add(new VariableFloat());
			for (int i = 0; i < Integer.parseInt(procedimiento[2]); i++)
				contStrGlo.add(new VariableStr());
			for (int i = 0; i < Integer.parseInt(procedimiento[3]); i++)
				contBoolGlo.add(new VariableBool());
			// carga el directorio de procedimientos
			while ((linea = archivo.readLine()) != null && !linea.equals("")) {
				procedimiento = linea.split("-");
				procedimientos.add(new Procedimiento(procedimiento[0], Integer.parseInt(procedimiento[1]), Integer.parseInt(procedimiento[2]),
						Integer.parseInt(procedimiento[3]), Integer.parseInt(procedimiento[4]), Integer.parseInt(procedimiento[5]),
								Integer.parseInt(procedimiento[6]), Integer.parseInt(procedimiento[7]),
										Integer.parseInt(procedimiento[8]), Integer.parseInt(procedimiento[9])));
			}
			archivo.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			//jtAreaOutput.append(e.toString() + "\n");
			//jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument().getLength());
		}
	}
	
	// Obtiene el valor de la dirección que se proporciona y regresa el valor y su tipo de dato
	private String[] getValor (int direccionVar1, int[] offsets) {
		String[] valores = new String[2];
		if (direccionVar1 >= 0 && direccionVar1 < 5000) {
			if (direccionVar1 >= 0 && direccionVar1 < 1000) {
				valores[0] = "" + contEntGlo.get(direccionVar1).getValor();
				valores[1] = contEntGlo.get(direccionVar1).getDataType();
			}
			else if (direccionVar1 >= 1000 && direccionVar1 < 2000) {
				direccionVar1 -= 1000;
				valores[0] = "" + contFlotGlo.get(direccionVar1).getValor();
				valores[1] = contFlotGlo.get(direccionVar1).getDataType();
			}
			else if (direccionVar1 >= 2000 && direccionVar1 < 3000) {
				direccionVar1 -= 2000;
				valores[0] = "" + contStrGlo.get(direccionVar1).getValor();
				valores[1] = contStrGlo.get(direccionVar1).getDataType();
			}
			else if (direccionVar1 >= 3000 && direccionVar1 < 4000) {
				direccionVar1 -= 3000;
				valores[0] = "" + contBoolGlo.get(direccionVar1).getValor();
				valores[1] = contBoolGlo.get(direccionVar1).getDataType();
			}
			else {
				direccionVar1 -= 4000;
				//No se usa
			}
		}
		else if (direccionVar1 >= 5000 && direccionVar1 < 10000) {
			direccionVar1 -= 5000;
			if (direccionVar1 >= 0 && direccionVar1 < 1000) {
				valores[0] = "" + contEntLoc.get(direccionVar1+(enableOffsets?offsets[0]:0)).getValor();
				valores[1] = contEntLoc.get(direccionVar1+(enableOffsets?offsets[0]:0)).getDataType();
			}
			else if (direccionVar1 >= 1000 && direccionVar1 < 2000) {
				direccionVar1 -= 1000;
				valores[0] = "" + contFlotLoc.get(direccionVar1+(enableOffsets?offsets[1]:0)).getValor();
				valores[1] = contFlotLoc.get(direccionVar1+(enableOffsets?offsets[1]:0)).getDataType();
			}
			else if (direccionVar1 >= 2000 && direccionVar1 < 3000) {
				direccionVar1 -= 2000;
				valores[0] = "" + contStrLoc.get(direccionVar1+(enableOffsets?offsets[2]:0)).getValor();
				valores[1] = contStrLoc.get(direccionVar1+(enableOffsets?offsets[2]:0)).getDataType();
			}
			else if (direccionVar1 >= 3000 && direccionVar1 < 4000) {
				direccionVar1 -= 3000;
				valores[0] = "" + contBoolLoc.get(direccionVar1+(enableOffsets?offsets[3]:0)).getValor();
				valores[1] = contBoolLoc.get(direccionVar1+(enableOffsets?offsets[3]:0)).getDataType();
			}
			else {
				direccionVar1 -= 4000;
				//No se usa
			}
		}
		else if (direccionVar1 >= 10000 && direccionVar1 < 15000) {
			direccionVar1 -= 10000;
			if (direccionVar1 >= 0 && direccionVar1 < 1000) {
				valores[0] = "" + contEntTmp.get(direccionVar1+(enableOffsets?offsets[4]:0)).getValor();
				valores[1] = contEntTmp.get(direccionVar1+(enableOffsets?offsets[4]:0)).getDataType();
			}
			else if (direccionVar1 >= 1000 && direccionVar1 < 2000) {
				direccionVar1 -= 1000;
				valores[0] = "" + contFlotTmp.get(direccionVar1+(enableOffsets?offsets[5]:0)).getValor();
				valores[1] = contFlotTmp.get(direccionVar1+(enableOffsets?offsets[5]:0)).getDataType();
			}
			else if (direccionVar1 >= 2000 && direccionVar1 < 3000) {
				direccionVar1 -= 2000;
				valores[0] = "" + contStrTmp.get(direccionVar1+(enableOffsets?offsets[6]:0)).getValor();
				valores[1] = contStrTmp.get(direccionVar1+(enableOffsets?offsets[6]:0)).getDataType();
			}
			else if (direccionVar1 >= 3000 && direccionVar1 < 4000) {
				direccionVar1 -= 3000;
				valores[0] = "" + contBoolTmp.get(direccionVar1+(enableOffsets?offsets[7]:0)).getValor();
				valores[1] = contBoolTmp.get(direccionVar1+(enableOffsets?offsets[7]:0)).getDataType();
			}
			else {
				direccionVar1 -= 4000;
				//No se usa
			}
		}
		else {
			direccionVar1 -= 15000;
			if (direccionVar1 >= 0 && direccionVar1 < 1000) {
				valores[0] = "" + contEntCons.get(direccionVar1).getValor();
				valores[1] = contEntCons.get(direccionVar1).getDataType();
			}
			else if (direccionVar1 >= 1000 && direccionVar1 < 2000) {
				direccionVar1 -= 1000;
				valores[0] = "" + contFlotCons.get(direccionVar1).getValor();
				valores[1] = contFlotCons.get(direccionVar1).getDataType();
			}
			else if (direccionVar1 >= 2000 && direccionVar1 < 3000) {
				direccionVar1 -= 2000;
				valores[0] = "" + contStrCons.get(direccionVar1).getValor();
				valores[1] = contStrCons.get(direccionVar1).getDataType();
			}
			else if (direccionVar1 >= 3000 && direccionVar1 < 4000) {
				direccionVar1 -= 3000;
				valores[0] = "" + contBoolCons.get(direccionVar1).getValor();
				valores[1] = contBoolCons.get(direccionVar1).getDataType();
			}
			else {
				direccionVar1 -= 4000;
				//No se usa
			}
		}
		return valores;
	}
	
	// método para cargar las variables locales y temporales de una función
	private void cargaProcedimiento(String nombre) {
		int procACargar = 0;
		while (!procedimientos.get(procACargar).getNombre().equals(nombre))
			procACargar++;
		for (int i = 0; i < procedimientos.get(procACargar).getvariablesEnterasL(); i++)
			contEntLoc.add(new VariableInt());
		for (int i = 0; i < procedimientos.get(procACargar).getvariablesFlotantesL(); i++)
			contFlotLoc.add(new VariableFloat());
		for (int i = 0; i < procedimientos.get(procACargar).getvariablesStringL(); i++)
			contStrLoc.add(new VariableStr());
		for (int i = 0; i < procedimientos.get(procACargar).getvariablesBooleanasL(); i++)
			contBoolLoc.add(new VariableBool());
		for (int i = 0; i < procedimientos.get(procACargar).getvariablesEnterasT(); i++)
			contEntTmp.add(new VariableInt());
		for (int i = 0; i < procedimientos.get(procACargar).getvariablesFlotantesT(); i++)
			contFlotTmp.add(new VariableFloat());
		for (int i = 0; i < procedimientos.get(procACargar).getvariablesStringT(); i++)
			contStrTmp.add(new VariableStr());
		for (int i = 0; i < procedimientos.get(procACargar).getvariablesBooleanasT(); i++)
			contBoolTmp.add(new VariableBool());
	}
	
	// método para descargar las variables locales y temporales de una función
		private void descargaProcedimiento(String nombre) {
			int procACargar = 0;
			while (!procedimientos.get(procACargar).getNombre().equals(nombre))
				procACargar++;
			for (int i = 0; i < procedimientos.get(procACargar).getvariablesEnterasL(); i++) {
				contEntLoc.remove(contEntLoc.size()-1);
			}
			for (int i = 0; i < procedimientos.get(procACargar).getvariablesFlotantesL(); i++) {
				contFlotLoc.remove(contFlotLoc.size()-1);
			}
			for (int i = 0; i < procedimientos.get(procACargar).getvariablesStringL(); i++) {
				contStrLoc.remove(contStrLoc.size()-1);
			}
			for (int i = 0; i < procedimientos.get(procACargar).getvariablesBooleanasL(); i++) {
				contBoolLoc.remove(contBoolLoc.size()-1);
			}
			for (int i = 0; i < procedimientos.get(procACargar).getvariablesEnterasT(); i++) {
				contEntTmp.remove(contEntTmp.size()-1);
			}
			for (int i = 0; i < procedimientos.get(procACargar).getvariablesFlotantesT(); i++) {
				contFlotTmp.remove(contFlotTmp.size()-1);
			}
			for (int i = 0; i < procedimientos.get(procACargar).getvariablesStringT(); i++) {
				contStrTmp.remove(contStrTmp.size()-1);
			}
			for (int i = 0; i < procedimientos.get(procACargar).getvariablesBooleanasT(); i++) {
				contBoolTmp.remove(contBoolTmp.size()-1);
			}
		}
	
	// método para encontrar el cuádruplo inicial de la función
	private int getNumeroInstruccion (String nombre) {
		int procACargar = 0;
		while (!procedimientos.get(procACargar).getNombre().equals(nombre))
			procACargar++;
		return procedimientos.get(procACargar).getQuadruploInicio();
	}
	
	// se deshace de toda la memoria que quede en las variables cargadas y procedimientos
	private void clearAll() {
		contEntGlo.clear();
		contEntLoc.clear();
		contEntTmp.clear();
		contEntCons.clear();
		contFlotGlo.clear();
		contFlotLoc.clear();
		contFlotTmp.clear();
		contFlotCons.clear();
		contStrGlo.clear();
		contStrLoc.clear();
		contStrTmp.clear();
		contStrCons.clear();
		contBoolGlo.clear();
		contBoolLoc.clear();
		contBoolTmp.clear();
		contBoolCons.clear();
		quadruplos.clear();
		procedimientos.clear();
		apuntador = 0;
		for (int i = 0; i < apuntadoresInstruccion.length; i++)
			apuntadoresInstruccion[i] = 0;
		for (int i = 0; i < 8; i++)
			offsets[i] = 0;
	}
	
}