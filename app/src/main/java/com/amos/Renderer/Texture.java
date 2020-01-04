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
//     Renderer/Texture.java : Texture management                             //
////////////////////////////////////////////////////////////////////////////////
package com.amos.Renderer;

import android.opengl.GLES20;
import android.opengl.GLUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


////////////////////////////////////////////////////////////////////////////////
//  Texture class definition                                                  //
////////////////////////////////////////////////////////////////////////////////
public class Texture
{
    ////////////////////////////////////////////////////////////////////////////
    //  Texture default constructor                                           //
    ////////////////////////////////////////////////////////////////////////////
    public Texture()
    {
        m_loaded = false;
        m_tex = new int[1];
        m_tex[0] = 0;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  load : Load texture                                                   //
    //  return : True if texture is loaded, false otherwise                   //
    ////////////////////////////////////////////////////////////////////////////
    public boolean load(final Context context)
    {
        // Reset Texture
        m_loaded = false;
        m_tex[0] = 0;

        // Create texture
        GLES20.glGenTextures(1, m_tex, 0);
        if (m_tex[0] == 0)
        {
            // Could not create texture
            return false;
        }

        // Load image
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        final Bitmap bitmap = BitmapFactory.decodeResource(
            context.getResources(), 0, options
        );

        // Bind texture
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, m_tex[0]);

        // Upload texture to GPU
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

        // Set texture min and mag filters
        GLES20.glTexParameteri(
            GLES20.GL_TEXTURE_2D,
            GLES20.GL_TEXTURE_MIN_FILTER,
            GLES20.GL_NEAREST
        );
        GLES20.glTexParameteri(
            GLES20.GL_TEXTURE_2D,
            GLES20.GL_TEXTURE_MAG_FILTER,
            GLES20.GL_NEAREST
        );

        // Unbind texture
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

        // Texture successfully loaded
        m_loaded = true;
        return true;
    }


    // Texture loaded state
    private boolean m_loaded;

    // Texture handle
    private int m_tex[];
}
