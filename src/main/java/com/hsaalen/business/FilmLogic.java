package com.hsaalen.business;

import java.util.ArrayList;
import java.util.List;

import com.hsaalen.data.api.FilmService;

public class FilmLogic {

	// Service Dependency wird in FilmLogic implementiert
	// FilmService Objekt wird erstellt
	private FilmService filmService;

	FilmLogic(FilmService filmService) {
		this.filmService = filmService;
	}

	// Alle Filme mit Batman im Namen von einem bestimmten Benutzer
	public List<String> retrieveFilmContainingBatman(String benutzer) {
		List<String> filteredFilm = new ArrayList<String>();
		List<String> allFilms = filmService.getFilm(benutzer);
		for (String film : allFilms) {
			if (film.contains("Batman")) {
				filteredFilm.add(film);
			}
		}
		return filteredFilm;
	}

	// Alle Filme die kein 'Batman' im Namen enthalten werden "gelöscht"
	public void deleteFilmsNotContainingBatman(String benutzer) {
		List<String> allFilms = filmService.getFilm(benutzer);
		for (String film : allFilms) {
			if (!film.contains("Batman")) {
				filmService.deleteFilm(film);
			}
		}
	}
}
