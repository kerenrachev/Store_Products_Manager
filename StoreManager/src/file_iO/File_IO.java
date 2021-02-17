package file_iO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class File_IO {

	public static void writeFixedString(String str, int size, DataOutput dataOutput) throws IOException
	/*
	 * d writing a object in binary file bit by bit in fix length
	 * 
	 */
	{
		for (int i = 0; i < size; i++) {
			char bit = 0;
			if (i < str.length())
				bit = str.charAt(i);
			dataOutput.writeChar(bit);
		}
	}

	public static String readFixedString(int size, DataInput in) throws IOException {
		StringBuilder b = new StringBuilder(size);
		int i = 0;
		Boolean done = false;
		while (!done && i < size) {
			char ch = in.readChar();
			i++;
			if (ch == 0)
				done = true;
			else
				b.append(ch);
		}
		in.skipBytes(2 * (size - i));
		return b.toString();
	}

	public static Object getObject(byte[] byteArr) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = new ByteArrayInputStream(byteArr);
		ObjectInput in = new ObjectInputStream(bis);
		return in.readObject();
	}

	public static byte[] getByteArray(Object obj) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (ObjectOutputStream os = new ObjectOutputStream(bos)) {
			os.writeObject(obj);
		}
		return bos.toByteArray();
	}

}
