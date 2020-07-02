package com.hsaalen.business;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.hsaalen.data.api.FilmService;
import com.hsaalen.data.stub.FilmServiceStub;

public class FilmLogicTest {

	@Test
	public void usingAStub() {
		FilmService filmService = new FilmServiceStub();
		FilmLogic filmLogic = new FilmLogic(filmService);
		List<String> filmList = filmLogic.retrieveFilmContainingBatman("Andy");
		assertEquals(3, filmList.size());
	}
}


