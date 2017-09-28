#include <fstream>
#include <climits>
#include <vector>
using namespace std;
 
const long long INF = LLONG_MAX / 2;
 
int main() {
    ifstream reader("pathmgep.in", ios_base::in);
    ofstream writer("pathmgep.out", ios_base::trunc);
    int vertexCount, begin, end;
    reader >> vertexCount;
    reader >> begin;
    reader >> end;
    begin--;
    end--;
    vector <vector<pair<int, int>>> graph;
    graph.resize(vertexCount);
 
    for (int i = 0; i < vertexCount; i++) {
        for (int j = 0; j < vertexCount; j++) {
            int k;
            reader >> k;
            if (k != -1 && i != j) {
                graph[i].push_back(make_pair(j, k));
            }
        }
    }
    vector<long long> d;
    d.resize(vertexCount);
    fill(d.begin(), d.end(), INF);
    d[begin] = 0;
 
    vector<char> used(vertexCount);
    fill(used.begin(), used.end(), false);
 
    for (int i = 0; i < vertexCount; i++) {
        int v = -1;
        for (int j = 0; j < vertexCount; j++) {
            if (!used[j] && (v == -1 || d[j] < d[v])) {
                v = j;
            }
        }
        if (d[v] == INF) {
            break;
        }
        used[v] = true;
        for (size_t j = 0; j < graph[v].size(); j++) {
            int to = graph[v][j].first,
                len = graph[v][j].second;
            if (d[v] + len < d[to]) {
                d[to] = d[v] + len;
            }
        }
 
    }
 
    if (d[end] != INF) {
        writer << d[end];
    }
    else {
        writer << -1;
    }
    reader.close();
    writer.close();
    return 0;
}
