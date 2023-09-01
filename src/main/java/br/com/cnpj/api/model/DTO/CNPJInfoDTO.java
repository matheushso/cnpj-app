package br.com.cnpj.api.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CNPJInfoDTO {

    private String cnpj;

    @JsonProperty("razao_social")
    private String razaoSocial;

    private EstabelecimentoDTO estabelecimento;

    private String endereco;

    private String telefone;

    public CNPJInfoDTO() {

    }

    public CNPJInfoDTO(String cnpj, String razaoSocial, EstabelecimentoDTO estabelecimento, String endereco, String telefone) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.estabelecimento = estabelecimento;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public EstabelecimentoDTO getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(EstabelecimentoDTO estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
