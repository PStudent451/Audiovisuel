package efrei.audiovisuel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;


import android.graphics.Matrix;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;
import java.io.FileOutputStream;


/**
 * Created by PAUL on 29/12/2015.
 */
/*
public class PlayActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        VideoView video = (VideoView) findViewById(R.id.videoView);
        MediaController controller = new MediaController(this);
        video.setMediaController(controller);



        video.setVideoURI(Uri.parse("/sdcard/video1.mp4"));
        video.setRotation(90);
        video.requestFocus();
    }
}
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

            // close the progress bar and play the video

            progressDialog.dismiss();

            //if we have a position on savedInstanceState, the video playback should start from here

            myVideoView.seekTo(position);
            myVideoView.pause();



            }

            });



            }



    @Override

    public void onSaveInstanceState(Bundle savedInstanceState) {

            super.onSaveInstanceState(savedInstanceState);

            //we use onSaveInstanceState in order to store the video playback position for orientation change

            savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());

            myVideoView.pause();

            }



    @Override

    public void onRestoreInstanceState(Bundle savedInstanceState) {

            super.onRestoreInstanceState(savedInstanceState);

            //we use onRestoreInstanceState in order to play the video playback from the stored position

            position = savedInstanceState.getInt("Position");

            myVideoView.seekTo(position);

            }

}
