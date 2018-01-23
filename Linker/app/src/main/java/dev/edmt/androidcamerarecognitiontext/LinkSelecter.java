package dev.edmt.androidcamerarecognitiontext;

import java.util.ArrayList;

/**
 * Created by atoja on 23.01.2018.
 */

public class LinkSelecter {
    private ArrayList<String> linkList = new ArrayList<String>();
    private ArrayList<String> correctLinks = new ArrayList<String>();
    private int listSize = 16;
    private int listSensitivity = 2;
    private int resultSize = 4;

    public LinkSelecter(){

    }

    public LinkSelecter(int listSize, int listSensitivity, int resultSize){
        this.listSize = listSize;
        this.listSensitivity = listSensitivity;
        this.resultSize = resultSize;

    }

    public void setSize(int listSize){

        this.listSize = listSize;
    }

    public void setSensitivity(int listSensitivity){

        this.listSensitivity = listSensitivity;
    }

    public void setResultSize(int resultSize){
        this.resultSize = resultSize;
    }

    public int getResultSize(){
        return resultSize;
    }

    public void addLink(String link){
        if(linkList.size()>=listSize){
            linkList.remove(0);
            linkList.add(link);
        } else {
            linkList.add(link);
        }
    }

    private void selectCorrectLinks(){
        ArrayList<String> linkListCopy = new ArrayList<>(linkList);
        while(!linkListCopy.isEmpty()){
            String link = linkListCopy.get(0);
            int count = 0;
            int index = 0;
            while(index<linkListCopy.size()){
                if(linkListCopy.get(index).equals(link)){
                    count++;
                    linkListCopy.remove(index);
                }  else {
                    index++;
                }
            }
            boolean flag = true;
            for(int i=0;i<correctLinks.size();i++){
                if(correctLinks.get(i).equals(link)) flag = false;
            }
            if(count>=listSensitivity&&flag){
                if(correctLinks.size()>=resultSize){
                    correctLinks.remove(0);
                    correctLinks.add(link);
                } else {
                    correctLinks.add(link);
                }
            }
        }
    }

    public ArrayList<String> getCorrectLinks(){
        selectCorrectLinks();
        ArrayList<String> linkListCopy = new ArrayList<>(correctLinks);
        return linkListCopy;
    }

}
