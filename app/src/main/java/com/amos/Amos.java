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
//     Amos.java : Amos Global management                                     //
////////////////////////////////////////////////////////////////////////////////
package com.amos;

import android.content.Context;

import com.amos.Renderer.AmosRenderer;


////////////////////////////////////////////////////////////////////////////////
//  Amos class definition                                                     //
////////////////////////////////////////////////////////////////////////////////
public class Amos
{
    ////////////////////////////////////////////////////////////////////////////
    //  Amos default constructor                                              //
    //  param context : Main activity context                                 //
    ////////////////////////////////////////////////////////////////////////////
    public Amos(final Context context)
    {
        m_loaded = false;
        m_renderer = null;
        m_context = context;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  init : Init Amos                                                      //
    ////////////////////////////////////////////////////////////////////////////
    public void init()
    {
        // Init done
        m_loaded = true;
    }

    ////////////////////////////////////////////////////////////////////////////
    //  compute : Compute all physics and logic                               //
    ////////////////////////////////////////////////////////////////////////////
    public void compute()
    {
        if (m_loaded)
        {

        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //  compute : Amos frame rendering                                        //
    ////////////////////////////////////////////////////////////////////////////
    public void render()
    {
        if (m_loaded)
        {

        }
    }


    ////////////////////////////////////////////////////////////////////////////
    //  getRenderer : Get Amos renderer                                       //
    //  return : Amos renderer reference                                      //
    ////////////////////////////////////////////////////////////////////////////
    public AmosRenderer getRenderer()
    {
        return m_renderer;
    }


    // Amos loaded state
    private boolean m_loaded;

    // Amos context
    private final Context m_context;

    // Amos Renderer
    private AmosRenderer m_renderer;
}
