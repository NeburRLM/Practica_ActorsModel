package Main;

import Actor.HelloWorldActor;
import Actor.*;
import ActorContext.ActorContext;
import Decorator.EncryptionDecorator;
import Decorator.FirewallDecorator;
import Decorator.FirewallLambdaDecorator;
import InsultService.*;
import Listener.Event;
import Listener.EventType;
import Message.*;
import Message.Message;
import Message.QuitMessage;
import MonitorService.MonitorService;
import Proxy.ActorProxy;
import Proxy.*;
import Ring.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 *
 * @author David Perez Fernandez, Ruben Lopez Martinez
 * @version 01-02-2023

 * Main class that runs the different options for the actor communication examples.
 * The class creates instances of different actors and proxies, and uses them to demonstrate different communication
 * options. The options include:
 * Option 1 - Communication HelloWorldActor
 * Option 2 - Communication using actorProxy
 * Option 3 - Communication actor-me
 * Option 4 - Communication dynamic service
 * Option 5 - Communication actor-actor
 * Option 6 - Insult Communication
 * Option 7 - Communication decorator-actor
 * Option 8 - Monitor service
 * Option 9 - Communication encrypt-actor
 * Option 10 - Events
 * Option 11 - Sends Messages
 * Option 12 - Prove ActorPingPong
 * Option 13 - Prove ActorRing
 * Option 14 - Lambda Firewall
 * Option 15 - Reflective Actor
 * Exit
 * The class also has a MonitorService instance that can be used to monitor the actors.
 *
 */
public class Main {

        /**
         * Main method that initializes the showMenu method and handles any InterruptedException that may occur
         * @param args standard input for main method
         * @throws InterruptedException if the thread is interrupted
         *
         */
        public static void main(String[] args) throws InterruptedException{
                showMenu();
        }

        /**
         * Displays the main menu with options for the different communication options and runs the chosen option.
         *
         */
        public static void showMenu() throws InterruptedException{
                ActorContext acc = ActorContext.getInstance();
                InsultActor actor1=new InsultActor("David");
                InsultActor actor2=new InsultActor("Ruben");
                InsultActor actor3=new InsultActor("Pedro");
                InsultActor actor4=new InsultActor("Benito");
                InsultActor actor5=new InsultActor("Dani");
                InsultActor actor6=new InsultActor("Marcos");
                PingPongActor pActor1= new PingPongActor("Rodolfo");
                PingPongActor pActor2= new PingPongActor("Papurri");

                ActorProxy proxy1 = ActorContext.spawnActor(actor1);
                ActorProxy proxy2 = ActorContext.spawnActor(actor2);
                ActorProxy proxy3 = ActorContext.spawnActor(new EncryptionDecorator(actor3));
                ActorProxy proxy4 = ActorContext.spawnActor(new FirewallDecorator(actor4));
                ActorProxy proxy5 = ActorContext.spawnActor(new EncryptionDecorator(actor5));
                ActorProxy proxy6 = ActorContext.spawnActor((pActor1));
                ActorProxy proxy7 = ActorContext.spawnActor((pActor2));
                ActorProxy proxy8 = ActorContext.spawnActor(new FirewallLambdaDecorator(actor6));


                MonitorService service = new MonitorService();

                Thread.sleep(2000);
                service.monitorAllActors();

                System.out.println("---------------------------\n");
                System.out.println("Communication started");
                System.out.println("------------------------\n");

                System.out.println("Communication between actors");

                System.out.println(acc.getNames());

                Scanner scanner = new Scanner(System.in);
                int option = 0;
                while (option != 16) {
                        System.out.println("---------------------------\n");
                        System.out.println("1. Option 1 - Communication HelloWorldActor");
                        System.out.println("2. Option 2 - Communication using actorProxy");
                        System.out.println("3. Option 3 - Communication actor-me");
                        System.out.println("4. Option 4 - Communication dynamic service");
                        System.out.println("5. Option 5 - Communication actor-actor");
                        System.out.println("6. Option 6 - Insult Communication");
                        System.out.println("7. Option 7 - Communication decorator-actor");
                        System.out.println("8. Option 8 - Monitor service");
                        System.out.println("9. Option 9 - Communication encrypt-actor");
                        System.out.println("10. Option 10 - Events");
                        System.out.println("11. Option 11 - Sends Messages");
                        System.out.println("12. Option 12 - Prove PingPongActor");
                        System.out.println("13. Option 13 - Prove RingActor");
                        System.out.println("14. Option 14 - Prove FirewallLambdaDecorator");
                        System.out.println("15. Option 15 - Prove ReflectiveActor");
                        System.out.println("16. Exit");
                        System.out.println("------------------------\n");
                        System.out.print("Select an option: ");
                        option = scanner.nextInt();
                        switch (option) {
                                case 1 -> option1();
                                case 2 -> option2();
                                case 3 -> option3(proxy2, proxy1);
                                case 4 -> option4(proxy1);
                                case 5 -> option5(proxy1, proxy2, proxy4);
                                case 6 -> option6(proxy2,proxy1);
                                case 7 -> option7(acc, proxy1, proxy4);
                                case 8 -> option8(service);
                                case 9 -> option9(proxy3, proxy5);
                                case 10 -> option10(service);
                                case 11 -> option11(service);
                                case 12 -> option12(proxy6,proxy7);
                                case 13 -> option13(service);
                                case 14 -> option14(proxy8, proxy1);
                                case 15 -> option15(proxy1,proxy2);
                                case 16 -> System.out.println("Exiting...");
                                default -> System.out.println("Invalid option, try again");
                        }
                }
                scanner.close();
        }



        /**
         * Option 1 - Communication HelloWorldActor
         */
        private static void option1() throws InterruptedException {
                ActorProxy hello = ActorContext.spawnActor(new HelloWorldActor("Greeter"));
                hello.send(new Message(null,"Hello world"));
                Thread.sleep(5000);
        }

        /**
         * Option 2 - Communication using actorProxy
         */
        private static void option2() throws InterruptedException {
                ActorProxy hello = ActorContext.spawnActor(new HelloWorldActor("Greeter"));
                // InsultActor insult = new InsultActor("Insulter");
                ActorProxy proxyInsulter = ActorContext.spawnActor(new InsultActor("Insulter"));
                proxyInsulter.send(new InsultMe(hello));
                Thread.sleep(5000);
        }

        /**
         * Option 3 - Communication actor-me
         *
         * @param proxy2 proxy of InsultActor
         * @param proxy1 proxy of InsultActor
         */
        private static void option3(ActorProxy proxy2, ActorProxy proxy1) throws InterruptedException {
                proxy2.send(new InsultMe(proxy1));
                Message m2= proxy2.receive();
                System.out.println(m2.getMessage());
                Message m1= proxy1.receive();
                System.out.println(m1.getMessage());
                Thread.sleep(5000);
        }

        /**
         * Option 4 - Communication dynamic proxy
         *
         * @param proxy1 proxy of InsultActor
         */
        private static void option4(ActorProxy proxy1) throws InterruptedException {
                InInsultService insulter = (InInsultService) DynamicProxy.intercept(new InsultService(proxy1),proxy1);
                insulter.addInsult("subnormal22");
                System.out.println(insulter.getAllInsults());
                Thread.sleep(5000);
        }

        /**
         * Option 5 - Communication actor-actor
         *
         * @param proxy1 proxy of InsultActor
         * @param proxy2 proxy of InsultActor
         * @param proxy4 proxy of FirewallDecorator
         */
        private static void option5(ActorProxy proxy1, ActorProxy proxy2, ActorProxy proxy4) throws InterruptedException {
                proxy2.send(new Message(proxy1, "Hello"));
                proxy1.send(new QuitMessage(proxy4,"Get out"));
                Thread.sleep(5000);
        }

        /**
         * Option 6 - Insult Communication
         *
         * @param proxy1 proxy of InsultActor
         * @param proxy2 proxy of InsultActor
         */
        private static void option6(ActorProxy proxy1, ActorProxy proxy2) throws InterruptedException {
                proxy2.send(new InsultMe(proxy1));
                Message result = proxy2.receive();
                System.out.println(result.getMessage());
                Thread.sleep(5000);


        }

        /**
         * Option 7 - Communication decorator-actor
         *
         * @param acc instance of ActorContext
         * @param proxy1 proxy of InsultActor
         * @param proxy4 proxy of FirewallDecorator
         */
        private static void option7(ActorContext acc, ActorProxy proxy1, ActorProxy proxy4) throws InterruptedException {
                Actor actor=acc.lookup(proxy1.getName());
                proxy4.send(new InsultMe(proxy1));
                proxy4.send(new InsultMe(actor));
                proxy1.send(new InsultMe(proxy4));
                Thread.sleep(5000);

        }

        /**
         * Option 8 -Monitor service
         *
         * @param service MonitorService
         */
        private static void option8( MonitorService service) {
                Map<String, List<String>> e=service.getTraffic();
                System.out.println(e.get("LOW"));
                System.out.println(e.get("MEDIUM"));
                System.out.println(e.get("HIGH"));
                System.out.println(service.getNumberOfMessages("David"));
        }

        /**
         * Option 9 - Communication encrypt-actor
         *
         * @param proxy3 proxy of EncryptionDecorator
         * @param proxy5 proxy of EncryptionDecorator
         */
        private static void option9(ActorProxy proxy3, ActorProxy proxy5) throws InterruptedException {
                proxy3.send(new Message(proxy5, "Hello"));
        }

        /**
         * Option 10 - Events
         *
         * @param service MonitorService
         */
        private static void option10(MonitorService service) {
                Map<EventType, List<Event>> mp =service.getEvents();
                System.out.println(mp);
        }

        /**
         * Option 11 - Sends Messages
         *
         * @param ms MonitorService
         */
        private static void option11( MonitorService ms ) {
                List<Message> m = ms.getSentMessages().get("David");
                for(Message elem : m)
                        System.out.println(elem.getMessage());
        }

        /**
         * Option 12 - PingPong
         *
         * @param p6 PingPongActor
         * @param p7 PingPongActor
         */
        private static void option12(ActorProxy p6, ActorProxy p7) throws InterruptedException {
                p7.send(new Message(p6,"Tonto"));
        }

        /**
         * Option 13 - RingActor
         *
         * @param service MonitorService
         */
        private static void option13(MonitorService service) throws InterruptedException {
                int n=100;

                String idActor = "1";
                ArrayList<RingActor> actorsListRing = new ArrayList<>();
                for(int i=0; i<n;i++){
                        int idIntActor = Integer.parseInt(idActor);
                        idIntActor = idIntActor + 1;
                        idActor = Integer.toString(idIntActor);
                        actorsListRing.add((RingActor) ActorContext.spawnActor(new RingActor(idActor)).getActor());
                }
                service.monitorAllActors();
                Ring ts=new Ring();
                ts.ring(actorsListRing, 100);
                actorsListRing.get(0).getProxy().send(new Message(actorsListRing.get(n-1).getProxy(),"Hello"));
        }

        /**
         * Option 14 - LambdaFirewallDecorator
         *
         * @param firewall FirewallLambdaDecorator
         * @param proxy1 proxy of InsultActor
         */
        private static void option14(ActorProxy firewall, ActorProxy proxy1) throws InterruptedException {

                Predicate<Object> isString = o -> o instanceof Integer;
                AddClosureMessage adm = new AddClosureMessage(proxy1,"", isString);
                firewall.send(adm);
                Thread.sleep(800);
                firewall.send(new Message(proxy1,"Tonto"));

        }

        /**
         * Option 15 - ReflectiveActor
         * @param proxy1 proxy of InsultActor
         * @param proxy2 proxy of InsultActor
         */
        private static void option15(ActorProxy proxy1,ActorProxy proxy2) throws InterruptedException{
                Actor insult = ActorContext.spawnActor(new ReflectiveActor(new InsultService(proxy1))).getActor();
                insult.send(new GetInsultMessage(proxy2));
                Thread.sleep(5000);
                Message message = proxy2.receive();
                assert message != null;
                System.out.println(message.getMessage());
        }

}