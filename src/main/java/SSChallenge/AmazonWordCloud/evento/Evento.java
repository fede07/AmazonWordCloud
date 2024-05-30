package SSChallenge.AmazonWordCloud.evento;

import org.springframework.context.ApplicationEvent;

public class Evento extends ApplicationEvent {

    public Evento(Object source) {
        super(source);
    }

}
