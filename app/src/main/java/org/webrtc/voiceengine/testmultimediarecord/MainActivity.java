package org.webrtc.voiceengine.testmultimediarecord;

import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;


/**
 * Created by along on 16/12/14 16:44.
 * Email:466210864@qq.com
 * 测试流程：
 * 两个录音都打开，部分手机可能会崩溃，
 * 若不崩，此时再打开微信发语音，可发现无法发送语音
 * 即，同一时间，手机中只能有一个 MediaRecorder实例在 start
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnRecordFirstOpen).setOnClickListener(this);
        findViewById(R.id.btnRecordFirstClose).setOnClickListener(this);
        findViewById(R.id.btnRecordSecondOpen).setOnClickListener(this);
        findViewById(R.id.btnRecordSecondClose).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRecordFirstOpen:
                Toast.makeText(this, "打开first", 0).show();
                openMediaRecord(1);
                break;
            case R.id.btnRecordFirstClose:
                Toast.makeText(this, "关闭first", 0).show();
                closeRecord(1);
                break;
            case R.id.btnRecordSecondOpen:
                Toast.makeText(this, "打开 second", 0).show();
                openMediaRecord(2);
                break;
            case R.id.btnRecordSecondClose:
                Toast.makeText(this, "关闭 second", 0).show();
                closeRecord(2);
                break;
        }
    }

    private String mFilepath = "/storage/sdcard0/stvoice/";
    MediaRecorder currentRecorder = null;
    MediaRecorder firstRecorder;
    MediaRecorder secondRecorder;
    public void openMediaRecord(int index) {
        switch (index) {
            case 1:
                firstRecorder = new MediaRecorder();
                currentRecorder = firstRecorder;
                break;
            case 2:
                secondRecorder = new MediaRecorder();
                currentRecorder = secondRecorder;
                break;
        }
        Log.i("TAG",index+" open---record:"+currentRecorder);
        currentRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//设置麦克风
        currentRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        currentRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        currentRecorder.setOutputFile(mFilepath + index + ".amr");
        try {
            currentRecorder.prepare();
            currentRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeRecord(int index){
        switch (index) {
            case 1:
                currentRecorder = firstRecorder;
                break;
            case 2:
                currentRecorder = secondRecorder;
                break;
        }
        Log.i("TAG",index+" close---record:"+currentRecorder);
        currentRecorder.stop();
        currentRecorder.release();
        currentRecorder = null;
    }
}
