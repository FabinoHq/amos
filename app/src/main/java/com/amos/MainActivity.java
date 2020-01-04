////////////////////////////////////////////////////////////////////////////////
//                                       ___________________________________  //
//          ____________________________/ . . . . . . . . . . . . . . . .  /  //
//         //. . . . . . . . . . . . . . .   _____________________________/   //
//        //.               _______________________/      / //.  _______/     //
//       //.     \_______________/           /.  _____   / //.  /____         //
//      //.  /\   \    //.   __     ___     /.  /    \\  | \\ .      \        //
//     //.   __    \  //.   / \\   / //    /|.  |    ||  |  \\_____   \       //
//    //.   / \\    \//.   /   \\_/ //    /||.  \____//  |_______//   /       //
//   //.   /   \\    \    /        //    / // .          /. . . .    /        //
//  //____/     \\____\__/        //____/ //____________/___________/         //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////
//   This is free and unencumbered software released into the public domain.  //
//                                                                            //
//   Anyone is free to copy, modify, publish, use, compile, sell, or          //
//   distribute this software, either in source code form or as a compiled    //
//   binary, for any purpose, commercial or non-commercial, and by any        //
//   means.                                                                   //
//                                                                            //
//   In jurisdictions that recognize copyright laws, the author or authors    //
//   of this software dedicate any and all copyright interest in the          //
//   software to the public domain. We make this dedication for the benefit   //
//   of the public at large and to the detriment of our heirs and             //
//   successors. We intend this dedication to be an overt act of              //
//   relinquishment in perpetuity of all present and future rights to this    //
//   software under copyright law.                                            //
//                                                                            //
//   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,          //
//   EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF       //
//   MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.   //
//   IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR        //
//   OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,    //
//   ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR    //
//   OTHER DEALINGS IN THE SOFTWARE.                                          //
//                                                                            //
//   For more information, please refer to <http://unlicense.org>             //
////////////////////////////////////////////////////////////////////////////////
//    AMOS : Android Mobile Operating System                                  //
//     MainActivity.java : Main program entry point                           //
////////////////////////////////////////////////////////////////////////////////
package com.amos;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.View;

import com.amos.Renderer.SurfaceView;


////////////////////////////////////////////////////////////////////////////////
//  MainActivity class definition                                             //
////////////////////////////////////////////////////////////////////////////////
public class MainActivity extends Activity
{
    ////////////////////////////////////////////////////////////////////////////
    //  onCreate : Application entry point                                    //
    ////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Create Amos
        m_amos = new Amos(this);

        // Create surface view for rendering
        m_surfaceView = new SurfaceView(this, m_amos.getRenderer(), m_amos);
        setContentView(m_surfaceView);
    }

    ////////////////////////////////////////////////////////////////////////////
    //  onWindowFocusChanged : Application gain or lost focus                 //
    //  param hasFocus : True if the application has focus, false otherwise   //
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
        {
            setFullscreen();
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //  setFullscreen : Set application fullscreen mode on                    //
    ////////////////////////////////////////////////////////////////////////////
    public void setFullscreen()
    {
        // Recursive Runnable to set Fullscreen on
        final Handler handler = new Handler();
        class StartupRunnable implements Runnable
        {
            public StartupRunnable()
            {
                m_loopCount = 0;
            }

            @Override
            public void run()
            {
                // Set fullscreen mode on
                View currentView = getWindow().getDecorView();
                currentView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                );

                // Callback this function for a while
                if (m_loopCount <= 20)
                {
                    ++m_loopCount;
                    handler.postDelayed(this, 200);
                }
            }

            // Loop counter
            private int m_loopCount;
        }
        
        StartupRunnable runnable = new StartupRunnable();
        handler.postDelayed(runnable, 200);
    }


    // Amos instance
    private Amos m_amos;

    // Main surface view
    private SurfaceView m_surfaceView;
}
