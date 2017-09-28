#include <fstream>
#include <vector>
#include <climits>
#include <set>
 
using namespace std;
 
void dejkstra(int s, int vertexCount);
 
const long long INF = LLONG_MAX / 2;
 
vector <vector<pair<int, int>>> graph;
set<pair<int, int>> q;
vector<vector<long long>> dist;
 
int main() {    
    ios::sync_with_stdio(false);
    ifstream reader("pathsg.in", ios_base::in);
    ofstream writer("pathsg.out", ios_base::trunc);
    int vertexCount, edgeCount;
    reader >> vertexCount >> edgeCount;
    graph.resize(vertexCount);  
    for (int i = 0; i < vertexCount; ++i) {
        graph[i].reserve(10);
    }
    for (int i = 0; i < edgeCount; i++) {        
        int from, to, weightV;
        reader >> from >> to >> weightV;
        graph[from - 1].push_back(make_pair(to - 1, weightV));              
    }
    dist.resize(vertexCount);
    for (int i = 0; i < vertexCount; i++)    {
        dist[i].resize(vertexCount);
    }
    for (int i = 0; i < vertexCount; i++) {
        fill(dist[i].begin(), dist[i].end(), INF);
        dejkstra(i, vertexCount);
    }   
    for (int i = 0; i < vertexCount; i++) {
        for (int j = 0; j < vertexCount; j++) {
            writer << dist[i][j] << " ";
        }
        writer << endl;
    }
    reader.close();
    writer.close();
}
 
void dejkstra(int s, int vertexCount) {
    dist[s][s] = 0;
    q.insert(make_pair(dist[s][s], s));
    while (!q.empty()) {
        int v = q.begin()->second;
        q.erase(q.begin());
 
        for (size_t j = 0; j < graph[v].size(); j++) {
            int to = graph[v][j].first,
                len = graph[v][j].second;
            if (dist[s][v] + len < dist[s][to]) {
                q.erase(make_pair(dist[s][to], to));
                dist[s][to] = dist[s][v] + len;             
                q.insert(make_pair(dist[s][to], to));
            }
        }
    }
}
