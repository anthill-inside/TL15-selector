package telran.regex.selector.tools;

import java.util.ArrayList;
import java.util.List;

public class PhotoSelector {
    //stringBuilder - check ->append(),->split()
    //TODO * Pattern Compile() Matcher() & Matcher Reset() Find()
    public static String[] select(String[] input, String mask){
        List<String> result = new ArrayList<>();
        for(String str: input){
            if(str.matches(mask)){
                result.add(str);
            }
        }
        return result.toArray(new String[0]);
    }
}
