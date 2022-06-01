package tmvf.wobbler;

public class Cube {
    public CubeFace[] cubeFaces;

    public float[] mT = new float[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
    };

    public Cube(int[] indexesFace) {
        cubeFaces = new CubeFace[6];
        for (int i = 0; i < 6; i++) {
            cubeFaces[i] = new CubeFace(indexesFace[i * 2], indexesFace[i * 2 + 1]);
        }
    }
}
