package funclang;

import java.util.ArrayList;

public class bashemulator {


    public int counter;

    public ArrayList<String> history = new ArrayList<>();

    public bashemulator() {
        counter = 0;
    }

    public void resetCounter(){
        counter = 0;
    }

    public String getNewLast(){
        counter++;

        if (counter == 0) counter = 1;
        if (history.size() < counter) counter = history.size();
        return history.get(history.size()-counter);
    }

    public String getOldLast(){
        counter--;
        if (counter <= 0) {
            counter = 0;
            return "";
        }
        return history.get(history.size()-counter);
    }

    public String getCurrent(){

        if (counter==0) return "";

        return history.get(history.size()-counter);
    }

    public void addToHistory(String s){
        s = s.replaceAll("\t"," ");
        s = this.eatSpaces(s);
        history.add(s);
    }

    public String eatSpaces(String s){
        Boolean spaceFound = false;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i)==' '){
                if (spaceFound){
                    if (i < s.length()-1)
                    return eatSpaces(s.substring(0, i) + s.substring(i+1));
                    else return s.substring(0, i);
                }else spaceFound=true;
            }
            else spaceFound = false;

        }

        return s;
    }

}
