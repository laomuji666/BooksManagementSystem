package com.lmj.bms.model.account.code;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/*
    CodeData
    该类用于生成验证码
    接口如下:
    1.CodeData:构造验证码
    2.getCodeStr:获取验证码字符串
    2.getCodePicture:获取验证码图片
 */
class CodeData {
    //验证码字符集,0Ooi1j比较难识别,我决定删除这几个
    private static final String codeChars=
            "23456789" +
                    "QWERTYUPASDFGHKLZXCVBNM"+
                    "qwertyupasdfghklzxcvbnm";

    //验证码随机的颜色
    private static final Color[] charsColor= {Color.red,Color.white,Color.yellow,Color.green,Color.pink};

    //验证码的长度
    private static final int codeLength=4;

    //验证码的宽高
    private static final int WIDTH=240,HEIGHT=100;

    //验证码字符的位置
    private static final int codeWidth=WIDTH/4,codeHeight=HEIGHT/4*3;

    //验证码字符的大小
    private static final Font codeFont= new Font("宋体",Font.ITALIC,70);

    //验证码字符串
    private String code=null;

    //验证码图片
    private BufferedImage image=null;

    //验证码图片
    private byte[] bytes=null;

    //随机数
    private static final Random random=new Random();

    //构造方法 创建验证码
    public CodeData(){
        code="";
        for (int i=0;i<codeLength;i++){
            code+=codeChars.charAt(random.nextInt(codeChars.length()));
        }
        image=createImageCode();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", out);
            bytes=out.toByteArray();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //生成验证码图片
    private BufferedImage createImageCode(){
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, 1);
        Graphics graphics = bufferedImage.getGraphics();
        //首先背景设置为黑色
        graphics.setColor(Color.black);
        graphics.fillRect(0,0,WIDTH,HEIGHT);

        //设置字体
        graphics.setFont(codeFont);
        //随机生成验证码
        String[] split = code.split("");
        for (int i=0;i<split.length;i++) {
            String str=split[i];
            graphics.setColor(charsColor[random.nextInt(charsColor.length)]);
            graphics.drawString(str,i*codeWidth,codeHeight);
        }
        //关闭并返回
        graphics.dispose();
        return bufferedImage;
    }

    //获取验证码字符串
    public String getCodeStr() {
        return code;
    }

    //获取验证码图片
    public byte[] getCodePicture() {
        return bytes;
    }

}
