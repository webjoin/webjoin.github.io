package com.viewt.string;

public class StringIndexPractice {
	
	
	public static void main(String[] args) {
		String sourceTxt = "14566690909090191090",
			   targetTxt = "191";
//		System.out.println(txt.indexOf("9090"));
		int index = indexOf(sourceTxt.toCharArray(), 0, sourceTxt.length(), 
							targetTxt.toCharArray(), 0, targetTxt.length(), 0);
		System.out.println(index);
	}

	static int indexOf(char[] source, int sourceOffset, int sourceCount,
            char[] target, int targetOffset, int targetCount,
            int fromIndex) {
        if (fromIndex >= sourceCount) {
            return (targetCount == 0 ? sourceCount : -1);
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            return fromIndex;
        }

        char first = target[targetOffset];
        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            /* Look for first character. */
            if (source[i] != first) {
                while (++i <= max && source[i] != first){
                	System.out.println("i--"+i+"---"+source[i]);
                }
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1;  j < end && source[j] == target[k] ; j++, k++){
                	System.out.println("j:"+i+"---"+source[i]);
                }

                if (j == end) {
                    /* Found whole string. */
                    return i - sourceOffset;
                }
            }
        }
        return -1;
    }
}
