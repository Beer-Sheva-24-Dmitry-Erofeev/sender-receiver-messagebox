package telran.producer.consumer;

public class Sender extends Thread {

    // MessageBox - interface, и он не будет зависеть от реализации
    private final MessageBox messageBox;

    private final int nMessages;

    public Sender(MessageBox messageBox, int nMessages) {
        this.messageBox = messageBox;
        this.nMessages = nMessages;
    }

    @Override
    public void run() {
        for (int i = 0; i < nMessages; i++) {
            try {
                messageBox.put("message number " + (i + 1));
            } catch (InterruptedException e) {
            }
        }
    }
}
