import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bau {

    private List<Item> itens;
    private Random random;

    public Bau(boolean inicial) {
        this.itens = new ArrayList<>();
        this.random = new Random();
        if (inicial) {
            preencherBauInicial();
        } else {
            preencherBauCaverna();
        }
    }

    private void preencherBauInicial() {
        itens.add(new Espada("madeira"));
        itens.add(new Comida("Maca", 2));
        itens.add(new Comida("Pao", 3));
    }

    private void preencherBauCaverna() {
        int roll = random.nextInt(100);

        itens.add(gerarComidaAleatoria());

        if (roll < 60) {
            itens.add(new Espada("pedra"));
        } else if (roll < 90) {
            itens.add(new Espada("ferro"));
        } else {
            itens.add(new Espada("diamante"));
        }

        if (random.nextInt(100) < 40) {
            itens.add(gerarComidaAleatoria());
        }
    }

    private Comida gerarComidaAleatoria() {
        int roll = random.nextInt(100);
        if (roll < 40) return new Comida("Maca", 2);
        if (roll < 70) return new Comida("Pao", 3);
        if (roll < 90) return new Comida("Carne", 4);
        return new Comida("Golden Apple", 6);
    }

    public List<Item> getItens() { return itens; }
}