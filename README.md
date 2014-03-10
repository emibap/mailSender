OpenFL MailSender extension
=============================

**This is a native extension for OpenFL which allows to call a native mail window.
Supports setting the subject, body, recipients and also an embedded image.
(iOS and Android)**

Installation
------------
You can install it directly from haxelib:
	
	haxelib install mailsender


If you didn't install this extension via haxelib and or to have the latest dev version you can download
this sources and set its folder as the source using the following command:
	
	haxelib dev mailsender path/to/your/downloaded/files

Usage
------------
Reference the extension in your xml descriptor file:
	
	<haxelib name="mailsender" />

Reference the following iOS framework in your .nmml:

	<dependency name="MessageUI.framework" if="ios" />

In your project, just call this static method to use it:
    
    MailSender.sendMail("MailSender sendMail sample", "Howdy, partner\n", false, ["youremail@gmail.com","another@email"], ["youremailCC@gmail.com"], ["youremailBCC@gmail.com"], optionalBmd);
	
	Parameters:
	-----------
	subject:String (default "")
	body:String (default "")
	isHTML:Bool (default true)
	to:Array<String> - pass null if you don't wish to set this parameter
	cc:Array<String> - pass null if you don't wish to set this parameter
	bcc:Array<String> - pass null if you don't wish to ser this parameter
	attImg:BitmapData - Optional image to be embedded at the end of the email. Pass null or skip it if you wish.


Running the test application
------------

    cd sample
    lime build application.xml ios
	
	or 
    lime build application.xml android

	For the flash target a mailto: URL call is being used.

Recompiling the extension
------------

    lime rebuild mailsender ios
	
	or
    lime rebuild mailsender android
    

License
------------

This extension and example license:

    Unless indicated otherwise, this code was created by Emiliano Angelini and
    provided under a MIT-style license. 
    Copyright (c) Emiliano Angelini. All rights reserved.

    Permission is hereby granted, free of charge, to any person obtaining a 
    copy of this software and associated documentation files (the "Software"),
    to deal in the Software without restriction, including without limitation
    the rights to use, copy, modify, merge, publish, distribute, sublicense,
    and/or sell copies of the Software, and to permit persons to whom the
    Software is furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included
    in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
    THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.

The extension includes a copy of some classes from Apple's [MailComposer sample][1] (see license information in the relevant
files).

[1]: http://developer.apple.com/library/ios/#samplecode/MailComposer/Introduction/Intro.html