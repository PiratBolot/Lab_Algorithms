#include <iostream>
#include <fstream>
#include <list>
#include <queue>
 
using namespace std;

#define INF 5222372036854775807
 
int main()
{
    ifstream fin("path.in");
    ofstream fout("path.out");
    int n, m, s;
    fin >> n >> m >> s;
    long long* d = new long long[n];
    long long** a = new long long*[n];
    list< pair<long long, long long> >* ver = new list< pair<long long, long long> >[n];
    bool* app = new bool[n];
    int i, v, u, j;
    long long w;
    pair<long long, long long> vw;
    bool* cycle = new bool[n];
    for (i = 0; i < n; i++) {
        d[i] = INF;
        cycle[i] = false;
        app[i] = false;
        a[i] = new long long[n];
        for (j = 0; j < n; j++)
            a[i][j] = INF;
    }
    d[s - 1] = 0;
    app[s - 1] = true;
    for (i = 0; i < m; i++) {
        fin >> v >> u >> w;
        a[v - 1][u - 1] = min(a[v - 1][u - 1], w);
    }
    for (i = 0; i < n; i++)
        for (j = 0; j < n; j++)
            if (a[i][j] < INF) {
                vw.first = j;
                vw.second = a[i][j];
                ver[i].push_back(vw);
            }
    for (i = 0; i < n - 1; i++) {
        for (u = 0; u < n; u++) {
            for (list<pair<long long, long long> >::iterator it = ver[u].begin(); it != ver[u].end(); it++) {
                v = it->first;
                if (app[u])
                    app[v] = true;
                if (app[v])
                    d[v] = min(d[v], d[u] + it->second);
            }
        }
    }
    queue<int> q;
    for (u = 0; u < n; u++) {
        if (cycle[u] || !app[u])
            continue;
        for (list<pair<long long, long long> >::iterator it = ver[u].begin(); it != ver[u].end(); it++) {
            v = it->first;
            if ((d[u] + it->second) < d[v]) {
                q.push(u);
                do {
                    i = q.front();
                    q.pop();
                    if (cycle[i])
                        continue;
                    for (list<pair<long long, long long> >::iterator it1 = ver[i].begin(); it1 != ver[i].end(); it1++) {
                        q.push(it1->first);
                    }
                    cycle[i] = true;
                } while (!q.empty());
            }
        }
    }
    for (i = 0; i < n; i++) {
        if (!app[i])
            fout << '*' << endl;
        else
            if (cycle[i])
                fout << '-' << endl;
            else
                fout << d[i] << endl;
    }
}
