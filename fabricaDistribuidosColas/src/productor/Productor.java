/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productor;

import com.rabbitmq.client.*;
import entidades.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.SerializationUtils;
public class Productor{
    
    private static final String RPC_QUEUE_NAME = "rpc_queue";

    public static List<Producto> crearListaProductos() {
        List<Producto> productos = new ArrayList<>();
        Producto producto1 = new Producto("Leche", 1.99, "Pascual", 10, "1234567890");
        Producto producto2 = new Producto("Huevos", 2.99, "Campofrío", 20, "0987654321");
        Producto producto3 = new Producto("Pan", 0.99, "Bimbo", 30, "2468101214");
        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);
        return productos;
    }

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
        channel.queuePurge(RPC_QUEUE_NAME);

        channel.basicQos(1);

        System.out.println(" [x] Awaiting RPC requests");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder()
                    .correlationId(delivery.getProperties().getCorrelationId())
                    .build();

            try {
                Peticion peticion = (Peticion) SerializationUtils.deserialize(delivery.getBody());
                System.out.println("Tipo peticion; " + peticion.getTipoPeticion() + ", Cliente; " + peticion.getCliente().getNombre());
            } catch (RuntimeException e) {
                System.out.println(" [.] " + e);
            } finally {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(crearListaProductos());
                byte[] bytes = bos.toByteArray();
                channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, bytes);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };

        channel.basicConsume(RPC_QUEUE_NAME, false, deliverCallback, (consumerTag -> {
        }));
    }
}
