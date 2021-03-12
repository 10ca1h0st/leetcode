// 拓扑排序 deg为顶点的入度
public List<Integer> topSort(int[] deg, List<List<Integer>> graph, List<Integer> items) {
	Queue<Integer> queue = new LinkedList<Integer>();
	for (int item : items) {
		if (deg[item] == 0) {
			queue.offer(item);
		}
	}
	List<Integer> res = new ArrayList<Integer>();
	while (!queue.isEmpty()) {
		int u = queue.poll(); 
		res.add(u);
		for (int v : graph.get(u)) {
			if (--deg[v] == 0) {
				queue.offer(v);
			}
		}
	}
	return res.size() == items.size() ? res : new ArrayList<Integer>();
}