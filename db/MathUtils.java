package tmvf.wobbler;

public class MathUtils {
    public static float[] vertices = new float[]{
            //front
            -.5f, .5f, .5f,
            .5f, .5f, .5f,
            .5f, -.5f, .5f,
            -.5f, -.5f, .5f,
            //back
            .5f, .5f, -.5f,
            -.5f, .5f, -.5f,
            -.5f, -.5f, -.5f,
            .5f, -.5f, -.5f,
            //left
            -.5f, .5f, -.5f,
            -.5f, .5f, .5f,
            -.5f, -.5f, .5f,
            -.5f, -.5f, -.5f,
            //right
            .5f, .5f, .5f,
            .5f, .5f, -.5f,
            .5f, -.5f, -.5f,
            .5f, -.5f, .5f,
            //top
            -.5f, .5f, -.5f,
            .5f, .5f, -.5f,
            .5f, .5f, .5f,
            -.5f, .5f, .5f,
            //bottom
            -.5f, -.5f, -.5f,
            -.5f, -.5f, .5f,
            .5f, -.5f, .5f,
            .5f, -.5f, -.5f,
    };

    public static float[] matVecMul(float[] m, float[] v) {
        float[] r = new float[4];
        for (int i = 0; i < 4; i++) {
            r[i] = m[i] * v[0] +
                    m[i + 4] * v[1] +
                    m[i + 8] * v[2] +
                    m[i + 12] * v[3];
        }
        return r;
    }

    public static float[] matrixMultiplication(float[] m1, float[] m2) {
        float[] m3 = new float[16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                m3[j + i * 4] = m1[j] * m2[i * 4] +
                        m1[j + 4] * m2[i * 4 + 1] +
                        m1[j + 8] * m2[i * 4 + 2] +
                        m1[j + 12] * m2[i * 4 + 3];
            }
        }
        return m3;
    }

    public static void rotateX(Cube[] tempP) {
        float w = (float) -Math.cos(Math.toRadians(90) / 2);
        float x = (float) -Math.sin(Math.toRadians(90) / 2);
        float y = 0;
        float z = 0;
        float[] temp = new float[16];

        temp[0] = 1 - 2 * (y * y + z * z);
        temp[4] = 2 * (x * y - z * w);
        temp[8] = 2 * (x * z + y * w);
        temp[12] = 0;

        temp[1] = 2 * (x * y + z * w);
        temp[5] = 1 - 2 * (x * x + z * z);
        temp[9] = 2 * (y * z - x * w);
        temp[13] = 0;

        temp[2] = 2 * (x * z - y * w);
        temp[6] = 2 * (y * z + x * w);
        temp[10] = 1 - 2 * (x * x + y * y);
        temp[14] = 0;
        temp[15] = 1;

        CameraMatrix.rotateCurrentShape = MathUtils.matrixMultiplication(temp, CameraMatrix.rotateCurrentShape);

        for (Cube cube : tempP) {
            cube.mT = MathUtils.matrixMultiplication(CameraMatrix.rotateCurrentShape, cube.mT);
        }
    }

    public static void rotateY(Cube[] tempP) {
        float w = (float) -Math.cos(Math.toRadians(90) / 2);
        float x = 0;
        float y = (float) -Math.sin(Math.toRadians(90) / 2);
        float z = 0;
        float[] temp = new float[16];

        temp[0] = 1 - 2 * (y * y + z * z);
        temp[4] = 2 * (x * y - z * w);
        temp[8] = 2 * (x * z + y * w);
        temp[12] = 0;

        temp[1] = 2 * (x * y + z * w);
        temp[5] = 1 - 2 * (x * x + z * z);
        temp[9] = 2 * (y * z - x * w);
        temp[13] = 0;

        temp[2] = 2 * (x * z - y * w);
        temp[6] = 2 * (y * z + x * w);
        temp[10] = 1 - 2 * (x * x + y * y);
        temp[14] = 0;
        temp[15] = 1;

        CameraMatrix.rotateCurrentShape = MathUtils.matrixMultiplication(temp, CameraMatrix.rotateCurrentShape);

        for (Cube cube : tempP) {
            cube.mT = MathUtils.matrixMultiplication(CameraMatrix.rotateCurrentShape, cube.mT);
        }
    }

    public static void rotateZ(Cube[] tempP) {
        float w = (float) -Math.cos(Math.toRadians(90) / 2);
        float x = 0;
        float y = 0;
        float z = (float) Math.sin(Math.toRadians(90) / 2);
        float[] temp = new float[16];

        temp[0] = 1 - 2 * (y * y + z * z);
        temp[4] = 2 * (x * y - z * w);
        temp[8] = 2 * (x * z + y * w);
        temp[12] = 0;

        temp[1] = 2 * (x * y + z * w);
        temp[5] = 1 - 2 * (x * x + z * z);
        temp[9] = 2 * (y * z - x * w);
        temp[13] = 0;

        temp[2] = 2 * (x * z - y * w);
        temp[6] = 2 * (y * z + x * w);
        temp[10] = 1 - 2 * (x * x + y * y);
        temp[14] = 0;
        temp[15] = 1;

        CameraMatrix.rotateCurrentShape = MathUtils.matrixMultiplication(temp, CameraMatrix.rotateCurrentShape);

        for (Cube cube : tempP) {
            cube.mT = MathUtils.matrixMultiplication(CameraMatrix.rotateCurrentShape, cube.mT);
        }
    }
}
