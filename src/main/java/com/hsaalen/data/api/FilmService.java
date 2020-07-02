package com.hsaalen.data.api;

import java.util.List;

// Externer Service, z.B. Datenbank mit Filmen
// Jeder Benutzer hat Lieblingsfilme
public interface FilmService {

	public List<String> getFilm(String benutzer);

	void deleteFilm(String film);
	// Keine Funktion, wird als Container für die gelöschten Filme benötigt
}

