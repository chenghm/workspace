package cn.wsn.park.message.delegate.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wsn.park.message.delegate.IMessageDelegate;

@Service
public class MessageDelegate implements IMessageDelegate {

    private static final Logger LOG = Logger.getLogger(MessageDelegate.class);

    @Override
    public void handleMessage(String message) {
        LOG.info("Consumed message with payload: " + message);

    }

}
