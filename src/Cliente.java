public class Cliente {
    private String nome;
    private String sobrenome;
    private String rg;
    private String cpf;
    private String endereco;


    public Cliente(String nome, String sobrenome, String cpf, String rg, String endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public String getNome() {return nome;}

    public String getSobrenome() {return sobrenome;}

    public String getRg() {return rg;}

    public String getCpf() {return cpf;}

    public String getEndereco() {return endereco;}

    public void setNome(String nome) {this.nome = nome;}

    public void setSobrenome(String sobrenome) {this.sobrenome = sobrenome;}

    public void setEndereco(String endereco) {this.endereco = endereco;}
}