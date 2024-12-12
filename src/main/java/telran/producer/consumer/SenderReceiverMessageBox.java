package telran.producer.consumer;

import java.util.ArrayList;
import java.util.List;

public class SenderReceiverMessageBox {

    private static final int N_MESSAGES = 20;
    private static final int N_RECIEVERS = 10;

    public static void main(String[] args) throws InterruptedException {
        MessageBox messageBox = new SimpleMessageBox();
        Sender sender = new Sender(messageBox, N_MESSAGES);

        // Создаём список получателей
        List<Receiver> receivers = new ArrayList<>();
        for (int i = 0; i < N_RECIEVERS; i++) {
            Receiver receiver = new Receiver(messageBox);
            receivers.add(receiver);
            receiver.start();
        }

        sender.start(); // Запускается метод run() в новом потоке
        sender.join();  // Основной поток main приостанавливается, пока поток sender не завершит работу

        // Завершаем всех Receiver
        for (Receiver receiver : receivers) {
            receiver.stopReceiver();
        }

        // Ждем завершения всех потоков Reciever
        for (Receiver receiver : receivers) {
            receiver.join();
        }
    }
}
