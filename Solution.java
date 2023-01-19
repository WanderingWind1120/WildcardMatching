package org.example;

public class Solution {
    Boolean dp[][];
    public boolean isMatch(String s, String p) {
        int m = p.length(); // Gán m là độ dài của String vs vai trò làm pattern
        int n = s.length(); // Gán n là độ dài của String vs vai trò cần kiểm tra

        dp = new Boolean[m][n]; // dp là 2d array vs số array con là m và số biến boolean trong mỗi array con là n

        return is_match(m-1,n-1,p,s);// Chỗ này phải dùng m-1 và n-1 vì trong base case chúng ta đang xét
        // dựa trên m-1 và n-1 tức luôn phải thêm 1 thì mới ra đúng length của 2 String
        // Và do trong id_match method có nhiều statement tìm kí tự theo index chạy tới cuối cùng
        // và do độ dài của String luôn lớn hơn index lớn nhất 1 đơn vị
        // Nên để không mất công thêm -1 vào toàn bộ các index thì ta pass in tham số -1 đơn vị luôn
        // còn lại không ảnh hưởng tới recursive
    }

    private boolean is_match(int m,int n,String p,String s){

        //Base Case
        // Nếu độ dài của String p và s cùng là 0 thì mathching return True, độ dài của p s là m+1 và n+1 khi pass in
        // tham số vào
        if (m<0 && n<0) return true;

        // Nếu n+1 >=1 và m = 0 tức String s có character còn pattern rỗng thì không match return false
        if (m<0 && n>=0) return false;

        // Nết Pattern có tồn tại phần tử còn String test rỗng thì chỉ true nếu pattern full of "*" vì
        // chấp nhận sequence of any characters hoặc rỗng
        if (n<0 && m>=0) {

            for (int i=0;i<=m;i++){
                if (p.charAt(i) != '*') return false;
            }
            return true;
        }
        // 3 cái if statement bên trên chính là base case khi recursive chạm đến

        //Tránh tính lại các trường hợp đã calculate rồi nếu đã tính toán ra rồi thì return ra luôn giá trị đó
        // Giảm complexing run time
        if (dp[m][n] != null) return dp[m][n];

        // Nếu kí tự ở vị trí m trong pattern bằng kí tự ở vị trí n trong String test hoặc tại vị trí m là
        if (p.charAt(m) == s.charAt(n) || p.charAt(m) == '?'){
            return dp[m][n] = is_match(m-1,n-1,p,s);
        }

        // Nếu trong pattern có kí tự "*" tại vị trí m thì xét
        // recursive 2 case nếu loại bỏ * thì phần còn lại có khớp không
        // hoặc * có tương đương vs phần từ đẳng trước + phần tử hiện tại trong String hay không
        if (p.charAt(m) == '*'){
            return dp[m][n] = is_match(m-1,n,p,s) || is_match(m,n-1,p,s);
        }
        // Nếu trường hợp kí tự tại điểm m và n của pattern vs string không bằng nhau sau khi đã xét 2 case recursive
        // trên thì trả luôn false
        return false;

    }
}
