package org.tomdog;

/**
 * Created by zk on 01/06/2018.
 */
public class Person {

    String name;
    Integer age;

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Person setAge(Integer age) {
        this.age = age;
        return this;
    }
    public static void print(Object text){
        System.out.println(text);
    }
    public static void main(String[] args) {
//        System.out.printf("%-10s","abc");
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(String.format("%-20s%-10d,%-10d", Long.toString((int) Math.pow(10,i)) ,i,10*i));
//        }

        System.out.println("current class path is: "+"file:///Users/zk/git/myWebFramework/src/main/java/org/tomdog/Person.java");
        String[] paths = {"", "/", ".",   "Person.class", "/org/tomdog/Person.class" };
        System.out.println(String.format("%-40s%-90s%-40s","path","Person.class.getResource","Thread.currentThread().getClass"));
        for (String path : paths) {
            System.out.println(String.format("%-40s%-90s%-40s", path,
                    Person.class.getResource(path),Thread.currentThread().getClass().getResource(path)));
        }

        //
//        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
//        try {
//            SAXParser saxParser = saxParserFactory.newSAXParser();
//
//            saxParser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("org/tomdog/person.xml"),new PersonHandler());
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
