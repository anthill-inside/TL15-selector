package telran.regex.selector.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhotoSelector {
    //stringBuilder - check ->append(),->split()
    //TODO * Pattern Compile() Matcher() & Matcher Reset() Find()
    public static String[] select(String[] input, String regex){
        List<String> result = new ArrayList<>();
        for(String str: input){
            if(str.matches(regex)){
                result.add(str);
            }
        }
        return result.toArray(new String[0]);
    }
    public static String[] selectSB(String[] input, String regex){
        StringBuilder sb = new StringBuilder();
        for(String str: input){
            if(str.matches(regex)){
                sb.append(str);
                sb.append('\n');
            }
        }
        return Pattern.compile("\n").split(sb,0);
    }
    public static String[] selectMatcher(String[] input, String regex){
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        for(String str: input){
            Matcher matcher = pattern.matcher(str);
            if(matcher.find()){
                result.add(str);
            }
        }
        return result.toArray(new String[0]);
    }
}
