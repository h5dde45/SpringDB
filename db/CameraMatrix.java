package tmvf.wobbler;

public class CameraMatrix {
    public static float[] unitMatrix = new float[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
    };

    public static float[] unitBox = new float[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
    };

    static float[] rotateWobblerZ = new float[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
    };
    static float[] rotateWobblerXY = new float[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
    };
    static float[] rotateShape = new float[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
    };
    static float[] rotateCurrentShape = new float[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
    };

    public static void clearRotateCurrentShape() {
      rotateCurrentShape[0]=1;
      rotateCurrentShape[1]=0;
      rotateCurrentShape[2]=0;
      rotateCurrentShape[3]=0;
      rotateCurrentShape[4]=0;
      rotateCurrentShape[5]=1;
      rotateCurrentShape[6]=0;
      rotateCurrentShape[7]=0;
      rotateCurrentShape[8]=0;
      rotateCurrentShape[9]=0;
      rotateCurrentShape[10]=1;
      rotateCurrentShape[11]=0;
      rotateCurrentShape[12]=0;
      rotateCurrentShape[13]=0;
      rotateCurrentShape[14]=0;
      rotateCurrentShape[15]=1;
    }
}
