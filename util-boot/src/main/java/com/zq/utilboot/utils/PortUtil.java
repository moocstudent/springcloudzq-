package com.zq.utilboot.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 默认defaultPort 如果启动时后缀跟着 port=端口号 则首先采用port=的端口号
 */
public class PortUtil {
    public static int setPort(String[] args, int second, String message, int defaultPort) {

        int port = 0;
        //这一段就表示启动的时候如果带了参数，如： port=8099, 那么程序就会使用 8099 作为端口号了。
        //样做的好处是，什么代码都不用改，只需要在启动的时候带不同的参数，就可以启动不同的端口了。
        if (null != args && 0 != args.length) {
            for (String arg : args) {
                if (arg.startsWith("port=")) {
                    String strPort = StrUtil.subAfter(arg, "port=", true);
                    if (NumberUtil.isNumber(strPort)) {
                        port = Convert.toInt(strPort);
                    }
                }
            }
        } else {
            Future<Integer> future = ThreadUtil.execAsync(() -> {
                int p = 0;
                System.out.println("[限时" + second + "秒输入]" + message + "[默认端口:" + defaultPort + "]");
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String strPort = scanner.nextLine();
                    if (!NumberUtil.isInteger(strPort)) {
                        System.err.println("只能是数字");
                        continue;
                    } else {
                        p = Convert.toInt(strPort);
                        scanner.close();
                        break;
                    }
                }
                return p;
            });
            try {
                port = future.get(second, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                port = defaultPort;
            }
        }

        if (!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用了，无法启动%n", port);
            System.exit(1);
        }
        return port;
    }
}
