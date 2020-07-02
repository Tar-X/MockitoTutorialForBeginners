package com.hsaalen.data.stub;

import java.util.Arrays;
import java.util.List;

import com.hsaalen.data.api.FilmService;

// FilmService wird "stubbed", Liste mit Filmen zum Testen wird angelegt
public class FilmServiceStub implements FilmService {
	public List<String> getFilm(String benutzer) {
		return Arrays.asList("Batman Begins", "Batman & Robin", "Spiderman");
	}

	public void deleteFilm(String film) {
		// Keine Funktion, wird als Container für die gelöschten Filme benötigt
	}
}


