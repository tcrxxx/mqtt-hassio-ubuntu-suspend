package com.t3tech;
import java.io.IOException;

import com.t3tech.command.Commander;
import com.t3tech.mqtt.ClienteMQTT;
import com.t3tech.mqtt.Fila;
import com.t3tech.mqtt.Subscriber;

public class Executable {

	private static final int MQTT_TOPIC_X_QOS = 0;
	/* MQTT TOPIC COMMAND BEST PRATICE: 		cmd/<application>/<context>/<destination-id>/<req-type> */
	private static final String MQTT_TOPIC_X = "cmd/hassio-ubuntu-suspend/f8:32:e4:90:23:df/suspend/req"; 
	private static final String MQTT_TOPIC_X_SEARCH = MQTT_TOPIC_X + "/#";
	private static final String MQTT_TOPIC_X_RESPONSE = "cmd/hassio-ubuntu-suspend/f8:32:e4:90:23:df/suspend/res";
	private static final String MQTT_SERVER = "tcp://192.168.1.165:1883";
	private static final String MQTT_SERVER_USER = "mosquitto_user";
    private static final String MQTT_SERVER_PASS = "mqtt099812";
    
	public static void main(String[] args) throws InterruptedException, IOException {
    	
		
        ClienteMQTT clienteMQTT = new ClienteMQTT(MQTT_SERVER, MQTT_SERVER_USER, MQTT_SERVER_PASS);
        clienteMQTT.iniciar();

        new Subscriber(clienteMQTT, MQTT_TOPIC_X_SEARCH, MQTT_TOPIC_X_QOS);

      while (true) {
	      Thread.sleep(1000);
//            clienteMQTT.publicar(MQTT_TOPIC_X, mensagem.getBytes(), 0);
	      if(!Fila.queue.isEmpty()) {

	    	int size = Fila.queue.size();
	    	String msg = Fila.queue.poll();

	    	System.out.println("Atendendo fila: 1/" + size);
	    	System.out.println("Mensagem tirada da fila: " + msg);  
	    	System.out.println("------------------------------\t");

	    	if(msg.equals("systemctl suspend")) {
	    		String output = Commander.command(Commander.brokenMessageCommand("systemctl suspend"),true,true);
	    		clienteMQTT.publicar(MQTT_TOPIC_X_RESPONSE, output.getBytes(), 0);
	    	}
	      }
      }
    }
}
