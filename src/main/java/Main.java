import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        int x = 145, y = 588, w = 345, h = 82; //координаты для взятия области с центральными картами на столе
        String outString; //строка с ответом
        File dir = new File(args[0]); //path указывает на директорию
        File[] arrFiles = dir.listFiles();
        for (int i = 0; i <  arrFiles.length; i++) {
            BufferedImage mainImg = ImageIO.read(arrFiles[i]); //зачитка картинки из файла
            BufferedImage img = mainImg.getSubimage(x, y, w, h); //взятия области с центральными картами на столе из общей картинки
            outString = "".concat(number(img, 0));
            outString = outString.concat(suit(img, 0));
            outString = outString.concat(number(img, 71));
            outString = outString.concat(suit(img, 71));
            outString = outString.concat(number(img, 143));
            outString = outString.concat(suit(img, 143));
            if (!black(img, 260, 20)) { //если 4-я карта на столе вообще есть
                outString = outString.concat(number(img, 214));
                outString = outString.concat(suit(img, 214));
            }
            if (!black(img, 330, 20)) { //если 5-я карта на столе вообще есть
                outString = outString.concat(number(img, 286));
                outString = outString.concat(suit(img, 286));
            }
            System.out.println(arrFiles[i].getName() + " - " + outString);
        }
    }

    /**
     * Функция, распознающая номер карты.
     *
     * @param   img   объект с распознаваемой картинкой.
     * @param   delta   смещение по оси X от первой игральной карты в центре стола.
     * @return  строку с номером карты.
     */
    private static String number(BufferedImage img, int delta) {
        if (notWhite(img, delta + 5, 25)) {
                return "A";//"h";
        } else
            if (notWhite(img, delta + 9, 8) && notWhite(img, delta + 20, 25) && notWhite(img, delta + 18, 15)) {
                return "2";//"h";
            } else
                if (notWhite(img, delta + 9, 4) && !notWhite(img, delta + 9, 13) && notWhite(img, delta + 18, 23)) {
                    return "3";//"h";
                } else
                    if (notWhite(img, delta + 23, 18) && notWhite(img, delta + 15, 20) && notWhite(img, delta + 19, 25)) {
                        return "4";
                    } else
                        if (notWhite(img, delta + 20, 18) && notWhite(img, delta + 27, 24)) {
                            return "Q";
                        } else
                            if (notWhite(img, delta + 9, 4) && notWhite(img, delta + 9, 25) && notWhite(img, delta + 20, 20)) {
                                return "K";
                            } else
                                if (notWhite(img, delta + 4, 6)) {
                                    return "10";
                                } else
                                    if (!notWhite(img, delta + 8, 18) && notWhite(img, delta + 10, 14) && !notWhite(img, delta + 21, 10)) {
                                        return "5";
                                    } else
                                        if (notWhite(img, delta + 8, 14) && notWhite(img, delta + 9, 19)) {
                                            return "6";
                                        } else
                                            if (notWhite(img, delta + 9, 4)) {
                                                return "7";
                                            } else
                                                if (notWhite(img, delta + 8, 19)) {
                                                    return "8";
                                                } else
                                                    if (notWhite(img, delta + 14, 4)) {
                                                        return "9";
                                                    } else {
                                                        return "J";
                                                    }
    }

    /**
     * Функция, распознающая, является ли пиксель небелым (то есть, чёрным или красным).
     *
     * @param   img   объект с распознаваемой картинкой.
     * @param   x   координата пикселя по оси X от первой игральной карты в центре стола.
     * @param   y   координата пикселя по оси Y от первой игральной карты в центре стола.
     * @return  true, если пиксель небелый, иначе - false.
     */
    private static boolean notWhite(BufferedImage img, int x, int y) {
        Color color = new Color(img.getRGB(x, y));
        if (color.getRed() < 110 || color.getBlue() < 110 || color.getGreen() < 110) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Функция, распознающая масть карты.
     *
     * @param   img   объект с распознаваемой картинкой.
     * @param   delta   смещение по оси X от первой игральной карты в центре стола.
     * @return  строку с первой буквой масти карты.
     */
    private static String suit(BufferedImage img, int delta) {
        if (red(img, delta + 39, 65)) {
            if (red(img, delta + 28, 54)) {
                return "h";//"♥";
            } else {
                return "d";//"♦";
            }
        } else {
            if (black(img, delta + 31, 57)) {
                return "s";//"♠";
            } else {
                return "c";//"♣";
            }
        }
    }

    /**
     * Функция, распознающая, является ли пиксель чёрным.
     *
     * @param   img   объект с распознаваемой картинкой.
     * @param   x   координата пикселя по оси X от первой игральной карты в центре стола.
     * @param   y   координата пикселя по оси Y от первой игральной карты в центре стола.
     * @return  true, если пиксель чёрный, иначе - false.
     */
    private static boolean black(BufferedImage img, int x, int y) {
        Color color = new Color(img.getRGB(x, y));
        if (color.getRed() < 110 && color.getBlue() < 110 && color.getGreen() < 110) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Функция, распознающая, является ли пиксель красным.
     *
     * @param   img   объект с распознаваемой картинкой.
     * @param   x   координата пикселя по оси X от первой игральной карты в центре стола.
     * @param   y   координата пикселя по оси Y от первой игральной карты в центре стола.
     * @return  true, если пиксель красный, иначе - false.
     */
    private static boolean red(BufferedImage img, int x, int y) {
        Color color = new Color(img.getRGB(x, y));
        if (color.getRed() > color.getBlue()*2 && color.getRed() > color.getGreen()*2) {
            return true;
        } else {
            return false;
        }
    }

}

