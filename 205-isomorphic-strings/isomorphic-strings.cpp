class Solution {
public:
    bool isIsomorphic(string s, string t) {
        if (s.length() != t.length()) return false;

        // Mapping for characters in s -> t and t -> s
        unordered_map<char, char> mapST, mapTS;

        for (int i = 0; i < s.length(); ++i) {
            char c1 = s[i];
            char c2 = t[i];

            // Check if the mapping exists and is consistent
            if ((mapST.count(c1) && mapST[c1] != c2) || 
                (mapTS.count(c2) && mapTS[c2] != c1)) {
                return false;
            }

            // Add the mapping
            mapST[c1] = c2;
            mapTS[c2] = c1;
        }

        return true;
    }
};