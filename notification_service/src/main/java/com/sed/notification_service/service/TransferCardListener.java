package com.sed.notification_service.service;


import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.sed.notification_service.service.DTO.TransactionDTO;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransferCardListener {
    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(TransferCardListener.class);
    protected static final org.slf4j.Logger smsLog = LoggerFactory.getLogger("Sms");
    Gson gson;
    SmsServices smsServices;

    @Autowired
    public TransferCardListener(SmsServices smsServices, Gson gson) {
        this.smsServices = smsServices;
        this.gson = gson;
    }

    @RabbitListener(queues = "sms", ackMode = "MANUAL")
    public void test(@Payload String msg, Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {
            Map map = gson.fromJson(msg, Map.class);
            TransactionDTO transactionDTO = gson.fromJson(String.valueOf(map.get("message")), TransactionDTO.class);
            Map<String, String> sms = generateSmg(transactionDTO);
            ResponseEntity result = smsServices.sendSMS(sms.get("mobileNumber"), sms.get("msg"));
            if (result.getStatusCode().equals(HttpStatus.OK)) {
                channel.basicAck(tag, false);
                smsLog.info("succeed send sms->"+sms.get("msg"));
            } else {
                logger.error("failed send sms");
                channel.basicReject(tag, true);
            }
        } catch (Exception e) {

            logger.error(e.getMessage());
            channel.basicReject(tag, true);
        }
    }


    private Map<String, String> generateSmg(TransactionDTO transactionDTO) {
        Map<String, String> sms = new HashMap<>();
        /*fetch user mobile number*/
        sms.put("mobileNumber", "091212345678");
        sms.put("msg", "transaction id: " + transactionDTO.getTransactionId() + "->" + transactionDTO.getStatus());
        return sms;

    }
}
