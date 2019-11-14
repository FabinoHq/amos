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
//     Renderer/Shader.java : Shader management                               //
////////////////////////////////////////////////////////////////////////////////
package com.amos.Renderer;

import android.opengl.GLES20;

import com.amos.Math.Matrix4x4;


////////////////////////////////////////////////////////////////////////////////
//  Shader class definition                                                   //
////////////////////////////////////////////////////////////////////////////////
public class Shader
{
    ////////////////////////////////////////////////////////////////////////////
    //  Default vertex shader                                                 //
    ////////////////////////////////////////////////////////////////////////////
    static final String defaultVertexShaderSrc =
        "attribute vec3 vertexPos;" +
        "attribute vec2 vertexColor;" +
        "uniform mat4 projMatrix;" +
        "uniform mat4 viewMatrix;" +
        "uniform mat4 modelMatrix;" +
        "uniform float alphaUniform;" +
        "varying vec2 texCoord;" +
        "varying float alphaValue;" +
        "void main()" +
        "{" +
        "   texCoord = vertexColor;" +
        "   alphaValue = alphaUniform;" +
        "   gl_Position = projMatrix*viewMatrix*modelMatrix*vec4(" +
                "vertexPos, 1.0);" +
        "}";

    ////////////////////////////////////////////////////////////////////////////
    //  Default fragment shader                                               //
    ////////////////////////////////////////////////////////////////////////////
    static final String defaultFragmentShaderSrc =
        "precision mediump float;" +
        "uniform sampler2D texture;" +
        "varying vec2 texCoord;" +
        "varying float alphaValue;" +
        "void main()" +
        "{" +
        "   vec4 texColor = texture2D(texture, texCoord);" +
        "   gl_FragColor = vec4(texColor.rgb, texColor.a*alphaValue);" +
        "}";


    ////////////////////////////////////////////////////////////////////////////
    //  Shader default constructor                                            //
    ////////////////////////////////////////////////////////////////////////////
    public Shader()
    {
        m_loaded = false;
        m_vertexShaderSrc = "";
        m_fragmentShaderSrc = "";
        m_vertexShader = 0;
        m_fragmentShader = 0;
        m_shaderProgram = 0;
        m_vertexLocation = -1;
        m_textureLocation = -1;
        m_projMatrixLocation = -1;
        m_viewMatrixLocation = -1;
        m_modelMatrixLocation = -1;
        m_alphaLocation = -1;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  init : Init shader                                                    //
    //  param vertexSrc : Vertex shader source                                //
    //  param fragmentSrc : Fragment shader source                            //
    //  return : True if the shader is successfully loaded, false otherwise   //
    ////////////////////////////////////////////////////////////////////////////
    public boolean init(String vertexSrc, String fragmentSrc)
    {
        // Reset shader
        m_loaded = false;
        m_vertexShaderSrc = "";
        m_fragmentShaderSrc = "";
        m_vertexShader = 0;
        m_fragmentShader = 0;
        m_shaderProgram = 0;
        m_vertexLocation = -1;
        m_textureLocation = -1;
        m_projMatrixLocation = -1;
        m_viewMatrixLocation = -1;
        m_modelMatrixLocation = -1;
        m_alphaLocation = -1;

        // Set shader sources
        m_vertexShaderSrc = vertexSrc;
        m_fragmentShaderSrc = fragmentSrc;

        // Init vertex shader
        m_vertexShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);

        // Set vertex shader source
        GLES20.glShaderSource(m_vertexShader, m_vertexShaderSrc);

        // Compile vertex shader
        GLES20.glCompileShader(m_vertexShader);

        // Init fragment shader
        m_fragmentShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);

        // Set fragment shader source
        GLES20.glShaderSource(m_fragmentShader, m_fragmentShaderSrc);

        // Compile fragment shader
        GLES20.glCompileShader(m_fragmentShader);

        // Create shader program
        m_shaderProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(m_shaderProgram, m_vertexShader);
        GLES20.glAttachShader(m_shaderProgram, m_fragmentShader);

        // Link shader
        GLES20.glLinkProgram(m_shaderProgram);

        // Use shader program
        GLES20.glUseProgram(m_shaderProgram);

        // Get vertex position attribute location
        m_vertexLocation = GLES20.glGetAttribLocation(
            m_shaderProgram,
            "vertexPos"
        );
        if (m_vertexLocation == -1) { return false; }

        // Get texture location
        m_textureLocation = GLES20.glGetUniformLocation(
            m_shaderProgram,
            "texture"
        );
        if (m_textureLocation == -1) { return false; }

        // Get projection matrix location
        m_projMatrixLocation = GLES20.glGetUniformLocation(
            m_shaderProgram,
            "projMatrix"
        );
        if (m_projMatrixLocation == -1) { return false; }

        // Get view matrix location
        m_viewMatrixLocation = GLES20.glGetUniformLocation(
            m_shaderProgram,
            "viewMatrix"
        );
        if (m_viewMatrixLocation == -1) { return false; }

        // Get model matrix location
        m_modelMatrixLocation = GLES20.glGetUniformLocation(
            m_shaderProgram,
            "modelMatrix"
        );
        if (m_modelMatrixLocation == -1) { return false; }

        // Get alpha value location
        m_alphaLocation = GLES20.glGetUniformLocation(
            m_shaderProgram,
            "alphaUniform"
        );
        if (m_alphaLocation == -1) { return false; }

        GLES20.glUseProgram(0);

        // Shader successfully loaded
        m_loaded = true;
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  bind : Bind shader to renderer                                        //
    ////////////////////////////////////////////////////////////////////////////
    public void bind()
    {
        if (m_loaded)
        {
            GLES20.glUseProgram(m_shaderProgram);
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //  unbind : Unbind shader                                                //
    ////////////////////////////////////////////////////////////////////////////
    public void unbind()
    {
        if (m_loaded)
        {
            GLES20.glUseProgram(0);
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //  getVertexPosLocation : Get vertex position attribute location         //
    //  return : Vertex position attribute location                           //
    ////////////////////////////////////////////////////////////////////////////
    public int getVertexPosLocation()
    {
        return m_vertexLocation;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  sendProjectionMatrix : Send projection matrix to use with this shader //
    //  param projectionMatrix : 4x4 Projection matrix to use                 //
    ////////////////////////////////////////////////////////////////////////////
    public void sendProjectionMatrix(Matrix4x4 projectionMatrix)
    {
        if (m_loaded)
        {
            GLES20.glUniformMatrix4fv(
                m_projMatrixLocation, 1, false, projectionMatrix.getMatrix(), 0
            );
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //  sendViewMatrix : Send view matrix to use with this shader             //
    //  param viewMatrix : 4x4 View matrix to use                             //
    ////////////////////////////////////////////////////////////////////////////
    public void sendViewMatrix(Matrix4x4 viewMatrix)
    {
        if (m_loaded)
        {
            GLES20.glUniformMatrix4fv(
                m_viewMatrixLocation, 1, false, viewMatrix.getMatrix(), 0
            );
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //  sendModelMatrix : Send model matrix to use with this shader           //
    //  param modelMatrix : 4x4 Model matrix to use                           //
    ////////////////////////////////////////////////////////////////////////////
    public void sendModelMatrix(Matrix4x4 modelMatrix)
    {
        if (m_loaded)
        {
            GLES20.glUniformMatrix4fv(
                m_modelMatrixLocation, 1, false, modelMatrix.getMatrix(), 0
            );
        }
    }


    // Shader loaded state
    private boolean m_loaded;

    // Shader sources
    private String m_vertexShaderSrc;
    private String m_fragmentShaderSrc;

    // Shader programs
    private int m_vertexShader;
    private int m_fragmentShader;
    private int m_shaderProgram;

    // Shader attributes locations
    private int m_vertexLocation;

    // Shader uniforms locations
    private int m_textureLocation;
    private int m_projMatrixLocation;
    private int m_viewMatrixLocation;
    private int m_modelMatrixLocation;
    private int m_alphaLocation;
}
