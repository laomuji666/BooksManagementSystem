package com.lmj.bms.util;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;

import androidx.annotation.ColorInt;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class QRCodeUtil {
    /**
     * 创建二维码位图
     *
     * @param content 字符串内容
     * @param width   位图宽度(单位:px)
     * @param height  位图高度(单位:px)
     * @return
     */
    public static Bitmap createQRCodeBitmap(String content, int width, int height) {
        return createQRCodeBitmap(content, width, height, "UTF-8", "H", "2", Color.BLACK, Color.WHITE);
    }

    /**
     * 创建二维码位图 (支持自定义配置和自定义样式)
     *
     * @param content          字符串内容
     * @param width            位图宽度,要求>0(单位:px)
     * @param height           位图高度,要求>0(单位:px)
     * @param character_set    字符集/字符转码格式 (支持格式:{@link com.google.zxing.common.CharacterSetECI})。传null时,zxing源码默认使用 "ISO-8859-1"
     * @param error_correction 容错级别 (支持级别:{@link com.google.zxing.qrcode.decoder.ErrorCorrectionLevel})。传null时,zxing源码默认使用 "L"
     * @param margin           空白边距 (可修改,要求:整型且>=0), 传null时,zxing源码默认使用"4"。
     * @param color_black      黑色色块的自定义颜色值
     * @param color_white      白色色块的自定义颜色值
     * @return
     */
    @Nullable
    public static Bitmap createQRCodeBitmap(String content, int width, int height,
                                            @Nullable String character_set, @Nullable String error_correction, @Nullable String margin,
                                            @ColorInt int color_black, @ColorInt int color_white) {
        // 参数合法性判断
        if (TextUtils.isEmpty(content)) {
            return null;
        }

        if (width <= 0 || height <= 0) {
            return null;
        }

        try {
            // 设置二维码相关配置,生成BitMatrix(位矩阵)对象
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();

            // 字符转码格式设置
            if (!TextUtils.isEmpty(character_set)) {
                hints.put(EncodeHintType.CHARACTER_SET, character_set);
            }

            // 容错级别设置
            if (!TextUtils.isEmpty(error_correction)) {
                hints.put(EncodeHintType.ERROR_CORRECTION, error_correction);
            }

            // 空白边距设置
            if (!TextUtils.isEmpty(margin)) {
                hints.put(EncodeHintType.MARGIN, margin);
            }
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            // 创建像素数组,并根据BitMatrix(位矩阵)对象为数组元素赋颜色值
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (bitMatrix.get(x, y)) {
                        // 黑色色块像素设置
                        pixels[y * width + x] = color_black;
                    } else {
                        // 白色色块像素设置
                        pixels[y * width + x] = color_white;
                    }
                }
            }

            // 创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,之后返回Bitmap对象
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 识别二维码
     */
    public static String identifyQRCodeBitmap(BinaryBitmap binaryBitmap){
        //定义二维码参数
        Map hints= new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        MultiFormatReader formatReader = new MultiFormatReader();
        com.google.zxing.Result result = null;
        try {
            result = formatReader.decode(binaryBitmap, hints);
            return result.toString();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
