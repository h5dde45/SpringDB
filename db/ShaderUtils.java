package tmvf.wobbler;

import static android.opengl.GLES32.GL_COMPILE_STATUS;
import static android.opengl.GLES32.GL_FRAGMENT_SHADER;
import static android.opengl.GLES32.GL_LINK_STATUS;
import static android.opengl.GLES32.GL_VERTEX_SHADER;
import static android.opengl.GLES32.glAttachShader;
import static android.opengl.GLES32.glCompileShader;
import static android.opengl.GLES32.glCreateProgram;
import static android.opengl.GLES32.glCreateShader;
import static android.opengl.GLES32.glDeleteProgram;
import static android.opengl.GLES32.glDeleteShader;
import static android.opengl.GLES32.glGetProgramiv;
import static android.opengl.GLES32.glGetShaderiv;
import static android.opengl.GLES32.glLinkProgram;
import static android.opengl.GLES32.glShaderSource;
import static android.opengl.GLES32.glUseProgram;

import android.content.Context;

public class ShaderUtils {
    public static int programId;

    public static void createProgram(Context context) {
        String text = FileUtils.readFromRaw(context, R.raw.vertex_shader);
        int vertexShaderId = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShaderId, text);
        glCompileShader(vertexShaderId);

        final int[] compileStatus = new int[1];
        glGetShaderiv(vertexShaderId, GL_COMPILE_STATUS, compileStatus, 0);
        if (compileStatus[0] == 0) {
            glDeleteShader(vertexShaderId);
            System.err.println("========vertexShader");
        }
        String text2 = FileUtils.readFromRaw(context, R.raw.fragment_shader);
        int fragmentShaderId = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShaderId, text2);
        glCompileShader(fragmentShaderId);

        final int[] compileStatus2 = new int[1];
        glGetShaderiv(fragmentShaderId, GL_COMPILE_STATUS, compileStatus2, 0);
        if (compileStatus2[0] == 0) {
            glDeleteShader(fragmentShaderId);
            System.err.println("========fragmentShader");
        }
        programId = glCreateProgram();
        glAttachShader(programId, vertexShaderId);
        glAttachShader(programId, fragmentShaderId);
        glLinkProgram(programId);

        final int[] linkStatus = new int[1];
        glGetProgramiv(programId, GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0) {
            glDeleteProgram(programId);
            System.err.println("========program");
        }
        glUseProgram(programId);
    }
}
