package DrumsEvo;

import java.util.ArrayList;

/**
 * Created by DmitryRukavchuk on 22.07.2016.
 */
public class PulseMask {
    private int[] pulseMask;


    public PulseMask(int pulseNum, int length) {
        pulseMask = new int[length];
        generateCommonMask(pulseNum,length);
    }


    //offset pre-generated mask "101001" -> "110100"
    public void createOffsetMask(int steps){
        for (int j = 0; j < steps; j++) {
            int save;
            int write = pulseMask[pulseMask.length - 1];
            for (int i = 0; i < pulseMask.length; i++){
                save = pulseMask[i];
                pulseMask[i] = write;
                write = save;
            }
        }
    }

    //invert pre-generated mask "101001" -> "010110"
    public void createInvertMask(){
        for(int i = 0; i < pulseMask.length; i++){
            if(pulseMask[i] == 0) pulseMask[i] = 1;
            else pulseMask[i] = 0;
        }
    }

    public int[] generateCommonMask(int pulseNum, int length){
        int i;
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        //create initial "string" of Bjorklund pulse mask: "1|1|1|0|0|0|0|0"
        for(i = 0; i<pulseNum; i++){
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(1);
            matrix.add(temp);
        }
        for(i = pulseNum; i < length; i++){
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(0);
            matrix.add(temp);
        }

        //Array-list magic based on Bjorklund algorithm, which transforms initial "string" into pulse mask:
        // "1|1|1|0|0|0|0|0" -> "10|10|10|0|0" -> "100|100|10" -> "10010|100" -> "10010100"

        int div = pulseNum;
        while(matrix.size()>1){
            for(i = 0; i<div;i++){
                if(matrix.size() == div) break;
                ArrayList<Integer> tempFrom = matrix.get(matrix.size()-1);
                ArrayList<Integer> tempTo = matrix.get(i);
                tempTo.addAll(tempFrom);
                matrix.remove(i);
                matrix.add(i, tempTo);
                matrix.remove(matrix.size() - 1);
            }
            if(div == matrix.size()) div = matrix.size()/2;
            else if(matrix.size() / div < 2) div = matrix.size() - div;
        }

        //Convert from array-list to int[] variable
        ArrayList<Integer> temp = matrix.get(0);
        int[] tempMask = new int[temp.size()];
        for (int j = 0; j < temp.size(); j++) {
            pulseMask[j] = temp.get(j);
            tempMask[j] = temp.get(j);
        }

        return tempMask;
    }

    // TODO Solve Tom1 and Crash composition principles
    public void createFillingMask(){

    }

    public int[] getPulseMask() {
        return pulseMask;
    }
}
