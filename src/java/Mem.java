
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
public class Mem {
    public static String mem() throws IOException {
        String line;
        int token;
        StringBuffer cmd = new StringBuffer("free -m ");
           
        Process p = Runtime.getRuntime().exec(cmd.toString());
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        
        StringBuffer sb = new StringBuffer("");
        StreamTokenizer st = new StreamTokenizer(br);
        st.eolIsSignificant(true);
        
        st.ordinaryChar(47);
        st.ordinaryChar(44);
        
        int linesCount = 1;
        int columnCount = 1;
     
        sb.append("<table><tr><td></td>");
        
        while((token = st.nextToken()) != StreamTokenizer.TT_EOF) {
            
            System.out.println(st.toString());
            if(linesCount == 1 && token == StreamTokenizer.TT_WORD) {
                sb.append("<td>" + " " + st.sval + " " + "</td>");
                columnCount++;
            }else if(linesCount == 3 && token == '-') {
                sb.append("<td> -");
            }else if(linesCount == 3 && token == '/'){
                sb.append("/");
            }else if(linesCount == 3 && token == '+'){
                sb.append("+");
            }else if(linesCount == 3 && token == StreamTokenizer.TT_WORD){
                sb.append(st.sval);
            }else if(token == ':') {
                sb.append(":</td>");
                columnCount++;
            }else if(linesCount == 3 && columnCount == 2){
                sb.append("<td> </td>");
                columnCount++;
            }else if(token == StreamTokenizer.TT_NUMBER) {
                sb.append("<td>" + (int)st.nval + "</td>");
                columnCount++;
            }else if(token == StreamTokenizer.TT_EOL) {
                sb.append("</tr>");
                linesCount++;
                sb.append("<tr>");
                columnCount = 1;
            }else if(token == StreamTokenizer.TT_WORD) {
                sb.append("<td>" + " " + st.sval + " ");
            }
        }
        sb.append("</table>");

        return sb.toString();
    }
}
