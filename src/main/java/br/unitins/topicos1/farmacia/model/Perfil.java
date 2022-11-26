package br.unitins.topicos1.farmacia.model;

public enum Perfil {
	CLIENTE(3, "Cliente"), 
	ADMINISTRADOR(1, "Administrador"), 
	CAIXA(2, "Caixa"), 
	VENDEDOR(4, "Vendedor");

	private int id;
	private String label;
	
	Perfil(int id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
		
	}
	
	public static Perfil valueOf(Integer id) {
		if (id == null)
			return null;
		for (Perfil perfil : Perfil.values()) 
			if (perfil.getId() == id) 
				return perfil;
		return null;
	}
	

}
