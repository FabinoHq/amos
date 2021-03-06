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
//     Renderer/AmosRenderer.java : AMOS Rendering management                 //
////////////////////////////////////////////////////////////////////////////////
package com.amos.Renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLES20;

import com.amos.Amos;


////////////////////////////////////////////////////////////////////////////////
//  AmosRenderer class definition                                             //
////////////////////////////////////////////////////////////////////////////////
public class AmosRenderer implements GLSurfaceView.Renderer
{
    ////////////////////////////////////////////////////////////////////////////
    //  AmosRenderer default constructor                                      //
    //  param amos : Amos reference                                           //
    ////////////////////////////////////////////////////////////////////////////
    public AmosRenderer(final Amos amos)
    {
        m_amos = amos;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  onSurfaceCreated : Renderer entry point                               //
    //  param gl10 : OpenGL ES 1.0 reference                                  //
    //  param config : OpenGL ES configuration                                //
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig config)
    {
        // Init OpenGL
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        // Init amos
        m_amos.init();
    }

    ////////////////////////////////////////////////////////////////////////////
    //  onSurfaceChanged : Renderer surface size changed                      //
    //  param gl10 : OpenGL ES 1.0 reference                                  //
    //  param width : Width of the rendering surface                          //
    //  param height : Height of the rendering surface                        //
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height)
    {
        // Set OpenGL viewport
        GLES20.glViewport(0, 0, width, height);
    }

    ////////////////////////////////////////////////////////////////////////////
    //  onDrawFrame : Renderer main loop callback                             //
    //  param gl10 : OpenGL ES 1.0 reference                                  //
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public void onDrawFrame(GL10 gl10)
    {
        // Clear frame
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        // Compute frame
        m_amos.compute();

        // Render frame
        m_amos.render();
    }


    // Amos reference
    private final Amos m_amos;
}
