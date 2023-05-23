package logbak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
    public static final Logger LOGGER = LoggerFactory.getLogger("Test.class");
    public static void main(String[] args){
        LOGGER.debug("main start");
        LOGGER.info("The second row");
        int a = 10;
        int b = 0;
        LOGGER.trace("a = " +a);
        LOGGER.trace("b = " +b);
        System.out.println(a);
    }
}
/*
在常规的日志系统中，消息一般可以分为以下几类：

Trace消息：提供最详细的日志信息，主要用于调试问题和追踪请求处理流程。

Debug消息：提供更详细的信息，显示应用程序内部状态的详细信息，通常用于开发、测试和排查问题。

Info消息：提供了描述当前应用程序状态或请求相关信息的消息。这些消息通常比debug和trace等级高，但比warning和error低。

Warning消息：警告级别的消息，标识出可能的错误或异常情况，但不影响应用程序的执行。

Error消息：用于标识出严重错误，该错误将阻止应用程序正常执行，需要及时修复。

Fatal/Critical消息：最高级别的消息，用于表示非常严重的错误，通常会导致应用程序崩溃或停止工作。
 */