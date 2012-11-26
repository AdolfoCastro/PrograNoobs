package compiladores;

public class Quadruplo {
	
	private String comando; //primer componente del quádruplo
	private String variable1; //segundo componente del quádruplo
	private String variable2; //tercer componente del quádruplo
	private String destino; //cuarto componente del quádruplo
	
	public Quadruplo (String comando, String variable1, String variable2, String destino) {
		this.comando = comando;
		this.variable1 = variable1;
		this.variable2 = variable2;
		this.destino = destino;
	}
	
	public String getComando () { return comando; }
	public String getVariable1 () { return variable1; }
	public String getVariable2 () { return variable2; }
	public String getDestino () { return destino; }
	public String toString () { return comando+","+variable1+","+variable2+","+destino; }

}
