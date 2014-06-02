package cn.wsn.park.message.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.wsn.park.message.service.IMessageSenderService;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class MessageAction extends ActionSupport {
    
    @Autowired
    private IMessageSenderService messageSenderService;

    private static final long serialVersionUID = 1194224944083167824L;
    
    private String text;
    private String message;
    private String clientId;
    
    public String execute(){
        
        try {
            messageSenderService.sendMessage(text,clientId);
            message=SUCCESS;
        } catch (Exception e) {
            message=ERROR+e.getMessage();
        }
        return SUCCESS;
        
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
