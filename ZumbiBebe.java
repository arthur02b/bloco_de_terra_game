import java.util.Random;

public class ZumbiBebe extends Inimigo {

    private final Random random = new Random();

    public ZumbiBebe() {
        super("Zumbi Bebe", 20, 5, 50);
    }

    public boolean desviou() {
        return random.nextInt(100) < 30;
    }
}