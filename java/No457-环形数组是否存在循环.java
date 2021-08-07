class Solution {
    public boolean circularArrayLoop(int[] nums) {
        boolean[] visited = null;
        for (int i = 0; i < nums.length; i++) {
            visited = new boolean[nums.length];
            if (nums[i] > 0) {
                int step = nums[i];
                int start = i;
                int position = i;
                int k = 0;
                visited[position] = true;
                while (!visited[(step+position)%nums.length]) {
                    position = (step+position)%nums.length;
                    step = nums[position];
                    visited[position] = true;
                    if (step < 0) {
                        break;
                    }
                    k++;
                }
                if (k > 0 && step > 0 && ((step+position)%nums.length) == start) {
                    return true;
                }
            }
        }
        for (int i = nums.length-1; i >= 0; i--) {
            visited = new boolean[nums.length];
            if (nums[i] < 0) {
                int step = nums[i];
                int start = i;
                int position = i;
                int k = 0;
                visited[position] = true;
                do {
                    position = position+step;
                    if (position < 0) {
                        while (position < 0) {
                            position = nums.length + position;
                        }
                    }
                    if (visited[position]) {
                        break;
                    }
                    step = nums[position];
                    visited[position] = true;
                    if (step > 0) {
                        break;
                    }
                    k++;
                } while (true);
                if (k > 0 && step < 0 && position == start) {
                    return true;
                }
            }
        }
        return false;
    }
}