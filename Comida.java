public class Comida extends Item {

    private int cura;

    public Comida(String nome, int cura) {
        super(nome, "Comum");
        this.cura = cura;
    }

    public int getCura() { return cura; }

    @Override
    public String getDescricao() {
        return nome + " [" + raridade + "] — restaura " + cura + " coracao(es)";
    }
}