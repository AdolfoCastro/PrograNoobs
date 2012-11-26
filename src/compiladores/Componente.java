package compiladores;

import java.awt.Polygon;

// Clase utilizada para los contenidos en los gráficos de programación
// Incluye datos como el tipo de gráfico que es, 
// el nombre que se le asignó para mostrar en el gráfico,
// y el contenido que tiene dentro.
public class Componente {
	
	private String tipo;
	private String nombre;
	private String contenido;
	// Las coordenadas de las lineas y los círculos
	private int x;
	private int x2;
	private int y1;
	private int y2;
	private Polygon p; // La figura que se dibuja
	private boolean desfazable; // Para decidir si se desplazan a la derecha o verticalmente
	private boolean por_desfazar; // Para decidir si se tiene que desfazar o no
	private boolean editable; // Para decidir si se pueden agregarle componentes ahí
	private int nivel; // Para checar si está dentro de una condicion/ciclo o no
	
	// Usado para los circulos de inicio y fin de programa
	// Usado para las lineas conectoras
	public Componente (String tipo, int x, int x2, int y1, int y2) {
		this.tipo = tipo;
		this.x = x;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		nivel = 0;
		desfazable = false;
		por_desfazar = false;
		editable = true;
	}
	
	// Usado para los componentes con contenido de Programación
	public Componente (String tipo, String nombre, String contenido, Polygon p) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.contenido = contenido;
		this.p = p;
		nivel = 0;
		desfazable = false;
		por_desfazar = false;
		editable = true;
	}
	
	// Gets
	public String getTipo () { return tipo; }
	public String getNombre () { return nombre; }
	public String getContenido () { return contenido; }
	public int getX () { return x; }
	public int getX2 () { return x2; }
	public int getY1 () { return y1; }
	public int getY2 () { return y2; }
	public Polygon getPolygon () { return p; }
	public boolean getDesfazable () { return desfazable; }
	public boolean getPorDesfazar () { return por_desfazar; }
	public int getNivel () { return nivel; }
	public boolean getEdtiable () { return editable; }

	//Sets
	public void setTipo (String tipo) { this.tipo = tipo; }
	public void setNombre (String nombre) { this.nombre = nombre; }
	public void setContenido (String contenido) { this.contenido = contenido; }
	public void setX (int x) { this.x = x; }
	public void setX2 (int x2) { this.x2 = x2; }
	public void setY1 (int y1) { this.y1 = y1; }
	public void setY2 (int y2) { this.y2 = y2; }
	public void setPolygon (Polygon p) { this.p = p; }
	public void setDesfazable (boolean desfazable) { this.desfazable = desfazable; }
	public void setPorDesfazar (boolean por_desfazar) { this.por_desfazar = por_desfazar; }
	public void setNivel (int nivel) { this.nivel = nivel; }
	public void setEditable (boolean editable) { this.editable = editable; }
	
}
