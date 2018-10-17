package com.zcj.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class CompressUtil {

	/**
	 * Ñ¹Ëõ
	 * 
	 * @param input
	 * @param level Ñ¹Ëõ¼¶±ð 0~9
	 * @return
	 */
	public static byte[] compress(byte[] input, int level) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Deflater compressor = new Deflater(level);
		try {
			compressor.setInput(input);
			compressor.finish();
			final byte[] buf = new byte[2048];
			while (!compressor.finished()) {
				int count = compressor.deflate(buf);
				bos.write(buf, 0, count);
			}
		} finally {
			compressor.end();
		}
		return bos.toByteArray();
	}

	/**
	 * ½âÑ¹Ëõ
	 * 
	 * @param input
	 * @return
	 * @throws DataFormatException
	 */
	public static byte[] uncompress(byte[] input) throws DataFormatException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Inflater decompressor = new Inflater();
		try {
			decompressor.setInput(input);
			final byte[] buf = new byte[2048];
			while (!decompressor.finished()) {
				int count = decompressor.inflate(buf);
				bos.write(buf, 0, count);
			}
		} finally {
			decompressor.end();
		}
		return bos.toByteArray();
	}

}
