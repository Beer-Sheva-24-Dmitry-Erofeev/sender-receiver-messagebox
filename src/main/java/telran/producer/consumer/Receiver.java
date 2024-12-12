package telran.producer.consumer;

public class Receiver extends Thread {

    private volatile boolean stopped = false;
    private MessageBox messageBox;

    public Receiver(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    public void stopReceiver() {
        stopped = true;
        interrupt(); // Прерывает блокирующую операцию messageBox.take()
    }

    public void setMessageBox(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    @Override
    public void run() {
        try {
            while (!stopped) {
                String message = messageBox.take(); // Блокирующий вызов
                System.out.printf("Thread: %s, Message: %s\n", getName(), message);
            }
        } catch (InterruptedException e) {
            // Поток прерван для завершения
        }
    }

}
