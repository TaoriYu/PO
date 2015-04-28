/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.StreamTokenizer;

/**
 *
 * @author root
 */
public class InfoBuilder {
    public static String net() throws IOException {
        String line;
        int token;
        StringBuffer cmd = new StringBuffer("ifconfig ");
           
        Process p = Runtime.getRuntime().exec(cmd.toString());
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
         
        StringBuffer sb = new StringBuffer("");
        StreamTokenizer st = new StreamTokenizer(br);
        st.eolIsSignificant(true);
        st.ordinaryChar(32);
        st.ordinaryChar(47);

        
        int lCounter = 1;
        
     
        while((token = st.nextToken()) != StreamTokenizer.TT_EOF) {
            
            System.out.println(st.toString());
            if(token == '/'){
                sb.append("/");          
            }else if(lCounter == 1 && token == StreamTokenizer.TT_WORD) {
                sb.append(st.sval + "<br />");
            }/*else if(lCounter == 10 && token == StreamTokenizer.TT_WORD) {
                sb.append(st.sval + "<br />");
            }*/else if(token == StreamTokenizer.TT_WORD) {
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
            }else if(token == '.'){
                sb.append(".");  
            }else if(token == StreamTokenizer.TT_NUMBER) {
                sb.append((int)st.nval);
            }else if(lCounter > 1 && token == StreamTokenizer.TT_EOL) {
                lCounter++;
                sb.append("<br />");
            }else lCounter++; 
        }

        return sb.toString();
    }

    
    
}
