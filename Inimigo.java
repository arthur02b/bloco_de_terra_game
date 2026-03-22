public abstract class Inimigo extends Entidade {

    protected int dano;
    protected int xpRecompensa;

    public Inimigo(String nome, int coracoes, int dano, int xpRecompensa) {
        super(nome, coracoes);
        this.dano = dano;
        this.xpRecompensa = xpRecompensa;
    }

    @Override
    public void atacar(Entidade alvo) {
        alvo.receberDano(dano);
        System.out.println("  " + nome + " atacou Steve com " + dano + " de dano!");
    }

    public int getXpRecompensa() { return xpRecompensa; }
}