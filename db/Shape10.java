package tmvf.wobbler.shapes;

import static android.opengl.GLES32.GL_ARRAY_BUFFER;
import static android.opengl.GLES32.GL_CLAMP_TO_EDGE;
import static android.opengl.GLES32.GL_ELEMENT_ARRAY_BUFFER;
import static android.opengl.GLES32.GL_FLOAT;
import static android.opengl.GLES32.GL_LINEAR;
import static android.opengl.GLES32.GL_STATIC_DRAW;
import static android.opengl.GLES32.GL_TEXTURE_2D;
import static android.opengl.GLES32.GL_TEXTURE_MAG_FILTER;
import static android.opengl.GLES32.GL_TEXTURE_MIN_FILTER;
import static android.opengl.GLES32.GL_TEXTURE_WRAP_S;
import static android.opengl.GLES32.GL_TEXTURE_WRAP_T;
import static android.opengl.GLES32.glBindBuffer;
import static android.opengl.GLES32.glBindTexture;
import static android.opengl.GLES32.glBindVertexArray;
import static android.opengl.GLES32.glBufferData;
import static android.opengl.GLES32.glEnableVertexAttribArray;
import static android.opengl.GLES32.glGenBuffers;
import static android.opengl.GLES32.glGenTextures;
import static android.opengl.GLES32.glGenVertexArrays;
import static android.opengl.GLES32.glGetAttribLocation;
import static android.opengl.GLES32.glTexParameterf;
import static android.opengl.GLES32.glUniformMatrix4fv;
import static android.opengl.GLES32.glVertexAttribPointer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import tmvf.wobbler.Cube;
import tmvf.wobbler.MathUtils;
import tmvf.wobbler.R;
import tmvf.wobbler.ShaderUtils;

public class Shape10 extends Shape {
    public int[] textureId = new int[1];
    int otMatrix;
    public int maPositionHandle;
    public int maTextureHandle;
    int mVertexBufferId;
    int mTextureBufferId;
    int mIndicesBufferId;
    int mVAOId;
    FloatBuffer mVertexBuffer;
    FloatBuffer mTextureBuffer;

    int[] indexesFace = new int[12];
    public float[] vertices = new float[12];

    public float[] textures = {
            0, 0, 1, 0, 1, 1, 0, 1,
    };

    byte[] indices = {
            0, 1, 2, 0, 2, 3,
    };

    public Shape10(Context context, int otMatrix) {
        maPositionHandle = glGetAttribLocation(ShaderUtils.programId, "aPosition");
        maTextureHandle = glGetAttribLocation(ShaderUtils.programId, "aTexture");
        this.otMatrix = otMatrix;
        cubes = new Cube[5];
        loadGLTexture(context);
        addIndexesFace();
        addValue();
    }

    private void addIndexesFace() {
        System.arraycopy(MathUtils.vertices, 0, vertices, 0, vertices.length);
        initVertexData();
        indexesFace[0] = mVAOId;
        indexesFace[1] = mIndicesBufferId;
        System.arraycopy(MathUtils.vertices, 12, vertices, 0, vertices.length);
        initVertexData();
        indexesFace[2] = mVAOId;
        indexesFace[3] = mIndicesBufferId;
        System.arraycopy(MathUtils.vertices, 24, vertices, 0, vertices.length);
        initVertexData();
        indexesFace[4] = mVAOId;
        indexesFace[5] = mIndicesBufferId;
        System.arraycopy(MathUtils.vertices, 36, vertices, 0, vertices.length);
        initVertexData();
        indexesFace[6] = mVAOId;
        indexesFace[7] = mIndicesBufferId;
        System.arraycopy(MathUtils.vertices, 48, vertices, 0, vertices.length);
        initVertexData();
        indexesFace[8] = mVAOId;
        indexesFace[9] = mIndicesBufferId;
        System.arraycopy(MathUtils.vertices, 60, vertices, 0, vertices.length);
        initVertexData();
        indexesFace[10] = mVAOId;
        indexesFace[11] = mIndicesBufferId;
    }

    public void initVertexData() {
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asFloatBuffer();
        mVertexBuffer.put(vertices).position(0);

        ByteBuffer tbb = ByteBuffer.allocateDirect(textures.length * 4);
        tbb.order(ByteOrder.nativeOrder());
        mTextureBuffer = tbb.asFloatBuffer();
        mTextureBuffer.put(textures).position(0);

        ByteBuffer mIndicesBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndicesBuffer.order(ByteOrder.nativeOrder());
        mIndicesBuffer.put(indices).position(0);

        int[] vaoIds = new int[1];
        glGenVertexArrays(1, vaoIds, 0);
        mVAOId = vaoIds[0];
        glBindVertexArray(mVAOId);

        int[] bufferIds = new int[3];
        glGenBuffers(3, bufferIds, 0);

        mVertexBufferId = bufferIds[0];
        glBindBuffer(GL_ARRAY_BUFFER, mVertexBufferId);
        glBufferData(GL_ARRAY_BUFFER, vertices.length * 4, mVertexBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(maPositionHandle);
        glVertexAttribPointer(maPositionHandle, 3, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        mTextureBufferId = bufferIds[1];
        glBindBuffer(GL_ARRAY_BUFFER, mTextureBufferId);
        glBufferData(GL_ARRAY_BUFFER, textures.length * 4, mTextureBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(maTextureHandle);
        glVertexAttribPointer(maTextureHandle, 2, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        mIndicesBufferId = bufferIds[2];
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, mIndicesBufferId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices.length, mIndicesBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        glBindVertexArray(0);
    }

    private void addValue() {
        cubes[0] = new Cube(indexesFace);
        cubes[1] = new Cube(indexesFace);
        cubes[2] = new Cube(indexesFace);
        cubes[3] = new Cube(indexesFace);
        cubes[4] = new Cube(indexesFace);

        defaultValue();
    }

    public void loadGLTexture(Context context) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.face10);
        glGenTextures(1, textureId, 0);
        glBindTexture(GL_TEXTURE_2D, textureId[0]);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        GLUtils.texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();
    }

    public void draw() {
        for (Cube cube : cubes) {
            glUniformMatrix4fv(otMatrix, 1, false, cube.mT, 0);
            for (int i = 0; i < cube.cubeFaces.length; i++) {
                cube.cubeFaces[i].draw(textureId[0]);
            }
        }
    }

    public void drawZero() {
        for (int i = 0; i < cubes[0].cubeFaces.length; i++) {
            cubes[0].cubeFaces[i].draw(textureId[0]);
        }
    }

    public void defaultValue() {
        for (Cube cube : cubes) {
            cube.mT[0] = 1;
            cube.mT[1] = 0;
            cube.mT[2] = 0;
            cube.mT[3] = 0;
            cube.mT[4] = 0;
            cube.mT[5] = 1;
            cube.mT[6] = 0;
            cube.mT[7] = 0;
            cube.mT[8] = 0;
            cube.mT[9] = 0;
            cube.mT[10] = 1;
            cube.mT[11] = 0;
        }
        cubes[0].mT[12] = -.5f;
        cubes[0].mT[13] = .5f;
        cubes[0].mT[14] = .5f;
        cubes[1].mT[12] = .5f;
        cubes[1].mT[13] = .5f;
        cubes[1].mT[14] = .5f;
        cubes[2].mT[12] = 1.5f;
        cubes[2].mT[13] = .5f;
        cubes[2].mT[14] = .5f;
        cubes[3].mT[12] = 1.5f;
        cubes[3].mT[13] = -.5f;
        cubes[3].mT[14] = .5f;
        cubes[4].mT[12] = 1.5f;
        cubes[4].mT[13] = -1.5f;
        cubes[4].mT[14] = .5f;
    }
}
