package com.emc.emergency.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.emc.emergency.data.bean.Notice;
import com.emc.emergency.data.bean.NoticeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hocan on 29-Jul-17.
 */
@Controller
public class NoticeController {
  @Autowired
     private SimpMessagingTemplate template;

  private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @MessageMapping("/notice")
    @SendTo("/topic/notices")
    public Notice notice(NoticeMessage  message) throws Exception {
        Thread.sleep(1000); // simulated delay
      logger.info("Notice from MessageMaping:"+ message.getName());
        return new Notice("Thông báo :, " + message.getName() + "!");
    }
  @RequestMapping(path="/notices", method=POST)
  public void sendNotice(String message) {
        logger.info("Notice from sendNotice:"+ message);

        System.out.println("notice");
        this.template.convertAndSend("/topic/notices", new Notice(message));
    }

  @RequestMapping(path="/NewAccidents", method=POST)
    public void NewAccident(String message) {
          logger.info("Notice from NewAccident:"+ message);

          System.out.println("NewAccident");
          this.template.convertAndSend("/topic/accidents", new Notice(message));
      }
  @RequestMapping(path="/alerts", method=POST)
      public void SendAlert(String message) {
            logger.info("Notice from SendAlert:"+ message);
            System.out.println("SendAlert");
            this.template.convertAndSend("/topic/alerts", new Notice(message));
        }
}
