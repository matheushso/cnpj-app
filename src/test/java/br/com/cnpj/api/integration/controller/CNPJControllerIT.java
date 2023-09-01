package br.com.cnpj.api.integration.controller;

import br.com.cnpj.api.model.DTO.CNPJInfoDTO;
import br.com.cnpj.api.model.DTO.CidadeDTO;
import br.com.cnpj.api.model.DTO.EstabelecimentoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CNPJControllerIT {

    @LocalServerPort
    private Integer port;

    @Test
    public void givenGetAllCNPJEndpoint_whenGetRequest_thenRetrieveAllCNPJ() {
        given()
                .basePath("/api/v1/cnpj")
                .port(port)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void givenGetCNPJEndpoint_whenValidCNPJ_thenReturnCNPJ() {
        given()
                .basePath("/api/v1/cnpj/27865757000102")
                .port(port)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void givenGetCNPJEndpoint_whenInvalidCNPJ_thenReturnBadRequest() {
        given()
                .basePath("/api/v1/cnpj/123")
                .port(port)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void givenPostCNPJEndpoint_whenPostRequestWithRequestBody_thenCreateCNPJ() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CidadeDTO cidade = new CidadeDTO("Cidade Exemplo");
        EstabelecimentoDTO estabelecimento = new EstabelecimentoDTO("Ativo", "2023-09-01", cidade);
        CNPJInfoDTO request = new CNPJInfoDTO("12345678901234", "Empresa Exemplo", estabelecimento, "Endereço Exemplo", "1234567890");
        String jsonRequest = objectMapper.writeValueAsString(request);

        given()
                .basePath("/api/v1/cnpj")
                .port(port)
                .contentType(ContentType.JSON)
                .body(jsonRequest) // Define o corpo da solicitação POST
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("razaoSocial", equalTo("Empresa Exemplo"))
                .body("cidade", equalTo("Cidade Exemplo"))
                .body("situacaoCadastral", equalTo("Ativo"))
                .body("dataDeCadastro", equalTo("2023-09-01"))
                .body("endereco", equalTo("Endereço Exemplo"))
                .body("telefone", equalTo("1234567890"));
    }

//    @Test
//    public void givenGetAllCNPJEndpoint_whenNoCNPJsExist_thenReturnEmptyList() {
//        ResponseEntity<List> response = restTemplate.getForEntity("/api/v1/cnpj", List.class);
//
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
//        assertTrue(response.getBody().isEmpty());
//    }
}
