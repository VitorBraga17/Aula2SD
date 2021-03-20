import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SimpleJavaServer {
    public SimpleJavaServer() {
    }

    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(9999);
            String str,op,flag;
            int num,num2,soma;
            while(true) {
                Socket c = s.accept();
                InputStream i = c.getInputStream();
                OutputStream o = c.getOutputStream();
                do {
                    byte[] line = new byte[100];
                    i.read(line);
                    str = new String(line).trim();
                    num = Integer.parseInt(str);
                    o.write(str.getBytes());

                    i.read(line);
                    str = new String(line).trim();
                    op = str;
                    o.write(str.getBytes());

                    i.read(line);
                    str = new String(line).trim();
                    num2 = Integer.parseInt(str);
                    soma = num+num2;
                    str = String.valueOf(soma);
                    if(op.equals("+")){
                        o.write(str.getBytes());
                    }else{
                        str = "a operacao informada esta incorreta";
                        o.write(str.getBytes());
                    }


/*
                    i.read(line);
                    soma = num+num2;
                    String resposta;
                    resposta = String.valueOf(soma);
                    o.write(resposta.getBytes());*/

                } while(!str.trim().equals("bye"));
                c.close();
            }
        } catch (Exception var7) {
            System.err.println(var7);
        }
    }
}