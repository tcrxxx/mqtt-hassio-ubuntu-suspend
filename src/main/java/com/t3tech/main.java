package com.t3tech;
import java.io.IOException;

import com.t3tech.command.Commander;
import com.t3tech.mqtt.ClienteMQTT;
import com.t3tech.mqtt.Fila;
import com.t3tech.mqtt.Subscriber;

public class main {

	private static final String MQTT_TOPIC_X = "teste/teste/";
	private static final String MQTT_SERVER = "tcp://192.168.1.165:1883";
	private static final String MQTT_SERVER_USER = "mosquitto_user";
    private static final String MQTT_SERVER_PASS = "mqtt099812";
    
	public static void main(String[] args) throws InterruptedException, IOException {
    	
		
        ClienteMQTT clienteMQTT = new ClienteMQTT(MQTT_SERVER, MQTT_SERVER_USER, MQTT_SERVER_PASS);
        clienteMQTT.iniciar();

        new Subscriber(clienteMQTT, MQTT_TOPIC_X + "#", 0);

      while (true) {
	      Thread.sleep(1000);
//            clienteMQTT.publicar(MQTT_TOPIC_X, mensagem.getBytes(), 0);
	      if(!Fila.queue.isEmpty()) {
	    	int size = Fila.queue.size();
	    	String msg = Fila.queue.poll();
	    	System.out.println("Atendendo fila: 1/" + size);
	    	System.out.println("Mensagem tirada da fila: " + msg);  
	    	System.out.println("------------------------------\t");
	    	Commander.command(Commander.brokenMessageCommand(msg),true,true);
	      }
      }
    }
}
