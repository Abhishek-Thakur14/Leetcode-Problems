class Solution {
    public boolean sumGame(String num) {
        int length = num.length();
      
        // Count question marks and sum of digits for the left half
        int leftQuestionMarks = 0;
        int leftSum = 0;
        for (int i = 0; i < length / 2; i++) {
            if (num.charAt(i) == '?') {
                leftQuestionMarks++;
            } else {
                leftSum += num.charAt(i) - '0';
            }
        }
      
        // Count question marks and sum of digits for the right half
        int rightQuestionMarks = 0;
        int rightSum = 0;
        for (int i = length / 2; i < length; i++) {
            if (num.charAt(i) == '?') {
                rightQuestionMarks++;
            } else {
                rightSum += num.charAt(i) - '0';
            }
        }
      
        // Alice wins if:
        // 1. Total question marks is odd (Alice gets the last move)
        // 2. The difference in sums cannot be balanced by the question marks
        //    (Bob needs exactly 9 * (rightQuestionMarks - leftQuestionMarks) / 2 
        //     to balance the sums when question marks are evenly distributed)
        int totalQuestionMarks = leftQuestionMarks + rightQuestionMarks;
        int sumDifference = leftSum - rightSum;
        int questionMarkDifference = rightQuestionMarks - leftQuestionMarks;
      
        return totalQuestionMarks % 2 == 1 || sumDifference != 9 * questionMarkDifference / 2;
    }
}
