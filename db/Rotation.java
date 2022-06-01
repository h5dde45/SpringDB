package tmvf.wobbler;

import static android.opengl.GLES20.GL_ARRAY_BUFFER;
import static android.opengl.GLES20.GL_CLAMP_TO_EDGE;
import static android.opengl.GLES20.GL_ELEMENT_ARRAY_BUFFER;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_LINEAR;
import static android.opengl.GLES20.GL_STATIC_DRAW;
import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TEXTURE_MAG_FILTER;
import static android.opengl.GLES20.GL_TEXTURE_MIN_FILTER;
import static android.opengl.GLES20.GL_TEXTURE_WRAP_S;
import static android.opengl.GLES20.GL_TEXTURE_WRAP_T;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.GL_UNSIGNED_BYTE;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindBuffer;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glBufferData;
import static android.opengl.GLES20.glDrawElements;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGenBuffers;
import static android.opengl.GLES20.glGenTextures;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glTexParameterf;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.GLES30.glBindVertexArray;
import static android.opengl.GLES30.glGenVertexArrays;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Rotation {
    public int maPositionHandle;
    public int maTextureHandle;
    int mVertexBufferId;
    int mTextureBufferId;
    int mIndicesBufferId;
    int mVAOId;
    FloatBuffer mVertexBuffer;
    FloatBuffer mTextureBuffer;
    public int[] textureId = new int[1];
    int otMatrix;

    public float[] vertices = {
            -1 / 7f, 1, 0,
            1 / 7f, 1, 0,
            1 / 7f, 1 - 2 / 7f * ProjectionMatrix.width / ProjectionMatrix.height, 0,
            -1 / 7f, 1 - 2 / 7f * ProjectionMatrix.width / ProjectionMatrix.height, 0
    };

    public float[] textures = {
            0, 0, 1, 0, 1, 1, 0, 1,
    };

    byte[] indices = {
            0, 1, 2, 0, 2, 3,
    };

    public float[] mT = new float[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, -1, 1
    };

    public Rotation(Context context, int otMatrix) {
//        vertices[1] = vertices[4] = .75f * ProjectionMatrix.width / ProjectionMatrix.height - 1;
        maPositionHandle = glGetAttribLocation(ShaderUtils.programId, "aPosition");
        maTextureHandle = glGetAttribLocation(ShaderUtils.programId, "aTexture");
        this.otMatrix = otMatrix;
        loadGLTexture(context);
        initVertexData();
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

    public void loadGLTexture(Context context) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.rotation);
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
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, textureId[0]);
        glBindVertexArray(mVAOId);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, mIndicesBufferId);
        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_BYTE, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }
}
