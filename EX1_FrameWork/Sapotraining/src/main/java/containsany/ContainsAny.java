package containsany;

import org.apache.commons.lang3.StringUtils;

/**
 * Kiểm tra Chuỗi input có bất kì kí tự nào giống chuỗi text
 */
public class ContainsAny {
    public void containsAny(String text ,String input){
        System.out.println(StringUtils.containsAny(text,input));
    }
}
