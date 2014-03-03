package;

#if cpp
import cpp.Lib;
import haxe.io.Bytes;
#elseif neko
import neko.Lib;
#elseif flash
import flash.Lib;
#end

#if (android && openfl)
import openfl.utils.JNI;
#end

import flash.utils.ByteArray;
import flash.display.BitmapData;
import flash.geom.Rectangle;

class MailSender {
	
	public static function sendMail(subject:String="", body:String="", isHTML:Bool=true, to:Array<String>=null, cc:Array<String>=null, bcc:Array<String>=null, attImg:BitmapData=null):Void {
		var strTo:String = (to != null && to.length > 0)? to.join(",") : "";
		var strCC:String = (cc != null && cc.length > 0)? cc.join(",") : "";
		var strBCC:String = (bcc != null && bcc.length > 0)? bcc.join(",") : "";
		
		#if (ios || android)
		
		var ba:ByteArray = null;
		
		if (attImg != null) {
			ba = attImg.getPixels(new Rectangle(0, 0, attImg.width, attImg.height));
		}
		
		#end
		
		#if ios
		
		if (attImg != null) {
			cpp_call_send_mail(subject, body, isHTML, strTo, strCC, strBCC, ba.getData(), Std.int(attImg.width), Std.int(attImg.height));
		} else {
			cpp_call_send_mail(subject, body, isHTML, strTo, strCC, strBCC, null, 0, 0);
		}
		
		#elseif (android && openfl)
		
		if (attImg != null) {
			jni_call_send_mail(subject, body, isHTML, strTo, strCC, strBCC, getB64PngData(attImg));
		} else {
			jni_call_send_mail(subject, body, isHTML, strTo, strCC, strBCC, null);
		}
			
		#elseif flash
		
		Lib.getURL(new flash.net.URLRequest("mailto:" + strTo + "?cc=" + strCC + "&bcc=" + strBCC + "&subject=" + subject + "&body=" + body), "_blank");
		
		#end
	}
	
	#if ios
	private static var cpp_call_send_mail = Lib.load ("mailsender", "mailsender_send_mail", -1);
	#end
	
	
	#if (android && openfl)
	
	private static inline var BASE_64_ENCODINGS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	private static inline var BASE_64_PADDING = "=";
	static inline function getB64PngData(bmp:BitmapData):String{

		var b:ByteArray = bmp.encode("png");

		var base64:String = new haxe.crypto.BaseCode(Bytes.ofString(BASE_64_ENCODINGS)).encodeBytes(b).toString();

		var remainder = base64.length % 4;

		if (remainder > 1) {
			base64 += BASE_64_PADDING;
		}
		if (remainder == 2) {
			base64 += BASE_64_PADDING;
		}

		return base64;
	}
	
	
	private static var jni_call_send_mail = JNI.createStaticMethod ("org.haxe.extension.MailSender", "sendMail", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
	
	#end
	
	
}