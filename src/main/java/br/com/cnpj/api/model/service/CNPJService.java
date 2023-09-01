package br.com.cnpj.api.model.service;

import br.com.cnpj.api.model.DTO.CNPJInfoDTO;
import br.com.cnpj.api.model.entity.CNPJ;
import br.com.cnpj.api.model.exception.CNPJException;
import br.com.cnpj.api.repository.CNPJRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CNPJService {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CNPJRepository cnpjRepository;

    public List<CNPJ> findAll() {
        return cnpjRepository.findAll();
    }

    public CNPJInfoDTO searchCNPJ(String cnpj) {
        validateCNPJ(cnpj);
        String url = "https://publica.cnpj.ws/cnpj/" + cnpj;
        ResponseEntity<CNPJInfoDTO> response = restTemplate.getForEntity(url, CNPJInfoDTO.class);
        validateResponse(response);
        return response.getBody();
    }

    public CNPJ saveCNPJ(CNPJInfoDTO cnpjInfoDTO) {
        CNPJ cnpj = new CNPJ(cnpjInfoDTO);

        return cnpjRepository.save(cnpj);
    }

    private void validateCNPJ(String cnpj) {
        if (StringUtils.isEmpty(cnpj)) {
            throw new CNPJException("CNPJ informado é inválido.");
        }
    }

    private void validateResponse(ResponseEntity<CNPJInfoDTO> response) {
        if (response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
            throw new CNPJException("CNPJ não encontrado.");
        }

        if (response.getStatusCode().equals(HttpStatus.TOO_MANY_REQUESTS)) {
            throw new CNPJException("Excedido o limite máximo de 3 consultas por minuto. Liberação em 1 minuto.");
        }
    }
}
