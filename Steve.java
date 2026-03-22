import java.util.ArrayList;
import java.util.List;

public class Steve extends Entidade {

    private int nivel;
    private int experiencia;
    private int xpParaProximoNivel;
    private List<Item> inventario;
    private Espada espadaEquipada;

    private static final int[] XP_POR_NIVEL = {0, 15, 18, 22, 25, 28, 30, 33, 36, 40};

    public Steve() {
        super("Steve", 10);
        this.nivel = 1;
        this.experiencia = 0;
        this.xpParaProximoNivel = XP_POR_NIVEL[1];
        this.inventario = new ArrayList<>();
    }

    @Override
    public void atacar(Entidade alvo) {
        int dano = espadaEquipada != null ? espadaEquipada.getBonusAtaque() : 1;
        alvo.receberDano(dano);
        System.out.println("  Steve atacou " + alvo.getNome() + " com " + dano + " de dano.");
    }

    public void comer(Comida comida) {
        int coracoesAntes = this.coracoes;
        this.coracoes = Math.min(this.coracoes + comida.getCura(), this.coracoesMaximos);
        int curado = this.coracoes - coracoesAntes;
        System.out.println("  Steve comeu " + comida.getNome() + " e recuperou " + curado + " coracao(es).");
        inventario.remove(comida);
    }

    public void equiparEspada(Espada espada) {
        this.espadaEquipada = espada;
        System.out.println("  Steve equipou " + espada.getNome() + "! Dano agora: " + espada.getBonusAtaque());
    }

    public void ganharXP(int xp) {
        this.experiencia += xp;
        System.out.println("  +" + xp + " XP! (" + experiencia + "/" + xpParaProximoNivel + ")");
        while (this.nivel < 10 && this.experiencia >= xpParaProximoNivel) {
            subirNivel();
        }
    }

    private void subirNivel() {
        this.experiencia -= xpParaProximoNivel;
        this.nivel++;
        this.xpParaProximoNivel = (nivel < XP_POR_NIVEL.length) ? XP_POR_NIVEL[nivel] : XP_POR_NIVEL[XP_POR_NIVEL.length - 1];
        System.out.println("\n  *** LEVEL UP! Steve agora e nivel " + nivel + "! ***");
    }

    public void adicionarItem(Item item) {
        if (item instanceof Espada) {
            Espada nova = (Espada) item;
            int danoAtual = espadaEquipada != null ? espadaEquipada.getBonusAtaque() : 0;
            if (nova.getBonusAtaque() > danoAtual) {
                equiparEspada(nova);
            } else {
                System.out.println("  Espada " + nova.getNome() + " e mais fraca que a atual. Deixada para tras.");
            }
        } else {
            inventario.add(item);
        }
    }

    public void exibirStatus() {
        int dano = espadaEquipada != null ? espadaEquipada.getBonusAtaque() : 1;
        System.out.println("-----------------------------");
        System.out.println(" STEVE | Nivel " + nivel + " | XP: (" + experiencia + "/" + xpParaProximoNivel + ")");
        System.out.println(" Coracoes: " + exibirCoracoes() + " (" + coracoes + "/" + coracoesMaximos + ")");
        System.out.println(" Dano: " + dano + " | Espada: " + (espadaEquipada != null ? espadaEquipada.getNome() : "nenhuma"));
        System.out.println("-----------------------------");
    }

    public void exibirInventario() {
        if (inventario.isEmpty()) {
            System.out.println("  Inventario vazio.");
            return;
        }
        System.out.println("  Inventario:");
        for (int i = 0; i < inventario.size(); i++) {
            System.out.println("  [" + (i + 1) + "] " + inventario.get(i).getDescricao());
        }
    }

    public List<Item> getInventario() { return inventario; }
    public int getNivel() { return nivel; }
}