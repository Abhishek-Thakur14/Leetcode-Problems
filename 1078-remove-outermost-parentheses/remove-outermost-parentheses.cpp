
class Solution {
public:
    string removeOuterParentheses(string s) {
        string result = "";
        int openCount = 0; // Keeps track of the balance of parentheses
        
        for (char c : s) {
            if (c == '(') {
                if (openCount > 0) { // Add to result only if it's not an outer parenthesis
                    result += c;
                }
                openCount++;
            } else if (c == ')') {
                openCount--;
                if (openCount > 0) { // Add to result only if it's not an outer parenthesis
                    result += c;
                }
            }
        }
        
        return result;
    }
};