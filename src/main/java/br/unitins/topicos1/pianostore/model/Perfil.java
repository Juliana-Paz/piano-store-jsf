package br.unitins.topicos1.pianostore.model;

import java.util.List;

public enum Perfil {
	CLIENTE(2, "Cliente", List.of()),
	ADMINISTRADOR(1, "Administrador",
			List.of("/PianoStore/faces/admin/usuarios.xhtml", "/PianoStore/faces/admin/formusuario.xhtml",
					"/PianoStore/faces/admin/marcas.xhtml", "/PianoStore/faces/admin/formmarca.xhtml",
					"/PianoStore/faces/admin/subtipos.xhtml", "/PianoStore/faces/admin/formsubtipo.xhtml",
					"/PianoStore/faces/admin/instrumentos.xhtml", "/PianoStore/faces/admin/forminstrumento.xhtml"));

	private List<String> paginas;

	private int id;
	private String label;

	Perfil(int id, String label, List<String> paginas) {
		this.id = id;
		this.label = label;
		this.paginas = paginas;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;

	}

	public List<String> getPaginas() {
		return paginas;
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
