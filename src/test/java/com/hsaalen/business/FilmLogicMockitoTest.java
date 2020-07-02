package com.hsaalen.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.hsaalen.data.api.FilmService;

public class FilmLogicMockitoTest {

	// Identische Logik wie bei Stub. filmService bleibt aber unberührt!!
	@Test
	public void usingMockito() {
		FilmService filmService = mock(FilmService.class);
		List<String> allFilms = Arrays.asList("Batman Begins", "Batman & Robin", "Spiderman");
		when(filmService.getFilm("Andy")).thenReturn(allFilms);

		// Implementierung des gemockten Interfaces
		FilmLogic filmLogic = new FilmLogic(filmService);
		List<String> filmList = filmLogic.retrieveFilmContainingBatman("Andy");
		assertEquals(2, filmList.size());
	}

	// "Behavior-driven development": einfacher lesbarer Code
	@Test
	public void usingMockito_UsingBDD() {

		// given: Kontext für den Test wird erstellt
		FilmService filmService = mock(FilmService.class);
		FilmLogic filmLogic = new FilmLogic(filmService);
		List<String> allFilms = Arrays.asList("Batman Begins", "Batman & Robin", "Spiderman");
		given(filmService.getFilm("Andy")).willReturn(allFilms);

		// when: Eine Methode wird ausgeführt
		List<String> filmList = filmLogic.retrieveFilmContainingBatman("Andy");

		// then: Es wird geprüft ob die Erwartung erfüllt wurde
		assertThat(filmList.size(), is(3));
	}

	//
	@Test
	public void testingDelete() {

		FilmService filmService = mock(FilmService.class);

		List<String> allFilms = Arrays.asList("Batman Begins", "Batman & Robin", "Spiderman");

		when(filmService.getFilm("Andy")).thenReturn(allFilms);

		FilmLogic filmLogic = new FilmLogic(filmService);

		filmLogic.deleteFilmsNotContainingBatman("Andy");

		verify(filmService).deleteFilm("Spiderman");

		verify(filmService, never()).deleteFilm("Batman Begins");

		verify(filmService, never()).deleteFilm("Batman & Robin");

		verify(filmService, times(1)).deleteFilm("Spiderman");

		verify(filmService, atLeast(1)).deleteFilm("Spiderman");

		verify(filmService, atLeastOnce()).deleteFilm("Spiderman");

	}

	@Test
	public void captureArgument() {
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

		FilmService filmService = mock(FilmService.class);

		List<String> allFilms = Arrays.asList("Batman Begins", "Batman & Robin", "Spiderman");
		when(filmService.getFilm("Andy")).thenReturn(allFilms);

		FilmLogic filmLogic = new FilmLogic(filmService);
		filmLogic.deleteFilmsNotContainingBatman("Andy");
		verify(filmService).deleteFilm(argumentCaptor.capture());

		assertEquals("Batman Begins", argumentCaptor.getValue());
	}
}
