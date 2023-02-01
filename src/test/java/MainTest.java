import Actor.Actor;
import Actor.HelloWorldActor;
import Actor.InsultActor;
import Actor.ReflectiveActor;
import ActorContext.ActorContext;
import Decorator.FirewallDecorator;
import Decorator.FirewallLambdaDecorator;
import InsultService.InInsultService;
import InsultService.InsultService;
import Message.*;
import MonitorService.MonitorService;
import Proxy.ActorProxy;
import Proxy.DynamicProxy;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import static junit.framework.TestCase.*;

/**
 * MainTest class contains a set of tests that check the functionality of the actors and decorators in the system.
 *
 * @author David, Ruben
 *
 */
public class MainTest {
    String[] mensajes = {"Silly!","Asshole","How about dumb","Even my grandmother programs better than you","Fuck off"};

    ActorContext acc = ActorContext.getInstance();

    InsultActor actor1=new InsultActor("David");
    InsultActor actor2=new InsultActor("Ruben");
    InsultActor actor4=new InsultActor("Benito");
    InsultActor actor6=new InsultActor("Marcos");

    ActorProxy proxy1 = ActorContext.spawnActor(actor1);
    ActorProxy proxy2 = ActorContext.spawnActor(actor2);
    ActorProxy proxy4 = ActorContext.spawnActor(new FirewallDecorator(actor4));
    ActorProxy proxy8 = ActorContext.spawnActor(new FirewallLambdaDecorator(actor6));

    MonitorService monitorTest= new MonitorService();





    /**
     * Test 1 -> Check the operation of the HelloWorldActor and check if the "Hello world" message is received correctly
     *
     */
    @Test
    public void testOption1() throws InterruptedException {
        // Inicializar actores
        ActorProxy hello = ActorContext.spawnActor(new HelloWorldActor("Greeter"));

        hello.send(new Message(null,"Hello world"));

        assertEquals("Hello world", hello.receive().getMessage());
    }

    /**
     * Test 2 -> Check the communication between a HelloWorldActor and an InsultActor
     */
    @Test
    public void testOption2() throws InterruptedException {
        // Inicializar actores
        ActorProxy hello = ActorContext.spawnActor(new HelloWorldActor("Greeter"));
        ActorProxy proxyInsulter = ActorContext.spawnActor(new InsultActor("Insulter"));
        proxyInsulter.send(new InsultMe(hello));

        assertTrue(Arrays.asList(mensajes).contains(hello.receive().getMessage()));
    }

    /**
     * Test 3 -> Check if random insults from the list of insults to each actor are received correctly
     */
    @Test
    public void testOption3() throws InterruptedException {
        proxy2.send(new InsultMe(proxy1));


        Message m1= proxy1.receive();

        assertTrue(Arrays.asList(mensajes).contains(m1.getMessage()));
    }

    /**
     * Test 4 -> Check the operation of the focused dynamicProxy when adding a new insult
     */
    @Test
    public void testOption4() throws InterruptedException {
        InInsultService insulter = (InInsultService) DynamicProxy.intercept(new InsultService(proxy1),proxy1);
        insulter.addInsult("subnormal22");
        assertEquals("[Silly!, Asshole, How about dumb, Even my grandmother programs better than you, Fuck off, subnormal22]",insulter.getAllInsults());
    }

    /**
     * Test 5 -> Check if the actors receive the messages correctly
     */
    @Test
    public void testOption5() throws InterruptedException {

        proxy1.send(new QuitMessage(proxy4,"Get out"));



        assertEquals("Get out",proxy1.receive().getMessage());
    }

    /**
     * Test 6 -> Check if the communication between actors works correctly and if they receive adequate messages
     */
    @Test
    public void testOption6() throws InterruptedException {
        proxy2.send(new InsultMe(proxy1));
        assertTrue(Arrays.asList(mensajes).contains(proxy1.receive().getMessage()));
    }

    /**
     * Test 7 -> Check if the Lambda Decorator Firewall filters correctly the messages that correspond to it
     */
    @Test
    public void testOption7() throws InterruptedException {

        Predicate<Object> isString = o -> o instanceof Integer;
        proxy8.send(new AddClosureMessage(proxy1,"", isString));
        assertTrue(((FirewallLambdaDecorator) proxy8.getActor()).getCorrect());
        Thread.sleep(5000);
        proxy8.send(new Message(proxy1,"Tonto"));
        assertFalse(((FirewallLambdaDecorator) proxy8.getActor()).getCorrect());

    }

    /**
     * Test 8 -> Count if the number of registered actors is correct
     */
    @Test
    public void testOption8()  {
        System.out.println(acc.getNames().size());
        assertEquals(6,acc.getNames().size());
    }


    /**
     * Test 9 -> Function of ReflectiveActor
     */
    @Test
    public void testOption9() throws InterruptedException {
        Actor insult = ActorContext.spawnActor(new ReflectiveActor(new InsultService(proxy1))).getActor();

        insult.send(new GetInsultMessage(proxy2));
        insult.processMessage(new GetInsultMessage(proxy2));
        Thread.sleep(5000);
        Message message = proxy2.receive();

        assert message != null;
        System.out.println(message.getMessage());
        assertTrue(Arrays.asList(mensajes).contains(message.getMessage()));
    }

    /**
     * Test 10 -> Monitor service test
     */
    @Test
    public void testOption10() throws InterruptedException {
        ArrayList<String> list= new ArrayList<String>();
        list.add("Ruben");
        list.add("David");
        monitorTest.monitorActor(proxy1.getName());
        monitorTest.monitorActor(proxy2.getName());
        proxy2.send(new Message(proxy1,"Tonto1"));
        proxy1.send(new Message(proxy2,"Tonto2"));
        Thread.sleep(5000);
        assertEquals(list, monitorTest.getTraffic().get("LOW"));
    }

}
