import TinyTestJ.Test;
import TinyTestJ.RunTests;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestSuite {

  @Test public static void ImageTest1() {
    Image i = new Image(100,100);
    assert (i.data.length == 30000);
  }

  @Test public static void ImageTest2() {
    Image i = new Image(100,100);
    i.set(0,0,0x123456);
    assert (i.data[0] == (byte)0x12);
    assert (i.data[1] == (byte)0x34);
    assert (i.data[2] == (byte)0x56);
    assert (i.data[3] == (byte)0x00);
  }

  @Test public static void ImageTest3() {
    Image i = new Image(100,100);
    i.set(99,99,0x123456);
    int len = i.data.length;
    assert (i.data[len-3] == (byte)0x12);
    assert (i.data[len-2] == (byte)0x34);
    assert (i.data[len-1] == (byte)0x56);
    assert (i.data[len] == (byte)0x00);
  }

  @Test public static void ImageTest4() throws java.io.FileNotFoundException,java.io.IOException {
    String filename = "test.ppm";
    Image i = new Image(100,100);
    i.write(filename);
    java.io.File f = new java.io.File(filename);
    boolean exists = f.exists() && f.isFile();
    assert (exists);
  }
}

class Image {

  public byte [] data;

  public Image(int width, int height ) {
      String matrix ="P3\n" + width + "\n" + height + "\n255\n";
      for(int i = 0; i <= width; i++){
          if (i!=0) {
              matrix.concat(" ");
          }
          for(int j = 0; i <= height; j++) {
              matrix.concat("00 00 00");
          }
          matrix.concat("\n");
      }
      data = new byte[width*height*3];
      System.arraycopy(matrix.getBytes(), 0, data, width*height*3 - matrix.length(), matrix.length());
  }

  public void set (int x, int y, int val) {
      System.out.println("Done");
  }

  public void write (String filename) {
    FileOutputStream fop = null;
    File file;

    try {
      file = new File(filename);
      fop = new FileOutputStream(file);
      if (!file.exists()) {
        file.createNewFile();
      }

      fop.write(data);
      fop.flush();
      fop.close();

      System.out.println("Done");

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (fop != null) {
          fop.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}


// A method set( int x, int y, int val ) which sets a single pixel at position (x,y) to the RGB value represented
// by val (Note: use zero-based indexing, i.e. the upper left corner pixel has position (0,0). Note: only consider
// the lower 24 bits of val).
// A method write( String filename ) which writes the image data to a file represented by filename. As image format,
// use the trivial PPM format with binary encoding (see http://netpbm.sourceforge.net/doc/ppm.html for the file structure).