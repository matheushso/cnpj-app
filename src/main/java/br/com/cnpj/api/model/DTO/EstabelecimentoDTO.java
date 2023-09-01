package br.com.cnpj.api.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstabelecimentoDTO {

    @JsonProperty("situacao_cadastral")
    private String situacaoCadastral;

    @JsonProperty("data_inicio_atividade")
    private String dataInicioAtividade;

    private CidadeDTO cidade;

    public EstabelecimentoDTO() {

    }

    public EstabelecimentoDTO(String situacaoCadastral, String dataInicioAtividade, CidadeDTO cidade) {
        this.situacaoCadastral = situacaoCadastral;
        this.dataInicioAtividade = dataInicioAtividade;
        this.cidade = cidade;
    }

    public String getSituacaoCadastral() {
        return situacaoCadastral;
    }

    public void setSituacaoCadastral(String situacaoCadastral) {
        this.situacaoCadastral = situacaoCadastral;
    }

    public String getDataInicioAtividade() {
        return dataInicioAtividade;
    }

    public void setDataInicioAtividade(String dataInicioAtividade) {
        this.dataInicioAtividade = dataInicioAtividade;
    }

    public CidadeDTO getCidade() {
        return cidade;
    }

    public void setCidade(CidadeDTO cidade) {
        this.cidade = cidade;
    }
}
