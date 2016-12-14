# TestMultiMediaRecord

同时有两个 MediaRecorder同时在 start会报错：

日志标签如下：

  IllegalStateException        start failed: -38    start failed: -19
 
  already existsstart failed: -17
  
示例一：

    E/MediaRecorder: start failed: -17
    E/MediaRecorder: notify failed: 1, 300
    D/AndroidRuntime: Shutting down VM
    W/dalvikvm: threadid=1: thread exiting with uncaught exception (group=0x41a62ba8)
    E/AndroidRuntime: FATAL EXCEPTION: main
                      Process: org.webrtc.voiceengine.testmultimediarecord, PID: 28583
                      java.lang.RuntimeException: already exists
                          at android.media.MediaRecorder.start(Native Method)
示例二：

    E/MediaRecorder: start failed: -38
    D/AndroidRuntime: Shutting down VM
    W/dalvikvm: threadid=1: thread exiting with uncaught exception (group=0x41d152a0)
    E/AndroidRuntime: FATAL EXCEPTION: main
                      java.lang.IllegalStateException
                          at android.media.MediaRecorder.start(Native Method)

可在触发此错误之前，使用微信发送语音，可发现无法发送语音

全局查找，将另外一个 MediaRecorder实例关闭即可。
