package prependIfmissing;

import org.apache.commons.lang3.StringUtils;

/**
 * Chèn chuỗi sau vào đầu của chuỗi trc
 */
public class PrependIfMissing {
    public void prependIfmissing(String text,String input){
        System.out.println(StringUtils.prependIfMissing(text,input));
    }
}
