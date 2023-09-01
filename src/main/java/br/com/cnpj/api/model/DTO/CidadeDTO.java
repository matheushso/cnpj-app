package br.com.cnpj.api.model.DTO;

public class CidadeDTO {

    private String nome;

    public CidadeDTO() {

    }

    public CidadeDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
