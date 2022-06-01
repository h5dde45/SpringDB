package tmvf.wobbler;

public class ProjectionMatrix {
    static float[] m = new float[16];
    static float[] im = new float[16];
    public static float width;
    public static float height;

    public static void setProjectionMatrix(float fov, float w, float h, float n, float f) {
        width = w;
        height = h;
        float aspect;

        float t;
        float b;
        float l;
        float r;

        aspect = w / h;
        t = n * (float) Math.tan(Math.toRadians(fov / 2));
        b = -t;
        l = b * aspect;
        r = t * aspect;

//        m[0] = 2 * n / (r - l);
        m[0] = n / r;
//        m[5] = 2 * n / (t - b);
        m[5] = n / t;
//        m[10] = -(f + n) / (f - n);
        m[10] = (f + n) / (n - f);
        m[11] = -1;
//        m[14] = -2 * f * n / (f - n);
        m[14] = 2 * f * n / (n - f);
    }

    public static void setProjectionMatrixIcon(float fov, float w, float h, float n, float f) {
        float aspect;

        float t;
        float b;
        float l;
        float r;

        aspect = 1;
        t = n * (float) Math.tan(fov * (Math.PI / 360.0));
        b = -t;
        l = b * aspect;
        r = t * aspect;

        im[0] = 2 * n / (r - l);
        im[5] = 2 * n / (t - b);
        im[10] = -(f + n) / (f - n);
        im[11] = -1;
        im[14] = -2 * f * n / (f - n);
    }
}
