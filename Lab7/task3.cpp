#include <fstream>
#include <vector>
#include <climits>
#include <set>
 
using namespace std;
 
void dejkstra(int s, int vertexCount);
 
const long long INF = LLONG_MAX / 2;
 
vector <vector<pair<int, int>>> graph;
set<pair<int, int>> q;
vector<long long> dist;
 
int main() {    
    ios::sync_with_stdio(false);
    ifstream reader("pathbgep.in", ios_base::in);
    ofstream writer("pathbgep.out", ios_base::trunc);
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
        graph[to - 1].push_back(make_pair(from - 1, weightV));      
    }
    dist.resize(vertexCount);
    fill(dist.begin(), dist.end(), INF);
    dejkstra(0, vertexCount);
    for (int i = 0; i < vertexCount; i++) {
        writer << dist[i] << " ";
    }
    reader.close();
    writer.close();
}
 
void dejkstra(int s, int vertexCount) {
    dist[s] = 0;
    q.insert(make_pair(dist[s], s));
    while (!q.empty()) {
        int v = q.begin()->second;
        q.erase(q.begin());
 
        for (size_t j = 0; j < graph[v].size(); j++) {
            int to = graph[v][j].first,
                len = graph[v][j].second;
            if (dist[v] + len < dist[to]) {
                q.erase(make_pair(dist[to], to));
                dist[to] = dist[v] + len;               
                q.insert(make_pair(dist[to], to));
            }
        }
    }
}
