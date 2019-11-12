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
        float frustHeight = (float)((Math.tan((fovy/360.0)*Math.PI))*near);
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

    ////////////////////////////////////////////////////////////////////////////
    //  translateX : Translate 4x4 matrix on X axis                           //
    //  param x : X axis translate value                                      //
    ////////////////////////////////////////////////////////////////////////////
    public void translateX(float x)
    {
        m_matrix[12] += (m_matrix[0]*x);
        m_matrix[13] += (m_matrix[1]*x);
        m_matrix[14] += (m_matrix[2]*x);
        m_matrix[15] += (m_matrix[3]*x);
    }

    ////////////////////////////////////////////////////////////////////////////
    //  translateY : Translate 4x4 matrix on Y axis                           //
    //  param y : Y axis translate value                                      //
    ////////////////////////////////////////////////////////////////////////////
    public void translateY(float y)
    {
        m_matrix[12] += (m_matrix[4]*y);
        m_matrix[13] += (m_matrix[5]*y);
        m_matrix[14] += (m_matrix[6]*y);
        m_matrix[15] += (m_matrix[7]*y);
    }

    ////////////////////////////////////////////////////////////////////////////
    //  translateZ : Translate 4x4 matrix on Z axis                           //
    //  param z : Z axis translate value                                      //
    ////////////////////////////////////////////////////////////////////////////
    public void translateZ(float z)
    {
        m_matrix[12] += (m_matrix[8]*z);
        m_matrix[13] += (m_matrix[9]*z);
        m_matrix[14] += (m_matrix[10]*z);
        m_matrix[15] += (m_matrix[11]*z);
    }

    ////////////////////////////////////////////////////////////////////////////
    //  rotate : Rotate 4x4 matrix arround an arbritrary axis                 //
    //  param angle : Angle to rotate in degrees                              //
    //  param x : X axis rotation value                                       //
    //  param y : Y axis rotation value                                       //
    //  param z : Z axis rotation value                                       //
    ////////////////////////////////////////////////////////////////////////////
    public void rotate(float angle, float x, float y, float z)
    {
        float magnitude = (float)Math.sqrt(x*x + y*y + z*z);
        float sinAngle = (float)Math.sin(angle*Math.PI / 180.0);
        float cosAngle = (float)Math.cos(angle*Math.PI / 180.0);
        float oneMinCos = 1.0f-cosAngle;
        if (magnitude > 0.0f)
        {
            x /= magnitude;
            y /= magnitude;
            z /= magnitude;
        }
        float rot0 = ((oneMinCos*x*x)+cosAngle);
        float rot1 = ((oneMinCos*x*y)-(z*sinAngle));
        float rot2 = ((oneMinCos*z*x)+(y*sinAngle));
        float rot4 = ((oneMinCos*x*y)+(z*sinAngle));
        float rot5 = ((oneMinCos*y*y)+cosAngle);
        float rot6 = ((oneMinCos*y*z)-(x*sinAngle));
        float rot8 = ((oneMinCos*z*x)-(y*sinAngle));
        float rot9 = ((oneMinCos*y*z)+(x*sinAngle));
        float rot10 = ((oneMinCos*z*z)+cosAngle);

        Matrix4x4 rotMat = new Matrix4x4();

        rotMat.m_matrix[0] = (m_matrix[0]*rot0
                        + m_matrix[4]*rot1
                        + m_matrix[8]*rot2);

        rotMat.m_matrix[1] = (m_matrix[1]*rot0
                        + m_matrix[5]*rot1
                        + m_matrix[9]*rot2);

        rotMat.m_matrix[2] = (m_matrix[2]*rot0
                        + m_matrix[6]*rot1
                        + m_matrix[10]*rot2);

        rotMat.m_matrix[3] = (m_matrix[3]*rot0
                        + m_matrix[7]*rot1
                        + m_matrix[11]*rot2);

        rotMat.m_matrix[4] = (m_matrix[0]*rot4
                        + m_matrix[4]*rot5
                        + m_matrix[8]*rot6);

        rotMat.m_matrix[5] = (m_matrix[1]*rot4
                        + m_matrix[5]*rot5
                        + m_matrix[9]*rot6);

        rotMat.m_matrix[6] = (m_matrix[2]*rot4
                        + m_matrix[6]*rot5
                        + m_matrix[10]*rot6);

        rotMat.m_matrix[7] = (m_matrix[3]*rot4
                        + m_matrix[7]*rot5
                        + m_matrix[11]*rot6);

        rotMat.m_matrix[8] = (m_matrix[0]*rot8
                        + m_matrix[4]*rot9
                        + m_matrix[8]*rot10);

        rotMat.m_matrix[9] = (m_matrix[1]*rot8
                        + m_matrix[5]*rot9
                        + m_matrix[9]*rot10);

        rotMat.m_matrix[10] = (m_matrix[2]*rot8
                        + m_matrix[6]*rot9
                        + m_matrix[10]*rot10);

        rotMat.m_matrix[11] = (m_matrix[3]*rot8
                        + m_matrix[7]*rot9
                        + m_matrix[11]*rot10);

        rotMat.m_matrix[12] = m_matrix[12];
        rotMat.m_matrix[13] = m_matrix[13];
        rotMat.m_matrix[14] = m_matrix[14];
        rotMat.m_matrix[15] = m_matrix[15];

        m_matrix = rotMat.m_matrix;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  rotateX : Rotate 4x4 matrix arround X axis                            //
    //  param angle : Angle to rotate in degrees                              //
    ////////////////////////////////////////////////////////////////////////////
    public void rotateX(float angle)
    {
        float sinAngle = (float)Math.sin(angle*Math.PI / 180.0);
        float cosAngle = (float)Math.cos(angle*Math.PI / 180.0);

        Matrix4x4 rotMat = new Matrix4x4();

        rotMat.m_matrix[0] = m_matrix[0];
        rotMat.m_matrix[1] = m_matrix[1];
        rotMat.m_matrix[2] = m_matrix[2];
        rotMat.m_matrix[3] = m_matrix[3];

        rotMat.m_matrix[4] = (m_matrix[4]*cosAngle
                            + m_matrix[8]*-sinAngle);

        rotMat.m_matrix[5] = (m_matrix[5]*cosAngle
                            + m_matrix[9]*-sinAngle);

        rotMat.m_matrix[6] = (m_matrix[6]*cosAngle
                            + m_matrix[10]*-sinAngle);

        rotMat.m_matrix[7] = (m_matrix[7]*cosAngle
                            + m_matrix[11]*-sinAngle);

        rotMat.m_matrix[8] = (m_matrix[4]*sinAngle
                            + m_matrix[8]*cosAngle);

        rotMat.m_matrix[9] = (m_matrix[5]*sinAngle
                            + m_matrix[9]*cosAngle);

        rotMat.m_matrix[10] = (m_matrix[6]*sinAngle
                            + m_matrix[10]*cosAngle);

        rotMat.m_matrix[11] = (m_matrix[7]*sinAngle
                            + m_matrix[11]*cosAngle);

        rotMat.m_matrix[12] = m_matrix[12];
        rotMat.m_matrix[13] = m_matrix[13];
        rotMat.m_matrix[14] = m_matrix[14];
        rotMat.m_matrix[15] = m_matrix[15];

        m_matrix = rotMat.m_matrix;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  rotateY : Rotate 4x4 matrix arround Y axis                            //
    //  param angle : Angle to rotate in degrees                              //
    ////////////////////////////////////////////////////////////////////////////
    public void rotateY(float angle)
    {
        float sinAngle = (float)Math.sin(angle*Math.PI / 180.0);
        float cosAngle = (float)Math.cos(angle*Math.PI / 180.0);

        Matrix4x4 rotMat = new Matrix4x4();

        rotMat.m_matrix[0] = (m_matrix[0]*cosAngle
                            + m_matrix[8]*sinAngle);

        rotMat.m_matrix[1] = (m_matrix[1]*cosAngle
                            + m_matrix[9]*sinAngle);

        rotMat.m_matrix[2] = (m_matrix[2]*cosAngle
                            + m_matrix[10]*sinAngle);

        rotMat.m_matrix[3] = (m_matrix[3]*cosAngle
                            + m_matrix[11]*sinAngle);

        rotMat.m_matrix[4] = m_matrix[4];
        rotMat.m_matrix[5] = m_matrix[5];
        rotMat.m_matrix[6] = m_matrix[6];
        rotMat.m_matrix[7] = m_matrix[7];

        rotMat.m_matrix[8] = (m_matrix[0]*-sinAngle
                            + m_matrix[8]*cosAngle);

        rotMat.m_matrix[9] = (m_matrix[1]*-sinAngle
                            + m_matrix[9]*cosAngle);

        rotMat.m_matrix[10] = (m_matrix[2]*-sinAngle
                            + m_matrix[10]*cosAngle);

        rotMat.m_matrix[11] = (m_matrix[3]*-sinAngle
                            + m_matrix[11]*cosAngle);

        rotMat.m_matrix[12] = m_matrix[12];
        rotMat.m_matrix[13] = m_matrix[13];
        rotMat.m_matrix[14] = m_matrix[14];
        rotMat.m_matrix[15] = m_matrix[15];

        m_matrix = rotMat.m_matrix;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  rotateZ : Rotate 4x4 matrix arround Z axis                            //
    //  param angle : Angle to rotate in degrees                              //
    ////////////////////////////////////////////////////////////////////////////
    public void rotateZ(float angle)
    {
        float sinAngle = (float)Math.sin(angle*Math.PI / 180.0);
        float cosAngle = (float)Math.cos(angle*Math.PI / 180.0);

        Matrix4x4 rotMat = new Matrix4x4();

        rotMat.m_matrix[0] = (m_matrix[0]*cosAngle
                            + m_matrix[4]*-sinAngle);

        rotMat.m_matrix[1] = (m_matrix[1]*cosAngle
                            + m_matrix[5]*-sinAngle);

        rotMat.m_matrix[2] = (m_matrix[2]*cosAngle
                            + m_matrix[6]*-sinAngle);

        rotMat.m_matrix[3] = (m_matrix[3]*cosAngle
                            + m_matrix[7]*-sinAngle);

        rotMat.m_matrix[4] = (m_matrix[0]*sinAngle
                            + m_matrix[4]*cosAngle);

        rotMat.m_matrix[5] = (m_matrix[1]*sinAngle
                            + m_matrix[5]*cosAngle);

        rotMat.m_matrix[6] = (m_matrix[2]*sinAngle
                            + m_matrix[6]*cosAngle);

        rotMat.m_matrix[7] = (m_matrix[3]*sinAngle
                            + m_matrix[7]*cosAngle);

        rotMat.m_matrix[8] = m_matrix[8];
        rotMat.m_matrix[9] = m_matrix[9];
        rotMat.m_matrix[10] = m_matrix[10];
        rotMat.m_matrix[11] = m_matrix[11];
        rotMat.m_matrix[12] = m_matrix[12];
        rotMat.m_matrix[13] = m_matrix[13];
        rotMat.m_matrix[14] = m_matrix[14];
        rotMat.m_matrix[15] = m_matrix[15];

        m_matrix = rotMat.m_matrix;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  scale : Scale 4x4 matrix                                              //
    //  param x : X factor to scale to                                        //
    //  param y : Y factor to scale to                                        //
    //  param z : Z factor to scale to                                        //
    ////////////////////////////////////////////////////////////////////////////
    public void scale(float x, float y, float z)
    {
        m_matrix[0] *= x;
        m_matrix[1] *= x;
        m_matrix[2] *= x;
        m_matrix[3] *= x;
        m_matrix[4] *= y;
        m_matrix[5] *= y;
        m_matrix[6] *= y;
        m_matrix[7] *= y;
        m_matrix[8] *= z;
        m_matrix[9] *= z;
        m_matrix[10] *= z;
        m_matrix[11] *= z;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  scaleX : Scale 4x4 matrix along the X axis                            //
    //  param x : X factor to scale to                                        //
    ////////////////////////////////////////////////////////////////////////////
    public void scaleX(float x)
    {
        m_matrix[0] *= x;
        m_matrix[1] *= x;
        m_matrix[2] *= x;
        m_matrix[3] *= x;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  scaleY : Scale 4x4 matrix along the Y axis                            //
    //  param y : Y factor to scale to                                        //
    ////////////////////////////////////////////////////////////////////////////
    public void scaleY(float y)
    {
        m_matrix[4] *= y;
        m_matrix[5] *= y;
        m_matrix[6] *= y;
        m_matrix[7] *= y;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  scaleZ : Scale 4x4 matrix along the Z axis                            //
    //  param z : Z factor to scale to                                        //
    ////////////////////////////////////////////////////////////////////////////
    public void scaleZ(float z)
    {
        m_matrix[8] *= z;
        m_matrix[9] *= z;
        m_matrix[10] *= z;
        m_matrix[11] *= z;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  multiply : Multiply 4x4 matrix by another 4x4 matrix                  //
    //  param mat : 4x4 matrix to multiply                                 //
    ////////////////////////////////////////////////////////////////////////////
    public void multiply(Matrix4x4 mat)
    {
        Matrix4x4 multMat = new Matrix4x4();

        multMat.m_matrix[0] = (m_matrix[0]*mat.m_matrix[0]
                            + m_matrix[4]*mat.m_matrix[1]
                            + m_matrix[8]*mat.m_matrix[2]
                            + m_matrix[12]*mat.m_matrix[3]);

        multMat.m_matrix[1] = (m_matrix[1]*mat.m_matrix[0]
                            + m_matrix[5]*mat.m_matrix[1]
                            + m_matrix[9]*mat.m_matrix[2]
                            + m_matrix[13]*mat.m_matrix[3]);

        multMat.m_matrix[2] = (m_matrix[2]*mat.m_matrix[0]
                            + m_matrix[6]*mat.m_matrix[1]
                            + m_matrix[10]*mat.m_matrix[2]
                            + m_matrix[14]*mat.m_matrix[3]);

        multMat.m_matrix[3] = (m_matrix[3]*mat.m_matrix[0]
                            + m_matrix[7]*mat.m_matrix[1]
                            + m_matrix[11]*mat.m_matrix[2]
                            + m_matrix[15]*mat.m_matrix[3]);

        multMat.m_matrix[4] = (m_matrix[0]*mat.m_matrix[4]
                            + m_matrix[4]*mat.m_matrix[5]
                            + m_matrix[8]*mat.m_matrix[6]
                            + m_matrix[12]*mat.m_matrix[7]);

        multMat.m_matrix[5] = (m_matrix[1]*mat.m_matrix[4]
                            + m_matrix[5]*mat.m_matrix[5]
                            + m_matrix[9]*mat.m_matrix[6]
                            + m_matrix[13]*mat.m_matrix[7]);

        multMat.m_matrix[6] = (m_matrix[2]*mat.m_matrix[4]
                            + m_matrix[6]*mat.m_matrix[5]
                            + m_matrix[10]*mat.m_matrix[6]
                            + m_matrix[14]*mat.m_matrix[7]);

        multMat.m_matrix[7] = (m_matrix[3]*mat.m_matrix[4]
                            + m_matrix[7]*mat.m_matrix[5]
                            + m_matrix[11]*mat.m_matrix[6]
                            + m_matrix[15]*mat.m_matrix[7]);

        multMat.m_matrix[8] = (m_matrix[0]*mat.m_matrix[8]
                            + m_matrix[4]*mat.m_matrix[9]
                            + m_matrix[8]*mat.m_matrix[10]
                            + m_matrix[12]*mat.m_matrix[11]);

        multMat.m_matrix[9] = (m_matrix[1]*mat.m_matrix[8]
                            + m_matrix[5]*mat.m_matrix[9]
                            + m_matrix[9]*mat.m_matrix[10]
                            + m_matrix[13]*mat.m_matrix[11]);

        multMat.m_matrix[10] = (m_matrix[2]*mat.m_matrix[8]
                            + m_matrix[6]*mat.m_matrix[9]
                            + m_matrix[10]*mat.m_matrix[10]
                            + m_matrix[14]*mat.m_matrix[11]);

        multMat.m_matrix[11] = (m_matrix[3]*mat.m_matrix[8]
                            + m_matrix[7]*mat.m_matrix[9]
                            + m_matrix[11]*mat.m_matrix[10]
                            + m_matrix[15]*mat.m_matrix[11]);

        multMat.m_matrix[12] = (m_matrix[0]*mat.m_matrix[12]
                            + m_matrix[4]*mat.m_matrix[13]
                            + m_matrix[8]*mat.m_matrix[14]
                            + m_matrix[12]*mat.m_matrix[15]);

        multMat.m_matrix[13] = (m_matrix[1]*mat.m_matrix[12]
                            + m_matrix[5]*mat.m_matrix[13]
                            + m_matrix[9]*mat.m_matrix[14]
                            + m_matrix[13]*mat.m_matrix[15]);

        multMat.m_matrix[14] = (m_matrix[2]*mat.m_matrix[12]
                            + m_matrix[6]*mat.m_matrix[13]
                            + m_matrix[10]*mat.m_matrix[14]
                            + m_matrix[14]*mat.m_matrix[15]);

        multMat.m_matrix[15] = (m_matrix[3]*mat.m_matrix[12]
                            + m_matrix[7]*mat.m_matrix[13]
                            + m_matrix[11]*mat.m_matrix[14]
                            + m_matrix[15]*mat.m_matrix[15]);

        m_matrix = multMat.m_matrix;
    }

    // Internal matrix
    private float m_matrix[];
}
