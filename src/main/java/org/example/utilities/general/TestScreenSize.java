package org.example.utilities.general;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author tangzhou
 * @version 1.0
 * @date 2023/5/29 15:00
 */
public class TestScreenSize {
    public static BufferedImage getScreenImage(int x, int y, int width, int height) {
        try {
            return (new Robot()).createScreenCapture(new Rectangle(x, y, width, height));
        } catch (Exception e) {
            return null;
        }
    }

    public static Dimension getScreenSize() {
        return getScreenSizeFromImage(getScreenImage(0, 0, 10000, 5000));
    }

    private static Dimension getScreenSizeFromImage(BufferedImage image) {
        if (image == null) {
            return Toolkit.getDefaultToolkit().getScreenSize();
        }
        int width = image.getWidth();
        int height = image.getHeight();
        long[] w = new long[width];
        long[] h = new long[height];
        for (int i = 0; i < width; i++) {
            long value = 0;
            for (int j = 0; j < height; j++) {
                value += image.getRGB(i, j);
            }
            w[i] = value;
        }
        for (int i = 0; i < height; i++) {
            long value = 0;
            for (int j = 0; j < width; j++) {
                value += image.getRGB(j, i);
            }
            h[i] = value;
        }
        return new Dimension(getValidSize(w), getValidSize(h));
    }

    private static int getValidSize(long[] v) {
        if (v == null || v.length == 0) {
            return 0;
        }
        long last = v[v.length - 1];
        for (int i = v.length - 1; i >= 0; i--) {
            if (last != v[i]) {
                return i + 1;
            }
        }
        return 0;
    }
    public static void main(String[] args) throws UnknownHostException {
        System.out.println(getScreenSize()+
                " windows Size2:"+Toolkit.getDefaultToolkit().getScreenSize().getWidth()+","+ Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println(" Local HostAddress:"+addr.getHostAddress());
        String hostname = addr.getHostName();
        System.out.println(" Local host name: "+hostname);
    }
}
