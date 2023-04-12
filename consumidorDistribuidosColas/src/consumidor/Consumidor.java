/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package consumidor;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.Channel;
import com.rabbitmq.client.AMQP.Connection;
import com.rabbitmq.client.ConnectionFactory;
import entidades.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josej
 */
public class Consumidor implements AutoCloseable { private com.rabbitmq.client.Connection connection;
    private com.rabbitmq.client.Channel channel;
    private String requestQueueName = "rpc_queue";

    public Consumidor() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public static void main(String[] argv) {
        try (Consumidor clienteRCP = new Consumidor()) {
            Peticion peticion = new Peticion(23233, TipoPeticion.CONSULTA, Calendar.getInstance().getTime(), Prioridad.MEDIA, new Cliente(12, "jose", 12, "alberto vargas"));
            System.out.println(peticion.toString());
            System.out.println(" [x] Requesting peticion(" + peticion.getTipoPeticion() + ")");
            List<Producto> response = clienteRCP.call(peticion);
            System.out.println(" [.] Got '" + response + "'");
        } catch (IOException | TimeoutException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public List<Producto> call(Peticion message) throws IOException, InterruptedException, ExecutionException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(message);
        byte[] bytes = bos.toByteArray();

        channel.basicPublish("", requestQueueName, props, bytes);

        final CompletableFuture<List<Producto>> response = new CompletableFuture<>();

        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                ByteArrayInputStream bis = new ByteArrayInputStream(delivery.getBody());
                ObjectInputStream ois = new ObjectInputStream(bis);
                List<Producto> responseList = null;
                try {
                    responseList = (List<Producto>) ois.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("Error; "+ex.getMessage());
                }
                response.complete(responseList);
            }
        }, consumerTag -> {
        });

        List<Producto> productList = response.get();
        channel.basicCancel(ctag);
        return productList;
    }

    public void close() throws IOException {
        connection.close();
    }
    
}
