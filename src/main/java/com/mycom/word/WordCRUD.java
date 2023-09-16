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
        String word = scanner.nextLine();

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
//        System.out.println("=> 수정할 단어 검색: ");
//        String word = scanner.nextLine();
//        return 0;
    }
    public void updateWord(){

    }
    @Override
    public int delete(Object obj) {
        return 0;
    }
    public void deleteWord(){

    }
    @Override
    public void selectOne(int id) {

    }
    public void listAll() {
        System.out.println("--------------------------------");
        for(int i = 0; i < list.size(); i++) {
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------------");
    }

    public ArrayList<Integer> listAll(String keyword){
        ArrayList<Integer> idlist = new ArrayList<>();
        int j = 0;
        System.out.println("--------------------------------");
        for(int i = 0; i < list.size(); i++) {
            String word = list.get(i).getWord();
            if(!word.contains(keyword)) continue;
            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());
            idlist.add(i);
            j++;
        }
        System.out.println("--------------------------------");
        return idlist;
    }
}
    public void listAll(int level) {
        int j = 0;
        System.out.println("--------------------------------");
        for(int i = 0; i < list.size(); i++) {
            int inputLevel = list.get(i).getLevel();
            if(inputLevel != level) continue;
            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());
            j++;
        }
        System.out.println("--------------------------------");
    }
