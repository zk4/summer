package threadLocal;

/**
 * Created by zk on 03/11/2017.
 */
public class ClientThread  extends   Thread{
    private Sequence sequence;

    public ClientThread(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for (int i = 0; i < 8; i++) {
            System.out.println(Thread.currentThread().getName()+" => " + sequence.getNumber());
        }
    }
}
