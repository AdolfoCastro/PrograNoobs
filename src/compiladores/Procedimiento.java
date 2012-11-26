package compiladores;

public class Procedimiento {
	
	private String nombre;
	private int quadruploInicio;
	private int variablesEnterasL;
	private int variablesFlotantesL;
	private int variablesStringL;
	private int variablesBooleanasL;
	private int variablesEnterasT;
	private int variablesFlotantesT;
	private int variablesStringT;
	private int variablesBooleanasT;
	
	public Procedimiento (String nombre, int quadruploInicio, int variablesEnterasL, int variablesFlotantesL,
			int variablesStringL, int variablesBooleanasL, int variablesEnterasT, int variablesFlotantesT,
			int variablesStringT, int variablesBooleanasT) {
		this.nombre = nombre;
		this.quadruploInicio = quadruploInicio;
		this.variablesEnterasL = variablesEnterasL;
		this.variablesFlotantesL = variablesFlotantesL;
		this.variablesStringL = variablesStringL;
		this.variablesBooleanasL = variablesBooleanasL;
		this.variablesEnterasT = variablesEnterasT;
		this.variablesFlotantesT = variablesFlotantesT;
		this.variablesStringT = variablesStringT;
		this.variablesBooleanasT = variablesBooleanasT;
	}
	
	public String getNombre () { return nombre; }
	public int getQuadruploInicio () { return quadruploInicio; }
	public int getvariablesEnterasL () { return variablesEnterasL; }
	public int getvariablesFlotantesL () { return variablesFlotantesL; }
	public int getvariablesStringL () { return variablesStringL; }
	public int getvariablesBooleanasL () { return variablesBooleanasL; }
	public int getvariablesEnterasT () { return variablesEnterasT; }
	public int getvariablesFlotantesT () { return variablesFlotantesT; }
	public int getvariablesStringT () { return variablesStringT; }
	public int getvariablesBooleanasT () { return variablesBooleanasT; }

}
