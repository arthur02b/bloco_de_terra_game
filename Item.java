public abstract class Item {

    protected String nome;
    protected String raridade;

    public Item(String nome, String raridade) {
        this.nome = nome;
        this.raridade = raridade;
    }

    public abstract String getDescricao();

    public String getNome() { return nome; }
}