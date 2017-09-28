#include <fstream>
#include <vector>
#include <string>
 
using namespace std;
 
vector<int> prefix_function(string s);
void KMPearch(vector<int> prefix, int len, int lenS);
 
int matchingCount = 0;
vector<int> entry;
 
int main() {
    ifstream reader("search2.in", ios_base::in);
    ofstream writer("search2.out", ios_base::trunc);
    string substring;
    getline(reader, substring); 
    string str;
    getline(reader, str);
    string integration = substring + "!" + str;
    vector<int> prefix(prefix_function(integration));
    KMPearch(prefix, substring.length(), str.length());
    writer << matchingCount << endl;
    for (vector<int>::iterator it = entry.begin(); it != entry.end(); it++)   {
        writer << *it << " ";
    }   
    writer.close();
    reader.close();
}
 
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
 
void KMPearch(vector<int> prefix, int lenSub, int lenStr) {
    for (int i = lenSub + 1; i < lenStr + lenSub + 1; i++) {
        if (prefix[i] == lenSub) {
            matchingCount++;
            entry.push_back(i - 2 * lenSub + 1);
        }
    }
}   
