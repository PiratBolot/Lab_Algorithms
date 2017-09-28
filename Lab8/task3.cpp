#include <fstream>
#include <climits>
#include <vector>
#include <string>
using namespace std;
 
vector<int> prefix_function(string s) {
    int n = (int)s.length();
    vector<int> pi(n);
    for (int i = 1; i < n; i++) {
        int j = pi[i - 1];
        while (j > 0 && s[i] != s[j])
            j = pi[j - 1];
        if (s[i] == s[j])  ++j;
        pi[i] = j;
    }
    return pi;
}
 
int main() {
    ifstream reader("prefix.in", ios_base::in);
    ofstream writer("prefix.out", ios_base::trunc);
    string str;
    getline(reader, str);
    vector<int> result(prefix_function(str));
    for (vector<int>::iterator it = result.begin(); it != result.end(); it++) {
        writer << *it << " ";
    }
    reader.close();
    writer.close(); 
    return 0;
}
 
    
