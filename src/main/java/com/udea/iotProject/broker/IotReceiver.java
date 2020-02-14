package com.udea.iotProject.broker;

/*import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

@EnableBinding( IotReceiver.InputChannel.class)
public class IotReceiver {
	
    @StreamListener(InputChannel.SINK)
    public void handle(String message) {
    	
    	System.out.println("new message:" + message + ", from worker :" + Thread.currentThread().getName());
    
        	   		
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public interface InputChannel {
        String SINK = "message-sink";
        @Input(SINK)
        SubscribableChannel sink();
    }
    
} */


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udea.iotProject.service.DataService;

@RabbitListener(queues = {"iotSound","iotCritic"})
@Component
public class IotReceiver {
 
	@Autowired
	public DataService dataService;
    @RabbitHandler
    public void receiveMessage(byte[] message) {
         //System.out.println("Nivel de ruido: " +new String(message));
         dataService.processMessage(new String(message));
    }
}

