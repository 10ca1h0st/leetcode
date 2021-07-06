class Solution {
    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<String> menu = new HashSet<>();
        Set<Integer> tableNos = new HashSet<>();
        Map<Integer, Map<String, Integer>> tables = new HashMap<>();
        for(List<String> order : orders) {
            int tableNo = Integer.valueOf(order.get(1));
            String food = order.get(2);
            menu.add(food);
            tableNos.add(tableNo);
            Map<String, Integer> table = tables.getOrDefault(tableNo, new HashMap<>());
            int count = table.getOrDefault(food, 0);
            count += 1;
            table.put(food, count);
            tables.put(tableNo, table);
        }
        List<Integer> numbers = new ArrayList<>(tableNos);
        Collections.sort(numbers);
        List<String> foods = new ArrayList<>(menu);
        Collections.sort(foods);
        List<List<String>> ans = new ArrayList<>();
        List<String> title = new ArrayList<>();
        title.add("Table");
        for(String food : foods) {
            title.add(food);
        }
        ans.add(title);
        for(int no : numbers) {
            List<String> line = new ArrayList<>();
            line.add(String.valueOf(no));
            Map<String, Integer> table = tables.get(no);
            for(String food : foods) {
                int count = table.getOrDefault(food, 0);
                line.add(String.valueOf(count));
            }
            ans.add(line);
        }
        return ans;
    }
}