/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/

class Solution {
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, List<Integer>> id2ids = new HashMap<>();
        Map<Integer, Integer> id2importance = new HashMap<>();
        for(Employee e : employees) {
            id2ids.put(e.id, e.subordinates);
            id2importance.put(e.id, e.importance);
        }
        return dfs(id, id2ids, id2importance);
    }

    int dfs(int id, Map<Integer, List<Integer>> id2ids, Map<Integer, Integer> id2importance) {
        int importance = 0;
        importance += id2importance.get(id);
        for(int i : id2ids.get(id)) {
            importance += dfs(i, id2ids, id2importance);
        }
        return importance;
    }
}