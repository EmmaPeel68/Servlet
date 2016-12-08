package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Form;
import model.Language;
import model.assembler.FormAssembler;
import repository.RepositoryCountry;
import repository.RepositoryLanguage;

public class Service {
	private RepositoryCountry repositoryCountry = new RepositoryCountry();
	private RepositoryLanguage repositoryLanguage = new RepositoryLanguage();
	
	public Form assembleFormFromRequest(HttpServletRequest req) {
		return FormAssembler.assembleUserFrom(req);
	}
	
	public void insert(Form form) {
		repositoryLanguage.insert(form);
		repositoryCountry.insert(form, repositoryLanguage.findLanguageId(form.getLanguage()));
	}
	
	public void delete(String language) {
		int i = repositoryLanguage.findLanguageId(language);
		repositoryLanguage.deleteLanguage(i);
		repositoryCountry.deleteCountry(i);
	}
	
	public List<Form> listAllForms() {
		return repositoryCountry.searchAll();
	}
	
	public List<Language> listAllLang() {
		return repositoryLanguage.searchLanguage();
	}
	
}
