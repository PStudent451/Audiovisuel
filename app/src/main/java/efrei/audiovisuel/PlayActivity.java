package efrei.audiovisuel;

import android.app.Activity;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;


/**
 * Created by PAUL on 29/12/2015.
 */
public class PlayActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        VideoView video = (VideoView) findViewById(R.id.videoView);
        MediaController controller = new MediaController(this);
        video.setMediaController(controller);
        video.setVideoURI(Uri.parse("/sdcard/video1.mp4"));
        video.requestFocus();
        video.start();


    }
}
