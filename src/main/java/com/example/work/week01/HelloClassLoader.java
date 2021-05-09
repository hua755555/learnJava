package com.example.work.week01;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\Hello.xlass");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int n=0;
            while ((n=fileInputStream.read(bytes))!=-1){
                byteArrayOutputStream.write(bytes,0,n);
            }
            byte[] bytes1 = byteArrayOutputStream.toByteArray();
            for (int i = 0; i < bytes1.length; i++) {
                byte b = bytes1[i];
                bytes1[i] = (byte) (255-b);
            }
            return defineClass(name,bytes1,0,bytes1.length);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            Class<?> hello = new HelloClassLoader().findClass("Hello");
            Method hello1 = hello.getMethod("hello");
            hello1.invoke(hello.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
