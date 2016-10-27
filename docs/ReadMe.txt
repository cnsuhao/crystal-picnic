
   =======================
  == keyboard controls ==
 =======================


battle:
                tab            -     switch to a different player
                w              -     use item
                a              -     attack
                d              -     (default: none, can be configured)
                s              -     jump

field view:
                escape         -     pause
                left shift     -     toggle run
                tab            -     switch to a different player *
                a              -     use your special ability *
                s              -     activate

items list:
                page up        -     moves selected item up
                page down      -     moves selected item down


* if unlocked




   ==========================================
  == xbox 360 compatible gamepad controls ==
 ==========================================


battle:
                rb             -     switch to a different player
                y              -     use item
                x              -     attack
                b              -     (default: none, can be configured)
                a              -     jump

field view:
                menu           -     pause
                rb             -     switch to a different player *
                x              -     use your special ability *
                a              -     activate

items list:
                lb             -     moves selected item up
                rb             -     moves selected item down


* if unlocked




   ==================
  == raspberry pi ==
 ==================


The Raspberry Pi port requires you to allocate at least 128 MB of GPU memory in
/boot/config.txt (gpu_mem=128). On a 256 MB Pi, you should allocate exactly 128
MB. Unless you have more than 128, running at a higher resolution than the
default will be a problem as you'll run out of GPU memory and the game will
either crash or textures will start showing up as black. You may be able to pull
it off with more than 128 MB on a later generation Pi though.

The game runs pretty well on the slowest Pi at stock clock, but you may not get
60 FPS in some areas without overclocking, which can cause instability.


   =============
  == credits ==
 =============

core team members have been credited in the in-game credits.

fonts and soundfonts are commercially licensed and not to be distributed by any
3rd party.

some sound effects are modified versions of sounds found on www.freesound.org:

	boing.ogg - plingativator
	quack.ogg - digitopia
	ribbit.ogg - juskiddink
	open_door.ogg - zabuhailo
	splat_hit.ogg - mwlandi
	splat.ogg - mwlandi
	kaching.ogg - benboncan
	slip.ogg - soundscapel.com

various open source and commercial libraries are used by the game including
allegro, bass, freetype, libpng, lua, poly2tri and zlib.

--

bstrlib (part of allegro) is used under the following license:

--

copyright (c) 2002-2008 paul hsieh
all rights reserved.

redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

    redistributions of source code must retain the above copyright notice,
    this list of conditions and the following disclaimer.

    redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

    neither the name of bstrlib nor the names of its contributors may be used
    to endorse or promote products derived from this software without
    specific prior written permission.

this software is provided by the copyright holders and contributors "as is"
and any express or implied warranties, including, but not limited to, the
implied warranties of merchantability and fitness for a particular purpose
are disclaimed. in no event shall the copyright owner or contributors be
liable for any direct, indirect, incidental, special, exemplary, or
consequential damages (including, but not limited to, procurement of
substitute goods or services; loss of use, data, or profits; or business
interruption) however caused and on any theory of liability, whether in
contract, strict liability, or tort (including negligence or otherwise)
arising in any way out of the use of this software, even if advised of the
possibility of such damage.

--

lua is used under the following license:

--

copyright (c) 1994-2015 lua.org, puc-rio.

permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "software"), to deal in
the software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the software, and to permit persons to whom the software is furnished to do so,
subject to the following conditions:

the above copyright notice and this permission notice shall be included in all
copies or substantial portions of the software.

the software is provided "as is", without warranty of any kind, express or
implied, including but not limited to the warranties of merchantability, fitness
for a particular purpose and noninfringement. in no event shall the authors or
copyright holders be liable for any claim, damages or other liability, whether
in an action of contract, tort or otherwise, arising from, out of or in
connection with the software or the use or other dealings in the software.

--

the mersenne twister implementation in src/mt19937ar.c is used under
the following license:

--

copyright (c) 1997 - 2002, makoto matsumoto and takuji nishimura, all rights
reserved.                          

redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

  1. redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

  2. redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

  3. the names of its contributors may not be used to endorse or promote
  products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

--

Poly2Tri is used under the following license:

--

Poly2Tri Copyright (c) 2009-2010, Poly2Tri Contributors
http://code.google.com/p/poly2tri/

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice,
  this list of conditions and the following disclaimer.
* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.
* Neither the name of Poly2Tri nor the names of its contributors may be
  used to endorse or promote products derived from this software without specific
  prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
