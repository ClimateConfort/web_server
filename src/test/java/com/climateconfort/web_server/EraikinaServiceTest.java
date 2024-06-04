package com.climateconfort.web_server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.climateconfort.web_server.domain.enpresa.model.Enpresa;
import com.climateconfort.web_server.domain.eraikina.dto.EraikinaDto;
import com.climateconfort.web_server.domain.eraikina.model.Eraikina;
import com.climateconfort.web_server.domain.eraikina.reporitory.EraikinaRepository;
import com.climateconfort.web_server.domain.eraikina.service.impl.EraikinaServiceImpl;
import com.climateconfort.web_server.domain.gela.dto.GelaDto;
import com.climateconfort.web_server.domain.gela.model.Gela;

class EraikinaServiceTest {

    @Mock
    private EraikinaRepository mockEraikinaRespository;

    @InjectMocks
    private EraikinaServiceImpl mockEraikinaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);    
    }

    @Test
    void testFindGelakByEraikinaIDFound() {
        Gela gela1 = new Gela();
        gela1.setGelaID(1L);
        gela1.setIzena("Gela1");
        gela1.setParamMax(100);
        gela1.setParamMin(50);

        Gela gela2 = new Gela();
        gela2.setGelaID(2L);
        gela2.setIzena("Gela2");
        gela2.setParamMax(200);
        gela2.setParamMin(100);

        Eraikina eraikina = new Eraikina();
        eraikina.setEraikinaID(1L);
        eraikina.setGelak(Arrays.asList(gela1, gela2));

        when(mockEraikinaRespository.findById(1L)).thenReturn(Optional.of(eraikina));

        Optional<List<GelaDto>> result = mockEraikinaService.findGelakByEraikinaID(1L);

        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());
        assertEquals(Long.valueOf(1L), result.get().get(0).getGelaID());
        assertEquals("Gela1", result.get().get(0).getIzena());
        assertEquals(Double.valueOf(100), result.get().get(0).getParamMax());
        assertEquals(Double.valueOf(50), result.get().get(0).getParamMin());
        assertEquals(Long.valueOf(2L), result.get().get(1).getGelaID());
        assertEquals("Gela2", result.get().get(1).getIzena());
        assertEquals(Double.valueOf(200), result.get().get(1).getParamMax());
        assertEquals(Double.valueOf(100), result.get().get(1).getParamMin());

        verify(mockEraikinaRespository, times(1)).findById(1L);
    }

    @Test
    void testFindGelakByEraikinaIDNotFound() {
        when(mockEraikinaRespository.findById(1L)).thenReturn(Optional.empty());

        Optional<List<GelaDto>> result = mockEraikinaService.findGelakByEraikinaID(1L);

        assertFalse(result.isPresent());

        verify(mockEraikinaRespository, times(1)).findById(1L);
    }

    @Test
    void testSaveEraikina() {
        EraikinaDto eraikinaDto = new EraikinaDto();
        eraikinaDto.setIzena("Eraikina1");
        eraikinaDto.setLokalizazioa("Lokalizazioa1");
        eraikinaDto.setEnpresa(new Enpresa());

        mockEraikinaService.saveEraikina(eraikinaDto);

        Eraikina expectedEraikina = new Eraikina();
        expectedEraikina.setIzena("Eraikina1");
        expectedEraikina.setLokalizazioa("Lokalizazioa1");
        expectedEraikina.setEnpresa(eraikinaDto.getEnpresa());

        verify(mockEraikinaRespository, times(1)).save(argThat(eraikina -> eraikina.getIzena().equals("Eraikina1") &&
                eraikina.getLokalizazioa().equals("Lokalizazioa1") &&
                eraikina.getEnpresa().equals(eraikinaDto.getEnpresa())));
    }

    @Test
    void testFindEraikinaByIzena() {
        Eraikina eraikina = new Eraikina();
        eraikina.setIzena("Eraikina1");

        when(mockEraikinaRespository.findByizena("Eraikina1")).thenReturn(Optional.of(eraikina));

        Eraikina result = mockEraikinaService.findEraikinaByIzena("Eraikina1");

        assertNotNull(result);
        assertEquals("Eraikina1", result.getIzena());

        verify(mockEraikinaRespository, times(1)).findByizena("Eraikina1");
    }

}
