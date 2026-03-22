import java.util.List;
import java.util.Scanner;

public class Combate {

    private Scanner scanner;

    public Combate(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean executar(Steve steve, Inimigo inimigo) {
        System.out.println(" =============================");

        while (steve.estaVivo() && inimigo.estaVivo()) {
            System.out.println();
            System.out.println(" Steve: " + steve.exibirCoracoes() + " (" + steve.getCoracoes() + "/" + steve.getCoracoesMaximos() + ")");
            System.out.println(" " + inimigo.getNome() + ": " + inimigo.exibirCoracoes() + " (" + inimigo.getCoracoes() + ")");
            ///System.out.println();
            System.out.println("\n O que voce faz?");
            System.out.println(" [1] Atacar");
            System.out.println(" [2] Usar item");
            System.out.println(" [3] Fugir");
            System.out.print(" Escolha: ");

            int escolha = lerOpcao(1, 3);

            if (escolha == 1) {
                steve.atacar(inimigo);

                if (inimigo.estaVivo()) {
                    if (inimigo instanceof ZumbiBebe && ((ZumbiBebe) inimigo).desviou()) {
                        System.out.println("  O Zumbi Bebe desviou! Muito rapido!");
                    } else {
                        inimigo.atacar(steve);
                    }
                }

            } else if (escolha == 2) {
                usarItem(steve);

            } else {
                System.out.println("\n  Steve recuou para as sombras...");
                return false;
            }
        }

        if (steve.estaVivo()) {
            System.out.println("\n  " + inimigo.getNome() + " foi derrotado!");
            steve.ganharXP(inimigo.getXpRecompensa());
            return true;
        } else {
            System.out.println("\n  Steve perdeu todos os coracoes...");
            return false;
        }
    }

    private void usarItem(Steve steve) {
        List<Item> inventario = steve.getInventario();

        if (inventario.isEmpty()) {
            System.out.println("  Inventario vazio! Nada para usar.");
            return;
        }

        System.out.println("\n  Qual item deseja usar?");
        steve.exibirInventario();
        System.out.println("  [0] Cancelar");
        System.out.print("  Escolha: ");

        int escolha = lerOpcao(0, inventario.size());

        if (escolha == 0) {
            return;
        }

        Item item = inventario.get(escolha - 1);
        steve.comer((Comida) item);
    }

    private int lerOpcao(int min, int max) {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor >= min && valor <= max) return valor;
                System.out.print(" Opcao invalida. Tente novamente: ");
            } catch (NumberFormatException e) {
                System.out.print(" Digite um numero valido: ");
            }
        }
    }
}