package inteligencia;

import java.util.ArrayList;
import java.util.List;

public class Nodo {

	private Estado estado;
	private List<Nodo> hijos;
	private Nodo parent;
	
	public Nodo(Nodo parent, Estado estado) {
		this.parent = parent;
		this.estado = estado;
		this.hijos = new ArrayList<Nodo>();		
	}
	
	
	public void addHijo(Estado value) {
		Nodo hijo = new Nodo(this, value);		
		hijos.add(hijo);		
	}

	public int getValor() {
		return estado.getValor();
	}
	
	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public Nodo getHijo(int hijo){
		if(isTerminal()) return null;		
		return hijos.get(hijo);
	}
	
	public List<Nodo> getHijos() {
		return hijos;
	}


	public void setHijos(List<Nodo> hijos) {
		this.hijos = hijos;
	}


	public Nodo getParent() {
		return parent;
	}


	public void setParent(Nodo parent) {
		this.parent = parent;
	}
	
	
	public boolean isTerminal() {
		return hijos == null || hijos.isEmpty();
	}
	
	
}
