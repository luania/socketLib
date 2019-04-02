[![996.icu](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu)

The library supposed that every request consist of a head and a body,
the head contains the body's length.  

# Add lib
- Add socketLib.jar to your project.

# Final usage
```
//a server listen on port 3000
CustomServer customServer = new CustomServer(3000, new SocketServerDelegate<CustomHead>() {
   @Override
   public String handle(CustomHead customHead, String s) {
       return "message";
   }
});
customServer.start();
```
```
//a client send a message
new CustomClient("C2", "messageBody").addListener(new SocketClientListenerAdapter<CustomHead>(){
    @Override
    public void success(CustomHead head, String body) {
        super.success(head, body);
    }
}).run();
```

# Custom
- custom head
```
public class CustomHead implements Head {
    private String command;
    private int bodyLength;
    @Override
    public int getBodyLength() {
        return bodyLength;
    }
    ...constructor
    ...gettings
    ...settings
}
```
- custom contract
```
public class CustomContract implements SocketContract<CustomHead> {
    @Override
    public int headLength() {
        return 40;
    }
    @Override
    public String encoding() {
        return "utf-8";
    }
    @Override
    public CustomHead parseHead(String strHead) {
        return new CustomHead(
            strHead.substring(0, 3).trim(),
            Integer.parseInt(strHead.substring(3, 11).trim())
        );
    }
    @Override
    public String getSendMsg(CustomHead head, String body) {
        //returned String will be send;
    }
}
```
- custom client and server
```
public class CustomClient extends SocketClient<CustomHead> {
    public CustomClient(String command, String message) {
        super(new CustomHead(command), message);
        //addsome base listeners
        addListener(new CustomClientListenerLog());
        addListener(new CustomClientListenerProgress());
    }
    @Override
    public String serverHost() {
        return "127.0.0.1";
    }
    @Override
    public int serverPort() {
        return 3000;
    }
    @Override
    public int timeout() {
        return 30*1000;
    }
    @Override
    public SocketContract<CustomHead> getContract() {
        return new CustomContract();
    }
}
```
```
public class CustomServer extends SocketServer<CustomHead> {
    public CustomServer(int port, SocketServerDelegate<CustomHead> socketDelegate) {
        super(port, socketDelegate);
        //addsome base listeners
        addListener(new CustomServerListenerLog());
    }

    @Override
    public SocketContract<CustomHead> getContract() {
        return new CustomContract();
    }
}
```
- now you can use it like final usage.

# Listeners
- you can listen the server or client by method "addListener".
- add sevral listeners is allowed.
- you can remove a listener by methos "removeListener".

here is a custom client listener:
```
public class CustomClientListenerLog implements SocketClientListener<CustomHead> {
    @Override
    public void onStart() {
        System.out.println("onStart");
    }
    @Override
    public void connSuccess() {
        System.out.println("connSuccess");
    }
    @Override
    public void connFail(Exception e) {
        System.out.println("connFail");
    }
    @Override
    public void sendBegin(String s) {
        System.out.println("sendBegin");
    }
    @Override
    public void success(CustomHead head, String s) {
        System.out.println("success");
    }
    @Override
    public void ioFail(IOException e) {
        System.out.println("ioFail");
    }
    @Override
    public void onFinal() {
        System.out.println("onFinal");
    }
}
```
and here is a custom server listener:
```
public class CustomServerListenerLog implements SocketServerListener<CustomHead> {
    @Override
    public void serverStart(int i) {
        System.out.println("serverStart");
    }
    @Override
    public void serverError(Exception e) {
        System.out.println("serverError");
    }
    @Override
    public void serverKillError(Exception e) {
        System.out.println("serverKillError");
    }
    @Override
    public void serverKill() {
        System.out.println("serverKill");
    }
    @Override
    public void beforeExcute() {
        System.out.println("beforeExcute");
    }
    @Override
    public void excuteError() {
        System.out.println("excuteError");
    }
    @Override
    public void excuteReceived(CustomHead head, String s) {
        System.out.println("excuteReceived");
    }
    @Override
    public void beforeRespond(String s) {
        System.out.println("beforeRespond");
    }
    @Override
    public void afterRespond(String s) {
        System.out.println("afterRespond");
    }
    @Override
    public void excuteFinal() {
        System.out.println("excuteFinal");
    }
}
```
