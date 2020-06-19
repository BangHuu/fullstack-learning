package countmatches;

import org.apache.commons.lang3.StringUtils;

/**
 * Đếm số lần suất hiện chuỗi con trong chuỗi trc
 */
public class CountMatches {
    public void countmatches(String text,String input){
        System.out.println(StringUtils.countMatches(text,input));
    }
}
