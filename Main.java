import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        exibirIntro();

        Steve steve = new Steve();

        abrirBauInicial(steve);

        iniciarCaverna(steve);
    }

    private static void exibirIntro() {
        System.out.println("==============================================");
        System.out.println("          BLOCO DE TERRA");
        System.out.println("       Um RPG nas profundezas");
        System.out.println("==============================================");
        System.out.println();
        System.out.println(" O sol esta se pondo no horizonte pixelado.");
        System.out.println(" Steve para na beira de uma caverna.");
        System.out.println(" La dentro, e escuro. Barulhos ecoam.");
        System.out.println(" Algo la no fundo o chama - ele ainda nao sabe o que e.");
        System.out.println();
        System.out.println(" Antes de descer, um bau velho chama atencao.");
        System.out.println(" Alguem deixou para tras. Talvez esperando que");
        System.out.println(" alguem corajoso o bastante aparecesse.");
        System.out.println();
        System.out.println(" [1] Iniciar jogo");
        System.out.print(" Escolha: ");
        lerOpcao(1, 1);
    }

    private static void abrirBauInicial(Steve steve) {
        System.out.println();
        System.out.println("==============================================");
        System.out.println(" BAU INICIAL");
        System.out.println("==============================================");
        System.out.println(" Voce encontra um bau com alguns itens basicos:");
        System.out.println();

        Bau bauInicial = new Bau(true);
        for (Item item : bauInicial.getItens()) {
            System.out.println("  - " + item.getDescricao());
            steve.adicionarItem(item);
        }

        System.out.println();
        System.out.println(" [1] Equipar itens e entrar na caverna");
        System.out.print(" Escolha: ");
        lerOpcao(1, 1);
    }

    private static void iniciarCaverna(Steve steve) {
        System.out.println();
        System.out.println("==============================================");
        System.out.println(" Steve entra na caverna.");
        System.out.println(" O calor do sol some. So pedra e escuridao.");
        System.out.println("==============================================");

        Caverna caverna = new Caverna(scanner);

        boolean continuando = caverna.avancar(steve);
        if (!continuando) {
            exibirDerrota(steve, caverna.getProfundidade());
            scanner.close();
            return;
        }

        while (steve.estaVivo()) {

            if (steve.getNivel() >= 10) {
                boolean venceu = enfrentarBoss(steve);
                if (venceu) {
                    exibirVitoria(steve, caverna.getProfundidade());
                } else {
                    exibirDerrota(steve, caverna.getProfundidade());
                }
                break;
            }

            System.out.println("\n O que voce faz?");
            System.out.println(" [1] Avancar na caverna");
            System.out.println(" [2] Ver inventario");
            System.out.println(" [3] Ver status");
            System.out.println(" [4] Desistir e sair");
            System.out.print(" Escolha: ");

            int opcao = lerOpcao(1, 4);

            if (opcao == 1) {
                boolean sobreviveu = caverna.avancar(steve);
                if (!sobreviveu) {
                    exibirDerrota(steve, caverna.getProfundidade());
                    break;
                }

            } else if (opcao == 2) {
                System.out.println();
                steve.exibirInventario();

            } else if (opcao == 3) {
                System.out.println();
                steve.exibirStatus();

            } else {
                System.out.println("\n Steve recua para a entrada da caverna.");
                System.out.println(" Alguns chamados ficam sem resposta.");
                System.out.println("\n Fim de jogo. Voce saiu.");
                break;
            }
        }

        scanner.close();
    }

    private static boolean enfrentarBoss(Steve steve) {
        System.out.println("\n==============================================");
        System.out.println(" A caverna fica mais silenciosa do que nunca.");
        System.out.println(" Steve sente o chao tremer levemente.");
        System.out.println(" La na frente, dois olhos brilham no escuro.");
        System.out.println(" Pequenos. Rapidos. Com muita fome.");
        System.out.println();
        System.out.println("   *** O ZUMBI BEBE APARECEU! ***");
        System.out.println();
        System.out.println(" O boss final esta aqui. Sem saida.");
        System.out.println("==============================================");

        ZumbiBebe boss = new ZumbiBebe();
        Combate combate = new Combate(scanner);
        return combate.executar(steve, boss);
    }

    private static void exibirVitoria(Steve steve, int profundidade) {
        System.out.println("\n==============================================");
        System.out.println("          VITORIA!");
        System.out.println("==============================================");
        System.out.println(" O Zumbi Bebe caiu.");
        System.out.println(" A caverna ficou em silencio pela primeira vez.");
        System.out.println(" Steve olha para os proprios punhos. Ainda tremem.");
        System.out.println(" Mas ele esta vivo.");
        System.out.println();
        System.out.println(" --- Resumo da aventura ---");
        System.out.println(" Nivel final:      " + steve.getNivel());
        System.out.println(" Profundidade:     " + profundidade + " andares");
        System.out.println(" Coracoes restantes: " + steve.getCoracoes() + "/" + steve.getCoracoesMaximos());
        System.out.println("==============================================");
    }

    private static void exibirDerrota(Steve steve, int profundidade) {
        System.out.println("\n==============================================");
        System.out.println("          FIM DE JOGO");
        System.out.println("==============================================");
        System.out.println(" Steve perdeu todos os coracoes.");
        System.out.println(" A caverna continua la. Escura. Esperando o proximo.");
        System.out.println();
        System.out.println(" --- Resumo da aventura ---");
        System.out.println(" Nivel alcancado:  " + steve.getNivel());
        System.out.println(" Profundidade:     " + profundidade + " andares");
        System.out.println("==============================================");
    }

    private static int lerOpcao(int min, int max) {
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