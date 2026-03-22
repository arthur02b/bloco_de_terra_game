public class Espada extends Item {

    private int bonusAtaque;

    public Espada(String material) {
        super(definirNome(material), definirRaridade(material));
        this.bonusAtaque = definirBonus(material);
    }

    private static String definirNome(String material) {
        return "Espada de " + material;
    }

    private static String definirRaridade(String material) {
        switch (material.toLowerCase()) {
            case "madeira":  return "Comum";
            case "pedra":    return "Incomum";
            case "ferro":    return "Raro";
            case "diamante": return "Epico";
            default:         return "Comum";
        }
    }

    private static int definirBonus(String material) {
        switch (material.toLowerCase()) {
            case "madeira":  return 2;
            case "pedra":    return 3;
            case "ferro":    return 5;
            case "diamante": return 7;
            default:         return 2;
        }
    }

    public int getBonusAtaque() { return bonusAtaque; }

    @Override
    public String getDescricao() {
        return nome + " [" + raridade + "] - " + bonusAtaque + " de ataque";
    }
}