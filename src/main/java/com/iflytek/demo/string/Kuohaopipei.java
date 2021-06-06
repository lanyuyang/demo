//package com.iflytek.demo.string;
//
//
//import com.fasterxml.jackson.core.JsonParser;
//import org.antlr.v4.runtime.ANTLRInputStream;
//import org.antlr.v4.runtime.CommonTokenStream;
//
///**
// * @ Author     ：yylan
// * @ Date       ：Created in 15:38 2019/5/10
// */
//public class Kuohaopipei {
//    public static void main(String[] args) {
//
//    }
//    public static void run(String expr)throws Exception{
//        ANTLRInputStream in = new ANTLRInputStream(expr);
//        JsonLexer lexer = new JsonLexer(in);
//        CommonTokenStream tokens = new CommonTokenStream(lexer);
//        JsonParser exprParser = new JsonParser(tokens);
//        ExprContext exprContext = exprParser.expr();
//        System.out.println(exprContext.getText());
//        List<Bracket_bodyContext> list = exprContext.bracket_body();
//        System.out.println(list.size());
//        System.out.println(list.get(2).getText());
//    }
//
//}
