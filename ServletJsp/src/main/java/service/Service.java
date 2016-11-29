package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Form;
import model.Language;
import model.assembler.FormAssembler;
import repository.Repository;

public class Service {
	private Repository repository = new Repository();
	
	public Form assembleFormFromRequest(HttpServletRequest req) {
		return FormAssembler.assembleUserFrom(req);
	}
	
	public void insert(Form formulary) {
		repository.insert(formulary);
	}
	
	public void delete(int idCountry) {
		repository.delete(idCountry);
	}
	
	public List<Form> listAllForms() {
		return repository.searchAll();
	}
	
	public List<Language> listAllLang() {
		return repository.searchLanguage();
	}
	
}

