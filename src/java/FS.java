
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class FS {
    public static String fs() throws IOException {
        String line;
        int token;
        StringBuffer cmd = new StringBuffer("sudo fdisk -l ");
           
        Process p = Runtime.getRuntime().exec(cmd.toString());
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        
        StringBuffer sb = new StringBuffer("");
        StreamTokenizer st = new StreamTokenizer(br);
        st.eolIsSignificant(true);
        st.ordinaryChar(47);
        
        int lCounter = 1;
        int columnCounter = 1;
        int firstNumber = 0;
        
     
        while((token = st.nextToken()) != StreamTokenizer.TT_EOF) {
            
            System.out.println(st.toString());
            if(token == '/'){
                sb.append("/");
            }else if(token == StreamTokenizer.TT_WORD && lCounter > 0 && lCounter < 8) {
                sb.append(st.sval + " ");
            }else if(token == ':'){
                sb.append(":  ");
            }else if(token == ','){
                sb.append(", ");  
            }else if(token == '='){
                sb.append("= ");  
            }else if(token == '*'){
                sb.append("* ");  
            }else if(token == '('){
                sb.append("( ");  
            }else if(token == ')'){
                sb.append(") ");  
            }else if(firstNumber == 0 && token == StreamTokenizer.TT_NUMBER) {
                firstNumber++;
                sb.append((float)st.nval);
            }else if(firstNumber > 0 && token == StreamTokenizer.TT_NUMBER && lCounter > 0 && lCounter < 8) {
                sb.append((int)st.nval + " ");
            }else if(lCounter > 1 && token == StreamTokenizer.TT_EOL) {
                lCounter++;
                sb.append("<br />");
            }else lCounter++; 
            if(lCounter > 8 && token == StreamTokenizer.TT_WORD) {
                sb.append(" " + st.sval + " ");
            }else if(lCounter > 8 && token == StreamTokenizer.TT_NUMBER) {
                sb.append(" " + st.nval + " ");
            }else if(lCounter > 8 && token == StreamTokenizer.TT_EOL) {
                lCounter++;
                sb.append("<br />");
            }
        }
      
        return sb.toString();
    }
}
