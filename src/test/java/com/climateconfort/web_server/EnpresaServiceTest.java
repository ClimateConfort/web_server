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

import com.climateconfort.web_server.domain.enpresa.dto.EnpresaDto;
import com.climateconfort.web_server.domain.enpresa.model.Enpresa;
import com.climateconfort.web_server.domain.enpresa.repository.EnpresaRespository;
import com.climateconfort.web_server.domain.enpresa.service.impl.EnpresaServiceImpl;
import com.climateconfort.web_server.domain.eraikina.dto.EraikinaDto;
import com.climateconfort.web_server.domain.user.model.User;
import com.climateconfort.web_server.domain.user.repository.UserRepository;

class EnpresaServiceTest {
 
    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private EnpresaRespository mockEnpresaRespository;

    @InjectMocks
    private EnpresaServiceImpl enpresaService;

    @BeforeEach
    void setIp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllEnpresas() {
        Enpresa enpresa1 = new Enpresa();
        enpresa1.setIzena("Empresa1");
        enpresa1.setEnpresaID(1L);
        enpresa1.setUser(new User());

        Enpresa enpresa2 = new Enpresa();
        enpresa2.setIzena("Empresa2");
        enpresa2.setEnpresaID(2L);
        enpresa2.setUser(new User());

        List<Enpresa> enpresaList = Arrays.asList(enpresa1, enpresa2);

        when(mockEnpresaRespository.findAll()).thenReturn(enpresaList);

        List<EnpresaDto> result = enpresaService.findAllEnpresas();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Empresa1", result.get(0).getIzena());
        assertEquals(Long.valueOf(1L), result.get(0).getEnpresaID());
        assertEquals("Empresa2", result.get(1).getIzena());
        assertEquals(Long.valueOf(2L), result.get(1).getEnpresaID());

        verify(mockEnpresaRespository, times(1)).findAll();
    }

    @Test
    void testFindEnpresaDtoByNameFound() {
        Enpresa enpresa = new Enpresa();
        enpresa.setIzena("Empresa1");
        enpresa.setEnpresaID(1L);
        enpresa.setUser(new User());

        when(mockEnpresaRespository.findByIzena("Empresa1")).thenReturn(Optional.of(enpresa));

        Optional<EnpresaDto> result = enpresaService.findEnpresaDtoByName("Empresa1");

        assertTrue(result.isPresent());
        assertEquals("Empresa1", result.get().getIzena());
        assertEquals(Long.valueOf(1L), result.get().getEnpresaID());

        verify(mockEnpresaRespository, times(1)).findByIzena("Empresa1");
    }

    @Test
    void testFindEnpresaDtoByNameNotFound() {
        when(mockEnpresaRespository.findByIzena("Empresa1")).thenReturn(Optional.empty());

        Optional<EnpresaDto> result = enpresaService.findEnpresaDtoByName("Empresa1");

        assertFalse(result.isPresent());
        verify(mockEnpresaRespository, times(1)).findByIzena("Empresa1");
    }

    @Test
    void testSaveEnpresa() {
        EnpresaDto enpresaDto = new EnpresaDto();
        enpresaDto.setIzena("Empresa1");
        enpresaDto.setUser(new User());

        enpresaService.saveEnpresa(enpresaDto);

        Enpresa expectedEnpresa = new Enpresa();
        expectedEnpresa.setIzena("Empresa1");
        expectedEnpresa.setUser(enpresaDto.getUser());

        verify(mockEnpresaRespository, times(1)).save(argThat(enpresa -> 
            enpresa.getIzena().equals("Empresa1") && enpresa.getUser().equals(enpresaDto.getUser())
        ));
    }

    @Test
    void testFindEraikinakByEnpresaIDNotFound() {
        when(mockEnpresaRespository.findById(1L)).thenReturn(Optional.empty());
        Optional<List<EraikinaDto>> result = enpresaService.findEraikinakByEnpresaID(1L);
        assertFalse(result.isPresent());
        verify(mockEnpresaRespository, times(1)).findById(1L);
    }

    @Test
    void testDeleteEnpresa() {
        Enpresa enpresa = new Enpresa();
        enpresa.setEnpresaID(1L);

        when(mockEnpresaRespository.findById(1L)).thenReturn(Optional.of(enpresa));

        enpresaService.deleteEnpresa(1L);
        verify(mockEnpresaRespository, times(1)).delete(enpresa);
    }

    @Test
    void testFindEnpresaByIzena() {
        Enpresa enpresa = new Enpresa();
        enpresa.setIzena("Empresa1");

        when(mockEnpresaRespository.findByIzena("Empresa1")).thenReturn(Optional.of(enpresa));

        Enpresa result = enpresaService.findEnpresaByIzena("Empresa1");

        assertNotNull(result);
        assertEquals("Empresa1", result.getIzena());
        verify(mockEnpresaRespository, times(1)).findByIzena("Empresa1");
    }

    @Test
    void testFindEnpresaByID() {
        Enpresa enpresa = new Enpresa();
        enpresa.setEnpresaID(1L);

        when(mockEnpresaRespository.findById(1L)).thenReturn(Optional.of(enpresa));

        Enpresa result = enpresaService.findEnpresaByID(1L);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getEnpresaID());

        verify(mockEnpresaRespository, times(1)).findById(1L);
    }

}
