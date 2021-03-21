package ua.com.alevel;

public final class Reverse {

    public static String reverse(String src) {
        char[] in = src.toCharArray();
        int begin = 0;
        int end = in.length - 1;
        char temp;
        while (end > begin) {
            temp = in[begin];
            in[begin] = in[end];
            in[end] = temp;
            end--;
            begin++;
        }
        return new String(in);
    }

    public static String reverse(String src, String dest) {
        String expression;
        if (src.contains(dest)) {
            String line = reverse(dest);
            expression = src.replace(dest, line);
        } else return src + " - no matches found";
        return expression;
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        String line = src.substring(firstIndex, lastIndex);
        src = reverse(src, line);
        return src;
    }

}
