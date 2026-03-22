public abstract class Entidade {

    protected String nome;
    protected int coracoes;
    protected int coracoesMaximos;

    public Entidade(String nome, int coracoes) {
        this.nome = nome;
        this.coracoes = coracoes;
        this.coracoesMaximos = coracoes;
    }

    public abstract void atacar(Entidade alvo);

    public void receberDano(int dano) {
        this.coracoes -= dano;
        if (this.coracoes < 0) {
            this.coracoes = 0;
        }
    }

    public boolean estaVivo() {
        return this.coracoes > 0;
    }

    public String exibirCoracoes() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coracoes; i++) {
            sb.append("<3 ");
        }
        if (coracoes == 0) {
            sb.append("sem coracoes");
        }
        return sb.toString().trim();
    }

    public String getNome() { return nome; }
    public int getCoracoes() { return coracoes; }
    public int getCoracoesMaximos() { return coracoesMaximos; }
    public void setCoracoes(int coracoes) { this.coracoes = Math.min(coracoes, coracoesMaximos); }
}