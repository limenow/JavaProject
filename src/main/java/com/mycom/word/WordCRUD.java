package com.mycom.word;

import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;

public class WordCRUD implements ICRUD{
    LinkedList<Word> list;
    Scanner scanner;

    WordCRUD(Scanner scanner) {
        list = new LinkedList<>();
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
    public void updateWord() {
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = scanner.next();
        LinkedList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 수정할 번호 선택 : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("=> 뜻 입력 : ");
        String meaning = scanner.nextLine();
        Word word = list.get(idlist.get(id-1));
        word.setMeaning(meaning);
        System.out.println("단어 수정이 성공적으로 되었습니다!!");
    }
    @Override
    public void deleteWord() {
        System.out.print("=> 삭제할 단어 검색 : ");
        String keyword = scanner.next();
        LinkedList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 삭제할 번호 선택 : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("=> 정말로 삭제하실래요?(Y/n) ");
        String ans = scanner.next();
        if(ans.equalsIgnoreCase("y")) {
            list.remove((int)idlist.get(id - 1));
            System.out.println("선택한 단어 삭제 완료 !!!");
        } else System.out.println("삭제 취소!!!");

    }
    public void searchLevel() {
        System.out.print("=> 원하는 레벨은? (1~3) ");
        int level = scanner.nextInt();
        listAll(level);
    }
    public void searchWord() {
        System.out.print("=> 원하는 단어는? ");
        String word = scanner.next();
        listAll(word);
    }
    public void listAll() {
        System.out.println("--------------------------------");
        for(int i = 0; i < list.size(); i++) {
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------------");
    }

    public LinkedList<Integer> listAll(String keyword){
        LinkedList<Integer> idlist = new LinkedList<>();
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
    @Override
    public void loadFile() {
        try {
            BufferedReader myBufferedReader = new BufferedReader(new FileReader("Dictionary.txt"));
            String line;
            int count = 0;

            while(true) {
                line = myBufferedReader.readLine();
                if(line == null) break;

                String[] data = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                count++;
            }
            myBufferedReader.close();
            System.out.println("==> " + count + "개 로딩 완료!!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void saveFile() {
        try {
            PrintWriter myPrintWriter = new PrintWriter("Dictionary.txt");
            for(Word word : list) {
                myPrintWriter.println(word.getLevel() + "|" + word.getWord() + "|" + word.getMeaning());
            }
            myPrintWriter.close();
            System.out.println("==> 데이터 저장 완료 !!!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
