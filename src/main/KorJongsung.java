package main;

public class KorJongsung {
    public static final String isHave(String word, String isTrue, String isFalse) {
        char lastword = word.charAt(word.length() - 1);

        // 유니코드의 한글 데이터 범위
        if (lastword < 0xAC00 || lastword > 0xD7A3) {
            return word + isTrue;
        }

        // 한글 분리 하는법 * 유니코드 기준 *
        // 초성 얻기 = (([글자index] - 0xAC00) / 28) / 21
        // 중성 얻기 = (([글자index] - 0xAC00) / 28) % 21
        // 종성 얻기 = ([글자index] - 0xAC00) % 28

        // 다시 붙이는 법
        // 0xAC00 + 28 * 21 * [초성index] + 28 * [중성index] + [종성index]

        // 받침이 있을 경우 종성index 값이 0보다 큼

        String character = (lastword - 0xAC00) % 28 > 0 ? isTrue : isFalse;
        return word + character;
    }
}
