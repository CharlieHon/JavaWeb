package com.charlie.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JavaJson {
    public static void main(String[] args) {

        // 创建一个gson对象，作为一个工具对象使用
        Gson gson = new Gson();

        // 演示javabean和json字符串的转换
        Book book = new Book(100, "十三经注疏");

        // 1. 演示把 javabean -> json字符串
        String strBook = gson.toJson(book);
        // strBook={"id":100,"name":"十三经注疏"}
        System.out.println("strBook=" + strBook);
        // 2. string -> javabean
        /*
        1. strBook 是一个 string字符串
        2. Book.class 指定将 JSON 字符串转成 Book对象
        3. 底层是反射机制
         */
        Book book2 = gson.fromJson(strBook, Book.class);
        // book2=Book{id=100, name='十三经注疏'}
        System.out.println("book2=" + book2);

        // 3. 演示把 list对象 -> json字符串
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(new Book(200, "倚天屠龙记"));
        bookList.add(new Book(260, "易经"));

        // 因为把对象、集合转成字符串，相对简单
        // 底层只需要遍历，按照json格式拼接返回即可
        String strBookList = gson.toJson(bookList);
        // [{"id":200,"name":"倚天屠龙记"},{"id":260,"name":"易经"}]
        System.out.println(strBookList);

        // 4. json字符串 -> list对象
        /*
        1) 如果需要把json字符串转成 集合这样复杂的类型，需要使用 gson 提供一个类
        2) TypeToken ，是一个自定义泛型类，需要通过 TypeToken来指定需要转换成的类型

        package com.google.gson.reflect;

        public class TypeToken<T> {
            final Class<? super T> rawType;
            final Type type;
            final int hashCode;

            protected TypeToken() {
                this.type = getSuperclassTypeParameter(this.getClass());
                this.rawType = Types.getRawType(this.type);
                this.hashCode = this.type.hashCode();
            }
        }
        */
        // 1) 返回类型的完整路径 java.util.List<com.charlie.json.Book>
        // 2) gson设计者，需要得到类型的完整路径，然后进行底层反射

        /*
        1. 如果使用 new TypeToken<List<Book>>() 提示
                TypeToken()' has protected access in 'com.google.gson.reflect.TypeToken
        2. 因为 TypeToken 的无参构造器是 protected，上述方式就是调用其无参构造器
        3. 因为在java中，如果一个方法是 protected 而且不再同一个包，是不能直接访问的，因此报错
        4. 为什么这样可以？ ： 涉及匿名内部类的知识
            new TypeToken<List<Book>>() {}
        5. 上述方法类型不是 TypeToken 而是一个匿名内部类(可以理解为子类)
        6. 而且这个匿名内部类是有自己的无参构造器(隐式)，根据java基础，当执行子类的无参构造器时，默认有super()
         */
        Type type = new TypeToken<List<Book>>() {}.getType();
        System.out.println("type= " + type);    // type= java.util.List<com.charlie.json.Book>
        List<Book> bookList2 = gson.fromJson(strBookList, type);
        System.out.println("bookList2= " + bookList2);  // bookList2= [Book{id=200, name='倚天屠龙记'}, Book{id=260, name='易经'}]

        // 5. 把map对象->json字符串
        HashMap<String, Book> bookMap = new HashMap<>();
        bookMap.put("k1", new Book(400, "易经"));
        bookMap.put("k2", new Book(400, "尚书"));
        String strBookMap = gson.toJson(bookMap);
        // strBookMap: {"k1":{"id":400,"name":"易经"},"k2":{"id":400,"name":"尚书"}}
        System.out.println("strBookMap: " + strBookMap);

        // 6. 把json字符串 -> map对象
        //      java.util.HashMap<java.lang.String, com.charlie.json.Book>
        HashMap<String, Book> bookMap2 = gson.fromJson(strBookMap, new TypeToken<HashMap<String, Book>>() {}.getType());
        // bookMap2: {k1=Book{id=400, name='易经'}, k2=Book{id=400, name='尚书'}}
        System.out.println("bookMap2: " + bookMap2);
    }
}
