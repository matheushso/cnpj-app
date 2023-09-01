package br.com.cnpj.api.unit.service;

import br.com.cnpj.api.model.DTO.CNPJInfoDTO;
import br.com.cnpj.api.model.DTO.CidadeDTO;
import br.com.cnpj.api.model.DTO.EstabelecimentoDTO;
import br.com.cnpj.api.model.entity.CNPJ;
import br.com.cnpj.api.model.exception.CNPJException;
import br.com.cnpj.api.model.service.CNPJService;
import br.com.cnpj.api.repository.CNPJRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CNPJServiceTest {

    @InjectMocks
    private CNPJService cnpjService;

    @Mock
    private CNPJRepository cnpjRepository;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll_thenCallFindAllToRepository() {
        cnpjService.findAll();

        verify(cnpjRepository, times(1)).findAll();
    }

    @Test
    public void searchCNPJ_givenCnpjNull_thenReturnException() {
        Exception exception = assertThrows(CNPJException.class, () -> cnpjService.searchCNPJ(null));

        assertEquals("CNPJ informado é inválido.", exception.getMessage());
    }

    @Test
    public void searchCNPJ_givenCnpjValid_thenReturnWithoutException() {
        String cnpj = "27865757000102";
        String url = "https://publica.cnpj.ws/cnpj/" + cnpj;
        ResponseEntity<CNPJInfoDTO> response = new ResponseEntity<>(HttpStatus.OK);
        when(restTemplate.getForEntity(url, CNPJInfoDTO.class)).thenReturn(response);

        assertDoesNotThrow(() -> cnpjService.searchCNPJ(cnpj));
    }

    @Test
    public void searchCNPJ_givenCnpjNotFound_thenReturnException() {
        String cnpj = "123";
        String url = "https://publica.cnpj.ws/cnpj/" + cnpj;
        ResponseEntity<CNPJInfoDTO> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        when(restTemplate.getForEntity(url, CNPJInfoDTO.class)).thenReturn(response);

        Exception exception = assertThrows(CNPJException.class, () -> cnpjService.searchCNPJ(cnpj));

        assertEquals("CNPJ não encontrado.", exception.getMessage());
    }

    @Test
    public void searchCNPJ_givenManyRequests_thenReturnException() {
        String cnpj = "27865757000102";
        String url = "https://publica.cnpj.ws/cnpj/" + cnpj;
        ResponseEntity<CNPJInfoDTO> response = new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        when(restTemplate.getForEntity(url, CNPJInfoDTO.class)).thenReturn(response);

        Exception exception = assertThrows(CNPJException.class, () -> cnpjService.searchCNPJ(cnpj));

        assertEquals("Excedido o limite máximo de 3 consultas por minuto. Liberação em 1 minuto.", exception.getMessage());
    }

    @Test
    public void saveCNPJ_givenCnpjValid_thenCallSaveToRepository() {
        CidadeDTO cidadeDTO = new CidadeDTO("Rio de Janeiro");
        EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO("Ativa", "31/01/1986", cidadeDTO);
        CNPJInfoDTO cnpjInfoDTO = new CNPJInfoDTO("27865757000102", "GLOBO COMUNICACAO E PARTICIPACOES S/A", estabelecimentoDTO, "Rua Lopes Quintas", "21554551");
        CNPJ cnpj = new CNPJ(cnpjInfoDTO);

        cnpjService.saveCNPJ(cnpjInfoDTO);

        verify(cnpjRepository, times(1)).save(cnpj);
    }
}
