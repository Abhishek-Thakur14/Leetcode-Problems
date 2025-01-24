class Solution {
    public int strStr(String haystack, String needle) {
 
       for (int i = 0; i <= haystack.length() - needle.length(); i++) {
          int k;
          // Check if needle matches the substring of haystack starting at index i
          for (k = 0; k < needle.length(); k++) {
                if (haystack.charAt(i + k) != needle.charAt(k)) {
                    break; // Exit inner loop if characters don't match
                }
          }
          // If all characters of needle matched, return the starting index
          if (k == needle.length()) {
             return i;
          }
        }
        // If no match is found, return -1
        return -1;

    }
    
}