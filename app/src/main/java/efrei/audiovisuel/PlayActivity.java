package efrei.audiovisuel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;


/**
 * Created by PAUL on 29/12/2015.
 */

public class PlayActivity extends Activity {

    private VideoView myVideoView;
    private int position = 0;
    private ProgressDialog progressDialog;
    private MediaController mediaControls;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_play_video);

            if (mediaControls == null) {
                 mediaControls = new MediaController(PlayActivity.this);
            }
            mediaControls.setBackgroundColor(Color.argb(5,40,40,40));
            myVideoView = (VideoView) findViewById(R.id.videoView);
            progressDialog = new ProgressDialog(PlayActivity.this);
            progressDialog.setTitle("Video is coming!");
            progressDialog.setMessage("Video is coming!");
            progressDialog.setCancelable(false);
            progressDialog.show();

            try {
                 myVideoView.setMediaController(mediaControls);
                 myVideoView.setVideoURI(Uri.parse("/sdcard/video1.mp4"));

            } catch (Exception e) {
                 Log.e("Error", e.getMessage());
                 e.printStackTrace();
            }

            myVideoView.requestFocus();
            myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                    progressDialog.dismiss();
                    myVideoView.seekTo(position);
                    myVideoView.pause();
            }});
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
            super.onSaveInstanceState(savedInstanceState);
            savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());
            myVideoView.pause();
    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);
            position = savedInstanceState.getInt("Position");
            myVideoView.seekTo(position);
    }

}