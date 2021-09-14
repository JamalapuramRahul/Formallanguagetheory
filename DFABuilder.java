import java.io.*;
import java.util.*;

public class DFABuilder{
    
    public static int backStates( int[][] transTable, List<String> alphabets,String curss, int indOfCurAlp){
        String curAlp = "";
        int curSt = 0;
        while(curss.length()>0){
            curAlp = curss.substring(0,1);
            curSt = transTable[curSt][alphabets.indexOf(curAlp)]-1;
            curss = curss.substring(1);
        }
        return curSt;
    }
    
    
    public static void main(String args[]) throws Exception {
        if(args.length != 0){
            
        } else{
            String inputString = new String();
            List<String> alphabets = new ArrayList<String>();
            String alphabet;
            int curAlpPoint = 0;
		    int presentSt = 0;
		    String currentAlp = "";
		    String currentSubstring = "";
		    int difState;
            
            File inputFile = new File("/uploads/inputString.txt");
            Scanner in = new Scanner(inputFile);
            
            File outputFile= new File("/myfiles/output.txt");
            outputFile.createNewFile();
            FileWriter fwriter = new FileWriter(outputFile);
            
            while(in.hasNextLine()){
                inputString = in.nextLine();
            }
        
            String currentAlphabet;
            for(int i=0;i<inputString.length();i++){
                currentAlphabet = Character.toString(inputString.charAt(i));
                if(currentAlphabet.equals(" ")){
                    System.out.println("Not a correct string, has spaces in between");
                    System.exit(1);
                } else {
                    if(!alphabets.contains(currentAlphabet)){
                        alphabets.add(currentAlphabet);
                    }
                }
            }
            
           
            Collections.sort(alphabets);
            for(int i=0;i<alphabets.size();i++){
                fwriter.write(alphabets.get(i)+ " ");
            }
            fwriter.write("\n");
            
            fwriter.flush();
            int[][] transTable = new int[inputString.length()+1][alphabets.size()];
            int lenOfInpStr = inputString.length();
            
            currentAlp = inputString.substring(curAlpPoint,curAlpPoint+1);
            for(int i=0; i<transTable[i].length;i++){
                if(i==alphabets.indexOf(currentAlp)){
                    transTable[0][i]=2;
                }else
                transTable[0][i]=1;
            }
            curAlpPoint++;
            presentSt++;
            for(int i=0;i< transTable[i].length;i++){
                transTable[lenOfInpStr][i]=lenOfInpStr+1;
            }
            

            for(int i=1;i<inputString.length();i++){
                currentAlp = inputString.substring(curAlpPoint,curAlpPoint+1);
                currentSubstring = inputString.substring(1,presentSt);
                backState = backStates(transTable, alphabets, currentSubstring, alphabets.indexOf(currentAlp));
                for(int j=0;j<transTable[i].length;j++){
                    transTable[presentSt][j]=transTable[backState][j];
                }
                transTable[presentSt][alphabets.indexOf(currentAlp)]=presentSt+2;
                curAlpPoint++;
                presentSt++;
                System.out.println(currentSubstring);
            }
            
        
            for(int i=0;i<lenOfInpStr+1;i++){
			    for(int j=0;j<alphabets.size();j++){
				    fwriter.write(transTable[i][j]+" ");
			    }
		    fwriter.write("\n");
		    }

		
		    fwriter.write(Integer.toString(lenOfInpStr+1));
		    fwriter.flush();
            }
        }
    }