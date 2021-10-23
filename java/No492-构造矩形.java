class Solution {
    public int[] constructRectangle(int area) {
        int width = (int) Math.sqrt(area);
        if (width*width == area) {
            return new int[] {width, width};
        }
        int length = width + 1;
        while (length*width != area) {
            if (length*width < area) {
                length++;
            } else {
                width--;
            }
        }
        return new int[] {length, width};
    }
}