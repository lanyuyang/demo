package com.iflytek.demo.dto;

/**
 * @ Author     ：yylan@iflytek.com
 * @ Date       ：Created in 10:28 2019/1/7
 * @ Description：${description}
 * @ Modified By：
 * @Version: 1.0
 */
public class Book {
        private String name;
        private int price;
        public Book(String name, int price) {
            this.name = name;
            this.price = price;
        }
        public String getName() {
            return name;
        }
        public int getPrice() {
            return price;
        }
        @Override
        public boolean equals(final Object obj) {
            if (obj == null) {
                return false;
            }
            final Book book = (Book) obj;
            if (this == book) {
                return true;
            } else {
                return (this.name.equals(book.name) && this.price == book.price);
            }
        }
        @Override
        public int hashCode() {
            int hashno = 7;
            hashno = 13 * hashno + (name == null ? 0 : name.hashCode());
            return hashno;
        }
    }

