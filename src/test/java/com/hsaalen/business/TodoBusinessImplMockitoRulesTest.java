package com.hsaalen.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.hsaalen.business.FilmLogic;
import com.hsaalen.data.api.FilmService;

public class TodoBusinessImplMockitoRulesTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	FilmService todoService;

	@InjectMocks
	FilmLogic todoBusinessImpl;

	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;

	@Test
	public void usingMockito() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		when(todoService.getFilm("Ranga")).thenReturn(allTodos);

		List<String> todos = todoBusinessImpl
				.retrieveFilmRelatedToSpring("Ranga");
		assertEquals(2, todos.size());
	}

	@Test
	public void usingMockito_UsingBDD() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		//given
		given(todoService.getFilm("Ranga")).willReturn(allTodos);

		//when
		List<String> todos = todoBusinessImpl
				.retrieveFilmRelatedToSpring("Ranga");

		//then
		assertThat(todos.size(), is(2));
	}

	@Test
	public void letsTestDeleteNow() {

		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		when(todoService.getFilm("Ranga")).thenReturn(allTodos);

		todoBusinessImpl.deleteFilmsNotRelatedToSpring("Ranga");

		verify(todoService).deleteFilm("Learn to Dance");

		verify(todoService, Mockito.never()).deleteFilm("Learn Spring MVC");

		verify(todoService, Mockito.never()).deleteFilm("Learn Spring");

		verify(todoService, Mockito.times(1)).deleteFilm("Learn to Dance");
		// atLeastOnce, atLeast

	}

	@Test
	public void captureArgument() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		Mockito.when(todoService.getFilm("Ranga")).thenReturn(allTodos);

		todoBusinessImpl.deleteFilmsNotRelatedToSpring("Ranga");
		Mockito.verify(todoService).deleteFilm(stringArgumentCaptor.capture());

		assertEquals("Learn to Dance", stringArgumentCaptor.getValue());
	}
}
