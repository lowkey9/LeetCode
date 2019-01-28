package problems;

public class ATOI {
    public int atoi(String str) {
        if(str == null || str.length() == 0)
            return 0;
        str = str.trim();
        if(str.length() == 0)
            return 0;
        int i = 0, cnt = 0, res = 0;
        boolean neg = false;
        // do not forget this part and it should be else if
        if(str.charAt(i) == '+')
            i++;
        else if(str.charAt(i) == '-') {
            neg = true;
            i++;
        }
        while(i < str.length() && Character.isDigit(str.charAt(i))) {
            int digit = str.charAt(i) - '0';
            int tmp = res;
            res = res * 10 + digit;
            // skip validate 000 000 000 until reach 214 748 364
            if(res != 0)
                cnt++;
            // overflow, but I don't understand this condition
            if(cnt > 9 && (res < 0 || (res - tmp) % 10 != digit)) {
                return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            i++;
        }
        return neg ? -res : res;
    }
}
