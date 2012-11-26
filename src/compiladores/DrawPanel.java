package compiladores;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import javax.swing.JPanel;

// Clase utilizada para dibujar los gráficos en el GUI
public class DrawPanel extends JPanel {
	
	/**
	 * Dato importado de la clase JPanel que se necesita declarar
	 */
	private static final long serialVersionUID = 1L;
	private static final int mitad = 450;
	private int [] desfazeEnY = new int[10];
	
	private ArrayList<Componente> componentes = new ArrayList<Componente>();
	
    public DrawPanel () {
        super();
        componentes.add(new Componente("oval", mitad-10, mitad+10, 10, 30));
        componentes.add(new Componente("linea", mitad, mitad, 30, 80));
        componentes.add(new Componente("linea", mitad-5, mitad, 75, 80));
        componentes.add(new Componente("linea", mitad+5, mitad, 75, 80));
        componentes.add(new Componente("oval", mitad-10, mitad+10, 80, 100));
        for (int i = 0; i < desfazeEnY.length; i++)
        	desfazeEnY[i] = 0;
    }
    
    public ArrayList<Componente> getComponentes() { return componentes; }
    public void setComponentes (ArrayList<Componente> componentes) { this.componentes = componentes; }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        repintar(g);
    }
    
    // Método que checa si dio click cerca o dentro de un componente de programación
    public Componente checarClick (int x, int y) {
    	int i = 0;
    	while (i < componentes.size())
    	{
    		if (componentes.get(i).getTipo().equals("oval")) {}
    		else if (componentes.get(i).getTipo().equals("linea"))
    		{
    			if (componentes.get(i).getX()-10 <= x && componentes.get(i).getX()+10 >= x)
    				if (componentes.get(i).getY1() <= y && componentes.get(i).getY2() >= y)
    					if (sePuedeAgregarAhi(i, componentes.get(i).getNivel()))
    						return componentes.get(i);
    		}
    		else {
    			if (componentes.get(i).getPolygon().contains(x, y))
    				return componentes.get(i);
;    		}
    		i++;
    	}
    	return null;
    }
    
    // Método para agregar componente
    public void agregaComponente (Componente componente, String tipo, String nombre, String contenido, Polygon p) {
    	int posicion = 0;
    	int nivel = componente.getNivel();
    	// Busca al componente que se eligió originalmente
    	while (!componentes.get(posicion).equals(componente) && posicion < componentes.size())
    		posicion++;
    	posicion = posicion + 3;
    	// Crea el nuevo componente después de la línea donde se dio el click
    	componentes.add(posicion, new Componente (tipo, nombre, contenido, p));
	    componentes.get(posicion).setNivel(nivel);
	    if (nivel > 0 && componente.getX() == mitad) {
	    	desfazaAlaDerecha(posicion, nivel);
	    }
	    if (componente.getX() != mitad) {
	    	componentes.get(posicion).setDesfazable(true);
	    	componentes.get(posicion).setPorDesfazar(componentes.get(posicion-1).getPorDesfazar());
	    	desfazarEnY(posicion, tipo, componentes.get(posicion).getNivel()-1);
	    }
	    int inicioY = maximo(p.ypoints);
	    posicion++;
	    // crea las lineas para el camino Verdadero
	    if (componentes.get(posicion-1).getTipo().equals("Condición") || componentes.get(posicion-1).getTipo().equals("Ciclo")) {
	    	agregaDesfazeALaDerecha(posicion, nivel, componentes.get(posicion-2).getX2());
	    	componentes.add(posicion, new Componente ("linea", componente.getX(), componente.getX(), maximo(p.ypoints), maximo(p.ypoints)+50));
	    	componentes.get(posicion).setNivel(nivel+1);
	    	posicion++;
	    	componentes.add(posicion, new Componente("linea", componente.getX()-5, componente.getX(), maximo(p.ypoints)+45, maximo(p.ypoints)+50));
	    	componentes.get(posicion).setNivel(nivel+1);
	    	posicion++;
	    	componentes.add(posicion, new Componente("linea", componente.getX()+5, componente.getX(), maximo(p.ypoints)+45, maximo(p.ypoints)+50));
	    	componentes.get(posicion).setNivel(nivel+1);
	    	posicion++;
	    	inicioY += 50;
	    	// Crea las lineas para el camino de Falso
	    	if (tipo.equals("Condición")) {
	    		componentes.add(posicion, new Componente ("linea", componente.getX()+100, componente.getX()+100, maximo(p.ypoints), maximo(p.ypoints)+40));
	    		componentes.get(posicion).setDesfazable(true);
	    		componentes.get(posicion).setPorDesfazar(true);
	    		componentes.get(posicion).setNivel(nivel+1);
	        	posicion++;
	        	componentes.add(posicion, new Componente("linea", componente.getX(), componente.getX()+100, maximo(p.ypoints), maximo(p.ypoints)));
	        	componentes.get(posicion).setDesfazable(true);
	    		componentes.get(posicion).setPorDesfazar(true);
	    		componentes.get(posicion).setNivel(nivel+1);
	        	posicion++;
	        	componentes.add(posicion, new Componente("linea", componente.getX(), componente.getX()+100, maximo(p.ypoints)+40, maximo(p.ypoints)+40));
	        	componentes.get(posicion).setDesfazable(true);
	    		componentes.get(posicion).setPorDesfazar(true);
	    		componentes.get(posicion).setNivel(nivel+1);
	        	posicion++;
	        	componentes.add(posicion, new Componente("linea", componente.getX(), componente.getX()+5, maximo(p.ypoints)+40, maximo(p.ypoints)+35));
	        	componentes.get(posicion).setDesfazable(true);
	    		componentes.get(posicion).setPorDesfazar(true);
	    		componentes.get(posicion).setNivel(nivel+1);
	        	posicion++;
	        	componentes.add(posicion, new Componente("linea", componente.getX(), componente.getX()+5, maximo(p.ypoints)+40, maximo(p.ypoints)+45));
	        	componentes.get(posicion).setDesfazable(true);
	    		componentes.get(posicion).setPorDesfazar(true);
	    		componentes.get(posicion).setNivel(nivel+1);
	        	posicion++;
	    	}
	    	// Crea las lineas para el retorno
	    	else {
	    		componentes.add(posicion, new Componente ("linea", componente.getX()+100, componente.getX()+100, maximo(p.ypoints), maximo(p.ypoints)+40));
	    		componentes.get(posicion).setDesfazable(true);
	    		componentes.get(posicion).setPorDesfazar(true);
	    		componentes.get(posicion).setNivel(nivel+1);
	        	posicion++;
	        	componentes.add(posicion, new Componente("linea", componente.getX(), componente.getX()+100, maximo(p.ypoints)+40, maximo(p.ypoints)+40));
	        	componentes.get(posicion).setDesfazable(true);
	    		componentes.get(posicion).setPorDesfazar(true);
	    		componentes.get(posicion).setNivel(nivel+1);
	        	posicion++;
	        	componentes.add(posicion, new Componente("linea", componente.getX()+50, componente.getX()+100, maximo(p.ypoints), maximo(p.ypoints)));
	        	componentes.get(posicion).setDesfazable(true);
	    		componentes.get(posicion).setPorDesfazar(true);
	    		componentes.get(posicion).setNivel(nivel+1);
	        	posicion++;
	        	componentes.add(posicion, new Componente("linea", componente.getX()+50, componente.getX()+55, maximo(p.ypoints), maximo(p.ypoints)-5));
	        	componentes.get(posicion).setDesfazable(true);
	    		componentes.get(posicion).setPorDesfazar(true);
	    		componentes.get(posicion).setNivel(nivel+1);
	        	posicion++;
	        	componentes.add(posicion, new Componente("linea", componente.getX()+50, componente.getX()+55, maximo(p.ypoints), maximo(p.ypoints)+5));
	        	componentes.get(posicion).setDesfazable(true);
	    		componentes.get(posicion).setPorDesfazar(true);
	    		componentes.get(posicion).setNivel(nivel+1);
	        	posicion++;
	    	}
    	}
    	componentes.add(posicion, new Componente ("linea", componente.getX(), componente.getX(), inicioY, inicioY+50));
    	componentes.get(posicion).setNivel(nivel);
    	if (componente.getX() != mitad) {
	    	componentes.get(posicion).setDesfazable(true);
	    	componentes.get(posicion).setPorDesfazar(componentes.get(posicion-1).getPorDesfazar());
	    }
    	posicion++;
    	componentes.add(posicion, new Componente("linea", componente.getX()-5, componente.getX(), inicioY+45, inicioY+50));
    	componentes.get(posicion).setNivel(nivel);
    	if (componente.getX() != mitad) {
	    	componentes.get(posicion).setDesfazable(true);
	    	componentes.get(posicion).setPorDesfazar(componentes.get(posicion-1).getPorDesfazar());
	    }
    	posicion++;
    	componentes.add(posicion, new Componente("linea", componente.getX()+5, componente.getX(), inicioY+45, inicioY+50));
    	componentes.get(posicion).setNivel(nivel);
    	if (componente.getX() != mitad) {
	    	componentes.get(posicion).setDesfazable(true);
	    	componentes.get(posicion).setPorDesfazar(componentes.get(posicion-1).getPorDesfazar());
	    }
    	posicion++;
    	int diferencia = inicioY-componente.getY2()+50;
    	// Actualiza las posiciones de los demás elementos
    	while (posicion < componentes.size()) {
    		if (componentes.get(posicion).getTipo().equals("linea") || componentes.get(posicion).getTipo().equals("oval")) {
    			if (componentes.get(posicion).getY1() >= componente.getY2()-(componente.getY2()-componente.getY1())/2)
    				componentes.get(posicion).setY1(componentes.get(posicion).getY1()+diferencia);
    			if (componentes.get(posicion).getY1() != componentes.get(posicion).getY2())
    				componentes.get(posicion).setY2(componentes.get(posicion).getY2()+diferencia);
    			if (componentes.get(posicion).getY2()-componentes.get(posicion).getY1() > 5 && Math.abs(componentes.get(posicion).getX2()-componentes.get(posicion).getX()) == 5)
    				componentes.get(posicion).setY2(componentes.get(posicion).getY2()-diferencia);
    		}
    		else {
    			componentes.get(posicion).getPolygon().translate(0, diferencia);
    		}
    		posicion++;
    	}
    }
    
    // Método para borrar un componente junto con sus subcomponentes
    public void borraComponente (Componente componente) {
    	int posicion = 0;
    	while (!componentes.get(posicion).equals(componente) && posicion < componentes.size())
    		posicion++;
    	int nivel = componentes.get(posicion).getNivel();
    	int yInicio = minimo(componentes.get(posicion).getPolygon().ypoints);
    	int cont = 0;
    	do {
    		componentes.remove(posicion);
    		cont++;
    	} while (componentes.get(posicion).getNivel() > nivel || cont < 4);
    	while (posicion < componentes.size()) {
    		int y1;
    		if (componentes.get(posicion).getTipo().equals("linea") || componentes.get(posicion).getTipo().equals("oval")) {
    			if (componentes.get(posicion).getX() != componentes.get(posicion).getX2() && !componentes.get(posicion).getTipo().equals("oval")) {
    				y1 = componentes.get(posicion).getY1();
		    		componentes.get(posicion).setY1(yInicio+y1-componentes.get(posicion).getY2());
		    		componentes.get(posicion).setY2(yInicio);
    			}
    			else {
		    		y1 = componentes.get(posicion).getY1();
		    		componentes.get(posicion).setY1(yInicio);
		    		componentes.get(posicion).setY2(yInicio+componentes.get(posicion).getY2()-y1);
		    		yInicio = componentes.get(posicion).getY2();
    			}
    		}
    		else {
    			y1 = minimo(componentes.get(posicion).getPolygon().ypoints);
    			componentes.get(posicion).getPolygon().translate(0, yInicio-y1);
    			yInicio = maximo(componentes.get(posicion).getPolygon().ypoints);
    		}
    		posicion++;
    	}
    }
    
    // Método para actualizar al componente
    public void actualizaComponente (Componente componente, String nombre, String contenido) {
    	int posicion = 0;
    	while (!componentes.get(posicion).equals(componente) && posicion < componentes.size())
    		posicion++;
    	componentes.get(posicion).setNombre(nombre);
    	componentes.get(posicion).setContenido(contenido);
    }
    
    // Método para volver a pintar
    public void repintar (Graphics g) {
    	for (int i = 0; i < componentes.size(); i++) {
    		Componente componente = componentes.get(i);
    		if (componente.getTipo().equals("oval")) {
    			if (i == componentes.size()-1)
    				g.fillOval(componente.getX(), componente.getY1(), componente.getX2()-componente.getX(), componente.getY2()-componente.getY1());
    			else {
    				g.setColor(Color.WHITE);
    				g.fillOval(componente.getX(), componente.getY1(), componente.getX2()-componente.getX(), componente.getY2()-componente.getY1());
    				g.setColor(Color.BLACK);
    			}
    			g.drawOval(componente.getX(), componente.getY1(), componente.getX2()-componente.getX(), componente.getY2()-componente.getY1());
    		}
    		else if (componente.getTipo().equals("linea")) {
    			g.drawLine(componente.getX(), componente.getY1(), componente.getX2(), componente.getY2());
    		}
    		else {
    			g.setColor(Color.WHITE);
    			g.fillPolygon(componente.getPolygon());
    			g.setColor(Color.BLACK);
    			g.drawPolygon(componente.getPolygon());
    			g.drawString(componente.getNombre(), maximo(componente.getPolygon().xpoints)+5, minimo(componente.getPolygon().ypoints)+(maximo(componente.getPolygon().ypoints)-minimo(componente.getPolygon().ypoints))/2);
    		}
    	}
    }
    
    // Calcula el máximo del arreglo
    private int maximo (int [] arr) {
    	int max = arr[0];
    	for (int i = 1; i < arr.length; i++)
    		if (arr[i] > max)
    			max = arr[i];
    	return max;
    }
    
 // Calcula el mínimo del arreglo
    private int minimo (int [] arr) {
    	int min = arr[0];
    	for (int i = 1; i < arr.length; i++)
    		if (arr[i] < min)
    			min = arr[i];
    	return min;
    }
    
    // Desfaza a la derecha aquellos componentes dentro del Ciclo o Condición
    private void desfazaAlaDerecha (int posicion, int nivel) {
    	while (nivel > 0 && posicion < componentes.size()) {
    		nivel = componentes.get(posicion).getNivel();
    		if (componentes.get(posicion).getPorDesfazar()) {
    			if (componentes.get(posicion).getTipo().equals("linea")) {
    				if (Math.abs(componentes.get(posicion).getX2()-componentes.get(posicion).getX()) != 5) {
    					if (componentes.get(posicion).getX2()-componentes.get(posicion).getX() == 0)
    						componentes.get(posicion).setX(componentes.get(posicion).getX()+50);
    					componentes.get(posicion).setX2(componentes.get(posicion).getX2()+50);
    				}
    			}
    			else {
    				componentes.get(posicion).getPolygon().translate(50, 0);
    			}
    			componentes.get(posicion).setPorDesfazar(false);
    		}
    		posicion++;
    	}
    }
    
    // Agrega los desfazes hacia la derecha para los que lo ocupan
    private void agregaDesfazeALaDerecha (int posicion, int nivel, int posX) {
    	while (nivel > 0 && posicion < componentes.size()) {
    		nivel = componentes.get(posicion).getNivel();
    		if (componentes.get(posicion).getX() != posX || componentes.get(posicion).getX2() != posX)
    			componentes.get(posicion).setPorDesfazar(true);
    		posicion++;
    	}
    }
    
    // Para los caminos alternos en ciclos/condiciones, checa si es tipo ciclo y regresa false
    // True en caso contrario
    private boolean sePuedeAgregarAhi (int posicion, int nivel) {
    	int posicionOriginal = posicion;
    	while (nivel > 0 && nivel <= componentes.get(posicion).getNivel())
    		posicion--;
    	if (componentes.get(posicion).getTipo().equals("Ciclo") && (posicionOriginal+6 > componentes.size() || componentes.get(posicionOriginal+5).getNivel() < nivel))
    		return false;
    	return true;
    }
    
    // Desfaza en Y en caso de que se agrega un componente en el camino de false
    private void desfazarEnY (int posicion, String tipo, int nivel) {
    	int extra = 0;
    	if (tipo.equals("Ciclo") || tipo.equals("Condición"))
    		extra += 50;
    	for (int i = nivel; i >= 0; i--) {
    		int posicionTemp = posicion-1;
	    	componentes.get(posicionTemp-desfazeEnY[nivel]).setY1(componentes.get(posicionTemp-desfazeEnY[nivel]).getY1()+100+extra);
	    	componentes.get(posicionTemp-desfazeEnY[nivel]).setY2(componentes.get(posicionTemp-desfazeEnY[nivel]).getY2()+100+extra);
	    	componentes.get(posicionTemp-3-desfazeEnY[nivel]).setY1(componentes.get(posicionTemp-3-desfazeEnY[nivel]).getY1()+100+extra);
	    	componentes.get(posicionTemp-3-desfazeEnY[nivel]).setY2(componentes.get(posicionTemp-3-desfazeEnY[nivel]).getY2()+100+extra);
	    	componentes.get(posicionTemp-4-desfazeEnY[nivel]).setY1(componentes.get(posicionTemp-4-desfazeEnY[nivel]).getY1()+100+extra);
	    	componentes.get(posicionTemp-4-desfazeEnY[nivel]).setY2(componentes.get(posicionTemp-4-desfazeEnY[nivel]).getY2()+100+extra);
	    	componentes.get(posicionTemp-5-desfazeEnY[nivel]).setY2(componentes.get(posicionTemp-5-desfazeEnY[nivel]).getY2()+100+extra);
	    	if (tipo.equals("Ciclo"))
	    		desfazeEnY[nivel] = desfazeEnY[nivel]+12;
	    	else if (tipo.equals("Condición"))
	    		desfazeEnY[nivel] = desfazeEnY[nivel]+12;
	    	else
	    		desfazeEnY[nivel] = desfazeEnY[nivel]+4;
    	}
    }
    
}
