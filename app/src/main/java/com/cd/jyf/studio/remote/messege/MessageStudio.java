package com.cd.jyf.studio.remote.messege;

import android.os.Message;
import android.os.Messenger;

/**
 * message 表示消息
 */
public class MessageStudio {

    /**
     * 表示消息
     */
    private Message  message;//消息的载体   包含消息 以及handle  因为 要让谁来处理这个message

    /**
     * 消息传递信使      如果需要服务器和客户端通信  需要使用replyto
     */
    private Messenger messenger;//


}
