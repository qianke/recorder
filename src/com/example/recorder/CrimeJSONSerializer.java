package com.example.recorder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;

public class CrimeJSONSerializer {
	private Context mContext;
	private String mFileName;

	public CrimeJSONSerializer(Context c, String f) {
		// TODO Auto-generated constructor stub
		mContext = c;
		mFileName = f;
	}

	public void saveCrimes(ArrayList<Crime> crimes) throws JSONException,
			IOException {

		JSONArray array = new JSONArray();

		/* 用字符流和字节流都可以写文件，这里还是用了字符流，其他方法见下面注释的代码部分 */
		Writer writer = null;
		for (Crime c : crimes) {
			array.put(c.toJSON());
		}

		try {
			// 字节流
			OutputStream out = mContext.openFileOutput(mFileName,
					Context.MODE_PRIVATE);
			// 转成字符流
			writer = new OutputStreamWriter(out);

			// 写入文件
			writer.write(array.toString());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}

		// 以缓冲字符流方式写文件 BufferedWriter writer = null;

		/*for (Crime c : crimes) {
			array.put(c.toJSON());
		}
		try {
			OutputStream out = mContext.openFileOutput(mFileName,
					Context.MODE_PRIVATE);
			writer = new BufferedWriter(new OutputStreamWriter(out));

			writer.write(array.toString());

		} finally {
			if (writer != null) {
				writer.close();
			}
		}*/

		// 以字节流方式写文件

		/*for (Crime c : crimes) {
			array.put(c.toJSON());
		}

		FileOutputStream out = mContext.openFileOutput(mFileName,
				Context.MODE_PRIVATE);
		out.write(array.toString().getBytes());*/

	}

	public ArrayList<Crime> loadCrimes() throws IOException, JSONException {
		ArrayList<Crime> crimes = new ArrayList<Crime>();
		// Reader reader = null;
		BufferedReader reader = null;

		try {
			InputStream in = mContext.openFileInput(mFileName);
			// 缓冲字符流读取文件
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;

			// 一行一行读
			while ((line = reader.readLine()) != null) {
				jsonString.append(line);
			}

			// 按字节读取。这里只是演示，读取了1024个字节，超过的话就读不到了
			/*
			 * reader = new InputStreamReader(in); char c[] = new char[1024] ;
			 * int len = reader.read(c);
			 * 
			 * String jsonString = new String(c,0,len);
			 */

			JSONArray array = (JSONArray) new JSONTokener(jsonString.toString())
					.nextValue();

			for (int i = 0; i < array.length(); i++) {
				crimes.add(new Crime(array.getJSONObject(i)));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		return crimes;
	}
}
