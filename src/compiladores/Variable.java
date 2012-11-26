package compiladores;

public class Variable {
	
	private String dataType;
	
	public Variable (String dataType) {
		this.dataType = dataType;
	}
	
	public String getDataType() { return dataType; }

}

class VariableInt extends Variable{
	
	private int valor;
	
	public VariableInt() {
		super ("int");
	}
	
	public VariableInt (String valor) {
		super("int");
		this.valor = Integer.parseInt(valor);
	}
	
	public int getValor() { return valor; }
	public void setValor(int valor) { this.valor = valor; }
	
}

class VariableFloat extends Variable{
	
	private float valor;
	
	public VariableFloat() {
		super ("float");
	}
	
	public VariableFloat (String valor) {
		super("float");
		this.valor = Float.parseFloat(valor);
	}
	
	public float getValor() { return valor; }
	public void setValor(float valor) { this.valor = valor; }
	
}

class VariableStr extends Variable{
	
	private String valor;
	
	public VariableStr() {
		super ("string");
	}
	
	public VariableStr (String valor) {
		super("string");
		this.valor = valor;
	}
	
	public String getValor() { return valor; }
	public void setValor(String valor) { this.valor = valor; }
	
}

class VariableBool extends Variable{
	
	private boolean valor;
	
	public VariableBool() {
		super ("boolean");
	}
	
	public VariableBool (String valor) {
		super("boolean");
		this.valor = Boolean.parseBoolean(valor);
	}
	
	public boolean getValor() { return valor; }
	public void setValor(boolean valor) { this.valor = valor; }
	
}