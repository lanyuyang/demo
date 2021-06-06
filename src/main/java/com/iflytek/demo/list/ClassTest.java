package com.iflytek.demo.list;

import com.iflytek.demo.dto.Book;

import java.util.*;

/**
 * @ Author     ：yylan
 * @ Date       ：Created in 18:03 2019/4/16
 */
public class ClassTest {
    public static void main(String[] args) {
        // 按类的属性去重并进行排序
        removeDuplicateTest();
    }
    public static void removeDuplicateTest(){
        List<Book> list = new ArrayList<Book>();
        {
            list.add(new Book("Core Java", 200));
            list.add(new Book("Core Java", 200));
            list.add(new Book("Learning Freemarker", 150));
            list.add(new Book("Spring MVC", 300));
            list.add(new Book("Spring MVC", 300));
        }

        List<Book> books = removeDuplicateOrder(list);
        for (Book book : books){
            System.out.println(book.getName());
        }
    }

    /**
     * 去重 并排序
     * @param deviceInfos
     * @return
     */
    private static List<Book> removeDuplicateOrder(List<Book> deviceInfos) {
        Set<Book> set = new TreeSet<Book>(new Comparator<Book>() {
            public int compare(Book bookA, Book bookB) {
                // 字符串则按照asicc码升序排列
                return bookA.getName().compareTo(bookB.getName());
            }
        });
        set.addAll(deviceInfos);
        return new ArrayList<Book>(set);
    }
}
