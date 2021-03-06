// Peace Rooks
// https://blog.csdn.net/qq_44791484/article/details/114144628
// https://blog.csdn.net/qq_45719639/article/details/111470570

#include <cstdio>
#include <cstring>
#include <iostream>

const int maxn = 100005;

int father[maxn];

int Find(int x) {
	return father[x] == x ? x : father[x] = Find (father[x]);
}

void Union(int u, int v) {
	if (Find(u) != Find(v)) {
		father[Find(u)] = Find(v);
	}
}

int main() {
	int T, n, m;
	scanf("%d", &T);
	while (T--) {
		scanf("%d %d", &n, &m);
		for (int i = 0; i <= n; i++) {
			father[i] = i;
		}
		int u, v;
		int ans = 0;
		for (int i = 0; i < m; i++) {
			scanf("%d %d", &u, &v);
			if (u == v) {
				continue;
			} else if (Find(u) == Find(v)) {
				ans += 2;
			} else {
				ans += 1;
				Union(u, v);
			}
		}
		printf("%d\n", ans);
	}

	return 0;
}
