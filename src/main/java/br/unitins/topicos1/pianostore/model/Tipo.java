package br.unitins.topicos1.pianostore.model;

public enum Tipo {
	PIANO(1, "Piano"), TECLADO(2, "Teclado");

	private int id;
	private String label;

	Tipo(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;

	}

	public static Tipo valueOf(Integer id) {
		if (id == null)
			return null;
		for (Tipo tipo : Tipo.values())
			if (tipo.getId() == id)
				return tipo;
		return null;
	}

}
