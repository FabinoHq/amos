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
//     Shader.java : Shader management                                        //
////////////////////////////////////////////////////////////////////////////////
package com.amos.Renderer;

import android.opengl.GLES20;


////////////////////////////////////////////////////////////////////////////////
//  Shader class definition                                                   //
////////////////////////////////////////////////////////////////////////////////
public class Shader
{
    ////////////////////////////////////////////////////////////////////////////
    //  Shader default constructor                                            //
    //  param vertexSrc : Vertex shader program source                        //
    //  param fragmentSrc : Fragment shader program source                    //
    ////////////////////////////////////////////////////////////////////////////
    public Shader(String vertexSrc, String fragmentSrc)
    {
        m_vertexShader = 0;
        m_fragmentShader = 0;
        m_shaderProgram = 0;

        // Init vertex shader
        m_vertexShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);

        // Set vertex shader source
        GLES20.glShaderSource(m_vertexShader, vertexSrc);

        // Compile vertex shader
        GLES20.glCompileShader(m_vertexShader);


        // Init fragment shader
        m_fragmentShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);

        // Set fragment shader source
        GLES20.glShaderSource(m_fragmentShader, fragmentSrc);

        // Compile fragment shader
        GLES20.glCompileShader(m_fragmentShader);


        // Create shader program
        m_shaderProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(m_shaderProgram, m_vertexShader);
        GLES20.glAttachShader(m_shaderProgram, m_fragmentShader);

        // Link shader
        GLES20.glLinkProgram(m_shaderProgram);
    }


    // Vertex shader program
    private int m_vertexShader;

    // Fragment shader program
    private int m_fragmentShader;

    // Shader program
    private int m_shaderProgram;
}
