﻿using System;
using System.Collections.Generic;
using System.Text;

namespace Game
{
    class Dialogue
    {
        NPC npc;
        string fileName;
        int position;


        public Dialogue(NPC npc, string fileName)
        {
            this.npc = npc;
            this.fileName = fileName;
        }

        public void init()
        {

        }
    }
}