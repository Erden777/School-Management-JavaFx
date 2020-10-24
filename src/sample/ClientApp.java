package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args){
        try {
            Socket socket = new Socket("127.0.0.1", 1999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Scanner in = new Scanner(System.in);
       /*     while(true){
                System.out.println("1. add car");
                System.out.println("2. view all");
                System.out.println("3. remove car");
                System.out.println("4. exit");

                int choice = in.nextInt();

                if(choice == 1){
                    System.out.print("model: ");
                    String model = in.next();
                    System.out.print("year: ");
                    int year = in.nextInt();
                    System.out.print("country: ");
                    String country = in.next();

                    Car c = new Car(null, model, year, country);
                    Request req = new Request("ADD", c);

                    oos.writeObject(req);

                    Reply rep = (Reply)ois.readObject();
                    System.out.println(rep.getCode());
                }

                if(choice == 2){
                    Request req = new Request("VIEW");
                    oos.writeObject(req);

                    Reply rep = (Reply)ois.readObject();

                    ArrayList<Car> list = rep.getCars();

                    for(Car c : list){
                        System.out.println(c);
                    }
                }

                if(choice == 3){
                    System.out.println("enter id to remove: ");
                    Long id = in.nextLong();

                    Request req = new Request("REMOVE", id);
                    oos.writeObject(req);
                }

                if(choice == 4){
                    break;
                }
            }*/

            oos.close();
            ois.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
