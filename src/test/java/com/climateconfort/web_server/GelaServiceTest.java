package com.climateconfort.web_server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.climateconfort.web_server.domain.eraikina.model.Eraikina;
import com.climateconfort.web_server.domain.gela.dto.GelaDto;
import com.climateconfort.web_server.domain.gela.model.Gela;
import com.climateconfort.web_server.domain.gela.reporitory.GelaRepository;
import com.climateconfort.web_server.domain.gela.service.impl.GelaServiceImpl;

class GelaServiceTest {
    
    @Mock
    private GelaRepository mockGelaRespository;

    @InjectMocks
    private GelaServiceImpl mockGelaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);    
    }

    @Test
    void testFingGelaByGelaIDFound() {
        Gela gela = new Gela();
        gela.setGelaID(1L);
        gela.setIzena("Gela1");
        gela.setParamMax(100);
        gela.setParamMin(50);
        gela.setEraikina(new Eraikina());

        when(mockGelaRespository.findById(1L)).thenReturn(Optional.of(gela));

        GelaDto result = mockGelaService.fingGelaByGelaID(1L);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getGelaID());
        assertEquals("Gela1", result.getIzena());
        assertEquals(Double.valueOf(100), result.getParamMax());
        assertEquals(Double.valueOf(50), result.getParamMin());
        assertNotNull(result.getEraikina());

        verify(mockGelaRespository, times(1)).findById(1L);
    }

    @Test
    void testFingGelaByGelaIDNotFound() {
        when(mockGelaRespository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> mockGelaService.fingGelaByGelaID(1L));
        verify(mockGelaRespository, times(1)).findById(1L);
    }
}
