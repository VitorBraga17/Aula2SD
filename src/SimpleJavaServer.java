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
            String str,str2,str3,str4,op;
            int num=0,num2=0;
            float soma=0;
            while(true) {
                Socket c = s.accept();
                InputStream i = c.getInputStream();
                OutputStream o = c.getOutputStream();
                do {
                    byte[] line = new byte[100];
                    i.read(line);//read 1
                    str = new String(line).trim();
                    try{
                        num = Integer.parseInt(str);
                        System.out.println(num);
                    }catch(NumberFormatException ex){
                        str = "x";
                    }
                    o.write(str.getBytes());//write 1

                    i.read(line);//read 2
                    str2 = new String(line).trim();
                    op = str2.substring(0,1);
                    System.out.println(op);
                    o.write(op.getBytes());//write 2

                    i.read(line);//read 3
                    str3 = new String(line).trim();
                    try{
                        num2 = Integer.parseInt(str3);
                        System.out.println(num2);
                    }catch(NumberFormatException ex){
                        str3 = "x";
                        System.out.println(num2);
                    }

                    if(op.equals("+") || op.equals("-" ) || op.equals("*") || op.equals("/") ){
                        switch (op) {
                            case "+":
                                soma = num + num2;
                                break;
                            case "-":
                                soma = num - num2;
                                break;
                            case "*":
                                soma = num * num2;
                                break;
                            case "/":
                                soma = num / num2;
                                break;
                        }
                        str4 = String.valueOf(soma);
                    }else{
                        str4 = "x";
                    }
                    if(str.equals("x")|| str4.equals("x") ||str3.equals("x")){
                        str4 = "valores incorretos! Tente novamente";
                    }
                    o.write(str4.getBytes());//write 3
                } while(!str.trim().equals("bye"));
                c.close();
            }
        } catch (Exception var7) {
            System.err.println(var7);
        }
    }
}