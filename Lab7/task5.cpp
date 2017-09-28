#include <fstream>
#include <vector>
#include <climits>
#include <algorithm>
using namespace std;
 
ifstream reader("negcycle.in", ios_base::in);
ofstream writer("negcycle.out", ios_base::trunc);
 
void solve(int vertexCount, int edgeCount);
 
struct edge {
    int a, b, cost;
 
    edge(int from, int to, int weight) {
        a = from;
        b = to;
        cost = weight;
    }
};
 
const long long INF = LLONG_MAX / 2;
 
vector<long long> dist;
vector<edge> e;
 
int main() {    
    ios::sync_with_stdio(false);    
    int vertexCount, edgeCount = 0;
    reader >> vertexCount;
    for (int i = 0; i < vertexCount; i++) {
        for (int j = 0; j < vertexCount; j++) {
            int number;
            reader >> number;
            if (number != 1000000000) {
                e.push_back(edge(i, j, number));
                edgeCount++;
            }
        }
    }
    solve(vertexCount, edgeCount);
    reader.close();
    writer.close();
}
 
void solve(int vertexCount, int edgeCount) {
    dist.resize(vertexCount);
    fill(dist.begin(), dist.end(), INF);
    vector<int> p(vertexCount, -1);
    int x;
    for (int i = 0; i < vertexCount; ++i) {
        x = -1;
        for (int j = 0; j < edgeCount; ++j) {
            if (dist[e[j].b] > dist[e[j].a] + e[j].cost) {
                dist[e[j].b] = min(-INF, dist[e[j].a] + static_cast<long long>(e[j].cost));
                p[e[j].b] = e[j].a;
                x = e[j].b;
            }
        }
    }
 
    if (x == -1)
        writer << "NO";
    else {
        int y = x;
        for (int i = 0; i < vertexCount; ++i)
            y = p[y];
 
        vector<int> path;
        for (int cur = y; ; cur = p[cur]) {
            path.push_back(cur);
            if (cur == y && path.size() > 1)  break;
        }
        reverse(path.begin(), path.end());
 
        writer << "YES" << endl << path.size() << endl;
        for (size_t i = 0; i < path.size(); ++i)
            writer << path[i] + 1 << " ";
    }
}
