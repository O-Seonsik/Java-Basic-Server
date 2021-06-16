import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.List;

public class ServerInitializer {
    public static void main(String[] args){
        int port = 5000;
        System.out.println("Server ON : " + port);

        Reactor reactor = new Reactor(port);

        try {
            Serializer serializer = new Persister();
            File source = new File("Java-Basic-Server/HandlerList.xml");
            ServerListData serverList =serializer.read(ServerListData.class, source);

            for (HandlerListData handlerListData: serverList.getServer()) {
                if("server1".equals(handlerListData.getName())) {
                    List<HandlerData> handlerList = handlerListData.getHandler();
                    for (HandlerData handler: handlerList) {
                        try {
                            reactor.registerHandler(handler.getHeader(), (EventHandler) Class.forName(handler.getHandler()).newInstance());
                        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        reactor.startServer();
    }
}
