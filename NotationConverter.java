import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotationConverter {
    public int toArabic(String romanNotation) throws InvalidValueException{
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);

        int arabic = 0;

        Pattern pattern = Pattern.compile("[^MDCLXVI]");
        Matcher matcher = pattern.matcher(romanNotation);
        if(matcher.find()){
            throw new InvalidValueException("String must contain only valid roman numerals: [M, D, C, L, X, V, I]");
        }

        romanNotation = romanNotation.concat(" ");
        for (int i = 0; i < romanNotation.length()-1; i++) {
            char a = romanNotation.charAt(i);
            char b = romanNotation.charAt(i + 1);
            boolean dis = (a == 'C' && b == 'M') || (a == 'C' && b == 'D') ||
                          (a == 'X' && b == 'C') || (a == 'X' && b == 'L') ||
                          (a == 'I' && b == 'X') || (a == 'I' && b == 'V');
            if(dis){
                arabic -= map.get(a);
            } else{
                arabic += map.get(a);
            }
        }
        return arabic;
    }
}
