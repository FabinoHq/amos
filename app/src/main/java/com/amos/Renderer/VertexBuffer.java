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
//     Renderer/VertexBuffer.java : VBO (Vertex Buffer Object) management     //
////////////////////////////////////////////////////////////////////////////////
package com.amos.Renderer;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.nio.FloatBuffer;


////////////////////////////////////////////////////////////////////////////////
//  VertexBuffer class definition                                             //
////////////////////////////////////////////////////////////////////////////////
public class VertexBuffer
{
    ////////////////////////////////////////////////////////////////////////////
    //  VertexBuffer default constructor                                      //
    ////////////////////////////////////////////////////////////////////////////
    public VertexBuffer()
    {
        m_loaded = false;
        m_vertexBuffer = new int[1];
        m_elementBuffer = new int[1];
        m_texCoordsOffset = 0;
        m_verticesData = new float[] {
            0.0f, 0.0f,
            1.0f, 0.0f,
            1.0f, 1.0f,
            0.0f, 1.0f
        };
        m_texCoordsData = new float[] {
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f,
            0.0f, 0.0f
        };
        m_indicesData = new short[] {
            0, 1, 2,
            0, 2, 3
        };
        m_vertCount = 6;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  init : Init vertex buffer object                                      //
    ////////////////////////////////////////////////////////////////////////////
    public boolean init()
    {
        // Reset VBO
        m_loaded = false;
        m_vertexBuffer[0] = 0;
        m_elementBuffer[0] = 0;

        // Create VBO
        GLES20.glGenBuffers(1, m_vertexBuffer, 0);

        // Create EBO
        GLES20.glGenBuffers(1, m_elementBuffer, 0);

        // Update VBO
        updateBuffer();

        // VBO successfully loaded
        m_loaded = true;
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  bind : Bind vertex buffer object to renderer                          //
    ////////////////////////////////////////////////////////////////////////////
    public void bind()
    {
        if (m_loaded)
        {
            GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, m_vertexBuffer[0]);
            GLES20.glBindBuffer(
                GLES20.GL_ELEMENT_ARRAY_BUFFER,
                m_elementBuffer[0]
            );
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //  unbind : Unbind vertex buffer object                                  //
    ////////////////////////////////////////////////////////////////////////////
    public void unbind()
    {
        if (m_loaded)
        {
            GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
            GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //  updateBuffer : Update vertex buffer object                            //
    ////////////////////////////////////////////////////////////////////////////
    public void updateBuffer()
    {
        // Prepare vertices data
        final FloatBuffer verticesData = ByteBuffer.allocateDirect(
            m_verticesData.length*4).order(ByteOrder.nativeOrder()
        ).asFloatBuffer();
        verticesData.put(m_verticesData).position(0);

        // Prepare indices data
        final ShortBuffer indicesData = ByteBuffer.allocateDirect(
            m_indicesData.length*4).order(ByteOrder.nativeOrder()
        ).asShortBuffer();

        // Send data to GPU
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, m_vertexBuffer[0]);
        m_texCoordsOffset = verticesData.capacity()*4;

        // Send vertices data
        GLES20.glBufferData(
            GLES20.GL_ARRAY_BUFFER,
            m_texCoordsOffset,
            verticesData,
            GLES20.GL_STATIC_DRAW
        );

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

        // Send indexes data
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, m_elementBuffer[0]);
        GLES20.glBufferData(
            GLES20.GL_ELEMENT_ARRAY_BUFFER,
            indicesData.capacity()*2,
            indicesData,
            GLES20.GL_STATIC_DRAW
        );

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    ////////////////////////////////////////////////////////////////////////////
    //  render : Render the vertex buffer object                              //
    ////////////////////////////////////////////////////////////////////////////
    public void render()
    {
        if (m_loaded)
        {
            // Enable vertices array
            GLES20.glEnableVertexAttribArray(0);
            GLES20.glVertexAttribPointer(0, 2, GLES20.GL_FLOAT, false, 0, 0);

            // Draw triangles
            GLES20.glDrawElements(
                GLES20.GL_TRIANGLES,
                m_vertCount,
                GLES20.GL_UNSIGNED_SHORT, 0
            );
        }
    }


    // VBO loaded state
    private boolean m_loaded;

    // Vertex buffer object
    private int m_vertexBuffer[];
    // Element buffer object
    private int m_elementBuffer[];

    // Textures coordinates offset
    private int m_texCoordsOffset;

    // Geometry data
    private float m_verticesData[];
    private float m_texCoordsData[];
    private short m_indicesData[];
    private int m_vertCount;
}
