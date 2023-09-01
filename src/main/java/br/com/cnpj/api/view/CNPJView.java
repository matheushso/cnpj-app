package br.com.cnpj.api.view;

import br.com.cnpj.api.model.DTO.CNPJInfoDTO;
import br.com.cnpj.api.model.entity.CNPJ;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named(value = "cnpjView")
@ViewScoped
public class CNPJView {

    private RestTemplate restTemplate = new RestTemplate();

    private CNPJInfoDTO cnpjInfoDTO = new CNPJInfoDTO();

    private List<CNPJ> cnpjList = new ArrayList<>();

    public CNPJInfoDTO getCnpjInfoDTO() {
        return cnpjInfoDTO;
    }

    public void setCnpjInfoDTO(CNPJInfoDTO cnpjInfoDTO) {
        this.cnpjInfoDTO = cnpjInfoDTO;
    }

    public List<CNPJ> getCnpjList() {
        return cnpjList;
    }

    public void setCnpjList(List<CNPJ> cnpjList) {
        this.cnpjList = cnpjList;
    }

    public void findAll() {
        String url = "http://localhost:8080/api/v1/cnpj/";

        ResponseEntity<CNPJ[]> response = restTemplate.getForEntity(url, CNPJ[].class);

        cnpjList = Arrays.asList(response.getBody());
    }

    public void searchCNPJ() {
        try {
            String url = "http://localhost:8080/api/v1/cnpj/" + cnpjInfoDTO.getCnpj();
            cnpjInfoDTO = restTemplate.getForObject(url, CNPJInfoDTO.class);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage()));
        }
    }

    public void saveCNPJ() {
        String url = "http://localhost:8080/api/v1/cnpj/";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CNPJInfoDTO> requestEntity = new HttpEntity<>(cnpjInfoDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("CNPJ saved successfully");
            cnpjInfoDTO = new CNPJInfoDTO();
        } else {
            System.out.println("Failed to save CNPJ");
        }
    }

}
