import java.util.Random;
import java.util.Scanner;

public class Caverna {

    private Random random;
    private Combate combate;
    private int profundidade;

    public Caverna(Scanner scanner) {
        this.random = new Random();
        this.combate = new Combate(scanner);
        this.profundidade = 0;
    }

    public boolean avancar(Steve steve) {
        profundidade++;
        System.out.println("\n=============================");
        System.out.println(" PROFUNDIDADE: " + profundidade);
        System.out.println("=============================");

        pausar();

        int evento = random.nextInt(100);

        if (evento < 60) {
            return eventoMonstro(steve);
        } else if (evento < 90) {
            eventoBau(steve);
            return true;
        } else {
            eventoMina(steve);
            return true;
        }
    }

    private boolean eventoMonstro(Steve steve) {
        Inimigo inimigo = gerarInimigo(steve.getNivel());
        System.out.println("\n  Um " + inimigo.getNome() + " apareceu na sua frente!");
        System.out.println("  " + inimigo.getNome() + ": " + inimigo.exibirCoracoes() + " (" + inimigo.getCoracoes() + ")");

        return combate.executar(steve, inimigo);
    }

    private void eventoBau(Steve steve) {
        System.out.println("\n  Um bau aparece encostado na parede da caverna!");
        Bau bau = new Bau(false);

        System.out.println("  Dentro do bau:");
        for (Item item : bau.getItens()) {
            System.out.println("   - " + item.getDescricao());
            steve.adicionarItem(item);
        }
    }

    private void eventoMina(Steve steve) {
        System.out.println("\n  Voce chega a um corredor de mina abandonada.");
        System.out.println("  Steve descansa entre os trilhos enferrujados e recupera 1 coracao.");
        steve.setCoracoes(steve.getCoracoes() + 1);
        System.out.println("  Coracoes: " + steve.exibirCoracoes() + " (" + steve.getCoracoes() + "/" + steve.getCoracoesMaximos() + ")");
    }

    private Inimigo gerarInimigo(int nivelSteve) {
        int roll = random.nextInt(100);

        if (nivelSteve <= 3) {
            return roll < 70 ? new Zumbi() : new Spider();
        } else if (nivelSteve <= 7) {
            if (roll < 40) return new Zumbi();
            if (roll < 70) return new Esqueleto();
            return new Spider();
        } else {
            if (roll < 30) return new Esqueleto();
            if (roll < 60) return new Spider();
            return new Creeper();
        }
    }

    private void pausar() {
        try { Thread.sleep(600); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public int getProfundidade() { return profundidade; }
}