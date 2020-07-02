package com.hsaalen.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.hsaalen.data.api.FilmService;

@RunWith(MockitoJUnitRunner.class)
public class FilmLogicMockitoInjectMocksTest {
	@Mock
	FilmService filmService;

	@InjectMocks
	FilmLogic filmLogic;

	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;

	@Test
	public void usingMockito() {
		List<String> allFilms = Arrays.asList("Batman Begins", "Batman & Robin", "Spiderman");

		when(filmService.getFilm("Andy")).thenReturn(allFilms);

		List<String> filmList = filmLogic.retrieveFilmContainingBatman("Andy");
		assertEquals(2, filmList.size());
	}

	@Test
	public void usingMockito_UsingBDD() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

		// given
		given(filmService.getFilm("Ranga")).willReturn(allTodos);

		// when
		List<String> todos = filmLogic.retrieveFilmContainingBatman("Andy");

		// then
		assertThat(todos.size(), is(2));
	}

	@Test
	public void letsTestDeleteNow() {

		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

		when(filmService.getFilm("Ranga")).thenReturn(allTodos);

		filmLogic.deleteFilmsNotContainingBatman("Ranga");

		verify(filmService).deleteFilm("Learn to Dance");

		verify(filmService, Mockito.never()).deleteFilm("Learn Spring MVC");

		verify(filmService, Mockito.never()).deleteFilm("Learn Spring");

		verify(filmService, Mockito.times(1)).deleteFilm("Learn to Dance");
		// atLeastOnce, atLeast

	}

	@Test
	public void captureArgument() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		Mockito.when(filmService.getFilm("Ranga")).thenReturn(allTodos);

		filmLogic.deleteFilmsNotContainingBatman("Ranga");
		Mockito.verify(filmService).deleteFilm(stringArgumentCaptor.capture());

		assertEquals("Learn to Dance", stringArgumentCaptor.getValue());
	}
}
