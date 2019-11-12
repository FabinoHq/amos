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
//     Math/Matrix4x4.java : 4x4 Matrix management                            //
////////////////////////////////////////////////////////////////////////////////
package com.amos.Math;

import java.lang.Math;


////////////////////////////////////////////////////////////////////////////////
//  Matrix4x4 class definition                                                //
////////////////////////////////////////////////////////////////////////////////
public class Matrix4x4
{
    ////////////////////////////////////////////////////////////////////////////
    //  Matrix4x4 default constructor                                         //
    ////////////////////////////////////////////////////////////////////////////
    public Matrix4x4()
    {
        m_matrix = new float[16];
        m_matrix[0] = 1.0f;
        m_matrix[1] = 0.0f;
        m_matrix[2] = 0.0f;
        m_matrix[3] = 0.0f;
        m_matrix[4] = 0.0f;
        m_matrix[5] = 1.0f;
        m_matrix[6] = 0.0f;
        m_matrix[7] = 0.0f;
        m_matrix[8] = 0.0f;
        m_matrix[9] = 0.0f;
        m_matrix[10] = 1.0f;
        m_matrix[11] = 0.0f;
        m_matrix[12] = 0.0f;
        m_matrix[13] = 0.0f;
        m_matrix[14] = 0.0f;
        m_matrix[15] = 1.0f;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  setIdentity : Set 4x4 identity matrix                                 //
    ////////////////////////////////////////////////////////////////////////////
    public void setIdentity()
    {
        m_matrix[0] = 1.0f;
        m_matrix[1] = 0.0f;
        m_matrix[2] = 0.0f;
        m_matrix[3] = 0.0f;
        m_matrix[4] = 0.0f;
        m_matrix[5] = 1.0f;
        m_matrix[6] = 0.0f;
        m_matrix[7] = 0.0f;
        m_matrix[8] = 0.0f;
        m_matrix[9] = 0.0f;
        m_matrix[10] = 1.0f;
        m_matrix[11] = 0.0f;
        m_matrix[12] = 0.0f;
        m_matrix[13] = 0.0f;
        m_matrix[14] = 0.0f;
        m_matrix[15] = 1.0f;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  setMatrix : Set 4x4 matrix from another matrix (copy)                 //
    //  param mat : 4x4 matrix to copy                                        //
    ////////////////////////////////////////////////////////////////////////////
    public void setMatrix(Matrix4x4 mat)
    {
        m_matrix[0] = mat.m_matrix[0];
        m_matrix[1] = mat.m_matrix[1];
        m_matrix[2] = mat.m_matrix[2];
        m_matrix[3] = mat.m_matrix[3];
        m_matrix[4] = mat.m_matrix[4];
        m_matrix[5] = mat.m_matrix[5];
        m_matrix[6] = mat.m_matrix[6];
        m_matrix[7] = mat.m_matrix[7];
        m_matrix[8] = mat.m_matrix[8];
        m_matrix[9] = mat.m_matrix[9];
        m_matrix[10] = mat.m_matrix[10];
        m_matrix[11] = mat.m_matrix[11];
        m_matrix[12] = mat.m_matrix[12];
        m_matrix[13] = mat.m_matrix[13];
        m_matrix[14] = mat.m_matrix[14];
        m_matrix[15] = mat.m_matrix[15];
    }

    ////////////////////////////////////////////////////////////////////////////
    //  setOrthographic : Set 4x4 matrix to orthographic view matrix          //
    //  param left : Left of the orthographic view                            //
    //  param right : Right of the orthographic view                          //
    //  param top : Top of the orthographic view                              //
    //  param bottom : Bottom of the orthographic view                        //
    //  param near : Near plane of the orthographic view                      //
    //  param far : Far plane of the orthographic view                        //
    ////////////////////////////////////////////////////////////////////////////
    public void setOrthographic(float left, float right, float top,
        float bottom, float near, float far)
    {
        m_matrix[0] = 2.0f/(right-left);
        m_matrix[1] = 0.0f;
        m_matrix[2] = 0.0f;
        m_matrix[3] = 0.0f;
        m_matrix[4] = 0.0f;
        m_matrix[5] = 2.0f/(top-bottom);
        m_matrix[6] = 0.0f;
        m_matrix[7] = 0.0f;
        m_matrix[8] = 0.0f;
        m_matrix[9] = 0.0f;
        m_matrix[10] = -2.0f/(far-near);
        m_matrix[11] = 0.0f;
        m_matrix[12] = -(right+left)/(right-left);
        m_matrix[13] = -(top+bottom)/(top-bottom);
        m_matrix[14] = -(far+near)/(far-near);
        m_matrix[15] = 1.0f;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  setFrustum : Set 4x4 matrix to frustum view matrix                    //
    //  param left : Left of the frustum view                                 //
    //  param right : Right of the frustum view                               //
    //  param top : Top of the frustum view                                   //
    //  param bottom : Bottom of the frustum view                             //
    //  param near : Near plane of the frustum view                           //
    //  param far : Far plane of the frustum view                             //
    ////////////////////////////////////////////////////////////////////////////
    public void setFrustum(float left, float right, float top,
        float bottom, float near, float far)
    {
        m_matrix[0] = (2.0f*near)/(right-left);
        m_matrix[1] = 0.0f;
        m_matrix[2] = 0.0f;
        m_matrix[3] = 0.0f;
        m_matrix[4] = 0.0f;
        m_matrix[5] = (2.0f*near)/(top-bottom);
        m_matrix[6] = 0.0f;
        m_matrix[7] = 0.0f;
        m_matrix[8] = (right+left)/(right-left);
        m_matrix[9] = (top+bottom)/(top-bottom);
        m_matrix[10] = -(far+near)/(far-near);
        m_matrix[11] = -1.0f;
        m_matrix[12] = 0.0f;
        m_matrix[13] = 0.0f;
        m_matrix[14] = -(2.0f*far*near)/(far-near);
        m_matrix[15] = 0.0f;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  setPerspective : Set 4x4 matrix to perspective view matrix            //
    //  param fovy : Angle of the field of view                               //
    //  param ratio : Perspective aspect ratio (width / height)               //
    //  param near : Near plane of the frustum view                           //
    //  param far : Far plane of the frustum view                             //
    ////////////////////////////////////////////////////////////////////////////
    public void setPerspective(float fovy, float ratio, float near, float far)
    {
        // Compute view frustum
        float frustHeight = (float)((Math.tan((fovy/360.0f)*Math.PI))*near);
        float frustWidth = frustHeight*ratio;
        float left = -frustWidth;
        float right = frustWidth;
        float top = frustHeight;
        float bottom = -frustHeight;
        
        // Set matrix
        m_matrix[0] = (2.0f*near)/(right-left);
        m_matrix[1] = 0.0f;
        m_matrix[2] = 0.0f;
        m_matrix[3] = 0.0f;
        m_matrix[4] = 0.0f;
        m_matrix[5] = (2.0f*near)/(top-bottom);
        m_matrix[6] = 0.0f;
        m_matrix[7] = 0.0f;
        m_matrix[8] = (right+left)/(right-left);
        m_matrix[9] = (top+bottom)/(top-bottom);
        m_matrix[10] = -(far+near)/(far-near);
        m_matrix[11] = -1.0f;
        m_matrix[12] = 0.0f;
        m_matrix[13] = 0.0f;
        m_matrix[14] = -(2.0f*far*near)/(far-near);
        m_matrix[15] = 0.0f;
    }

    // Internal matrix
    private float m_matrix[];
}
