package sampleJavaPrj;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class zipFiles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Object[] obj = writeFiles();
			zipFiles((List<String>) obj[0]);
			String folder = (String) obj[1];
			//recursiveDelete(new File(folder));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Object[] writeFiles() throws IOException {

		Path folder = Paths.get("D:\\javaSamples");
		Path path = Files.createTempDirectory(folder, "temp");
		
		Object[] obj = new Object[2];
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put("1", "File 1 content");
		ht.put("2", "File 2 content");
		ht.put("3", "File 3 content");

		List<String> files = new ArrayList<>();
		Set<String> keys = ht.keySet();
		Iterator<String> itr = keys.iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			String value = ht.get(key);
			System.out.println("key:" + key + " - value" + value);
			try {

				files.add(path.toAbsolutePath() + "\\" + key + ".txt");
				FileWriter fw = new FileWriter(path.toAbsolutePath() + "\\" + key + ".txt");
				fw.write(value);
				fw.close();
			} catch (Exception e) {

			}

		}
		obj[0] = files;
		obj[1] = path.toAbsolutePath().toString();
		System.out.println(files);
		return obj;
	}

	public static void zipFiles(List<String> files) {
		FileOutputStream fos = null;
		ZipOutputStream zipOut = null;
		FileInputStream fis = null;
		try {
			fos = new FileOutputStream("D:\\javaSamples\\testing.zip"); // TODO you can define your own path or zip file
																		// name
			zipOut = new ZipOutputStream(new BufferedOutputStream(fos));
			for (String filePath : files) {
				File input = new File(filePath);
				fis = new FileInputStream(input);
				ZipEntry ze = new ZipEntry(input.getName());
				System.out.println("Zipping the file: " + input.getName());
				zipOut.putNextEntry(ze);
				byte[] tmp = new byte[4 * 1024];
				int size = 0;
				while ((size = fis.read(tmp)) != -1) {
					zipOut.write(tmp, 0, size);
				}
				zipOut.flush();
				fis.close();
			}
			zipOut.close();
			System.out.println("Done... Zipped the files...");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception ex) {

			}
		}
	}

	public static void recursiveDelete(File file) {
		// to end the recursive loop
		if (!file.exists())
			return;

		// if directory, go inside and call recursively
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				// call recursively
				recursiveDelete(f);
			}
		}
		// call delete to delete files and empty directory
		file.delete();
		System.out.println("Deleted file/folder: " + file.getAbsolutePath());
	}

}
