/*
 * Course: Formal Language Theory
 * Assignment : FLT Assignment 1a
 * Name : Rahul Jamalapuram
 * CWID : A20303740
 * Program to simulate a DFSM
*/



/*--------------------README------------------
 *Compile the code using the command "javac FLTAssignment1a.java"
 * Run the program using the command "java FLTAssignment1a argument1 argument2" 
 * Note:
 * 1) If there is no final state, in the table please specify by giving an extra space
 * 2) give the same number of spaces in the table for the alphabets and the states.
 * 3) the extra alphabet in the string for example( aabbc) if the alphabets are ab are not handled.
 */

import java.util.*;
import java.io.*;

public class DFSMSpecification
{
	public static void main(String[] args) throws Exception {
	
	if(args.length != 2){
		System.out.println("Not Enough Arguments");
	
		System.exit(1);
	} else{	
		//Initialising the variables.
        	String[] alphabet,initial;
        	String[][] transition;
        	String[] finalStates;
        	String[] tableStates;
        	String inputString = new String();
        	String alp;
        	String currentInput;
        	List<String> transFunctionTable = new ArrayList<String>();
        	int flag=0;
    		
		//Reading the specified files
        	File DFSMspec = new File(args[0]);
       		Scanner in1 = new Scanner(DFSMspec);
        
        	int sizeOfDFSMspec,noOfColumns;
       
        	while (in1.hasNextLine()) {
            		String data = in1.nextLine();
            		transFunctionTable.add(data);
        	}
        
       		if(transFunctionTable.size()<2){
            	System.out.println("NO, Transition table is not given");
            	System.exit(1);
        	}
        	
		File inputstr = new File(args[1]);
        	Scanner in2 = new Scanner(inputstr);
        	while(in2.hasNextLine()){
            		inputString = in2.nextLine();
        	}
        
        	alp = transFunctionTable.get(0);
        	alphabet= alp.split(" ");
        
        	for(int i =0; i<alphabet.length;i++){
            		alphabet[i]=alphabet[i].trim();
        	}
        
        	alp = transFunctionTable.get(transFunctionTable.size() - 1);
        	finalStates=alp.split(" ");
        	for(int i =0; i<alphabet.length;i++){
            		alphabet[i]=alphabet[i].trim();
       	 	}

        	if(finalStates.length == 0){
            		System.out.println("NO, There is no final state and hence It will not accept any string"); 
            		System.exit(1);
        	} 
        	else {
            		sizeOfDFSMspec = transFunctionTable.size() - 2;
            		noOfColumns = alphabet.length;
            
           
            		transition = new String[sizeOfDFSMspec][noOfColumns];
            
            		for(int i=1; i <= transFunctionTable.size()-2;i++){
                		alp = transFunctionTable.get(i);
                		tableStates = alp.split(" ");
                		transition[i-1]=tableStates;
                
            		}
              
                //The Transitions start from here and curSt is the Current state which is one in the beginning as specified in the instructions
            	int curSt = 1;
              
            	while(inputString.length() > 0){
                	int rowVariable = curSt - 1;
                	currentInput = inputString.substring(0,1);
                	for(int j=0; j<alphabet.length; j++){
                    		if(alphabet[j].equals(currentInput)){
                        		curSt = Integer.parseInt(transition[rowVariable][j]);
                   		 }
               		 }
               		inputString = inputString.substring(1);
           	 }
            
            	for(int i=0;i<finalStates.length;i++){
                	if(curSt == Integer.parseInt(finalStates[i])){
                    		System.out.println("Yes");
                    		flag =1;
                    		break;
               		 }
           	 }
            
            	if(flag==0){
                	System.out.println("NO");
           	 }
        
       		}
	}	
	}
}
