package cn.hlsd.websys;

import com.sun.xml.internal.fastinfoset.util.CharArray;

/**
 * @author Ean
 * @version 0.1.0
 * @Description
 * @create 2020-08-19 16:38
 * @since 0.1.0
 **/
public class test {

    public static void main(String[] args) {
        boolean flag=true;
        String u1 = "/usexc/loginddd/d";
        String patten = "/*/login*";
        flag=boolPaten(u1,patten);
        System.out.println(flag);
    }

    private static boolean boolPaten(String url, String patten) {
        int index = 0;
        char[] arr = patten.toCharArray();//url
        for (int i = 0; i < patten.length(); i++) {
            if (arr[i] == '*') {
                if (url.charAt(index)=='/'){
                    index+=1;
                    continue;
                }
                while (index<url.length()&&url.charAt(index)!='/'){
                    index+=1;
                }
            } else {
                if (arr[i] == url.charAt(index)) {
                    index += 1;
                    continue;
                } else {
                    return false;
                }
            }
        }
        if (url.length()-1>index){
            return false;
        }
        return true;
    }
}
