package br.com.cnpj.api.model.entity;

import br.com.cnpj.api.model.DTO.CNPJInfoDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class CNPJ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razaoSocial;

    private String cidade;

    private String situacaoCadastral;

    private String dataDeCadastro;

    private String endereco;

    private String telefone;

    public CNPJ() {
    }

    public CNPJ(CNPJInfoDTO cnpjInfoDTO) {
        this.razaoSocial = cnpjInfoDTO.getRazaoSocial();
        this.cidade = cnpjInfoDTO.getEstabelecimento().getCidade().toString();
        this.situacaoCadastral = cnpjInfoDTO.getEstabelecimento().getSituacaoCadastral();
        this.dataDeCadastro = cnpjInfoDTO.getEstabelecimento().getDataInicioAtividade();
        this.endereco = cnpjInfoDTO.getEndereco();
        this.telefone = cnpjInfoDTO.getTelefone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getSituacaoCadastral() {
        return situacaoCadastral;
    }

    public void setSituacaoCadastral(String situacaoCadastral) {
        this.situacaoCadastral = situacaoCadastral;
    }

    public String getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(String dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CNPJ cnpj = (CNPJ) o;
        return Objects.equals(id, cnpj.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
