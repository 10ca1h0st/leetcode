class Solution {
    public String destCity(List<List<String>> paths) {
        Set<String> starts = new HashSet<>();
        Set<String> ends = new HashSet<>();
        for (List<String> list : paths) {
            starts.add(list.get(0));
            ends.add(list.get(1));
        }
        ends.removeAll(starts);
        for (String s : ends) {
            return s;
        }
        return "";
    }
}