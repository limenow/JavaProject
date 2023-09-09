package com.mycom.word;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner scanner;

    WordCRUD(Scanner scanner) {
        list = new ArrayList<>();
        this.scanner = scanner;
    }
    @Override
    public Object add() {
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = scanner.nextInt();
        String word = scanner.next();

        System.out.println("뜻 입력 : ");
        String meaning = scanner.nextLine();
        return new Word(0, level, word, meaning);
    }
    public void addWord(){
        Word one = (Word)add();
        list.add(one);
        System.out.println("\n새 단어가 단어장에 추가되었습니다!!! \n");
    }
    @Override
    public int update(Object obj) {
        return 0;
    }
    public void updateWord(){}
    @Override
    public int delete(Object obj) {
        return 0;
    }
    public void deleteWord(){}
    @Override
    public void selectOne(int id) {

    }
    public void listAll(){
        System.out.println("--------------------------------");
        for(int i = 0; i < list.size(); i++) {
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------------");
    }
}
