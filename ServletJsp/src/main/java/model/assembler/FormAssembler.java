package model.assembler;

import javax.servlet.http.HttpServletRequest;

import model.Form;

public class FormAssembler {

	public static Form assembleUserFrom(HttpServletRequest req) {
		Form form = new Form();
		// Recuperamos los valores del formulario
		String country = req.getParameter("country");
		String language1 = req.getParameter("language1");
		String language2 = req.getParameter("language2");
		
		// Guardamos pais
		form.setCountry(country);
		
		// Seleccionamos que idioma (combobox o manual) esta informado
		if (language1==null){
			form.setLanguage(language2);
		} else {
			form.setLanguage(language1);
		}
		
		return form;
	}
}
