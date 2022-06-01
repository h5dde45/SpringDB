package tmvf.wobbler;

import static android.opengl.GLES32.GL_ELEMENT_ARRAY_BUFFER;
import static android.opengl.GLES32.GL_TEXTURE0;
import static android.opengl.GLES32.GL_TEXTURE_2D;
import static android.opengl.GLES32.GL_TRIANGLES;
import static android.opengl.GLES32.GL_UNSIGNED_BYTE;
import static android.opengl.GLES32.glActiveTexture;
import static android.opengl.GLES32.glBindBuffer;
import static android.opengl.GLES32.glBindTexture;
import static android.opengl.GLES32.glBindVertexArray;
import static android.opengl.GLES32.glDrawElements;

public class CubeFace {
    int mVAOId;
    int mIndicesBufferId;

    public CubeFace(int mVAOId, int mIndicesBufferId) {
        this.mVAOId = mVAOId;
        this.mIndicesBufferId = mIndicesBufferId;
    }

    public void draw(int id) {
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, id);
        glBindVertexArray(mVAOId);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, mIndicesBufferId);
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_BYTE, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }
}
