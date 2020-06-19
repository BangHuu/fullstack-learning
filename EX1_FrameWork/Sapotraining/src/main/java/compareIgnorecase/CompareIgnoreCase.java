package compareIgnorecase;

import org.apache.commons.lang3.StringUtils;

/**
 * So sánh 2 chuỗi theo từ điển , bỏ qua sự khác nhau về kiểu chữ
 */
public class CompareIgnoreCase {
    public void compareignorecase(String text, String input){
        System.out.println(StringUtils.compareIgnoreCase(text,input));
    }
}
